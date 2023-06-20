package com.automation.LoginPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.page.BasePage;

public class CheckMailPage extends BasePage {
	
	
	@FindBy(linkText="Return to Login")WebElement returnToLogin;


	public  WebDriverWait wait = new WebDriverWait(driver, 40);
	
	public CheckMailPage(WebDriver driver) {
		super(driver);
		
	}
	public boolean checkforReturnToLoginButton() throws InterruptedException {
		
		boolean isReturnToLoginDisplayed=returnToLogin.isDisplayed();
		System.out.println(isReturnToLoginDisplayed+" : Return To Login Button is displayed");
		return isReturnToLoginDisplayed;
	}
	public void waitforCheckMailPageTitle() {
		wait.until(ExpectedConditions.titleContains("Check Your Email | Salesforce"));
		
	}
	

}
