package selectors.profile;

import org.openqa.selenium.WebDriver;

import java.util.Random;

public class AvatarSelectors {
    private final WebDriver driver;

    private static final String[] Age0to6 = {
        "14",
        "2",
        "16",
        "4",
        "15",
        "3"
    };

    private static final String[] Age7to11 = {
        "14",
        "2",
        "16",
        "4",
        "15",
        "3"
    };

    private static final String[] Age12to14 = {
        "22",
        "20",
        "18",
        "13",
        "24",
        "7",
        "8",
        "11",
        "41",
        "5",
        "21",
        "19",
        "17",
        "6",
        "12",
        "23",
        "42",
        "9",
        "40",
        "39",
        "10",
        "34",
        "35",
        "36",
        "37",
        "38"
    };
    private static final String[] Age15to17 = {
        "22",
        "20",
        "18",
        "13",
        "24",
        "7",
        "8",
        "11",
        "41",
        "5",
        "21",
        "19",
        "17",
        "6",
        "12",
        "23",
        "42",
        "9",
        "40",
        "39",
        "10",
        "34",
        "35",
        "36",
        "37",
        "38"
    };
    private static final String[] Age18plus = {
        "22",
        "20",
        "18",
        "13",
        "24",
        "7",
        "8",
        "11",
        "41",
        "5",
        "21",
        "19",
        "17",
        "6",
        "12",
        "23",
        "42",
        "9",
        "40",
        "39",
        "10",
        "34",
        "35",
        "36",
        "37",
        "38"
    };

    public AvatarSelectors(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public String getRandomAvatarSelectorForAge(Integer age) {
        String[] array;
        if (age <= 6) {
            array = Age0to6;
        } else if (age >= 7 && age <= 11) {
            array = Age7to11;
        } else if (age >= 12 && age <= 14) {
            array = Age12to14;
        } else if (age >=15 && age <= 17) {
            array = Age15to17;
        } else {
            array = Age18plus;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(array.length);

//        return driver.findElement(By.xpath("//input[@id='" + array[randomNumber] + "']/following-sibling::label/div/img"));
        return "//input[@id='" + array[randomNumber] + "']/following-sibling::label/div/img";
    }

}
