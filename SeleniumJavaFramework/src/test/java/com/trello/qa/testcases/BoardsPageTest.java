package com.trello.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;



import com.trello.qa.base.TestBase;
import com.trello.qa.pages.BoardsPage;
import com.trello.qa.pages.LoginPage;

public class BoardsPageTest extends TestBase{

LoginPage loginPage;
BoardsPage boardPage;
	
	public BoardsPageTest() {
		super();
	}
	
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		boardPage = new BoardsPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (enabled = false)
	public void createBoardsTest() {
		boardPage.createBoard("SampleTest");
		boolean boardName = boardPage.validateCreatedBoard("SampleTest");
		AssertJUnit.assertTrue(boardName);	
	}
	
	@Test
	public void openBoardTest() {
		boardPage.openBoard("SampleTest");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
