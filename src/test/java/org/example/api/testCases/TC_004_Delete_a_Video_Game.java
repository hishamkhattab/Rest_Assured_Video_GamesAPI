package org.example.api.testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.example.api.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class TC_004_Delete_a_Video_Game extends TestBase {

    @BeforeClass
    public void deleteVideoGame(){
        logger.info("***************** Started TC_004_Delete_a_Video_Game *******************");

        RestAssured.baseURI = "http://localhost:8010/app/videogames";

        httpRequest = RestAssured.given();
        httpRequest.header("Accept","application/json");
        response = httpRequest.request(Method.DELETE,"/"+ID);

    }

    @Test
    public void validateResponseBody(){
        logger.info("*************** Check Response Body ******************");

        String body = response.getBody().asString();
        logger.info("Response Body==>"+body);

        assertTrue(body.contains("Record Deleted Successfully"));
    }


    @Test
    public void validateResponseStatus() {
        logger.info("*************** Check Response Status Code ***********");

        int statusCode = response.getStatusCode();
        logger.info("Response Status Code==>"+statusCode);

        Assert.assertEquals(200,statusCode);
    }

    @Test
    public void validateResponseContentHeader(){
        logger.info("*************** Check Content-Type Header ************");

        String content = response.getHeader("Content-Type");
        logger.info("Response Content-Type Header==>"+content);

        Assert.assertEquals("application/json",content);

    }

    @Test
    public void validateResponseLength(){
        logger.info("*************** Check Content-Length Header **********");

        String length = response.getHeader("Content-length");
        logger.info("Response Content-Type Length==>"+length);

        if (Integer.parseInt(length) < 10)
            logger.warn("Response Body Length is less than 10");

        Assert.assertTrue(Integer.parseInt(length)>10);
    }

    @AfterClass
    public void tearDown(){
        logger.info("**** Finish TC_004_Delete_a_Video_Game *******");
    }

}
