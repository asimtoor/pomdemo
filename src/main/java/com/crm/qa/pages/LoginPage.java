package com.crm.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	// Page Factory - OR
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;

	@FindBy(xpath="//*[@value='Login']")
	WebElement btnLogin;
		
	@FindBy(xpath="//img[contains(@class, 'img-responsive')]")
	WebElement crmLogo;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String usr, String pwd){
		username.sendKeys(usr);
		password.sendKeys(pwd);
		btnLogin.click();
		return new HomePage();
	}
	
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateCompanyLogo(){
		return crmLogo.isDisplayed();
	}
}
