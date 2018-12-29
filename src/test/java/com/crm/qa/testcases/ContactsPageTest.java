package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "Contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		Initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.swithToFrame();
		homePage.clickOnContactsPage();
	}

	@Test
	public void verifyContactsLabelTest(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing on the page");
	}
	
	@Test
	public void selectContactsTest(){
		contactsPage.selectContactsByName("Shah Sahib");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3, dataProvider="getCRMTestData")
	public void validateNewContactCreation(String title, String firstName, String lastName, String org){
		homePage.clickOnNewContact();
		contactsPage.createNewContact(title, firstName, lastName, org);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
