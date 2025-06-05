package page_Objects;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MyAccountDetails_Page extends BasePage{
    public MyAccountDetails_Page(AndroidDriver driver) {
        super(driver);
    }

    //elements
    private By firstName_Field=By.id("name");
    private By cellPhone_Field=By.id("phoneNumber");

    //functions


    @Step
    public String getFirstNameInputValue(){
        return getInputFieldValue(firstName_Field);
    }


    @Step
    public String getCellPhoneInputValue(){
        return getInputFieldValue(cellPhone_Field);
    }


}
