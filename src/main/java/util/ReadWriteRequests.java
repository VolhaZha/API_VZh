package util;

import constants.UrlConstants;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class ReadWriteRequests {

    public void makeReadRequest(String token) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(UrlConstants.URL);
            httpGet.setHeader("Authorization", "Bearer " + token);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    System.out.println("GET Response: " + responseContent);
                } else {
                    System.err.println("GET Request failed with status code: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeWriteRequest(String token) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(UrlConstants.URL);
            httpPost.setHeader("Authorization", "Bearer " + token);
            String jsonPayload = "{\"age\": 30, \"name\": \"Olka\", \"sex\": \"FEMALE\"}";
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 201) {
                    String responseContent = EntityUtils.toString(response.getEntity());
                    System.out.println("POST Response: " + responseContent);
                } else {
                    System.err.println("POST Request failed with status code: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
