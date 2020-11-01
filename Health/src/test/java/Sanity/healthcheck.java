package Sanity;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class healthcheck {

    // public WebDriver driver;
    public static ChromeDriver driver;


    //Create ExtendReport and attach reporter(s)
    public static ExtentReports extent;

    //Create a toggle for the given test, adds all log events under it
    public static ExtentTest test;
    public static ExtentTest test1;
    public static ExtentTest test2;
    public static ExtentTest test3;


    @BeforeClass
    public void BeforeClass() throws IOException {


        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(".\\test-output\\extentReport.html");

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Sanity/Validation Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        //attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);


        //name the test and add description
        test = extent.createTest("Website Availability", "Check Website Availability");
        test1 = extent.createTest("Website element", "Check Website Elements");
        test2 = extent.createTest("Website News", "Check Website News");
        test3 = extent.createTest("Appointment", "Make an appointment");

        //add custom system info
        extent.setSystemInfo("Test of", "Health Website");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Shalom Halevy");


        boolean driverEstablish = false;

        try {


            //create chrome instancexe");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\shalomh\\Downloads\\chromedriver_win32\\chromedriver.exe");
            // Create log for Chrome Actions
            System.setProperty("webdriver.chrome.logfile", ".\\test-output\\chromedriver.txt");
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            driver = new ChromeDriver(options);


            //manage timeout
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            //maximize window
            driver.manage().window().maximize();

            driverEstablish = true;

        } catch (Exception e) {
            e.printStackTrace();
            fail("Can't connect chromeDriver");
            test.log(com.aventstack.extentreports.Status.FATAL, "Driver Connection Failed!" + e.getMessage());
            //test.log(Status.FATAL, "Driver Connection Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(com.aventstack.extentreports.Status.PASS, "Driver established successfully");

            }
        }
    }




    @Test(priority = 1)

    public void Test01_openPage() {


        boolean pageOpened = false;
        try {
            driver.get("http://localhost:9000/"); //browse to walla website
            String firstWindowsString = driver.getWindowHandle();
            System.out.println("Window String: " + firstWindowsString);

            pageOpened = true;

        } catch (Exception e) {
            e.printStackTrace();
            test.log(com.aventstack.extentreports.Status.FAIL, "page not found" + e.getMessage());
            pageOpened = false;
        } finally {
            if (pageOpened) {
                test.log(com.aventstack.extentreports.Status.PASS, "Webpage opened successfully");
            }
        }
    }


    @Test (priority = 2)
    public void Test02_WebsiteLink() {

        boolean WebPageLink = false;
        try {
            //print Health Website
            System.out.println(driver.getCurrentUrl());
            String WebPage = "http://localhost:9000/";
            Assert.assertEquals (WebPage,driver.getCurrentUrl());

            //refresh website
            driver.navigate().refresh();

            WebPageLink = true;

        } catch (Exception e) {
            e.printStackTrace();
            test.log(com.aventstack.extentreports.Status.FAIL, "Web Page Link not correct" + e.getMessage());
            WebPageLink = false;
        } finally {
            if (WebPageLink) {
                test.log(com.aventstack.extentreports.Status.PASS, "Web Page Link is correct");
            }
        }
    }




    @Test (priority = 3)
    public void Test03_Home() {

        boolean Home = false;
        try {
            //Check Home Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[1]/a"));

         Home = true;

        } catch (Exception e) {
            e.printStackTrace();
            test1.log(com.aventstack.extentreports.Status.FAIL, "Home Tab not exist" + e.getMessage());
            Home = false;
        } finally {
            if (Home) {
                test1.log(com.aventstack.extentreports.Status.PASS, "Home Tab  exist");
            }
        }
    }



    @Test (priority = 4)
    public void Test04_AboutUs() {

        boolean AboutUs = false;
        try {
            //Check AboutUs Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[2]/a"));

            AboutUs = true;

        } catch (Exception e) {
            e.printStackTrace();
            test1.log(com.aventstack.extentreports.Status.FAIL, "About Us Tab not exist" + e.getMessage());
            AboutUs = false;
        } finally {
            if (AboutUs) {
                test1.log(com.aventstack.extentreports.Status.PASS, "About Us Tab  exist");
            }
        }
    }


    @Test (priority = 5)
    public void Test05_Doctors() {

        boolean Doctors = false;
        try {
            //Check Doctors Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[3]/a"));

            Doctors = true;

        } catch (Exception e) {
            e.printStackTrace();
            test1.log(com.aventstack.extentreports.Status.FAIL, "Doctors Tab not exist" + e.getMessage());
            Doctors = false;
        } finally {
            if (Doctors) {
                test1.log(com.aventstack.extentreports.Status.PASS, "Doctors Tab  exist");
            }
        }
    }


    @Test (priority = 6)
    public void Test06_News() {

        boolean News = false;
        try {
            //Check Doctors Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[3]/a"));

            News = true;

        } catch (Exception e) {
            e.printStackTrace();
            test1.log(com.aventstack.extentreports.Status.FAIL, "News Tab not exist" + e.getMessage());
            News = false;
        } finally {
            if (News) {
                test1.log(com.aventstack.extentreports.Status.PASS, "News Tab  exist");
            }
        }
    }



    @Test (priority = 7)
    public void Test07_Contact() {

        boolean Contact = false;
        try {
            //Check Contact Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[3]/a"));

            Contact = true;

        } catch (Exception e) {
            e.printStackTrace();
            test1.log(com.aventstack.extentreports.Status.FAIL, "Contact Tab not exist" + e.getMessage());
            Contact = false;
        } finally {
            if (Contact) {
                test1.log(com.aventstack.extentreports.Status.PASS, "Contact Tab  exist");
            }
        }
    }


    @Test (priority = 8)
    public void Test08_CheckNews() {

        boolean CheckNews = false;
        try {
            //Click on News Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[4]/a")).click();
            //Click on Amazing Technology
            driver.findElement(By.xpath("//*[@id=\"news\"]/div/div/div[2]/div/div/h3/a")).click();

            CheckNews = true;

        } catch (Exception e) {
            e.printStackTrace();
            test2.log(com.aventstack.extentreports.Status.FAIL, "Navigate between News failed" + e.getMessage());
            CheckNews = false;
        } finally {
            if (CheckNews) {
                test2.log(com.aventstack.extentreports.Status.PASS, "Navigate between News passed successfully");
            }
        }
    }


    @Test (priority = 9)
    public void Test09_appointment() {

        boolean appointment = false;
        try {
            //Click on "Make an appointment" Tab
            driver.findElement(By.xpath("//*[@id=\"sticky-wrapper\"]/section/div/div[2]/ul/li[6]/a")).click();
            //Type full  name in Name field
            driver.findElement(By.id("name")).sendKeys("Shalom Halevy");
            //Type an email address in email address field
            driver.findElement(By.id("email")).sendKeys("shalomh@xglobe.co.il");
            //Type a date in Select Date field
            // driver.findElement(By.id("date")).sendKeys("11/03/2020");
            //Type a Phone Number in
            driver.findElement(By.id("phone")).sendKeys("0528582823");
            //Type a Additional Message
            driver.findElement(By.id("message")).sendKeys("Please Schedule appointment between 16:00 to 18:00");


            appointment = true;

        } catch (Exception e) {
            e.printStackTrace();
            test3.log(com.aventstack.extentreports.Status.FAIL, "Create a new appointment failed" + e.getMessage());
            appointment = false;
        } finally {
            if (appointment) {
                test3.log(com.aventstack.extentreports.Status.PASS, "a new appointment created");
            }
        }

        // hold for 5 minutes
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






    @AfterClass
    public void AfterClassEnds() {
        //wait 5 second
        //close browser and end the session
        driver.quit();
        //build and flush report
        extent.flush();
    }



    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
