package seleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 1;
	int maxTry = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (!result.isSuccess() && (count <= maxTry)) {
			count++;
			return true;
		}

		return false;

	}

}
