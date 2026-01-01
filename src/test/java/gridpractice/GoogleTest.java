package gridpractice;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.mysql.cj.x.protobuf.MysqlxConnection.Capabilities;

public class GoogleTest {

	@Test
	public void HomePageCheck() throws MalformedURLException, URISyntaxException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WIN11);
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		
		
		WebDriver driver = new RemoteWebDriver(new URI(" http://192.168.1.36:4444").toURL(),capabilities);
		
		driver.get("https://www.google.com/");
	}
	
}
