package mk.ukim.finki.seleniumtesting;

import com.sun.java.swing.plaf.windows.resources.windows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ResourceBundle;

public class BasePage {
    public final WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }
}
