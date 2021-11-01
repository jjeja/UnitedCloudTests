package selectors.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AgeSelectors {
    private static final String Age0to6ID = "AGE_0_6";
    private static final String Age7to11ID = "AGE_7_11";
    private static final String Age12to14ID = "AGE_12_14";
    private static final String Age15to17ID = "AGE_15_17";
    private static final String Age18plusID = "AGE_18_PLUS";
    private static final String BirthYearID = "year";

    private final WebDriver driver;

    public AgeSelectors(WebDriver driver) {
        this.driver = driver;
    }

    public By getAge0to6Selector() {
        return By.id(Age0to6ID);
    }

    public WebElement getAge0to6() {
        return driver.findElement(getAge0to6Selector());
    }

    public By getAge7to11Selector() {
        return By.id(Age7to11ID);
    }

    public WebElement getAge7to11() {
        return driver.findElement(getAge7to11Selector());
    }

    public By getAge12to14Selector() {
        return By.id(Age12to14ID);
    }

    public WebElement getAge12to14() {
        return driver.findElement(getAge12to14Selector());
    }

    public By getAge15to17Selector() {
        return By.id(Age15to17ID);
    }

    public WebElement getAge15to17() {
        return driver.findElement(getAge15to17Selector());
    }

    public By getAge18PlusSelector() {
        return By.id(Age18plusID);
    }

    public WebElement getAge18plus() {
        return driver.findElement(getAge18PlusSelector());
    }

    public WebElement getBirthYear() {
        return driver.findElement(By.id(BirthYearID));
    }
}
