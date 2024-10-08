package com.cercli.employees.dbms.functional.employee;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/employee")
@SelectPackages("com.cercli.employees.dbms.functional.employee")
public class EmployeesFeature {
}
