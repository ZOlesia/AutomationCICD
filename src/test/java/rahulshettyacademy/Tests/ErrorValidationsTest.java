package rahulshettyacademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

import static org.testng.Assert.assertEquals;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("testtesefsster123@gmail.com", "Testtesdfster123!");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalog productCatalog = landingPage.loginApplication("testtester123@gmail.com", "Testtester123!");
		
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		Thread.sleep(3000);
		
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
