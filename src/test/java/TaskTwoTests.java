import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adapterfacade.secondtask.AuthAdapter;
import adapterfacade.secondtask.DBAdapter;
import adapterfacade.secondtask.ReportBuilder;

class TaskTwoTests {

    private DBAdapter dbAdapter;
    private ReportBuilder reportBuilder;
    private AuthAdapter authAdapter;

    @BeforeEach
    void setUp() {
        dbAdapter = new DBAdapter();
        reportBuilder = new ReportBuilder(dbAdapter);
        authAdapter = new AuthAdapter();
    }

    @Test
    void testBuildReport() {
        String result = reportBuilder.buildReport();
        String expectedOutput = """
            Building report...
            User data: %s
            Statistical data: %s
            """.formatted(dbAdapter.getUserData(),
                        dbAdapter.getStatisticalData());

        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void testAuthenticate() {
        boolean result = authAdapter.authenticate(dbAdapter);
        Assertions.assertTrue(result);
    }
}