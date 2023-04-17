package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class CheckBoxTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//*[@id=\"app\"]/div/div/div[2]/div/div[1]";
	public String tagMenuCheckbox = "//*[@id=\"item-1\"]";
	public String tagHomeCheckbox = "//*[@id=\"tree-node\"]/ol/li/span/label/span[1]";
	public String tagResultSelected = "//*[@id=\"result\"]";
	public String tagBtnXtend = "//*[@id=\"tree-node\"]/ol/li/span/button";
	public String tagOptionLevel2 = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/ol/li/ol";

	public void TestTC1() {
		String result = "You have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordFile excelFile";
		String expectedOptionLevel2 = "Desktop Documents Downloads";
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuCheckbox);

		// Confirm Home check box is unchecked
		boolean homeStatus = tb.checkStatusCheckbox(tagHomeCheckbox);
		System.out.println(homeStatus ? "TC1_1 - Fail: default is ON" : "TC1_1 - Pass default is OFF");

		// Check Text is shown
		tb.onClick(tagHomeCheckbox);
		String actualResult = tb.onGetText(tagResultSelected).replace("\n", " ");
		System.out.println(actualResult);
		if (actualResult.equals(result)) {
			System.out.println("TC1_2 - Pass: Text is correct");
		} else {
			System.out.println("TC1_2 - Fail: Text is incorrect");
		}

		// Check click btn [>]
		tb.onClick(tagBtnXtend);
		WebElement element = tb.driver.findElement(By.xpath(tagOptionLevel2));
		String actualOptionLevel2 = element.getText().replace("\n", " ");
		if (actualOptionLevel2.equals(expectedOptionLevel2)) {
			System.out.println("TC1_3 - Pass: "+expectedOptionLevel2);
		} else {
			System.out.println("TC1_3 - Fail: "+expectedOptionLevel2);
		}
	}
}
