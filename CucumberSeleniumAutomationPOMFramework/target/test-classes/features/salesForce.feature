Feature: login functionality

Background:
Given users opens salesforce application

Scenario: Login error message
	When user is on "LoginPage"
	And enter the username
	And clear the password
	And click on the loginbutton
	Then check for error message
	
Scenario: Login successful
	When user is on "LoginPage"
	And enter the username
	And enter the password
	And click on the loginbutton
	When user is on "HomePage"
	Then check for page title
	
Scenario: Login remember me
	When user is on "LoginPage"
	And enter the username
	And enter the password
	And check the remember me checkbox 
	And click on the loginbutton
	When user is on "HomePage"
	And click on user menu
	And select logout
	When user is on "reLoginPage"
	Then check the userid is saved
	
Scenario: Forgot Password
	When user is on "LoginPage"
	And click on forgot password link
	When user is on "ForgotPasswordPage"
	And enter userid to get the mail
	And click on continue button
	When user is on "CheckMailPage"
	Then check for ReturnToLogin Button

Scenario: Login with invalid userid and password error message
	When user is on "LoginPage"
	And enter the Invalid username
	And enter the Invalid password
	And click on the loginbutton
	Then check for invalid data error message