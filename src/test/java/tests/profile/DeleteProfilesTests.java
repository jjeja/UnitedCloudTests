package tests.profile;

import com.github.javafaker.Faker;
import methods.profile.CreateProfileMethods;
import methods.profile.DeleteProfileMethods;
import methods.login.LogInMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DeleteProfilesTests extends BaseTest {
    private final LogInMethods logInMethods;
    private final DeleteProfileMethods deleteProfileMethods;
    private final CreateProfileMethods createProfileMethods;
    public String username = "jelena.jankovic";
    public String password = "Lozinka123";

    public DeleteProfilesTests() {
        logInMethods = new LogInMethods(driver);
        deleteProfileMethods = new DeleteProfileMethods(driver);
        createProfileMethods = new CreateProfileMethods(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeTest
    public void setup() {
        this.logInMethods.submitLogIn(username, password);
        System.out.println("login");
    }

    @Test
    public void deleteProfileTest() {
        Random random = new Random();
        int age = random.nextInt(100);
        Integer birthYear = null;
        String name = Faker.instance().lorem().characters(15);

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);
        deleteProfileMethods.deleteProfile();

        webElementUtils.waitForElementToBeVisible(By.className("profiles__profile"));
        List<WebElement> profiles = driver.findElements(By.className("profiles__profile"));

        Assert.assertEquals(profiles.size(), 1);
    }

    @Test
    public void familyProfileDelete() {
        webElementUtils.waitForElementToBeVisible(By.className("profiles__profile"));
        List<WebElement> profiles = driver.findElements(By.className("profiles__profile"));
        profiles.get(0).click();

        String disabled = driver.findElement(By.className("card__delete")).getAttribute("disabled");

        Assert.assertEquals(disabled, "true");
    }
}
