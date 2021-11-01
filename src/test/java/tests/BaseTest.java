package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import utils.WebElementUtils;

public class BaseTest {
    protected final WebDriver driver;
    protected final WebElementUtils webElementUtils;

    public BaseTest() {
        driver = new ChromeDriver();
        this.webElementUtils = new WebElementUtils(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}