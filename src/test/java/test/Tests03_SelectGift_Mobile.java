package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

@Listeners(utils.Listeners.class)
public class Tests03_SelectGift extends BaseTest {
    @BeforeMethod
    public void checkPageReadyAndPreConditions() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        homePage.selectCategory("יום הולדת");
        verifications.verifyTrue(birthDayGiftsPage.isBirthDayGiftsDisplayed(), "Verify birthday gifts list appears");
        birthDayGiftsPage.selectGift("chef"); ///select bymechef
        verifications.verifyTrue(couponPage.getTextDisplayedGiftCard().toLowerCase().contains(readFromThisFile("giftCardName")), "Verify that the expected gift card appears after been selected ");
        verifications.verifyTrue(couponPage.isAmountInputVisible(), "Verify amount input appears");
        verifications.verifyTrue(couponPage.isAmountInputEnabled(), "Verift amount input is enabled");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Select gift without enter amount and verify that expected error message appears")
    public void test01_Verify_That_Amount_Field_IsMandatory() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount("0");
        verifications.verifyTextEquals(couponPage.getTextNoAmountEnteredErrorMessage(), readFromThisFile("CouponPage_NoAmountEntered_Error"), "Error message-No amount entered");
        verifications.verifyTrue(couponPage.isErrorMessageLocatedBelowInputField(), "Verify -Error message located below amount input field");
        verifications.assertAll();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Select gift for someone else and verify next step is enabled after required fields are filled.")
    public void test02_Buy_Gift_For_Friend() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount(readFromThisFile("couponAmount"));
        couponPage.clickSelect();
        Assert.assertTrue(purchaseGiftCard_Step1_Page.isPurchasePageStep1Opened());
        purchaseGiftCard_Step1_Page.sendKey_GiftReciever_Name("אמא");
        purchaseGiftCard_Step1_Page.clickOnRecieverContact();
        verifications.verifyTrue(purchaseGiftCard_Step2_Page.isPurchasePageStep2HowToSendOpened(), "Verify -Step 2 -How to send Opens");
        verifications.assertAll();

    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Select the option gift for myself  and verify that the correct message appears")
    public void test03_Buy_Gift_ForMyself() throws ParserConfigurationException, IOException, SAXException {
        couponPage.sendKeysAmount(readFromThisFile("couponAmount"));
        couponPage.clickSelect();
        purchaseGiftCard_Step1_Page.click_For_Myself();
        verifications.verifyTextEquals(purchaseGiftCard_Step1_Page.getTextgiftCardForMyselfMessage(), readFromThisFile("giftForMySelf_Message"), "Text title-gift card for myself");
    //    verifications.assertAll();
    }
}
