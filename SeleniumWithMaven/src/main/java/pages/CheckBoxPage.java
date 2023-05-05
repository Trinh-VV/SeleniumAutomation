package pages;

import org.openqa.selenium.WebDriver;

public class CheckBoxPage extends Page {

	public CheckBoxPage(WebDriver dr) {
		super(dr);
	}

	public boolean checkTextShown(String tag) {
		// Select home check box
		clickToElement("//span[@class='rct-checkbox']");
		// Check text shown
		String textShown = getTextElement(tag).replace("\n", " ");
		String textExpected = "You have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordFile excelFile";
		System.out.println(textShown);
		System.out.println(textExpected);
		return textShown.equals(textExpected);
	}

	public boolean checkSelectedCheckBox() {
		clickToElement("//button[@title='Toggle']");
		boolean checkBoxDesktop = isSelected("//input[@id='tree-node-desktop']");
		boolean checkBoxDocuments = isSelected("//input[@id='tree-node-documents']");
		boolean checkBoxDownloads = isSelected("//input[@id='tree-node-downloads']");
		return checkBoxDesktop & checkBoxDocuments & checkBoxDownloads;
	}
}
