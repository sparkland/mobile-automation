package com.mobile.hooks;

import com.mobile.base.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks extends Base {

    Base base;
    AppiumDriver<MobileElement> driver;

    public Hooks(Base base) {
        this.base = base;
        this.driver = this.base.driver;
    }

    @Before
    public void setUp() throws IOException {
        base.getDriver("Android", true);
    }

    @After
    public void teardown() {
        base.quitDriver();
    }
}
