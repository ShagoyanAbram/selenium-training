package selen.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

import static selen.driver.DriverFactory.*;
import static selen.function.TestFunction.sleep;

public class FourteenthTask {
    TwelfthTask twelfthTask = new TwelfthTask();

    @Before
    public void start() {
        createDriver("http://localhost/litecart/admin/", "Chrome" /*"Firefox" "InternetExplorer"*/);
    }

    @Test
    public void myFourteenthTest() {
        twelfthTask.loginAdmin();
        getDriver().findElement(By.xpath("//span[text()='Countries']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Add New Country')]")).click();
        checkActiveLink();
        sleep(5);

    }

    public void checkActiveLink() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//tr//i[@class='fa fa-external-link']"));
        String originalWindow = getDriver().getWindowHandle();
        Set<String> existingWindows = getDriver().getWindowHandles();
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).click();
            String newWindow = getWait().until(anyWindowOtherThan(existingWindows));
            getDriver().switchTo().window(newWindow);
            getDriver().close();
            getDriver().switchTo().window(originalWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ?
                        handles.iterator().next() : null;
            }
        };
    }

    @After
    public void stop() {
        terDawn();
    }
}
