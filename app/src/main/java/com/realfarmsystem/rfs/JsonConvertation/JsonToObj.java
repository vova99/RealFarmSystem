package com.realfarmsystem.rfs.JsonConvertation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class JsonToObj<T extends Object> {
    private T t;
    public T convert(byte[] responseBody, Class<T> tClass){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object =(JsonObject) parser.parse(new String(responseBody));
        JsonElement user  = object.get("result");
        System.out.println(object.toString());
        t = gson.fromJson(user.getAsJsonObject(),(Class<T>) tClass);
        System.out.println(t.toString());

        return t;
    }
}
