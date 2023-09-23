package utilities;

import java.io.File;


import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

public static ExtentReports extent;
	
	public static ExtentReports getInstance(String filename) {
		
		if(extent == null) {
			
			extent = new ExtentReports(filename,true,DisplayOrder.NEWEST_FIRST);
			
			extent.loadConfig(new File(System.getProperty("user.dir") + "//reportConfig.xml"));
			
			extent.addSystemInfo("Rest-Assured Version", "4.4.0").addSystemInfo("Enviroment","QA");
			
		}
		
		return extent;
	}
}
