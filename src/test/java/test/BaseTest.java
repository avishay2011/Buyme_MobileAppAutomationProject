package test;

import flows.CheckGiftCardBalance_Flows;
import flows.FilterAndSearchGifts_Flows;
import flows.RegistrationAndLogin_Flows;
import flows.SelectGift_Flows;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import page_Objects.*;
import verifications.Verifications;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static utils.Utilities.readFromThisFile;

public class BaseTest {

    //attributes
    protected AndroidDriver driver;
    public    Actions actions;
    public    WebDriverWait wait;
    protected SoftAssert softAssert;
    protected HomePage homePage;
    protected RegistrationPage_Step1_InsertEmail registrationPage_Step1_InsertEmail;
    protected SearchResults_Page searchResults_page;
    protected BirthDayGifts_Page birthDayGiftsPage;
    protected Coupon_Page couponPage;
    protected Purchase_GiftCard_Step1_Page purchaseGiftCard_Step1_Page;
    protected Purchase_GiftCard_Step2_Page purchaseGiftCard_Step2_Page;
    protected ProfileAndInfo_Page profileAndInfo_Page;
    protected AssigningGifts_Page assigningGifts_page;
    protected RegistrationAndLogin_Flows registrationAndLoginFlows;
    protected FilterAndSearchGifts_Flows filterAndSearchGiftsFlows;
    protected SelectGift_Flows selectGiftFlows;
    protected CheckGiftCardBalance_Flows checkGiftCardBalanceFlows;
    protected Verifications verifications;



    //Calling to page objects
    // Calculator_Page calculatorPage;

    @BeforeMethod
    public void uploadApp() throws IOException, ParserConfigurationException, SAXException {
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidDevice");
        capabilities.setCapability("appPackage", readFromThisFile("appPackage"));
        capabilities.setCapability("appActivity", "");
        capabilities.setCapability("newCommandTimeout", 720);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("unicodeKeyBoard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setUdid(readFromThisFile("Udid"));
        //setUp WebDriver
        // Start a new session with Appium server (localhost:4723) using the given capabilities
        // This connects the test to the Android device and the target app
        driver = new AndroidDriver(new URL(readFromThisFile("androidURL")), capabilities);
        // Set a global wait time of up to 30 seconds when searching for elements
        // If an element is not found immediately, Appium will keep trying until timeout
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        softAssert = new SoftAssert(); ///Important Reinitialize SoftAssert in @BeforeMethod so that each test starts with clean softassertions data
        homePage = new HomePage(driver);
        registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        searchResults_page = new SearchResults_Page(driver);
        birthDayGiftsPage = new BirthDayGifts_Page(driver);
        couponPage = new Coupon_Page(driver);
        purchaseGiftCard_Step1_Page = new Purchase_GiftCard_Step1_Page(driver);
        purchaseGiftCard_Step2_Page = new Purchase_GiftCard_Step2_Page(driver);
        profileAndInfo_Page=new ProfileAndInfo_Page(driver);
        assigningGifts_page=new AssigningGifts_Page(driver);
        registrationAndLoginFlows=new RegistrationAndLogin_Flows(driver);
        filterAndSearchGiftsFlows=new FilterAndSearchGifts_Flows(driver);
        selectGiftFlows=new SelectGift_Flows(driver);
        checkGiftCardBalanceFlows=new CheckGiftCardBalance_Flows(driver);
        verifications=new Verifications(driver,softAssert);


    }


    public String getCurrentTabHandle() {
        return driver.getWindowHandle();
    }

    public void switchToTab(String handle) {
        driver.switchTo().window(handle);
    }

    public void closeCurrentTabAndSwitchTo(String handle) {
        driver.close();
        switchToTab(handle);
    }


    public void openChromeAndSwitchToWebView() {
        try {
            // 1. Open Chrome with the URL using an intent
            Map<String, Object> args = new HashMap<>();
            args.put("intent", "android.intent.action.VIEW");
            args.put("url", readFromThisFile("mailiNator_Url"));
            args.put("package", "com.android.chrome");
            args.put("flags", 0x10000000); // FLAG_ACTIVITY_NEW_TASK
            args.put("category", "android.intent.category.BROWSABLE");

            // Perform the deepLink
            driver.executeScript("mobile: deepLink", args);

            // 2. Wait a few seconds for Chrome to load
            Thread.sleep(7000); //

            // 3. Print all available contexts
            Set<String> contexts = driver.getContextHandles();
            System.out.println("=== Available contexts ===");
            for (String context : contexts) {
                System.out.println(context);
            }

            // 4. Switch to a WEBVIEW or Chrome context if available
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
                System.out.println("❌ No WebView or Chrome context found. Staying in NATIVE_APP.");
            }

        } catch (Exception e) {
            System.out.println("Error while opening Chrome or switching to WebView:");
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
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing the application...");
            driver.quit(); // Close app and appium session
        }
    }
}





