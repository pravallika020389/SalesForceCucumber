package com.automation.LoginPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.page.BasePage;

public class ReLoginPage extends BasePage {
	
	
	@FindBy(id="idcard-identity")WebElement reLoginidElement;
	
	public  WebDriverWait wait = new WebDriverWait(driver, 40);
	
	public ReLoginPage(WebDriver driver) {
		super(driver);
	
	}
	public void waitForReloginfieldIsVisible() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(reLoginidElement));
		Thread.sleep(2000);
	}
	public String getLoginText() {
		String actualUserId = getText(reLoginidElement);
		return actualUserId;
	}
	
	public void checkTitle(String expectedTitle) throws InterruptedException {
		Thread.sleep(2000);
		verifyTittle(expectedTitle);
		
	}
	public String getTitle(WebDriver driver) {
			String actualTitle= driver.getTitle();
			return actualTitle;

	}
	

}
