package com.gathergrid;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.gathergrid.helpers.user.UserValidationHelperTest;
import com.gathergrid.service.imp.EventServiceImpTest;

@Suite
@SelectClasses({ UserValidationHelperTest.class, EventServiceImpTest.class })
public class Suit {

}
