package testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageObject.LandingPage;
import org.apache.commons.io.FileUtils;
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
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//GlobalData.properties");
        prop.load(fis);
        String browserName  = prop.getProperty("browser");

        if(browserName.equals("chrome")) {
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
}
