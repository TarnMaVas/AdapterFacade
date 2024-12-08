package adapterfacade.firsttask;

public class MessageSender {

    private static final long HOUR = 3600000;

    public String send(String text, User user, String country) {

        long validTime = System.currentTimeMillis() - HOUR;
        if (user.getCountry().equalsIgnoreCase(country)
            && user.getLastActiveTime() > validTime) {
            return "Sending message: "
                + text + " to user with email: " + user.getEmail();
        } else {
            return
                "The user is not active or not from the same country";

        }
    }
    
}
