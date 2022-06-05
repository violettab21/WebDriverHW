package Hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CalculationResultsPage {
    private WebDriver driver;
    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/";
    public CalculationResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getPriceFromCalc () {
        String price = driver
                .findElement(By.xpath("//h2/b[contains(text(), 'Total Estimated Cost')]"))
                .getText()
                .substring("Total Estimated Cost: USD ".length());

        String pricePerMonth = price.substring(0 ,price.length()-" per 1 month".length());
        return pricePerMonth;
    }
    public CalculationResultsPage sendEmail () throws IOException, UnsupportedFlavorException, InterruptedException, AWTException {
        //Click 'Email' btn
        driver.findElement(By.xpath("//div/button[@id='email_quote']")).click();
        String calcTab = driver.getWindowHandle();//save calc tab
        openEmailGenerator()
                .generateEmail()
                .goBackToCalc(calcTab);
        //go to second frame
        String firstFrameXpath = "//*[@id='cloud-site']/devsite-iframe/iframe";
        String secondFrameXpath = "//*[@id='maia-main']/div/div/iframe";
        WebElement firstFrameElement = driver.findElement(By.xpath(firstFrameXpath));
        Thread.sleep(1000);
        driver.switchTo().frame(firstFrameElement);
        WebElement secondFrameElement = driver.findElement(By.xpath(secondFrameXpath));
        driver.switchTo().frame(secondFrameElement);

        pasteGeneratedEmail();
        //Click send
        driver.findElement(By.xpath("//md-dialog-actions/button[contains(text(),'Send Email')]")).click();
        switchToEmailTab();
        return this;
    }
    public EmailMainPage openEmailGenerator () throws InterruptedException, AWTException {
       /* String oldTab = driver.getWindowHandle();*/
        openNewTab();
       /* String newTab = driver.getWindowHandle();*/
        driver.get(EMAIL_GENERATOR_URL);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        return new EmailMainPage (driver);
    }
    public void switchToEmailTab() throws AWTException, InterruptedException {
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
    }
    public void openNewTab() throws AWTException, InterruptedException {
        //open new tab
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
        Thread.sleep(1000);
        switchToEmailTab();
    }
    public void pasteGeneratedEmail() throws IOException, UnsupportedFlavorException {
        //find email field
        WebElement input = driver.findElement(By.xpath("//div/md-input-container/label[text() = 'Email ']/following-sibling::input"));
        //Get copied string
        String myText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        //Paste string to the email
        input.sendKeys(myText);
    }
}
