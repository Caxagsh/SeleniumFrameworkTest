package tests;

import com.pageObject.CartPage;
import com.pageObject.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {



    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApp("Caxa@gmail.com", "Aea520559");

        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        boolean match = cartPage.isOnlyThisProductPresent(productName+"3");
        Assert.assertFalse(match);

    }

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation(){
        landingPage.loginApp("Caxa@3333gmail.com", "Aea520559");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
}
