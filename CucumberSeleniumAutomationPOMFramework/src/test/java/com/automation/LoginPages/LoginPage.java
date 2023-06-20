package com.automation.LoginPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.page.BasePage;

public class LoginPage extends BasePage {
	
	
	@FindBy(id="username")WebElement loginidElement;
	@FindBy(name="pw")WebElement passwordElement;
	@FindBy(name="Login")WebElement loginButtonElement;
	@FindBy(id="error")WebElement errorElement;
	@FindBy(id="rememberUn")WebElement rememberMe;
	@FindBy(id="forgot_password_link")WebElement forgotPswd;
	//@FindBy(id="error")WebElement invalidErrorElement;

	
	public  WebDriverWait wait = new WebDriverWait(driver, 40);
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public void enterUserName(String usernamedata) throws InterruptedException {
		Thread.sleep(2000);
		enterText(loginidElement, usernamedata, " login field");
	}
	public void enterPassword(String passworddata) throws InterruptedException {
		Thread.sleep(2000);
		enterText(passwordElement, passworddata, " password field");
	}
	public WebDriver clickLoginButton() {
		clickOnElement(loginButtonElement,"LoginButton");
		return driver;
	}
	public String getPageTittle(WebDriver driver) {
		String actualTitle= driver.getTitle();
		return actualTitle;
	}
	
	public void checkTitle(String expectedTitle) {
		verifyTittle(expectedTitle);
		
	}
	public void clearPswdField() {
		clearTheField(passwordElement);
		
	}
	public String getErrorMsg() {
		waitForPageLoad(60);
		return getText(errorElement);
		
	}
	public void checkRememberMe() {
		clickOnElement(rememberMe,"Remember me");
	}
	public void waitForForgotPswdToBeClickable() {
		wait.until(ExpectedConditions.visibilityOf(forgotPswd));
	}
	public WebDriver clickForgotPswd() {
		clickOnElement(forgotPswd,"Forgot Password");
		return driver;
		
	}
	
	
	

}
