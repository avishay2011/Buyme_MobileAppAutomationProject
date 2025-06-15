package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import verifications.Verifications;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.Utilities.readFromThisFile;

@Listeners(utils.Listeners.class)
public class Tests02_FilterAndSearchGifts extends BaseTest {



    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Filter potential gifts and verify that only the relevant gifts appears on list after filter ")
    //The same filtering process is performed at the beginning of both tests.
    public void test01_Filter_And_Search_Gifts() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.scrollDown(1);
        homePage.selectCategory("2025");
        verifications.verifyTextEquals(searchResults_page.getTextSearchResults(),"המתנות האהובות של 2025","Verify the category is correct");
        verifications.verifyTrue(searchResults_page.areAllCategoryElemsAppears(),"Verify that all fashion elements appears");
        searchResults_page.clickMainFiltersButton();
        searchResults_page.clickRegions();
        verifications.verifyTrue(searchResults_page.areAllRegionsDropdownValuesMatchExpected(),"Verify that all regions on list are correct");
        searchResults_page.selectRegion("דרום");
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickAmounts();
        verifications.verifyTrue(searchResults_page.areAllAmountsDropdownValuesMatchExpected(),"Verify that all amounts on list are correct");
        searchResults_page.selectAmount("199");
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickFilterButton();
        verifications.verifyTrue(searchResults_page.areSearchResultsApears(),"Verify search results after filter process");
        verifications.assertAll();

    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Clear filter fields and verify that all the filter selections have made resets to default")
    public void test02_Filter_And_Clear_Filter_Fields() throws ParserConfigurationException, IOException, SAXException {
        registrationPage_Step1_InsertEmail.closeMobilePopup();
        homePage.scrollDown(1);
        homePage.selectCategory("2025");
        verifications.verifyTextEquals(searchResults_page.getTextSearchResults(),"המתנות האהובות של 2025","Verify the category is correct");
        verifications.verifyTrue(searchResults_page.areAllCategoryElemsAppears(),"Verify that all fashion elements appears");
        searchResults_page.clickMainFiltersButton();
        searchResults_page.clickRegions();
        verifications.verifyTrue(searchResults_page.areAllRegionsDropdownValuesMatchExpected(),"Verify that all regions on list are correct");
        searchResults_page.selectRegion("דרום");
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickAmounts();
        verifications.verifyTrue(searchResults_page.areAllAmountsDropdownValuesMatchExpected(),"Verify that all amounts on list are correct");
        searchResults_page.selectAmount("199");
        searchResults_page.clickCloseDropDown();
        searchResults_page.clickFilterButton();
        verifications.verifyTrue(searchResults_page.areSearchResultsApears(),"Verify search results after filter process");
        searchResults_page.clickMainFiltersButton();
        searchResults_page.clickclearFilters();
        verifications.verifyTrue(searchResults_page.isAllAreasDrowdownValueIsChecked(),"Verify that regions filter clearded");
        searchResults_page.clickAmounts2();
        verifications.verifyTrue(searchResults_page.isAllAmountsDrowdownValueIsChecked(),"Verify that amounts filter clearded");
        verifications.assertAll();

    }
}

