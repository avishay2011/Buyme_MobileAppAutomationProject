package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class AssigningGifts_Page extends BasePage {

    public AssigningGifts_Page(AndroidDriver driver) {
        super(driver);
    }

    // Elements (Getters)
    private By giftCardCodeField = By.id("il.co.mintapp.buyme:id/shovarNumberInput");
    private By expirationDayField = By.xpath("//*[contains(@text, 'הזן תוקף')]");
    private By selectedDate = By.xpath("//*[@text='1']"); // Date picker selected date
    private By nextButton = By.id("android:id/next");
    private By daysListLocation = By.cssSelector(".android.view.View");
    private By approveDateButton = By.id("android:id/button1");
    private By addGifcardButton = By.id("il.co.mintapp.buyme:id/addVoucherButton");

    // Action Methods

    @Step("Send keys '{gift card code}' on {gift card field _Field}")  // Types the given gift card code into the gift card input field and hides the keyboard.
    public AssigningGifts_Page sendKeysGiftCardCode(String giftCardCode) {
        sendKeys(giftCardCodeField, giftCardCode);
        driver.hideKeyboard();
        return this;
    }

    @Step("Tap on expiration day field") // Opens the expiration date picker by clicking the expiration day field twice.
    public AssigningGifts_Page openExpirationDayField() throws InterruptedException {
        click(expirationDayField);
        Thread.sleep(100); // optional wait for UI update
        click(expirationDayField);
        return this;
    }

    @Step("Select a date by day, month, year") // Selects a date on the date picker by day, month, and year
    public AssigningGifts_Page select_Date_Picker(String day, String month, String year) throws InterruptedException {
        select_Year_DatePicker(year);
        select_Month_DatePicker(month);
        select_Day_DatePicker(day);
        clickApproveDate();
        return this;
    }

    @Step("Select year on date picker")  // Selects the year on the date picker by clicking the next button until the year appears or max clicks reached
    public AssigningGifts_Page select_Year_DatePicker(String year) throws InterruptedException {
        String selectedDateOnScreen = driver.findElement(selectedDate).getAttribute("content-desc");
        int clicksCount = 0;
        while (!selectedDateOnScreen.contains(year) && clicksCount < 72) {
            click(nextButton);
            selectedDateOnScreen = driver.findElement(selectedDate).getAttribute("content-desc");
            clicksCount++;
        }
        if (!selectedDateOnScreen.contains(year)) {
            Allure.step("Failed to select year '" + year + "' after " + clicksCount + " clicks", Status.FAILED);
        }
        return this;
    }

    @Step("Select month on date picker")     // Selects the month on the date picker by clicking the next button until the month appears or max clicks reached
    public AssigningGifts_Page select_Month_DatePicker(String month) throws InterruptedException {
        String selectedDateOnScreen = driver.findElement(selectedDate).getAttribute("content-desc");
        int clicksCount = 0;
        while (!selectedDateOnScreen.contains(month) && clicksCount < 12) {
            click(nextButton);
            selectedDateOnScreen = driver.findElement(selectedDate).getAttribute("content-desc");
            clicksCount++;
        }
        if (!selectedDateOnScreen.contains(month)) {
            Allure.step("Failed to select month '" + month + "' after " + clicksCount + " clicks", Status.FAILED);
        }
        return this;
    }

    @Step("Select day on date picker")   //Selects the day on the date picker by clicking on the day in the list
    public AssigningGifts_Page select_Day_DatePicker(String day) {
        List<WebElement> daysList = getElementsFromListLocation(daysListLocation);
        boolean found = false;
        for (WebElement element : daysList) {
            if (element.getText().equalsIgnoreCase(day)) {
                element.click();
                found = true;
                break;
            }
        }
        if (!found) {
            Allure.step("Day '" + day + "' was not found in the calendar, skipping click", Status.FAILED);
        }
        return this;
    }

    @Step("Click approve date button") // Clicks the approve date button to confirm the selected date.
    public AssigningGifts_Page clickApproveDate() {
        click(approveDateButton);
        return this;
    }

    @Step("Click add gift card button")       ///* Scrolls down once and clicks the "Add Gift Card" button.
    public AssigningGifts_Page clickAddGiftCard() {
        scrollDown(1);
        click(addGifcardButton);
        return this;
    }

    @Step("Get toast view text")   // Returns the text of the toast message displayed on screen.
    public String getToastViewText() {
        WebElement toastView = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.Toast[1]")
        ));
        return toastView.getText();}
}