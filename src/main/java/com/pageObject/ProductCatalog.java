package com.pageObject;

import com.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
    WebDriver driver;
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }



    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");

    By addToCartBy = (By.cssSelector("button.btn.w-10.rounded"));

    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement>getProductList() throws InterruptedException {
        waitForElementToAppear(productsBy);
        Thread.sleep(2000);

        return products;
    }

    public WebElement getProductByName(String productName) throws InterruptedException {
        return getProductList().stream().filter(e-> e.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        getProductByName(productName).findElement(addToCartBy).click();
        waitForElementToAppear(addToCartBy);
        waitForElementToDisappear(spinner);
        //TODO waitForElementToDisappear(webEle)
    }

}
