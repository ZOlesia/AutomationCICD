package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsLocator = By.cssSelector(".mb-3"); //By.xpath("//div[contains(@class,'mb-3')]")
	By addToCartLocator = By.cssSelector(".card-body button:last-of-type");
	By toasMessageLocator = By.cssSelector("#toast-container");
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsLocator);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCartLocator).click();
		waitForElementToAppear(toasMessageLocator);
		waitForElementToDisappear(spinner);
	}
	
}
