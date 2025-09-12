package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
public class ExtentReportsListener implements ITestListener, IRetryAnalyzer, IAnnotationTransformer {
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static ExtentTest extentTest;
    /**
     * onstart==> Tum testlerden once tek bir kez cagrilir
     * Böylece icine yazdigimiz kodlar sayesinde test başladığında raporlama baslatilir.
     *
     * @param context TestNG context nesnesi
     */
    @Override
    public void onStart(ITestContext context) {
        String reportName = context.getCurrentXmlTest().getName();
        if (extentReports == null) { // ExtentReports nesnesi oluşturulmamış ise
            // Bu objecti raporları oluşturmak ve yönetmek için kullanacağız
            extentReports = new ExtentReports();
            // Öncelikle oluşturmak istediğimiz HTML raporu projemizde nerede saklamak istiyorsak bir dosya yolu oluşturmalıyız
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            String path = "target/extentReport/" + reportName + " " + date + " htmlReport.html";
            extentHtmlReporter = new ExtentHtmlReporter(path);
            // ExtentsReports'a HTML raporlayıcı ekler, ve bu raporun HTML formatında oluşturulmasını sağlar
            extentReports.attachReporter(extentHtmlReporter);
            // HTML raporun belge başlığını ayarlar
            extentHtmlReporter.config().setDocumentTitle("Batch 343");
            // Raporda gösterilecek olan genel başlığı ayarlar
            extentHtmlReporter.config().setReportName(reportName);
            // Bu HTML raporunda görmek isteyebileceğimiz diğer bilgileri aşağıdaki şekilde ekleyebiliriz
            extentReports.setSystemInfo("<span style='color:blue; font-weight:bold'><i class='fa fa-server'></i> Environment:</span>", " QA");
            extentReports.setSystemInfo("<span style='color:green; font-weight:bold'><i class='fa fa-chrome'></i> Browser:</span>", " Chrome");
            extentReports.setSystemInfo("<span style='color:purple; font-weight:bold'><i class='fa fa-user'></i> Test Automation Engineer:</span>", " Ali Can");
        }
    }
    /**
     * onTestStart==> her bir @Test methodundan once bir kez cagrilir
     * Böylece Test methoduna başlandığında, testName ve description verileri alınarak rapora eklenir.
     *
     * @param result Test sonucu nesnesi
     */
    @Override
    public void onTestStart(ITestResult result) {
        //Test methodlarinin parantez icined description parametresiyle yazmis oldugumuz aciklamalari
        // dynamic olarak rapora ne testi oldugunu yansitabiliriz
        // mesela test caselerdeki test objective buraya yazilabilir
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription(); // Metod açıklamasını kullanarak description alıyoruz
        try {
            extentTest = extentReports.createTest(
                    "<span style='color:blue; font-weight:bold'> " + testName + " </span>",
                    "<span style='color:blue; font-weight:bold'> " + description + " </span>");
        } catch (Exception e) {
            // Metod açıklamasını kullanarak description eklenmez ise nullPointer almamak icin method ismini rapora ekliyoruz
            extentTest = extentReports.createTest(
                    "<span style='color:blue; font-weight:bold'> " + testName + " </span>",
                    "<span style='color:blue; font-weight:bold'> " + result.getName() + " </span>");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        /*
        Bu kod, Extent Reports ile bir testin başarıyla tamamlandığını belirten bir mesajı raporlamak için kullanılır.
        Mesaj, HTML biçimlendirmesi ile yeşil ve kalın bir şekilde görüntülenir ve sonuna bir yeşil onay işareti eklenir.
        Bu, test raporunda görsel olarak belirgin bir başarı işareti sağlar.
         */
        String passIsareti = "&#9989";
        extentTestPass("<span style='color:green; font-weight:bold'>" + result.getName() + " Testi başarıyla tamamlandı. </span>" + passIsareti);
    }
    /**
     * onTestFailure==> sadece fail olan testlerden sonra bir kez cagrilir
     * Test başarısız olduğunda, ekran görüntüsü alınır ve raporlama oluşturulur.
     *
     * @param result Test sonucu nesnesi
     */
    @Override
    public void onTestFailure(ITestResult result) {
        /*
        Bu kod, Extent Reports ile bir testin başarısız olduğunu belirten bir mesajı raporlamak için kullanılır.
        Mesaj, HTML biçimlendirmesi ile kırmızı ve kalın bir şekilde görüntülenir ve sonuna bir kırmızı çarpı işareti eklenir.
        Bu, test raporunda görsel olarak belirgin bir başarısızlık işareti sağlar.
         */
        // Fail mesajı ekleme
        String failIsareti = "&#10060";
        extentTestFail("<span style='color:red; font-weight:bold'>" + result.getName() + " Testi başarısız oldu! </span>" + failIsareti);
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            Files.write(Paths.get("target/screenShots/image " + date + ".jpeg"), ts.getScreenshotAs(OutputType.BYTES));
            extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/target/screenShots/image " + date + ".jpeg");
            // hata alindigi icin Açık kalan browseri WebDriver örneğini kapatıyoruz.
            // Driver.quitDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * onTestSkipped==> sadece skip olan (atlanan) testlerden sonra bir kez cagrilir.
     * Test atlandığında, bu durum rapora kaydedilir ve raporda belirtilir.
     *
     * @param result Test sonucu nesnesi
     */
    @Override
    public void onTestSkipped(ITestResult result) {
    /*
    Bu kod, Extent Reports ile bir testin atlandığını belirten bir mesajı raporlamak için kullanılır.
    Mesaj, HTML biçimlendirmesi ile sarı ve kalın bir şekilde görüntülenir ve sonuna yasak işareti eklenir.
    Bu, test raporunda görsel olarak belirgin bir atlama işareti sağlar.
    */
        // Skip mesajı ekleme
        String skipIsareti = "<img src='URL_TO_IMAGE' alt='skip_icon' style='width:16px;height:16px;'>";  // Simge olarak resim ekleniyor
        String message = "<span style='color:orange; font-weight:bold'> Hata nedeniyle Test atlandı! \n" + result.getName() + " testi tekrar calistirilacak</span>" + skipIsareti;
        if (extentTest != null) {
            extentTest.skip(message);
        }
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            Files.write(Paths.get("target/screenShots/image " + date + ".jpeg"), ts.getScreenshotAs(OutputType.BYTES));
            extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/target/screenShots/image " + date + ".jpeg");
            //hata alindigi icin Açık kalan browseri WebDriver örneğini kapatıyoruz.
            //Driver.quitDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * onfinish==> Tum testlerden sonra tek bir kez cagrilir
     * Böylece tüm testler bittiğinde raporlama kapatılır.
     *
     * @param context TestNG context nesnesi
     */
    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    /**
     * Test başarılı olduğunda çalışacak metod.
     *
     * @param message Başarılı test mesajı
     */
    public static void extentTestPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }
    /**
     * Test başarısız olduğunda çalışacak metod.
     *
     * @param message Başarısız test mesajı
     */
    public static void extentTestFail(String message) {
        if (extentTest != null) {
            extentTest.fail(message);
        }
    }
    /**
     * Test hakkında bilgi ekler.
     *
     * @param message Test bilgi mesajı
     */
    public static void extentTestInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }
    /**
     * onTestSuccess==> sadece pass olan testlerden sonra bir kez cagrilir
     * Test başarılı olduğunda, başarılı mesajı eklenir.
     *
     * @param result Test sonucu nesnesi
     */


    // Her test için deneme (retry) sayısını takip eden bir Map. Test metodu ismiyle sayıyı eşleştiriyoruz.
    private static Map<String, Integer> retryCounts = new HashMap<>();
    // Test yeniden çalıştırma (retry) işlemi için kullanılır. Eğer test başarısız olursa, burada belirtilen sayı kadar yeniden çalıştırılır.
    private static final int maxRetryCount = 1;
    @Override
    public boolean retry(ITestResult result) {
        String testMethodName = result.getMethod().getMethodName();  // Test metodu ismini alır.
        // Eğer bu metod için daha önce bir deneme yapılmamışsa, 0 olarak başlatır.
        retryCounts.putIfAbsent(testMethodName, 0);
        int retryCount = retryCounts.get(testMethodName);  // Şu anki deneme sayısını alır.
        // Eğer deneme sayısı maksimumdan küçükse, yeniden çalıştırır.
        if (retryCount < maxRetryCount) {
            retryCount++;  // Deneme sayısını artırır.
            retryCounts.put(testMethodName, retryCount);  // Günceller.
            return true;  // Testi yeniden çalıştır.
        }
        return false;  // Test yeniden çalıştırılmayacak.
    }
    // Bu metod, TestNG'nin her test metodu için retry mekanizmasını eklemesi için kullanılır.
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // Her test metoduna retry analyzer ekler. Bu sayede test başarısız olursa belirlenen sayıda yeniden çalıştırılır.
        annotation.setRetryAnalyzer(ExtentReportsListener.class);
    }


