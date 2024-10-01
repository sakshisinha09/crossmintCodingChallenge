import org.example.APIs;
import org.example.Phase2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class Phase2Test {

    APIs mockApi;
    Phase2 phase2;

    @BeforeEach
    void setUp() {
        // Mock APIs class
        mockApi = Mockito.mock(APIs.class);

        // Spy the Phase2 class to inject mocked APIs
        phase2 = Mockito.spy(new Phase2(mockApi));
        phase2.api = mockApi;  // Inject the mocked APIs into the phase2 object
    }

    @Test
    void testParseGoalJsonWithPolyanet() throws Exception {
        // Create a mock JSON response containing "POLYANET"
        JSONObject jsonResponse = new JSONObject();
        JSONArray goalArray = new JSONArray();
        goalArray.put(new JSONArray().put("POLYANET"));
        jsonResponse.put("goal", goalArray);

        // Mock the behavior of APIs methods
        when(mockApi.generatePOLYANETBody(0, 0)).thenReturn("{\"mocked\": \"polyanetBody\"}");
        doNothing().when(mockApi).postPOLYanetAPICall(anyString());

        // Call the method
        phase2.parseGoalJson(jsonResponse.toString());

        // Verify that generatePOLYANETBody and postPOLYanetAPICall were called with correct arguments
        verify(mockApi, times(1)).generatePOLYANETBody(0, 0);
        verify(mockApi, times(1)).postPOLYanetAPICall("{\"mocked\": \"polyanetBody\"}");
    }

    @Test
    void testParseGoalJsonWithCometh() throws Exception {
        // Create a mock JSON response containing "COMETH"
        JSONObject jsonResponse = new JSONObject();
        JSONArray goalArray = new JSONArray();
        goalArray.put(new JSONArray().put("UP_COMETH"));
        jsonResponse.put("goal", goalArray);

        // Mock the behavior of APIs methods
        when(mockApi.generateComethBody(0, 0, "up")).thenReturn("{\"mocked\": \"comethBody\"}");
        doNothing().when(mockApi).postComethAPICall(anyString());

        // Call the method
        phase2.parseGoalJson(jsonResponse.toString());

        // Verify that generateComethBody and postComethAPICall were called with correct arguments
        verify(mockApi, times(1)).generateComethBody(0, 0, "up");
        verify(mockApi, times(1)).postComethAPICall("{\"mocked\": \"comethBody\"}");
    }

    @Test
    void testParseGoalJsonWithSoloon() throws Exception {
        // Create a mock JSON response containing "SOLOON"
        JSONObject jsonResponse = new JSONObject();
        JSONArray goalArray = new JSONArray();
        goalArray.put(new JSONArray().put("RED_SOLOON"));
        jsonResponse.put("goal", goalArray);

        // Mock the behavior of APIs methods
        when(mockApi.generateSoloonBody(0, 0, "red")).thenReturn("{\"mocked\": \"soloonBody\"}");
        doNothing().when(mockApi).postSoloonAPICall(anyString());

        // Call the method
        phase2.parseGoalJson(jsonResponse.toString());

        // Verify that generateSoloonBody and postSoloonAPICall were called with correct arguments
        verify(mockApi, times(1)).generateSoloonBody(0, 0, "red");
        verify(mockApi, times(1)).postSoloonAPICall("{\"mocked\": \"soloonBody\"}");
    }

    @Test
    void testParseGoalJsonWithSpace() throws Exception {
        // Create a mock JSON response containing "SPACE"
        JSONObject jsonResponse = new JSONObject();
        JSONArray goalArray = new JSONArray();
        goalArray.put(new JSONArray().put("SPACE"));
        jsonResponse.put("goal", goalArray);

        // Call the method
        phase2.parseGoalJson(jsonResponse.toString());

        // Verify that none of the API methods were called for "SPACE"
        verify(mockApi, never()).generatePOLYANETBody(anyInt(), anyInt());
        verify(mockApi, never()).generateComethBody(anyInt(), anyInt(), anyString());
        verify(mockApi, never()).generateSoloonBody(anyInt(), anyInt(), anyString());
    }
}

