package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTablePage extends Page {

	public static final String TAG_TABLE_DATA = "//div[@role='row' and contains(@class,'rt-tr ') and not(contains(@class,'padRow'))]";
	public static final String TAG_SEARCH_BOX = "//input[@id='searchBox']";

	public WebTablePage(WebDriver dr) {
		super(dr);
	}

	public boolean checkSearchTable(String keySearch) {
		boolean rsSearch = true;
		inputTextSearch(keySearch);
		List<WebElement> rowsSearch = getListElement(TAG_TABLE_DATA);
		for (WebElement e : rowsSearch) {
			if (!(e.getText().toLowerCase()).contains(keySearch.toLowerCase())) {
				rsSearch = false;
				break;
			}
		}
		return rsSearch;
	}

	public boolean addNewRecord() throws InterruptedException {
		boolean resultAdd = false;
		getElement(TAG_SEARCH_BOX).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		deleteAllCurrentData();
		Thread.sleep(200);
		
		// Click button Add
		clickToElement("//button[@id='addNewRecordButton']");
		Thread.sleep(200);

		// Input data
		sendKeyToElement("//input[@id='firstName']", "Vo");
		sendKeyToElement("//input[@id='lastName']", "Trinh");
		sendKeyToElement("//input[@id='userEmail']", "trinh@gmail.com");
		sendKeyToElement("//input[@id='age']", "30");
		sendKeyToElement("//input[@id='salary']", "1000");
		sendKeyToElement("//input[@id='department']", "QA");

		// Submit data
		clickToElement("//button[@id='submit']");

		// Check submit
		List<WebElement> records = getListElement(TAG_TABLE_DATA);
		if (records.size() != 0) {
			resultAdd = (records.get(0).getText().replace("\n", " ").trim())
					.equals("Vo Trinh trinh@gmail.com 30 1000 QA");
		}
		return resultAdd;
	}

	public void deleteAllCurrentData() throws InterruptedException {
		List<WebElement> rowsTable = getListElement(TAG_TABLE_DATA);
			for (int i = 1; i <= rowsTable.size(); i++) {
				clickToElement("//*[@id='delete-record-" + i + "']");
				Thread.sleep(200);
		}
	}
	
	public void inputTextSearch(String keySearch) {
		sendKeyToElement(TAG_SEARCH_BOX, keySearch);
	}
}
