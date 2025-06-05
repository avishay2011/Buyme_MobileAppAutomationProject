package test;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Tests01_RegistrationAndLogin_Mobile extends BaseTest{

    @Test
    public void test01_loginValidUserName() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        verifications.verifyTrue(registrationPage_Step1_InsertEmail.getUserText().contains("אבישי"),"Test User Name Text");
        verifications.assertAll();
    }

    @Test
    public void test02_loginInvalidUserName() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_LoginByEmailAccount();
        registrationPage_Step1_InsertEmail.insertEmailAccount();
        registrationPage_Step1_InsertEmail.click_Enter_After_InsertEmail();
        verifications.verifyTextEquals(registrationPage_Step1_InsertEmail.getTextInvalidEmailErrorMessage(),"המייל לא תקין","Test Error message ");
        verifications.assertAll();
    }

}
