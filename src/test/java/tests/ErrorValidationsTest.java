package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pageObject.CartPage;
import com.pageObject.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseTest;
import testComponents.Retry;

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

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation(){

        landingPage.loginApp("Caxa@3333gmail.com", "Aea520559");
        Assert.assertEquals("Incorrect email o password.", landingPage.getErrorMessage());
    }
}
