package HurtMePlenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalculatorPage {
    private WebDriver driver;
    public GoogleCalculatorPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
    }
    public GoogleCalculatorPage addTooEstimate () throws InterruptedException {

        new GoogleCalculatorPage(driver)
                .goToIFrame()
                .addInstancesFieldsValues()
                .addGPUsFieldsValues()
                .addAdditionalFieldsValues();
        //Click 'ADD TO ESTIMATE'
        WebElement addToEstimateBtn = driver.findElement(By.xpath("//form[@name='ComputeEngineForm']/div/button"));
        addToEstimateBtn.click();
        return this;
    }
    public GoogleCalculatorPage goToIFrame() throws InterruptedException {
        String firstFrameXpath = "//*[@id='cloud-site']/devsite-iframe/iframe";//first frame xpath
        String secondFrameXpath = "//*[@id='maia-main']/div/div/iframe";//second frame xpath
        Thread.sleep(2000);
        WebElement firstFrameElement = driver.findElement(By.xpath(firstFrameXpath));//find first frame
        Thread.sleep(2000);
        driver.switchTo().frame(firstFrameElement);//switch to first frame
        WebElement secondFrameElement = driver.findElement(By.xpath(secondFrameXpath));//find second frame
        driver.switchTo().frame(secondFrameElement);//switch to second frame
        return this;
    }
    public GoogleCalculatorPage addInstancesFieldsValues() throws InterruptedException {
        //compute engine
        driver.findElement(By.xpath("//div[text()='Compute Engine']")).click();
        //Number of instances
        driver.findElement(By.xpath("//label[contains(text(),'Number of instances')]/following-sibling::input")).sendKeys("1");
        //What are these instances for
        driver.findElement(By.xpath("//label[contains(text(),'What are these instances for?')]/following-sibling::input")).sendKeys("");
        //Operation System/Software
        driver.findElement(By.xpath("//label[contains(text(),'Operating System / Software')]/following-sibling::md-select")).click();
        driver.findElement(By.xpath("//md-select-menu/md-content/md-option/div[contains(text(),'Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License')]/parent::*")).click();
        //Provisioning model
        driver.findElement(By.xpath("//label[contains(text(),'Provisioning model')]/following-sibling::md-select")).click();
        driver.findElement(By.xpath("//md-select-menu/md-content/md-option/div[contains(text(),'Regular')]/parent::*")).click();
        //Series
        driver.findElement(By.xpath("//label[contains(text(),'Series')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitSeriesOptions = new WebDriverWait(driver, 30);
        ulWaitSeriesOptions.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Series')]/following-sibling::md-select")));
        driver.findElement(By.xpath("//md-select-menu/md-content/md-option/div[contains(text(),'N1')]/parent::*")).click();
        //Machine type
        driver.findElement(By.xpath("//label[contains(text(),'Machine type')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitMachineType = new WebDriverWait(driver, 30);
        ulWaitMachineType.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'Machine type')]/following-sibling::md-select")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'n1-standard-8')]/parent::*")).click();
        return this;
    }
    public GoogleCalculatorPage addGPUsFieldsValues() throws InterruptedException {
        //click add GPUs checkbox
        driver.findElement(By.xpath("//div[contains(text(),'Add GPUs')]/parent::*")).click();
        Thread.sleep(2000);
        //select GPU type
        driver.findElement(By.xpath("//label[contains(text(),'GPU type')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitGPU = new WebDriverWait(driver, 30);
        ulWaitGPU.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'NVIDIA Tesla V100')]/parent::*")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'NVIDIA Tesla V100')]/parent::*")).click();
        //select Number of GPUs
        driver.findElement(By.xpath("//label[contains(text(),'Number of GPUs')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitNumberGPU = new WebDriverWait(driver, 30);
        ulWaitNumberGPU.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//md-select-menu/md-content/md-option[@id='select_option_464']")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//md-select-menu/md-content/md-option[@id='select_option_464']")).click();
        return this;
    }
    public GoogleCalculatorPage addAdditionalFieldsValues() throws InterruptedException {
        //select Local SSD
        driver.findElement(By.xpath("//label[contains(text(),'Local SSD')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitLocalSSD = new WebDriverWait(driver, 30);
        ulWaitLocalSSD.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), '2x375 GB')]/parent::*")));
        driver.findElement(By.xpath("//div[contains(text(), '2x375 GB')]/parent::*")).click();
        //select Datacenter location
        driver.findElement(By.xpath("//label[contains(text(),'Datacenter location')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitLocation = new WebDriverWait(driver, 30);
        ulWaitLocation.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Frankfurt (europe-west3)')]/parent::md-option[@id='select_option_220']")));
        driver.findElement(By.xpath("//div[contains(text(),'Frankfurt (europe-west3)')]/parent::md-option[@id='select_option_220']")).click();
        Thread.sleep(2000);
        //select Committed usage
        driver.findElement(By.xpath("//label[contains(text(),'Committed usage')]/following-sibling::md-select")).click();
        WebDriverWait ulWaitUsage = new WebDriverWait(driver, 30);
        ulWaitUsage.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//md-select-menu/md-content/md-option[@id='select_option_123']/div[contains(text(),'1 Year')]/parent::*")));
        driver.findElement(By.xpath("//md-select-menu/md-content/md-option[@id='select_option_123']/div[contains(text(),'1 Year')]/parent::*")).click();
        return this;
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}

