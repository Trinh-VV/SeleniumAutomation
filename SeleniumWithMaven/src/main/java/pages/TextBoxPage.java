package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import common.Elements_TextBox;
import common.TestBase;

public class TextBoxPage extends Page {

	TestBase testBase = new TestBase(driver);

	public By locFullName = By.id("userName");
	public By locEmail = By.id("userEmail");
	public By locCurrentAddress = By.xpath("//textArea[@id='currentAddress']");
	public By locPermanentAddress = By.xpath("//textArea[@id='permanentAddress']");
	public By locFullName_Output;
	public By locEmail_Output;
	public By locCurrentAddress_Output;
	public By locPermanentAddress_Output;
	public By locSubmit = By.xpath("//button[@id='submit']");
	public static final String PATH_DATA_TEXTBOX = "I:\\02AutomationTraining\\05Project\\03Git\\SeleniumAutomation\\SeleniumWithMaven\\src\\main\\resources\\TextBoxInputData.csv";
	public ArrayList<Elements_TextBox> listTestCase = new ArrayList<Elements_TextBox>();
	Elements_TextBox data;
	String idTestCase = "";
	String name = "";
	String email = "";
	String currentAddress = "";
	String permanentAddress = "";

	public TextBoxPage(WebDriver dr) {
		super(dr);
	}

	public boolean submitTextBox(String idTestCase) throws InterruptedException {
		boolean result = true;
		readTestCasesFromFile();
		setDataForTestCase(idTestCase);

		// Input data
		testBase.sendKeyToElement(locFullName, name);
		testBase.sendKeyToElement(locEmail, email);
		testBase.sendKeyToElement(locCurrentAddress, currentAddress);
		testBase.sendKeyToElement(locPermanentAddress, permanentAddress);
		Thread.sleep(100);
		testBase.scrollToElement(locSubmit);

		// Submit data
		testBase.clickToElement(locSubmit);
		locFullName_Output = By.xpath("//p[@id='name']");
		locEmail_Output = By.xpath("//p[@id='email']");
		locCurrentAddress_Output = By.xpath("//p[@id='currentAddress']");
		locPermanentAddress_Output = By.xpath("//p[@id='permanentAddress']");

		// Check submit data
		if (testBase.checkFormatEmail(email)) {
			boolean checkName = checkDataSubmitText(locFullName_Output, name);
			boolean checkCurrentAddress = checkDataSubmitText(locCurrentAddress_Output, currentAddress);
			boolean checkPermanentAddress = checkDataSubmitText(locPermanentAddress_Output, permanentAddress);
			boolean checkEmail = checkDataSubmitText(locEmail_Output, email);
			result = checkName & checkEmail & checkCurrentAddress & checkPermanentAddress;
		} else {
			result = checkDataSubmitEmail(locEmail_Output, email);
		}
		return result;
	}

	private void setDataForTestCase(String idTestCase2) {
		int id = Integer.parseInt(idTestCase.substring(2));
		name = listTestCase.get(id).fullName;
		email = listTestCase.get(id).email;
		currentAddress = listTestCase.get(id).currentAddress;
		permanentAddress = listTestCase.get(id).permanentAddress;
	}

	private void readTestCasesFromFile() {
		File file = new File(PATH_DATA_TEXTBOX);
		try {
			List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			for (String line : allText) {
				addDataToListTestCase(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDataToListTestCase(String lineData) {
		String[] splits = lineData.split(",");
		data = new Elements_TextBox();
		data.setId(splits[0]);
		data.setFullName(splits[1]);
		data.setEmail(splits[2]);
		data.setCurrentAddress(splits[3]);
		data.setPermanentAddress(splits[4]);
		listTestCase.add(data);
	}

	public boolean checkDataSubmitEmail(By locator, String inputEmail) {
		boolean checkEmail = true;
		if (testBase.checkFormatEmail(inputEmail)) {
			checkEmail = checkDataSubmitText(locator, inputEmail);
		} else {
			checkColorBorderEmail();
		}
		System.out.println(checkEmail ? "TTT" : "FFF");
		return checkEmail;
	}

	public String getActualTextSubmit(By locator) {
		String str = testBase.getTextElement(locator);
		String actualText = "";
		if (str != "") {
			int i = str.indexOf(":");
			actualText = str.substring(i + 1);
		}
		return actualText;
	}

	public boolean checkDataSubmitText(By locator, String inputText) {
		return inputText.equals(getActualTextSubmit(locator));
	}

	public boolean checkColorBorderEmail() {
		WebElement element = testBase.getElement(locEmail);
		return Color.fromString(element.getCssValue("border-bottom-color")).asHex().equals("#ff0000");
	}
}
