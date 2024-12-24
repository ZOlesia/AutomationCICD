package rahulshettyacademy.StepDefinitions;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		productCatalog.addProductToCart(productName);
		Thread.sleep(3000);
	}
	
	@When("Checkout {string} and submit the order")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckoutPage();
		
		//code is weird probably issues with app itself that's why using js to scroll page to be able to see and click on dropdown
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(200,500)");
		Thread.sleep(3000);
		//
		
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }	
}
