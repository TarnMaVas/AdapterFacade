package adapterfacade.secondtask;

public class DBAdapter {
    private final БазаДаних db;

    public DBAdapter() {
        this.db = new БазаДаних();
    }

    public String getUserData() {
        return db.отриматиДаніКористувача();
    }

    public String getStatisticalData() {
        return db.отриматиСтатистичніДані();
    }

    public БазаДаних getSrc() {
        return db;
    }
}
