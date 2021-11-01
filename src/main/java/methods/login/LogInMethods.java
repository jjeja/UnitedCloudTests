package methods.login;

import selectors.login.LogInSelectors;
import org.openqa.selenium.WebDriver;
import selectors.profile.HomeSelectors;
import utils.WebElementUtils;

public class LogInMethods {

    private final LogInSelectors logInSelectors;
    private final HomeSelectors homeSelectors;
    private final WebDriver driver;
    private final WebElementUtils utils;

    public LogInMethods(WebDriver driver) {
        this.driver = driver;
        this.logInSelectors = new LogInSelectors(driver);
        this.homeSelectors = new HomeSelectors(driver);
        this.utils = new WebElementUtils(driver);
    }

    private void inputUsername(String username) {
        logInSelectors.getUsername().click();
        logInSelectors.getUsername().sendKeys(username);
    }

    private void inputPassword(String password) {
        logInSelectors.getPassword().click();
        logInSelectors.getPassword().sendKeys(password);
    }

    private void clickLogInButton() {
        utils.waitForElementToBeClickable(logInSelectors.getLoginButton());
        logInSelectors.getLoginButton().submit();
    }

    public void submitLogIn(String username, String password) {
        driver.get(homeSelectors.getHomePageUrl());
        inputUsername(username);
        inputPassword(password);
        clickLogInButton();
    }
}
