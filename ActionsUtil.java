package trainings;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class ActionsUtil {

	WebDriver driver=null;
	Reports reports=null;
	
	public ActionsUtil(WebDriver driver,Reports reports)
	{
		this.driver=driver;
		this.reports=reports;
	}
	
	public void hoverOnElement(WebElement element)
    {
    	Actions a1=new Actions(driver);
    	
    	//We are choosing the action that is hovering on the element
    	//Then we build up the action that is to be performed
    	//Then we perform the action on the browser
    	a1.moveToElement(element).build().perform();
    }
    
    public void hoverOnElement(String locator,String value) throws Exception
    {
    	Actions a1=new Actions(driver);
    	
    	//We are choosing the action that is hovering on the element
    	//Then we build up the action that is to be performed
    	//Then we perform the action on the browser
    	a1.moveToElement(getElementOnBasisOfLocator(locator, value)).build().perform();
    }
    
    public void doubleClick(String locator,String value) throws Exception
    {
    	Actions a1=new Actions(driver);
    	
    	//We are choosing the action that is hovering on the element
    	//Then we build up the action that is to be performed
    	//Then we perform the action on the browser
    	a1.doubleClick(getElementOnBasisOfLocator(locator, value)).build().perform();
    }
    
    public void rightClick(String locator,String value) throws Exception
    {
    	Actions a1=new Actions(driver);
    	
    	//We are choosing the action that is hovering on the element
    	//Then we build up the action that is to be performed
    	//Then we perform the action on the browser
    	a1.contextClick(getElementOnBasisOfLocator(locator, value)).build().perform();
    }
    
    public boolean waitForElementClickable(WebElement element)
    {
    	//Explicit Wait --> It is applied for a particular WebElement
    	
    	try
    	{
	    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	    	wait.until(ExpectedConditions.elementToBeClickable(element));
	    	
	    	return true;
    	}
    	
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
    
    public boolean waitForElementClickable(String locator,String value)
    {
    	//Explicit Wait --> It is applied for a particular WebElement
    	
    	try
    	{
	    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	    	wait.until(ExpectedConditions.elementToBeClickable(getElementOnBasisOfLocator(locator, value)));
	    	
	    	return true;
    	}
    	
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
    
    public void clickOnElement_ActionsClass(String locator,String value) throws Exception
    {
    	if(getElementOnBasisOfLocator(locator, value)!=null)
		{
			if(waitForElementClickable(locator,value))
			{
				hoverOnElement(locator, value);
				reports.captureScreenshot("Before_Click");
				Actions a1=new Actions(driver);
				a1.click(getElementOnBasisOfLocator(locator, value)).build().perform();
			}
			
			else
			{
				throw new Exception("Element is not clickable");
			}
		}
		
		else
			throw new Exception("Element is null for the locator: "+locator+" and value: "+value+" so please check it!!!, it may be due to an exception also");
    }
    
	public void clickOnElement(String locator,String value) throws Exception
	{
		if(getElementOnBasisOfLocator(locator, value)!=null)
		{
			if(waitForElementClickable(locator,value))
			{
				hoverOnElement(locator, value);
				reports.captureScreenshot("Before_Click");
				getElementOnBasisOfLocator(locator, value).click();
			}
			
			else
			{
				throw new Exception("Element is not clickable");
			}
		}
		
		else
			throw new Exception("Element is null for the locator: "+locator+" and value: "+value+" so please check it!!!, it may be due to an exception also");
	}
	
	public void clickOnElement(WebElement element) throws Exception
	{
		if(element!=null)
		{
			hoverOnElement(element);
			element.click();
		}
		else
			throw new Exception("Element is null, please check it");
	}
	
	public List<WebElement> getElementsOnBasisOfLocator(String locator, String value)
	{
		List<WebElement> element;
		switch(locator.toUpperCase().replace(" ", ""))
		{
			case "LINKTEXT":
					element=driver.findElements(By.linkText(value));
					break;
					
			case "ID":
				element=driver.findElements(By.id(value));
				break;
					
			case "NAME":
				element=driver.findElements(By.name(value));
				break;
			
			case "TAGNAME":
				element=driver.findElements(By.tagName(value));
				break;
			
			case "CLASS":
				element=driver.findElements(By.className(value));
				break;
			
			case "PARTIALLINKTEXT":
				element=driver.findElements(By.partialLinkText(value));
				break;
			
			case "XPATH":
				element=driver.findElements(By.xpath(value));
				break;
			
			default: System.out.println("Locator not found, please check");
					 element=null;
		}
		
		return element;

	}
	
	public WebElement getElementOnBasisOfLocator(String locator, String value)
	{
		try
		{
		WebElement element;
		switch(locator.toUpperCase().replace(" ", ""))
		{
			case "LINKTEXT":
					element=driver.findElement(By.linkText(value));
					break;
					
			case "ID":
				element=driver.findElement(By.id(value));
				break;
					
			case "NAME":
				element=driver.findElement(By.name(value));
				break;
			
			case "TAGNAME":
				element=driver.findElement(By.tagName(value));
				break;
			
			case "CLASS":
				element=driver.findElement(By.className(value));
				break;
			
			case "PARTIALLINKTEXT":
				element=driver.findElement(By.partialLinkText(value));
				break;
			
			case "XPATH":
				element=driver.findElement(By.xpath(value));
				break;
			
			default: System.out.println("Locator not found, please check");
					 element=null;
		}
		
		return element;
		}
		
		catch(NoSuchElementException e3)
		{
			return null;
		}
	}

	public void enterText(WebElement element, String data) throws Exception
	{
		//element.getAttribute("value") ---> Means it helps us in getting the data entered in the text box
		if(!element.getAttribute("value").equalsIgnoreCase(""))
		{
			String value=element.getAttribute("value");
			for(int i=0;i<value.length();i++)
			{
				Thread.sleep(300);
				element.sendKeys(Keys.BACK_SPACE);
			}
		}
		
		element.sendKeys(data);
		
		if(!element.getAttribute("value").equalsIgnoreCase(data))
		{
			reports.captureScreenshot("Not_Entered");
			throw new Exception("Data not entered properly for the data: "+data);
		}
		reports.captureScreenshot();
	}
	
	public void enterText_ActionsClass(String locator,String value,String data) throws Exception
	{
		Actions a1=new Actions(driver);
		
		if(!getAttributeValue(getElementOnBasisOfLocator(locator, value), "value").equalsIgnoreCase(""))
		{
			String existingValue=getAttributeValue(getElementOnBasisOfLocator(locator, value), "value");
			
			for(int i=0;i<existingValue.length();i++)
			{
				Thread.sleep(300);
				
				a1.sendKeys(Keys.BACK_SPACE).build().perform();
			}
		}
		
		a1.sendKeys(getElementOnBasisOfLocator(locator, value)).build().perform();
		
		if(!getAttributeValue(locator, value, "value").equalsIgnoreCase(data))
		{
			reports.captureScreenshot("Not_Entered");
			throw new Exception("Data Not Entered Properly, please check");
		}
		
		reports.captureScreenshot();
	}
	
	public void enterText(String locator,String value,String data) throws Exception
	{
		if(!getAttributeValue(getElementOnBasisOfLocator(locator, value), "value").equalsIgnoreCase(""))
		{
			String existingValue=getAttributeValue(getElementOnBasisOfLocator(locator, value), "value");
			
			for(int i=0;i<existingValue.length();i++)
			{
				Thread.sleep(300);
				getElementOnBasisOfLocator(locator, value).sendKeys(Keys.BACK_SPACE);
			}
		}
		
		getElementOnBasisOfLocator(locator, value).sendKeys(data);
		
		if(!getAttributeValue(locator, value, "value").equalsIgnoreCase(data))
		{
			reports.captureScreenshot("Not_Entered");
			throw new Exception("Data Not Entered Properly, please check");
		}
		
		reports.captureScreenshot();
	}
	
	public String getAttributeValue(WebElement element,String attribute)
	{
		if(attribute.equalsIgnoreCase("text"))
		return element.getText();
		
		else
		return element.getAttribute(attribute);
	}
	
	public String getAttributeValue(String locator,String value,String attribute)
	{
		if(attribute.equalsIgnoreCase("text"))
		{
			return getElementOnBasisOfLocator(locator, value).getText();
		}
		
		else
		return getElementOnBasisOfLocator(locator, value).getAttribute(attribute);
	}
	
	public void pressEnterKey(WebElement element)
	{
		element.sendKeys(Keys.ENTER);
	}
	
	public void launchApplication(String url) throws Exception
	{
		if(url.equalsIgnoreCase("") || url==null)
			throw new Exception("Do not pass an empty or null URL ");
		
		else if(url.contains("http")==false)
			throw new Exception("HTTPS protocol is not maintained in the URL Please check");
		
		
		driver.get(url);
		driver.manage().window().maximize();
		
		//Implicit wait is applied across the complete driver
		
		//Implicit wait is used when the page is taking some time to load, it waits for 10 sec to ensure whether it is found or not
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
		
		reports.captureScreenshot("Launch");
	}
	
	public String launchNewApplicationInNewTabOrWindow(String tabOrWindow,String url) throws Exception
	{
		Thread.sleep(2000);
		//Syntax for Creating a new TAB
		
		if(url.equalsIgnoreCase("") || url==null)
			throw new Exception("Do not pass an empty or null URL ");
		
		else if(url.contains("http")==false)
			throw new Exception("HTTPS protocol is not maintained in the URL Please check");
		
		if(tabOrWindow.equalsIgnoreCase("") || tabOrWindow==null)
			throw new Exception("Code does not know whether to launch a tab or a window");
			
		if(tabOrWindow.equalsIgnoreCase("Tab"))
			driver.switchTo().newWindow(WindowType.TAB);
		
		else if(tabOrWindow.equalsIgnoreCase("Window"))
			driver.switchTo().newWindow(WindowType.WINDOW);
			
		else
			throw new Exception("Please mention either it is a tab or window");
			
		driver.get(url);//Syntax for launching the application
		
		//driver.getWindowHandle() ---> It is used to get the Unique Reference number of the tab/window that is launched
		return driver.getWindowHandle();
	}
	
	public void switchToAllWindows() throws InterruptedException
	{
		//driver.getWindowHandles() ---> 
		//It is used to get the Unique Reference number of all the tab/window that is launched
		
		Set<String> handles=driver.getWindowHandles();
		
		for(String s:handles)
		{
			Thread.sleep(2000);
			System.out.println(driver.getTitle()); //Prints the title of the current window
			driver.switchTo().window(s);
		}
	}
	
	public void closeWindowOrTab(String expectedTitleOrUrl) throws InterruptedException
	{
		//org.openqa.selenium.WebDriverException: tab crashed ---> Occurs when you close the tab in the middle and u are trying to access it
		
		Set<String> handles=driver.getWindowHandles();
		
		for(String s:handles)
		{
			Thread.sleep(2000);
			driver.switchTo().window(s);
			
			if(driver.getTitle().contains(expectedTitleOrUrl) || driver.getTitle().equalsIgnoreCase(expectedTitleOrUrl))
			{
				Thread.sleep(4000);
				driver.close(); //Closing that particular tab
				break;
			}
		}
	}
	
	public void quitDriver()
	{
		if(driver!=null)
			driver.quit();
	}
	
	public boolean isAlertPresent(int seconds)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}
		
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void acceptAlert()
	{
		if(isAlertPresent(3))
			driver.switchTo().alert().accept();
	}
	
	public void dismissAlert()
	{
		if(isAlertPresent(3))
			driver.switchTo().alert().dismiss();
	}
	
	public String getAlertText()
	{
		if(isAlertPresent(3))
			return driver.switchTo().alert().getText();
		else
			return "";
	}
	
	public void selectValuesFromDropDown(String locator,String value,String data) throws Exception
	{
		Select s1=new Select(getElementOnBasisOfLocator(locator, value));
		
		if(data.equalsIgnoreCase(""))
		{
			List<WebElement> options=s1.getOptions();
			
			int index=ThreadLocalRandom.current().nextInt(options.size()-1);
			
			String text=options.get(index).getText();
			
			while(text.contains("select an option"))
			{
				index=ThreadLocalRandom.current().nextInt(options.size()-1);
				text=options.get(index).getText();
//				selectValuesFromDropDown(locator, value, data);
			}
			
			s1.selectByVisibleText(text);
		}
		
		else
		{
			try
			{
				s1.selectByVisibleText(data);
				reports.captureScreenshot("DropDown_Value");
			}
			
			catch(Exception ex)
			{
				try
				{
					s1.selectByValue(data);
					reports.captureScreenshot("DropDown_Value");
				}
				
				catch(Exception e1)
				{
					s1.selectByIndex(Integer.parseInt(data));
					reports.captureScreenshot("DropDown_Value");
				}
			}
		}
		
	}
}
