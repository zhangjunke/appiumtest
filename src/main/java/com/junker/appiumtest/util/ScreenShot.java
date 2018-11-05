package com.junker.appiumtest.util;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class ScreenShot {
    private static Logger logger =Logger.getLogger(ScreenShot.class);
    private static int num=1;
    public static void screenShot(AndroidDriver driver){
        File screenShotFile=driver.getScreenshotAs(OutputType.FILE);
        String screenShotPath="";
        try {
            screenShotPath=MyProperties.getProperties("screenshotPath");
            FileUtils.copyFile(screenShotFile,new File(screenShotPath+num+".png"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        num++;
    }
}
