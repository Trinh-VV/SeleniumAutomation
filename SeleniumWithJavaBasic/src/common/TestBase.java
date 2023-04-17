package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

public class TestBase {
	public WebDriver driver;
	public static final String PATH_WEBDRIVER = "I:\\02AutomationTraining\\05Project\\03Git\\SeleniumAutomation\\SeleniumWithJavaBasic\\src\\divers\\chromedriver.exe";
	public static final String IS_FAIL = "fail";

	public void openWebBrowser() {
		// open browser
		System.setProperty("webdriver.chrome.driver", PATH_WEBDRIVER);
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
	}

	public void onClick(String tagXpath) {
		driver.findElement(By.xpath(tagXpath)).click();
	}

	public void onInput(String tagXpath, String inputText) {
		driver.findElement(By.xpath(tagXpath)).sendKeys(inputText);
	}

	public String onGetText(String tagXpath) {
		try {
			return driver.findElement(By.xpath(tagXpath)).getText();
		} catch (Exception e) {
			return IS_FAIL;
		}
	}
	
	public boolean checkStatusCheckbox(String tagXpath) {
		WebElement element = driver.findElement(By.xpath(tagXpath));
		return element.isSelected();
	}
	
	public String checkColorCode(String tagItem, String keyItem) {
		WebElement element = driver.findElement(By.xpath(tagItem));
		return Color.fromString(element.getCssValue(keyItem)).asHex();
	}

	public void onScrollToView(String tagXpath) {
		WebElement element = driver.findElement(By.xpath(tagXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}

	
	@Override
	public String toString() {
		return super.toString();
	}
}
