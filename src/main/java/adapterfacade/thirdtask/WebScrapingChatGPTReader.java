package adapterfacade.thirdtask;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebScrapingChatGPTReader {

    private static final String API_KEY = "it_doesn't_work";
    private static final String OPENAI_API_URL = 
            "https://api.openai.com/v1/completions";

    public Company getCompanyInfo(String website) {
        Company company = new Company();

        try {
            Document doc = Jsoup.connect("https://" + website).get();
            Element nameElement = doc.selectFirst(
                        "meta[property=og:site_name]"
                                    );
            Element logoElement = doc.selectFirst("link[rel=icon]");

            if (nameElement != null) {
                company.setName(nameElement.attr("content"));
            } else {
                company.setName("Default Name from WebScraping");
            }

            if (logoElement != null) {
                company.setLogo(logoElement.attr("href"));
            } else {
                company.setLogo("Default Logo URL from WebScraping");
            }

            String description = getChatGPTDescription(website);
            company.setDescription(description);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return company;
    }

    private String getChatGPTDescription(String website) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        JsonObject json = new JsonObject();
        json.addProperty("model", "gpt-3.5-turbo");

        JsonArray messages = new JsonArray();
        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty(
                    "content",
"Provide a brief description for the company with the website: " + website);
        messages.add(userMessage);

        json.add("messages", messages);
        json.addProperty("max_tokens", 50);
        json.addProperty("temperature", 0.5);

        RequestBody body = RequestBody.create(json.toString(), mediaType);
        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseString = response.body().string();
                return parseResponse(responseString);
            } else if (response.code() == 429) {
                System.out.println(
                    "API call failed with status: 429 ( I'm broke :( )"
                    );
                return "Default Description from ChatGPT for " + website;
            } else {
                System.out.println(
                    "API call failed with status: " + response.code()
                    );
                return "Default Description from ChatGPT for " + website;
            }
        } catch (IOException e) {
            System.out.println("Error calling OpenAI API: " + e.getMessage());
            return "Default Description from ChatGPT for " + website;
        }
    }

    private String parseResponse(String responseString) {
        try {
            JsonObject jsonResponse = JsonParser
                                        .parseString(responseString)
                                        .getAsJsonObject();
            return jsonResponse.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .get("text").getAsString().trim();
        } catch (Exception e) {
            System.out.println(
                "Error parsing API response: " + e.getMessage()
                );
            return "Error parsing GPT response.";
        }
    }
}