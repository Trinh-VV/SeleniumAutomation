package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.Elements_TextBox;
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
	
		Assert.assertTrue(textBoxPage.submitTextBox("TC1"));
	}

	@Test(priority = 2, description = "TC2 - Submit invalid format email")
	public void submitFail() throws InterruptedException {
		Assert.assertTrue(textBoxPage.submitTextBox("TC2"));
	}

	
}
