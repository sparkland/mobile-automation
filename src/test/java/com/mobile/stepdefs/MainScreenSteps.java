package com.mobile.stepdefs;

import com.mobile.actions.MainScreenActions;
import com.mobile.base.Base;
import io.cucumber.java.en.Given;

public class MainScreenSteps extends Base {

    private Base base;
    private MainScreenActions mainScreenActions;

    public MainScreenSteps(Base base) {
        this.base = base;
        mainScreenActions = new MainScreenActions(base);
    }

    @Given("I enter {string} in the text field")
    public void iEnterInTheTextField(String value) {
        mainScreenActions.enterText(value);
    }
}
