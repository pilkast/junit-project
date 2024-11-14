package gov.login.secure;

import gov.login.secure.page.SignInPage;
import gov.login.secure.singleton.Singleton;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SignInTest {
    SignInPage signInPage;

    @BeforeEach
    public void setUp() {
        signInPage = new SignInPage();
        signInPage.open();
    }

    @Test
    @DisplayName("Sign in test, with empty fields")
    public void testSignInWithEmptyFields() {
        signInPage.clickSpinnerButnContent();
        List<WebElement> usaErrorMessages = signInPage.getUSAErrorMessages();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(usaErrorMessages.get(0)).as("This field is required").isEqualTo(usaErrorMessages.get(0));
        softAssertions.assertThat(usaErrorMessages.get(1)).as("This field is required").isEqualTo(usaErrorMessages.get(1));
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Wrong email format")
    public void testWrongEmailFormat() {
        signInPage.sendKeysEmail();
        signInPage.clickSpinnerButnContent();
        List<WebElement> usaErrorMessages = signInPage.getUSAErrorMessages();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(usaErrorMessages.get(0)).as("Please include an '@' in the email address. 'dsa' is missing an '@'.").isEqualTo(usaErrorMessages.get(0));
        softAssertions.assertThat(usaErrorMessages.get(1)).as("This field is required").isEqualTo(usaErrorMessages.get(1));
    }

    @Test
    @DisplayName("Authentication failed test")
    public void authenticationFailed() {
        signInPage.sendKeysEmailAndPassword();
        signInPage.clickSpinnerButnContent();
        WebElement errorMessage = signInPage.getErrorAlertMessage();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(errorMessage.getText()).as("The email or password you’ve entered is wrong. Try resetting your password.").isEqualTo(errorMessage.getText());
    }

    @AfterEach
    public void tearDown() {
        Singleton.quitDriver();
    }
}
