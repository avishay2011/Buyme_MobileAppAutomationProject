package utils;


import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DropDownValues_And_ElementsMap {

    public static List<String> getExpectedAmounts() {
        return Arrays.asList(
                "הכל",
                "עד ₪99",
                "100–₪199",
                "200–₪299",
                "300–₪499",
                "500–₪749",
                "750–₪1499"
        );
    }

    public static List<String> getExpectedRegions() {
        return Arrays.asList(
                "כל האזורים",
                "מרכז",
                "ת\"א והסביבה",
                "צפון",
                "דרום",
                "ירושלים",
                "חיפה"
        );
    }
}