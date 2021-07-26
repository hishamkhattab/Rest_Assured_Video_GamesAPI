package org.example.api.testCases;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.example.api.base.TestBase;
import org.example.api.utilities.ReadExcel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_002_Add_a_New_Video_Game extends TestBase {

    @BeforeAll
    public void getAllGames() throws InterruptedException {

        logger.info("**** Started TC_002_Add_a_New_Video_Game ******");

        RestAssured.baseURI = "http://localhost:8010/app";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.POST,"/videogames");
        Thread.sleep(5);
    }

    public Object[][]testData() throws IOException {
        String path = "D:\\WorkSpace\\Rest_Assured_Full_Project\\Rest_Assured_Video_GameAPI\\src\\test\\resources\\Book1.xlsx";
        ReadExcel util = new ReadExcel(path);
        int rowNumber = util.getRowCount("sheet1");
        int colNumber = util.getCellCount("sheet1",1);
        Object data[][] = new Object[rowNumber][colNumber];
        for (int r=1; r<=rowNumber; r++){
            for (int c=0; c<colNumber; c++){
                data[r-1][c] = util.getCellData("sheet1",r,c);
            }
        }
        return data;
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void validateDataDrivenTest(
        String id,
        String name,
        String releaseDate,
        String reviewScore,
        String category,
        String  rating)
    {
        HashMap<String, String> jsonPath = new HashMap<String, String>();
        jsonPath.put("id",id);
        jsonPath.put("name",name);
        jsonPath.put("releaseDate",releaseDate);
        jsonPath.put("reviewScore",reviewScore);
        jsonPath.put("category",category);
        jsonPath.put("rating",rating);

        JSONObject jsonBody = new JSONObject(jsonPath);

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(jsonBody);
        Response response = httpRequest.request(Method.POST,"/videogames");


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

