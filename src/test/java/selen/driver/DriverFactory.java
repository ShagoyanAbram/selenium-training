package selen.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver createDriver(String URL) {
       // ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(/*options*/);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MICROSECONDS);
        wait = new WebDriverWait(driver, 10/*seconds*/);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void terDawn() {
        //driver.close();
        driver.quit();
        driver = null;
    }
}
