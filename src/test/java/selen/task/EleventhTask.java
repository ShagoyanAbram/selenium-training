package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.security.SecureRandom;

import static selen.driver.DriverFactory.*;
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
        driver().findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();
        login(email, PASSWORD);
        driver().findElement(By.xpath("//div[@class='content']//a[text()='Logout']")).click();
    }

    public void newUserRegistration(String firstName, String lastName, String address, String postcode, String city, String country, String phone, String password) {
        email = randomMailGeneration(7, "gmail.com");
        String script = "";
        driver().findElement(By.xpath("//a[text()='New customers click here']")).click();
        sleep(1);
        driver().findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
        driver().findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        driver().findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
        driver().findElement(By.xpath("//input[@name='postcode']")).sendKeys(postcode);
        driver().findElement(By.xpath("//span[@class='select2-selection__rendered']")).click();
        driver().findElement(By.xpath("//input[@type='search']")).sendKeys(country);
        driver().findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
        driver().findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver().findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
        driver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver().findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        driver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver().findElement(By.xpath("//button[@name='create_account']")).click();
        //Дублирую код так, как не смог указать зону
        driver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver().findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        driver().findElement(By.xpath("//button[@name='create_account']")).click();
    }

    public void login(String email, String password) {
        driver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver().findElement(By.xpath("//button[@name='login']")).click();
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
