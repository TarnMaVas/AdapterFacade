package adapterfacade.firsttask;

public class MessageSender {
    public String send(String text, User user, String country) {

        long validTime = System.currentTimeMillis() - 3600000;
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
