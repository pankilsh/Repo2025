package seleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class StandAlone {

	public void test() throws IOException {
		/*Pankil
		 * String url = "https://rahulshettyacademy.com/AutomationPractice/";
		 * 
		 * // Proxy proxy = new Proxy(); // proxy.setHttpProxy("ipadress:9898");
		 * 
		 * ChromeOptions options = new ChromeOptions();
		 * options.setAcceptInsecureCerts(true);
		 * 
		 * WebDriver driver = new ChromeDriver(options);
		 * 
		 * driver.get(url); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 * driver.manage().deleteAllCookies();
		 * 
		 * WebElement footer = driver.findElement(By.cssSelector("div#gf-BIG"));
		 * 
		 * SoftAssert sAssert = new SoftAssert();
		 * 
		 * List<WebElement> allLinks = footer.findElements(By.tagName("a"));
		 * 
		 * /* List<String> allHrefs = new ArrayList<String>();
		 * 
		 * for (WebElement el : allLinks) { allHrefs.add(el.getAttribute("href")); }
		 * 
		 * for (String href : allHrefs) { HttpURLConnection connect =
		 * (HttpURLConnection) new URL(href).openConnection();
		 * connect.setRequestMethod("HEAD"); connect.connect(); int resCode =
		 * connect.getResponseCode(); String resMessage = connect.getResponseMessage();
		 * if (resCode > 200) System.out.println(href + " : " + resCode + " : " +
		 * resMessage); }
		 * 
		 * for (WebElement link : allLinks) { String href = link.getAttribute("href");
		 * 
		 * HttpURLConnection connect = (HttpURLConnection) new
		 * URL(href).openConnection(); connect.setRequestMethod("HEAD");
		 * connect.connect();
		 * 
		 * int resCode = connect.getResponseCode(); String resMessage =
		 * connect.getResponseMessage();
		 * 
		 * sAssert.assertTrue(resCode == 200, link.getText() + " is broken with code : "
		 * + resCode); connect.disconnect(); }
		 * 
		 * driver.close(); sAssert.assertAll();
		 * 
		 * List<WebElement> elementList = driver.findElements(By.cssSelector("sdfsdf"));
		 * List<String> prices = new ArrayList<String>(); do { prices =
		 * elementList.stream().filter(s -> s.getText().contains("Rice")).map(s ->
		 * getVegPrice(s)) .collect(Collectors.toList()); if (prices.size() == 0) { //
		 * next.click(); } } while (prices.size() == 0);
		 * 
		 * prices.stream().forEach(s -> System.out.println(s));
		 * 
		 * driver.switchTo().newWindow(WindowType.TAB); WebElement element =
		 * driver.findElement(By.cssSelector("")); File scr =
		 * element.getScreenshotAs(OutputType.FILE); File dst = new File("sdfdsf");
		 * FileUtils.copyFile(scr, dst);
		 * 
		 * element.getRect().getDimension().getHeight(); element.getRect().getWidth();
		 * 
		 * 
		 * FluentWait<WebDriver> waitF = new FluentWait<>(driver)
		 * .withTimeout(Duration.ofSeconds(10)) .pollingEvery(Duration.ofSeconds(2))
		 * .ignoring(NoSuchElementException.class);
		 * 
		 * WebDriverWait waitE = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * waitE.until(ExpectedConditions.alertIsPresent());
		 */
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
		StandAlone standAlone = new StandAlone();
		

	}

}
