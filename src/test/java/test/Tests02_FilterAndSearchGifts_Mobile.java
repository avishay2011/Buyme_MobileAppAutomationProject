package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@Listeners(utils.Listeners.class)
public class Tests02_FilterAndSearchGifts_Mobile extends BaseTest {



    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Filter potential gifts and verify that only the relevant gifts appears on list after filter ")
    //The same filtering process is performed at the beginning of both tests.
    public void test01_Filter_And_Search_Gifts() throws ParserConfigurationException, IOException, SAXException {
        filterAndSearchGiftsFlows.filterAndSearchGiftsFLow();
        verifications.assertAll();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Clear filter fields and verify that all the filter selections have made resets to default")
    public void test02_Filter_And_Clear_Filter_Fields() throws ParserConfigurationException, IOException, SAXException {
        filterAndSearchGiftsFlows.filterAndClearFilterFields();
        verifications.assertAll();

    }
}

