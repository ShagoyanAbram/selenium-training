package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selen.driver.DriverFactory;
import selen.function.TestFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
;

import static selen.driver.DriverFactory.createDriver;
import static selen.driver.DriverFactory.terDawn;

public class NinthTask {

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin/?app=countries&doc=countries");
    }

    @Test
    public void myNinthTest() {
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        DriverFactory.getDriver().findElement(By.xpath("//button[@name='login']")).click();
        checkCountriesSort("//table[@class='dataTable']//tr[@class='row']//td[5]/a","text");
        checkZoneSort("//table[@class='dataTable']//tr[@class='row']//td[6]", "//table[@class='dataTable']//tr//td[3]//input[@type='hidden']");
    }

    public void checkCountriesSort(String xpathListCounty, String attribute) {
        List<WebElement> listElemCounty = DriverFactory.getDriver().findElements(By.xpath(xpathListCounty));
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
        List<WebElement> listCountyToZone = DriverFactory.getDriver().findElements(By.xpath(xpathCountZone));
        List<String> listID = new ArrayList<>();
        for (int i = 0; i < listCountyToZone.size(); i++) {
            if (!listCountyToZone.get(i).getText().equals("0"))
                listID.add(String.valueOf(i + 1));
        }
        for (String s : listID) {
            WebElement country = DriverFactory.getDriver().findElement(By.xpath("(//table[@class='dataTable']//tr[@class='row']//td[5]//a)[" + s + "]"));
            country.click();
            checkCountriesSort(xpathListZone,"value");
            DriverFactory.getDriver().navigate().back();
        }
    }

    @After
    public void stop() {
        terDawn();
    }

}
