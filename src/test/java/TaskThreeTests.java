import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import adapterfacade.thirdtask.Company;
import adapterfacade.thirdtask.CompanyFacade;

public class TaskThreeTests {
    @Test
    public void testCompanyFacade() {
        CompanyFacade facade = new CompanyFacade();
        try {
            Company company = facade.getCompanyInfo("ucu.edu.ua");
            String expectedOutput = """
                Company{name='УКУ', description='Description from GPT: \
                Default Description from ChatGPT for ucu.edu.ua; ', \
                logo='https://ucu.edu.ua/wp-content/themes/ucu/img/favicon/\
                favicon-32x32.png'}
                """;
            Assertions.assertEquals(expectedOutput.trim(), 
                                    company.toString().trim());
        } catch (IOException e) {
            Assertions.fail("Error: " + e.getMessage());
        }
    }
}