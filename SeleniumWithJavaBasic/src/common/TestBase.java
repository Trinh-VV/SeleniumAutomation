package common;

import java.util.List;

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
		driver.manage().window().maximize();
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

	public void inputDropbox(String tagDropbox, int index) {
		WebElement dropBox = driver.findElement(By.xpath(tagDropbox));
		Select years = new Select(dropBox);
		index--;
		years.selectByIndex(index);
	}
	public void inputDropbox(String tagDropbox, String value) {
		WebElement dropBox = driver.findElement(By.xpath(tagDropbox));
		Select years = new Select(dropBox);
		years.selectByValue(value);
	}
	
	public String onSelectDropbox(String tagOptions, int index) {
		List<WebElement> e = driver.findElements(By.xpath(tagOptions));
		String str = e.get(index).getText();
		e.get(index).click();
		return str;
	}

	public void inputDay(String tagDays, int date) {
		List<WebElement> visibleDays = driver.findElements(By.xpath(tagDays));
		for (int i = 1; i <= visibleDays.size(); i++) {
			if (i==date)
				visibleDays.get(i).click();
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
