package com.dinh.demoloadmore.service;

public class APIUntil {
    private static String baseURL = "http://appmusic-test.herokuapp.com/"; // https://mobishops.herokuapp.com/ http://vtnshop.herokuapp.com/

    public static APIService getServer() {
        return APIClient.getApiClientLSP(baseURL).create(APIService.class);
    }
}
