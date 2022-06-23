package com.trello.qa.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class SampleTest {
	
	WebDriver driver = null;
	private WebDriverWait wait;
	
	@BeforeTest
	public void setUpTest() {
		String projectpath = System.getProperty("user.dir");
		System.out.println(projectpath);
		//System.setProperty("webdriver.chrome.driver", "/Users/ningappakanamadi/eclipse-workspace/SeleniumJavaFramework/drivers/chromedriver");
		System.setProperty("webdriver.chrome.driver", projectpath+"/drivers/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 7);
		
		
	}
	
	@Test(priority=-1)
	public void test01login(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://trello.com/login");
		   
	     //   driver.findElement(By.id("user")).sendKeys("muktaburli94@gmail.com");
	        driver.findElement(By.id("user")).sendKeys("kanamadiraju@gmail.com");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).click();
	        driver.findElement(By.id("login")).submit();
  
	        if (driver.findElements(By.id("user-password")).size() > 0) {
	            driver.findElement(By.id("user-password")).click();
	        }
	        
	      //  driver.findElement(By.id("password")).sendKeys("admin12!@");
	        driver.findElement(By.id("password")).sendKeys("IND2020@ind");
	        driver.findElement(By.id("login-submit")).submit();
	       
	        driver.manage().window().maximize();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='member-boards-view']")));
	        String actualTitle = driver.getTitle();
	        String expectedTitle ="Boards | Trello";
	      
	        
	        AssertJUnit.assertEquals(actualTitle, expectedTitle, "Title matched");
	        		
	}
	
	@Test(enabled = false)
	public void createBoard() {
		String boardName = "Test-Board1";
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class=\"boards-page-board-section-list\"]/li/div/p/span[text()='Create new board']"))).click();
		
		 driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']")).sendKeys(boardName);		
		 driver.findElement(By.xpath("//div[contains(@id, 'create-board-select-visibility')]")).click();
		 driver.findElement(By.xpath("//div[text()='Workspace']")).click();
		 driver.findElement(By.xpath("//button[text()='Create']")).click();
		 List<WebElement> list = driver.findElements(By.xpath("//h1[text()='" + boardName + "']"));
		 AssertJUnit.assertTrue("Text not found!", list.size() > 0);
		 closeBoardWindow();
		
				
	}
	
	
	@Test(enabled = false)
	public void createList() {
		openBoard("Test-Board1");
		createDemoList("List01");
		closeBoardWindow();
	
	}
	
	
	@Test(enabled = false)
	public void addCard() {
		
		openBoard("Test-Board1");
		createDemoList("List02");
		createDemoCard("List02","TestCard1");
		closeBoardWindow();
		
	}
	
	@Test(enabled = false)
	public void archiveList() {
		
		openBoard("Test-Board1");
		archiveAllLists();
		closeBoardWindow();
		
		
	}
	
	@Test
	public void moveCard() throws InterruptedException {
		openBoard("Test-Board1");
		archiveAllLists();
		createDemoList("MS");
		createDemoList("ITC");
		createDemoCard("MS","TestCard1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='icon-sm icon-edit list-card-operation dark-hover js-open-quick-card-editor js-card-menu']"))).click();
		driver.findElement(By.xpath("//span[text()='Move']")).click();
		Select se = new Select(driver.findElement(By.xpath("//select[@class='js-select-list']")));
		se.selectByVisibleText("ITC");
		
		driver.findElement(By.xpath("//input[@value='Move']")).click();
		//Thread.sleep(3000);

		
		WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-list list-wrapper'][2]/div/div[1]//textarea[text()='ITC']/../../div[2]/a/div[3]/span[@class='list-card-title js-card-name']")));
		JavascriptExecutor js= (JavascriptExecutor) driver; 
		String strtext=(String) js.executeScript("return arguments[0].lastChild.textContent;", element);

		AssertJUnit.assertEquals(strtext, "TestCard1", "Card moved between List successfully");
	
	}
		
	
	
public void openBoardCreateList() {
		openBoard("Test-Board1");
		//createDemoList("List01");
		//createDemoCard("List01","TestCard1");
		archiveAllLists();
	//	 driver.findElement(By.xpath("//span[@class='placeholder']")).click();
	//	 driver.findElement(By.xpath("//input[@class='list-name-input']")).sendKeys("List01");	
	//	 driver.findElement(By.xpath("//input[@value='Add list']")).click();
		
		
	}
	
	public void closeBoardWindow() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']/div[@class='board-wrapper']")));
		driver.findElement(By.xpath("//a[@aria-label='Back to home']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='member-boards-view']")));
	}
	
	public void openBoard(String boardName) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='board-tile-details-name']/div[text()='" + boardName + "']"))).click();
	   // String actualTitle = driver.getTitle();
       // String expectedTitle =boardName +" | Trello";    
       // Assert.assertEquals(actualTitle, expectedTitle, "Title matched");
        
    }
	
	public void createDemoList(String demoListName) {
		
		/*if (driver.findElements(By.xpath("//span[@class='placeholder']")).size() > 0) {
            driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        }
        */
		
		if(driver.findElement(By.xpath("//span[@class='placeholder']")).isDisplayed()) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='placeholder']"))).click();
		}
		
		
	//	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='placeholder']"))).click();
		 driver.findElement(By.xpath("//input[@class='list-name-input']")).sendKeys(demoListName);	
		 driver.findElement(By.xpath("//input[@value='Add list']")).click();
		
		
	}
	
	public void createDemoCard(String listName, String cardNameTitle) {
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[text()='" + listName + "']")));
		
		driver.findElement(By.xpath("//span[text()='Add a card']")).click();
		
		driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys(cardNameTitle);
		
		driver.findElement(By.xpath("//input[@value='Add card']")).click();
		
		
	}
	
	private void archiveAllLists() {
        List<WebElement> listMenus = new ArrayList<WebElement>(driver.findElements(By.xpath("//div[@class='list-header-extras']")));
        int elementSize = listMenus.size();
        listMenus.forEach(this::archiveList);

  
        if (elementSize >= 5) {
            archiveAllLists();
        }
    }
	private void archiveList(WebElement element) {
        element.click();
        driver.findElement(By.xpath("//a[@class='js-close-list' and text()='Archive this list']")).click();
    }

		
		
	}



