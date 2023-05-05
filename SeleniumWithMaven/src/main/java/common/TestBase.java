
package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	/**
	 * This is a class which contains  
	 */
	public WebDriver dr;
	public static final String PATH_WEBDRIVER = "I:\\02AutomationTraining\\05Project\\03Git\\SeleniumAutomation\\SeleniumWithJavaBasic\\src\\divers\\chromedriver.exe";

	public void openWebWithSigleBrowser(String browserName) {
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", PATH_WEBDRIVER);
			dr = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			//TODO
		} else if (browserName.equals("Edge")) {
			//TODO
		}
		dr.get("https://demoqa.com/");
		dr.manage().window().maximize();
	}

}
