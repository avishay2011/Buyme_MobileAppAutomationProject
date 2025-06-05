
package page_Objects;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DropDownValues_And_ElementsMap;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    //constructor
    public HomePage(AndroidDriver driver){
        super(driver);
    }

    //elements
  private By login_Button=By.id("anonymous_user");
  private By profileAndInfoLink=By.id("il.co.mintapp.buyme:id/profileTab");
  private By searchButton=By.id("search_icon");
  private By clearSearch_Button=By.id("search_close_btn");
  private By gifts_Links = By.id("il.co.mintapp.buyme:id/title");
  private By fashionBrandsCategory = By.id("il.co.mintapp.buyme:id/title");




    // =============== Actions =====================
    // =============================================
    @Step("Click - \"Login\" - button")
    public HomePage navigateToRegistrationPage(){
        click(login_Button);
        return this;
    }

    @Step("Navigate to profile and information page")
    public HomePage navigateToProfileAndInfoPage(){
        click(profileAndInfoLink);
        return this;
    }

//    @Step("Click - \"Login\" - button")
//    public HomePage scrollToChefRestraunt(){
//        scrollToVisibleTextAndClick("שף",titleInsideList);
//        click(login_Button);
//        return this;
//    }





//    @Step
//    public boolean homePageIsDisplayed() {  // verify that the right page loaded by  elem location that unique for page. Search button is unique for home page
//            return isElementDisplayed(searchButton);
//    }

//    @Step("Navigate to \" my account details \" - page")
//    public HomePage navigateToMyAccountDetailsPage(){
//         click(myAccount_Link);
//         return this;
//        }

//    @Step("Open dropdown - amounts")
//    public HomePage openAmounts_Dropdown(){
//        click(amounts_Dropdown_Button);
//        return this;
//    }

//    @Step("Open dropdown - regions")
//    public HomePage openRegions_Dropdown(){
//        click(regions_Dropdown_Button);
//        return this;
//    }
//
//    @Step("Open dropdown - categories")
//    public HomePage openCategories_Dropdown(){
//        click(categories_Dropdown_Button);
//        return this;
//    }

//    @Step("Select  amounts range  by dropdown ")
//    public HomePage selectAmount(String amount){
//        List<WebElement> dropDownList_AmountsValues_Elems=driver.findElements(dropDownList_Values);
//        for (WebElement elem: dropDownList_AmountsValues_Elems){
//            if(elem.getText().contains(amount)){
//                elem.click();
//                break;
//            }
//        }
//        return this;
//    }

//    @Step("Select  region  by dropdown ")
//    public HomePage selectRegion(String region){
//        List<WebElement> dropDownList_AmountsValues_Elems=driver.findElements(dropDownList_Values);
//        for (WebElement elem: dropDownList_AmountsValues_Elems){
//            if(elem.getText().contains(region)){
//                elem.click();
//                break;
//            }
//        }
//        return this;
//    }

    @Step("Select  category  by  dropdown")
    public HomePage selectCategory(String category){
        List<WebElement> dropDownList_AmountsValues_Elems=driver.findElements(gifts_Links);
        for (WebElement elem: dropDownList_AmountsValues_Elems){
           if(elem.getText().contains(category)){
                elem.click();
               break;
            }
        }
        return this;
    }

    @Step("Select  category  by  dropdown")
    public HomePage selectFashionBrandsCategory(){
        click(fashionBrandsCategory);
        return this;
    }



    @Step("Click - find gift - button")
    public HomePage clickFindMeGift(){
        click(searchButton);
        return this;
    }

//    @Step("Click on link -birth day gifts")
//    public HomePage click_BirthDayGifts_Link(){
//        click(birthDayGifts_Link);
//        return this;
//    }






    @Step("Click \"Clear search\" button")
    public HomePage clearSearch(){
        click(clearSearch_Button);
        return this;
    }



//    @Step("Click \"Log out\" - button")
//    public HomePage logOut(){
//        try {
//            hoverToElement(myAccount_DropDown);
//            List<WebElement> closeButtons = driver.findElements(logOut_Button);
//            if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {
//                closeButtons.get(0).click();
//            }
//        }
//        catch (Exception error){
//        }
//        return this;
//    }


    // =============== Getters and validations =====================
    // =============================================


//    @Step
//    @Description("Verify that the amount range  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
//    public boolean areAllAmountsDropdownValuesMatchExpected(){
//        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedAmounts(),dropDownList_Values);
//    }

//    @Description("Verify that all country Regiongs  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
//    @Step
//    public boolean areAllRegionsDropdownValuesMatchExpected(){
//        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedRegions(),dropDownList_Values);
//    }
//
//    @Description("Verify that all  Categories  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
//    @Step
//    public boolean areAllCategoriesDropdownValuesMatchExpected(){
//        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedCategories(),dropDownList_Values);
//    }



//    @Step
//    public boolean isAmountFieldIsClear(){
//        return isElementDisplayed(amounts_Dropdown_Button); ////This element is displayed only while amount field is clear
//    }

//    @Step
//    public boolean isRegionFieldIsClear(){
//        return isElementDisplayed(regions_Dropdown_Button); ////This element is displayed only while region field is clear
//    }

//    @Step
//    public boolean isCategoryFieldIsClear(){
//        return isElementDisplayed(categories_Dropdown_Button); ////This element is displayed only while category field is clear
//    }


}






