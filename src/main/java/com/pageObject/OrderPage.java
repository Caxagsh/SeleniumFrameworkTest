package com.pageObject;

import com.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    @FindBy(css = "div.subtotal button.btn")
    WebElement checkoutButton;

    public boolean verifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }


}
