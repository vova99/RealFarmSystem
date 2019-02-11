package com.realfarmsystem.rfs.JsonConvertation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class JsonToArrayList<T extends Object> {
    private ArrayList<T> tArrayList = new ArrayList<>();

    public ArrayList<T> convert(byte[] responseBody,Class<T> tClass){
        tArrayList.clear();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        JsonObject object =(JsonObject) parser.parse(new String(responseBody));
        JsonArray jsonArray = object.getAsJsonArray("result");
        System.out.println(jsonArray.toString());
        for (int i=0; i< jsonArray.size();i++){
            object = (JsonObject) parser.parse(jsonArray.get(i).toString());
            System.out.println(object.toString());
            T t = gson.fromJson(object,(Class<T>) tClass);
            System.out.println(t.toString());
            tArrayList.add(t);
        }
        return tArrayList;
    }

    public T convert(JsonObject object,Class<T> tClass) {
        Gson gson = new Gson();

        System.out.println(object.toString());
        T t = gson.fromJson(object,(Class<T>) tClass);
        System.out.println(t.toString());

        return t;
    }
}
