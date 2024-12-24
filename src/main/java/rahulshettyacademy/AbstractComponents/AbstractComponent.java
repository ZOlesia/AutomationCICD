package rahulshettyacademy.AbstractComponents;

import java.awt.print.PageFormat;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.core.GoLangFileMatch;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
}
