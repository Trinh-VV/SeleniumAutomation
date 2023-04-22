package tests;

import common.TestBase;

public class TextBoxTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//div[@class ='category-cards']/div[1]";
	public String tagMenuTextbox = "//div[@class='left-pannel']/div/div[1]/div/ul/li[1]";
	public String tagInputName = "//input[@id='userName']";
	public String tagInputEmail = "//input[@id='userEmail']";
	public String tagCurentAddress = "//textarea[@id='currentAddress']";
	public String tagpermanentAddress = "//textarea[@id='permanentAddress']";
	public String tagBtnSubmit = "//button[@id='submit']";
	public String tagActualName = "//p[@id='name']";
	public String tagActualEmail = "//p[@id='email']";
	public String tagActualCurentAddress = "//p[@id='currentAddress']";
	public String tagActualPermanentAddress = "//p[@id='permanentAddress']";
	public String tagBoderEmail = "//*[@id='userEmail']";
	public String colorBoderRed = "#ff0000";

	public void testTC1() {
		String inputName = "Trinh";
		String inputEmail = "Trinh@gmail.com";
		String inputCurentAddress = "Hà Nội";
		String inputPermanentAddress = "Hà Nội 2";
		String inputData = inputName + inputEmail + inputCurentAddress + inputPermanentAddress;
		// Open screen
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuTextbox);
		// Input + submit data
		tb.onInput(tagInputName, inputName);
		tb.onInput(tagInputEmail, inputEmail);
		tb.onInput(tagCurentAddress, inputCurentAddress);
		tb.onInput(tagpermanentAddress, inputPermanentAddress);
		tb.onScrollToView(tagBtnSubmit);
		tb.onClick(tagBtnSubmit);
		// Get data submited

		String actualName = getTextInputed(tb.onGetText(tagActualName));
		String actualEmail = getTextInputed(tb.onGetText(tagActualEmail));
		String actualCurentAddress = getTextInputed(tb.onGetText(tagActualCurentAddress));
		String actualPermanentAddress = getTextInputed(tb.onGetText(tagActualPermanentAddress));
		String actualData = actualName + actualEmail + actualCurentAddress + actualPermanentAddress;
		if (inputData.equals(actualData)) {
			System.out.println("Textbox TC1: Pass submit data");
		} else {
			System.out.println("Textbox TC1: Fail submit data");
		}
	}

	public void testTC2() throws InterruptedException {
		String inputName = "Trinh";
		String inputEmail = "trinh.com";
		String inputCurentAddress = "Hà Nội";
		String inputPermanentAddressmail = "Hà Nội 2";
		// Open screen
		tb.openWebBrowser();
		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuTextbox);
		// Input + submit data
		tb.onInput(tagInputName, inputName);
		tb.onInput(tagInputEmail, inputEmail);
		tb.onInput(tagCurentAddress, inputCurentAddress);
		tb.onInput(tagpermanentAddress, inputPermanentAddressmail);
		tb.onScrollToView(tagBtnSubmit);
		tb.onClick(tagBtnSubmit);
		Thread.sleep(2000);
		// Get text inputed
		String actualName = getTextInputed(tb.onGetText(tagActualName));
		String actualEmail = getTextInputed(tb.onGetText(tagActualEmail));
		String actualCurentAddress = getTextInputed(tb.onGetText(tagActualCurentAddress));
		String actualPermanentAddress = getTextInputed(tb.onGetText(tagActualPermanentAddress));
		String actualData = actualName + actualEmail + actualCurentAddress + actualPermanentAddress;
		// Check boder red
		String colorHex = tb.checkColorCode(tagBoderEmail, "border-bottom-color");
		if (colorHex.equals(colorBoderRed)) {
			System.out.println("Textbox TC2: Pass color boder correct");
		} else {
			System.out.println("Textbox TC2: Fail color boder incorrect");
		}
		// Check not saved data invalid
		if (actualData.equals("")) {
			System.out.println("Textbox TC2: Fail data is saved");
		} else {
			System.out.println("Textbox TC2: Pass data is not saved");
		}
	}

	private String getTextInputed(String str) {
		int i = str.indexOf(":");
		return str.substring(i + 1, str.length());
	}
}
