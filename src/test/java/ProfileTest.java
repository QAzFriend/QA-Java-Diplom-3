import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.time.Duration;

import static config.UserData.*;
import static config.WebDriverFactory.createWebDriver;

public class ProfileTest{
    WebDriver driver;
    UserClient userClient = new UserClient();
    @Before
    public void setUp() {
        userClient.setUp();
        driver = createWebDriver();
        userClient.createUser(new User(EMAIL, PASSWORD, NAME));
    }
    @Test
    @DisplayName("Проверь переход по клику на «Личный кабинет»")
    public void goToProfileTest()  {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.open();
        mainPage.clickGoToProfileButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.clickGoToProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text() = 'Выход']")));
        profilePage.checkLogoutButton();
    }

    @Test
    @DisplayName("Проверь переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToMenuWithConstructionButtonTest(){
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickGoToProfileButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.clickGoToProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text() = 'Выход']")));
        profilePage.clickConstructorButton();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверь переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToMenuWithLogoButtonTest(){
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickGoToProfileButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.clickGoToProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable((By.xpath("//p[text()='Конструктор']"))));
        profilePage.clickLogoButton();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете.")
    public void LogoutTest()
     {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.open();
        mainPage.clickGoToProfileButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
         mainPage.clickGoToProfileButton();
         new WebDriverWait(driver, Duration.ofSeconds(7))
                 .until(ExpectedConditions.elementToBeClickable(( By.xpath(".//button[text() = 'Выход']"))));
        profilePage.checkLogoutButton();
        profilePage.clickLogoutButton();
        loginPage.checkClickableSignInButton();

    }
    @After
    public void tearDown() {
        String accessToken = UserClient.loginUser(new User(EMAIL, PASSWORD)).extract().path("accessToken");
        if (accessToken != null) {
            UserClient.deleteUser(accessToken);
        }
        driver.quit();
    }
}
