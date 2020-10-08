package selen.task;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestProxy {

    //    public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();
//    public EventFiringWebDriver driver;
//    public WebDriverWait wait;
    public BrowserMobProxy proxy;

    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }
//
//        @Override
//        public void onException(Throwable throwable, WebDriver driver) {
//            System.out.println(throwable);
//            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
////            try {
////                Files.copy();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//            System.out.println(screen);
//        }
    }

//    @Before
//    public void start() {
//        if (tlDriver.get() != null) {
//            driver = tlDriver.get();
//            wait = new WebDriverWait(driver, 10);
//            return;
//        }
//    }


    public void myProxy() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", proxy);
        WebDriver driver = new ChromeDriver(caps);

//        proxy = new BrowserMobProxyServer();
//        proxy.start(0);
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//        driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
//        driver.register(new MyListener());
//        tlDriver.set(driver);
//        wait = new WebDriverWait(driver, 10);
//
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> {
//                    driver.quit();
//                    driver = null;
//                }));
    }

    @Test
    public void getBrowserLogs() {
//        proxy = new BrowserMobProxyServer();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", proxy);
        WebDriver driver = new ChromeDriver(caps);
        //proxy.newHar();
        driver.navigate().to("http://selenium2.ru");
       // Har har = proxy.endHar();
       // har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }

    @After
    public void stop() {

    }
}
