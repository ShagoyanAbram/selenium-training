package selen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;
import static selen.driver.DriverFactory.*;

public class SeventhTask {

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void mySeventhTest() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//button[@name='login']")).click();
        sleep(5);
        clicker("//ul[@id='box-apps-menu']/li", "//ul[@id='box-apps-menu']/li/ul/li", "//h1");
    }

    public void clickElemMenu(String elemName) {
        getDriver().findElement(By.xpath("//span[text()=\'" + elemName + "\']")).click();
    }

    public void checkElement(String xpath) {
        WebElement element = getDriver().findElement(By.xpath(xpath));
        Assertions.assertTrue(element.isDisplayed());
    }

    public void clicker(String xpathElemClickA, String xpathElemClickB, String xpathCheckElement) {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpathElemClickA));
        for (int i = 0; i < elements.size(); i++) {
            List<WebElement> element = getDriver().findElements(By.xpath(xpathElemClickA));
            element.get(i).click();
            checkElement(xpathCheckElement);
            List<WebElement> nestedElements = getDriver().findElements(By.xpath(xpathElemClickB));
            for (int j = 0; j < nestedElements.size(); j++) {
                List<WebElement> nestedElement = getDriver().findElements(By.xpath(xpathElemClickB));
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
