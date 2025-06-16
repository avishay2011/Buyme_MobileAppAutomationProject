package flows;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import page_Objects.*;
import test.BaseTest;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class SelectGift_Flows extends BaseTest {

    // Constructor to initialize page objects and test utilities
    public SelectGift_Flows(AndroidDriver driver) {
        this.driver = driver;
        this.registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        this.homePage = new HomePage(driver);
        this.softAssert = new SoftAssert();
        this.birthDayGiftsPage = new BirthDayGifts_Page(driver);
        this.couponPage = new Coupon_Page(driver);
        this.purchaseGiftCard_Step1_Page = new Purchase_GiftCard_Step1_Page(driver);
        this.purchaseGiftCard_Step2_Page = new Purchase_GiftCard_Step2_Page(driver);
        this.verifications = new Verifications(driver, softAssert);
    }

    // Checks app readiness and selects a birthday gift verifying all UI elements
    public SelectGift_Flows checkPageReadyAndPreConditions() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        homePage.selectCategory(readFromThisFile("categorybirthDay"));
        verifications.verifyTrue(birthDayGiftsPage.isBirthDayGiftsDisplayed(), "Verify birthday gifts list appears");
        birthDayGiftsPage.selectGift(readFromThisFile("giftName")); // select bymechef
        verifications.verifyTrue(couponPage.getTextDisplayedGiftCard().toLowerCase().contains(readFromThisFile("giftCardName")), "Verify that the expected gift card appears after been selected");
        verifications.verifyTrue(couponPage.isAmountInputVisible(), "Verify amount input appears");
        verifications.verifyTrue(couponPage.isAmountInputEnabled(), "Verify amount input is enabled");
        return this;
    }

    // Verifies that the amount input field is mandatory and shows error if empty
    public SelectGift_Flows verifyAmountFieldIsMandatoryFlow() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount(readFromThisFile("amountZero"));
        verifications.verifyTextEquals(couponPage.getTextNoAmountEnteredErrorMessage(), readFromThisFile("CouponPage_NoAmountEntered_Error"), "Error message - No amount entered");
        verifications.verifyTrue(couponPage.isErrorMessageLocatedBelowInputField(), "Verify error message is located below amount input field");
        return this;
    }

    // Buys a gift for a friend and verifies the purchase flow steps
    public SelectGift_Flows buyGiftForFriendFlow() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount(readFromThisFile("couponAmount"));
        couponPage.clickSelect();
        Assert.assertTrue(purchaseGiftCard_Step1_Page.isPurchasePageStep1Opened());
        purchaseGiftCard_Step1_Page.sendKey_GiftReciever_Name(readFromThisFile("giftRecieverName"));
        purchaseGiftCard_Step1_Page.clickOnRecieverContact();
        verifications.verifyTrue(purchaseGiftCard_Step2_Page.isPurchasePageStep2HowToSendOpened(), "Verify Step 2 - How to send page opens");
        return this;
    }

    // Buys a gift for the user himself and verifies confirmation message
    public SelectGift_Flows buyGiftForMyselfFlow() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount(readFromThisFile("couponAmount"));
        couponPage.clickSelect();
        purchaseGiftCard_Step1_Page.click_For_Myself();
        verifications.verifyTextEquals(purchaseGiftCard_Step1_Page.getTextgiftCardForMyselfMessage(), readFromThisFile("giftForMySelf_Message"), "Text title - gift card for myself");
        return this;
    }
}

