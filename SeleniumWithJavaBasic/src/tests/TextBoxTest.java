package tests;

import common.TestBase;

public class TextBoxTest {
	TestBase tb = new TestBase();
	public String tagBtnElements = "//*[@id=\"app\"]/div/div/div[2]/div/div[1]";
	public String tagMenuTextbox = "//*[@id=\"item-0\"]";
	public String tagInputName = "//*[@id=\"userName\"]";
	public String tagInputEmail = "//*[@id=\"userEmail\"]";
	public String tagCurentAddress = "//*[@id=\"currentAddress\"]";
	public String tagpermanentAddress = "//*[@id=\"permanentAddress\"]";
	public String tagBtnSubmit = "//*[@id=\"submit\"]";
	public String tagActualName = "//*[@id=\"name\"]";
	public String tagActualEmail = "//*[@id=\"email\"]";
	public String tagActualCurentAddress = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[3]";
	public String tagActualPermanentAddress = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[4]";
	public String tagBoderEmail = "//*[@id=\"userEmail\"]";
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
