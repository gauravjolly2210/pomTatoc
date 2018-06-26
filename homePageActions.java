package com.qait.automation.pomTatoc;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class homepageActions {
	
	String qcolor;
	String acolor;
	WebDriver driver;
	public homepageActions(WebDriver driver) {
		this.driver = driver;
	}

	
	public void launchApplication(String url) {
		driver.get(url);
		System.out.println("User launched the url: "+url);
	}


	public void verifyHomePage() {
		WebElement logo = driver.findElement(By.xpath("//div[@class='title']"));
		Assert.assertTrue(logo.isDisplayed());
		System.out.println("Logo is displayed");
	}


	public void navigatetoBasic() {
		WebElement basic = driver.findElement(By.xpath("//a[text()='Basic Course']"));
		basic.click();
		System.out.println("User clicked on Basic Course Link");
	}
	
	
	public void colorPassed(String color) throws InterruptedException {
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.linkText("Basic Course")).sendKeys(Keys.ENTER);
			driver.findElement(By.className(color)).click();		
		if(color.equals("greenbox"))
			{
			Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/basic/frame/dungeon" );
			}
		else if(color.equals("redbox")) {
			Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/error" );
			}
		}
	
	
	
	
	public String getQcolor() {
		 qcolor=driver.findElement(By.id("answer")).getAttribute("class");
		return qcolor;
	}
	
	public String getAcolor() {
		 acolor=driver.findElement(By.id("answer")).getAttribute("class");
		return acolor;
	}
	public void sameColor() throws InterruptedException {
	//Thread.sleep(1000);
	driver.switchTo().frame("main");
	getQcolor();
	while(true){
		//Thread.sleep(1000);
		driver.findElement(By.linkText("Repaint Box 2")).click();
		driver.switchTo().frame("child");
		getAcolor();	
		driver.switchTo().parentFrame();
		if(qcolor.equals(acolor)) {
			driver.findElement(By.linkText("Proceed")).click();
			Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/basic/drag" );
			break;
			}
			}
		}

public void clicking_On_Proceed_Frame_Dungeon() {
		driver.switchTo().frame(0);
		driver.findElement(By.linkText("Proceed")).click();
		Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/error" );
	}

public void dragAround() throws InterruptedException {
	 driver.switchTo().defaultContent();	
	 Thread.sleep(1000);
	 WebElement from=driver.findElement(By.id("dragbox"));
    WebElement to=driver.findElement(By.id("dropbox"));					
    Actions act=new Actions(driver);					
    act.dragAndDrop(from, to).build().perform();	
    driver.findElement(By.linkText("Proceed")).click();  
    Assert.assertEquals(driver.getCurrentUrl(),"http://10.0.1.86/tatoc/basic/windows" );
}
public void clicking_On_Proceed_Drag_Around() {
	driver.findElement(By.linkText("Proceed")).click();
	Assert.assertEquals(driver.getCurrentUrl(),"http://10.0.1.86/tatoc/error");
}

public void popUp() throws InterruptedException {
	driver.findElement(By.linkText("Launch Popup Window")).click();
     String parentWindow=driver.getWindowHandle();
     String subWindow=null;
     Set<String> handles=driver.getWindowHandles();
     for(String h:handles) {
    	 subWindow=h;
     }	     
     driver.switchTo().window(subWindow);
     System.out.println(driver.getCurrentUrl());
     driver.findElement(By.id("name")).sendKeys("name");
     driver.findElement(By.id("submit")).click();
     driver.switchTo().window(parentWindow); 
     driver.findElement(By.linkText("Proceed")).click();
     Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/basic/cookie" );
}
public void clicking_On_Proceed_Pop_Up() {
	driver.findElement(By.linkText("Proceed")).click();
	Assert.assertEquals(driver.getCurrentUrl(),"http://10.0.1.86/tatoc/error");
	}



public void cookies() {
	driver.findElement(By.linkText("Generate Token")).click();
     String value=driver.findElement(By.id("token")).getText();
     System.out.println(value);
     Cookie ck=new Cookie("Token", value.substring(value.indexOf(":")+2));
     driver.manage().addCookie(ck);	
     driver.findElement(By.linkText("Proceed")).click();
     Assert.assertEquals( driver.getCurrentUrl(),"http://10.0.1.86/tatoc/end" );
}

public void clicking_On_Proceed_Cookies() {
	driver.manage().deleteAllCookies();
	driver.findElement(By.linkText("Proceed")).click();
	Assert.assertEquals(driver.getCurrentUrl(),"http://10.0.1.86/tatoc/error");
}
}
