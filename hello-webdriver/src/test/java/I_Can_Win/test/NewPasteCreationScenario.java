package I_Can_Win.test;

import I_Can_Win.page.PastebinMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewPasteCreationScenario {
        private WebDriver driver;

        @BeforeMethod(alwaysRun = true)
        public void browserSetup(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Test(description = "Just first test")
        public void pasteCreation() throws InterruptedException {

            new PastebinMainPage(driver).openPage().createPaste();
            Assert.assertTrue(1 > 0, "Test");//Just for example, there is no condition in the task
            Thread.sleep(3000);
        }

        @AfterMethod(alwaysRun = true)
        public void browserTearDown(){
            driver.quit();
            driver=null;
        }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
