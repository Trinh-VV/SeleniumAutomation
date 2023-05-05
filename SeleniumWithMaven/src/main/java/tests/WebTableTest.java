package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ElementsPage;
import pages.HomePage;
import pages.WebTablePage;

public class WebTableTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	WebTablePage webTablePage;

	@BeforeTest
	public void openTextBoxPage() {
		openWebsite();
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickElementsMenu();
		webTablePage = elementsPage.clickWebTableMenu();
	}

	@Test(priority = 1, description = "TC1 - Search data")
	public void submitSuccessfully() throws InterruptedException {
		Assert.assertTrue(webTablePage.checkSearchTable("t"));
	}

	@Test(priority = 2, description = "TC2 - Add a record")
	public void submitFail() throws InterruptedException {
		Assert.assertEquals(webTablePage.addNewRecord(), false);
	}
}
