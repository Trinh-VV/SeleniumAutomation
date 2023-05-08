package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.TestBase;

public class ElementsPage extends Page {
	TestBase testBase = new TestBase(driver);
	public By locTextBox = By.xpath("//span[text()='Text Box']/parent::li");
	public By locCheckBox = By.xpath("//span[text()='Check Box']/parent::li");
	public By locWebTable = By.xpath("//span[text()='Web Tables']/parent::li");
	public By locPracticeForm = By.xpath("//span[text()='Practice Form']/parent::li");

	public ElementsPage(WebDriver dr) {
		super(dr);
	}

	public TextBoxPage clickTextBoxMenu() {
		testBase.clickToElement(locTextBox);
		return new TextBoxPage(driver);
	}

	public CheckBoxPage clickCheckBoxMenu() {
		testBase.clickToElement(locCheckBox);
		return new CheckBoxPage(driver);
	}

	public WebTablePage clickWebTableMenu() {
		testBase.clickToElement(locWebTable);
		return new WebTablePage(driver);
	}

	public PracticeFormPage clickPracticeFormMenu() {
		testBase.clickToElement(locPracticeForm);
		return new PracticeFormPage(driver);
	}
}
