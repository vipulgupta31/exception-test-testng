package test;

import java.net.*;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class ExceptionTest {

	public RemoteWebDriver driver = null;

	String username = System.getenv("LT_USERNAME") == null ? "<lambdatest_username>" : System.getenv("LT_USERNAME");
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "<lambdatest_accesskey>"
			: System.getenv("LT_ACCESS_KEY");

	@BeforeTest
	public void setup() {
		try {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPlatformName("Windows 10");
			chromeOptions.setBrowserVersion("110.0");

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("build", "Exception Test in TestNG");

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
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSingleException_passed() {
		driver.findElement(By.xpath("//*[@class='st_heading_1']"));
	}

	// This case will fail as web element selector is valid
	// hence no exception is raised
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSingleException_failed() {
		driver.findElement(By.xpath("//*[@class='st_heading']"));
	}

	// This case will pass as exception thrown
	// is one of the expected list
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_passed() {
		driver.switchTo().window("new_window");
	}

	// This case will fail as exception raised is not on the list 
	//i.e. ArithmeticException
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_failed_1() {
		int i = 10 / 0;
	}

	// This case will fail as none of the expected exception is raised
	@Test(expectedExceptions = { NoSuchElementException.class, NoSuchWindowException.class })
	public void testMultipleException_failed_2() {
		driver.findElement(By.xpath("//*[@class='st_heading']"));
	}

	// This case will pass as expected exception message is same.
	@Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp = "/ by zero")
	public void testExceptionMessage_passed() {
		int i = 10 / 0;
	}

	// This case will fail as expected exception message is not same.
	@Test(expectedExceptions = NoSuchWindowException.class, expectedExceptionsMessageRegExp = "/ by zero")
	public void testExceptionMessage_failed() {
		driver.switchTo().window("new_window");
	}

}
