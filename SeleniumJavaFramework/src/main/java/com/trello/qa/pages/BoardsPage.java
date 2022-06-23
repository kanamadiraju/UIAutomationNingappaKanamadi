package com.trello.qa.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.trello.qa.base.TestBase;

public class BoardsPage extends TestBase {
	
	BoardWindowPage boardWindowPage = new BoardWindowPage(); 

@FindBy(xpath="//ul[@class='boards-page-board-section-list']/li/div/p/span[text()='Create new board']")
WebElement createBoard;

@FindBy(xpath="//input[@data-test-id='create-board-title-input']")
WebElement createBoardTitle;

@FindBy(xpath="//div[contains(@id, 'create-board-select-visibility')]")
WebElement visibilityOption;

@FindBy(xpath="//div[text()='Workspace']")
WebElement workspaceVisibility;

@FindBy(xpath="//button[text()='Create']")
WebElement createBtn;

//private WebDriver driver;
private WebDriverWait wait = new WebDriverWait(driver, 7, 50);

//Initializing the page object
public BoardsPage() {
	PageFactory.initElements(driver, this);
}

public boolean validateCreatedBoard(String boardName) {
	wait.until(ExpectedConditions.visibilityOf(boardWindowPage.boardWindow));
	List<WebElement> list = driver.findElements(By.xpath("//h1[text()='" + boardName + "']"));
	// Assert.assertTrue(list.size() > 0, "Text not found!");
	return list.size() > 0;
}

	
	
	
public void createBoard(String boardName) {
		//String boardName = "Test-Board1";
		 wait.until(ExpectedConditions.visibilityOf(createBoard)).click();
		 createBoardTitle.sendKeys(boardName);
		 visibilityOption.click();
		 workspaceVisibility.click();
		 createBtn.click();
		 		
}

public void openBoard(String boardName) {

	List<WebElement> list = driver.findElements(By.xpath("//div[@class='board-tile-details-name']/div[text()='" + boardName + "']"));
	// Assert.assertTrue(list.size() > 0, "Text not found!");
	if(list.size() > 0) {
		driver.findElement(By.xpath("//div[@class='board-tile-details-name']/div[text()='" + boardName + "']")).click();
	}
	else
		System.out.println("Board is not present to open");   
}

}
