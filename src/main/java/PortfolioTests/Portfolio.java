package PortfolioTests;

import PortfolioTests.Utilities.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static PortfolioTests.Utilities.Utils.driver;

public class Portfolio {
    @BeforeMethod
    public void setUp() {
        Utils.instantiate();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test(testName = "Access Positive Test", description = "Check that you can access the 01 Drums page", groups = {"01Drums"})
    public void accessDrumsPositiveTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();
        Assert.assertEquals(driver.getTitle(), "JS30: 01 Drums");
    }

    @Test(testName = "Access Negative Test", description = "Check that you can access the correct 01Drums page", groups = {"01Drums"})
    public void accessDrumsNegativeTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();
        Assert.assertNotEquals(driver.getTitle(), "Lorem Ipsum");
    }

    @Test(testName = "Click 'A' Test", description = "Check that you can click the 'A' key and the correct response triggers", groups = {"01Drums"})
    public void aKeyTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();
        WebElement aKey = driver.findElement(By.xpath("/html/body/div/div[1]"));
        Actions builder = new Actions(driver);
        Action sendAKey = builder.moveToElement(aKey).sendKeys("A").build();
        sendAKey.perform();
        Assert.assertEquals(aKey.getAttribute("class"), "key playing");
    }

    @Test(testName = "Back from Drums Test", description = "Check that you can go back from the 01 Drums page", groups = {"01Drums"})
    public void backFromDrumsTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[1]")).click();
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(), "JavaScript 30 Portfolio by Andrei Dobra");
    }

    @Test(testName = "Access 02 Clock Test", description = "Check that you can access the 02 Clock page", groups = {"02Clock"})
    public void accessClockTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[2]")).click();
        Assert.assertEquals(driver.getTitle(), "Clock");
    }

    @Test(testName = "02 Clock Functionality Test", description = "Check that the 02 Clock page functions correctly", groups = {"02Clock"})
    public void verifyClockTest() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div[2]/div[2]")).click();
        String firstStyle = driver.findElement(By.xpath("/html/body/div/div/div[3]")).getAttribute("style");
        Thread.sleep(2000);
        String secondStyle = driver.findElement(By.xpath("/html/body/div/div/div[3]")).getAttribute("style");
        Assert.assertNotEquals(firstStyle, secondStyle, "There are no differences in the two styles");
    }

    @Test(testName = "Access 03 CSS Variable Test", description = "Check that you can access the 03 CSS Variable page", groups = {"03CSS"})
    public void accessCSSTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[3]")).click();
        Assert.assertEquals(driver.getTitle(), "CSS Variable update");
    }

    @Test(testName = "03 CSS Variable Functionality Test", description = "Check that the 03 CSS Variable page functions correctly", groups = {"03CSS"})
    public void verifyCSSTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[3]")).click();
        String initialStyle = driver.findElement(By.xpath("/html")).getAttribute("style");
        WebElement slider = driver.findElement(By.xpath("/html/body/div/p[1]/input[2]"));
        slider.click();
        slider.sendKeys(Keys.ARROW_RIGHT);
        String secondStyle = driver.findElement(By.xpath("/html")).getAttribute("style");
        Assert.assertNotEquals(initialStyle, secondStyle);
    }

    @Test(testName = "Access 04 Flex Gallery Test", description = "Check that you can access the 04 Flex Gallery page", groups = {"04Flex"})
    public void accessFlexGalleryTest() {

        driver.findElement(By.xpath("/html/body/div[2]/div[5]")).click();
        Assert.assertEquals(driver.getTitle(), "Flex Panels \uD83D\uDCAA");
        driver.manage().window().maximize();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = driver.getTitle().concat(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
            System.out.println(fileName);
            FileUtils.copyFile(scrFile, new File("target/" + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot.");
            e.printStackTrace();
        }
    }

}
