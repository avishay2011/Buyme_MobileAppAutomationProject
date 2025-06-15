package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class Tests04_CheckGiftCardBalance_Mobile extends BaseTest {
    @BeforeMethod
    public void checkPageReadyAndPreConditions() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        checkGiftCardBalanceFlows.checkPageReadyAndPreConditions();
    }

    @Test
    public void test01_WrongGiftCardCodeErrorMessage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        checkGiftCardBalanceFlows.verifyWrongCardCodeErrorFlow();
        verifications.assertAll();
    }

    @Test
    public void test02_UsedGiftCardCodeMessage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        checkGiftCardBalanceFlows.verifyUsedGiftCardCodeMessageFlow();
        verifications.assertAll();
    }

    @Test
    public void test03_ValidGiftCardCodeMessage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        checkGiftCardBalanceFlows.verifyValidGiftCardCodeMessage();
        verifications.assertAll();
    }

}
