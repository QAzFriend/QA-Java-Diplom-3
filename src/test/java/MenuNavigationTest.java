
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static config.WebDriverFactory.createWebDriver;

public class MenuNavigationTest {
    WebDriver driver;
    UserClient userClient = new UserClient();
    @Before
    public void setUp() {
        userClient.setUp();
        driver = createWebDriver();
    }

    @Test
    @DisplayName("Проверь, что работают переходы к разделу:«Булки»")
    public void navigationToBunsTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        mainPage.checkGoToTheBunsSection();
    }
    @Test
    @DisplayName("Проверь, что работают переходы к разделу: «Соусы»")
    public void navigationToSaucesTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.clickSaucesButton();
        mainPage.checkGoToTheSaucesSection();
    }
    @Test
    @DisplayName("Проверь, что работают переходы к разделу:«Начинки».")
    public void navigationToFillingsTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.waitForLoadMainPage();
        mainPage.clickFillingsButton();
        mainPage.checkGoToTheFillingsSection();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
