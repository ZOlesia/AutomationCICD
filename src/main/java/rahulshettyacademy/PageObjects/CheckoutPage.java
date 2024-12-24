package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountry;
	
	By countryDropdownResult = By.cssSelector(".ta-results");
	By shippingInfo = By.cssSelector(".details__user");
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCountry(String countryName) {
		waitForElementToAppear(shippingInfo);
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(countryDropdownResult);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		waitForElementToBeClickable(submit);
		submit.click();
		return new ConfirmationPage(driver);
	}
}
