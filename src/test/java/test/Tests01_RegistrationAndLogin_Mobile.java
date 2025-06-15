package test;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Tests01_RegistrationAndLogin_Mobile extends BaseTest{

    @Test
    public void test01_loginValidUserNameGoogleAccount() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        registrationAndLoginFlows.loginValidUserNameGoogleAccountFlow();
        verifications.assertAll();
    }


    @Test
    public void test02_loginInvalidUserName() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
       registrationAndLoginFlows.loginInvalidUserNameFlow();
       verifications.assertAll();
    }

    @Test
    public void test03_loginInvalidPassword() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
       registrationAndLoginFlows.loginInvalidPasswordFlow();
       verifications.assertAll();
    }

}
