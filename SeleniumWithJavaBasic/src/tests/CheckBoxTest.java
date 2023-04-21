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
		String actualText = tb.onGetText(tagResultSelected).replace("\n", " ");
		if (actualText.equals(expectedText)) {
			System.out.println("Checkbox TC1_2: Pass shown text is correct");
		} else {
			System.out.println("Checkbox TC1_2: Fail shown text is incorrect");
		}
		Thread.sleep(1000);
		// Check click Button [>]
		tb.onClick(tagBtnXtend);
		WebElement element = tb.driver.findElement(By.xpath(tagOptionLevel2));
		String actualOptionLevel2 = element.getText().replace("\n", " ");
		if (actualOptionLevel2.equals(expectedOptionLevel2)) {
			System.out.println("Checkbox TC1_3: Pass: "+expectedOptionLevel2);
		} else {
			System.out.println("Checkbox TC1_3 - Fail: "+expectedOptionLevel2);
		}
	}
}
