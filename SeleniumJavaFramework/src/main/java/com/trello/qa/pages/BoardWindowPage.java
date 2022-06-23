package com.trello.qa.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.trello.qa.base.TestBase;

public class BoardWindowPage extends TestBase {
	
	LoginPage login = new LoginPage();
	
	@FindBy(xpath="//span[@class='placeholder']")
	WebElement addListPlaceholder;
	
	@FindBy(xpath="//input[@class='list-name-input']")
	WebElement listTitleTxtField;
	
	@FindBy(xpath="//input[@value='Add list']")
	WebElement addListBtn;
	
	@FindBy(xpath="//span[text()='Add a card']")
	WebElement addCardLink;
	
	@FindBy(xpath="//textarea[@placeholder='Enter a title for this card…']")
	WebElement cardTxtField;
	
	@FindBy(xpath="//input[@value='Add card']")
	WebElement addCardBtn;
	
	@FindBy(xpath="//a[@class='js-close-list' and text()='Archive this list']")
	WebElement listActionArchive;
	
	@FindBy(xpath="//div[@id='content']/div[@class='board-wrapper']")
	WebElement boardWindow;
	
	@FindBy(xpath="//a[@aria-label='Back to home']")
	WebElement homeIcon;
	
	@FindBy(xpath="//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']")
	WebElement editCardIcon;
	
	@FindBy(xpath="//span[text()='Move']")
	WebElement editCardMoveAction;
	
	@FindBy(xpath="//select[@class='js-select-list']")
	WebElement listDropdown;
	
	@FindBy(xpath="//input[@value='Move']")
	WebElement moveBtn;

	//private WebDriver driver;
	private WebDriverWait wait = new WebDriverWait(driver, 7, 50);

	//Initializing the page object
	public BoardWindowPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void moveCard(String cardName, String srcList, String destList) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(editCardIcon)).click();
		editCardMoveAction.click();
		Select se = new Select(listDropdown);
		se.selectByVisibleText(destList);
		
		moveBtn.click();

		WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-list list-wrapper'][2]/div/div[1]//textarea[text()='"+destList+"']/../../div[2]/a/div[3]/span[@class='list-card-title js-card-name']")));
		JavascriptExecutor js= (JavascriptExecutor) driver; 
		String strtext=(String) js.executeScript("return arguments[0].lastChild.textContent;", element);

		Assert.assertEquals(strtext, cardName, "Card moved between List successfully");
	
	}
	
	

	public void closeBoardWindow() {
		
		 wait.until(ExpectedConditions.visibilityOf(boardWindow));
		 homeIcon.click();
		 wait.until(ExpectedConditions.visibilityOf(login.boardPage));

	}
	
	
	public void archiveAllLists() {
        List<WebElement> listMenus = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='list-header-extras']")));
        int elementSize = listMenus.size();
        listMenus.forEach(this::archiveList);

  
        if (elementSize >= 5) {
            archiveAllLists();
        }
    }
	
	public void archiveList(WebElement element) {
        element.click();
        listActionArchive.click();
    }
	
	
	public void createDemoCard(String listName, String cardNameTitle) {
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[text()='" + listName + "']")));		
		addCardLink.click();
		cardTxtField.sendKeys(cardNameTitle);		
		addCardBtn.click();
				
	}
	
	
	
	public void createDemoList(String demoListName) {
		
		if(addListPlaceholder.isDisplayed()) {
			 wait.until(ExpectedConditions.visibilityOf(addListPlaceholder)).click();
		}		
		 listTitleTxtField.sendKeys(demoListName);	
		 addListBtn.click();				
	}
	
	

}
