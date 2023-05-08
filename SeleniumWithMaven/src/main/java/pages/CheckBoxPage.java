package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.TestBase;

public class CheckBoxPage extends Page {
	TestBase testBase = new TestBase(driver);
	public By locCheckBoxHome = By.xpath("//span[@class='rct-checkbox']");
	public By locCheckBoxTreeHome = By.id("tree-node-home");
	public By locToggleOpenClose = By.xpath("//button[@title='Toggle']"); 
	public By locCheckBoxDesktop = By.id("tree-node-desktop");
	public By locCheckBoxDocuments = By.id("tree-node-documents");
	public By locCheckBoxDownloads = By.id("tree-node-downloads");
	public By locTextResult = By.id("result");

	public CheckBoxPage(WebDriver dr) {
		super(dr);
	}

	public boolean checkTextShown() {
		// Select home check box
		testBase.clickToElement(locCheckBoxHome);
		// Check text shown
		String textShown = testBase.getTextElement(locTextResult).replace("\n", " ");
		String textExpected = "You have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordFile excelFile";
		System.out.println(textShown);
		System.out.println(textExpected);
		return textShown.equals(textExpected);
	}

	public boolean isSelectedCheckBoxHome() {
		return testBase.isSelected(locCheckBoxTreeHome);
	}

	public boolean checkSelectedCheckBox() {
		testBase.clickToElement(locToggleOpenClose);
		boolean checkBoxDesktop = testBase.isSelected(locCheckBoxDesktop);
		boolean checkBoxDocuments = testBase.isSelected(locCheckBoxDocuments);
		boolean checkBoxDownloads = testBase.isSelected(locCheckBoxDownloads);
		return checkBoxDesktop & checkBoxDocuments & checkBoxDownloads;
	}
}
