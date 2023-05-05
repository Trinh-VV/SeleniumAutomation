package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class TextBoxPage extends Page {

	public TextBoxPage(WebDriver dr) {
		super(dr);
	}

	public boolean submitTextBox(String name, String email, String currentAddress, String permanentAddress)
			throws InterruptedException {
		// Input data
		sendKeyToElement("//input[@id='userName']", name);
		sendKeyToElement("//input[@id='userEmail']", email);
		sendKeyToElement("//textarea[@id='currentAddress']", currentAddress);
		sendKeyToElement("//textarea[@id='permanentAddress']", permanentAddress);
		Thread.sleep(100);
		scrollToElement("//button[@id='submit']");

		// Submit data
		getElement("//button[@id='submit']").click();

		// Check submit data
		boolean checkName = checkDataSubmitText("//p[@id='name']", name);
		boolean checkCurrentAddress = checkDataSubmitText("//p[@id='currentAddress']", currentAddress);
		boolean checkPermanentAddress = checkDataSubmitText("//p[@id='permanentAddress']", permanentAddress);
		boolean checkEmail = checkDataSubmitEmail("//p[@id='email']", email);
		return checkName & checkEmail & checkCurrentAddress & checkPermanentAddress;
	}

	public boolean checkDataSubmitEmail(String tagElement, String inputEmail) {
		boolean checkEmail = true;
		if (checkFormatEmail(inputEmail)) {
			checkEmail = checkDataSubmitEmail("\"//p[@id='name']\"", inputEmail);
		} else {
			checkColorBorderEmail();
		}
		System.out.println(checkEmail?"TTT":"FFF");
		return checkEmail;
	}

	public String getActualTextSubmit(String tagElement) {
		String str = getTextElement(tagElement);
		String actualText = "";
		if (str != "") {
			int i = str.indexOf(":");
			actualText = str.substring(i + 1, str.length());
		}
		return actualText;
	}

	public boolean checkDataSubmitText(String tagElement, String inputText) {
		return inputText.equals(getActualTextSubmit(tagElement));
	}

	public boolean checkColorBorderEmail() {
		WebElement element = getElement("//*[@id='userEmail']");
		return Color.fromString(element.getCssValue("border-bottom-color")).asHex().equals("#ff0000");
	}
}
