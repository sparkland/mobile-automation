package com.mobile.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.mobile.utilities.Ports.getAvailablePort;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    public AppiumDriver<MobileElement> driver;
    public AppiumDriverLocalService service;
    private int appiumPort;

    /**
     *  path: '/wd/hub',
     *   port: 4723,
     *   capabilities: {
     *     platformName: "Android",
     *     platformVersion: "8",
     *     deviceName: "Android Emulator",
     *     app: "/path/to/the/downloaded/ApiDemos-debug.apk",
     *     appPackage: "io.appium.android.apis",
     *     appActivity: ".view.TextFields",
     *     automationName: "UiAutomator2"
     */

    public AppiumDriver<MobileElement> getDriver(String driverType, boolean localAppium) throws IOException {
        String ipAddress = localAppium ? "127.0.0.1" : "";
        if (driver == null) {
            appiumPort = Integer.parseInt(getAvailablePort());
            configureAppiumService(driverType, ipAddress);
            startService();
            configureAppiumDriver(driverType, ipAddress);
        }
        return driver;
    }

    public void startService() {
        service.start();
    }

    public void stopService() {
        service.stop();
    }

    public void quitDriver() {

        if (driver != null)
            driver.quit();

        if (service != null)
            if (service.isRunning())
                stopService();
    }

    private void configureAppiumDriver(String driverType, String ipAddress) throws MalformedURLException {
        driver = new AndroidDriver<>(new URL("http://" + ipAddress + ":" + appiumPort + "/wd/hub"),
                getDesiredCapabilities(driverType));
    }

    private DesiredCapabilities getDesiredCapabilities(String driverType) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (driverType.equals("Android")) {
            capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability("app", "app/android/demo.apk");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".view.TextFields");
            //capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability(AndroidMobileCapabilityType.APPLICATION_NAME, "UIAutomator2");
        }
        return capabilities;
    }

    private void configureAppiumService(String driverType, String ipAddress) throws IOException {
        if (driverType.equals("Android")) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .usingPort(appiumPort)
                    .withIPAddress(ipAddress)
                    .withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, getAvailablePort())
                    .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, getAvailablePort())
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
        }
    }
}
