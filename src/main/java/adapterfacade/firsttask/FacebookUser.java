package adapterfacade.firsttask;

public class FacebookUser {
    private final String email;
    private final String country;
    private final long activeTime;

    public FacebookUser(String email, String country, long activeTime) {
        this.email = email;
        this.country = country;
        this.activeTime = activeTime;
    }
    
    public String getEmail() {
        return email;
    }

    public String getUserCountry() {
        return country;
    }

    public long getUserActiveTime() {
        return activeTime;
    }
}
