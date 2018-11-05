package com.junker.appiumtest.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;

public class NetworkConnection {
    public static void setNetwork(AndroidDriver driver,int type){
        switch(type) {
            case 0:
                driver.setNetworkSpeed(NetworkSpeed.GSM);//连接GSM
                break;
            case 1:
                driver.setNetworkSpeed(NetworkSpeed.SCSD);//连接SCSD
                break;
            case 2:
                driver.setNetworkSpeed(NetworkSpeed.GPRS);//连接GPRS
                break;
            case 3:
                driver.setNetworkSpeed(NetworkSpeed.EDGE);//连接EDGE
                break;
            case 4:
                driver.setNetworkSpeed(NetworkSpeed.UMTS);//连接UMTS
                break;
            case 5:
                driver.setNetworkSpeed(NetworkSpeed.HSDPA);//连接HSDPA
                break;
            case 6:
                driver.setNetworkSpeed(NetworkSpeed.LTE);//连接LTE
                break;
            case 7:
                driver.setNetworkSpeed(NetworkSpeed.EVDO);//连接EVDO
                break;
            case 8:
                driver.setNetworkSpeed(NetworkSpeed.FULL);//连接FULL
                break;
        }
    }

}
