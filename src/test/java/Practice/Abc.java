package Practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Abc {
//	@FindBy(tagName = "h4")
//	 List<WebElement> ele;
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.edgedriver().setup();
		WebDriver driver= new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("http://rmgtestingserver/domain/Online_Tourism_Management_System/index.php");
		driver.findElement(By.linkText("/ Sign In")).click();
		driver.findElement(By.xpath("//input[@id='email' and @placeholder='Enter your Email']")).sendKeys("abcdef@mail.co");	
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("Abcd@123");
		Thread.sleep(2000);
		driver.findElement(By.name("signin")).click();
		wait.until(ExpectedConditions.titleContains("Package List"));
		driver.findElement(By.linkText("Tour Packages")).click();
		JavascriptExecutor je=(JavascriptExecutor)driver;
		WebElement pac = driver.findElement(By.xpath("(//h4[contains(text(),'Indonesia')]/following::a)[1]"));
		int y = pac.getLocation().getY();
		je.executeScript("window.scrollTo(0,"+y+")");
		pac.click();
		Thread.sleep(2000);
		driver.findElement(By.id("datepicker")).click();
		driver.findElement(By.linkText("2")).click();
		driver.findElement(By.id("datepicker1")).click();
		driver.findElement(By.linkText("5")).click();
		Thread.sleep(2000);
		WebElement submit = driver.findElement(By.name("submit2"));
		int y1 = submit.getLocation().getY();
		je.executeScript("window.scrollTo(0,"+y1+")");
		String str="This is automated";
		driver.findElement(By.name("comment")).sendKeys(str);
		driver.findElement(By.name("submit2")).click();
		driver.findElement(By.linkText("/ Logout")).click();
		Thread.sleep(2000);
		driver.close();

	}

}
