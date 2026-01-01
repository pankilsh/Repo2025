package seleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumFrameworkDesign.TestComponents.BaseTest;

public class UploadDownloadTest extends BaseTest {

	public void updateFruitPriceInExcel(String fruitName, String newPrice, String fileName)
			throws InvalidFormatException, FileNotFoundException, IOException {
		String filePath = uploadPage.getDownloadFolderPath() + "\\" + fileName;
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();

		int numberOfRows = sheet.getLastRowNum();
		int numberOfCells = sheet.getRow(0).getLastCellNum();

		int fruitNameCol = 0;
		int priceColNum = 0;

		for (int i = 0; i < numberOfCells; i++) {
			if (formatter.formatCellValue(sheet.getRow(0).getCell(i)).equalsIgnoreCase("fruit_name")) {
				fruitNameCol = i;
			}
			if (formatter.formatCellValue(sheet.getRow(0).getCell(i)).equalsIgnoreCase("price")) {
				priceColNum = i;
			}
		}

		boolean found = false;

		for (int i = 1; i < numberOfRows; i++) {
			Row row = sheet.getRow(i);
			String value = formatter.formatCellValue(row.getCell(fruitNameCol));
			if (value.equalsIgnoreCase(fruitName)) {
				row.getCell(priceColNum).setCellValue(newPrice);
				found = true;
				break;
			}
		}

		try {
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		workbook.close();

		Assert.assertTrue(found, "No fruits found with the name : " + fruitName);

	}

	@Test
	public void uploadFileTest() throws IOException, InvalidFormatException, InterruptedException {
		
		uploadPage.deleteAndDownloadFile();

		String fruitName = "Papaya";
		String newPrice = "500";
		String fileName = "download.xlsx";

		Thread.sleep(1000);
		Assert.assertTrue(uploadPage.isFileDownloaded(fileName));
		updateFruitPriceInExcel(fruitName, newPrice, fileName);

		uploadPage.uploadFile(fileName);
		uploadPage.waitForToastToAppear();
		Assert.assertTrue(uploadPage.getToastMessage().equalsIgnoreCase("Updated Excel Data Successfully."));
		uploadPage.waitForToastToDisappear();

		String updatedPrice = uploadPage.getPriceOfFruit(fruitName);

		Assert.assertEquals(newPrice, updatedPrice);

	}

}
