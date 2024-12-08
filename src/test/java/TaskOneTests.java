import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adapterfacade.firsttask.FacebookUser;
import adapterfacade.firsttask.FacebookUserAdapter;
import adapterfacade.firsttask.MessageSender;
import adapterfacade.firsttask.TwitterUser;
import adapterfacade.firsttask.TwitterUserAdapter;
import adapterfacade.firsttask.User;

class TaskOneTests {

    private static final String USA = "USA";
    private static final String CANADA = "Canada";
    private static final String ACTIVE_EMAIL = "activeuser@example.com";
    private static final String INACTIVE_EMAIL = "inactiveuser@example.com";
    private static final String DIFFERENT_COUNTRY_EMAIL =
                            "differentcountryuser@example.com";
    private static final long TWO_HOURS_IN_MILLIS = 7200000;

    private MessageSender messageSender;
    private User activeUser;
    private User inactiveUser;
    private User differentCountryUser;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSender();

        FacebookUser activeFacebookUser = new FacebookUser(
            ACTIVE_EMAIL, USA, System.currentTimeMillis());
        activeUser = new FacebookUserAdapter(activeFacebookUser);

        FacebookUser inactiveFacebookUser = new FacebookUser(
            INACTIVE_EMAIL, USA, 
                            System.currentTimeMillis() - TWO_HOURS_IN_MILLIS);
        inactiveUser = new FacebookUserAdapter(inactiveFacebookUser);

        TwitterUser differentCountryTwitterUser = new TwitterUser(
            DIFFERENT_COUNTRY_EMAIL, CANADA, System.currentTimeMillis());
        differentCountryUser = new TwitterUserAdapter(
                                        differentCountryTwitterUser
                                        );
    }

    @Test
    void testSendMessage_UserActiveAndFromSameCountry() {
        String result = messageSender.send("Hello", activeUser, USA);
        Assertions.assertEquals(
        "Sending message: Hello to user with email: activeuser@example.com", 
            result);
    }

    @Test
    void testSendMessage_UserNotActive() {
        String result = messageSender.send("Hello", inactiveUser, USA);
        Assertions.assertEquals(
            "The user is not active or not from the same country", 
            result);
    }

    @Test
    void testSendMessage_UserFromDifferentCountry() {
        String result = messageSender.send(
                                       "Hello",
                                            differentCountryUser,
                                            USA
                                            );
        Assertions.assertEquals(
            "The user is not active or not from the same country", 
            result);
    }
}