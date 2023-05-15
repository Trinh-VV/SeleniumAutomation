package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.LocatorManager;
import common.TestBase;
import common.Utils;

public class TextBoxPage extends Page {

	TestBase testBase = new TestBase(driver);
	public static final String PATH_DATA_TESTCASES = "src\\main\\resources\\TestCasesTextBox.csv";
	public static final String PATH_DATA_LOCATORS = "src\\main\\resources\\LocatorsTextBox.csv";
	public static final String COLOR_RED_CODE = "rgba(255, 0, 0, 1)";
	public static final String ATRIBUTE_NAME = "border-bottom-color";

	LocatorManager locatorManager = new LocatorManager(PATH_DATA_LOCATORS);
	public By locFullName = locatorManager.getByLocator("fullName");
	public By locEmail = locatorManager.getByLocator("email");
	public By locCurrentAddress = locatorManager.getByLocator("currentAddress");
	public By locPermanentAddress = locatorManager.getByLocator("permanentAddress");
	public By locFullName_Output = locatorManager.getByLocator("fullName_output");
	public By locEmail_Output = locatorManager.getByLocator("email_output");
	public By locCurrentAddress_Output = locatorManager.getByLocator("currentAddress_output");
	public By locPermanentAddress_Output = locatorManager.getByLocator("permanentAddress_output");
	public By locSubmit = locatorManager.getByLocator("submit");

	String name = "";
	String email = "";
	String currentAddress = "";
	String permanentAddress = "";

	public TextBoxPage(WebDriver dr) {
		super(dr);
	}
	
	public boolean checkSubmitData(String idTestCase) {
		boolean result = true;
		System.out.println("__" + locatorManager.getByLocator("fullName"));
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

	public void readDataTestCase(String testCaseId) {
		List<String[]> testData = Utils.readTestData(PATH_DATA_TESTCASES);
		for (int i = 0; i < testData.size(); i++) {
			if (testCaseId.substring(2).equals(i + "")) {
				name = testData.get(i)[1];
				email = testData.get(i)[2];
				currentAddress = testData.get(i)[3];
				permanentAddress = testData.get(i)[4];
			}
		}
	}

	public boolean checkDataSubmitText(By locator, String inputText) {
		String actualText = Utils.cutStringFromSubString(testBase.getTextElement(locator), ":");
		return inputText.equals(actualText);
	}

}
