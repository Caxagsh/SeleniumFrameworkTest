package com.pageObject;

import com.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "div.infoWrap h3")
    List<WebElement> cartList;

    @FindBy(css = "div.subtotal button.btn")
    WebElement checkoutButton;

    public boolean isOnlyThisProductPresent(String productName) {
        return cartList.stream().allMatch(e -> e.getText().equals(productName));
    }

    public CheckOutPage goToCheckoutPage(){
        checkoutButton.click();
//
        return new CheckOutPage(driver);
    }
}
