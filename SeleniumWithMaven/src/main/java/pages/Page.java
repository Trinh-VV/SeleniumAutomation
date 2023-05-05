package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Page {
	/**
	 * This is a class which contains all common method a Web site
	 */
	public WebDriver driver;

	public Page(WebDriver dr) {
		this.driver = dr;
	}

	public WebElement getElement(String tagElement) {
		return driver.findElement(By.xpath(tagElement));
	}

	public List<WebElement> getListElement(String tagElement) {
		return driver.findElements(By.xpath(tagElement));
	}

	public boolean isDisplay(String tagElement) {
		return getElement(tagElement).isDisplayed();
	}

	public boolean isSelected(String tagElement) {
		return getElement(tagElement).isSelected();
	}

	public void sendKeyToElement(String tagElement, String txt) {
		WebElement element = getElement(tagElement);
		element.clear();
		element.sendKeys(txt);
	}

	public String getTextElement(String tagElement) {
		return getElement(tagElement).getText();
	}
//	public String getTextElement(String tagElement) {
//		String str = getElement(tagElement).getText();
//		int i = str.indexOf(":");
//		return str.substring(i + 1, str.length());
//	}

	public void clickToElement(String tagElement) {
		getElement(tagElement).click();
	}

	public boolean checkFormatEmail(String email) {
		// Regex error
		return email.equals(".") & email.equals("@");
	}

	public void uploadFile(String tagElement, String pathFile) {
		getElement(tagElement).sendKeys(pathFile);
	}

	public void scrollToEndPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToElement(String tagElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(tagElement));
	}

	public String selectItemDropdownlist(String tagDropdown, int index) {
		String selectedText = getListElement(tagDropdown).get(index).getText();
		getListElement(tagDropdown).get(index).click();
		return selectedText;
	}

	public void selectItemDropdownByValue(String tagDropdown, String value) {
		WebElement dropBox = getElement(tagDropdown);
		Select sl = new Select(dropBox);
		sl.selectByValue(value);
	}

	public void selectItemDropdownByIndex(String tagDropdown, int index) {
		WebElement dropBox = getElement(tagDropdown);
		Select sl = new Select(dropBox);
		sl.selectByIndex(index);
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}

}
