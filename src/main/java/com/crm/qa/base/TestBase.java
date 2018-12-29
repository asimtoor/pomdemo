package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger log;
	
	public TestBase(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:/Users/Asim Toor/Documents/Naveen/Selenium/FreeCRMTest/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void Initialization(){
		String browserName = prop.getProperty("browser");
		log = Logger.getLogger(TestBase.class);
		
		if(browserName.equals("chrome")){
			log.info("************ Launching Chrome Browser **************");
			System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equals("FF")){
			log.info("************ Launching Firefox Browser **************");
			System.setProperty("webdriver.gecko.driver", "C:/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (browserName.equals("IE")){
			log.info("************ Launching IE Browser **************");
			System.setProperty("webdriver.ie.driver", "C:/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		
		
		driver.get(prop.getProperty("url"));
		log.info("************ Refreshing Browser **************");
		driver.navigate().refresh();
	}
	
}
