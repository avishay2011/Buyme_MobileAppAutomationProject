package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BirthDayGifts_Page extends BasePage {
    public BirthDayGifts_Page(AndroidDriver driver) {
        super(driver);
    }

    //elements
    private By birthDay_gifts_List = By.id("il.co.mintapp.buyme:id/businessName");

    private List<WebElement> getBirthDayGiftsList() {
        return getElementsFromListLocation(birthDay_gifts_List);
    }

    //Methods
    @Step("Verify that the list of birthday gifts is visible and all elements are displayed")
    public boolean isBirthDayGiftsDisplayed() {
        List<WebElement> birthDayGiftsList = getBirthDayGiftsList();
        return !birthDayGiftsList.isEmpty() && birthDayGiftsList.stream().allMatch(WebElement::isDisplayed);
    }

    @Step("Select gift from list")  //Open dropdown and select value
    public BirthDayGifts_Page selectGift(String giftForSearch) {
        List<WebElement> birthDayGiftsList = getBirthDayGiftsList();
        for (WebElement element : birthDayGiftsList) {
            if (element.getText().toLowerCase().contains(giftForSearch)) {
                element.click();
                break;
            }
        }
        return this;
    }
}
