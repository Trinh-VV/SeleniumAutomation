package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ElementsPage;
import pages.HomePage;
import pages.TextBoxPage;

public class TextBoxTest extends TestCase {
	HomePage homePage;
	ElementsPage elementsPage;
	TextBoxPage textBoxPage;

	@Test(priority = 1, description = "TC1 - Submit valid data", groups = "SmokeTest")
	public void submitSuccessfully() throws InterruptedException {
		openTextBoxPage();
		Assert.assertTrue(textBoxPage.checkSubmitData("TC1"));
	}

	@Test(priority = 2, description = "TC2 - Submit invalid format email")
	public void submitFail() throws InterruptedException {
		openTextBoxPage();
		Assert.assertTrue(textBoxPage.checkSubmitData("TC2"));
	}

	public void openTextBoxPage() {
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickElementsMenu();
		textBoxPage = elementsPage.clickTextBoxMenu();
	}

}
