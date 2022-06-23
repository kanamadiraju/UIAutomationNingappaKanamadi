package com.trello.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import com.trello.qa.base.TestBase;
import com.trello.qa.pages.LoginPage;


public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	
	public LoginPageTest() {
		super();
	}
	
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		AssertJUnit.assertEquals(title, "Log in to Trello");
	}
	
	@Test
	public void trelloLogoImageTest() {
		boolean flag = loginPage.validateTrelloImage();
		AssertJUnit.assertTrue(flag);
	}
	
	@Test
	public void loginTest() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 	String actualTitle = driver.getTitle();
	        String expectedTitle ="Boards | Trello";           
	        AssertJUnit.assertEquals(actualTitle, expectedTitle, "Title matched");
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
