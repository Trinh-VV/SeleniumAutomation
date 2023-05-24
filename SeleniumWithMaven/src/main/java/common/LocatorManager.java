package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

public class LocatorManager {
	private Map<String, String> locators = new HashMap<>();
	public LocatorManager(String csvFilePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					locators.put(parts[0], parts[1] + "=" + parts[2]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public By getByLocator(String key) {
		By by = null;
		String locatorOriginal = locators.get(key);
		if (locatorOriginal.startsWith("id=")) {
			by = By.id(locatorOriginal.substring(3));
		} else if (locatorOriginal.startsWith("className=")) {
			by = By.className(locatorOriginal.substring(10));
		} else if (locatorOriginal.startsWith("name=")) {
			by = By.name(locatorOriginal.substring(5));
		} else if (locatorOriginal.startsWith("cssSelector=")) {
			by = By.cssSelector(locatorOriginal.substring(12));
		} else if (locatorOriginal.startsWith("xpath=")) {
			by = By.xpath(locatorOriginal.substring(6));
		} else if (locatorOriginal.startsWith("linkText=")) {
			by = By.linkText(locatorOriginal.substring(9));
		} else {
			throw new RuntimeException("Locator is not valid");
		}
		return by;
	}
	
}