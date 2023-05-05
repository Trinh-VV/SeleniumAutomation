package tests;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ElementsPage;
import pages.HomePage;
import pages.PracticeFormPage;

public class PracticeFormTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	PracticeFormPage formPage;

	@BeforeTest
	public void openTextBoxPage() {
		openWebsite();
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickFormsMenu();
		formPage = elementsPage.clickPracticeFormMenu();
	}

	@Test(priority = 1, description = "TC1 - Submit successfully")
	public void submitSuccessfully() throws InterruptedException, ParseException {
		Assert.assertEquals(formPage.inputTextForm("Vo", "Trinh", "Male", "0123456789"), true);
	}

	@Test(priority = 2, description = "TC2 - Submit fail")
	public void submitFail() throws InterruptedException, ParseException {
		formPage.refreshCurrentPage();
		Assert.assertEquals(formPage.inputTextForm("Vo", "Trinh", "Male", ""), true);
	}
}
