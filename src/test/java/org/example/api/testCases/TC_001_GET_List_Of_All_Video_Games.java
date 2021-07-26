package org.example.api.testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.example.api.base.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_001_GET_List_Of_All_Video_Games extends TestBase {

    @BeforeAll
    public void getAllGames() throws InterruptedException {

        logger.info("**** Started TC_001_GET_List_Of_All_Video_Games ******");

        RestAssured.baseURI = "http://localhost:8010/app";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/videogames");
        Thread.sleep(5);
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

        if (Integer.parseInt(length) < 1000)
            logger.warn("Response Body Length is less than 1000");
        assertTrue(Integer.parseInt(length)>1000);
    }

    @AfterAll
    public void tearDown(){
        logger.info("**** Finish TC_001_GET_List_Of_All_Video_Games *******");
    }
}
