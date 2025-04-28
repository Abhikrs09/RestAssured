package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TestListeners implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListeners.class);
	private Map<String, Long> testStartTimes = new HashMap<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Started: {}", result.getName());
		testStartTimes.put(result.getName(), System.currentTimeMillis());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed: {}", result.getName());
		logExecutionTime(result);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed: {}", result.getName());
		
		// Create RetryAnalyzer instance
		RetryAnalyzer retryAnalyzer = new RetryAnalyzer();
		
		if (result.getThrowable() != null) {
			logger.error("Error details: {}", result.getThrowable().getMessage());
		}

		// Check if test should be retried
		if (retryAnalyzer.retry(result)) {
			logger.info("Retrying test: {}", result.getName());
			result.setStatus(ITestResult.SKIP);
			
			try {
				result.getMethod().getConstructorOrMethod().getMethod()
					  .invoke(result.getInstance());
				
				result.setStatus(ITestResult.SUCCESS);
				logger.info("Retry successful for test: {}", result.getName());
				
			} catch (Exception e) {
				logger.error("Retry failed: {}", e.getMessage());
				result.setStatus(ITestResult.FAILURE);
				result.setThrowable(e);
			}
		} else {
			logger.error("Test failed after all retries: {} - Error: {}", 
				result.getName(), 
				result.getThrowable().getMessage());
		}
		
		logExecutionTime(result);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Skipped: {}", result.getName());
		if (result.getThrowable() != null) {
			logger.info("Skip reason: {}", result.getThrowable().getMessage());
		}
		logExecutionTime(result);
	}
	
	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started: {}", context.getName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Finished: {}", context.getName());
		
		StringBuilder summary = new StringBuilder("\nTest Execution Summary:")
			.append("\nTotal Tests: ").append(context.getAllTestMethods().length)
			.append("\nPassed Tests: ").append(context.getPassedTests().size())
			.append("\nFailed Tests: ").append(context.getFailedTests().size())
			.append("\nSkipped Tests: ").append(context.getSkippedTests().size());
		
		logger.info(summary.toString());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Test Failed Within Success Percentage: {}", result.getName());
	}

	private void logExecutionTime(ITestResult result) {
		Long startTime = testStartTimes.get(result.getName());
		if (startTime != null) {
			long executionTime = System.currentTimeMillis() - startTime;
			logger.info("Test execution time for {}: {} ms", result.getName(), executionTime);
		}
	}
}
