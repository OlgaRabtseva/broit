package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.TestContext;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends Page {

    @FindBy(id = "category-6")
    private WebElement accessoriesLink;
    @FindBy(id = "subcategories")
    private WebElement subCategories;
    @FindBy(xpath = "//div[@id='_desktop_user_info']//a")
    private WebElement signInLink;
    @FindBy(id = "field-email")
    private WebElement emailField;
    @FindBy(xpath = "//span[@class='cart-products-count']")
    private WebElement productsCountInCart;
    @FindBy(xpath = "//button[contains(@class,'add-to-cart')]")
    private WebElement addToCartBtn;
    @FindBy(xpath = "//div[@id='blockcart-modal']//button[@aria-label='Close']")
    private WebElement closeItemModalBtn;
    @FindBy(xpath = "//div[@id='_desktop_cart']//div[@class='header']")
    private WebElement cartBtn;
    @FindBy(xpath = "//article[@data-id-product-attribute]")
    private List<WebElement> productsList;
    @FindBy(xpath = "//a[@data-link-action]")
    private List<WebElement> quickViewBtns;
    @FindBy(id = "framelive")
    private WebElement shopIFrame;
    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private WebElement itemQuantityInput;

    public MainPage(TestContext testContext) {
        super(testContext);
        switchToShopFrame();
    }

    public LoginPage openLoginPage() {
        signInLink.click();
        wait.until(visibilityOf(emailField));
        return new LoginPage(getTestContext());
    }

    public void openAccessoriesPage() {
        accessoriesLink.click();
        wait.until(visibilityOf(subCategories));
    }

    public int getItemCountFromCart() {
        ((JavascriptExecutor) getTestContext().getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", productsCountInCart);
        return Integer
                .parseInt(productsCountInCart.getText().substring(1, productsCountInCart.getText().length() - 1));
    }

    public void addOneItemToCart(int numberOfItem) {
        addOneItemToCartWithQuantity(numberOfItem, 1);
    }

    public void addOneItemToCartWithQuantity(int numberOfItem, int itemQuantity) {
        ((JavascriptExecutor) getTestContext().getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", productsList.get(numberOfItem));
        new Actions(getTestContext().getDriver()).moveToElement(productsList.get(numberOfItem)).build().perform();
        wait.until(visibilityOf(quickViewBtns.get(numberOfItem))).click();
        wait.until(visibilityOf(addToCartBtn));
        if (itemQuantity > 1) {
            itemQuantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(itemQuantity));
        }
        addToCartBtn.click();
        wait.until(visibilityOf(closeItemModalBtn)).click();
        Selenide.sleep(1500);
    }

    public CartPage goToCart() {
        cartBtn.click();
        return new CartPage(getTestContext());
    }

    public void switchToShopFrame() {
        getTestContext().getDriver().switchTo().frame(shopIFrame);
        wait.until(visibilityOf(accessoriesLink));
    }
}
