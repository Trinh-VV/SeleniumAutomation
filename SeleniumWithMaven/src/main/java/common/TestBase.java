
package common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	/**
	 * This is a class which contains
	 */
	public WebDriver dr;

	public TestBase(WebDriver driver) {
		this.dr = driver;
	}

	public static final long LONG_TIME_OUT = 30;
	public static final long SHORT_TIME_OUT = 5;
	public static final String PATH_WEBDRIVER = "I:\\02AutomationTraining\\05Project\\03Git\\SeleniumAutomation\\SeleniumWithJavaBasic\\src\\divers\\chromedriver.exe";

	public void openWebWithSigleBrowser(String browserName) {
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", PATH_WEBDRIVER);
			dr = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			// TODO
		} else if (browserName.equals("Edge")) {
			// TODO
		}
		dr.get("https://demoqa.com/");
		dr.manage().window().maximize();
	}

	public WebElement waitForDisplay(long timeOutInSecond, By elementLocator) {
		return new WebDriverWait(dr, Duration.ofSeconds(timeOutInSecond)).until(dri -> dr.findElement(elementLocator));
		
	}

	public void waitForTime(long timeOutInMilliSecond) {
		dr.manage().timeouts().implicitlyWait(Duration.ofMillis(timeOutInMilliSecond));
	}

	public WebElement getElement(By locator) {
		return dr.findElement(locator);
	}

	public By getLocatorByXpath(String xpathElement) {
		return By.xpath(xpathElement);
	}

	public By getLocatorById(String id) {
		return By.id(id);
	}

	public By getLocatorByClass(String className) {
		return By.className(className);
	}

	public List<WebElement> getListElement(By locator) {
		return dr.findElements(locator);
	}

	public boolean isDisplay(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public void sendKeyToElement(By locator, String txt) {
		waitForDisplay(SHORT_TIME_OUT, locator);
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(txt);
	}

	public String getTextElement(By locator) {
		return getElement(locator).getText();
	}

	public void clickToElement(By locator) {
		waitForDisplay(SHORT_TIME_OUT, locator);
		getElement(locator).click();
	}

	public boolean checkFormatEmail(String email) {
		// Regex error?????
		return email.contains(".") & email.contains("@");
	}

	public void uploadFile(By locator, String pathFile) {
		waitForDisplay(SHORT_TIME_OUT, locator);
		getElement(locator).sendKeys(pathFile);
	}
	
	public boolean checkColorBorderAfterFinishAniation(By locator, String atributeName, long timeSecond, String excpectedColorCode ) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(timeSecond));
		wait.until(ExpectedConditions.attributeToBe(element, atributeName, excpectedColorCode));
		return element.getCssValue(atributeName).equals(excpectedColorCode);
	}

	public void scrollToEndPage() {
		((JavascriptExecutor) dr).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToElement(By locator) {
		waitForDisplay(SHORT_TIME_OUT, locator);
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public String selectItemDropdownlist(By locator, int index) {
		waitForDisplay(SHORT_TIME_OUT, locator);
		String selectedText = getListElement(locator).get(index).getText();
		getListElement(locator).get(index).click();
		return selectedText;
	}

	public void selectItemDropdownByValue(By locator, String value) {
		WebElement dropBox = getElement(locator);
		Select sl = new Select(dropBox);
		sl.selectByValue(value);
	}

	public void selectItemDropdownByIndex(By locator, int index) {
		WebElement dropBox = getElement(locator);
		Select sl = new Select(dropBox);
		sl.selectByIndex(index);
	}

	public void refreshCurrentPage() {
		dr.navigate().refresh();
	}
	
	public void zoomInPage() {
		JavascriptExecutor js = (JavascriptExecutor) dr;
		js.executeScript("document.body.style.zoom='65%';");
	}

}
