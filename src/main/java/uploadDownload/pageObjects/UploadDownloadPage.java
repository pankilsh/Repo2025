package uploadDownload.pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class UploadDownloadPage extends AbstractComponent {

	WebDriver driver;
	UploadDownloadPage uploadPage;

	public UploadDownloadPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "downloadButton")
	WebElement downloadButton;

	@FindBy(id = "fileinput")
	WebElement chooseFile;

	@FindBy(css = "div.Toastify__toast-body div:nth-child(2)")
	WebElement toastMessage;

	@FindBy(css = "[id*='row'] div[data-column-id='2']")
	List<WebElement> fruits;

	public void downloadFile() {
		downloadButton.click();
	}

	public void goTo(String url) {
		driver.get(url);
	}

	public String getDownloadFolder() throws FileNotFoundException, IOException {
		String globalPropertiesPath = System.getProperty("user.dir") + "\\resources\\GlobalData.properties";

		Properties property = new Properties();
		property.load(new FileInputStream(globalPropertiesPath));

		String downloadFolder = property.getProperty("downloadFilePath");

		return downloadFolder;
	}

	public void uploadFile() throws FileNotFoundException, IOException {
		chooseFile.sendKeys(getDownloadFolder() + "download.xlsx");
	}

	public boolean verifyFileDownload() throws FileNotFoundException, IOException {
		Path path = Paths.get(getDownloadFolder() + "download.xlsx");
		return Files.exists(path);
	}

	public void waitForToastToAppear() {
		waitForElementToAppear(toastMessage);
	}

	public void waitForToastToDisappear() {
		waitForElementToDisappear(toastMessage);
	}

	public String getToastMessage() {
		return toastMessage.getText();
	}

	public String getPriceOfFruit(String fruitName) {
		String fruitPrice = "";
		for (WebElement fruit : fruits) {
			if (fruit.getText().equalsIgnoreCase(fruitName)) {
				fruitPrice = fruit.findElement(By.xpath("following-sibling::div[@data-column-id='4']")).getText();
			}
		}
		return fruitPrice;
	}

}
