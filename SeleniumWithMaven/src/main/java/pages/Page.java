package pages;

import org.openqa.selenium.WebDriver;

import common.TestBase;

public class Page {
	/**
	 * This is a class which contains all common method a Web site
	 */
	public WebDriver driver;
	public TestBase testBase = new TestBase(driver);

	public Page(WebDriver dr) {
		this.driver = dr;
	}
	
	public void clearData() {
		driver.navigate().refresh();
	}

}
