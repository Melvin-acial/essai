package acial.selenium.exercices;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Exercice2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	 
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\mlb\\Documents\\Formation\\chrome driver\\chromedriver.exe");
	 
	  driver = new ChromeDriver();
	  
	  
 
    
	  
    baseUrl = "http://universitedutest.com/OrangeHRM/";
   
    
    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test(priority=1)
  public void testLogin() throws Exception {
    driver.get("http://universitedutest.com/OrangeHRM/");
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("Nantes$2020");
    driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test(priority=2)
  public void testAjouterEmploy() throws Exception {
    driver.findElement(By.id("menu_pim_viewPimModule")).click();
    driver.findElement(By.id("menu_pim_addEmployee")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Zinedine");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Zidane");
    driver.findElement(By.id("employeeId")).clear();
    driver.findElement(By.id("employeeId")).sendKeys("3601");
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("personal_optGender_1")).click();
    new Select(driver.findElement(By.id("personal_cmbNation"))).selectByValue("64");
    new Select(driver.findElement(By.id("personal_cmbMarital"))).selectByValue("Married");
    driver.findElement(By.id("personal_DOB")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Next'])[1]/following::select[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Next'])[1]/following::select[2]")).click();
    driver.findElement(By.linkText("14")).click();
    driver.findElement(By.id("btnSave")).click();
  }
 
  @Test(priority=3)
  public void testSupprimerEmploy() throws Exception {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("empsearch_id")).click();
    driver.findElement(By.id("empsearch_id")).clear();
    driver.findElement(By.id("empsearch_id")).sendKeys("3601");
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td/input")).click();
    driver.findElement(By.id("btnDelete")).click();
    driver.findElement(By.id("dialogDeleteBtn")).click();
  }

  
  @Test (priority=4)
  public void testLogout() throws Exception {
    driver.findElement(By.id("welcome")).click();
    driver.findElement(By.linkText("Déconnexion")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
