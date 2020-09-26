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
        WebElement blackDuck = getDriver().findElement(By.xpath("//a[text()='Black Duck']"));
        Assertions.assertTrue(blackDuck.isDisplayed());
    }

    public void addProduct(String name, String code, String quantity, String soldOutStatus, String imagePath, String dateValidFrom, String dateValidTo, String manufacturer,
                           String shortDescription, String description, String purchasePrice, String price) {
        getDriver().findElement(By.xpath("//span[text()='Catalog']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'edit_product')]")).click();
        getDriver().findElement(By.xpath("//label/input[@value='1']")).click();
        sleep(1);
        getDriver().findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@name='code']")).sendKeys(code);
        getDriver().findElement(By.xpath("//input[@data-name='Root']")).click();
        getDriver().findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        getDriver().findElement(By.xpath("//input[@name='quantity']")).clear();
        getDriver().findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
        getDriver().findElement(By.xpath("//select[@name='sold_out_status_id']")).click();
        getDriver().findElement(By.xpath("//*[text()='" + soldOutStatus + "']")).click();
        getDriver().findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(imagePath);
        getDriver().findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys(dateValidFrom);
        getDriver().findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys(dateValidTo);

        getDriver().findElement(By.xpath("//a[contains(@href,'information')]")).click();
        getDriver().findElement(By.xpath("//select[@name='manufacturer_id']")).click();
        getDriver().findElement(By.xpath("//*[text()='" + manufacturer + "']")).click();
        getDriver().findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys(shortDescription);
        getDriver().findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(description);

        getDriver().findElement(By.xpath("//a[contains(@href,'prices')]")).click();
        getDriver().findElement(By.xpath("//input[@name='purchase_price']")).clear();
        getDriver().findElement(By.xpath("//input[@name='purchase_price']")).sendKeys(purchasePrice);
        getDriver().findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(price);

        getDriver().findElement(By.xpath("//button[@name='save']")).click();
    }

    public void loginAdmin() {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        getDriver().findElement(By.xpath("//button[@name='login']")).click();
        sleep(1);
    }

    @After
    public void stop() {
        terDawn();
    }
}
