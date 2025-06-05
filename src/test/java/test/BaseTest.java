package test;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import page_Objects.*;
import verifications.Verifications;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    //attributes
    protected AndroidDriver driver;
    public    Actions actions;
    public    WebDriverWait wait;
    protected SoftAssert softAssert;
    protected HomePage homePage;
    protected RegistrationPage_Step1_InsertEmail registrationPage_Step1_InsertEmail;
    protected MyAccountDetails_Page myAccountDetails_page;
    protected SearchResults_Page searchResults_page;
    protected BirthDayGifts_Page birthDayGiftsPage;
    protected Coupon_Page couponPage;
    protected Purchase_GiftCard_Step1_Page purchaseGiftCard_Step1_Page;
    protected Purchase_GiftCard_Step2_Page purchaseGiftCard_Step2_Page;
    protected MailinatorPage mailinatorPage;
    protected ProfileAndInfo_Page profileAndInfo_Page;
    protected Verifications verifications;



    //Calling to page objects
    // Calculator_Page calculatorPage;

    @BeforeMethod
    public void uploadApp() throws MalformedURLException {
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "androidDevice");
        capabilities.setCapability("appPackage", "il.co.mintapp.buyme");
        capabilities.setCapability("appActivity", "");
        capabilities.setCapability("newCommandTimeout", 720);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("unicodeKeyBoard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setUdid("RF8R42F61LA");
        //setUp WebDriver
        // Start a new session with Appium server (localhost:4723) using the given capabilities
        // This connects the test to the Android device and the target app
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        // Set a global wait time of up to 30 seconds when searching for elements
        // If an element is not found immediately, Appium will keep trying until timeout
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        softAssert = new SoftAssert(); ///Important Reinitialize SoftAssert in @BeforeMethod so that each test starts with clean softassertions data
        homePage = new HomePage(driver);
        registrationPage_Step1_InsertEmail = new RegistrationPage_Step1_InsertEmail(driver);
        myAccountDetails_page = new MyAccountDetails_Page(driver);
        searchResults_page = new SearchResults_Page(driver);
        birthDayGiftsPage = new BirthDayGifts_Page(driver);
        couponPage = new Coupon_Page(driver);
        purchaseGiftCard_Step1_Page = new Purchase_GiftCard_Step1_Page(driver);
        purchaseGiftCard_Step2_Page = new Purchase_GiftCard_Step2_Page(driver);
        myAccountDetails_page=new MyAccountDetails_Page(driver);
        mailinatorPage=new MailinatorPage(driver);
        profileAndInfo_Page=new ProfileAndInfo_Page(driver);
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



    public void returnToChromeWithoutNewSession() {
        driver.activateApp("com.android.chrome");
    }



//    @AfterMethod
//    public void quit() {
//        if (driver != null) {
//            System.out.println("Quitting driver...");
//            driver.quit();
//            System.out.println("Driver quit successfully.");
//        }
//    }

}


