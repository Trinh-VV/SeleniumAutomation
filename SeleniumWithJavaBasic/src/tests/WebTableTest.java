package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class WebTableTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//h5[text()='Elements']/ancestor::div[3]";
	public String tagMenuWebTable = "//span[text()='Web Tables']/parent::li";
	public String tagBoxSearch = "//input[@id='searchBox']";
	public String tagListData = "//div[@role='row' and contains(@class,'rt-tr ') and not(contains(@class,'padRow'))]";
	public String tagDeleteFirst = "//*[@id='delete-record-";
	public String tagDeleteLast = "']";
	public String tagBtnAdd = "//button[@id='addNewRecordButton']";
	public String tagInputFirstName = "//input[@id='firstName']";
	public String tagInPutLastName = "//input[@id='lastName']";
	public String tagInPutEmail = "//input[@id='userEmail']";
	public String tagInPutAge = "//input[@id='age']";
	public String tagInPutSalary = "//input[@id='salary']";
	public String tagInPutDepartment = "//input[@id='department']";
	public String tagBtnSubmit = "//button[@id='submit']";

	public void testTC1() throws InterruptedException {
		String txtKeySearch = "234";
		
		// Open screen
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuWebTable);
		
		// Count keySearch before input key
		List<WebElement> rowsFirst = tb.driver.findElements(By.xpath(tagListData));
		int numKeyFirst = checkContain(rowsFirst, txtKeySearch);
		
		// Count keySearch after input key
		tb.onInput(tagBoxSearch, txtKeySearch);
		List<WebElement> rowsSearch = tb.driver.findElements(By.xpath(tagListData));
		int numKeySearched = checkContain(rowsSearch, txtKeySearch);
		
		// Check search result
		if (numKeySearched == numKeyFirst && numKeyFirst == rowsSearch.size()) {
			System.out.println("Webtable TC1: Pass search");
		} else {
			System.out.println("Webtable TC1: Fail search");
		}
	}

	public void testTC2() throws InterruptedException {
		String tagBtnDelete = "";
		
		// Open screen
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuWebTable);
		
		// Delete current record
		List<WebElement> rows = tb.driver.findElements(By.xpath(tagListData));
		for (int i = 1; i <= rows.size(); i++) {
			tagBtnDelete = tagDeleteFirst + i + tagDeleteLast;
			tb.onClick(tagBtnDelete);
			Thread.sleep(300);
		}
		
		// Add new record
		tb.onClick(tagBtnAdd);
		Thread.sleep(300);
		tb.onInput(tagInputFirstName, "Vo");
		tb.onInput(tagInPutLastName, "Trinh");
		tb.onInput(tagInPutEmail, "trinh@gmail.com");
		tb.onInput(tagInPutAge, "30");
		tb.onInput(tagInPutSalary, "20");
		tb.onInput(tagInPutDepartment, "QA");
		tb.onClick(tagBtnSubmit);
		String strInput = "Vo Trinh 30 trinh@gmail.com 20 QA";
		
		// Check Add record success
		List<WebElement> records = tb.driver.findElements(By.xpath(tagListData));
		String actualResult = records.get(0).getText().replace("\n", " ").trim();
		if (strInput.equals(actualResult) && records.size() == 1) {
			System.out.println("Webtable TC2: PASS - Add success");
		} else {
			System.out.println("Webtable TC2: FAIL - Add fail");
		}
	}

	public int checkContain(List<WebElement> listE, String key) {
		int count = 0;
		for (int i = 0; i < listE.size(); i++) {
			if ((listE.get(i).getText().toLowerCase()).contains(key.toLowerCase())) {
				count++;
			}
		}
		return count;
	}
}
