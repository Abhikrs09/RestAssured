package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            logger.info("Retrying test {} with status {} for the {} time(s).",
                    result.getName(),
                    getResultStatusName(result.getStatus()),
                    retryCount);
            return true;
        }
        return false;
    }

    private String getResultStatusName(int status) {
        switch (status) {
            case 1:
                return "SUCCESS";
            case 2:
                return "FAILURE";
            case 3:
                return "SKIP";
            default:
                return "UNKNOWN";
        }
    }
} 