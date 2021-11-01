package selectors.profile;

import lombok.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateProfileSelectors {

    private static final String createNewProfileButtonSelector = ".create button";
    private static final String createNewProfileNavigationButtonSelector = "//button[text()='Create profile']";
    private static final String nameID = "profile-name";
    private static final String submitButtonSelector = "form button[type=\"submit\"]";
    private final WebDriver driver;

    public CreateProfileSelectors(WebDriver driver) {
        this.driver = driver;
    }

    public By getCreateNewProfileSelector() {
        return By.cssSelector(createNewProfileButtonSelector);
    }

    public WebElement getCreateNewProfileButton() {
        return driver.findElement(getCreateNewProfileSelector());
    }

    public By getCreateNewProfileFromNavigationSelector() {
        return By.xpath(createNewProfileNavigationButtonSelector);
    }
    public WebElement getCreateNewProfileFromNavigationButton() {
        return driver.findElement(getCreateNewProfileFromNavigationSelector());
    }

    public WebElement getName() {
        return driver.findElement(By.id(nameID));
    }

    public void sendKeysName(String name) {
        getName().sendKeys(name);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.cssSelector(submitButtonSelector));
    }
}
