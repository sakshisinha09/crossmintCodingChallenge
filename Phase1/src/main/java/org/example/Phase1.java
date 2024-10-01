package org.example;

public class Phase1 {
    APIs api;

    // Constructor to inject APIs
    public Phase1(APIs api) {
        this.api = api;
    }

    public void generateIndices() throws Exception {
        int row = 2, column = 2; //generates indices (2,2), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8)
        while(row <= 8 && column <= 8)
        {
            String body = api.generatePOLYANETBody(row, column);
            api.postPOLYanetAPICall(body);

            row++;
            column++;
        }

        row = 2;
        column = 8;
        while(row < column) //generates indices (2,8), (8,2), (3,7), (7,3), (4,6), (6,4)
        {
            String body = api.generatePOLYANETBody(row, column); // (2,8)
            api.postPOLYanetAPICall(body);

            String body_reverse = api.generatePOLYANETBody(column, row); // (8,2)
            api.postPOLYanetAPICall(body_reverse);

            row++;
            column--;
        }
    }
}
