package tests;

import org.testng.annotations.DataProvider;
import testComponents.BaseTest;
import com.pageObject.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {


    String productName = "ZARA COAT 3";
    String country = "United States";
    String positiveOrderTitle = "Thankyou for the order.";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

        ProductCatalog productCatalog = landingPage.loginApp(input.get("email"), input.get("password"));
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.goToCartPage();
        boolean match = cartPage.isOnlyThisProductPresent(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckoutPage();
        checkOutPage.selectCountry(country);
        PlaceOrderPage placeOrderPage = checkOutPage.clickPlaceOrderButton();
        String expectedTitle = placeOrderPage.getOrderText();
        Assert.assertEquals(expectedTitle.toLowerCase(), positiveOrderTitle.toLowerCase());
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest()  {
        ProductCatalog productCatalog = landingPage.loginApp("Caxa@gmail.com", "Aea520559");
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }



    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("email", "Caxa@gmail.com");
//        map.put("password", "Aea520559");
//        map.put("productName", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<>();
//
//        map.put("email", "Caxa1@gmail.com");
//        map.put("password", "Aca520559");
//        map.put("productName", "ADIDAS ORIGINAL");
        List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object [][]{{data.get(0)}, {data.get(1)}};
    }


}
