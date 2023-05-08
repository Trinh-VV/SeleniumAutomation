package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import common.TestBase;

public class TextBoxPage extends Page {

	TestBase testBase = new TestBase(driver);

	public String xpathFullName = "//input[@id='userName']";
	public String xpathEmail = "//input[@id='userEmail']";
	public String xpathCurrentAddress = "//textArea[@id='currentAddress']";
	public String xpathPermanentAddress = "//textArea[@id='permanentAddress']";
	public String xpathFullNameOut = "//p[@id='name']";
	public String xpathEmailOut = "//p[@id='email']";
	public String xpathCurrentAddressOut = "//p[@id='currentAddress']";
	public String xpathPermanentAddressOut = "//p[@id='permanentAddress']";
	public String xpathBtnSubmit = "//button[@id='submit']";

	public By locFullName = By.id("userName");
	public By locEmail = By.id("userEmail");
	public By locCurrentAddress = By.xpath("//textArea[@id='currentAddress']");
	public By locPermanentAddress = By.xpath("//textArea[@id='permanentAddress']");
	public By locFullName_Output;
	public By locEmail_Output;
	public By locCurrentAddress_Output;
	public By locPermanentAddress_Output;
	public By locSubmit = By.xpath("//button[@id='submit']");

	public TextBoxPage(WebDriver dr) {
		super(dr);
	}

	public boolean submitTextBox(String name, String email, String currentAddress, String permanentAddress)
			throws InterruptedException {
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
		boolean checkName = checkDataSubmitText(locFullName_Output, name);
		boolean checkCurrentAddress = checkDataSubmitText(locCurrentAddress_Output, currentAddress);
		boolean checkPermanentAddress = checkDataSubmitText(locPermanentAddress_Output, permanentAddress);
		boolean checkEmail = checkDataSubmitEmail(xpathEmailOut, email);
		return checkName & checkEmail & checkCurrentAddress & checkPermanentAddress;
	}

	public boolean checkDataSubmitEmail(String tagElement, String inputEmail) {
		boolean checkEmail = true;
		if (testBase.checkFormatEmail(inputEmail)) {
			checkEmail = checkDataSubmitEmail("\"//p[@id='name']\"", inputEmail);
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
