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
		tb.openWebBrowser();
		String inputName = "Trinh";
		String inputEmail = "Trinh@gmail.com";
		String inputCurentAddress = "Hà Nội";
		String inputPermanentAddressmail = "Hà Nội 2";

		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuTextbox);
		tb.onInput(tagInputName, inputName);
		tb.onInput(tagInputEmail, inputEmail);
		tb.onInput(tagCurentAddress, inputCurentAddress);
		tb.onInput(tagpermanentAddress, inputPermanentAddressmail);
		tb.onScrollToView(tagBtnSubmit);
		tb.onClick(tagBtnSubmit);

		String actualName = getSubText(tb.onGetText(tagActualName));
		String actualEmail = getSubText(tb.onGetText(tagActualEmail));
		String actualCurentAddress = getSubText(tb.onGetText(tagActualCurentAddress));
		String actualPermanentAddress = getSubText(tb.onGetText(tagActualPermanentAddress));

		if (getSubText(actualName).equals(inputName))
			System.out.println("Pass input name");
		if (getSubText(actualEmail).equals(inputEmail))
			System.out.println("Pass input email");
		if (getSubText(actualCurentAddress).equals(inputCurentAddress))
			System.out.println("Pass input current address");
		if (getSubText(actualPermanentAddress).equals(inputPermanentAddressmail))
			System.out.println("Pass input address");
	}
	
	public void testTC2() throws InterruptedException {
		tb.openWebBrowser();
		String inputName = "Trinh";
		String inputEmail = "trinh.com";
		String inputCurentAddress = "Hà Nội";
		String inputPermanentAddressmail = "Hà Nội 2";

		tb.onClick(tagBtnElements);
		tb.onClick(tagMenuTextbox);
		tb.onInput(tagInputName, inputName);
		tb.onInput(tagInputEmail, inputEmail);
		tb.onInput(tagCurentAddress, inputCurentAddress);
		tb.onInput(tagpermanentAddress, inputPermanentAddressmail);
		tb.onScrollToView(tagBtnSubmit);
		tb.onClick(tagBtnSubmit);
		
		Thread.sleep(3000);

		String actualName = getSubText(tb.onGetText(tagActualName));
		String actualEmail = getSubText(tb.onGetText(tagActualEmail));
		String actualCurentAddress = getSubText(tb.onGetText(tagActualCurentAddress));
		String actualPermanentAddress = getSubText(tb.onGetText(tagActualPermanentAddress));
		
		//Check boder red
		String colorHex = tb.checkColorCode(tagBoderEmail, "border-bottom-color");
		System.out.println(colorHex.equals(colorBoderRed) ? "TC2 - Pass: Red border is displayed"
				: "TC2 - Fail: Red border isn't displayed");
		System.out.println("mau: "+colorHex);
		
		//Check saved data
		if (actualName != tb.IS_FAIL || actualEmail != tb.IS_FAIL || actualCurentAddress != tb.IS_FAIL
				|| actualPermanentAddress != tb.IS_FAIL) {
			System.out.println("TC2 - Fail: data is saved");
		} else {
			System.out.println("TC2 - Pass: data is not saved");
		}
	}

	private String getSubText(String str) {
		int i = str.indexOf(":");
		return str.substring(i + 1, str.length());
	}
}
