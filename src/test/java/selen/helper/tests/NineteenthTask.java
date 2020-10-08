package selen.helper.tests;

import org.junit.Test;

import static selen.helper.app.Application.sleep;

public class NineteenthTask extends TestBase {

    @Test
    public void myNineteenthTest() {
        app.openHomePage();
        app.addProductToCart(3);
        sleep(1);
        app.openCart();
        sleep(1);
        app.emptyCart();

    }

}
