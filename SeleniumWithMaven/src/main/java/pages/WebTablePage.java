package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class WebTablePage extends Page {
	TestBase testBase = new TestBase(driver);
	public By locDataTable = By.xpath("//div[@role='row' and contains(@class,'rt-tr ') and not(contains(@class,'padRow'))]");
	public By locSearchBox = By.id("searchBox");
	public By locBtnAdd = By.id("addNewRecordButton");
	public By locFirstName = By.id("firstName");
	public By locLastName = By.id("lastName");
	public By locEmail = By.id("userEmail");
	public By locAge = By.id("age");
	public By locSalary = By.id("salary");
	public By locDepartment = By.id("department");
	public By locBtnSubmit = By.id("submit");

	public WebTablePage(WebDriver dr) {
		super(dr);
	}

	public boolean checkSearchTable(String keySearch) {
		boolean rsSearch = true;
		inputTextSearch(keySearch);
		List<WebElement> rowsSearch = testBase.getListElement(locDataTable);
		for (WebElement e : rowsSearch) {
			if (!(e.getText().toLowerCase()).contains(keySearch.toLowerCase())) {
				rsSearch = false;
				break;
			}
		}
		return rsSearch;
	}

	public boolean addNewRecord(String firstName, String lastName, String email, String age, String salary, String department) {
		boolean resultAdd = false;
		testBase.getElement(locSearchBox).sendKeys(Keys.BACK_SPACE);
		deleteAllCurrentData();

		// Click button Add
		testBase.clickToElement(locBtnAdd);

		// Input data
		testBase.sendKeyToElement(locFirstName, firstName);
		testBase.sendKeyToElement(locLastName, lastName);
		testBase.sendKeyToElement(locEmail, email);
		testBase.sendKeyToElement(locAge, age);
		testBase.sendKeyToElement(locSalary, salary);
		testBase.sendKeyToElement(locDepartment, department);

		// Submit data
		testBase.clickToElement(locBtnSubmit);

		// Check submit
		List<WebElement> records = testBase.getListElement(locDataTable);
		if (records.size() != 0) {
			resultAdd = (records.get(0).getText().replace("\n", "").trim())
					.equals(firstName+lastName+email+age+salary+department);
		}
		return resultAdd;
	}

	public void deleteAllCurrentData() {
		List<WebElement> rowsTable = testBase.getListElement(locDataTable);
		for (int i = 1; i <= rowsTable.size(); i++) {
			driver.findElement(By.xpath("//*[@id='delete-record-" + i + "']")).click();
		}
	}

	public void inputTextSearch(String keySearch) {
		testBase.sendKeyToElement(locSearchBox, keySearch);
	}
}
