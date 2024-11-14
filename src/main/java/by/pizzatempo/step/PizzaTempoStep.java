package by.pizzatempo.step;

import by.pizzatempo.page.PizzaTempoPage;
import org.assertj.core.api.SoftAssertions;

public class PizzaTempoStep {
    PizzaTempoPage pizzaTempoPage;
    SoftAssertions softAssertions;
    String titlePizza;
    String orderPopUp;
    String basketTitle;

    public String getTitlePizza() {
        return titlePizza;
    }

    public String getOrderPopUp() {
        return orderPopUp;
    }

    public String getBasketTitle() {
        return basketTitle;
    }

    public void clickPizzaButtonAndOrderPizzaAndCheckBasket() {
        pizzaTempoPage = new PizzaTempoPage();

        pizzaTempoPage.clickPizzaButton();
        pizzaTempoPage.clickOrderButtonOnFirstPizza();
        pizzaTempoPage.clickOrderButtonOnPopUp();
        pizzaTempoPage.openBasket();
    }

    public void assertPizzaIsInOrder(){
        softAssertions = new SoftAssertions();

        softAssertions.assertThat(titlePizza).as("Пицца").isEqualTo(titlePizza);
        softAssertions.assertThat(orderPopUp).as("Пицца с курицей, грибами и песто").isEqualTo(orderPopUp);
        softAssertions.assertThat(basketTitle).as("Оформление заказа").isEqualTo(basketTitle);
        softAssertions.assertAll();
    }
}
