package com.pageObject;

import com.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckOutPage  extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(css = "input[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css = "button.ng-star-inserted")
    List<WebElement> countries;

    @FindBy(css = "a.btnn.action__submit.ng-star-inserted")
    WebElement placeOrderButton;

    By countrySection = By.cssSelector("section.ng-star-inserted");

    public void selectCountry(String country) {
        selectCountry.sendKeys("united");
        waitForElementToAppear(countrySection);
        countries.stream().filter(e -> e.getText().equals(country)).findFirst().orElse(null).click();
    }

    public PlaceOrderPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return new PlaceOrderPage(driver);
    }




}
