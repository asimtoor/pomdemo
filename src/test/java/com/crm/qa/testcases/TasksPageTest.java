package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class TasksPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	TasksPage tasksPage;
	
	public TasksPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		Initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.swithToFrame();
		tasksPage = homePage.clickOnTasksPage();
	}
	
	@Test
	public void verifyTasksLabelTest(){
		Assert.assertTrue(tasksPage.verifyTasksLabel(), "Tasks label is not found on the page");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
