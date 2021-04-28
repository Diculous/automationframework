package hardcore.test;

import hardcore.driver.DriverSingleton;
import hardcore.model.Engine;
import hardcore.page.GooglePricingCalculatorPage;
import hardcore.service.EngineCreator;
import hardcore.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * 1. Открыть https://cloud.google.com/
 * 2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
 * 3. Запустить поиск, нажав кнопку поиска.
 * 4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
 * 5. Активировать раздел COMPUTE ENGINE вверху страницы
 * 6. Заполнить форму следующими данными:
 *     * Number of instances: 4
 *     * What are these instances for?: оставить пустым
 *     * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
 *     * VM Class: Regular
 *     * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
 *     * Выбрать Add GPUs
 *         * Number of GPUs: 1
 *         * GPU type: NVIDIA Tesla V100
 *     * Local SSD: 2x375 Gb
 *     * Datacenter location: Frankfurt (europe-west3)
 *     * Committed usage: 1 Year
 * 7. Нажать Add to Estimate
 * 8. Выбрать пункт EMAIL ESTIMATE
 * 9. В новой вкладке открыть https://10minutemail.com или аналогичный сервис для генерации временных email'ов
 * 10. Скопировать почтовый адрес сгенерированный в 10minutemail
 * 11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
 * 12. Нажать SEND EMAIL
 * 13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем, что отображается в калькуляторе
 *
 * Практическое задание
 * Задача - построить фреймворк для автоматизации Hardcore задания из курса WebDriver.
 *
 * Что должно быть в итоговом фреймворке:
 *
 * +webdrivermanager для управления коннекторам к браузерам
 * +Page Object / Page Factory для абстракций страниц
 * +-Модель для бизнес-объектов необходимых сущностей
 * +properties файлы с тестовыми данным для разных окружений (как минимум 2) +++4 строки
 * +xml suites для smoke тестов и всех тестов
 * +При падении теста должен быть сделан скриншот с датой и временем
 * Фреймворк должен иметь возможность запуска с Jenkins и параметризацией браузера, тест suite, environment.
 * Результаты тестов должны быть на графике джобы, скриншоты должны быть заархивированны как артефакты
 *
 * mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testing-smoke.xml clean test
 * mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testing-smoke.xml clean test
 * mvn -Dbrowser=firefox -Denvironment=dev -Dsurefire.suiteXmlFiles=src\test\resources\testing-smoke.xml clean test
 *
 * qa TEST DATA CORRUPTED
 */

@Listeners({TestListener.class})
public class GooglePricingCalculatorSendResultsByEmailTest {

    WebDriver driver;
    GooglePricingCalculatorPage page;
    Engine engine = EngineCreator.createEngineFromProperties();

    @BeforeClass
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
        page = new GooglePricingCalculatorPage(driver);
    }

    @Test
    public void checkGooglePricingCalculatorVMClass() {

            page.initSearch("Google Cloud Platform Pricing Calculator")
                .clickOnFirstResult()
                .fillInstancesAmount(engine.getNumberOfInstances())
                .findBaseInstanceType()
                .selectBaseInstanceType(engine.getBaseType())
                .findAccuarteInstanceType()
                .selectAccurateInstanceType()
                .enableGPU()
                .findAmountOfGPUs()
                .selectAmountOfGPUs()
                .findGPUsType()
                .selectGPUsType()
                .findSSDVolume()
                .selectSSDVolume()
                .findLocation()
                .selectLocation(engine.getLocation())
                .findCommittedUsage()
                .selectCommittedUsage(engine.getCommittedUsage())
                .submitFilledForm()
                .getMonthlyCost()
                .switchToNewMailPage()
                .findGeneratedMailAddress()
                .switchToCalculatorPage()
                .findSendEmailButton()
                .fillMailInputField()
                .sendEmail()
                .switchToMailPage()
                .receiveMailFromGoogleCalculator()
                .findTotalEstimatedMontlyCostFromEmail();

        Assert.assertEquals("Total Estimated Cost: " + page.getTenMinutesMailPage().getTotalMonthlyCost() + " per 1 month",
               page.getTotalMonthlyCost());
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}