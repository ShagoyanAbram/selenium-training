package selen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selen.driver.DriverFactory;

import java.util.List;

import static selen.driver.DriverFactory.createDriver;
import static selen.driver.DriverFactory.terDawn;

public class EighthTask {
    @Before
    public void start() {
        createDriver("http://localhost/litecart/");
    }

    @Test
    public void mySeventhTest() {
        checkingStickerProduct("(//div[@class='content']//ul[contains(@class,'listing')])");
    }

    public void checkingStickerProduct(String productXpath) {
        List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(productXpath));
        for (int i = 0; i < elements.size(); i++) {
            int positionI = i + 1;
            List<WebElement> productListInBlock = DriverFactory.getDriver().findElements(By.xpath(productXpath.concat("[" + positionI + "]//li")));
            for (int j = 0; j < productListInBlock.size(); j++) {
                int positionJ = j + 1;
                String stickerXpath = productXpath.concat("[" + positionI + "]//li[" + positionJ + "]").concat("//div[contains(@class,'sticker')]");
                List<WebElement> listProductSticker = DriverFactory.getDriver().findElements(By.xpath(stickerXpath));
                Assertions.assertTrue(listProductSticker.get(0).isDisplayed());
                Assertions.assertEquals(1, listProductSticker.size());
            }
        }
    }

    @After
    public void stop() {
        terDawn();
    }
}

//    String stickerXpath = productXpath
//            .concat("[" + positionI + "]//li[" + positionJ + "]")
//            .concat("//div[contains(@class,'sticker')]");
//    WebElement product = DriverFactory.getDriver().findElement(By.xpath(stickerXpath));
//    List<WebElement> listSticker = DriverFactory.getDriver().findElements(By.xpath(productXpath.concat(stickerXpath)));