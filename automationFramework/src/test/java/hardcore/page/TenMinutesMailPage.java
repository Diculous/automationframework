package hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class TenMinutesMailPage {

    private WebDriver driver;
    private GooglePricingCalculatorPage googlePricingCalculatorPage;
    private String emailAddress;
    private String totalMonthlyCost;

    public TenMinutesMailPage(WebDriver driver, GooglePricingCalculatorPage googlePricingCalculatorPage) {
        this.driver = driver;
        this.googlePricingCalculatorPage = googlePricingCalculatorPage;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "mail")
    private WebElement mail;

    @FindBy(xpath = "//*[text()=\\\"Google Cloud Sales \\\"]")
    private WebElement receivedMail;

    @FindBy(xpath = "//h3[text()[contains(.,'USD')]]")
    private WebElement totalEstimatedMonthlyCost;


    public GooglePricingCalculatorPage switchToCalculatorPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(0).switchTo().frame(0);
        return this.googlePricingCalculatorPage;
    }

    public TenMinutesMailPage findGeneratedMailAddress() {
        emailAddress = mail.getAttribute("value");
        return this; }

    public TenMinutesMailPage receiveMailFromGoogleCalculator() {
        waitForElementLocatedBy(driver, By.xpath("//*[text()=\"Google Cloud Sales \"]")).click();
        return this; }

    public TenMinutesMailPage findTotalEstimatedMontlyCostFromEmail() {
        totalMonthlyCost =  waitForElementLocatedBy(driver, By.xpath("//h3[text()[contains(.,'USD')]]")).getText();
        return this; }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTotalMonthlyCost() {
        return totalMonthlyCost;
    }
}
