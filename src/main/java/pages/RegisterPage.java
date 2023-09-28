package pages;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static config.Urls.REGISTER_PAGE_URL;
import static org.hamcrest.core.StringStartsWith.startsWith;
public class RegisterPage {
    //поле Имя
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    //поле Email
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    //поле Пароль
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");
    //кнопка Зарегистрироваться
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // ошибка Некорректный пароль
    private final By shortPasswordError = By.xpath(".//p[text()='Некорректный пароль']");
    //кнопка Войти
    private final By signInButton = By.xpath(".//a[text()='Войти']");

    private final WebDriver driver;

    public RegisterPage(WebDriver driver){

        this.driver = driver;
    }
    public void open() {
        driver.get(REGISTER_PAGE_URL); }
    //ввод имени
    public void enterName(String name){
        driver.findElement(nameField).sendKeys(name);
    }
    //ввод почты
    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    //ввод пароля
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    //клик на кнопку Регистрация
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }
    //проверка наличия сообщения об ошибке
    public void checkShortPasswordError(){
        String textOfError = driver.findElement(shortPasswordError).getText();
        MatcherAssert.assertThat("Вход", textOfError, startsWith("Некорректный пароль"));
    }
    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }
}
