package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.xml.sax.SAXException;
import utils.DropDownValues_And_ElementsMap;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static utils.Utilities.readFromThisFile;

public class Purchase_GiftCard_Step1_Page extends BasePage{
    public Purchase_GiftCard_Step1_Page(AndroidDriver driver) {
        super(driver);
    }

    //Elenents
    private By firstStep_Title=By.id("il.co.mintapp.buyme:id/text_title");
    private By giftRecieverName_Input=By.id("il.co.mintapp.buyme:id/search_text");
    private By whatAreWeCelebrating_Dropdown_Button=By.id(".b2c-dropdown__wrapper__icon");
    private By blessingFreeText_Field=By.id("greeting-b2c-textarea");
    private By addPicOrVideo_Button=By.id("add-pic-selection_button__input");
    private By continue_Button=By.id("button[type=\"submit\"]");
    private By celebrationsReasons_List=By.id("ul[role=\"listbox\"]>li");
    private By contactsListLoaction=By.id("il.co.mintapp.buyme:id/contact_name");
    private By giftForMySelf_Message=By.id("il.co.mintapp.buyme:id/title");
    private By recieverContact=By.id("il.co.mintapp.buyme:id/contact_name");

    // =============== Actions =====================
    // =============================================

    @Step("Send keys '{recieverName}' on {giftReciever_Name}")
    public Purchase_GiftCard_Step1_Page sendKey_GiftReciever_Name(String recieverName){
        sendKeys(giftRecieverName_Input,recieverName);
        return this;
    }

    @Step("Send keys '{blessing}' on {blessingFreeText_Field}")
    public Purchase_GiftCard_Step1_Page sendKey_Blessing(String blessing){
        sendKeys(blessingFreeText_Field,blessing);
        return this;
    }

    @Step("Send keys '{picPath}' on {addPicOrVideo_Button}")
    public Purchase_GiftCard_Step1_Page sendKeys_addPicOrVideo(String picPath){
        sendKeys(addPicOrVideo_Button,picPath);
        return this;
    }

    @Step("Send keys '{picPath}' on {addPicOrVideo_Button}")
    @Description("Select celebration reason from list ")
    public Purchase_GiftCard_Step1_Page select_CelebrationReason(String celebrationReason){
        click(whatAreWeCelebrating_Dropdown_Button);
        List<WebElement> celebrationReasons_List=getElementsFromListLocation(celebrationsReasons_List);
        for (WebElement element:celebrationReasons_List)
            if(element.getText().contains(celebrationReason)){
                element.click();
                break;
            }
        return this;
    }
    @Step("Click - \"Continue\" ")
    public Purchase_GiftCard_Step1_Page click_Continue(){
        click(continue_Button);
        return this;
    }

    @Step("Select - gift for myself")
    public Purchase_GiftCard_Step1_Page clickOnRecieverContact(){
        click(recieverContact);
        return this;
    }
    @Step("Select - gift for myself")
    public Purchase_GiftCard_Step1_Page click_For_Myself(){
        List<WebElement> contactsList=getElementsFromListLocation(contactsListLoaction);
        for (WebElement element:contactsList)
            if(element.getText().contains("מתנה לעצמי")){
                element.click();
                break;
            }
        return this;
    }




    // =============== Getters and validations =====================
    // =============================================

    @Step
    public boolean isPurchasePageStep1Opened() throws ParserConfigurationException, IOException, SAXException { //The name price inside the url is indication that is step 1 in the purchase process
        return getText(firstStep_Title).equalsIgnoreCase("למי המתנה?");
    }


//    @Step
//    @Description("Verify that all expected elements in page displayed ") /// The expected elements and their names have taken from another class
//    public boolean areAllExpectedElementsDisplayed(){  ///Map include string that contains name and that contains location
//        Map<String, By> elementsMap = DropDownValues_And_ElementsMap.getElementsMap(); //If element (found by location) not displayed return false , fail the method and report allure with the element name
//        boolean areAllElementsDisplayed = true;
//        for (Map.Entry<String, By> entry : elementsMap.entrySet()) {
//            String elementName = entry.getKey();
//            By locator = entry.getValue();
//            try {
//                if (!driver.findElement(locator).isDisplayed()) {
//                    Allure.step("❌ Element not displayed: " + elementName, Status.FAILED);
//                    areAllElementsDisplayed = false;
//                    Assert.fail();
//                }
//            } catch (NoSuchElementException error) {
//                Allure.step("❌ Element not found in DOM: " + elementName, Status.FAILED);
//                areAllElementsDisplayed = false;
//                Assert.fail();
//            }
//        }

//        if (areAllElementsDisplayed) {
//            Allure.step("✅ All expected elements are displayed");
//        }
//
//        return areAllElementsDisplayed;
//    }

    @Step
    public String getTextgiftCardForMyselfMessage(){
        return getText(giftForMySelf_Message);
    }

}
