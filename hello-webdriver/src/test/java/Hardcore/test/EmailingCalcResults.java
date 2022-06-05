package Hardcore.test;

import Hardcore.page.CalculationResultsPage;
import Hardcore.page.EmailMainPage;
import Hardcore.page.GoogleCloudMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class EmailingCalcResults {
    private WebDriver driver;
    private String searchTerm = "Google Cloud Platform Pricing Calculator";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Check calculated price in the email")
    public void checkEmailResults() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        new GoogleCloudMainPage(driver)
                .openPage()
                .searchForCalculator(searchTerm)
                .openCalculator()
                .addTooEstimate();
        String calcPrice = new CalculationResultsPage(driver).getPriceFromCalc();

        new CalculationResultsPage(driver).sendEmail();

        String emailPrice = new EmailMainPage(driver).getPriceFromEmail();

        Assert.assertTrue(emailPrice.equals(calcPrice), "Price in the email does not equal price from calculator.");
        Thread.sleep(3000);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
                driver.quit();
                driver=null;
    }

}
