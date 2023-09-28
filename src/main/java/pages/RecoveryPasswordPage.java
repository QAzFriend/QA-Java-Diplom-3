package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static config.Urls.FORGOT_PASSWORD_PAGE_URL;

public class RecoveryPasswordPage {


    //кнопка Войти
    private final By logInButton = By.xpath("//a[@href='/login']");
    private final WebDriver driver;
    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }


    public void open() {
        driver.get(FORGOT_PASSWORD_PAGE_URL); }

    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }


}
