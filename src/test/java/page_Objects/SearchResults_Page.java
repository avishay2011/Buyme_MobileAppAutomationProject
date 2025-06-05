package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DropDownValues_And_ElementsMap;

import java.util.List;

public class SearchResults_Page extends BasePage{
    public SearchResults_Page(AndroidDriver driver) {
        super(driver);
    }

    //elements
    private By searchResults_HeadLine=By.id("il.co.mintapp.buyme:id/categoryName");
    private By bussinessListLocation=By.id("il.co.mintapp.buyme:id/businessImage");
    private By filtersMainButton=By.id("il.co.mintapp.buyme:id/filters_button");
    private By regionsButton=By.xpath("//*[contains(@text, 'כל האזורים')]");
    private By amountsButton=By.xpath("//*[contains(@text, 'הכל')]");
    private By amountsButton2=By.xpath("//*[contains(@text, 'טווח מחירים')]");
    private By regionsListLocation=By.id("il.co.mintapp.buyme:id/check_box");
    private By amountsListLocation=By.xpath("//android.widget.RadioButton");
    private By closeDropdown=By.id("il.co.mintapp.buyme:id/arrow");
    private By filterButton=By.id("il.co.mintapp.buyme:id/submit_filter");
    private By searchResultsList=By.id("il.co.mintapp.buyme:id/businessName");
    private By clearFiltersButton=By.id("il.co.mintapp.buyme:id/clear_filters");
    private By allAreasDropdownValue=By.xpath("//*[contains(@text, 'כל האזורים')]");
    private By allAmountsDropdownValue=By.xpath("//*[contains(@text, 'הכל')]");


    //functions

    @Step
    public SearchResults_Page clickMainFiltersButton(){
        click(filtersMainButton);
        return this;
    }

    @Step
    public SearchResults_Page clickRegions(){
        click(regionsButton);
        return this;
    }

    @Step
    public SearchResults_Page clickAmounts(){
        click(amountsButton);
        return this;
    }

    @Step
    public SearchResults_Page clickAmounts2(){  //The name button is changed after first filter
        click(amountsButton2);
        return this;
    }
    @Step
    public SearchResults_Page clickFilterButton(){
        click(filterButton);
        return this;
    }




    @Step
    public SearchResults_Page clickCloseDropDown(){
        click(closeDropdown);
        return this;
    }

    @Step("Select  category  by  dropdown")
    public SearchResults_Page selectRegion(String region){
        List<WebElement> dropDownList_AmountsValues_Elems=driver.findElements(regionsListLocation);
        for (WebElement elem: dropDownList_AmountsValues_Elems){
            if(elem.getText().contains(region)){
                elem.click();
                break;
            }
        }
       return this;
    }

    @Step("Select  category  by  dropdown")
    public SearchResults_Page selectAmount(String amount){
        List<WebElement> dropDownList_AmountsValues_Elems=driver.findElements(amountsListLocation);
        for (WebElement elem: dropDownList_AmountsValues_Elems){
            if(elem.getText().contains(amount)){
                elem.click();
                break;
            }
        }
        return this;
    }



    @Step
    public String getTextSearchResults(){
        return getText(searchResults_HeadLine);
    }

    @Description("Verify that all  Categories  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
    @Step
    public boolean areAllCategoryElemsAppears(){
        List<WebElement> bussinessElems=driver.findElements(bussinessListLocation);
        boolean areAlleElementsAppears=true;
        for (WebElement elem: bussinessElems){
            if(!elem.isDisplayed()){
                areAlleElementsAppears=false;
                break;
            }
        }
        return areAlleElementsAppears;
    }


    @Step
    @Description("Verify that the amount range  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
    public boolean areAllRegionsDropdownValuesMatchExpected(){
        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedRegions(),regionsListLocation);
    }

    @Step
    @Description("Verify that the amount range  inside the dropdown list are correct") ///The helper method for checking this boolean is in base page and the expected have taken from another class
    public boolean areAllAmountsDropdownValuesMatchExpected(){
        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedAmounts(),amountsListLocation);
    }

    @Step
    @Description("Are search results appears")
    public boolean areSearchResultsApears(){
        List<WebElement> bussinessElems=driver.findElements(searchResultsList);
        return !bussinessElems.isEmpty();
    }

    @Step
    @Description("Clear filters")
    public SearchResults_Page clickclearFilters(){
        click(clearFiltersButton);
        return this;
    }

    @Step
    @Description("Clear filters")
    public boolean isAllAreasDrowdownValueIsChecked(){
        return driver.findElement(allAreasDropdownValue).getAttribute("checked").equals("true");
    }

    @Step
    @Description("Clear filters")
    public boolean isAllAmountsDrowdownValueIsChecked(){
        return driver.findElement(allAmountsDropdownValue).getAttribute("checked").equals("true");
    }

}
