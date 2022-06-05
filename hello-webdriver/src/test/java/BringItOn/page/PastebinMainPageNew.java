package BringItOn.page;

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

public class PastebinMainPageNew {
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    public PastebinMainPageNew(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public PastebinMainPageNew openPage() {
        driver.get(HOMEPAGE_URL);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timout for waiting was expected");
        return this;
    }
    public PastebinPastePage createPaste (String code, String param) throws InterruptedException {
        //Enter code to the Code field
        WebElement codeInput = waitForElementLocatedBy(driver, By.id("postform-text"));
        codeInput.sendKeys(code);
        //Select "Syntax Highlighting"
        driver.findElement(By.xpath("//label[text()='Syntax Highlighting:']/following-sibling::div")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li/ul/li[text()='Bash']")).click();
        //Select "Paste expiration"
        driver.findElement(By.xpath("//label[text()='Paste Expiration:']/following-sibling::div")).click();
        driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='10 Minutes']")).click();
        //Fill in paste title
        WebElement pasteName = waitForElementLocatedBy(driver, By.id("postform-name"));
        pasteName.sendKeys(param);
        //Create Paste
        WebElement createPasteBtn = driver.findElement(By.xpath("//*[@class='btn -big']"));
        createPasteBtn.click();
        Thread.sleep(3000);
        return new PastebinPastePage(driver, param);
    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
