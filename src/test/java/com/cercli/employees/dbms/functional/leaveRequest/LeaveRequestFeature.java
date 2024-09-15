package com.cercli.employees.dbms.functional.leaveRequest;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/leaveRequest")
@SelectPackages("com.cercli.employees.dbms.functional.leaveRequest")
public class LeaveRequestFeature {
}
