package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class WebTableTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//*[@id=\"app\"]/div/div/div[2]/div/div[1]";
	public String tagMenuWebTable = "//*[@id=\"item-3\"]";
	public String tagBoxSearch = "//*[@id=\"searchBox\"]";
	public String tagBtnSearch = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/div";
	public String tagListData = "//div[@role='row' and contains(@class,'rt-tr ') and not(contains(@class,'padRow'))]";
	public String tagDeleteFirst = "//*[@id='delete-record-";
	public String tagDeleteLast = "']";
	public String tagBtnAdd = "//*[@id=\"addNewRecordButton\"]";
	public String tagInputFirstName = "//*[@id='firstName']";
	public String tagInPutLastName = "//*[@id='lastName']";
	public String tagInPutEmail = "//*[@id='userEmail']";
	public String tagInPutAge = "//*[@id='age']";
	public String tagInPutSalary = "//*[@id='salary']";
	public String tagInPutDepartment = "//*[@id='department']";
	public String tagBtnSubmit = "//*[@id=\"userForm\"]/div[7]/div";
	public String txtKeySearch = "234";

	public void testTC1() throws InterruptedException {
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
