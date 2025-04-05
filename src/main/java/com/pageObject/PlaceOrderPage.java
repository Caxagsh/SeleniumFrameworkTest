package com.pageObject;

import com.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage extends AbstractComponent {
    WebDriver driver;
    public PlaceOrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1.hero-primary")
    WebElement thxForOrderTitle;

    public String getOrderText() {
        return thxForOrderTitle.getText();
    }
}
