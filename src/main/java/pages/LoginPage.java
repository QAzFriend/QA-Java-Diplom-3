package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static config.UserData.EMAIL;
import static config.UserData.PASSWORD;



public class LoginPage {

    //кнопка Войти
    private final By signInButton = By.xpath(".//button[text()='Войти']");
    //поле ввода почты
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    //поле ввода пароля
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");


    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    //ввод почты на стр авторизации
    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(EMAIL);
    }
    //ввод пароля на стр авторизации
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(PASSWORD);
    }
    //проверка кликабельности кнопки Войти
    public void checkClickableSignInButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }
    //клик по кнопке Войти
    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }

}
