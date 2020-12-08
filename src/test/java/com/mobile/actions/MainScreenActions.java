package com.mobile.actions;

import com.mobile.base.Base;
import com.mobile.screens.MainScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MainScreenActions extends Base {

    Base base;
    AppiumDriver<MobileElement> driver;
    MainScreen mainScreen;

    public MainScreenActions(Base base) {
        this.base = base;
        this.driver = this.base.driver;
        mainScreen = new MainScreen(driver);
    }

    public void enterText(String value) {
        mainScreen.getTextField().sendKeys(value);
    }
}
