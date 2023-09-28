package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ProfilePage {
    //кнопка Выход
    private final By logoutButton =
            By.xpath(".//button[text() = 'Выход']");
    //кнопка Конструктор
    private final By constructorButton =
            By.xpath(".//p[text()='Конструктор']");
    //ЛОГО
    private final By logoButton =
            By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final WebDriver driver;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    //Методы для теста выхода в личном кабинете
    public void checkLogoutButton(){
        String textOfLogoutButton = driver.findElement(logoutButton).getText();
        assertThat(textOfLogoutButton, startsWith("Выход"));
    }
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }
    // Проверка перехода по кнопке в Конструктор из ЛК
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
    // Метод для проверки перехода по кнопке ЛОГО из ЛК
    public void clickLogoButton(){
        driver.findElement(logoButton).click();
    }
}
