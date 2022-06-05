package HurtMePlenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CalculationResultsPage {
    private WebDriver driver;
    public CalculationResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String CheckVMFieldData () {
        String VMClass = driver
                .findElement(By.xpath("//div[contains(text(), 'Provisioning model')]"))
                .getText()
                .substring("Provisioning model: ".length());
        return VMClass;
    }
    public String CheckInstanceTypeFieldData () {
        String instanceTypeAllParentText = driver
                .findElement(By.xpath("//div[contains(text(), 'Instance type')]"))
                .getText();
        String instanceTypeChildText = driver
                .findElement(By.xpath("//div[contains(text(), 'Instance type')]/div"))
                .getText();
        String instanceTypeValue = instanceTypeAllParentText.substring("Instance type: ".length(), instanceTypeAllParentText.length()-instanceTypeChildText.length()-1);
        return instanceTypeValue;
    }
    public String CheckRegionTypeFieldData () {
        String region = driver
                .findElement(By.xpath("//div[contains(text(), 'Region:')]"))
                .getText()
                .substring("Region: ".length());
        return region;
    }
    public String CheckLocalSSDFieldData () {
        String localSSDAllParentText = driver
                .findElement(By.xpath("//div[contains(text(), 'Local SSD')]"))
                .getText();
        String localSSDAllChildText = driver
                .findElement(By.xpath("//div[contains(text(), 'Local SSD')]/div"))
                .getText();
        String res = localSSDAllParentText.substring("Local SSD: ".length(), localSSDAllParentText.length()-localSSDAllChildText.length()-1);
        return res;
    }
    public String CheckCommitmentTermFieldData () {
        String commitmentTerm = driver
                .findElement(By.xpath("//div[contains(text(), 'Commitment term')]"))
                .getText()
                .substring("Commitment term: ".length());
        return commitmentTerm;
    }
    public Double CheckPrice () {
        String price = driver
                .findElement(By.xpath("//h2/b[contains(text(), 'Total Estimated Cost')]"))
                .getText()
                .substring("Total Estimated Cost: USD ".length());

        Double pricePerMonth = Double.valueOf(price.substring(0 ,price.length()-" per 1 month".length()));
        return pricePerMonth;
    }
}
