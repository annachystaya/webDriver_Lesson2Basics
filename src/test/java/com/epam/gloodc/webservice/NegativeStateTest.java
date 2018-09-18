package com.epam.gloodc.webservice;

import com.epam.gloodc.uitesting.utilities.CustomListener;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class NegativeStateTest extends BaseWebServiceTest {

    private static final String URL = "http://services.groupkt.com/state/get/USA/QA";

    @Test
    public void checkIncorrectState() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(URL).asJson();
        logger.info("Response status is: " + jsonResponse.getStatus());
        Assert.assertEquals(jsonResponse.getStatus(), 200);
        logger.info("Json Response Body: " + "\n\t" + jsonResponse.getBody());
        Assert.assertTrue(jsonResponse.getBody().toString().contains("No matching state found for requested code"));
    }

}
