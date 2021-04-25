package hardcore.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GooglePricingCalculatorPage {

    private WebDriver driver;
    private String googlePage = "https://cloud.google.com";
    private String mailPage = "https://10minemail.com/en/" ;
    private TenMinutesMailPage tenMinutesMailPage;
    private String totalMonthlyCost;

    public GooglePricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        driver.get(googlePage);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement search;

    @FindBy(xpath = "//b[text()=\"Google Cloud Platform Pricing Calculator\"]")
    private WebElement firstSearchResult;

    @FindBy(id = "input_65")
    private WebElement instancesField;

    @FindBy(id = "select_90")
    private WebElement baseInstanceType;

    @FindBy(id = "select_option_190")
    private WebElement baseInstanceTypeSelect;

    @FindBy(id = "select_92")
    private WebElement accurateInstanceType;

    @FindBy(id = "select_option_365")
    private WebElement accurateInstanceTypeSelect;

    @FindBy(xpath = "//md-checkbox[contains(@aria-label, \"Add GPUs\")]")
    private WebElement enableGPUCheckbox;

    @FindBy(id = "select_399")
    private WebElement amountOfGPUs;

    @FindBy(id = "select_option_404")
    private WebElement selectAmountOfGPUs;

    @FindBy(id = "select_401")
    private WebElement typeOfGPUs;

    @FindBy(id = "select_option_411")
    private WebElement selectTypeOfGPUs;

    @FindBy(id = "select_360")
    private WebElement volumeSSD;

    @FindBy(id = "select_option_386")
    private WebElement selectVolumeSSD;

    @FindBy(id = "select_94")
    private WebElement location;

    @FindBy(id = "select_option_219")
    private WebElement selectLocation;

    @FindBy(id = "select_101")
    private WebElement committedUsage;

    @FindBy(id = "select_option_99")
    private WebElement selectCommittedUsage;

    @FindBy(id = "email_quote")
    private WebElement email;

    @FindBy(id = "input_482")
    private WebElement emailInput;

    @FindBy(xpath = "//button[contains(@aria-label, \"Send Email\")]")
    private WebElement sendEmail;

    @FindBy(xpath = "//b[text()[contains(.,'Total')]]")
    private WebElement totalCost;

    public GooglePricingCalculatorPage initSearch(String searchRequest) {
        waitForElementIsClickable(driver, search);
        search.sendKeys(searchRequest);
        search.submit();
        return this;
    }

    public GooglePricingCalculatorPage clickOnFirstResult() {
        waitForElementIsClickable(driver, firstSearchResult).click();
    return this; }

    public GooglePricingCalculatorPage fillInstancesAmount(String numberOfInstances) {
        driver.switchTo().frame(0).switchTo().frame(0);
        instancesField.sendKeys(numberOfInstances);
    return this; }

    public GooglePricingCalculatorPage findBaseInstanceType() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementIsClickable(driver, baseInstanceType).click();
    return this; }

    public GooglePricingCalculatorPage selectBaseInstanceType() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementIsClickable(driver, baseInstanceTypeSelect).click();
    return this; }

    public GooglePricingCalculatorPage findAccuarteInstanceType() {
        accurateInstanceType.click();
    return this; }

    public GooglePricingCalculatorPage selectAccurateInstanceType() {
        waitForElementIsClickable(driver, accurateInstanceTypeSelect).click();
    return this; }

    public GooglePricingCalculatorPage enableGPU() {
        enableGPUCheckbox.click();
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
    return this; }

    public GooglePricingCalculatorPage findAmountOfGPUs() {
        amountOfGPUs.click();
    return this; }

    public GooglePricingCalculatorPage selectAmountOfGPUs() {
        waitForElementIsClickable(driver, selectAmountOfGPUs).click();
    return this; }

    public GooglePricingCalculatorPage findGPUsType() {
        typeOfGPUs.click();
    return this; }

    public GooglePricingCalculatorPage selectGPUsType() {
        waitForElementIsClickable(driver, selectTypeOfGPUs).click();
    return this; }

    public GooglePricingCalculatorPage findSSDVolume() {
        volumeSSD.click();
    return this; }

    public GooglePricingCalculatorPage selectSSDVolume() {
        waitForElementIsClickable(driver, selectVolumeSSD).click();
    return this; }

    public GooglePricingCalculatorPage findLocation() {
        location.click();
    return this; }

    public GooglePricingCalculatorPage selectLocation() {
        waitForElementIsClickable(driver, selectLocation).click();
    return this; }

    public GooglePricingCalculatorPage findCommittedUsage() {
        committedUsage.click();
    return this; }

    public GooglePricingCalculatorPage selectCommittedUsage() {
        waitForElementIsClickable(driver, selectCommittedUsage).click();
    return this; }

    public GooglePricingCalculatorPage submitFilledForm() {
        instancesField.submit();
    return this; }

    public TenMinutesMailPage switchToNewMailPage() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(mailPage);
        tenMinutesMailPage = new TenMinutesMailPage(driver, this);
        return tenMinutesMailPage;
    }

    public GooglePricingCalculatorPage findSendEmailButton() {
        email.click();
        return this; }

    public GooglePricingCalculatorPage fillMailInputField() {
        emailInput.sendKeys(tenMinutesMailPage.getEmailAddress());
        return this; }

    public GooglePricingCalculatorPage sendEmail() {
        waitForElementIsClickable(driver, sendEmail);
        sendEmail.click();
        return this; }

    public TenMinutesMailPage switchToMailPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
        enableGPUCheckbox.sendKeys(Keys.ARROW_DOWN);
        return tenMinutesMailPage;
    }

    public GooglePricingCalculatorPage getMonthlyCost() {
        totalMonthlyCost = totalCost.getText();
        return this;
    }

    public String getTotalMonthlyCost() {
        return totalMonthlyCost;
    }

    public TenMinutesMailPage getTenMinutesMailPage() {
        return tenMinutesMailPage;
    }

    private static WebElement waitForElementIsClickable(WebDriver driver, WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(webElement));
    }
}