package com.junker.appiumtest.util;

import io.appium.java_client.events.EventFiringWebDriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;

public class Capabilities {
    private static Logger logger =Logger.getLogger(Capabilities.class);
    public  AndroidDriver driver;
    public  Capabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "uiautomator2");
        try {
            capabilities.setCapability("deviceName", MyProperties.getProperties("deviceName"));
            capabilities.setCapability("automationName", MyProperties.getProperties("automationName"));
            capabilities.setCapability("platformName", MyProperties.getProperties("platformName"));
            capabilities.setCapability("platformVersion", MyProperties.getProperties("platformVersion"));
            capabilities.setCapability("app", MyProperties.getProperties("apkPath"));//自动安装app
            capabilities.setCapability("noReset", false);//重装app
            driver = new AndroidDriver(new URL(MyProperties.getProperties("appiumServerUrl")), capabilities);
            driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver,new EventListener());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
