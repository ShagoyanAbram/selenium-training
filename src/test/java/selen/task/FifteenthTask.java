package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static selen.driver.DriverFactory.*;
import static selen.function.TestFunction.sleep;

public class FifteenthTask {

//    @Before
//    public void start() {
//        //  System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, 10);
//    }


//    @Before
//    public void start() {
//        createDriver("https://litecart.stqa.ru/en/", "Chrome" /*"Firefox" "InternetExplorer"*/);
//    }

    @Test
    public void myFifteenthTest() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.3.12:4444/wd/hub"), chromeOptions);
        driver.get("http://www.google.com/");
        driver.quit();
    }

//    @After
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }

}
