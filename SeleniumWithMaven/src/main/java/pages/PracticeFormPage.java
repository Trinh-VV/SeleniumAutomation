package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class PracticeFormPage extends Page {

	public PracticeFormPage(WebDriver dr) {
		super(dr);
	}

	public boolean inputTextForm(String firstName, String lastName, String gender, String mobileNumber)
			throws InterruptedException, ParseException {
		boolean result = false;
		String genderFirst = "//label[text()='";
		String genderLast = "']/parent::div";

		// Input first name, last name, email, gender, mobile number
		sendKeyToElement("//input[@id='firstName']", firstName);
		sendKeyToElement("//input[@id='lastName']", lastName);
		sendKeyToElement("//input[@id='userEmail']", "trinh@gmail.com");
		clickToElement(genderFirst + gender + genderLast);
		sendKeyToElement("//input[@id='userNumber']", mobileNumber);

		// Scroll to end page
		scrollToEndPage();

		// Input birthday, subject, hobbies, upload file, address
		String birthDay = inputBirthDay(22, 3, 1993);
		String inputSubject = inputSubject("m");
		clickToElement("//label[text()='Sports']/parent::*");
		uploadFile("//input[@id='uploadPicture']", "C:\\Users\\Admin\\Desktop\\data_Third\\Photo\\1.jpg");
		sendKeyToElement("//textarea[@id='currentAddress']", "địa chỉ");

		// Select state, city
		String inputState = selectFirstItemDropbox("//div[@id='state']", "//div[contains(@id,'react-select-3')]");
		String inputCity = selectFirstItemDropbox("//div[@id='city']", "//div[contains(@id,'react-select-4')]");

		// Submit data
		clickToElement("//button[@id='submit']");
		Thread.sleep(500);
		String inputText = firstName + " " + lastName + "trinh@gmail.com" + gender + mobileNumber + birthDay
				+ inputSubject + "Sports" + "1.jpg" + "địa chỉ" + inputState + " " + inputCity;

		// Check result submit
		if (firstName != "" & lastName != "" & gender != "" & mobileNumber != "") {
			String actualText = getActualText();
			result = inputText.equals(actualText);
		} else {
			result = checkBorderRedColor(firstName, lastName, genderLast, mobileNumber);
		}
		return result;
	}

	public boolean checkBorderRedColor(String firstName, String lastName, String gender, String mobileNumber) {
		boolean checkFirstName = checkBoderBoxElement("//input[@id='firstName']", firstName);
		boolean checkLastName = checkBoderBoxElement("//input[@id='lastName']", lastName);
		boolean checkGender = checkBoderBoxElement("//input[@id='userEmail']", gender);
		boolean checkMobileNumber = checkBoderBoxElement("//input[@id='userNumber']", mobileNumber);
		return checkFirstName && checkLastName && checkGender && checkMobileNumber;
	}

	private String getActualText() {
		String strActual = "";
		List<WebElement> dataSubmited = getListElement("//tbody/tr/td[2]"); 
		for (int i = 0; i < dataSubmited.size(); i++) {
			strActual += dataSubmited.get(i).getText();
		}
		return strActual;
	}
	

	private String selectFirstItemDropbox(String tagDropbox, String tagListOption) {
		clickToElement(tagDropbox);
		List<WebElement> e = getListElement(tagListOption);
		String str = e.get(0).getText();
		e.get(0).click();
		return str;
	}

	private String inputSubject(String key) throws InterruptedException {
		sendKeyToElement("//input[@id='subjectsInput']", key);
		String selectSubject = selectItemDropdownlist("//div[contains(@id,'react-select-2-option')]", 0);
		return selectSubject;
	}

	private String inputBirthDay(int date, int month, int year) throws ParseException, InterruptedException {
		// Click to open datePicker
		clickToElement("//input[@id='dateOfBirthInput']");
		// Select year
		selectItemDropdownByValue("//select[@class='react-datepicker__year-select']", year + "");
		// Select Month
		selectItemDropdownByIndex("//select[@class='react-datepicker__month-select']", month - 1);
		// Select date
		inputDay(date);
		// Convert format date
		SimpleDateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatOut = new SimpleDateFormat("dd MMMM,yyyy", Locale.US);
		Date day = formatIn.parse(date + "/" + month + "/" + year);
		return formatOut.format(day);
	}

	private void inputDay(int date) {
		List<WebElement> visibleDays = getListElement("//div[@role ='option' and not(contains(@class,'outside-month'))]");
		for (WebElement e : visibleDays) {
			if (e.getText().equals(date + "")) {
				e.click();
				break;
			}
		}
	}

	public boolean checkBoderBoxElement(String tagElement, String inputString) {
		Boolean check = true;
		WebElement element = getElement(tagElement);
		if (inputString == "") {
			check = Color.fromString(element.getCssValue("border-bottom-color")).asHex().equals("#dc3545");
		}
		return check;
	}

	@Override
	public void refreshCurrentPage() {
		super.refreshCurrentPage();
	}
}
