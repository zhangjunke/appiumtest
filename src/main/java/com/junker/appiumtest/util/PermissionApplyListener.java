package com.junker.appiumtest.util;

import com.junker.appiumtest.cases.StartTest;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

//持续监测是否有权限申请弹出框
public class PermissionApplyListener extends Thread{
    private static org.apache.log4j.Logger logger = Logger.getLogger(PermissionApplyListener.class);
    private  AndroidDriver ad;
    public PermissionApplyListener(AndroidDriver driver){
        ad=driver;
    }
    public void run() {
        while(true){
            By by1=new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"永久记住选择\")");
            By by2=new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"允许\")");
            try {
                if (ad.findElement(by1).isDisplayed()) {
                    logger.info("出现权限申请弹窗");
                    ad.findElement(by1).click();
                    logger.info("选择永久记住");
                    Thread.sleep(3000);
                    ad.findElement(by2).click();
                    logger.info("允许权限申请");
                }
            }catch(Exception e){

            }
            }
    }
    public void start () {
        Thread thread=new Thread(this);
        thread.start();
    }
}
