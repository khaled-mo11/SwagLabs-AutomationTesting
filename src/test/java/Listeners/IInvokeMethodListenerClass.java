package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokeMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        //log I want to listen on test start
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        //Utility.takeFullScreenshot(getDriver(), testResult.getName(), new P02_HomePage(getDriver()).getNumberOfProductsOnCartIcon());
        //log that test case passed and if it failed take screenshot and log a message
        if (testResult.getStatus() == ITestResult.FAILURE) {

            LogsUtils.info("Test Case " + testResult.getName() + "failed");
            Utility.takeScreenshot(getDriver(), testResult.getName());

        }


    }

}
