package tests;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.TestBase;

public class PracticeForm {
	TestBase tb = new TestBase();
	public String tagBtnForm = "//h5[text()='Forms']/ancestor::div[3]";
	public String tagMenuForm = "//span[text()='Practice Form']/parent::li";
	public String tagInputFirstName = "//input[@id='firstName']";
	public String tagInputLastName = "//input[@id='lastName']";
	public String tagInputEmail = "//input[@id='userEmail']";
	public String tagGenderMale = "//label[text()='Male']/parent::*";
	public String tagGenderFemale = "//label[text()='Female']/parent::*";
	public String tagGenderOther = "//label[text()='Other']/parent::*";
	public String tagInputMobile = "//input[@id='userNumber']";
	public String tagInputBirth = "//input[@id='dateOfBirthInput']";
	public String tagInputSubject = "//div[@id='subjectsContainer']";
	public String tagListSubject = "//div[contains(@id,'react-select-2-option')]";
	public String tagHobbiesSport = "//label[text()='Sports']/parent::*";
	public String tagHobbiesReading = "//label[text()='Reading']/parent::*";
	public String tagHobbiesMusic = "//label[text()='Music']/parent::*";
	public String tagUploadPicture = "//input[@id='uploadPicture']";
	public String tagInputAddress = "//textarea[@id='currentAddress']";
	public String tagSelectState = "//div[@id='state']";
	public String tagOptionsState = "//div[contains(@id,'react-select-3')]";
	public String tagSelectCity = "//div[@id='city']";
	public String tagOptionsCity = "//div[contains(@id,'react-select-4')]";
	public String tagBtnSubmit = "//button[@id='submit']";
	public String tagDropboxYear = "//select[@class='react-datepicker__year-select']";
	public String tagDropboxMonth = "//select[@class='react-datepicker__month-select']";
	public String tagDays = "//div[@role ='option' and not(contains(@class,'outside-month'))]";

	public void testTC1() throws InterruptedException, ParseException {
		String inputFirstName = "Van";
		String inputLastName = "Trinh";
		String inputEmail = "Trinh@gmail.com";
		String inputGender = "Male";
		String inputMobile = "1234567890";
		int inputYear = 1993;
		int inputMonth = 3;
		int inputDay = 17;
		String inputSubject = "";
		String inputHobbies = "Reading, Music";
		String pathPicture = "C:\\Users\\Admin\\Desktop\\data_Third\\Photo\\2.png";
		String pictureName = "2.png";
		String inputAddress = "Hà nội, Việt Nam";
		String inputState = "";
		String inputCity = "";
		// Open screen
		tb.openWebBrowser();
		tb.onClick(tagBtnForm);
		tb.onClick(tagMenuForm);
		// Input Name
		tb.onInput(tagInputFirstName, inputFirstName);
		tb.onInput(tagInputLastName, inputLastName);
		// Input email
		tb.onInput(tagInputEmail, inputEmail);
		// Select gender
		tb.onClick(tagGenderMale);
		// Input mobile
		tb.onInput(tagInputMobile, inputMobile);
		// Scroll to end
		((JavascriptExecutor) tb.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(500);

		// Input Date of Birth
		tb.onClick(tagInputBirth);
		tb.onClick(tagDropboxYear);
		tb.inputDropbox(tagDropboxYear, inputYear + "");
		tb.onClick(tagDropboxMonth);
		tb.inputDropbox(tagDropboxMonth, inputMonth);
		tb.inputDay(tagDays, inputDay - 1);

		// Input Subjects
		tb.onClick(tagInputSubject);
		tb.onInput("//input[@id='subjectsInput']", "m");
		Thread.sleep(500);
		List<WebElement> listItems = tb.driver.findElements(By.xpath(tagListSubject));
		inputSubject = listItems.get(0).getText();
		listItems.get(0).click();

		// Select Hobbies
		tb.onClick(tagHobbiesReading);
		tb.onClick(tagHobbiesMusic);
		tb.onInput(tagInputAddress, inputAddress);

		// Select Picture
		tb.onInput(tagUploadPicture, pathPicture);
		Thread.sleep(1000);

		// Select State-City
		tb.onClick(tagSelectState);
		inputState = tb.onSelectDropbox(tagOptionsState, 0);
		tb.onClick(tagSelectCity);
		inputCity = tb.onSelectDropbox(tagOptionsCity, 0);

		// Submit
		tb.onClick(tagBtnSubmit);

		// Text input
		SimpleDateFormat fmIn = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat fmOut = new SimpleDateFormat("dd MMMM,yyyy", Locale.US);
		Date date = fmIn.parse(inputDay + "/" + inputMonth + "/" + inputYear);
		String inputDate = fmOut.format(date);
		String strInput = inputFirstName + " " + inputLastName + inputEmail + inputGender + inputMobile + inputDate
				+ inputSubject + inputHobbies + pictureName + inputAddress + inputState + " " + inputCity;

		// Text output
		String strActual = "";
		List<WebElement> dataSubmited = tb.driver.findElements(By.xpath("//tbody/tr"));
		for (int i = 0; i < dataSubmited.size(); i++) {
			By tagName = new ByTagName("td");
			List<WebElement> childs = dataSubmited.get(i).findElements(tagName);
			String val = childs.get(1).getText();
			strActual += val;
		}

		// Check data submit
		if (strInput.equals(strActual)) {
			System.out.println("Form TC1: Pass");
		} else {
			System.out.println("Form TC1: Fail");
		}
	}
}
