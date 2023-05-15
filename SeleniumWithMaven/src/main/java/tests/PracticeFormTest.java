package tests;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ElementsPage;
import pages.HomePage;
import pages.PracticeFormPage;

public class PracticeFormTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	PracticeFormPage formPage;

	@Test(priority = 1, description = "TC1 - Submit successfully")
	public void submitSuccessfully() throws ParseException, InterruptedException {
		openTextBoxPage();
		Assert.assertTrue(formPage.inputTextForm("Vo", "Trinh", "Male", "0123456789"));
	}

	@Test(priority = 2, description = "TC2 - Submit fail")
	public void submitFail() throws ParseException, InterruptedException {
		openTextBoxPage();
		Assert.assertTrue(formPage.inputTextForm("Vo", "Trinh", "Male", ""));
	}

	public void openTextBoxPage() {
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickFormsMenu();
		formPage = elementsPage.clickPracticeFormMenu();
		testBase.zoomInPage();
	}
}
