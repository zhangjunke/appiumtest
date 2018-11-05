package com.junker.appiumtest.performance;

import com.junker.appiumtest.util.DriverUtil;
import com.junker.appiumtest.util.MyProperties;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import java.io.IOException;

public class PerformanceMonitor {
    private static Activity main=null;
    private static String emmageePackage=null;
    private static Logger logger =Logger.getLogger(PerformanceMonitor.class);

    public static void emmageeMonitor(AndroidDriver adriver){
        String emmagePath= null;
        String emmageeMainActivity=null;
        try {
            emmagePath = MyProperties.getProperties("emmageePath");
            emmageePackage=MyProperties.getProperties("emmageePackage");
            emmageeMainActivity=MyProperties.getProperties("emmageeMainActivity");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if(adriver.isAppInstalled(emmageePackage)){
            adriver.removeApp(emmageePackage);
        }
        adriver.installApp(emmagePath);//安装emmagee
        PerformanceMonitor.setEmail(adriver);//设置邮件，用于接收测试报告
        PerformanceMonitor.shutSuspensionWindow(adriver);//关闭悬浮窗
        main=new Activity(emmageePackage,emmageeMainActivity);
        adriver.startActivity(main);
        DriverUtil.clickElementIfExists(adriver, new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"小瞳卫士\")"));
        DriverUtil.clickElementIfExists(adriver, new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"开始测试\")"));//选择小瞳卫士开始测试
    }

    public static void shutSuspensionWindow(AndroidDriver adriver){
        //切换到设置界面
        String activity=null;
        try {
            emmageePackage = MyProperties.getProperties("emmageePackage");
            activity = MyProperties.getProperties("emmageeSettingActivity");
        }catch (IOException e) {
            logger.error(e.getMessage());
        }
        Activity setting=new Activity(emmageePackage,activity);
        adriver.startActivity(setting);
        String sunpensionWindowResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/floating_Window\")";
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(sunpensionWindowResourceId));//关闭悬浮窗
    }

    public static void setEmail(AndroidDriver adriver){
        String sender="";
        String recipients="";
        String emailpwd="";
        String smtp="";
        try {
            sender=MyProperties.getProperties("sender");
            recipients=MyProperties.getProperties("recipients");
            emailpwd=MyProperties.getProperties("emailpwd");
            smtp=MyProperties.getProperties("smtp");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        //切换到邮件设置界面
        String activity=null;
        try {
            emmageePackage=MyProperties.getProperties("emmageePackage");
            activity=MyProperties.getProperties("emmageeEmailSettingActivity");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Activity email=new Activity(emmageePackage,activity);
        adriver.startActivity(email);
        String senderResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/sender\")";
        String pwdResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/password\")";
        String smtpResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/smtp\")";
        String recipientsResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/recipients\")";
        String setResourceId="new UiSelector().resourceId(\"com.netease.qa.emmagee:id/btn_set\")";

        //输入各项信息
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(senderResourceId),sender);
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(pwdResourceId),emailpwd);
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(smtpResourceId),smtp);
        DriverUtil.sendKeysIfExists(adriver,new MobileBy.ByAndroidUIAutomator(recipientsResourceId),recipients);
        DriverUtil.clickElementIfExists(adriver,new MobileBy.ByAndroidUIAutomator(setResourceId));
    }

    public static void stopMonitor(AndroidDriver driver){
        DriverUtil.clickElementIfExists(driver, new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"停止测试\")"));//结束测试
    }
}
