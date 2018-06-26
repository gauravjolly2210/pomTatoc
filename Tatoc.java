package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.pomTatoc.cookiesObject;
import com.qait.automation.pomTatoc.dragAroundObject;
import com.qait.automation.pomTatoc.frameDungeonObject;
import com.qait.automation.pomTatoc.gridGateObject;
import com.qait.automation.pomTatoc.homepageActions;
import com.qait.automation.pomTatoc.popUpObject;

public class Tatoc {

	WebDriver driver;
	homepageActions homepage;
	gridGateObject boxcolor;
	frameDungeonObject frame;
	dragAroundObject drag;
	popUpObject pop;
	cookiesObject cook;
	
	@BeforeClass
	public void initvars() {
		driver =  new ChromeDriver();
		homepage = new homepageActions(driver);
	}
	
	@Test
	public void Step01_Launch_Tatoc() {
		homepage.launchApplication("http://10.0.1.86/tatoc");
		homepage.verifyHomePage();
	}
	
	@Test(dependsOnMethods= {"Step01_Launch_Tatoc"})
	public void Step02_Navigate_To_Basic_TATAOC() {
		homepage.navigatetoBasic();
	}
	
	@Test(dependsOnMethods= {"Step02_Navigate_To_Basic_TATAOC"})
	public void Step_03_is_RedBox() throws InterruptedException {
		//boxcolor =new gridGateObject(driver);
		homepage.colorPassed("redbox");
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods= {"Step_03_is_RedBox"})
	public void Step_04_is_GreenBox() throws InterruptedException {
		homepage.colorPassed("greenbox");
	}
	
	@Test(dependsOnMethods= {"Step_04_is_GreenBox"})
	public void Step_05_fail_Case_Frame_Dungeon() throws InterruptedException {
		homepage.clicking_On_Proceed_Frame_Dungeon();
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods= {"Step_05_fail_Case_Frame_Dungeon"})
	public void Step_06_checking_Color_Frame_Dungeon() throws InterruptedException {
		homepage.sameColor();
	}
	
	@Test(dependsOnMethods= {"Step_06_checking_Color_Frame_Dungeon"})
	public void Step_07_fail_Case_Drag_Around() {
		//drag=new dragAroundObject(driver);
		homepage.clicking_On_Proceed_Drag_Around();
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods= {"Step_07_fail_Case_Drag_Around"})
	public void Step_08_drag_Successful() throws InterruptedException {
		homepage.dragAround();
	}
	
	@Test(dependsOnMethods= {"Step_08_drag_Successful"})
	public void Step_09_fail_Case_PopUp() {
		homepage.clicking_On_Proceed_Pop_Up();
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods= {"Step_09_fail_Case_PopUp"})
	public void Step_10_pop_Up_Check() throws InterruptedException {
		homepage.popUp();
	}
	
	@Test(dependsOnMethods= {"Step_10_pop_Up_Check"})
	public void Step_11_fail_Case_Cookie() {
		
		homepage.clicking_On_Proceed_Cookies();
		driver.navigate().back();
	}
	
	@Test(dependsOnMethods= {"Step_11_fail_Case_Cookie"})
	public void Step_12_Step_Cookie_Check() throws InterruptedException {
		homepage.cookies();
		}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
