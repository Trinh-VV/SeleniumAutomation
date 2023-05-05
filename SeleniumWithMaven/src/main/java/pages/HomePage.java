package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

	public HomePage(WebDriver dr) {
		super(dr);
	}

	// Click on Elements item, return ElementsPage
	public ElementsPage clickElementsMenu() {
		driver.findElement(By.xpath("//h5[text()='Elements']/ancestor::div[3]")).click();
		return new ElementsPage(driver);
	}
	public ElementsPage clickFormsMenu() {
		driver.findElement(By.xpath("//h5[text()='Forms']/ancestor::div[3]")).click();
		return new ElementsPage(driver);
	}
}
