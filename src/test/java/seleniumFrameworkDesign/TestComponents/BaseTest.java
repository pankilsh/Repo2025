package seleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumFrameworkDesign.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public String url;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//resources//GlobalData.properties");
		property.load(fis);

		String browserName = System.getProperty("browserName") != null ? System.getProperty("browserName")
				: property.getProperty("browserName");

		url = property.getProperty("url");
		

		if (browserName.contains("edge"))
			driver = new EdgeDriver();
		else if (browserName.contains("firefox"))
			driver = new FirefoxDriver();
		else {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless"))
				options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1449, 900));
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFileName) throws IOException {
		String jsonFilePath = System.getProperty("user.dir") + "//resources//" + jsonFileName + ".json";

		// String jsonContent = Files.readString(Paths.get(jsonFilePath));
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenshot(String screenshotName, WebDriver driver) throws IOException {
		TakesScreenshot scrshot = (TakesScreenshot) driver;
		File sourceFile = scrshot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "//screenshots" + screenshotName + ".png";
		File destinationFile = new File(destinationPath);
		FileUtils.copyFile(sourceFile, destinationFile);
		return destinationPath;
	}

	
	
	public List<HashMap<String, String>> getExcelDataToMapMethodOne(String testCaseName, String dataExcelFileName)
			throws InvalidFormatException, IOException {
		String excelPath = System.getProperty("user.dir") + "//resources//" + dataExcelFileName + ".xlsx";
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		XSSFWorkbook workbook = new XSSFWorkbook(new File(excelPath));
		XSSFSheet sheet;
		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<String> mapKeys = new ArrayList<String>();

		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetName(i).trim().equalsIgnoreCase(testCaseName)) {
				sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				cells.next();

				while (cells.hasNext()) {
					Cell currentCell = cells.next();
					String keyValue = null;
					switch (currentCell.getCellType()) {
					case STRING:
						keyValue = currentCell.getStringCellValue();
						break;
					case NUMERIC:
						keyValue = String.valueOf(currentCell.getNumericCellValue());
						break;
					case BOOLEAN:
						keyValue = String.valueOf(currentCell.getBooleanCellValue());
						break;
					default:
						break;
					}

					mapKeys.add(keyValue);
				}

				int dataIndex = 0, rowIndex = 0;
				while (rows.hasNext()) {
					Row row = rows.next();
					Iterator<Cell> cellData = row.cellIterator();
					String value = null;
					while (cellData.hasNext()) {
						Cell currentDataCell = cellData.next();

						switch (currentDataCell.getCellType()) {
						case STRING:
							value = currentDataCell.getStringCellValue();
							break;
						case NUMERIC:
							value = String.valueOf(currentDataCell.getNumericCellValue());
							break;
						case BOOLEAN:
							value = String.valueOf(currentDataCell.getBooleanCellValue());
							break;
						default:
							break;
						}

						data.get(rowIndex).put(mapKeys.get(dataIndex), value);
						dataIndex++;

					}
					dataIndex = 0;
					rowIndex++;
				}
				rowIndex = 0;
			}
		}
		workbook.close();
		return data;
	}
	
	public List<HashMap<String, String>> getExcelDataToMapMethodTwo(String testCaseName, String excelFileName)
			throws InvalidFormatException, IOException {
		String excelPath = System.getProperty("user.dir") + "//resources//" + excelFileName + ".xlsx";
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		DataFormatter formatter = new DataFormatter();
		
		XSSFWorkbook workbook = new XSSFWorkbook(new File(excelPath));
		XSSFSheet sheet = null;
		int sheetCount = workbook.getNumberOfSheets();

		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetName(i).trim().equalsIgnoreCase(testCaseName)) {
				sheet = workbook.getSheetAt(i);
				break;
			} else {
				Assert.assertTrue(false, "No Sheet found with the name : " + testCaseName);
			}
		}

		Row firstRow = sheet.getRow(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = firstRow.getLastCellNum();
		String key,value;
		HashMap<String, String> testData = null;
		
		for (int i = 1; i < rowCount; i++) {
			testData = new HashMap<String, String>();
			for (int k = 1; k < colCount; k++) {
				
				key = formatter.formatCellValue(firstRow.getCell(k));
				value = formatter.formatCellValue(sheet.getRow(i).getCell(k));
				testData.put(key, value);
			}
			data.add(testData);	
		}
		
		workbook.close();
		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo(url);
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeApplication() {
		driver.quit();
	}

}
