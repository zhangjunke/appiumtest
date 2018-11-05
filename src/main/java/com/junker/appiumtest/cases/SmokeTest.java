package com.junker.appiumtest.cases;

import com.junker.appiumtest.performance.PerformanceMonitor;
import com.junker.appiumtest.util.DriverUtil;
import com.junker.appiumtest.util.MyProperties;
import com.junker.appiumtest.util.ScreenShot;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

public class SmokeTest {
    private static Logger logger = org.apache.log4j.Logger.getLogger(SmokeTest.class);
    private static AndroidDriver adriver;
    private static String indexResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_*_detect\")";
    private static String settingsButton_loginResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/iv_system_setting\")";
    private static String settingsButton_homeResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_system_setting\")";
    private static WebElement indexEle;
    private static WebElement settingsEle_home;

    @BeforeClass
    public static void Before(){
        adriver=StartTest.adriver;

    }

    @AfterMethod
    public static void screenShot_After(){
        ScreenShot.screenShot(adriver);
    }

    @Test
    public static void homePage(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(settingsButton_loginResourceId));//进入设置界面
    }

    @Test(dependsOnMethods = { "homePage" })
    public static void setting(){
        String titleResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_rl_setting_title\")";
        WebElement settingsTitle=adriver.findElement(new MobileBy.ByAndroidUIAutomator(titleResourceId));
        Assert.assertEquals(settingsTitle.isDisplayed(),true);//检测是否出现设置界面title

        String ip="";
        String port= "";
        try {
            ip=MyProperties.getProperties("serverIp");
            port= MyProperties.getProperties("serverPort");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        String ipResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_et_server_ip\")";
        String portResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_et_server_port\")";
        String backClass="new UiSelector().className(\"android.widget.ImageView\")";
        adriver.findElement(new MobileBy.ByAndroidUIAutomator(ipResourceId)).sendKeys(ip);
        adriver.findElement(new MobileBy.ByAndroidUIAutomator(portResourceId)).sendKeys(port);
        adriver.findElement(new MobileBy.ByAndroidUIAutomator(backClass)).click();//进入登录界面
    }

    @Test(dependsOnMethods = { "setting" })
    public static void login(){
        String usm="admin";
        String pwd="admin";
        String usmResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/lf_login_user_edit\")";
        String pwdResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/lf_login_pwd_edit\")";
        String loginResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_login\")";
        WebElement loginEle=adriver.findElement(new MobileBy.ByAndroidUIAutomator(loginResourceId));
        Assert.assertEquals(loginEle.isDisplayed(),true);//检测登录按钮

        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(usmResourceId),usm);
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(pwdResourceId),pwd);
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(loginResourceId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        indexEle=adriver.findElement(new MobileBy.ByAndroidUIAutomator(indexResourceId));
        Assert.assertEquals(indexEle.isDisplayed(),true);//检测"人脸识别"按钮
        indexEle.click();//点击人脸识别按钮
    }

    @Test(dependsOnMethods = { "login" })
    public static void detect(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        String homeResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_rl_title_home\")";
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(homeResourceId));
    }

    @Test(dependsOnMethods = { "*_detect" })
    public static void adminVerify(){
        String pwdInputResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/lf_input_pwd_edit\")";
        String confirmResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_confirm\")";
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(pwdInputResourceId),"admin");
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(confirmResourceId));
        Assert.assertEquals(indexEle.isDisplayed(),true);//检测是否返回首页
    }

    @Test(dependsOnMethods = { "adminVerify" })
    public static void logout(){
        settingsEle_home=adriver.findElement(new MobileBy.ByAndroidUIAutomator(settingsButton_homeResourceId));
        settingsEle_home.click();
        String logoutResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_exit_login\")";
        WebElement logoutEle=adriver.findElement(new MobileBy.ByAndroidUIAutomator(logoutResourceId));
        Assert.assertEquals(logoutEle.isDisplayed(),true);//检测退出登录按钮
        logoutEle.click();//点击退出登录按钮
        String loginResourceId="new UiSelector().resourceId(\"entrance.**.cn.lfentrancepartner:id/id_tv_login\")";
        WebElement loginEle=adriver.findElement(new MobileBy.ByAndroidUIAutomator(loginResourceId));
        Assert.assertEquals(loginEle.isDisplayed(),true);//检测登录按钮
    }

    @AfterClass
    public static void exit(){
        logger.info("Before_closeAPP:"+adriver.getCurrentPackage());
        adriver.closeApp();
        logger.info("After_closeAPP:"+adriver.getCurrentPackage());
        PerformanceMonitor.stopMonitor(adriver);
        logger.info("|||||||END|||||||");
    }
}
