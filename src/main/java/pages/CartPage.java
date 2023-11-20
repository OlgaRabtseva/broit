package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestContext;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CartPage extends Page {

    @FindBy(xpath = "//li[@class='cart-item']")
    private List<WebElement> cartItems;
    @FindBy(xpath = "//div[@class='text-sm-center']//a[contains(@class,'btn-primary')]")
    private WebElement proceedToCheckoutBtn;
    @FindBy(id = "checkout-personal-information-step")
    private WebElement personalInfoSection;
    @FindBy(xpath = "//div[@id='_desktop_logo']//a")
    private WebElement myStoreLogo;
    @FindBy(xpath = "//div[@id='_desktop_cart']//div[@class='header']")
    private WebElement cartBtn;
    @FindBy(xpath = "//a[@class='remove-from-cart']")
    private List<WebElement> removeItemBtn;
    @FindBy(xpath = "//input[@name='product-quantity-spin']")
    private WebElement itemQuantityInput;

    public CartPage(TestContext testContext) {
        super(testContext);
        wait.until(visibilityOfElementLocated(By.xpath("//ul[@class='cart-items']")));
    }

    public int getItemsAmount() {
        return cartItems.size();
    }

    public boolean startCheckoutSteps() {
        proceedToCheckoutBtn.click();
        wait.until(visibilityOf(personalInfoSection));
        return personalInfoSection.isDisplayed();
    }

    public void cleanUpCart() {
        wait.until(visibilityOf(myStoreLogo)).click();
        wait.until(visibilityOf(cartBtn)).click();
        wait.until(visibilityOf(removeItemBtn.get(0)));
        for (int i = 0; i < removeItemBtn.size(); i++) {
            removeItemBtn.get(0).click();
            Selenide.sleep(2000);
        }
    }

    public void changeItemQuantity(int newQuantity) {
        wait.until(visibilityOf(itemQuantityInput));
        itemQuantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(newQuantity));
        itemQuantityInput.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        Selenide.sleep(2000);
    }
}
