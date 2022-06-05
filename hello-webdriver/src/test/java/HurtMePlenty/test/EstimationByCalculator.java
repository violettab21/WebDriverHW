package HurtMePlenty.test;

import HurtMePlenty.page.CalculationResultsPage;
import HurtMePlenty.page.GoogleCloudMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EstimationByCalculator {
    private WebDriver driver;
    private String searchTerm = "Google Cloud Platform Pricing Calculator";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Check calculated price")
    public void checkEstimationResults() throws InterruptedException {
        new GoogleCloudMainPage(driver)
                .openPage()
                .searchForCalculator(searchTerm)
                .openCalculator()
                .addTooEstimate();

        String provisioningModel = new CalculationResultsPage(driver).CheckVMFieldData();

        String instanceType = new CalculationResultsPage(driver).CheckInstanceTypeFieldData();

        String region = new CalculationResultsPage(driver).CheckRegionTypeFieldData();

        String localSSD = new CalculationResultsPage(driver).CheckLocalSSDFieldData();

        String commitmentTerm = new CalculationResultsPage(driver).CheckCommitmentTermFieldData();

        Double price = new CalculationResultsPage(driver).CheckPrice();
        System.out.println(price);

        Assert.assertTrue(provisioningModel.equals("Regular"), "VM Class does not equal to entered value.");
        Assert.assertTrue(instanceType.equals("n1-standard-8"), "Instance type does not equal to entered value.");
        Assert.assertTrue(region.equals("Frankfurt"), "Region does not equal to entered value.");
        Assert.assertTrue(localSSD.equals("2x375 GiB"), "Local SSD does not equal to entered value.");
        Assert.assertTrue(commitmentTerm.equals("1 Year"), "Commitment Term does not equal to entered value.");
        Assert.assertTrue(price.equals(270.30), "Price does not equal to entered value.");
        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
                driver.quit();
                driver=null;
    }

}
