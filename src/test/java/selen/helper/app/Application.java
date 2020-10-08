package selen.helper.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selen.helper.pages.CartPage;
import selen.helper.pages.HomePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    private WebDriver driver;
    WebDriverWait wait;

    private CartPage cartPage;
    private HomePage homePage;

    public Application() {
        driver = new ChromeDriver();
        cartPage = new CartPage(driver);
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    public void quit() {
        driver.quit();
    }

    public void openHomePage() {
        homePage.open();
    }

    public void openCart() {
       homePage.cart.click();
    }

    public void emptyCart() {
        List<WebElement> countProduct = driver.findElements(By.xpath("//ul[@class='shortcuts']/li"));
        countProduct.get(0).click();
        int count = countProduct.size();
        for (int i = count; i > 0; i--) {
            List<WebElement> elements = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
            cartPage.remove.click();
            wait.until(ExpectedConditions.stalenessOf(elements.get(1)));
            //elements = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr"));
        }
    }

    public void addProductToCart(int productCount) {
        for (int i = 1; i <= productCount; i++) {
            driver.findElement(By.xpath("//div[@id='box-most-popular']//li[contains(@class,'product')][1]")).click();
            if (isElementNotPresent(By.xpath("//select[@name='options[Size]']"))) {
                driver.findElement(By.xpath("//select[@name='options[Size]']")).click();
                driver.findElement(By.xpath("//*[text()='Small']")).click();
            }
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='quantity' and text()='" + i + "']")));
            driver.findElement(By.xpath("//div[@id='logotype-wrapper']/a")).click();
        }

    }

    public boolean isElementNotPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 1;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
