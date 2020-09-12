package selen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selen.driver.DriverFactory;

import java.util.List;

import static java.lang.Thread.sleep;
import static selen.driver.DriverFactory.createDriver;
import static selen.driver.DriverFactory.terDawn;

public class SeventhTask {

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin");
    }

    @Test
    public void mySeventhTest() throws InterruptedException {
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        DriverFactory.getDriver().findElement(By.xpath("//button[@name='login']")).click();
        sleep(5);
        clicker("//ul[@id='box-apps-menu']/li", "//ul[@id='box-apps-menu']/li/ul/li", "//h1");
//        clickElemMenu("Appearence");
//        checkElement("//h1");
//        clickElemMenu("Catalog");
//        checkElement("//h1");
//        clickElemMenu("Countries");
//        checkElement("//h1");
//        clickElemMenu("Currencies");
//        checkElement("//h1");
//        clickElemMenu("Customers");
//        checkElement("//h1");
//        clickElemMenu("Geo Zones");
//        checkElement("//h1");
//        clickElemMenu("Languages");
//        checkElement("//h1");
//        clickElemMenu("Modules");
//        checkElement("//h1");
//        clickElemMenu("Orders");
//        checkElement("//h1");
//        clickElemMenu("Pages");
//        checkElement("//h1");
//        clickElemMenu("Reports");
//        checkElement("//h1");
//        clickElemMenu("Settings");
//        checkElement("//h1");
//        clickElemMenu("Slides");
//        checkElement("//h1");
//        clickElemMenu("Tax");
//        checkElement("//h1");
//        clickElemMenu("Translations");
//        checkElement("//h1");
//        clickElemMenu("Users");
//        checkElement("//h1");
//        clickElemMenu("vQmods");
//        checkElement("//h1");
    }

    public void clickElemMenu(String elemName) {
        DriverFactory.getDriver().findElement(By.xpath("//span[text()=\'" + elemName + "\']")).click();
    }

    public void checkElement(String xpath) {
        WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
        Assertions.assertTrue(element.isDisplayed());
    }

    public void clicker(String xpathElemClickA, String xpathElemClickB, String xpathCheckElement) {
        List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(xpathElemClickA));
        for (int i = 0; i < elements.size(); i++) {
            List<WebElement> element = DriverFactory.getDriver().findElements(By.xpath(xpathElemClickA));
            element.get(i).click();
            checkElement(xpathCheckElement);
            List<WebElement> nestedElements = DriverFactory.getDriver().findElements(By.xpath(xpathElemClickB));
            for (int j = 0; j < nestedElements.size(); j++) {
                List<WebElement> nestedElement = DriverFactory.getDriver().findElements(By.xpath(xpathElemClickB));
                nestedElement.get(j).click();
                checkElement(xpathCheckElement);
            }
        }
    }

    @After
    public void stop() {
        terDawn();
    }

}
