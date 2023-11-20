package pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestContext;

import java.time.Duration;

abstract public class Page {
    private TestContext testContext;
    public static WebDriverWait wait;

    public Page(TestContext testContext) {
        this.testContext = testContext;
        wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(20), Duration.ofMillis(500));
        PageFactory.initElements(testContext.getDriver(), this);
    }

    public TestContext getTestContext() {
        return testContext;
    }

}
