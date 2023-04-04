package Organisation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateProduct {

	public static void main(String[] args) throws IOException {
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
		
		driver.findElement(By.linkText("Products")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		Random ran=new Random();
		int r=ran.nextInt(100);
		FileInputStream excel=new FileInputStream("./src/test/resources/ExcelFile.xlsx");
		Workbook wb=WorkbookFactory.create(excel);
		String name = wb.getSheet("Product").getRow(0).getCell(0).getStringCellValue()+r;
		
		driver.findElement(By.name("productname")).sendKeys(name);
		driver.findElement(By.name("button")).click();
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.close();
		
	}

}


