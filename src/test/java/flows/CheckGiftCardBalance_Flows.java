package flows;

import io.appium.java_client.android.AndroidDriver;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import page_Objects.*;
import test.BaseTest;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class CheckGiftCardBalance_Flows extends BaseTest {
    public CheckGiftCardBalance_Flows(AndroidDriver driver) {
        this.driver = driver;
        this.softAssert =new SoftAssert();
        this.registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        this.homePage = new HomePage(driver);
        this.assigningGifts_page=new AssigningGifts_Page(driver);
        this.profileAndInfo_Page=new ProfileAndInfo_Page(driver);
        this.verifications=new Verifications(driver,softAssert);
    }

    public CheckGiftCardBalance_Flows checkPageReadyAndPreConditions() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        return this;
    }

    private CheckGiftCardBalance_Flows insertGiftCard(String codeKey, String dayKey, String monthKey, String yearKey) throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        homePage.navigateToProfileAndInfoPage();
        profileAndInfo_Page.navigateToassigningGiftsPage();
        assigningGifts_page.sendKeysGiftCardCode(readFromThisFile(codeKey));
        assigningGifts_page.openExpirationDayField();
        assigningGifts_page.select_Date_Picker(readFromThisFile(dayKey), readFromThisFile(monthKey), readFromThisFile(yearKey));
        assigningGifts_page.clickAddGiftCard();
        return this;
    }

    public CheckGiftCardBalance_Flows verifyWrongCardCodeErrorFlow() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        insertGiftCard("wrongcouponCode", "day", "month", "year");
        verifications.verifyTextEquals(assigningGifts_page.getToastViewText(),readFromThisFile("wrongGifCodeError"),"Verify error message after insert wrong gift card code");
        return this;
    }

    public CheckGiftCardBalance_Flows verifyUsedGiftCardCodeMessageFlow() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        insertGiftCard("existsUsedCouponCode","existsUsedCouponExpiration_day","existsUsedCouponExpiration_month","existsUsedCouponExpiration_year");
        verifications.verifyTextEquals(assigningGifts_page.getToastViewText(),readFromThisFile("usedGiftCodeMessage"),"Verify error message after insert wrong gift card code");
        return this;
    }

    public CheckGiftCardBalance_Flows verifyValidGiftCardCodeMessage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        insertGiftCard("validCouponCode","validCouponExpiration_day","validCouponExpiration_month","validCouponExpiration_year");
        verifications.verifyTextEquals(assigningGifts_page.getToastViewText(),readFromThisFile("usedGiftCodeMessage"),"Verify error message after insert wrong gift card code");
        return this;
    }
}
