
package page_Objects;


import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static utils.Utilities.readFromThisFile;


public class RegistrationPage_Step1_InsertEmail extends BasePage {

    //constructor
    public RegistrationPage_Step1_InsertEmail(AndroidDriver driver){
        super(driver);
    }

    //elements
    private By closeMobilePopupButton=By.id("btnClose");
    private By login_ByEmail_Button=By.id("emailButton");
    private By login_ByGoogle_Button=By.id("il.co.mintapp.buyme:id/googleButton");
    private By googleAccount=By.id("com.google.android.gms:id/account_display_name");
    private By addAccountButton=By.id("com.google.android.gms:id/add_account_chip_title");
    private By emailInputField=By.id("il.co.mintapp.buyme:id/edit_text");
    private By userNameText=By.id("il.co.mintapp.buyme:id/username_text");
    private By enter_Button=By.id("enterButton");
    private By errorMessage_WrongEmail=By.id("error_tv");


    // =============== Actions =====================
    // =============================================
    @Step("Close popup")
    public RegistrationPage_Step1_InsertEmail closeMobilePopup(){
        click(closeMobilePopupButton);
        return this;
    }


    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail select_Register_By_GoogleAccount(){
        click(login_ByGoogle_Button);
        return this;
    }

    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail select_LoginByEmailAccount(){
        click(login_ByEmail_Button);
        return this;
    }

    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail insertEmailAccount(){
        sendKeys(emailInputField,"AAAA");
        return this;
    }

    @Step("Close popup")
    public RegistrationPage_Step1_InsertEmail selectGoogleAccount(){
        click(googleAccount);
        return this;
    }

    @Step
    public String getUserText(){
        return getText(userNameText);
    }


    @Step("Click enter button ")
    public RegistrationPage_Step1_InsertEmail click_Enter_After_InsertEmail(){
        click(enter_Button);
        return this;
    }

    @Step("Click enter button ")
    public RegistrationPage_Step1_InsertEmail navigateToMailiNator(){
        openChromeAndSwitchToWebView();
        return this;
    }


    public void openChromeAndSwitchToWebView() {
        try {
            // 1. פותח את כרום עם ה-URL באמצעות אינטנט
            Map<String, Object> args = new HashMap<>();
            args.put("intent", "android.intent.action.VIEW");
            args.put("url", "https://www.mailinator.com/");
            args.put("package", "com.android.chrome");
            args.put("flags", 0x10000000); // FLAG_ACTIVITY_NEW_TASK
            args.put("category", "android.intent.category.BROWSABLE");

            // מבצע את ה-deepLink
            driver.executeScript("mobile: deepLink", args);

            // 2. מחכה כמה שניות שכרום ייטען
            Thread.sleep(7000); // עדיף להחליף ב-explicit wait בהמשך

            // 3. הדפסת כל הקונטקסטים הזמינים
            Set<String> contexts = driver.getContextHandles();
            System.out.println("=== Available contexts ===");
            for (String context : contexts) {
                System.out.println(context);
            }

            // 4. מעבר ל-context מסוג WEBVIEW (אם קיים)
            boolean switched = false;
            for (String context : contexts) {
                if (context.toLowerCase().contains("webview") || context.toLowerCase().contains("chrome")) {
                    driver.context(context);
                    System.out.println("✅ Switched to context: " + context);
                    switched = true;
                    break;
                }
            }

            if (!switched) {
                System.out.println("❌ לא נמצא context מסוג WebView או Chrome. נשארת ב-NATIVE_APP.");
            }

        } catch (Exception e) {
            System.out.println("שגיאה במהלך פתיחת כרום או מעבר ל-WebView:");
            e.printStackTrace();
        }
    }

    public void backToBuyMeAPP(){
        try {
            driver.activateApp("il.co.mintapp.buyme");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    // =============== Getters and validations =====================
    // =============================================


    @Step
    public String getTextInvalidEmailErrorMessage(){
        return getText(errorMessage_WrongEmail);
    }




}
