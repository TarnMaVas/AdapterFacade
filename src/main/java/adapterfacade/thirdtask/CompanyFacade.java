package adapterfacade.thirdtask;

import java.io.IOException;

public class CompanyFacade {
    private final PDLReader pdlReader;
    private final WebScrapingChatGPTReader webScrapingChatGPTReader;
    private final BrandfetchReader brandfetchReader;

    public CompanyFacade() {
        this.pdlReader = new PDLReader();
        this.webScrapingChatGPTReader = new WebScrapingChatGPTReader();
        this.brandfetchReader = new BrandfetchReader();
    }

    public Company getCompanyInfo(String website) throws IOException {
        Company company = new Company();
        StringBuilder descriptionBuilder = new StringBuilder();

        Company pdlCompany = pdlReader.getCompanyInfo(website);
        company.setName(pdlCompany.getName());
        company.setLogo(pdlCompany.getLogo());
        if (pdlCompany.getDescription() != null) {
            descriptionBuilder.append("Description from PDL: ")
                    .append(pdlCompany.getDescription()).append("; ");
        }

        Company webScrapingCompany = webScrapingChatGPTReader
                                        .getCompanyInfo(website);
        if (company.getName() == null) {
            company.setName(webScrapingCompany.getName());
        }
        if (company.getLogo() == null) {
            company.setLogo(webScrapingCompany.getLogo());
        }
        if (webScrapingCompany.getDescription() != null) {
            descriptionBuilder.append("Description from GPT: ")
                        .append(webScrapingCompany.getDescription())
                        .append("; ");
        }

        Company brandfetchCompany = brandfetchReader.getCompanyInfo(website);
        if (company.getName() == null) {
            company.setName(brandfetchCompany.getName());
        }
        if (company.getLogo() == null) {
            company.setLogo(brandfetchCompany.getLogo());
        }
        if (brandfetchCompany.getDescription() != null) {
            descriptionBuilder.append("Description from Brandfetch: ")
            .append(brandfetchCompany.getDescription()).append("; ");
        }

        company.setDescription(descriptionBuilder.toString());

        return company;
    }
}