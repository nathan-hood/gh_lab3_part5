import org.junit.Test;

import junit.framework.*;

//import com.google.common.base.CharMatcher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebAppTest extends TestCase {

	WebDriver driver;

	public WebAppTest() {
		driver = new FirefoxDriver();
	}

	public String parseTemp(String conversionMsg) {
		
		int starts = conversionMsg.indexOf("=") + 2; //adding 2 to move past = and trailing space
		int ends = conversionMsg.indexOf("Celsius");
		
		return conversionMsg.substring(starts,ends).trim();
	}

	@Test
	public void testAndyLoginAnd32F() {

		driver.get("http://adnan.appspot.com/testing-lab-login.html");

		// Enter the user id
		WebElement userId = driver.findElement(By.name("userId"));
		userId.clear();
		userId.sendKeys("andy");

		// Enter the password
		WebElement password = driver.findElement(By.name("userPassword"));
		password.clear();
		password.sendKeys("apple");
		password.submit();

		// Retrieve the page title
		String pageTitle = driver.getTitle();

		// Confirm that the Bad Login screen was NOT loaded and then confirm
		// that the regular application page WAS loaded.
		assertFalse("Bad Login Screen was detected",
				pageTitle.equals("Bad Login"));
		assertTrue(
				"Bad Login Screen was detected",
				pageTitle.trim().equals(
						"Online temperature conversion calculator"));

		// Enter the temp
		WebElement fTemp = driver.findElement(By.name("farenheitTemperature"));
		fTemp.clear();
		fTemp.sendKeys("32");
		fTemp.submit();

		// Retrieve the converted temp
		WebElement cTemp = driver.findElement(By.tagName("h2"));
		String celsiusTemp = parseTemp(cTemp.getText());

		// Confirm that 32F was converted to 0.00C.
		assertEquals("Checking for 32F -> 0.00C", "0.00", celsiusTemp);

	}

	@Test
	public void testBobAnd213FConversion() {
        
		driver.get("http://adnan.appspot.com/testing-lab-login.html");

		// Enter the user id
		WebElement userId = driver.findElement(By.name("userId"));
		userId.clear();
		userId.sendKeys("bob");

		// Enter the password
		WebElement password = driver.findElement(By.name("userPassword"));
		password.clear();
		password.sendKeys("bathtub");
		password.submit();

		// Enter the temp
		WebElement fTemp = driver.findElement(By.name("farenheitTemperature"));
		fTemp.clear();
		fTemp.sendKeys("213");
		fTemp.submit();

		// Retrieve the converted temp
		WebElement cTemp = driver.findElement(By.tagName("h2"));
		String celsiusTemp = parseTemp(cTemp.getText());

		// Confirm that 213F was converted to 100.6C.
		assertEquals("Checking for 213F -> 100.6C", "100.6", celsiusTemp);

	}
	
	@Test
	public void testPausedCharleyAnd45FConversion() {
		
        long end = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < end) {
        	//pause for 10 sec. 
        }
        
		driver.get("http://adnan.appspot.com/testing-lab-login.html");

		// Enter the user id
		WebElement userId = driver.findElement(By.name("userId"));
		userId.clear();
		userId.sendKeys("charley");

		// Enter the password
		WebElement password = driver.findElement(By.name("userPassword"));
		password.clear();
		password.sendKeys("china");
		password.submit();

		// Enter the temp
		WebElement fTemp = driver.findElement(By.name("farenheitTemperature"));
		fTemp.clear();
		fTemp.sendKeys("45");
		fTemp.submit();

		// Retrieve the converted temp
		WebElement cTemp = driver.findElement(By.tagName("h2"));
		String celsiusTemp = parseTemp(cTemp.getText());

		// Confirm that 45F was converted to 7.22C.
		assertEquals("Checking for 45F -> 7.22C", "7.22", celsiusTemp);

	}

}
