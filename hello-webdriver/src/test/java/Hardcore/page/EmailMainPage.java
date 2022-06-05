package Hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailMainPage {
    private WebDriver driver;
    public EmailMainPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public EmailMainPage generateEmail () {
        //Go to 'random'
        waitForElementLocatedBy(driver,By.xpath("//a[text()='Random']")).click();
        //Copy generated email
        waitForElementLocatedBy(driver,By.xpath("//span[text()='Copy to clipboard']/ancestor::button")).click();
        return this;
    }
    public CalculationResultsPage goBackToCalc (String calcTab) throws InterruptedException {
        driver.switchTo().window(calcTab);
        Thread.sleep(1000);
        return new CalculationResultsPage(driver);
    }
    public String getPriceFromEmail() throws InterruptedException {
        //Click "Check Inbox" btn
        driver.findElement(By.xpath("//span[text()='Check Inbox']/parent::button")).click();
        //refresh the inbox
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='refresh']")).click();
        //get price
        String emailFrameXpath = "//iframe[@id='ifmail']";
        WebElement emailFrameElement = driver.findElement(By.xpath(emailFrameXpath));
        Thread.sleep(2000);
        driver.switchTo().frame(emailFrameElement);
        String priceRow = driver.findElement(By.xpath("//h3[contains(text(),'Total Estimated Monthly Cost')]/parent::td/following-sibling::td")).getText();
        String price = priceRow.substring("USD ".length());
        System.out.println(price);
        return price;
    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
