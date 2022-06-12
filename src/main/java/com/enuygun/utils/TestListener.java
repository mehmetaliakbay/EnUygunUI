package com.enuygun.utils;

import com.enuygun.base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListener extends BaseTest implements ITestListener {



    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        ExtentReportManager.flushReport();
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
       ExtentReportManager.createExtentTest(iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
       ExtentReportManager.getExtentTest().pass(iTestResult.getMethod().getDescription()+" test complete.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String failedTest = iTestResult.getName();
        String screenshotDir = System.getProperty("user.dir")+"/extent-reports/img/";
        takeScreenshot(failedTest);
            ExtentReportManager.getExtentTest().fail("Capturing Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotDir+failedTest+".png").build());

        ExtentReportManager.getExtentTest().fail(iTestResult.getThrowable().getLocalizedMessage());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
      ExtentReportManager.getExtentTest().skip(getTestMethodName(iTestResult)+" test skipped");
    }


    /**
     * Takes screenshot of whole page and allows you to name the screenshot
     *
     * @param screenshotName The screenshot file name
     */
    private void takeScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/extent-reports/img/" + screenshotName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
