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
	public String tagResultTable = "//div[@class='rt-tbody']";
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
	public String txtKeySearch = "2";

	public void testTC1() {
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuWebTable);
		tb.onInput(tagBoxSearch, txtKeySearch);

		boolean tc = true;
		List<WebElement> rows = tb.driver.findElements(By.xpath(tagResultTable));
		// * Chưa lấy dc list item
		for (int i = 0; i < rows.size(); i++) {
			if (!(rows.get(i).getText().replace("\n", " ").toLowerCase()).contains(txtKeySearch.toLowerCase())) {
				tc = false;
			}
			System.out.println(rows.get(i).getText().replace("\n", " "));
		}
		System.out.println(tc ? "TC1 PASS" : "TC1 FAIL");
	}

	public void testTC2() throws InterruptedException {
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuWebTable);
		String tagBtnDelete = "";

		List<WebElement> rows = tb.driver.findElements(By.xpath(tagResultTable));
		for (int i = 3; i >= 1; i--) {
			tagBtnDelete = tagDeleteFirst + i + tagDeleteLast;
			tb.onClick(tagBtnDelete);
			Thread.sleep(500);
		}

		tb.onClick(tagBtnAdd);
		Thread.sleep(500);
		tb.onInput(tagInputFirstName, "Vo");
		tb.onInput(tagInPutLastName, "Trinh");
		tb.onInput(tagInPutEmail, "trinh@gmail.com");
		tb.onInput(tagInPutAge, "30");
		tb.onInput(tagInPutSalary, "20");
		tb.onInput(tagInPutDepartment, "QA");
		tb.onClick(tagBtnSubmit);
		String strInput = "Vo Trinh 30 trinh@gmail.com 20 QA";

		String actualResult = tb.onGetText(tagResultTable).replace("\n", " ").trim();
		if (strInput.equals(actualResult)) {
			System.out.println("TC2 PASS: Add success");
		} else {
			System.out.println("TC2 FAIL: Add fail");
		}

	}

}
