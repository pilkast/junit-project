package ddocs.page;

import ddocs.singleton.Singleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class GeneralPage {
    private WebDriver driver;
    public static final String EMAIL = "fileverso16@armyspy.com";
    public static final String URL = "https://www.fakemailgenerator.com/inbox/armyspy.com/fileverso16/";

    public GeneralPage() {
        this.driver = Singleton.getDriver();
    }

    public void open() throws InterruptedException {
        driver.get("http://localhost:3000/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Thread.sleep(4000);
//        WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"radix-:r4:\"]/button")));
//        until.click();
        WebElement until2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"radix-:r7:\"]/button")));
        until2.click();
    }

    public void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement emailWindow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Navbar\"]/div[2]/button[4]")));
        emailWindow.click();
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"email-input\"]"));
        emailInput.sendKeys(EMAIL);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"privy-modal-content\"]/div/div[1]/div[2]/div/div[1]/div/label/button")));
        submit.click();
        String sixDigitCodeFromFakeMail = getSixDigitCodeFromFakeMail();
        WebElement codeInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"privy-modal-content\"]/div/div[1]/div[2]/div[2]/div[1]/div[2]/input[1]")));
        codeInput.sendKeys(sixDigitCodeFromFakeMail);
        Thread.sleep(2000);

        WebElement submitLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'sc-keTIit') and contains(@class, 'sc-dpBQxM') and contains(@class, 'dkFXNe') and contains(@class, 'bJnPRn') and normalize-space(text())='Sign and continue']\n")));
        submitLogin.click();

        WebElement migrationAlert = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"radix-:r2d:\"]/button")));
        migrationAlert.click();
        Thread.sleep(4000);
    }

    public void clickLeftMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement leftMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Navbar\"]/div[1]/button[1]")));
        leftMenu.click();
    }

    public void addDocument() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement documentButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[starts-with(@id, 'radix-:r')]/div[2]/div[1]/button[1]/a/button")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", documentButton);
        Thread.sleep(5000);
    }

    public void setFileName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement setName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Navbar\"]/div[1]/div/div/div/input")));
        setName.clear();
        setName.sendKeys(generateRandomString(5));
        setName.sendKeys("abc");
    }

    public void addTextToDo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement toDoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editor\"]/div/div/div/div/div[1]/button[1]")));
        toDoButton.click();
    }

    private String generateRandomString(int length) {
        String candidateChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }

    public String getSixDigitCodeFromFakeMail() throws InterruptedException {
        String originalWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(8000);
        driver.get(URL);
        ((JavascriptExecutor) driver).executeScript("window.stop();");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement codeElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//ul[@id='email-list']/li)[1]//div[contains(@class, 'col-xs-9') and contains(@class, 'col-sm-10') and contains(@class, 'col-md-8') and contains(@class, 'col-lg-7')]/p\n")));

        String codeText = codeElement.getText();
        String code = codeText.replaceAll("\\D", "");

        driver.close();
        driver.switchTo().window(originalWindow);
        return code;
    }
}
