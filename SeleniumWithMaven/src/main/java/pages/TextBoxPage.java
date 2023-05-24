package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.TestBase;
import common.Utils;

public class TextBoxPage extends Page {

	TestBase testBase = new TestBase(driver);
	public static final String PATH_DATA_TESTCASES = "src\\main\\resources\\TestCasesTextBox.csv";
	public static final String PATH_DATA_TEST = "test_data\\TestData.xlsx";
	public static final String PATH_DATA_LOCATORS = "src\\main\\resources\\LocatorsTextBox.csv";
	public static final String COLOR_RED_CODE = "rgba(255, 0, 0, 1)";
	public static final String ATRIBUTE_NAME = "border-bottom-color";
	public By locFullName = testBase.getLocatorById("userName");
	public By locEmail = testBase.getLocatorById("userEmail");
	public By locCurrentAddress = testBase.getLocatorByXpath("//textarea[@id='currentAddress']");
	public By locPermanentAddress = testBase.getLocatorByXpath("//textarea[@id='permanentAddress']");
	public By locFullName_Output = testBase.getLocatorById("name");
	public By locEmail_Output = testBase.getLocatorById("email");
	public By locCurrentAddress_Output = testBase.getLocatorByXpath("//p[@id='currentAddress']");
	public By locPermanentAddress_Output = testBase.getLocatorByXpath("//p[@id='permanentAddress']");
	public By locSubmit = testBase.getLocatorById("submit");

	String name = "";
	String email = "";
	String currentAddress = "";
	String permanentAddress = "";

	public TextBoxPage(WebDriver dr) {
		super(dr);
	}
	
	public boolean checkSubmitData(String idTestCase) throws IOException {
		boolean result = true;
		readDataTestCase(idTestCase);

		// Input data
		testBase.sendKeyToElement(locFullName, name);
		testBase.sendKeyToElement(locEmail, email);
		testBase.sendKeyToElement(locCurrentAddress, currentAddress);
		testBase.sendKeyToElement(locPermanentAddress, permanentAddress);
		testBase.scrollToElement(locSubmit);

		// Submit data
		testBase.clickToElement(locSubmit);

		// Check submit data
		if (testBase.checkFormatEmail(email)) {
			boolean checkName = checkDataSubmitText(locFullName_Output, name);
			boolean checkCurrentAddress = checkDataSubmitText(locCurrentAddress_Output, currentAddress);
			boolean checkPermanentAddress = checkDataSubmitText(locPermanentAddress_Output, permanentAddress);
			boolean checkEmail = checkDataSubmitText(locEmail_Output, email);
			result = checkName & checkEmail & checkCurrentAddress & checkPermanentAddress;
		} else {
			result = testBase.checkColorBorderAfterFinishAniation(locEmail, ATRIBUTE_NAME, TestBase.SHORT_TIME_OUT, COLOR_RED_CODE);
		}
		return result;
	}

	public void readDataTestCase(String testCaseId) throws IOException {
		String[][] arrDataExcel = Utils.getDataFromExcel(PATH_DATA_TEST,"TextBoxTest");
		for (int i = 0; i < arrDataExcel.length; i++) {
			if (arrDataExcel[i][0].equals(testCaseId)) {
				name = arrDataExcel[i][1];
				email = arrDataExcel[i][2];
				currentAddress = arrDataExcel[i][3];
				permanentAddress = arrDataExcel[i][4];
			}
		}
	}

	public boolean checkDataSubmitText(By locator, String inputText) {
		String actualText = Utils.cutStringFromSubString(testBase.getTextElement(locator), ":");
		return inputText.equals(actualText);
	}

}
