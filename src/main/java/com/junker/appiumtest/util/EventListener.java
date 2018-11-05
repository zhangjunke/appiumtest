package com.junker.appiumtest.util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import io.appium.java_client.functions.ExpectedCondition;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventListener implements AppiumWebDriverEventListener {
    private static org.apache.log4j.Logger logger = Logger.getLogger(DriverUtil.class);

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver arg0) {}
    @Override
    public void afterNavigateBack(WebDriver arg0) {}
    @Override
    public void beforeNavigateForward(WebDriver arg0) {}
    @Override
    public void afterNavigateForward(WebDriver arg0) {}
    @Override
    public void beforeNavigateRefresh(WebDriver arg0) {}
    @Override
    public void afterNavigateRefresh(WebDriver arg0) {}

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        DriverUtil.acceptPermissionRequest(webDriver);
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {}
    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {}
    @Override
    public void beforeScript(String arg0, WebDriver arg1) {}
    @Override
    public void afterScript(String arg0, WebDriver arg1) {}

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
        public void onException(Throwable error, WebDriver arg1) {}

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

}
