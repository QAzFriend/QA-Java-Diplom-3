import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPasswordPage;
import pages.RegisterPage;

import static config.UserData.*;
import static config.WebDriverFactory.createWebDriver;

public class LoginTest {
    private WebDriver driver;
    UserClient userClient = new UserClient();

    @Before
    public void setUp() {
        userClient.setUp();
        driver = createWebDriver();
        userClient.createUser(new User(EMAIL, PASSWORD, NAME));
    }
    @Test
    @DisplayName("Проверь вход через кнопку в форме регистрации")
    public void loginInRegistrationFormTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);


        registerPage.open();
        registerPage.clickSignInButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.waitForLoadMainPage();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверь вход через кнопку в форме восстановления пароля.")
    public void LoginInPasswordRecoveryFormTest(){
        RecoveryPasswordPage forgotPasswordPage = new RecoveryPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        forgotPasswordPage.open();
        forgotPasswordPage.clickLogInButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.waitForLoadMainPage();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверь вход по кнопке «Войти в аккаунт» на главной")
    public void loginInMainPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickSignInButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.waitForLoadMainPage();
        mainPage.checkOrderButton();
    }
    @Test
    @DisplayName("Проверь вход через кнопку «Личный кабинет»")
    public void loginInProfileTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.open();
        mainPage.clickGoToProfileButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.waitForLoadMainPage();
        mainPage.checkOrderButton();
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
