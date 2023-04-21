package common;
import java.text.ParseException;
import tests.CheckBoxTest;
import tests.PracticeForm;
import tests.TextBoxTest;
import tests.WebTableTest;

public class Main {
	public static void main(String[] args) throws InterruptedException, ParseException {
		// Check Text box
		TextBoxTest textbox = new TextBoxTest();
		textbox.testTC1();
		Thread.sleep(1000);
		textbox.testTC2();
		Thread.sleep(2000);
		// Check Check box
		CheckBoxTest checkbox = new CheckBoxTest();
		checkbox.TestTC1();
		Thread.sleep(2000);
		// Check Web table
		WebTableTest table = new WebTableTest();
		table.testTC1();
		Thread.sleep(1000);
		table.testTC2();
		Thread.sleep(2000);
		// Check Practice form
		PracticeForm form = new PracticeForm();
		form.testTC1();

	}

}
