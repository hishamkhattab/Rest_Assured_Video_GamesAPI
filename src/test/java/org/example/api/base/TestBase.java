package org.example.api.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static String ID = "14";
    public Logger logger;

    @BeforeClass
    public void setup(){
        //Generate the logs
        logger = Logger.getLogger("EmployeesRestAPI");  //added Logger
        PropertyConfigurator.configure("Log4j.properties"); //add logger
        logger.setLevel(Level.DEBUG);
    }

}
