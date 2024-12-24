package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyProductDisplay(String productName) {
		return cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkout.click();
		return new CheckoutPage(driver);
	}
}