    /** * Testin herhangi bir noktasında ekran görüntüsü almak ve rapora eklemek için kullanılır.
     * Bu metot statiktir ve direkt olarak `ExtentReportListener.addScreenshotToReport(...)` şeklinde çağrılabilir.
     *
     * @param logMessage Ekran görüntüsü altına eklenecek mesaj veya açıklama.
     */
    public static void addScreenshotToReport(String logMessage) {
        if (extentTest == null) {
            return;
        }

        try {
            WebDriver driver = Driver.getDriver();
            if (driver == null) {
                return;
            }
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            String destDir = "target/screenshots";
            File destDirFile = new File(destDir);
            if (!destDirFile.exists()) {
                FileUtils.forceMkdir(destDirFile);
            }
            String destPath = destDir + "/image_" + date + ".png";
            File dest = new File(destPath);
            FileUtils.copyFile(src, dest);

            // Raporun, ekran görüntüsü dosyasını bulabilmesi için görece yolu kullan
            String relativePath = "../screenshots/image_" + date + ".png";
            extentTest.log(Status.INFO, logMessage, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

        } catch (IOException | RuntimeException e) {
            if (extentTest != null) {
                extentTest.log(Status.ERROR, "Ekran görüntüsü alınırken bir hata oluştu: " + e.getMessage());
            }
        }
    }


}