package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class APIs {
    final Config config;
    final int waitTime = 10000;
    int retryCount = 0;

    final HttpClient client;

    public APIs(HttpClient client, Config config) {
        this.client = client;
        this.config = config;
    }
    public void postPOLYanetAPICall(String body) throws Exception
    {
        retryChecker(retryCount++);

        HttpRequest request = newBuilder()
                .uri(URI.create("https://challenge.crossmint.io/api/polyanets"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.print("Poly: " + response.statusCode() + "\n");
        } else {
            System.out.println("Poly API Error: " + response.statusCode()  + " " + response.body() + "\n");
        }
    }

    public void postSoloonAPICall(String body) throws Exception
    {
        retryChecker(retryCount++);

        HttpRequest request = newBuilder()
                .uri(URI.create("https://challenge.crossmint.io/api/soloons"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.print("Soloon: " + response.statusCode() + "\n");
        } else {
            System.out.println("Soloon API Error: " + response.statusCode()  + " " + response.body() + "\n");
        }
    }

    public HttpResponse<String> postGoalAPICall() throws Exception
    {
        retryChecker(retryCount++);

        HttpRequest request = newBuilder()
                .uri(URI.create("https://challenge.crossmint.io/api/map/" + config.getCandidateId() + "/goal"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void postComethAPICall(String body) throws Exception
    {
        retryChecker(retryCount++);

        HttpRequest request = newBuilder()
                .uri(URI.create("https://challenge.crossmint.io/api/comeths"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.print("Cometh: " + response.statusCode() + "\n");
        } else {
            System.out.println("Cometh API Error: " + response.statusCode() + " " + response.body() + "\n");
        }
    }

    public String generatePOLYANETBody(int row, int column)
    {
        return "{\"row\": \"" + row + "\", \"column\": \"" + column + "\",\"candidateId\":\"" + config.getCandidateId() + "\"}";
    }

    public String generateSoloonBody(int row, int column, String color)
    {
        return "{\"row\": \"" + row + "\", \"column\": \"" + column + "\",\"candidateId\":\"" + config.getCandidateId() + "\",\"color\": \"" + color + "\"}";
    }

    public String generateComethBody(int row, int column, String direction)
    {
        return "{\"row\": \"" + row + "\", \"column\": \"" + column + "\",\"candidateId\":\"" + config.getCandidateId() + "\",\"direction\": \"" + direction + "\"}";
    }

    protected void retryChecker(int retryCount) throws Exception
    {
        if(retryCount != 0 && retryCount % 9 == 0)
        {
            Thread.sleep(waitTime);
            System.out.print(retryCount + "\n");
        }
    }
}
