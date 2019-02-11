package com.realfarmsystem.rfs.JsonConvertation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.realfarmsystem.rfs.Entity.Shelf;

public class BuilderShelfConverter {
    public static Shelf convert(String responseBody){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        JsonObject object = (JsonObject) parser.parse(responseBody);
        JsonObject shelf =  object.getAsJsonObject("result");

        JsonArray rows = shelf.getAsJsonArray("RowsOrderedByFlor");

        for (int i=0; i<rows.size();i++) {
            JsonElement rowEl = rows.get(i);
            JsonObject row = rowEl.getAsJsonObject();
            JsonObject male = row.get("male").getAsJsonObject();
        }




        return null;
    }
}
