package com.realfarmsystem.rfs.StaticData;

public class UrlData {
    private final static String apiHost = "http://api.realfarmsystem.com/";
    private final static String auth = apiHost + "/v1/auth/token";
    private final static String userInfo = apiHost + "/v1/auth/info";
    private final static String myChinchillas = apiHost + "/v1/chinchillas/my";
    private final static String shelfs = apiHost+"/v1/shelfs/my";
    private final static String shelfsFullInfo = apiHost + "/v1/Shelfs/full/"; // + shelfId
    private final static String myFur = apiHost + "/v1/FurTracking/my";
    private final static String addFur = apiHost + "/v1/FurTracking/111515";
    private final static String sortingsAllocation = apiHost + "/v1/FurTrackingStatistics/sortingsAllocation";
    private final static String sortingsAllocationPerCountry = apiHost + "/v1/FurTrackingStatistics/sortingsAllocationPerCountry";
    private final static String userUpdatePassword = apiHost + "/v1/Users/UpdatePassword/";
    private final static String news = apiHost + "/v1/News/";

    public static String getMyFur() {
        return myFur;
    }

    public static String getAuth() {
        return auth;
    }

    public static String getUserInfo() {
        return userInfo;
    }

    public static String getMyChinchillas() {
        return myChinchillas;
    }

    public static String getShelfs() {
        return shelfs;
    }

    public static String getAddFur() {
        return addFur;
    }

    public static String getSortingsAllocation() {
        return sortingsAllocation;
    }

    public static String getSortingsAllocationPerCountry() {
        return sortingsAllocationPerCountry;
    }

    public static String getUserUpdatePassword() {
        return userUpdatePassword;
    }

    public static String getNews() {
        return news;
    }

    public static String getShelfsFullInfo() {
        return shelfsFullInfo;
    }
}
