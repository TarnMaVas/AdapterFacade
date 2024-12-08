package adapterfacade.thirdtask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class PDLReader {
    public Company getCompanyInfo(String website) throws IOException {
        String API_KEY = "";
        URL url = new URL(
        "https://api.peopledatalabs.com/v5/company/enrich?website=" + website
        );
        HttpURLConnection connection = 
                    (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == 401) {
            System.out.println("I have no PDL API key :(");
            return new Company();
        } else if (responseCode != 200) {
            System.out.println(
                "Error: Server returned HTTP response code: " + responseCode
                );
            return new Company();
        }

        String text = new Scanner(connection.getInputStream())
                        .useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);

        Company company = new Company();
        company.setName(jsonObject.optString("name"));
        company.setDescription(jsonObject.optString("description"));
        company.setLogo(jsonObject.optString("logo"));

        return company;
    }
}