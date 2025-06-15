package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class Tests04_CheckGiftCardBalance extends BaseTest {
    @BeforeMethod
    public void checkPageReadyAndPreConditions() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
    }

    @Test
    public void test01_WrondGiftCardCode() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        homePage.navigateToProfileAndInfoPage();
        profileAndInfo_Page.navigateToassigningGiftsPage();

    }

}
