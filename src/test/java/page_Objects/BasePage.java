package page_Objects;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static utils.Utilities.takeScreenShot;

public class BasePage {
    protected static AndroidDriver driver;
    protected static WebDriverWait wait;
    protected Actions action;
    public static final String configPath ="C:/Users/avish/IdeaProjects/MobileTest/ReadingFromFile/config.xml";

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.action= new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    ///Action methods on elements : click, send keys and etc

    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }



    public void sendKeys(By elementLocation, String text) {
        WebElement el = driver.findElement(elementLocation);
        el.click();
        el.clear(); // אופציונלי
        el.sendKeys(text);
    }


    public void swipeLeftOnElement(By elementLocation) {
        // מקבל את מיקום וגודל האלמנט
        Point location = driver.findElement(elementLocation).getLocation();
        Dimension size = driver.findElement(elementLocation).getSize();

        int startX = location.getX() + (int) (size.width * 0.8);
        int endX = location.getX() + (int) (size.width * 0.2);
        int y = location.getY() + (size.height / 2);  // באמצע גובה האלמנט

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }


    public void scrollToVisibleTextAndClick(String visibleText,By elementLocation) {
        int maxSwipes = 5;
        boolean found = false;

        while (maxSwipes > 0) {
            try {
                // חיפוש לפי הטקסט הגלוי על האלמנט
                WebElement element1 = driver.findElement(By.xpath("//*[contains(@text, '" + visibleText + "')]"));
                     driver.findElement(elementLocation).click();
                found = true;
                break;
            } catch (Exception e) {
                swipeLeftOnElement(elementLocation);
                maxSwipes--;
            }
        }

        if (!found) {
            throw new RuntimeException("האלמנט עם הטקסט '" + visibleText + "' לא נמצא לאחר גלילה");
        }
    }




    public void hoverToElement(By elementLocation) { /// this function have written for fields that user need to hover on it before the click fuction is possible
        try {
            WebElement element=waitVisibility(elementLocation);
            action.moveToElement(element).build().perform();
            Allure.step("Hovering to element " + elementLocation.toString());
        }
        catch (NoSuchElementException | TimeoutException error){
            Allure.step("Hovering to element failed: : Element not found or timeout" + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            throw  error;
        }
        catch (Exception error) {
            Allure.step("Send keys failed: Unexpected error - " + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            throw error;
        }
    }

    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.4);
        int x = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }


    public void scrollDown(int maxScrolls) {
        for (int i = 0; i <= maxScrolls; i++) {
            swipeDown();
            }
        }



    public List<WebElement> getElementsFromListLocation(By locator) {
        waitVisibility(locator);
        return driver.findElements(locator);
    }

    /// Get  text,css values and attributes

    public String getText(By elementLocation) {
        try {
            WebElement element=waitVisibility(elementLocation);
            String text = element.getText();
            Allure.addAttachment("Text from element", text);
            return text;
        } catch (Exception error) {
            Allure.step("❌ Failed to get text from element: " + elementLocation + " → " + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            return "Web element not found";
        }
    }


    public String getColor(By elementLocation) {
        try {
            WebElement element=waitVisibility(elementLocation);
            String color = element.getCssValue("color");  //return rgba that is the format that define color in css
            Allure.addAttachment("Element color is :",color);
            return color;
        } catch (Exception error) {
            Allure.step("❌ Failed to get color from element: " + elementLocation + "   →   " + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            return "Web element not found";
        }
    }


    public String getInputFieldValue(By elementLocation){  //Get the value displayed in the input field after user input
        try {
            WebElement element=waitVisibility(elementLocation);
            String text = element.getAttribute("value");
            Allure.addAttachment("Text from element", text);
            return text;
        } catch (Exception error) {
            Allure.step("❌ Failed to get input field text from : " + elementLocation + " → " + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            return "";
        }
    }

    ////Validations
    //Not verification . I use it just for checking verification methods in page objects
    @Step("Check that the requested page is loaded without any errors or mistakes") ///General method that can be used for any page for checking that one of the major error not appears
    public boolean pageLoadedWithNoErrors() {
        wait.until( webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        boolean noErrors=true;
        String pageSource = driver.getPageSource().toLowerCase();
        String[] criticalErrors = {
                "internal server error",
                "page not found",
                "an unexpected error occurred",
                "שגיאה בטעינת הדף"
        };

        for (String error : criticalErrors) {
            if(pageSource.contains(error)) {
                System.out.println("הודעת שגיאה התגלתה בדף: " + error);
                noErrors=false;
            }

        }
        return noErrors;
    }


    public boolean isElementDisplayed(By elementLocation){
        try {
            waitVisibility(elementLocation);
            Allure.step("Element is displayed " + elementLocation.toString());
            return true;
        }
        catch(Exception error){
            Allure.step("❌ Failed to get element visibility: " + elementLocation.toString() + " → " + error.getMessage(), Status.FAILED);
            takeScreenShot(driver);
            return false;
        }
    }

    @Step("Verify that error message is displayed *below* the input field") //להעביר ל BASE
    //////lower y value means that the element located actually higher on the screen
    public boolean isElement1LocatedBelowElement2(By upperElement,By lowerElement) {
        try {
            Point upperElementPoint = waitVisibility(upperElement).getLocation();
            Point lowerElementPoint = waitVisibility(lowerElement).getLocation();
            if (upperElementPoint.getY() < lowerElementPoint.getY()) {
                Allure.step("✔ Element " + lowerElement + " is correctly displayed below " + upperElement );
                return true;
            } else {
                Allure.step("❌ Element " + lowerElement + " is NOT displayed below " + upperElement, Status.FAILED );
                takeScreenShot(driver);
                return false;
            }
        } catch (Exception error) {
            Allure.step("❌ Exception during element position check: " + error.getMessage(), Status.FAILED );
            takeScreenShot(driver);
            return false;
        }
    }

    @Step
    public boolean areDropdownValuesMatchExpected(List<String> expectedValues,By actualValues) {
        List<WebElement> actualList = getElementsFromListLocation(actualValues);
        String actualText;
        String expectedText;
        for (int i = 0; i < expectedValues.size(); i++) {
            actualText = actualList.get(i).getText().trim();
            expectedText = expectedValues.get(i).trim();
            System.out.println(actualText);
            if (!expectedText.equalsIgnoreCase(actualText)) {
                Allure.step("Mismatch at index : " +i);
                Allure.step("Expected : " +expectedText);
                Allure.step("Actual : "   +actualText );
                return false;
            }
        }
        return true;
    }

    protected void highlightElementForShortTime(WebElement element) {  ///Method for make background field with bright yellow and the text bold
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color: #FFF68F; " +  // Banana Yellow
                "border: 1px solid red; " +
                "font-weight: bold; " +
                "color: initial; " +
                originalStyle;

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "var tmpArguments = arguments;" +
                        "setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');}, 0);",
                element
        );

        js.executeScript(
                "var tmpArguments = arguments;" +
                        "setTimeout(function () {tmpArguments[0].setAttribute('style', '" + originalStyle + "');}, 600);",
                element
        );
    }

    /// Wait method
    public WebElement waitVisibility(By elementLocation) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocation));
    }

}
