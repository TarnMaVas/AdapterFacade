package adapterfacade.firsttask;

public class FacebookUserAdapter implements User {
    private final FacebookUser facebookUser;

    public FacebookUserAdapter(FacebookUser facebookUser) {
        this.facebookUser = facebookUser;
    }

    @Override
    public String getEmail() {
        return facebookUser.getEmail();
    }

    @Override
    public String getCountry() {
        return facebookUser.getUserCountry();
    }

    @Override
    public long getLastActiveTime() {
        return facebookUser.getUserActiveTime();
    }
    
}
