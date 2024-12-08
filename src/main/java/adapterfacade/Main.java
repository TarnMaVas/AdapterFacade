package adapterfacade;

import java.io.IOException;

import adapterfacade.firsttask.FacebookUser;
import adapterfacade.firsttask.FacebookUserAdapter;
import adapterfacade.firsttask.MessageSender;
import adapterfacade.firsttask.TwitterUser;
import adapterfacade.firsttask.TwitterUserAdapter;
import adapterfacade.secondtask.AuthAdapter;
import adapterfacade.secondtask.DBAdapter;
import adapterfacade.secondtask.ReportBuilder;
import adapterfacade.thirdtask.Company;
import adapterfacade.thirdtask.CompanyFacade;

public class Main {

    private static final long ALMOST_NOW = 100;
    private static final long LONG_AGO = 4600000;

    private static final long VALID = System.currentTimeMillis() - ALMOST_NOW;
    private static final long INVALID = System.currentTimeMillis() - LONG_AGO;

    public static void main(String[] args) {
        FacebookUser facebookUser = new FacebookUser(
                                    "one@example.com",
                                    "USA",
                                    VALID
                                    );

        TwitterUser twitterUser = new TwitterUser(
                                    "two@example.com",
                                    "Canada",
                                    INVALID
                                    );

        MessageSender messageSender = new MessageSender();

        System.out.println(messageSender.send(
            "Hello!", new FacebookUserAdapter(facebookUser), "USA"
            ));
        System.out.println(messageSender.send(
            "Hello!", new TwitterUserAdapter(twitterUser), "Canada"
            ));

        System.out.println();

        DBAdapter db = new DBAdapter();
        AuthAdapter authAdapter = new AuthAdapter();
        if (authAdapter.authenticate(db)) {
            ReportBuilder br = new ReportBuilder(db);
            System.out.println(br.buildReport());
        }

        System.out.println();

        CompanyFacade facade = new CompanyFacade();
        try {
            Company company = facade.getCompanyInfo("ucu.edu.ua");
            System.out.println();
            System.out.println(company);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}