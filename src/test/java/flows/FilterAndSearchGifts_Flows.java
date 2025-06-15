package flows;

import io.appium.java_client.android.AndroidDriver;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import page_Objects.HomePage;
import page_Objects.RegistrationPage_Step1_InsertEmail;
import page_Objects.SearchResults_Page;
import test.BaseTest;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

public class FilterAndSearchGifts_Flows extends BaseTest {
    public FilterAndSearchGifts_Flows(AndroidDriver driver) {
        this.driver = driver;
        this.registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        this.homePage = new HomePage(driver);
        this.searchResults_page=new SearchResults_Page(driver);
        this.softAssert =new SoftAssert();
        this.verifications=new Verifications(driver,softAssert);
    }

    private FilterAndSearchGifts_Flows applyFiltersAndVerify() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.scrollDown(1);
        homePage.selectCategory(readFromThisFile("filterCategory"));
        verifications.verifyTextEquals(searchResults_page.getTextSearchResults(),readFromThisFile("categoryName"),"Verify the category is correct");
        verifications.verifyTrue(searchResults_page.areAllCategoryElemsAppears(),"Verify that all fashion elements appears");
        searchResults_page.clickMainFiltersButton();
        searchResults_page.clickRegions();
        verifications.verifyTrue(searchResults_page.areAllRegionsDropdownValuesMatchExpected(),"Verify that all regions on list are correct");
        searchResults_page.selectRegion(readFromThisFile("filterRegion"));
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickAmounts();
        verifications.verifyTrue(searchResults_page.areAllAmountsDropdownValuesMatchExpected(),"Verify that all amounts on list are correct");
        searchResults_page.selectAmount(readFromThisFile("filterAmount"));
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickFilterButton();
        verifications.verifyTrue(searchResults_page.areSearchResultsApears(),"Verify search results after filter process");
        return this;
    }

    public FilterAndSearchGifts_Flows filterAndSearchGiftsFLow() throws ParserConfigurationException, IOException, SAXException {
        applyFiltersAndVerify();
        return this;
    }

    public FilterAndSearchGifts_Flows filterAndClearFilterFields() throws ParserConfigurationException, IOException, SAXException {
        applyFiltersAndVerify();
        searchResults_page.clickMainFiltersButton();
        searchResults_page.clickclearFilters();
        verifications.verifyTrue(searchResults_page.isAllAreasDrowdownValueIsChecked(),"Verify that regions filter clearded");
        searchResults_page.clickAmounts2();
        verifications.verifyTrue(searchResults_page.isAllAmountsDrowdownValueIsChecked(),"Verify that amounts filter clearded");
        return this;
    }
}


