package HurtMePlenty.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class GoogleCloudMainPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;
    @FindBy(xpath = "//input[@class = 'devsite-search-field devsite-search-query']")
    private WebElement searchInput;
    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMainPage openPage() {
        //open main page
        driver.get(HOMEPAGE_URL);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timout for waiting was expected");
        return this;
    }
    public GoogleSearchResultsPage searchForCalculator(String param) {
     //Fill in search field with param and start search process
     searchInput.sendKeys(param);
     searchInput.sendKeys(Keys.ENTER);
        return new GoogleSearchResultsPage(driver);
    }
}
