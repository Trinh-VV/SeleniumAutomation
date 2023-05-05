package pages;

import org.openqa.selenium.WebDriver;

public class ElementsPage extends Page{

	public ElementsPage(WebDriver dr) {
		super(dr);
	}
	public TextBoxPage clickTextBoxMenu() {
		getElement("//span[text()='Text Box']/parent::li").click();
		return new TextBoxPage(driver);
	}
	
	public CheckBoxPage clickCheckBoxMenu() {
		getElement("//span[text()='Check Box']/parent::li").click();
		return new CheckBoxPage(driver);
	}

	public WebTablePage clickWebTableMenu() {
		getElement("//span[text()='Web Tables']/parent::li").click();
		return new WebTablePage(driver);
	}
	
	public PracticeFormPage clickPracticeFormMenu() {
		getElement("//span[text()='Practice Form']/parent::li").click();
		return new PracticeFormPage(driver);
	}
}
