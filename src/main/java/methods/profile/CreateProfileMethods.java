package methods.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selectors.common.CommonSelectors;
import selectors.profile.AgeSelectors;
import selectors.profile.AvatarSelectors;
import selectors.profile.CreateProfileSelectors;
import org.openqa.selenium.WebDriver;
import utils.WebElementUtils;

import java.time.Duration;

public class CreateProfileMethods {

    private final CreateProfileSelectors createProfileSelectors;
    private final AgeSelectors ageSelectors;
    private final AvatarSelectors avatarSelectors;
    private final WebDriver driver;
    private final WebElementUtils webElementUtils;
    private final CommonSelectors commonSelectors;

    public CreateProfileMethods(WebDriver driver) {
        this.driver = driver;
        createProfileSelectors = new CreateProfileSelectors(driver);
        ageSelectors = new AgeSelectors(driver);
        avatarSelectors = new AvatarSelectors(driver);
        webElementUtils = new WebElementUtils(driver);
        commonSelectors = new CommonSelectors();
    }

    public void createProfileFromChooseMenu(String name, Integer age, Integer birthYear) {
        webElementUtils.waitForElementToBeClickable(createProfileSelectors.getCreateNewProfileSelector());
        createProfileSelectors.getCreateNewProfileButton().click();
        createProfile(name, age, birthYear);
    }

    public void createProfileFromNavigation(String name, Integer age, Integer birthYear) {
        webElementUtils.waitForElementToBeClickable(createProfileSelectors.getCreateNewProfileFromNavigationSelector());
        createProfileSelectors.getCreateNewProfileFromNavigationButton().click();
        createProfile(name, age, birthYear);
    }

    public void createProfile(String name, Integer age, Integer birthYear) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        createProfileSelectors.getName().click();
        createProfileSelectors.sendKeysName(name);

        pickRandomAge(age, birthYear);
        pickAvatarBasedOnAge(age);

        createProfileSelectors.getSubmitButton().click();
    }

    private void pickRandomAge(Integer age, Integer birthYear) {
        By ageElementSelector;

        if (age <= 6) {
            ageElementSelector = ageSelectors.getAge0to6Selector();
        } else if (age >= 7 && age <= 11) {
            ageElementSelector = ageSelectors.getAge7to11Selector();
        } else if (age >= 12 && age <= 14) {
            ageElementSelector = ageSelectors.getAge12to14Selector();
        } else if (age >= 15 && age <= 17) {
            ageElementSelector = ageSelectors.getAge15to17Selector();
        } else {
            ageElementSelector = ageSelectors.getAge18PlusSelector();
        }

        webElementUtils.waitForElementToBeInvisible(commonSelectors.getLoaderWrapperSelector());
        WebElement parent = driver.findElement(ageElementSelector).findElement(By.xpath("./.."));
        parent.click();

        if (age >= 18) {
            addBirthYear(birthYear);
        }
    }

    private void addBirthYear(Integer birthYear) {
        ageSelectors.getBirthYear().click();
        ageSelectors.getBirthYear().sendKeys(birthYear == null ? "" : birthYear.toString());
    }

    private void pickAvatarBasedOnAge(Integer age) {
        String avatarSelector = avatarSelectors.getRandomAvatarSelectorForAge(age);
        webElementUtils.waitForElementToBeInvisible(By.xpath(avatarSelector));
        webElementUtils.waitForElementToBeVisible(By.xpath(avatarSelector));

        Actions actions = new Actions(driver);

        WebElement element = driver.findElement(By.xpath(avatarSelector));
        actions.moveToElement(element).click().perform();
    }
}
