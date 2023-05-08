package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CheckBoxPage;
import pages.ElementsPage;
import pages.HomePage;

public class CheckBoxTest extends TestCase {

	HomePage homePage;
	ElementsPage elementsPage;
	CheckBoxPage checkBoxPage;

	@BeforeTest
	public void openTextBoxPage() {
		openWebsite();
		homePage = new HomePage(testBase.dr);
		elementsPage = homePage.clickElementsMenu();
		checkBoxPage = elementsPage.clickCheckBoxMenu();
	}

	@Test(priority = 1, description = "TC1 - Vertify Check box function")
	public void vertifyCheckBox() throws InterruptedException {

		// TC1/1 The [Home] checkBox is unchecked
		Assert.assertFalse(checkBoxPage.isSelectedCheckBoxHome());

		// TC1/2 Text shown is correct
		Assert.assertTrue(checkBoxPage.checkTextShown());

		// TC1/3 All check boxes are displayed and checked
		Assert.assertTrue(checkBoxPage.checkSelectedCheckBox());
	}
}
