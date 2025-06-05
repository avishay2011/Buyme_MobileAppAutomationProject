package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProfileAndInfo_Page extends BasePage{
    public ProfileAndInfo_Page(AndroidDriver driver) {
        super(driver);
    }

    //Elements
    private By assigningGiftsButton=By.xpath("//*[contains(@text, 'שיוך')]");

    //Methods
    public ProfileAndInfo_Page navigateToassigningGiftsPage(){
        click(assigningGiftsButton);
        return this;
    }
}
