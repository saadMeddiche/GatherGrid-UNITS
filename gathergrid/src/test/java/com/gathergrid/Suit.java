package com.gathergrid;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.gathergrid.helpers.user.UserValidationHelperTest;
import com.gathergrid.service.imp.EventServiceImpTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ UserValidationHelperTest.class, EventServiceImpTest.class })
public class Suit {

}
