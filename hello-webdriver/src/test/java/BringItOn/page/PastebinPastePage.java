package BringItOn.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;

public class PastebinPastePage {
    private WebDriver driver;
    private final String title;
    public PastebinPastePage(WebDriver driver, String title){
        this.driver = driver;
        this.title = title;
        PageFactory.initElements(driver, this);
    }
    public String getPageTitleText() {
        String pageName = driver.getTitle();
        return pageName;
    }
    public String getTextStyle() {
        String commandColor = driver.findElement(By.xpath("//div/span[text() = 'git config']")).getCssValue("color");
        Color color = Color.fromString(commandColor);
        return color.asHex();
    }
    public String getCreatedPasteContent() {
        String pasteCode = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/ol")).getText();
        return pasteCode;
    }
}
