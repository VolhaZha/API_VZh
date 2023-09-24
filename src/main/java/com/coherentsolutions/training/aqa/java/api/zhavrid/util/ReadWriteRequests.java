package com.coherentsolutions.training.aqa.java.api.zhavrid.util;

public class ReadWriteRequests {

    public static void writeRequest(String writeToken) {
        HttpClientUtil writeRequests = new HttpClientUtil();
        writeRequests.makePostRequest(writeToken);
    }

    public static void readRequest(String readToken) {
        HttpClientUtil readRequests = new HttpClientUtil();
        readRequests.makeGetRequest(readToken);
    }

    public static void writeRequestPut(String writeToken) {
        HttpClientUtil writeRequests = new HttpClientUtil();
        writeRequests.makePutRequest(writeToken);
    }

    public static void writeRequestDelete(String writeToken) {
        HttpClientUtil writeRequests = new HttpClientUtil();
        writeRequests.makeDeleteRequest(writeToken);
    }

}
