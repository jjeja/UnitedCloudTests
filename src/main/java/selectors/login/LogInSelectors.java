package selectors.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInSelectors {

    private static final String UsernameID = "username";
    private static final String PasswordID = "password";
    private static final String LoginButtonSelector = "form button";
    private static final String LogoutButtonSelector = "//button[text()='Logout']";

    private final WebDriver driver;

    public LogInSelectors(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement getUsername() {
        return driver.findElement(By.id(UsernameID));
    }

    public WebElement getPassword() {
        return driver.findElement(By.id(PasswordID));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.cssSelector(LoginButtonSelector));
    }

    public WebElement getLogoutButton() {
        return driver.findElement(By.xpath(LogoutButtonSelector));
    }
}
