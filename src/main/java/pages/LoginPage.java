package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestContext;

public class LoginPage extends Page {

    @FindBy(id = "field-email")
    private WebElement emailField;
    @FindBy(id = "field-password")
    private WebElement passField;
    @FindBy(id = "submit-login")
    private WebElement signInBtn;

    public LoginPage(TestContext testContext) {
        super(testContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("_desktop_logo")));
    }

    public void doSuccessLogin(String email, String pass) {
        emailField.sendKeys(email);
        passField.sendKeys(pass);
        signInBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(signInBtn));
    }
}
