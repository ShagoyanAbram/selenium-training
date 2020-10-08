package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

import static selen.driver.DriverFactory.*;
import static selen.driver.DriverFactory.driver;

public class SeventeenthTask {
    TwelfthTask twelfthTask = new TwelfthTask();

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void mySeventeenthTest() {
        twelfthTask.loginAdmin();
        driver().findElement(By.xpath("//span[text()='Catalog']")).click();
        driver().findElement(By.xpath("//a[text()='Rubber Ducks']")).click();
        checkProductPage();
        List<LogEntry> browserLogs = driver().manage().logs().get("browser").getAll();
        System.out.println(browserLogs.size());
    }

    public void checkProductPage() {
        List<WebElement> elements = driver().findElements(By.xpath("//tr/td/a[contains(text(),'Duck')]"));
        for (int i = 0; i < elements.size(); i++) {
            elements = driver().findElements(By.xpath("//tr/td/a[contains(text(),'Duck')]"));
            elements.get(i).click();
            driver().navigate().back();
        }
    }

    @After
    public void stop() {
        terDawn();
    }
}
