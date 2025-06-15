package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

@Listeners(utils.Listeners.class)
public class Tests03_SelectGift_Mobile extends BaseTest {
    @BeforeMethod
    public void checkPageReadyAndPreConditions() throws ParserConfigurationException, IOException, SAXException {
        selectGiftFlows.checkPageReadyAndPreConditions();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Select gift without enter amount and verify that expected error message appears")
    public void test01_Verify_That_Amount_Field_IsMandatory() throws ParserConfigurationException, IOException, SAXException {
        selectGiftFlows.verifyAmountFieldIsMandatoryFlow();
        verifications.assertAll();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Select gift for someone else and verify next step is enabled after required fields are filled.")
    public void test02_Buy_Gift_For_Friend() throws ParserConfigurationException, IOException, SAXException {
        selectGiftFlows.buyGiftForFriendFlow();
        verifications.assertAll();
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Select the option gift for myself  and verify that the correct message appears")
    public void test03_Buy_Gift_ForMyself() throws ParserConfigurationException, IOException, SAXException {
        selectGiftFlows.buyGiftForMyselfFlow();
        verifications.assertAll();
    }
}
