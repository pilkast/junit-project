package by.pizzatempo;

import gov.login.secure.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PizzaTempoPage {
    private WebDriver driver;

    public PizzaTempoPage() {
        this.driver = Singleton.getDriver();
    }

    public void open() {
        driver.get("https://www.pizzatempo.by/");
    }

    public void clickPizzaButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"https://www.pizzatempo.by/menu/pizza.html\"]")));
        WebElement element = driver.findElement(By.xpath("//a[@href=\"https://www.pizzatempo.by/menu/pizza.html\"]"));
        element.click();
    }

    public void clickOrderButtonOnFirstPizza() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"order-btn\"]/button")));
        WebElement element = driver.findElement(By.xpath("//div[@class=\"order-btn\"]/button"));
        element.click();
    }

    public void clickOrderButtonOnSecondPizza() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"39\"]/div/div[2]/div[3]/div[3]/button/span/span")));
        WebElement element = driver.findElement(By.xpath("//*[@id=\"39\"]/div/div[2]/div[3]/div[3]/button/span/span"));
        element.click();
    }

    public void clickOrderButtonOnPopUp(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"order_pizza_popup\"]/div[2]/div[2]/div[2]/div[1]/div[3]/div[4]/button")));
        WebElement element = driver.findElement(By.xpath("//*[@id=\"order_pizza_popup\"]/div[2]/div[2]/div[2]/div[1]/div[3]/div[4]/button"));
        element.click();
    }

    public void openBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"bs-price-col\"]/a[@href=\"https://www.pizzatempo.by/menu/order/\"]")));
        WebElement element = driver.findElement(By.xpath("//div[@class=\"bs-price-col\"]/a[@href=\"https://www.pizzatempo.by/menu/order/\"]"));
        element.click();
    }

    public String getPizzaTitle() {
        WebElement pizzaTitle = driver.findElement(By.xpath("//div[@class=\"pageTitle\"]/h1"));
        return pizzaTitle.getText();
    }

    public String getOrderPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"order_pizza_popup\"]/div[2]/div[2]/div[1]/h3")));
        WebElement orderPopUp = driver.findElement(By.xpath("//*[@id=\"order_pizza_popup\"]/div[2]/div[2]/div[1]/h3"));
        return orderPopUp.getText();
    }

    public String getBasketTitle() {
        WebElement orderTitle = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/h1"));
        return orderTitle.getText();
    }

    public List<String> getPizzaTitleInBasket() {
        List<String> pizzaTitles = new ArrayList<String>();
        WebElement firstPizza = driver.findElement(By.xpath("//*[@id=\"basket_expand\"]/table/tbody/tr[1]/td[2]/div[1]"));
        pizzaTitles.add(firstPizza.getText());
        WebElement secondPizza = driver.findElement(By.xpath("//*[@id=\"basket_expand\"]/table/tbody/tr[2]/td[2]/div[1]"));
        pizzaTitles.add(secondPizza.getText());
        return pizzaTitles;
    }
}
