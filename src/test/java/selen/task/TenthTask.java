package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static selen.driver.DriverFactory.*;


public class TenthTask {

    @Before
    public void start() {
        createDriver("http://localhost/litecart/en/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myTenthTest() {
        List<String> listOne = new ArrayList<>(checkProductValues("//h3[text()='Campaigns']//parent::div//div[@class='name']",
                "//h3[text() ='Campaigns']//parent::div//s[@class='regular-price']", "(//h3[text()='Campaigns']//parent::div)//strong"));

        driver().findElement(By.xpath("//h3[text()='Campaigns']//parent::div//a[@class='link']")).click();

        List<String> listTwo = new ArrayList<>(checkProductValues("//h1[@itemprop='name']", "//s[@class='regular-price']",
                "//*[@class='campaign-price']"));

        Assertions.assertEquals(listOne.size(), listTwo.size());

        for (int i = 0; i < listOne.size() - 2; i++) {
            Assertions.assertEquals(listOne.get(i), listTwo.get(i));
        }

        Assertions.assertEquals("true", listOne.get(3));
        Assertions.assertEquals("true", listOne.get(4));
        Assertions.assertEquals("true", listOne.get(5));
        Assertions.assertEquals("true", listOne.get(6));

        Assertions.assertTrue(Double.parseDouble(listOne.get(7)) < Double.parseDouble(listOne.get(8)));
        Assertions.assertTrue(Double.parseDouble(listTwo.get(7)) < Double.parseDouble(listTwo.get(8)));
    }

    public List<String> checkProductValues(String productNameXpath, String regularPriceXpath, String strongPriceXpath) {
        List<String> listA = new ArrayList<>();

        //а) на главной странице и на странице товара совпадает текст названия товара
        WebElement productName = driver().findElement(By.xpath(productNameXpath));

        //б) на главной странице и на странице товара совпадают цены обычная
        WebElement regularPrice = driver().findElement(By.xpath(regularPriceXpath));

        //б) на главной странице и на странице товара совпадают цены акционная
        WebElement strongPrice = driver().findElement(By.xpath(strongPriceXpath));

        listA.add(productName.getText());
        listA.add(regularPrice.getText());
        listA.add(strongPrice.getText());
        listA.add(String.valueOf(regularPrice.getCssValue("text-decoration").contains("line-through")));
        listA.add(String.valueOf(checkGrayColor(regularPrice)));
        listA.add(String.valueOf(boldTextCheck(strongPrice)));
        listA.add(String.valueOf(checkRedColor(strongPrice)));
        listA.add(regularPrice.getCssValue("font-size").replace("px", ""));
        listA.add(strongPrice.getCssValue("font-size").replace("px", ""));

        return listA;
    }

    public boolean checkGrayColor(WebElement webElement) {
        String color = webElement.getCssValue("color");
        color = color.replaceAll("[^\\d,]", "");
//        color = color.substring(color.indexOf('(') + 1, color.indexOf(')'));
        String[] rgba = (color.split(","));
        return rgba[0].equals(rgba[1]) && rgba[1].equals(rgba[2]);
    }

    public boolean checkRedColor(WebElement webElement) {
        String color = webElement.getCssValue("color");
        color = color.replaceAll("[^\\d,]", "");
        String[] rgba = (color.split(","));
        return rgba[1].equals("0") && rgba[2].equals("0");
    }

    public boolean boldTextCheck(WebElement webElement) {
        int boldValue = Integer.parseInt(webElement.getCssValue("font-weight"));
        return boldValue >= 700;
    }

    @After
    public void stop() {
        terDawn();
    }

}
