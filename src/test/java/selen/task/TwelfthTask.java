package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

import static selen.driver.DriverFactory.*;
import static selen.function.TestFunction.sleep;

public class TwelfthTask {
    private final String IMAGE_PATH = new File("img/blackDuck.jpg").getAbsolutePath();
    private String shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";
    private String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.";

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myTwelfthTest() {
        loginAdmin();
        sleep(3);
        addProduct("Black Duck", "bd004", "30,00", "Temporary sold out", IMAGE_PATH, "20092020", "20092025", "ACME Corp.", shortDescription, description, "10,00", "20.0000");
        WebElement blackDuck = driver().findElement(By.xpath("//a[text()='Black Duck']"));
        Assertions.assertTrue(blackDuck.isDisplayed());
    }

    public void addProduct(String name, String code, String quantity, String soldOutStatus, String imagePath, String dateValidFrom, String dateValidTo, String manufacturer,
                           String shortDescription, String description, String purchasePrice, String price) {
        driver().findElement(By.xpath("//span[text()='Catalog']")).click();
        driver().findElement(By.xpath("//a[contains(@href,'edit_product')]")).click();
        driver().findElement(By.xpath("//label/input[@value='1']")).click();
        sleep(1);
        driver().findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
        driver().findElement(By.xpath("//input[@name='code']")).sendKeys(code);
        driver().findElement(By.xpath("//input[@data-name='Root']")).click();
        driver().findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver().findElement(By.xpath("//input[@name='quantity']")).clear();
        driver().findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
        driver().findElement(By.xpath("//select[@name='sold_out_status_id']")).click();
        driver().findElement(By.xpath("//*[text()='" + soldOutStatus + "']")).click();
        driver().findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(imagePath);
        driver().findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(dateValidFrom);
        driver().findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(dateValidTo);

        driver().findElement(By.xpath("//a[contains(@href,'information')]")).click();
        driver().findElement(By.xpath("//select[@name='manufacturer_id']")).click();
        driver().findElement(By.xpath("//*[text()='" + manufacturer + "']")).click();
        driver().findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys(shortDescription);
        driver().findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(description);

        driver().findElement(By.xpath("//a[contains(@href,'prices')]")).click();
        driver().findElement(By.xpath("//input[@name='purchase_price']")).clear();
        driver().findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(purchasePrice);
        driver().findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(price);

        driver().findElement(By.xpath("//button[@name='save']")).click();
    }

    public void loginAdmin() {
        driver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver().findElement(By.xpath("//button[@name='login']")).click();
        sleep(1);
    }

    @After
    public void stop() {
        terDawn();
    }
}
