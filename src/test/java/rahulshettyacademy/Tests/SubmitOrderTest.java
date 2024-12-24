package rahulshettyacademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.OrderPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;

import java.awt.color.ProfileDataException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.CustomAttribute;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	static String productName = "ADIDAS ORIGINAL";
	static String countryName = "India";

	
	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String, String> data) throws IOException, InterruptedException {
		ProductCatalog productCatalog = landingPage.loginApplication(data.get("email"), data.get("password"));
		
//		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(data.get("product"));
		Thread.sleep(3000);
		
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(data.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		
		//code is weird probably issues with app itself that's why using js to scroll page to be able to see and click on dropdown
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(200,500)");
		Thread.sleep(3000);
		//
		
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryTest() throws InterruptedException {
		ProductCatalog productCatalog = landingPage.loginApplication("testtester123@gmail.com", "Testtester123!");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/rahulshettyacademy/Data/PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
		
		// method1
	//		return new Object[][] {{"testtester123@gmail.com", "Testtester123!"}, {"gogo123@gmail.com", "Gogo123321!"}};
		
		//		method2
	//		HashMap<String, String> map1 = new HashMap<String, String>();
	//		map1.put("email", "testtester123@gmail.com");
	//		map1.put("password", "Testtester123!");
	//		
	//		HashMap<String, String> map2 = new HashMap<String, String>();
	//		map2.put("email", "gogo123@gmail.com");
	//		map2.put("password", "Gogo123321!");
	//		return new Object[][] {{map1}, {map2}};
		
	}
	
	

}
