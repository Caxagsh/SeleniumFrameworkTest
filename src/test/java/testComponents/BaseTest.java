package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/alexanderdubrovin/IdeaProjects/SeleniumFramework/src/test/resources/GlobalData.properties");
        prop.load(fis);
        String browserName  = prop.getProperty("browser")!=null?System.getProperty("browser") : prop.getProperty("browser");

        if(browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        
        else if(browserName.equals("firefox")) {
            //firefox
        } else if (browserName.equals("edge")) {
            //edge
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToLendingPage();
        return landingPage;

    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8
        );
        //String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;


    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        driver.close();
    }
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file =  new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
}
