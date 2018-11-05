package com.junker.appiumtest.util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class DriverUtil {
    private static org.apache.log4j.Logger logger = Logger.getLogger(DriverUtil.class);

    public static void changeActivity(AndroidDriver driver,String activityName){
        String packageName= null;
        try {
            packageName = MyProperties.getProperties("apkPackage");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        driver.startActivity(new Activity(packageName,activityName));
    }

    public static void clickElementIfExists(AndroidDriver driver, By by){
        try {
            int i=0;
            WebElement wl=null;
            while(i<3&&!(wl=driver.findElement(by)).isDisplayed()) {
                Thread.sleep(1000);
                i++;
            }
            wl.click();
        }catch(NoSuchElementException e) {
            ScreenShot.screenShot(driver);
            logger.info("未找到该控件: " + by);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public static void sendKeysIfExists(AndroidDriver driver, By by,String text){
        try {
            int i=0;
            WebElement we=null;
            while(i<3&&!(we=driver.findElement(by)).isDisplayed()) {
                Thread.sleep(1000);
                i++;
            }
            we.sendKeys(text);
        }catch(NoSuchElementException e) {
            ScreenShot.screenShot(driver);
            logger.info("未找到该控件: " + by);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public static void acceptPermissionRequest(WebDriver driver){
        By by1=new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"永久记住选择\")");
            By by2=new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"允许\")");
            try {
                if (driver.findElement(by1).isDisplayed()) {
                    logger.info("出现权限申请弹窗");
                    driver.findElement(by1).click();
                    logger.info("选择永久记住");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    driver.findElement(by2).click();
                    logger.info("允许权限申请");
                }
            }catch(Exception e){
                logger.info("未发现弹窗");
            }
        }
    }
