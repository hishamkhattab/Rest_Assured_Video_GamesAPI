package org.example.api.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class DataProvide {

    public static String getID(){
        //return RandomStringUtils.randomNumeric(2);
        return ("20");
    }

    public static String getName(){
        /*String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("War"+generatedString);*/
        return ("Sonic");
    }

    public static String getDate(){
        /*String generatedString = RandomStringUtils.randomNumeric(2);
        return ("2021-07-"+generatedString);*/
        return ("2021-07-25");
    }

    public static String getScore(){
        //return RandomStringUtils.randomNumeric(2);
        return ("9");
    }

    public static String getCategory(){
        /*String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("R-"+generatedString);*/
        return ("RPG");
    }

    public static String getRating(){
        /*String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("+"+generatedString);*/
        return ("universal");
    }


}
