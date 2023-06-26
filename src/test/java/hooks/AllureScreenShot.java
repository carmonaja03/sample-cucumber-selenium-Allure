package hooks;

import automation.library.common.Property;
import automation.library.common.TestContext;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AllureScreenShot {
    /**
     * Adds a screenshot of the current page displayed.
     * @param driver
     * @param name filename of the screenshot
     */
    public static void attachScreenShot(WebDriver driver, String name) {
        String scrollingScreenShot = null;
        byte[] screenshot = null;

        scrollingScreenShot =   Property.getProperties(TestContext.getInstance().testdataGet("paths").toString()).getString("scrollingScreenshot");
        if (scrollingScreenShot != null) {
            screenshot =(scrollingScreenShot.equalsIgnoreCase("true") ? grabScrollingScreenshot(driver, "scrollingScreenshot") : grabDisplayedAreaScreenShot(driver));
        } else {
            screenshot = grabDisplayedAreaScreenShot(driver);
        }
        Allure.getLifecycle().addAttachment(name, "image/png", "png", screenshot);
    }

    public static byte[] grabDisplayedAreaScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] grabScrollingScreenshot(WebDriver driver, String test) {
        ru.yandex.qatools.ashot.Screenshot screenshot;
        if (System.getProperties().get("os.name").toString().contains("Mac")) {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)).takeScreenshot(driver);
        } else {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}



