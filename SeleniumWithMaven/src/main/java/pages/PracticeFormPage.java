package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import common.TestBase;

public class PracticeFormPage extends Page {
	TestBase testBase = new TestBase(driver);
	public static final String PATH_PHOTO = "C:\\Users\\Admin\\Desktop\\data_Third\\Photo\\1.jpg";
	public static final String COLOR_RED_BODER = "#dc3545";
	public static final String BODER_PROPETY_NAME = "border-bottom-color";
	public By locFirstName = By.id("firstName");
	public By locLastName = By.id("lastName");
	public By locEmail = By.id("userEmail");
	public By locPhoneNumber = By.id("userNumber");
	public By locHobies_Sports = By.xpath("//label[text()='Sports']/parent::*");
	public By locUploadFile = By.id("uploadPicture");
	public By locCurrentAddress = By.id("currentAddress");
	public By locBtnSubmit = By.id("submit");
	public By locDataSubmited = By.xpath("//tbody/tr/td[2]");
	public By locSubjects = By.id("subjectsInput");
	public By locSubjectsListData = By.xpath("//div[contains(@id,'react-select-2-option')]");
	public By locDateOfBirth = By.id("dateOfBirthInput");
	public By locYear = By.className("react-datepicker__year-select");
	public By locMonth = By.className("react-datepicker__month-select");
	public By locDay = By.xpath("//div[@role ='option' and not(contains(@class,'outside-month'))]");
	public By locState = By.id("state");
	public By locStateList = By.xpath("//div[contains(@id,'react-select-3')]");
	public By locCity = By.id("city");
	public By locCityList = By.xpath("//div[contains(@id,'react-select-4')]");

	public PracticeFormPage(WebDriver dr) {
		super(dr);
	}

	public boolean inputTextForm(String firstName, String lastName, String gender, String mobileNumber)
			throws ParseException, InterruptedException {
		boolean result = false;
		String genderFirst = "//label[text()='";
		String genderLast = "']/parent::div";

		// Input first name, last name, email, gender, mobile number
		testBase.sendKeyToElement(locFirstName, firstName);
		testBase.sendKeyToElement(locLastName, lastName);
		testBase.sendKeyToElement(locEmail, "trinh@gmail.com");
		driver.findElement(By.xpath(genderFirst + gender + genderLast)).click();
		testBase.sendKeyToElement(locPhoneNumber, mobileNumber);

		// Scroll to end page
		testBase.scrollToEndPage();

		// Input birthday, subject, hobbies, upload file, address
		String birthDay = inputBirthDay(22, 3, 1993);
		String inputSubject = inputSubject("m");
		testBase.clickToElement(locHobies_Sports);
		testBase.uploadFile(locUploadFile, PATH_PHOTO);
		testBase.sendKeyToElement(locCurrentAddress, "địa chỉ");

		// Select state, city
		String inputState = selectFirstItemDropbox(locState, locStateList);
		String inputCity = selectFirstItemDropbox(locCity, locCityList);

		// Submit data
		testBase.clickToElement(locBtnSubmit);
		String inputText = firstName + " " + lastName + "trinh@gmail.com" + gender + mobileNumber + birthDay
				+ inputSubject + "Sports" + "1.jpg" + "địa chỉ" + inputState + " " + inputCity;

		// Check result submit
		if (firstName != "" & lastName != "" & gender != "" & mobileNumber != "") {
			String actualText = getActualText();
			result = inputText.equals(actualText);
		} else {
			result = checkBorderColor(firstName, lastName, gender, mobileNumber);
		}
		return result;
	}

	public boolean checkBorderColor(String firstName, String lastName, String gender, String mobileNumber) throws InterruptedException {
		boolean checkFirstName = isBoderBoxRed(locFirstName, firstName);
		boolean checkLastName = isBoderBoxRed(locLastName, lastName);
		boolean checkGender = isBoderBoxRed(locEmail, gender);
		boolean checkMobileNumber = isBoderBoxRed(locPhoneNumber, mobileNumber);
		return checkFirstName && checkLastName && checkGender && checkMobileNumber;
	}

	private String getActualText() {
		String strActual = "";
		List<WebElement> dataSubmited = testBase.getListElement(locDataSubmited);
		for (int i = 0; i < dataSubmited.size(); i++) {
			strActual += dataSubmited.get(i).getText();
		}
		return strActual;
	}

	private String selectFirstItemDropbox(By locatorDropbox, By locatorListOption) {
		testBase.clickToElement(locatorDropbox);
		List<WebElement> e = testBase.getListElement(locatorListOption);
		String str = e.get(0).getText();
		e.get(0).click();
		return str;
	}

	private String inputSubject(String key) {
		testBase.sendKeyToElement(locSubjects, key);
		String selectSubject = testBase.selectItemDropdownlist(locSubjectsListData, 0);
		return selectSubject;
	}

	private String inputBirthDay(int date, int month, int year) throws ParseException {
		// Click to open datePicker
		testBase.clickToElement(locDateOfBirth);
		// Select year
		testBase.selectItemDropdownByValue(locYear, year + "");
		// Select Month
		testBase.selectItemDropdownByIndex(locMonth, month - 1);
		// Select date
		inputDay(date);
		// Convert format date
		SimpleDateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatOut = new SimpleDateFormat("dd MMMM,yyyy", Locale.US);
		Date day = formatIn.parse(date + "/" + month + "/" + year);
		return formatOut.format(day);
	}

	private void inputDay(int date) {
		List<WebElement> visibleDays = testBase.getListElement(locDay);
		for (WebElement e : visibleDays) {
			if (e.getText().equals(date + "")) {
				e.click();
				break;
			}
		}
	}

	public boolean isBoderBoxRed(By locator, String inputString) throws InterruptedException {
		Boolean check = true;
		WebElement element = testBase.getElement(locator);
		if (inputString == "") {
			Thread.sleep(1000);
			check = Color.fromString(element.getCssValue(BODER_PROPETY_NAME)).asHex().equals(COLOR_RED_BODER);
		}
		return check;
	}

	public void refreshCurrentPage() {
		testBase.refreshCurrentPage();

	}

}
