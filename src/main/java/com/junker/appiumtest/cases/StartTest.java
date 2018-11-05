package com.junker.appiumtest.cases;

import com.junker.appiumtest.performance.PerformanceMonitor;
import com.junker.appiumtest.util.Capabilities;
import com.junker.appiumtest.util.DriverUtil;
import com.junker.appiumtest.util.MyProperties;
import com.junker.appiumtest.util.PermissionApplyListener;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;

public class StartTest {
    private static Logger logger =Logger.getLogger(StartTest.class);
    public static AndroidDriver adriver;

    @BeforeSuite
    public static void getDriver(){
        Capabilities capa=new Capabilities();
        adriver=capa.driver;
    }

    @BeforeSuite
    public void startTest(){
        logger.info( "|||||||BEGINING|||||||" );
        /*PermissionApplyListener pl = new PermissionApplyListener(adriver);
        pl.start();*/
        String performance="";
        try {
            String screenShotPath=MyProperties.getProperties("screenshotPath");
            FileUtils.deleteDirectory(new File(screenShotPath));//删除测试截图目录
            //NetworkConnection.setNetwork(driver,8);//设置连接wifi
            performance= MyProperties.getProperties("performance");
            if(performance.equals("true")){
                PerformanceMonitor.emmageeMonitor(adriver);//如果开关打开，则开启性能监控
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @BeforeSuite
    public static void lanch(){
        String apkMainActivity=null;
        try {
            apkMainActivity=MyProperties.getProperties("apkMainActivity");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        String currentPackage=adriver.getCurrentPackage();
        if(!currentPackage.equals("entrance.**.cn.***")){
            DriverUtil.changeActivity(adriver,apkMainActivity);//如果当前app不是被测app则切换窗口
        }
    }


}
