package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class Phase2 {
    public APIs api;

    // Constructor to inject APIs
    public Phase2(APIs api) {
        this.api = api;
    }
    public void parseGoalJson(String jsonResponse) throws Exception {

        // Parse the response JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray goalArray = jsonObject.getJSONArray("goal");

        // Iterate over the goal array (2D array)
        for (int i = 0; i < goalArray.length(); i++) {
            JSONArray rowArray = goalArray.getJSONArray(i);
            for (int j = 0; j < rowArray.length(); j++) {
                String cellValue = rowArray.getString(j);

                if(cellValue.equals("SPACE")) continue;

                if(cellValue.equals("POLYANET"))
                {
                    String polyBody = api.generatePOLYANETBody(i, j);
                    api.postPOLYanetAPICall(polyBody);
                }
                else if(cellValue.contains("COMETH"))
                {
                    String direction = cellValue.split("_")[0];
                    String comethBody = api.generateComethBody(i, j, direction.toLowerCase());
                    api.postComethAPICall(comethBody);
                }
                else if(cellValue.contains("SOLOON")) {
                    String color = cellValue.split("_")[0];
                    String soloonBody = api.generateSoloonBody(i, j, color.toLowerCase());
                    api.postSoloonAPICall(soloonBody);

                }
            }
        }
    }
}
