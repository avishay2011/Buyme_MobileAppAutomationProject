package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static utils.Utilities.readFromThisFile;

public class RegistrationPage_Step1_InsertEmail extends BasePage {

    // constructor
    public RegistrationPage_Step1_InsertEmail(AndroidDriver driver) {
        super(driver);
    }

    // elements
    private By closeMobilePopupButton = By.id("btnClose");
    private By login_ByEmail_Button = By.id("emailButton");
    private By login_ByGoogle_Button = By.id("il.co.mintapp.buyme:id/googleButton");
    private By googleAccount = By.id("com.google.android.gms:id/account_display_name");
    private By emailInputField = By.id("il.co.mintapp.buyme:id/edit_text");
    private By userNameText = By.id("il.co.mintapp.buyme:id/username_text");
    private By enter_ButtonAfterEmail = By.id("enterButton");
    private By errorMessage_WrongEmail = By.id("error_tv");
    private By errorMessage_WorngPassword = By.id("il.co.mintapp.buyme:id/error_tv");
    private By enter_ButtonAfterPassword = By.id("il.co.mintapp.buyme:id/enterButton");

    // =============== Actions =====================
    // =============================================

    @Step("Close popup")
    public RegistrationPage_Step1_InsertEmail closeMobilePopup() { // Closes the popup window
        click(closeMobilePopupButton);
        return this;
    }

    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail select_Register_By_GoogleAccount() { // Clicks the Google registration button
        click(login_ByGoogle_Button);
        return this;
    }

    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail select_LoginByEmailAccount() { // Clicks the Email login button
        click(login_ByEmail_Button);
        return this;
    }

    @Step("Select - register by google")
    public RegistrationPage_Step1_InsertEmail insertEmailAccount(String emailAccount) { // Enters the given email into the input field
        sendKeys(emailInputField, emailAccount);
        return this;
    }

    @Step("Close popup")
    public RegistrationPage_Step1_InsertEmail selectGoogleAccount() { // Selects the Google account from the list
        click(googleAccount);
        return this;
    }

    @Step
    public String getUserText() { // Returns the username text displayed
        return getText(userNameText);
    }

    @Step("Click enter button ")
    public RegistrationPage_Step1_InsertEmail click_Enter_After_InsertEmail() { // Clicks the Enter button after entering email
        click(enter_ButtonAfterEmail);
        return this;
    }

    @Step("Click enter button ")
    public RegistrationPage_Step1_InsertEmail click_Enter_After_InsertPassword() { // Clicks the Enter button after entering email
        click(enter_ButtonAfterPassword);
        return this;
    }

    @Step("Click enter button ")
    public RegistrationPage_Step1_InsertEmail navigateToMailiNator() { // Opens Mailinator website in Chrome
        openChromeAndSwitchToWebView();
        return this;
    }

    public void openChromeAndSwitchToWebView() { // Opens Chrome with Mailinator and switches to WebView context
        try {
            // Step 1: Set up deep link arguments to open Chrome with a URL
            Map<String, Object> args = new HashMap<>();
            args.put("intent", "android.intent.action.VIEW"); // Action to view the URL
            args.put("url", "https://www.mailinator.com/"); // Target URL to open
            args.put("package", "com.android.chrome"); // Open with Chrome browser
            args.put("flags", 0x10000000); // FLAG_ACTIVITY_NEW_TASK to start a new task
            args.put("category", "android.intent.category.BROWSABLE"); // Category for browser-compatible intent

            // Step 2: Execute the deep link command to open the URL in Chrome
            driver.executeScript("mobile: deepLink", args);

            // Step 3: Wait for Chrome to load completely (could be improved with explicit wait)
            Thread.sleep(7000);

            // Step 4: Get all available context handles (NATIVE_APP, WEBVIEW, etc.)
            Set<String> contexts = driver.getContextHandles();
            System.out.println("=== Available contexts ===");
            for (String context : contexts) {
                System.out.println(context); // Print each context for debugging
            }

            // Step 5: Try to switch to a WEBVIEW or Chrome context if available
            boolean switched = false;
            for (String context : contexts) {
                if (context.toLowerCase().contains("webview") || context.toLowerCase().contains("chrome")) {
                    driver.context(context); // Switch to the desired web context
                    System.out.println("✅ Switched to context: " + context);
                    switched = true;
                    break;
                }
            }

            // Step 6: Print message if no web context was found
            if (!switched) {
                System.out.println("❌ WebView or Chrome context not found. Staying in NATIVE_APP.");
            }

        } catch (Exception e) {
            // Step 7: Handle any exception that occurs during Chrome launch or context switch
            System.out.println("Error opening Chrome or switching to WebView:");
            e.printStackTrace();
        }
    }


    public void backToBuyMeAPP() { // Switches back to the BuyMe app
        try {
            driver.activateApp("il.co.mintapp.buyme");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =============== Getters and validations =====================

    @Step
    public String getTextInvalidEmailErrorMessage() { // Returns error message text for invalid email
        return getText(errorMessage_WrongEmail);
    }

    @Step
    public String getTextInvalidPasswordErrorMessage() { // Returns error message text for invalid email
        return getText(errorMessage_WorngPassword);
    }
}
