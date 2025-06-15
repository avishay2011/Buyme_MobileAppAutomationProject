package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    //constructor
    public HomePage(AndroidDriver driver){
        super(driver);
    }

    //elements
    private By login_Button = By.id("anonymous_user");
    private By profileAndInfoLink = By.id("il.co.mintapp.buyme:id/profileTab");
    private By searchButton = By.id("search_icon");
    private By clearSearch_Button = By.id("search_close_btn");
    private By gifts_Links = By.id("il.co.mintapp.buyme:id/title");
    private By fashionBrandsCategory = By.id("il.co.mintapp.buyme:id/title");

    // =============== Actions =====================

    @Step("Click - \"Login\" - button")
    // Clicks the login button
    public HomePage navigateToRegistrationPage(){
        click(login_Button);
        return this;
    }

    @Step("Navigate to profile and information page")
    // Clicks the profile tab to navigate to profile and info page
    public HomePage navigateToProfileAndInfoPage(){
        click(profileAndInfoLink);
        return this;
    }

    @Step("Select  category  by  dropdown")
    // Selects a category by matching text in dropdown list
    public HomePage selectCategory(String category){
        List<WebElement> dropDownList_AmountsValues_Elems = driver.findElements(gifts_Links);
        for (WebElement elem : dropDownList_AmountsValues_Elems){
            if(elem.getText().contains(category)){
                elem.click();
                break;
            }
        }
        return this;
    }

    @Step("Select  category  by  dropdown")
    // Clicks the fashion brands category
    public HomePage selectFashionBrandsCategory(){
        click(fashionBrandsCategory);
        return this;
    }

    @Step("Click - find gift - button")
    // Clicks the search button to initiate gift search
    public HomePage clickFindMeGift(){
        click(searchButton);
        return this;
    }

    @Step("Click \"Clear search\" button")
    // Clicks the button to clear the search input field
    public HomePage clearSearch(){
        click(clearSearch_Button);
        return this;
    }
}



