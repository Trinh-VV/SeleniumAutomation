package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ElementsPage;
import pages.HomePage;
import pages.TextBoxPage;

public class TextBoxTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	TextBoxPage textBoxPage;

	@BeforeTest
	public void openTextBoxPage() {
		openWebsite();
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickElementsMenu();
		textBoxPage = elementsPage.clickTextBoxMenu();
	}

	@Test(priority = 1, description = "TC1 - Submit valid data")
	public void submitSuccessfully() throws InterruptedException {
		Assert.assertTrue(textBoxPage.submitTextBox("ABC", "abc@gmail.com", "Ha noi", "Viet Nam"));
	}

	@Test(priority = 2, description = "TC2 - Submit invalid format email")
	public void submitFail() throws InterruptedException {
		Assert.assertTrue(textBoxPage.submitTextBox("ABC", "abcgmail.com", "Ha noi", "Viet Nam"));
	}
}
