//package rahulshettyacademy.Tests;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import rahulshettyacademy.PageObjects.LandingPage;
//
//import java.time.Duration;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//public class StandAloneTest {
//	static WebDriver driver;
//
//	static String productName = "ADIDAS ORIGINAL";
//
//	static String countryName = "India";
//
//	public static void main(String[] args) throws InterruptedException {
////		ChromeOptions options = new ChromeOptions();
////
////		options.setAcceptInsecureCerts(true);
//
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		landingPage.loginApplication("testtester123@gmail.com", "Testtester123!");
//
//
//		//Explicit wait for page to load after click on login
//
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//
//
//		//List of all the products on page
//
//		List<WebElement> allproducts = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
//		//or by cssSelector
//		List<WebElement> allproductscss = driver.findElements(By.cssSelector(".mb-3"));
//
//		//Finding a specific product from the list of products and then click for add cart
//
//		WebElement prod = allproducts.stream().filter(product->
//		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//
//
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//
//		//xpath("//button[contains(@class,'btn w-10 rounded')]") - selecting always first value
//
//
//		//checking for success message and the click on cart link
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//
//		//ng-animating
//
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//
//
//		//Now to verify the items in cart
//		//xpath = "//div[@class='cartSection']/h3"
//		//css selector ".cartSection h3"
//
//		List<WebElement> listOfSelectedProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
//
//		Boolean match = listOfSelectedProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
//
//		Assert.assertTrue(match);
//
//
//		//Then click check out by xpath - "//button[text()='Checkout']"
//		//or by CSS Selector = ".totalRow button"
//
//		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details__user")));
//		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(200,500)");
//		Thread.sleep(5000);
//
//		Actions act = new Actions(driver);
//
//		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryName).build().perform();
//
//
//
//		//xpath class for the autosuggestive dropdowns - ta-results list-group ng-star-inserted
//		// "//Section[@class='ta-results list-group ng-star-inserted']/button //span[text()='India']"
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
//		driver.findElement(By.xpath("//Section[@class='ta-results list-group ng-star-inserted']/button //span[text()=' "+countryName+"']")).click();
//
//		System.out.println("Entered Shipping country "+countryName);
//
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit")));
//
//		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
//
//
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//
//		driver.close();
//		
//		
//		
//		
//		
//		
//		
//		// TODO Auto-generated method stub
////		String productName = "ZARA COAT 3";
////
////		WebDriverManager.chromedriver().setup();
////		WebDriver driver = new ChromeDriver();
////		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////		driver.manage().window().maximize();
////		driver.get("https://rahulshettyacademy.com/client");
////		JavascriptExecutor js = (JavascriptExecutor) driver;
////		
////		//login
////		driver.findElement(By.id("userEmail")).sendKeys("testtester123@gmail.com");
////		driver.findElement(By.id("userPassword")).sendKeys("Testtester123!");
////		driver.findElement(By.id("login")).click();
////		
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
////		
////		//add to cart
////		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
////		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
////		WebElement product = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
////		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
////		
////		//before go to cart
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
////		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink='/dashboard/cart']")));
////		WebElement cart = driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']"));
////		js.executeScript("arguments[0].click();", cart);
////		
////		
////		//go to cart
////		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
////		Boolean isMatch = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
////		Assert.assertTrue(isMatch);
////		
////		//go to Checkout
////		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
////		js.executeScript("arguments[0].click();", checkout);
////		
////		//shipping info
////		Actions a = new Actions(driver);
////		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));
//////		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
////		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
////		
////		js.executeScript("window.scrollBy(0,100)");
////		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
////		driver.findElement(By.cssSelector(".action__submit")).click();
////		
////		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
////		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
////		driver.close();
//		
//	}
//
//}
