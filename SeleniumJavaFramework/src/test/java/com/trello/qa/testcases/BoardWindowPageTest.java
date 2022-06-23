package com.trello.qa.testcases;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.trello.qa.base.TestBase;
import com.trello.qa.pages.BoardWindowPage;
import com.trello.qa.pages.BoardsPage;
import com.trello.qa.pages.LoginPage;

public class BoardWindowPageTest extends TestBase {
	
	LoginPage loginPage;
	BoardsPage boardPage;
	BoardWindowPage boardWindowPage;
		
		public BoardWindowPageTest() {
			super();
		}
			

		@BeforeTest
		public void setUp() {
			initialization();
			loginPage = new LoginPage();
			boardPage = new BoardsPage();
			boardWindowPage = new BoardWindowPage(); 
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		@Test(priority = -1)
		public void createBoardTest() {
			boardPage.createBoard("UITestAutomation");
			boolean boardName = boardPage.validateCreatedBoard("UITestAutomation");
			AssertJUnit.assertTrue(boardName);
			boardWindowPage.closeBoardWindow();
		}
		
		@Test
		public void createListTest() {
			
			boardPage.openBoard("UITestAutomation");
			boardWindowPage.createDemoList("TestList1");
			boardWindowPage.closeBoardWindow();
		
		}
		
		
		@Test
		public void addCardTest() {
			
			boardPage.openBoard("UITestAutomation");
			boardWindowPage.createDemoList("TestList1");
			boardWindowPage.createDemoCard("TestList1", "TestCard1");
			boardWindowPage.closeBoardWindow();
			
		}
		
		@Test
		public void archiveListTest() {
			
			boardPage.openBoard("UITestAutomation");
			boardWindowPage.archiveAllLists();
			boardWindowPage.closeBoardWindow();
						
		}
		
		@Test
		public void moveCardTest() throws InterruptedException {
			
			boardPage.openBoard("UITestAutomation");
			boardWindowPage.archiveAllLists();
			boardWindowPage.createDemoList("TestList1");
			boardWindowPage.createDemoList("TestList2");
			boardWindowPage.createDemoCard("TestList1", "TestCard1");
			boardWindowPage.moveCard("TestCard1", "TestList1", "TestList2");
			boardWindowPage.closeBoardWindow();
												
		}
		

		
		@AfterTest
		public void tearDown() {
			driver.quit();
		}
		

}
