package Tests.US_10;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
import utilities.ExtentReportsListener;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class US10ScreenUtils {

    /**
     * US_10 testi için ekran görüntüsü alır ve ExtentReport'a ekler.
     * @param //testType "tooShortTextBox", "goodTextBox", "strongTextBox" gibi test tipi
     */
    public static void captureScreen(String message) {
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            String path = "target/screenShots/screenshot_" + date + ".jpeg";
            File srcFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(path));

            // Screenshot + mesaj aynı satırda rapora ekleniyor
            ExtentReportsListener.getExtentTest();
                   // .info(message,
                           // MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/" + path).build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}