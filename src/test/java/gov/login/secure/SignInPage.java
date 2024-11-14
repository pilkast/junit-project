package gov.login.secure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignInPage {
    private final By SPINNER_BUTN_CONTENT = By.className("spinner-button__content");
    private final By USA_ERROR_MESSAGE = By.className("usa-error-message");
    private WebDriver driver;

    public SignInPage() {
        this.driver = Singleton.getDriver();
    }

    public void open() {
        driver.get("https://secure.login.gov/");
    }

    public void clickSpinnerButnContent() {
        WebElement element = driver.findElement(SPINNER_BUTN_CONTENT);
        element.click();
    }

    public void sendKeysEmail() {
        WebElement element = driver.findElement(By.id("user_email"));
        element.sendKeys("random");
    }

    public void sendKeysEmailAndPassword() {
        WebElement element = driver.findElement(By.id("user_email"));
        WebElement password = driver.findElement(By.name("user[password]"));
        element.sendKeys("random@random.com");
        password.sendKeys("random");
    }

    public List<WebElement> getUSAErrorMessages() {
        return driver.findElements(USA_ERROR_MESSAGE);
    }

    public WebElement getErrorAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class=\"usa-alert__text\"]")));
        return driver.findElement(By.className("usa-alert__text"));
    }
}
