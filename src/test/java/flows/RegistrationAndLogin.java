package flows;

import io.appium.java_client.android.AndroidDriver;
import page_Objects.RegistrationPage_Step1_InsertEmail;

import static utils.Utilities.readFromThisFile;

public class RegistrationAndLogin_Flows {
    AndroidDriver driver=new AndroidDriver();
    RegistrationPage_Step1_InsertEmail registrationPage_Step1_InsertEmail=new RegistrationPage_Step1_InsertEmail()

    public RegistrationAndLogin_Flows loginByValidUserNameFlow(){
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.navigateToRegistrationPage();
        registrationPage_Step1_InsertEmail.select_Register_By_GoogleAccount();
        registrationPage_Step1_InsertEmail.selectGoogleAccount();
        verifications.verifyTrue(registrationPage_Step1_InsertEmail.getUserText().contains(readFromThisFile("userName")),readFromThisFile("UserNameTestDescription"));
        return this;
    }
}
