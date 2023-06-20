package com.automation.HomePages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.page.BasePage;

public class HomePage extends BasePage {
	
	public  WebDriverWait wait  = new WebDriverWait(driver, 40);
	@FindBy(id="userNavButton")WebElement userMenuDropdown;
	@FindBy(linkText="Logout")WebElement logout;
	@FindBy(id="userNav-menuItems")WebElement userMenuOptions;
	@FindBy(linkText="Accounts")WebElement accountsOption;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void checkTitle(String expectedTitle) {
		verifyTittle(expectedTitle);
	}

	public void waitForMenuToBeClickable() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(userMenuDropdown));
		Thread.sleep(2000);
	}

	public void clickUserMenu() {
		clickOnElement(userMenuDropdown,"User Menu Dropdown");
		
	}

	public void waitForLogoutToBeClickable() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(logout));
		Thread.sleep(2000);
	}
	
	public WebDriver clickOnLogout() throws InterruptedException {
		Thread.sleep(2000);
		clickOnElement(logout,"Logout");
		return driver;
	}

	public void waitForLoginTitleToLoad() throws InterruptedException {
		//wait.until(ExpectedConditions.titleContains("Login | Salesforce"));
		Thread.sleep(2000);
	}

	public List<String> getActualDropdownList() {
		List<WebElement> arrMenu = userMenuOptions.findElements(By.tagName("a"));
		List<String> actualDropdown = new ArrayList<String>();
		for (WebElement webEle : arrMenu) {
			actualDropdown.add(webEle.getText());
		}
		return actualDropdown;
	}

	public WebDriver clickOnAccountsTab() {
		clickOnElement(accountsOption, "Accounts Option");
		return driver;
	}
	
	

	
	
}
