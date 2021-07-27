package org.example.api.testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.example.api.base.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TC_005_GET_Single_Video_Game extends TestBase {

    @BeforeClass
    public void getSingleGame(){
        logger.info("**** Started TC_005_GET_Single_Video_Game ******");


        RestAssured.baseURI = "http://localhost:8010/app/videogames";

        httpRequest =RestAssured.given();
        httpRequest.header("Accept","application/json");

        response = httpRequest.request(Method.GET,"/"+ID);
    }

    @Test
    public void validateResponseBody(){
        logger.info("*************** Check Response Body ******************");

        String body = response.getBody().asString();
        logger.info("Response Body==>"+body);

        assertNotNull(body);
    }
    @Test
    public void validateResponseStatus(){
        logger.info("*************** Check Response Status Code ***********");

        int statusCode = response.getStatusCode();
        logger.info("Response Status Code==>"+statusCode);

        assertEquals(200,statusCode);
    }

    @Test
    public void validateContentType(){
        logger.info("*************** Check Content-Type Header ************");

        String content = response.getHeader("Content-Type");
        logger.info("Response Content-Type Header==>"+content);

        assertEquals("application/json",content);
    }

    @Test
    public void validateResponseLength(){
        logger.info("*************** Check Content-Length Header **********");

        String length = response.getHeader("Content-length");
        logger.info("Response Content-Type Length==>"+length);

        if (Integer.parseInt(length) < 100)
            logger.warn("Response Body Length is less than 100");
        assertTrue(Integer.parseInt(length)>100);
    }

    @AfterClass
    public void tearDown(){
        logger.info("**** Finish TC_005_GET_Single_Video_Game *******");
    }
}
