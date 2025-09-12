package Tests.US_6;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.ExtentReportsListener;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class US6ScreenUtils {

    /**
     * US_10 testi için ekran görüntüsü alır ve ExtentReport'a ekler.
     * @param testType "tooShortTextBox", "goodTextBox", "strongTextBox" gibi test tipi
     */
    public static void captureScreen(String testType) {
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            String path = "target/screenShots/" + testType + "_" + date + ".jpeg";
            File srcFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(path));

            // Burada getter kullanıyoruz
           // ExtentReportsListener.getExtentTest().addScreenCaptureFromPath(System.getProperty("user.dir") + "/" + path);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}