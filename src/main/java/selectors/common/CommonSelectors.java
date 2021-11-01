package selectors.common;

import org.openqa.selenium.By;

public class CommonSelectors {
    private static final String loaderWrapperSelector = "loader-wrapper__loader";

    public By getLoaderWrapperSelector() {
        return By.className(loaderWrapperSelector);
    }
}
