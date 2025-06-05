package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


public class MailinatorPage extends BasePage{
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public MailinatorPage(AndroidDriver driver) {
        super(driver);
    }

    //elements
    private By searchEmailField = By.cssSelector("#search-mobile");


    //Methods
    @Step("Search email")
    public MailinatorPage searchEmail() throws InterruptedException {
        // גלול אל האלמנט
        js.executeScript("document.getElementById('search-mobile').scrollIntoView(true);");
        Thread.sleep(300);

        // קליק באמצעות JS
        js.executeScript("document.getElementById('search-mobile').click();");
        Thread.sleep(300);

        // נקה קודם את השדה (sendKeys עם Ctrl+A + Delete לא תמיד עובד, לכן JS)
        js.executeScript("document.getElementById('search-mobile').value = '';");
        Thread.sleep(200);

        // הזן את הטקסט דרך JS וטריגר אירוע input
        js.executeScript(
                "var input = document.getElementById('search-mobile');" +
                        "input.value = 'AAA';" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));"
        );
        Thread.sleep(500);

        // אפשר לשלוח גם sendKeys כדי לוודא שהדפדפן "מרגיש" את הקלט
        sendKeys(searchEmailField, "AAA");

        return this;
    }


}
