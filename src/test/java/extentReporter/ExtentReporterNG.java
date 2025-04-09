package extentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporterNG {
    public static ExtentReports getReportObject(){

        // Указываем путь к файлу отчета
        String path = System.getProperty("user.dir") + "//report//index.html";

        // Создаем объект File для проверки существования директории
        File reportFolder = new File(System.getProperty("user.dir") + "//report");

        // Проверяем, существует ли папка report, если нет, создаем её
        if (!reportFolder.exists()) {
            boolean created = reportFolder.mkdirs();
            if (created) {
                System.out.println("Directory 'report' created successfully.");
            } else {
                System.out.println("Failed to create 'report' directory.");
            }
        }

        // Создаем ExtentSparkReporter с указанным путем
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("TestReport");
        reporter.config().setDocumentTitle("Test Results");

        // Создаем объект ExtentReports и прикрепляем репортер
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Alex Dubrovin");

        return extent;
    }
}
