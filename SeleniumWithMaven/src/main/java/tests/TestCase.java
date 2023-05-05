package tests;

import common.TestBase;

public class TestCase {
	public TestBase testBase = new TestBase();
	
	public void openWebsite() {
		testBase.openWebWithSigleBrowser("Chrome");
	}
}
