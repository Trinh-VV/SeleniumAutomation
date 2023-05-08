package tests;

import org.openqa.selenium.WebDriver;

import common.TestBase;

public class TestCase {
	public WebDriver driver;
	public TestBase testBase = new TestBase(driver);

	public void openWebsite() {
		testBase.openWebWithSigleBrowser("Chrome");
	}
}
