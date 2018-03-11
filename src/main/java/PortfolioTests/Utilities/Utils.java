package PortfolioTests.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public final class Utils {

    public static WebDriver driver;

    private Utils() {
    }

    public static WebDriver instantiate() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://andreidbr.github.io/JS30/");
        return driver;
    }
}
