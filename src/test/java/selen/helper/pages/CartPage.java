package selen.helper.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[@name='remove_cart_item']")
    public WebElement remove;

    @FindBy(xpath = "//div[@id='box-most-popular']//li[contains(@class,'product')][1]")
    public WebElement product;
}
