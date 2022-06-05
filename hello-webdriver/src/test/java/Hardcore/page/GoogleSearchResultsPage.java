package Hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage {
    private WebDriver driver;
    @FindBy(xpath = "//div/a[@class='gs-title']/b[text()='Google Cloud Pricing Calculator']")
    private WebElement calculatorLink;
    public GoogleSearchResultsPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public GoogleCalculatorPage openCalculator() {
        //Open calculator page
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div/a[@class='gs-title']/b[text()='Google Cloud Pricing Calculator']")));
        calculatorLink.click();
        return new GoogleCalculatorPage(driver);
    }
}
