package com.coherentsolutions.training.aqa.java.api.zhavrid.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    private static TokenManager instance;
    private String writeScopeToken;
    private String readScopeToken;

    private TokenManager() {
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public String getTokenFromResponse(String response) {
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(response);
            return obj.get("access_token").toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public String getWriteScopeToken() {
        if (writeScopeToken == null) {
            String response = retrieveToken("write");
            writeScopeToken = this.getTokenFromResponse(response);
        }
        return writeScopeToken;
    }

    public String getReadScopeToken() {
        if (readScopeToken == null) {
            String response = retrieveToken("read");
            readScopeToken = this.getTokenFromResponse(response);
        }
        return readScopeToken;
    }

    private String retrieveToken(String scope) {
        int port = Integer.parseInt(PropertiesFileReader.getProperty(PropertyKey.PORT));
        String user = PropertiesFileReader.getProperty(PropertyKey.DEFAULT_USER);
        String pass = PropertiesFileReader.getProperty(PropertyKey.DEFAULT_PASS);
        String uri = PropertiesFileReader.getProperty(PropertyKey.URI);


        try {
            final HttpHost targetHost = new HttpHost("localhost", port, "http");
            final BasicCredentialsProvider provider = new BasicCredentialsProvider();
            AuthScope authScope = new AuthScope(targetHost);
            provider.setCredentials(authScope, new UsernamePasswordCredentials(user, pass));

            HttpPost request = new HttpPost(uri);
            List<BasicNameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
            parameters.add(new BasicNameValuePair("scope", scope));

            request.setEntity(new UrlEncodedFormEntity(parameters, StandardCharsets.UTF_8));

            try (CloseableHttpClient client = HttpClients.custom()
                    .setDefaultCredentialsProvider(provider)
                    .build();
                 CloseableHttpResponse response = client.execute(targetHost, request)) {

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity);
                    }
                } else {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve token due to an IOException.", e);
        }
        throw new RuntimeException("Failed to retrieve token for an unknown reason.");
    }
}
