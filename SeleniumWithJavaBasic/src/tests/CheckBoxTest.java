package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class CheckBoxTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//h5[text()='Elements']/ancestor::div[3]";
	public String tagMenuCheckbox = "//span[text()='Check Box']/parent::li";
	public String tagHomeCheckbox = "//span[@class='rct-checkbox']";
	public String tagResultSelected = "//div[@id='result']";
	public String tagBtnXpand = "//button[@title='Toggle']";
	public String tagOptionLevel2 = "//div[@id='tree-node']/ol/li/ol";
	public String tagOpDesktop = "//input[@id='tree-node-desktop']";
	public String tagOpDocuments = "//input[@id='tree-node-documents']";
	public String tagOpDownloads = "//input[@id='tree-node-downloads']";

	public void TestTC1() throws InterruptedException {
		String expectedText = "You have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordFile excelFile";
		String expectedOptionLevel2 = "Desktop Documents Downloads";
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuCheckbox);

		// Check default home check box is unchecked
		boolean homeStatus = tb.checkStatusCheckbox(tagHomeCheckbox);
		System.out.println(homeStatus ? "Checkbox TC1_1: Fail default is ON" : "Checkbox TC1_1: Pass default is OFF");
		// Check text is shown
		tb.onClick(tagHomeCheckbox);
		Thread.sleep(500);
		String actualText = tb.onGetText(tagResultSelected).replace("\n", " ");
		if (actualText.equals(expectedText)) {
			System.out.println("Checkbox TC1_2: Pass shown text is correct");
		} else {
			System.out.println("Checkbox TC1_2: Fail shown text is incorrect");
		}
		Thread.sleep(1000);

		// Check click Button [>]
		tb.onClick(tagBtnXpand);
		System.out.println(tb.checkStatusCheckbox(tagOpDesktop)?"ttt":"fff");
		WebElement element = tb.driver.findElement(By.xpath(tagOptionLevel2));
		String actualOptionLevel2 = element.getText().replace("\n", " ");
		if (actualOptionLevel2.equals(expectedOptionLevel2) && tb.checkStatusCheckbox(tagOpDesktop)) {
			System.out.println("Checkbox TC1_3: Pass: " + expectedOptionLevel2);
		} else {
			System.out.println("Checkbox TC1_3 - Fail: " + expectedOptionLevel2);
		}
	}
}
