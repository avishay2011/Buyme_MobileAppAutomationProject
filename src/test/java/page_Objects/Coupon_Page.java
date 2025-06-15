package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Coupon_Page extends BasePage {

    // Constructor
    public Coupon_Page(AndroidDriver driver) {
        super(driver);
    }

    // Elements
    private By giftCard_Name = By.id("il.co.mintapp.buyme:id/voucher_name");
    private By amount_Field = By.id("il.co.mintapp.buyme:id/voucher_edit_text");
    private By purchaseButton = By.id("il.co.mintapp.buyme:id/purchase_button");
    private By errorMessage_NoAmountEntered = By.id("il.co.mintapp.buyme:id/invalid_price");

    // Methods

    @Step("Click select button")
    // Clicks the purchase button
    public Coupon_Page clickSelect() {
        click(purchaseButton);
        return this;
    }

    @Step("Send keys '{couponAmount}' on {amount_Field}")
    // Enters the coupon amount in the amount input field
    public Coupon_Page sendKeysAmount(String couponAmount) {
        sendKeys(amount_Field, couponAmount);
        return this;
    }

    // Retrieves the displayed gift card name text
    public String getTextDisplayedGiftCard() {
        return getText(giftCard_Name);
    }

    @Step
    // Checks if the amount input field is visible
    public boolean isAmountInputVisible() {
        return isElementDisplayed(amount_Field);
    }

    @Step
    // Checks if the amount input field is enabled
    public boolean isAmountInputEnabled() {
        return waitVisibility(amount_Field).isEnabled();
    }

    @Step
    // Gets the error message text for no amount entered
    public String getTextNoAmountEnteredErrorMessage() {
        return getText(errorMessage_NoAmountEntered);
    }

    @Step
    // Gets the color value of the no amount entered error message
    public String getColorNoamountEnteredErrorMessage() {
        return getColor(errorMessage_NoAmountEntered);
    }

    @Step("Verify that error message is displayed *below* the input field")
    // Verifies that the error message is located below the amount input field
    public boolean isErrorMessageLocatedBelowInputField() {
        return isElement1LocatedBelowElement2(amount_Field, errorMessage_NoAmountEntered);
    }
}

