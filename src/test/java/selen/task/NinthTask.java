package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selen.function.TestFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static selen.driver.DriverFactory.*;

public class NinthTask {
    TestFunction testFunction = new TestFunction();

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin/?app=countries&doc=countries", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myNinthTest() {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//button[@name='login']")).click();
        testFunction.sleep(1);
        checkCountriesSort("//table[@class='dataTable']//tr[@class='row']//td[5]/a", "text");
        checkZoneSort("//table[@class='dataTable']//tr[@class='row']//td[6]", "//table[@class='dataTable']//tr//td[3]//input[@type='hidden']");
    }

    @Test
    public void testSortZoneToCountry() {
        getDriver().get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//button[@name='login']")).click();
        testFunction.sleep(1);
        checkGeoZoneSort("//tr[@class='row']/td[3]/a", "//select[contains(@name,'zone_code')]//option[@selected='selected']");
    }

    public void checkCountriesSort(String xpathListCounty, String attribute) {
        List<WebElement> listElemCounty = getDriver().findElements(By.xpath(xpathListCounty));
        List<String> listCounty = new ArrayList<>();
        for (WebElement country : listElemCounty) {
            if (attribute.equals("text")) {
                listCounty.add(country.getText());
            } else {
                listCounty.add(country.getAttribute("value"));
            }

        }
        Assertions.assertEquals(listCounty.stream().sorted().collect(Collectors.toList()), listCounty);
    }

    public void checkZoneSort(String xpathCountZone, String xpathListZone) {
        List<WebElement> listCountyToZone = getDriver().findElements(By.xpath(xpathCountZone));
        List<String> listID = new ArrayList<>();
        for (int i = 0; i < listCountyToZone.size(); i++) {
            if (!listCountyToZone.get(i).getText().equals("0"))
                listID.add(String.valueOf(i + 1));
        }
        for (String s : listID) {
            WebElement country = getDriver().findElement(By.xpath("(//table[@class='dataTable']//tr[@class='row']//td[5]//a)[" + s + "]"));
            country.click();
            checkCountriesSort(xpathListZone, "value");
            getDriver().navigate().back();
        }
    }

    public void checkGeoZoneSort(String countryNameXpath, String zoneNameXpath) {
        List<WebElement> listCountryName = getDriver().findElements(By.xpath(countryNameXpath));
        List<String> listZoneName = new ArrayList<>();
        for (int i = 0; i < listCountryName.size(); i++) {
            listCountryName = getDriver().findElements(By.xpath(countryNameXpath));
            listCountryName.get(i).click();
            List<WebElement> listElemZoneName = getDriver().findElements(By.xpath(zoneNameXpath));
            for (int j = 0; j < listElemZoneName.size(); j++) {
                listElemZoneName = getDriver().findElements(By.xpath(zoneNameXpath));
                listZoneName.add(listElemZoneName.get(i).getText());
            }
            Assertions.assertEquals(listZoneName.stream().sorted().collect(Collectors.toList()), listZoneName);
            listZoneName.clear();
            getDriver().navigate().back();
        }
    }

    @After
    public void stop() {
        terDawn();
    }

}
