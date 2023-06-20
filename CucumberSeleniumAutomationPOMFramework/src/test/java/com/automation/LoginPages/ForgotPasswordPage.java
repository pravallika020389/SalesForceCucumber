package com.automation.LoginPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.page.BasePage;

public class ForgotPasswordPage extends BasePage {
	
	
	@FindBy(id="un")WebElement userName;
	@FindBy(id="continue")WebElement continueButton;
	

	public  WebDriverWait wait = new WebDriverWait(driver, 40);
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		
	}
	public void waitForUsernameFieldIsVisible() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(userName));
		Thread.sleep(2000);
	}
	public void enterUserName(String usernamedata) {
		enterText(userName, usernamedata, " login field");
		
	}
	
	public WebDriver clickContinueButton() {
		clickOnElement(continueButton,"Continue Button");
		return driver;
	}
	
	public void checkTitle(String expectedTitle) throws InterruptedException {
		Thread.sleep(2000);
		verifyTittle(expectedTitle);
		
	}
	

}
