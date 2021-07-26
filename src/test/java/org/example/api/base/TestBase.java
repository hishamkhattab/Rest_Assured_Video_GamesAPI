package org.example.api.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public static String ID = "14";
    public Logger logger;

    @BeforeAll
    public void setup(){
        //Generate the logs
        logger = Logger.getLogger("EmployeesRestAPI");  //added Logger
        PropertyConfigurator.configure("Log4j.properties"); //add logger
        logger.setLevel(Level.DEBUG);
    }

}
