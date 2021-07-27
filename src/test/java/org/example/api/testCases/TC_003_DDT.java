package org.example.api.testCases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.example.api.base.TestBase;
import org.example.api.utilities.ReadExcel;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import static org.junit.Assert.assertTrue;


public class TC_003_DDT extends TestBase {



    @DataProvider(name = "dataProvider")
    public Object[][]testData() throws IOException {
        String path = "D:\\WorkSpace\\Rest_Assured_Full_Project\\Rest_Assured_Video_GameAPI\\src\\test\\resources\\Book1.xlsx";
        ReadExcel util = new ReadExcel(path);
        int rowNumber = util.getRowCount("sheet1");
        int colNumber = util.getCellCount("sheet1",1);
        Object[][] data = new Object[rowNumber][colNumber];
        for (int r=1; r<=rowNumber; r++){
            for (int c=0; c<colNumber; c++){
                data[r-1][c] = util.getCellData("sheet1",r,c);
            }
        }
        return data;
    }



    @Test(dataProvider = "dataProvider")
    public void validateDataDrivenTest(
            String id,
            String name,
            String releaseDate,
            String reviewScore,
            String category,
            String  rating)
    {
        logger.info("***************** Started TC_003_DDT *******************");

        RestAssured.baseURI = "http://localhost:8010/app";
        httpRequest = RestAssured.given();

        HashMap<String, String> jsonPath = new HashMap<>();
        jsonPath.put("id",id);
        jsonPath.put("name",name);
        jsonPath.put("releaseDate",releaseDate);
        jsonPath.put("reviewScore",reviewScore);
        jsonPath.put("category",category);
        jsonPath.put("rating",rating);

        JSONObject jsonBody = new JSONObject(jsonPath);

        httpRequest.header("Content-Type","application/json");
        httpRequest.header("Accept","application/json");
        httpRequest.body(jsonBody);

        response = httpRequest.request(Method.POST,"/videogames");

    }

    @Test (dependsOnMethods = {"validateDataDrivenTest"})
    public void validateResponseBody(){
        logger.info("*************** Check Response Body ******************");

        String body = response.getBody().asString();
        logger.info("Response Body==>"+body);

        assertTrue(body.contains("Record Added Successfully"));
    }

    @Test (dependsOnMethods = {"validateDataDrivenTest"})
    public void validateResponseStatus() {
        logger.info("*************** Check Response Status Code ***********");

        int statusCode = response.getStatusCode();
        logger.info("Response Status Code==>"+statusCode);

        Assert.assertEquals(200,statusCode);
    }



    @Test (dependsOnMethods = {"validateDataDrivenTest"})
    public void validateResponseContentHeader(){
        logger.info("*************** Check Content-Type Header ************");

        String content = response.getHeader("Content-Type");
        logger.info("Response Content-Type Header==>"+content);

        Assert.assertEquals("application/json",content);

    }

    @Test (dependsOnMethods = {"validateDataDrivenTest"})
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
        logger.info("**** Finish TC_003_DDT *******");
    }
}
