package com.coherentsolutions.training.aqa.java.api.zhavrid.util;

import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.logging.Logger;

public class HttpClientUtil {
    private static final Logger logger = Logger.getLogger(ReadWriteRequests.class.getName());

    String url = PropertiesFileReader.getProperty(PropertyKey.URL);

    public void makeGetRequest(String token) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", "Bearer " + token);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    logger.info("GET Response: " + responseContent);
                } else {
                    logger.info("GET Request failed with status code: " + statusCode);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void makePostRequest(String token) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Authorization", "Bearer " + token);
            String jsonPayload = "{\"age\": 30, \"name\": \"Olka\", \"sex\": \"FEMALE\"}";
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 201) {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    logger.info("POST Response: " + responseContent);

                } else {
                    logger.info("POST Request failed with status code: " + statusCode);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makePutRequest(String token) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setHeader("Authorization", "Bearer " + token);
            String jsonPayload = "{\"userNewValues\": {\"age\": 300,\"name\": \"Olka\",\"sex\": \"FEMALE\"}," +
                    "\"userToChange\": {\"age\": 30, \"name\": \"Olka\", \"sex\": \"FEMALE\"}}";
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPut.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    logger.info("PUT Response: " + responseContent);
                } else {
                    logger.info("PUT Request failed with status code: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeDeleteRequest(String token) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
            httpDelete.setHeader("Authorization", "Bearer " + token);
            String jsonPayload = "{\"age\": 300,\"name\": \"Olka\",\"sex\": \"FEMALE\"}";
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpDelete.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    logger.info("DELETE Request was successful.");
                } else {
                    logger.info("DELETE Request failed with status code: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}