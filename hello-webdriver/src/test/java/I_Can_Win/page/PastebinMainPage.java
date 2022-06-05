package I_Can_Win.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.NoSuchElementException;

public class PastebinMainPage {
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    public PastebinMainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timout for waiting was expected");
        return this;
    }
    public PastebinMainPage createPaste () {
        //Fill in code
        WebElement codeInput = waitForElementLocatedBy(driver, By.id("postform-text"));
        codeInput.sendKeys("Hello from WebDriver");
        //Select "Paste Expiration"
        driver.findElement(By.xpath("//label[text()='Paste Expiration:']/following-sibling::div")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='10 Minutes']")).click();
        //Fill in Paste Name
        WebElement pasteName = waitForElementLocatedBy(driver, By.id("postform-name"));
        pasteName.sendKeys("helloweb");
        //Click [Create New Paste]
        WebElement createPasteBtn = driver.findElement(By.xpath("//*[@class='btn -big']"));
        createPasteBtn.click();
        return this;
    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
