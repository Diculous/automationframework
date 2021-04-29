package hardcore.page;

import hardcore.util.Sleeper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

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

    @FindBy(id = "input_66")
    private WebElement instancesField;

    @FindBy(id = "select_91")
    private WebElement baseInstanceType;

    @FindBy(id = "select_93")
    private WebElement accurateInstanceType;

    @FindBy(id = "select_option_366")
    private WebElement accurateInstanceTypeSelect;

    @FindBy(xpath = "//md-checkbox[contains(@aria-label, \"Add GPUs\")]")
    private WebElement enableGPUCheckbox;

    @FindBy(id = "select_400")
    private WebElement amountOfGPUs;

    @FindBy(id = "select_option_405")
    private WebElement selectAmountOfGPUs;

    @FindBy(id = "select_402")
    private WebElement typeOfGPUs;

    @FindBy(id = "select_option_412")
    private WebElement selectTypeOfGPUs;

    @FindBy(id = "select_361")
    private WebElement volumeSSD;

    @FindBy(id = "select_option_387")
    private WebElement selectVolumeSSD;

    @FindBy(id = "select_95")
    private WebElement location;

    @FindBy(id = "select_102")
    private WebElement committedUsage;

    @FindBy(id = "email_quote")
    private WebElement email;

    @FindBy(id = "input_483")
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
        Sleeper.timeout(3);
        instancesField.sendKeys(numberOfInstances);
        return this; }

    public GooglePricingCalculatorPage findBaseInstanceType() {
        Sleeper.timeout(2);
        waitForElementIsClickable(driver, baseInstanceType).click();
        baseInstanceType.sendKeys(Keys.ARROW_DOWN);
        baseInstanceType.sendKeys(Keys.ARROW_DOWN);
        baseInstanceType.sendKeys(Keys.ARROW_DOWN);
        return this; }

    public GooglePricingCalculatorPage selectBaseInstanceType(String baseType) {
        Sleeper.timeout(2);
        WebElement type = driver.findElements(By.xpath("//div[text()[contains(.,'" + baseType + "')]]")).get(0);
        waitForElementIsClickable(driver, type).click();
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

    public GooglePricingCalculatorPage selectLocation(String location) {
        WebElement selectedLocation = driver.findElements(By.xpath("//div[text()[contains(.,'" + location + "')]]")).get(3);
        waitForElementIsClickable(driver, selectedLocation).click();
        return this; }

    public GooglePricingCalculatorPage findCommittedUsage() {
        committedUsage.click();
        return this; }

    public GooglePricingCalculatorPage selectCommittedUsage(String usage) {
        WebElement committed = driver.findElements(By.xpath("//div[text()[contains(.,'" + usage + "')]]")).get(1);
        waitForElementIsClickable(driver, committed).click();
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
        Sleeper.timeout(2);
        sendEmail.sendKeys(Keys.ARROW_DOWN);
        waitForElementIsClickable(driver, sendEmail);
        sendEmail.click();
        return this; }

    public TenMinutesMailPage switchToMailPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
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