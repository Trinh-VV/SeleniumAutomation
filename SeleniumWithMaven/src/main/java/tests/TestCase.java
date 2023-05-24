package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import common.TestBase;

public class TestCase {
	public WebDriver driver;
	public TestBase testBase = new TestBase(driver);

	@BeforeMethod
	@Parameters("browser")
	public void openWebsite(String browserName) {
		testBase.openWebWithSigleBrowser(browserName);
	}

	@AfterMethod
	public void closeBrowser() {
		testBase.dr.quit();
	}
}
