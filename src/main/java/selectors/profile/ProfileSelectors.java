package selectors.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileSelectors {
    private final WebDriver driver;

    private static final String nameSelector = "card__profile-name";
    private static final String typeSelector = "card__profile-type";
    private static final String nameErrorSelector = "//*[contains(text(), 'Please fill in this field.')]";

    public ProfileSelectors(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProfileName() {
        return driver.findElement(By.className(nameSelector));
    }

    public WebElement getProfileType() {
        return driver.findElement(By.className(typeSelector));
    }

    public WebElement getErrorName() {
        return driver.findElement(By.xpath(nameErrorSelector));
    }
}
