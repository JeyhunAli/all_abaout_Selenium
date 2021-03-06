package Seleniums_Forever;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class explicitlywaitCustom2 {
	static WebDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.get("http://app.hubspot.com/");

		By emailId = By.id("username");

		WebElement ele = retryingElement(emailId);
		ele.sendKeys("test@gmail.com");

		//refreshing page in order to get stale element exception and catch block lets if it can handle it
		driver.navigate().refresh();

		ele = retryingElement(emailId);
		ele.sendKeys("test11@gmail.com");

	}

	public static WebElement retryingElement(By locator) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < 30) {

			try {
				element = driver.findElement(locator);
				break;
			}

			catch (Exception e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}
			
//
//			catch (NoSuchElementException e) {
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e1) {
//
//				}
//
//				System.out.println("element is not found in attempt : " + (attempts + 1));
//			}

			attempts++;
		}
		System.out.println("element found after attempt number " + attempts);

		return element;

	}

}
