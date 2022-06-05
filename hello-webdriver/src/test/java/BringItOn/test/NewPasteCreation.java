package BringItOn.test;


import BringItOn.page.PastebinMainPageNew;
import BringItOn.page.PastebinPastePage;
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

public class NewPasteCreation {
        private WebDriver driver;
        String title = "how to gain dominance among developers";
        String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";

        @BeforeMethod(alwaysRun = true)
        public void browserSetup(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Test(description = "test1")
        public void pasteCreationResults() throws InterruptedException {
            String pageTitle = new PastebinMainPageNew(driver)
                    .openPage()
                    .createPaste(code, title)
                    .getPageTitleText();

            Assert.assertTrue(pageTitle.contains(title), "Page title does not correspond to Paste Name / Title value");
            Assert.assertTrue(new PastebinPastePage(driver, title).getTextStyle().equals("#c20cb9"), "Code is not highlighted");
            Assert.assertTrue(new PastebinPastePage(driver, title).getCreatedPasteContent().equals(code), "Created paste code does not equal with entered one during paste creation");
            Thread.sleep(2000);
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
