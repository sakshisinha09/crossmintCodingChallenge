package org.example;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class Main {
    public static HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS) // Enable follow redirects
            .build();
    static Config config;
    static APIs api = new APIs(client, config);
    static Phase2 phase2 = new Phase2(api);

    public static void main(String[] args) throws Exception {
        // Create a new thread for Phase1 execution
        Thread phase1Thread = new Thread(() -> {
            Phase1 p1 = new Phase1(api);
            try {
                p1.generateIndices();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Phase1 executed!" + "\n");
        });

        // Start Phase1 thread
        phase1Thread.start();

        // Wait for Phase1 to complete
        phase1Thread.join();

        // Introduce a delay before executing Phase2 to avoid being rate-limited
        try {
            System.out.println("Waiting for a few seconds before executing Phase2...");
            Thread.sleep(api.waitTime);  // Delay for waitTime milliseconds
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted: " + e.getMessage() + "\n");
        }

        // After Phase1 completes, proceed with Phase2
        HttpResponse<String> response = api.postGoalAPICall();
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            phase2.parseGoalJson(responseBody);
        } else {
            System.out.println("Goal API Error: " + response.statusCode() + " " + response.body() + "\n");
        }

        System.out.println("Phase2 executed!"+ "\n");
    }
}
