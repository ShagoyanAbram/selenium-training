package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import selen.driver.DriverFactory;

import java.security.SecureRandom;

import static selen.driver.DriverFactory.createDriver;
import static selen.driver.DriverFactory.terDawn;
import static selen.function.TestFunction.sleep;

public class EleventhTask {
    private static String email;
    private static final String PASSWORD = "Test12345";

    @Before
    public void start() {
        createDriver("http://localhost/litecart/en/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myEleventhTest() {
        newUserRegistration("Ivan", "Ivanov", "1255 22ND ST. NW", "12345",
                "Washington", "United States", "2345678900", PASSWORD);
        DriverFactory.getDriver().findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();
        login(email, PASSWORD);
        DriverFactory.getDriver().findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();
    }

    public void newUserRegistration(String firstName, String lastName, String address, String postcode, String city, String country, String phone, String password) {
        email = randomMailGeneration(7, "gmail.com");
        String script = "";
        DriverFactory.getDriver().findElement(By.xpath("//a[text()='New customers click here']")).click();
        sleep(1);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
        DriverFactory.getDriver().findElement(By.xpath("//span[@class='select2-selection__rendered']")).click();
        DriverFactory.getDriver().findElement(By.xpath("//input[@type='search']")).sendKeys(country);
        DriverFactory.getDriver().findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        DriverFactory.getDriver().findElement(By.xpath("//button[@name='create_account']")).click();
        //Дублирую код так, как не смог указать зону
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        DriverFactory.getDriver().findElement(By.xpath("//button[@name='create_account']")).click();
    }

    public void login(String email, String password) {
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        DriverFactory.getDriver().findElement(By.xpath("//button[@name='login']")).click();
    }

    public String randomMailGeneration(int length, String mailType) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString().concat("@").concat(mailType);
    }

    @After
    public void stop() {
        terDawn();
    }
}
