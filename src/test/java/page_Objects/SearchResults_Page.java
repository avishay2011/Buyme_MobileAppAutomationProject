package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DropDownValues_And_ElementsMap;

import java.util.List;

public class SearchResults_Page extends BasePage {
    public SearchResults_Page(AndroidDriver driver) {
        super(driver);
    }

    //elements
    private By searchResults_HeadLine = By.id("il.co.mintapp.buyme:id/categoryName");
    private By bussinessListLocation = By.id("il.co.mintapp.buyme:id/businessImage");
    private By filtersMainButton = By.id("il.co.mintapp.buyme:id/filters_button");
    private By regionsButton = By.xpath("//*[contains(@text, 'כל האזורים')]");
    private By amountsButton = By.xpath("//*[contains(@text, 'הכל')]");
    private By amountsButton2 = By.xpath("//*[contains(@text, 'טווח מחירים')]");
    private By regionsListLocation = By.id("il.co.mintapp.buyme:id/check_box");
    private By amountsListLocation = By.xpath("//android.widget.RadioButton");
    private By closeDropdown = By.id("il.co.mintapp.buyme:id/arrow");
    private By filterButton = By.id("il.co.mintapp.buyme:id/submit_filter");
    private By searchResultsList = By.id("il.co.mintapp.buyme:id/businessName");
    private By clearFiltersButton = By.id("il.co.mintapp.buyme:id/clear_filters");
    private By allAreasDropdownValue = By.xpath("//*[contains(@text, 'כל האזורים')]");
    private By allAmountsDropdownValue = By.xpath("//*[contains(@text, 'הכל')]");

    //functions

    @Step
    // Clicks the main filters button
    public SearchResults_Page clickMainFiltersButton() {
        click(filtersMainButton);
        return this;
    }

    @Step
    // Clicks the regions filter button
    public SearchResults_Page clickRegions() {
        click(regionsButton);
        return this;
    }

    @Step
    // Clicks the amounts filter button
    public SearchResults_Page clickAmounts() {
        click(amountsButton);
        return this;
    }

    @Step
    // Clicks the amounts filter button (alternate name after filtering)
    public SearchResults_Page clickAmounts2() {
        click(amountsButton2);
        return this;
    }

    @Step
    // Clicks the apply filter button
    public SearchResults_Page clickFilterButton() {
        click(filterButton);
        return this;
    }

    @Step
    // Clicks to close the dropdown menu
    public SearchResults_Page clickCloseDropDown() {
        click(closeDropdown);
        return this;
    }

    @Step("Select  category  by  dropdown")
    // Selects a region from the regions dropdown by text match
    public SearchResults_Page selectRegion(String region) {
        List<WebElement> dropDownList_AmountsValues_Elems = driver.findElements(regionsListLocation);
        for (WebElement elem : dropDownList_AmountsValues_Elems) {
            if (elem.getText().contains(region)) {
                elem.click();
                break;
            }
        }
        return this;
    }

    @Step("Select  category  by  dropdown")
    // Selects an amount range from the amounts dropdown by text match
    public SearchResults_Page selectAmount(String amount) {
        List<WebElement> dropDownList_AmountsValues_Elems = driver.findElements(amountsListLocation);
        for (WebElement elem : dropDownList_AmountsValues_Elems) {
            if (elem.getText().contains(amount)) {
                elem.click();
                break;
            }
        }
        return this;
    }

    @Step
    // Gets the headline text of search results
    public String getTextSearchResults() {
        return getText(searchResults_HeadLine);
    }

    @Description("Verify that all Categories inside the dropdown list are correct")
    @Step
    // Checks that all business elements are displayed
    public boolean areAllCategoryElemsAppears() {
        List<WebElement> bussinessElems = driver.findElements(bussinessListLocation);
        boolean areAlleElementsAppears = true;
        for (WebElement elem : bussinessElems) {
            if (!elem.isDisplayed()) {
                areAlleElementsAppears = false;
                break;
            }
        }
        return areAlleElementsAppears;
    }

    @Step
    @Description("Verify that the regions inside the dropdown list match expected values")
    // Verifies that dropdown regions match expected list
    public boolean areAllRegionsDropdownValuesMatchExpected() {
        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedRegions(), regionsListLocation);
    }

    @Step
    @Description("Verify that the amounts inside the dropdown list match expected values")
    // Verifies that dropdown amounts match expected list
    public boolean areAllAmountsDropdownValuesMatchExpected() {
        return areDropdownValuesMatchExpected(DropDownValues_And_ElementsMap.getExpectedAmounts(), amountsListLocation);
    }

    @Step
    @Description("Check if search results appear")
    // Checks if any search results are displayed
    public boolean areSearchResultsApears() {
        List<WebElement> bussinessElems = driver.findElements(searchResultsList);
        return !bussinessElems.isEmpty();
    }

    @Step
    @Description("Clear filters")
    // Clicks the button to clear all filters
    public SearchResults_Page clickclearFilters() {
        click(clearFiltersButton);
        return this;
    }

    @Step
    @Description("Check if 'All Areas' dropdown value is checked")
    // Checks if "All Areas" dropdown value is selected
    public boolean isAllAreasDrowdownValueIsChecked() {
        return driver.findElement(allAreasDropdownValue).getAttribute("checked").equals("true");
    }

    @Step
    @Description("Check if 'All Amounts' dropdown value is checked")
    // Checks if "All Amounts" dropdown value is selected
    public boolean isAllAmountsDrowdownValueIsChecked() {
        return driver.findElement(allAmountsDropdownValue).getAttribute("checked").equals("true");
    }
}
