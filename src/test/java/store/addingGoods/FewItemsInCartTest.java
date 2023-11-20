package store.addingGoods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;
import store.TestSetup;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class FewItemsInCartTest extends TestSetup {

    private MainPage mainPage;
    private LoginPage loginPage;
    private CartPage cartPage;


    @BeforeClass
    public void setUp() {
        mainPage = new MainPage(testContext);
//        loginPage = mainPage.openLoginPage();
//        loginPage.doSuccessLogin(EMAIL, PASS);
    }

    @Test
    public void addFewItemsInCartTest() {
        mainPage.openAccessoriesPage();
        assertEquals("The cart should be empty", 0, mainPage.getItemCountFromCart());
        mainPage.addOneItemToCart(0);
        assertEquals("The cart should contain 1 item", 1, mainPage.getItemCountFromCart());
        mainPage.addOneItemToCart(1);
        assertEquals("The cart should contain 2 items", 2, mainPage.getItemCountFromCart());
        cartPage = mainPage.goToCart();
        assertEquals("The cart should contain 2 rows", 2, cartPage.getItemsAmount());
        assertTrue("Personal info should be shown", cartPage.startCheckoutSteps());
        cartPage.cleanUpCart();
    }

    @Test
    public void addFewSameItemsInCartTest() {
        mainPage.openAccessoriesPage();
        assertEquals("The cart should be empty", 0, mainPage.getItemCountFromCart());
        mainPage.addOneItemToCartWithQuantity(0, 5);
        assertEquals("The cart should contain 1 item", 5, mainPage.getItemCountFromCart());
        mainPage.addOneItemToCart(0);
        assertEquals("The cart should contain 1 item", 6, mainPage.getItemCountFromCart());
        cartPage = mainPage.goToCart();
        assertEquals("The cart should contain 1 row", 1, cartPage.getItemsAmount());
        assertTrue("Personal info should be shown", cartPage.startCheckoutSteps());
        cartPage.cleanUpCart();
    }

    @Test
    public void increaseItemQuantityInTheCart() {
        mainPage.openAccessoriesPage();
        assertEquals("The cart should be empty", 0, mainPage.getItemCountFromCart());
        mainPage.addOneItemToCart(0);
        assertEquals("The cart should contain 1 item", 1, mainPage.getItemCountFromCart());
        cartPage = mainPage.goToCart();
        assertEquals("The cart should contain 1 row", 1, cartPage.getItemsAmount());
        cartPage.changeItemQuantity(50);
        assertEquals("The cart should contain 50 items", 50, mainPage.getItemCountFromCart());
        assertTrue("Personal info should be shown", cartPage.startCheckoutSteps());
        cartPage.cleanUpCart();
    }

    @AfterClass
    public void cleanUpTheCart() {
        if (cartPage != null && cartPage.getItemsAmount() > 0) {
            cartPage.cleanUpCart();
        }
    }
}
