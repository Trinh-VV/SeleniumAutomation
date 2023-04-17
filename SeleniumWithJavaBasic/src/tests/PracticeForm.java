package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.TestBase;

public class PracticeForm {
	TestBase tb = new TestBase();
	public String tagBtnForm = "//*[@id=\"app\"]/div/div/div[2]/div/div[2]";
	public String tagMenuForm = "//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[2]/div/ul";
	public String tagInputFirstName = "//*[@id=\"firstName\"]";
	public String tagInputLastName = "//*[@id=\"lastName\"]";
	public String tagInputEmail = "//*[@id=\"userEmail\"]";
	public String tagGenderMale = "//*[@id=\"genterWrapper\"]/div[2]/div[1]";
	public String tagGenderFemale = "//*[@id=\"genterWrapper\"]/div[2]/div[2]";
	public String tagGenderOther = "//*[@id=\"genterWrapper\"]/div[2]/div[3]";
	public String tagInputMobile = "//*[@id=\"userNumber\"]";
	public String tagInputBirth = "//*[@id=\"dateOfBirth\"]/div/div";
	public String tagInputSubject = "//div[@class='subjects-auto-complete__control css-yk16xz-control']";
	public String tagHobbiesSport = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]";
	public String tagHobbiesReading = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]";
	public String tagHobbiesMusic = "//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]";
	public String tagInputAddress = "//*[@id=\"currentAddress\"]";
	public String tagSelectState = "//*[@id=\"stateCity-wrapper\"]/div[2]";
	public String tagSelectCity = "//*[@id=\"stateCity-wrapper\"]/div[3]";
	public String tagBtnSubmit = "//*[@id=\"userForm\"]/div[11]/div";

	public void testTC1() throws InterruptedException {
		tb.openWebBrowser();
		String inputFirstName = "Van";
		String inputLastName = "Trinh";
		String inputEmail = "Trinh@gmail.com";
		String inputGender = "Male";
		String inputMobile = "1234567890";
		String inputBirth = "11 Apr 2023";
		String inputSubject = "Demo test";
		String inputHobbies = "Sports,Reading";
		String inputAddress = "Hà nội, Việt Nam";
		String inputState = "NCR";
		String inputCity = "Delhi";

		tb.onClick(tagBtnForm);
		tb.onClick(tagMenuForm);
		tb.onInput(tagInputFirstName, inputFirstName);
		tb.onInput(tagInputLastName, inputLastName);
		tb.onInput(tagInputEmail, inputEmail);
		tb.onClick(tagGenderMale);
		tb.onInput(tagInputMobile, inputMobile);
		
		tb.onScrollToView(tagBtnSubmit);
		Thread.sleep(500);
	    
		// * Date of Birth
		//tb.onInput(tagInputBirth, inputBirth);
		
		// * Subjects
		//tb.driver.findElement(By.xpath("//*[@id=\"subjectsWrapper\"]/div[2]")).click();
		//tb.driver.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div")).sendKeys("t");	
		//Select dropdown = new Select(elmSubject);
		//dropdown.selectByVisibleText("Maths");
		
		tb.onClick(tagHobbiesReading);
		tb.onClick(tagHobbiesMusic);
		tb.onInput(tagInputAddress, inputAddress);
		
		// * Picture
		WebElement elm = tb.driver.findElement(By.xpath(tagSelectState));
		elm.click();
		Select dropdown = new Select(tb.driver.findElement(By.xpath(tagSelectState)));
		Thread.sleep(500);
		dropdown.selectByVisibleText(inputState);
		dropdown.selectByIndex(1);


		tb.onScrollToView(tagBtnSubmit);
		tb.onClick(tagBtnSubmit);
	}

	public void testTC2() {
	}

}
