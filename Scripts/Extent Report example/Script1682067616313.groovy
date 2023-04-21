import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.ExtentReports as ExtentReports
import com.relevantcodes.extentreports.ExtentTest as ExtentTest
import com.relevantcodes.extentreports.LogStatus as LogStatus
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

String execID = RunConfiguration.getExecutionSourceName()

ExtentReports extent = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.setupExtentReport'(execID)

String tcID = RunConfiguration.getExecutionSourceName().toString()

ExtentTest extentTest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.startExtentTest'(tcID, 'Extent Test', 
    extent)

WebDriver driver = DriverFactory.startNewBrowser(DriverFactory.getExecutedBrowser())

extentTest.log(LogStatus.INFO, 'Browser Launched')

//driver = DriverFactory.getWebDriver()

String baseUrl = 'https://www.google.com'

driver.get(baseUrl + '/')

WebUI.maximizeWindow()

extentTest.log(LogStatus.INFO, 'Navigated to www.google.com')

RunConfiguration.getProjectDir()
WebUI.takeScreenshot('Screenshots/'+"googleScreenshot.png")
extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addScreenCapture(RunConfiguration.getProjectDir()+"/Screenshots/googleScreenshot.png"));

// get title.
String title = driver.getTitle()

println(title)

extentTest.log(LogStatus.INFO, 'Get the WebSite title')

// Verify title.
//Assert.assertTrue(title.contains("Search hundreds of energy supplier offers near you"));
if (title.equalsIgnoreCase('Google')) {
    extentTest.log(LogStatus.PASS, 'Title verified')
} else {
    String dest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.getScreeshot'(driver, execID, tcID)

    extentTest.log(LogStatus.FAIL, 'Error Snapshot : ' + extentTest.addScreenCapture(dest))
}

// Close application.
driver.quit()

extentTest.log(LogStatus.INFO, 'Browser closed')

CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.tearDownTest'(driver, extent, extentTest)

