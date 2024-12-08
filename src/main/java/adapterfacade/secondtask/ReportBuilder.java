package adapterfacade.secondtask;

public class ReportBuilder {

    private final DBAdapter db;

    public ReportBuilder(DBAdapter db) {
        this.db = db;
    }

    public String buildReport() {

        String out = "Building report...\n";
        out += "User data: " + db.getUserData() + '\n';
        out += "Statistical data: " + db.getStatisticalData() + '\n';

        return out;
    }
}
