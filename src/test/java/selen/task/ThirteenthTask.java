package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static selen.driver.DriverFactory.*;
import static selen.function.TestFunction.sleep;

public class ThirteenthTask {
//    @FindBy(xpath = "//div[@id='box-most-popular']//li[contains(@class,'product')][1]")
//    private WebElement product;



    @Before
    public void start() {
        createDriver("https://litecart.stqa.ru/en/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myThirteenthTest() {
        addProductToCart(3);
        sleep(1);
        openCart();
        sleep(1);
        emptyCart();

    }

    public void addProductToCart(int productCount) {
        for (int i = 1; i <= productCount; i++) {
            driver().findElement(By.xpath("//div[@id='box-most-popular']//li[contains(@class,'product')][1]")).click();
            if (isElementNotPresent(By.xpath("//select[@name='options[Size]']"))) {
                driver().findElement(By.xpath("//select[@name='options[Size]']")).click();
                driver().findElement(By.xpath("//*[text()='Small']")).click();
            }
            driver().findElement(By.xpath("//button[@name='add_cart_product']")).click();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='quantity' and text()='" + i + "']")));
            driver().findElement(By.xpath("//div[@id='logotype-wrapper']/a")).click();
        }

    }

    public void emptyCart() {
        List<WebElement> countProduct = driver().findElements(By.xpath("//ul[@class='shortcuts']/li"));
        countProduct.get(0).click();
        int count = countProduct.size();
        for (int i = count; i > 0; i--) {
            List<WebElement> elements = driver().findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
            driver().findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            getWait().until(ExpectedConditions.stalenessOf(elements.get(1)));
            elements = driver().findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
        }
    }

    public void openCart() {
        driver().findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }

    public boolean isElementNotPresent(By locator) {
        try {
            driver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return driver().findElements(locator).size() == 1;
        } finally {
            driver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @After
    public void stop() {
        terDawn();
    }

}
