package com.automation.steps;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.HomePages.HomePage;
import com.automation.LoginPages.CheckMailPage;
import com.automation.LoginPages.ForgotPasswordPage;
import com.automation.LoginPages.LoginPage;
import com.automation.LoginPages.ReLoginPage;
import com.automation.utility.PropertiesUtility;
import com.automation.utility.log4jUtitlity;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceStepDef {
	WebDriver driver;
	protected static Logger log;
	protected static log4jUtitlity logObject = log4jUtitlity.getInstance();
	LoginPage loginPage;
	HomePage homePage;
	ReLoginPage reLoginPage;
	ForgotPasswordPage forgotPasswordPage;
	CheckMailPage checkMailPage;
	PropertiesUtility pro = new PropertiesUtility();
	Properties appProp = pro.loadFile("applicationDataProperties");
	String userId = appProp.getProperty("login.valid.userid");
	String password = appProp.getProperty("login.valid.password");
	String randomUserId = appProp.getProperty("login.random.userid");
	String invalidUserId = appProp.getProperty("login.invalid.userid");
	String invalidPassword = appProp.getProperty("login.invalid.password");

	///////////////////////////////////////////////////////////////////////

	public void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	public void getUrl(String url) {
		driver.get(url);
		log.info(url + " is entered");

	}

	public void closeBrowser() {
		driver.close();
		log.info("Browser is closed");

	}
///////////////////////////////////////////////////////////////////////////

	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log = logObject.getLogger();
	}

	@Before
	public void setUpEachScenario() {
		launchBrowser("chrome");
	}

	@After
	public void tearDown() {
		closeBrowser();
	}
///////////////////////////////////////////////////////////////////////////////

	@Given("users opens salesforce application")
	public void users_opens_salesforce_application() {
		String url = appProp.getProperty("url");
		getUrl(url);

	}

////////////////////scenario Login error message/////////////////////////////
	@When("user is on {string}")
	public void user_is_on(String page) {
		if (page.equalsIgnoreCase("loginpage")) {
			loginPage = new LoginPage(driver);
			loginPage.checkTitle("Login | Salesforce");
		}
		else if (page.equalsIgnoreCase("homepage")) {
			homePage = new HomePage(driver);
			homePage.checkTitle("Home Page ~ Salesforce - Developer Edition");
		}
		else if  (page.equalsIgnoreCase("reLoginPage")) {
			reLoginPage = new ReLoginPage(driver);
		}
		else if  (page.equalsIgnoreCase("forgotPasswordPage")) {
			forgotPasswordPage = new ForgotPasswordPage(driver);
		}
		else if  (page.equalsIgnoreCase("checkMailPage")) {
			checkMailPage = new CheckMailPage(driver);
		}
	}

	@When("enter the username")
	public void enter_the_username() throws InterruptedException {
		loginPage.enterUserName(userId);
	}

	@When("clear the password")
	public void clear_the_password() {
		loginPage.clearPswdField();
	}

	@When("click on the loginbutton")
	public void click_on_the_loginbutton() {
		driver = loginPage.clickLoginButton();
	}

	@Then("check for error message")
	public void check_for_error_message() {
		String actualErrorMsg = loginPage.getErrorMsg();
		String expectedErrorMsg = appProp.getProperty("login.error");

		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
//////////////////////scenario Login successful\\\\\\\\\\\\\\\\\\\

	@When("enter the password")
	public void enter_the_password() throws InterruptedException {
		loginPage.enterPassword(password);
	}

	@Then("check for page title")
	public void check_for_page_title() {
		String actualHomeTitle = homePage.getPagetitle();
		String expectedHomeTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualHomeTitle, expectedHomeTitle);
	}

//////scenario remember me//////////////////////////
//	@When("verify page title {string}")
//	public void verify_page_title(String string) {
//		loginPage.checkTitle(string);
//	}

	@When("check the remember me checkbox")
	public void check_the_remember_me_checkbox() {
		loginPage.checkRememberMe();
		
	}

	@When("click on user menu")
	public void click_on_user_menu() throws InterruptedException {
		homePage.waitForMenuToBeClickable();
		homePage.clickUserMenu();
	}

	@When("select logout")
	public void select_logout() throws InterruptedException {
		homePage.waitForLogoutToBeClickable();
		driver = homePage.clickOnLogout();
	}

	@Then("check the userid is saved")
	public void check_the_userid_is_saved() throws InterruptedException {
		reLoginPage.waitForReloginfieldIsVisible();
		String actualUserId = reLoginPage.getLoginText();
		Assert.assertEquals(userId,actualUserId);
	}
	////////////////scenario forgot password/////////////
	
	@When("click on forgot password link")
	public void click_on_forgot_password_link() {
		loginPage.waitForForgotPswdToBeClickable();
		driver=loginPage.clickForgotPswd();  
	}

	@When("enter userid to get the mail")
	public void enter_userid_to_get_the_mail() throws InterruptedException {
		forgotPasswordPage.waitForUsernameFieldIsVisible();
		System.out.println(randomUserId);
		forgotPasswordPage.enterUserName(randomUserId);
	}
	
	@When("click on continue button")
	public void click_on_continue_button() {
		driver=forgotPasswordPage.clickContinueButton();  
	}

	@Then("check for ReturnToLogin Button")
	public void check_for_return_to_login_button() throws InterruptedException {
		checkMailPage.waitforCheckMailPageTitle();
		Assert.assertTrue(checkMailPage.checkforReturnToLoginButton());
	}
	/////////////////Scenario Login with invalid userid and password error message///////////// 
	@When("enter the Invalid username")
	public void enter_the_invalid_username() throws InterruptedException {
		loginPage.enterUserName(invalidUserId);
	}

	@When("enter the Invalid password")
	public void enter_the_invalid_password() throws InterruptedException {
		loginPage.enterPassword(invalidPassword);
	}

	@Then("check for invalid data error message")
	public void check_for_invalid_data_error_message() {
		String actualErrorMsg = loginPage.getErrorMsg();
		String expectedErrorMsg = appProp.getProperty("invalid.login.error");
		Assert.assertEquals(expectedErrorMsg,actualErrorMsg);
	}
}
