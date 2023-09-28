package pages;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.Urls.MAIN_PAGE_URL;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;
public class MainPage {
    //кнопка Личный кабинет
    private final By goToProfileButton =
            By.xpath(".//p[text()='Личный Кабинет']");
    //кнопка Войти в аккаунт
    private final By signInButton =
            By.xpath(".//button[text()='Войти в аккаунт']");
    //кнопка Оформить заказ
    private final By makeOrderButton =
            By.xpath(".//button[contains(text(),'Оформить заказ')]");
    //Надпись Соберите бургер
    private final By makeBurgerText =
            By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");
    //кнопка Булки
    private final By bunsButton =
    By.xpath(".//div/span[text()='Булки']");
    //кнопка Соусы
    private final By saucesButton =
            By.xpath(".//div/span[text()='Соусы']");
    //кнопка Начинки
   private final By fillingsButton =
           By.xpath(".//div/span[text()='Начинки']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {         //открыть сайт
        driver.get(MAIN_PAGE_URL);
    }
    // клик по кнопке Личный кабинет
    public void clickGoToProfileButton() {
        driver.findElement(goToProfileButton).click();
    }
    // клик по кнопке Войти
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
    //проверка наличия кнопки заказа
    public void checkOrderButton() {
        String textOrderButton = driver.findElement(makeOrderButton).getText();
        MatcherAssert.assertThat(textOrderButton, startsWith("Оформить заказ"));
    }
    //Ожидание загрузки главной страницы"
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(makeBurgerText));
    }
    // КЛИКИ ПО КНОПКАМ КОНСТРУКТОРА
    public void clickBunsButton() { //клик на кнопку Булки
        driver.findElement(bunsButton).click();
    }
    public void clickSaucesButton() { //клик на кнопку Соусы
        driver.findElement(saucesButton).click();
    }
    public void clickFillingsButton() { //клик на кнопку Начинки
        driver.findElement(fillingsButton).click();
    }

    // ПРОВЕРКИ АКТИВНОСТИ КНОПОК КОНСТРУКТОРА
    public void checkGoToTheBunsSection(){
        String text = driver.findElement(By.xpath(".//div[@style]/div[1]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("current"));
    }
    public void checkGoToTheSaucesSection(){
        String text = driver.findElement(By.xpath(".//div[@style]/div[2]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("current"));
    }
    public void checkGoToTheFillingsSection(){
        String text = driver.findElement(By.xpath(".//div[@style]/div[3]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("current"));
    }

}
