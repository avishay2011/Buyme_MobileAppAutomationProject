package flows;

import io.appium.java_client.android.AndroidDriver;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import page_Objects.HomePage;
import page_Objects.RegistrationPage_Step1_InsertEmail;
import test.BaseTest;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class RegistrationAndLogin_Flows extends BaseTest {

    public RegistrationAndLogin_Flows(AndroidDriver driver) {
        this.driver = driver;
        this.registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        this.homePage = new HomePage(driver);
        this.softAssert =new SoftAssert();
        this.verifications=new Verifications(driver,softAssert);
    }

    public RegistrationAndLogin_Flows loginValidUserNameGoogleAccountFlow() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        verifications.verifyTrue(registrationPage_Step1_InsertEmail.getUserText().contains(readFromThisFile("myName")),"Test User Name Text");
        return this;
    }

    private RegistrationAndLogin_Flows performLoginByEmail(String emailKey) throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_LoginByEmailAccount();
        registrationPage_Step1_InsertEmail.insertEmailAccount(emailKey);
        registrationPage_Step1_InsertEmail.click_Enter_After_InsertEmail();
        return this;
    }

    public RegistrationAndLogin_Flows loginInvalidUserNameFlow() throws ParserConfigurationException, IOException, SAXException {
        performLoginByEmail(readFromThisFile("wrongEmail"));
        verifications.verifyTextEquals(registrationPage_Step1_InsertEmail.getTextInvalidEmailErrorMessage(),readFromThisFile("wrongEmailMessage"),"Test Wrong Email Error message ");
        return this;
    }

    public RegistrationAndLogin_Flows loginInvalidPasswordFlow() throws ParserConfigurationException, IOException, SAXException {
        performLoginByEmail(readFromThisFile("validEmail"));
        registrationPage_Step1_InsertEmail.click_Enter_After_InsertPassword();
        verifications.verifyTextEquals(registrationPage_Step1_InsertEmail.getTextInvalidPasswordErrorMessage(),readFromThisFile("wrongPasswordMessage"),"Check WrongError message is correct ");
        return this;
    }
}
