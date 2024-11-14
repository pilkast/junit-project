package by.pizzatempo;

import by.pizzatempo.page.PizzaTempoPage;
import by.pizzatempo.singleton.Singleton;
import by.pizzatempo.step.PizzaTempoStep;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.List;

public class PizzaOrderTest {
    PizzaTempoPage pizzaTempoPage;

    @BeforeEach
    public void setUp() {
        pizzaTempoPage = new PizzaTempoPage();
        pizzaTempoPage.open();
    }

    @Test
    @DisplayName("Order page opens")
    public void orderPageOpens() {
        pizzaTempoPage.clickPizzaButton();

        String titlePizza = pizzaTempoPage.getPizzaTitle();
        Assertions.assertEquals("Пицца", titlePizza);
    }

    @Test
    @DisplayName("Add one pizza in basket")
    public void addPizzaInBasket() {
        PizzaTempoStep pizzaTempoStep = new PizzaTempoStep();
        pizzaTempoStep.clickPizzaButtonAndOrderPizzaAndCheckBasket();
        pizzaTempoStep.assertPizzaIsInOrder();
    }

    @Test
    @DisplayName("Add two pizza in basket")
    public void addTwoPizzaInBasket() {
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
