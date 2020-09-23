package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selen.driver.DriverFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static selen.driver.DriverFactory.*;
import static selen.function.TestFunction.sleep;

public class ThirteenthTask {

    @Before
    public void start() {
        createDriver("https://litecart.stqa.ru/en/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myThirteenthTest() {
        for (int i = 1; i < 4; i++) {
            getDriver().findElement(By.xpath("//div[@id='box-most-popular']//li[contains(@class,'product')][1]")).click();
            if (isElementNotPresent(By.xpath("//select[@name='options[Size]']"))) {
                getDriver().findElement(By.xpath("//select[@name='options[Size]']")).click();
                getDriver().findElement(By.xpath("//*[text()='Small']")).click();
            }
            getDriver().findElement(By.xpath("//button[@name='add_cart_product']")).click();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='quantity' and text()='" + i + "']")));
            getDriver().findElement(By.xpath("//div[@id='logotype-wrapper']/a")).click();
        }
        sleep(1);
        getDriver().findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
        sleep(1);
        List<WebElement> countProduct = getDriver().findElements(By.xpath("//ul[@class='shortcuts']/li"));
        countProduct.get(0).click();
        int count = countProduct.size();
        for (int i = count; i > 0; i--) {
            List<WebElement> elements = getDriver().findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
            getDriver().findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            getWait().until(ExpectedConditions.stalenessOf(elements.get(1)));
            elements = getDriver().findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));

        }

    }

    public boolean isElementNotPresent(By locator) {
        try {
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return DriverFactory.getDriver().findElements(locator).size() == 1;
        } finally {
            DriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @After
    public void stop() {
        terDawn();
    }

}
