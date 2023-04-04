package Organisation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateContact {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String key="webdriver.chrome.driver";
		String value="./src/main/resources/chromedriver.exe";
		System.setProperty(key, value);
		
		WebDriver driver = new ChromeDriver();
		
		FileInputStream fis=new FileInputStream("./src/test/resources/Comdata.property");
		Properties pos=new Properties();
		pos.load(fis);
		String url=pos.getProperty("url");
		String username=pos.getProperty("username");
		String password=pos.getProperty("password");
		
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		FileInputStream excel=new FileInputStream("./src/test/resources/ExcelFile.xlsx");
		Workbook wb=WorkbookFactory.create(excel);
		String sal = wb.getSheet("Contact").getRow(0).getCell(0).getStringCellValue();
		System.out.println(sal);
		String firstname = wb.getSheet("Contact").getRow(0).getCell(1).getStringCellValue();
		String lastname = wb.getSheet("Contact").getRow(0).getCell(2).getStringCellValue();
		double phno = wb.getSheet("Contact").getRow(0).getCell(3).getNumericCellValue();
		Thread.sleep(2000);
		
		String mob=String.format("%.0f", phno);
	
		WebElement salutation = driver.findElement(By.name("salutationtype"));
		
		Select s=new Select(salutation);
		s.selectByVisibleText(sal);
		driver.findElement(By.name("firstname")).sendKeys(firstname);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.id("mobile")).sendKeys(mob);
		
		driver.findElement(By.name("button")).click();
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.close();
		
	}

}
