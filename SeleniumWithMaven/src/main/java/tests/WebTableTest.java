package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ElementsPage;
import pages.HomePage;
import pages.WebTablePage;

public class WebTableTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	WebTablePage webTablePage;

	@Test(priority = 1, description = "TC1 - Search data")
	public void submitSuccessfully() {
		openTextBoxPage();
		Assert.assertTrue(webTablePage.checkSearchTable("t"));
	}

	@Test(priority = 2, description = "TC2 - Add a record")
	public void submitFail() {
		openTextBoxPage();
		Assert.assertFalse(webTablePage.addNewRecord("Vo", "Trinh", "trinh@gmail.com", "30", "1000", "QA"));
	}

	public void openTextBoxPage() {
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickElementsMenu();
		webTablePage = elementsPage.clickWebTableMenu();
	}
}
