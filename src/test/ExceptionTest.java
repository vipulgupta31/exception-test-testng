package test;

import java.net.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class ExceptionTest {

	public RemoteWebDriver driver = null;

	String username = System.getenv("LT_USERNAME") == null ? "<lambdatest_username>" : System.getenv("LT_USERNAME");
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "<lambdatest_accesskey>" : System.getenv("LT_ACCESS_KEY");

	@BeforeTest
	public void setup() {
		try {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPlatformName("Windows 10");
			chromeOptions.setBrowserVersion("110.0");

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("build", "Exception Test in TestNG");
			ltOptions.put("name", "Exception Test in TestNG");

			chromeOptions.setCapability("LT:Options", ltOptions);

			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), chromeOptions);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.get("https://www.lambdatest.com/selenium-playground/");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	// This case will pass as same exception is thrown
	// due to invalid web element selector
	@Test(expectedExceptions = TimeoutException.class)
	public void testSingleException_passed() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MILLISECONDS);
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	// This case will fail as web element selector is valid
	// hence no exception is raised
	@Test(expectedExceptions = TimeoutException.class)
	public void testSingleException_failed() {
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	// This case will pass as exception thrown
	// is one of the expected list i.e. NoSuchWindowException
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_NoSuchWindowException_passed() {
		driver.switchTo().window("new_window");
	}

	// This case will pass as exception thrown
	// is one of the expected list i.e. NoSuchElementException
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_NoSuchElementException_passed() {
		driver.findElement(By.xpath("//*[@class='st_heading_1']"));
	}

	// This case will fail as exception raised is not on the list
	// i.e. TimeoutException
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_failed_1() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MILLISECONDS);
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	// This case will fail as none of the expected exception is raised
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_failed_2() {
		driver.findElement(By.xpath("//*[@class='st_heading']"));
	}

	// This case will pass as expected exception message is same.
	@Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "(?s).*no such element.*")
	public void testExceptionMessage_passed() {
		driver.findElement(By.xpath("//*[@class='st_heading_1']"));
	}

	// This case will fail as expected exception message is not same.
	@Test(expectedExceptions = NoSuchWindowException.class, expectedExceptionsMessageRegExp = "(?s).*no such element.*")
	public void testExceptionMessage_failed() {
		driver.switchTo().window("new_window");
	}

}
