package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class Purchase_GiftCard_Step2_Page extends BasePage{
    public Purchase_GiftCard_Step2_Page(AndroidDriver driver) {
        super(driver);
    }

    //Elements

    private By pageTitle=By.id("il.co.mintapp.buyme:id/title");

    //Functions
    ///Method  verify by title that purchase page step 2 opened b
    public boolean isPurchasePageStep2HowToSendOpened() throws ParserConfigurationException, IOException, SAXException { //The name price inside the url is indication that is step 1 in the purchase process
        return getText(pageTitle).equalsIgnoreCase(readFromThisFile("purchasePage2Message"));
    }

}
