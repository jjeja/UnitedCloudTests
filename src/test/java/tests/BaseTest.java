package tests;

import methods.login.LogInMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.WebElementUtils;

public class BaseTest {
    private static final String username = "jelena.jankovic";
    private static final String password = "Lozinka123";

    protected final WebDriver driver;
    protected final WebElementUtils webElementUtils;
    protected final LogInMethods logInMethods;

    public BaseTest() {
        driver = new ChromeDriver();
        this.webElementUtils = new WebElementUtils(driver);
        this.logInMethods = new LogInMethods(driver);
    }

    @BeforeTest
    public void setup() {
        this.logInMethods.submitLogIn(username, password);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}