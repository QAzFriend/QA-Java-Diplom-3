import api.*;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static config.UserData.*;
import static config.WebDriverFactory.createWebDriver;

public class RegistrationTest{
    WebDriver driver;
    UserClient userClient = new UserClient();
    @Before
    public void setUp() {
        userClient.setUp();
        driver = createWebDriver();
    }
    private final String incorrectPassword = "Test";


    @Test
    @DisplayName("Успешная регистрация")
    public void successRegistrationTest()  {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registerPage.open();
        registerPage.enterName(NAME);
        registerPage.enterEmail(EMAIL);
        registerPage.enterPassword(PASSWORD);
        registerPage.clickRegisterButton();
        loginPage.checkClickableSignInButton();
        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Проверь ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    public void shortPasswordErrorTest(){
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.open();
        registerPage.enterName(NAME);
        registerPage.enterEmail(EMAIL);
        registerPage.enterPassword(incorrectPassword);
        registerPage.clickRegisterButton();
        registerPage.checkShortPasswordError();
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
