import org.example.APIs;
import org.example.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class APIsTest {

    @InjectMocks
    private APIs api;

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @Mock
    private Config mockConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.api = new APIs(mockHttpClient, mockConfig);
        when(mockConfig.getCandidateId()).thenReturn("12345");
    }

    @Test
    void testPostPOLYanetAPICall_Success() throws Exception {

        String requestBody = "{\"row\": \"2\", \"column\": \"3\",\"candidateId\":\"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);

        api.postPOLYanetAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(200, mockHttpResponse.statusCode());
    }

    @Test
    void testPostPOLYanetAPICall_Error() throws Exception {

        String requestBody = "{\"row\": \"2\", \"column\": \"3\",\"candidateId\":\"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(400);
        when(mockHttpResponse.body()).thenReturn("Bad Request");

        api.postPOLYanetAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(400, mockHttpResponse.statusCode());
        assertEquals("Bad Request", mockHttpResponse.body());
    }

    @Test
    void testPostSoloonAPICall_Success() throws Exception {

        String requestBody = "{\"row\": \"2\", \"column\": \"3\", \"candidateId\": \"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);

        api.postSoloonAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(200, mockHttpResponse.statusCode());
    }

    @Test
    void testPostSoloonAPICall_Error() throws Exception {
        String requestBody = "{\"row\": \"2\", \"column\": \"3\", \"candidateId\": \"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(400);
        when(mockHttpResponse.body()).thenReturn("Bad Request");

        api.postSoloonAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(400, mockHttpResponse.statusCode());
        assertEquals("Bad Request", mockHttpResponse.body());
    }

    @Test
    void testPostGoalAPICall() throws Exception {
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);
        when(mockConfig.getCandidateId()).thenReturn("12345");

        HttpResponse<String> response = api.postGoalAPICall();

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(200, response.statusCode());
    }

    @Test
    void testPostComethAPICall_Success() throws Exception {
        String requestBody = "{\"row\": \"2\", \"column\": \"3\", \"candidateId\": \"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);

        api.postComethAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(200, mockHttpResponse.statusCode());
    }

    @Test
    void testPostComethAPICall_Error() throws Exception {
        String requestBody = "{\"row\": \"2\", \"column\": \"3\", \"candidateId\": \"12345\"}";
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(400);
        when(mockHttpResponse.body()).thenReturn("Bad Request");

        api.postComethAPICall(requestBody);

        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(400, mockHttpResponse.statusCode());
        assertEquals("Bad Request", mockHttpResponse.body());
    }

    @Test
    void testGeneratePOLYANETBody() {
        int row = 2;
        int column = 3;
        String expectedJson = "{\"row\": \"2\", \"column\": \"3\",\"candidateId\":\"12345\"}";

        String actualJson = api.generatePOLYANETBody(row, column);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void testGenerateSoloonBody() {
        int row = 1;
        int column = 4;
        String color = "blue";
        String expectedJson = "{\"row\": \"1\", \"column\": \"4\",\"candidateId\":\"12345\",\"color\": \"blue\"}";

        String actualJson = api.generateSoloonBody(row, column, color);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void testGenerateComethBody() {
        int row = 5;
        int column = 7;
        String direction = "left";
        String expectedJson = "{\"row\": \"5\", \"column\": \"7\",\"candidateId\":\"12345\",\"direction\": \"left\"}";

        String actualJson = api.generateComethBody(row, column, direction);

        assertEquals(expectedJson, actualJson);
    }


}
