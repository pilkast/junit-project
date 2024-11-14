package by.pizzatempo;

import gov.login.secure.Singleton;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class PizzaOrderTest {
    PizzaTempoPage pizzaTempoPage;

    @BeforeEach
    public void setUp() {
        System.out.println("before each test");
        pizzaTempoPage = new PizzaTempoPage();
        pizzaTempoPage.open();
        System.out.println(Singleton.getDriver());
    }

    @Test
    @DisplayName("Order page opens")
    public void orderPageOpens() {
        System.out.println(Singleton.getDriver());
        pizzaTempoPage.clickPizzaButton();

        String titlePizza = pizzaTempoPage.getPizzaTitle();
        Assertions.assertEquals("Пицца", titlePizza);
    }

    @Test
    @DisplayName("Add one pizza in basket")
    public void addPizzaInBasket() {
//        pizzaTempoPage = new PizzaTempoPage();
//        pizzaTempoPage.open();
        System.out.println(Singleton.getDriver());

        pizzaTempoPage.clickPizzaButton();
        String titlePizza = pizzaTempoPage.getPizzaTitle();
        pizzaTempoPage.clickOrderButtonOnFirstPizza();
        String orderPopUp = pizzaTempoPage.getOrderPopUp();
        pizzaTempoPage.clickOrderButtonOnPopUp();
        pizzaTempoPage.openBasket();
        String basketTitle = pizzaTempoPage.getBasketTitle();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(titlePizza).as("Пицца").isEqualTo(titlePizza);
        softAssertions.assertThat(orderPopUp).as("Пицца с курицей, грибами и песто").isEqualTo(orderPopUp);
        softAssertions.assertThat(basketTitle).as("Оформление заказа").isEqualTo(basketTitle);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Add two pizza in basket")
    public void addTwoPizzaInBasket() {
//        pizzaTempoPage = new PizzaTempoPage();
//        pizzaTempoPage.open();
        System.out.println(Singleton.getDriver());

        pizzaTempoPage.clickPizzaButton();
        String titlePizza = pizzaTempoPage.getPizzaTitle();
        pizzaTempoPage.clickOrderButtonOnFirstPizza();
        String orderPopUp = pizzaTempoPage.getOrderPopUp();
        pizzaTempoPage.clickOrderButtonOnPopUp();
        pizzaTempoPage.clickOrderButtonOnSecondPizza();
        String orderPopUpSecondTime = pizzaTempoPage.getOrderPopUp();
        pizzaTempoPage.clickOrderButtonOnPopUp();

        pizzaTempoPage.openBasket();
        String basketTitle = pizzaTempoPage.getBasketTitle();
        List<String> pizzaTitleInBasket = pizzaTempoPage.getPizzaTitleInBasket();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(titlePizza).as("Пицца").isEqualTo(titlePizza);
        softAssertions.assertThat(orderPopUp).as("Пицца с курицей, грибами и песто").isEqualTo(orderPopUp);
        softAssertions.assertThat(orderPopUpSecondTime).as("Пицца \"Сицилийская\"").isEqualTo(orderPopUpSecondTime);
        softAssertions.assertThat(basketTitle).as("Оформление заказа").isEqualTo(basketTitle);
        softAssertions.assertThat(pizzaTitleInBasket.get(0)).as("Пицца с курицей, грибами и песто").isEqualTo(pizzaTitleInBasket.get(0));
        softAssertions.assertThat(pizzaTitleInBasket.get(1)).as("Пицца \"Сицилийская\"").isEqualTo(pizzaTitleInBasket.get(1));
    }

    @AfterEach
    public void tearDown() {
        Singleton.quitDriver();
    }
}
