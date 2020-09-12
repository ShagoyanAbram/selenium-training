package selen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class CapabilitiesFireFox {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        // новая схема (начиная с версии 3.0):
//    driver = new FirefoxDriver();
//    // новая схема более явно (этот способ указания опций рекомендуется, начиная с версии 3.3):
//    FirefoxOptions options = new FirefoxOptions().setLegacy(false);
//     driver = new FirefoxDriver(options);
//    // новая схема более явно (при использовании Selenium до версии 3.3):
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, true);
        //driver = new FirefoxDriver(caps);
        //driver = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")), new FirefoxProfile(), caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
//    // старая схема более явно (этот способ указания опций рекомендуется, начиная с версии 3.3):
//    FirefoxOptions options = new FirefoxOptions().setLegacy(true);
//     driver = new FirefoxDriver(options);
//    // старая схема (при использовании Selenium до версии 3.3):
//    DesiredCapabilities caps = new DesiredCapabilities();
//caps.setCapability(FirefoxDriver.MARIONETTE, false);
//     driver = new FirefoxDriver(caps);
    }

    @Test
    public void testCapabilities() {
        driver.get("http://localhost/litecart/admin");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
