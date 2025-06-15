package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DropDownValues_And_ElementsMap;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;

import static utils.Utilities.readFromThisFile;

public class Purchase_GiftCard_Step1_Page extends BasePage {
    public Purchase_GiftCard_Step1_Page(AndroidDriver driver) {
        super(driver);
    }

    // Elements
    private By firstStep_Title = By.id("il.co.mintapp.buyme:id/text_title");
    private By giftRecieverName_Input = By.id("il.co.mintapp.buyme:id/search_text");
    private By contactsListLoaction = By.id("il.co.mintapp.buyme:id/contact_name");
    private By giftForMySelf_Message = By.id("il.co.mintapp.buyme:id/title");
    private By recieverContact = By.id("il.co.mintapp.buyme:id/contact_name");

    // =============== Actions =====================


    @Step("Send keys '{recieverName}' on {giftReciever_Name}")
    // Enters the receiver's name in the input field
    public Purchase_GiftCard_Step1_Page sendKey_GiftReciever_Name(String recieverName) {
        sendKeys(giftRecieverName_Input, recieverName);
        return this;
    }

    @Step("Select - gift for myself")
    // Clicks on the receiver contact element
    public Purchase_GiftCard_Step1_Page clickOnRecieverContact() {
        click(recieverContact);
        return this;
    }

    @Step("Select - gift for myself")
    // Selects the "gift for myself" option from the contacts list
    public Purchase_GiftCard_Step1_Page click_For_Myself() throws ParserConfigurationException, IOException, SAXException {
        List<WebElement> contactsList = getElementsFromListLocation(contactsListLoaction);
        for (WebElement element : contactsList)
            if (element.getText().contains(readFromThisFile("giftForMySelf_Message"))) {
                element.click();
                break;
            }
        return this;
    }

    // =============== Getters and validations =====================


    @Step
    // Checks if the purchase step 1 page is opened by verifying the title text
    public boolean isPurchasePageStep1Opened() throws ParserConfigurationException, IOException, SAXException {
        return getText(firstStep_Title).equalsIgnoreCase(readFromThisFile("purchasePage1Message"));
    }

    @Step
    // Retrieves the text of the "gift for myself" message element
    public String getTextgiftCardForMyselfMessage() {
        return getText(giftForMySelf_Message);
    }
}
