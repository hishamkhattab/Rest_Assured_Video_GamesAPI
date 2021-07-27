package org.example.api.testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.example.api.base.TestBase;
import org.example.api.utilities.DataProvide;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

public class TC_002_Add_a_New_Video_Game extends TestBase {

    String id = DataProvide.getID();
    String name = DataProvide.getName();
    String releaseDate = DataProvide.getDate();
    String category = DataProvide.getCategory();
    String rating = DataProvide.getRating();
    String score = DataProvide.getScore();

    @BeforeClass
    public void getAllGames() throws InterruptedException {

        logger.info("**** Started TC_002_Add_a_New_Video_Game ******");

        RestAssured.baseURI = "http://localhost:8010/app";
        httpRequest = RestAssured.given();

        HashMap<String,String> data = new HashMap<>();
        data.put("id",id);
        data.put("name",name);
        data.put("releaseDate",releaseDate);
        data.put("reviewScore",score);
        data.put("category",category);
        data.put("rating",rating);

        httpRequest.header("Content-Type","application/json");
        httpRequest.header("Accept","application/json");

        JSONObject jsonData = new JSONObject(data);
        httpRequest.body(jsonData);


        response = httpRequest.request(Method.POST,"/videogames");
        Thread.sleep(5);
    }


    @Test
    public void validateResponseBody(){
        logger.info("*************** Check Response Body ******************");

         String body = response.getBody().asString();
         logger.info("Response Body==>"+body);

         assertTrue(body.contains("Record Added Successfully"));
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

        if (Integer.parseInt(length) < 30)
            logger.warn("Response Body Length is less than 30");

        assertTrue(Integer.parseInt(length)>20);
    }


    @AfterClass
    public void tearDown(){
            logger.info("**** Finish TC_001_GET_List_Of_All_Video_Games *******");
        }
}

