import org.example.APIs;
import org.example.Phase1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class Phase1Test {

    APIs mockApi;
    Phase1 phase1;

    @BeforeEach
    void setUp() {
        // Mock the APIs class
        mockApi = Mockito.mock(APIs.class);
        // Inject the mock into the Phase1 instance
        phase1 = new Phase1(mockApi);
    }

    @Test
    void testGenerateIndicesDiagonal() throws Exception {
        // Set up the behavior of the mocked APIs for diagonal indices
        when(mockApi.generatePOLYANETBody(anyInt(), anyInt())).thenReturn("mockedBody");

        // Call the method to test
        phase1.generateIndices();

        // Verify if `generatePOLYANETBody` was called with the correct diagonal arguments
        verify(mockApi, times(1)).generatePOLYANETBody(2, 2);
        verify(mockApi, times(1)).generatePOLYANETBody(3, 3);
        verify(mockApi, times(1)).generatePOLYANETBody(4, 4);
        verify(mockApi, times(1)).generatePOLYANETBody(5, 5);
        verify(mockApi, times(1)).generatePOLYANETBody(6, 6);
        verify(mockApi, times(1)).generatePOLYANETBody(7, 7);
        verify(mockApi, times(1)).generatePOLYANETBody(8, 8);

        verify(mockApi, times(1)).generatePOLYANETBody(2, 8);
        verify(mockApi, times(1)).generatePOLYANETBody(8, 2);
        verify(mockApi, times(1)).generatePOLYANETBody(3, 7);
        verify(mockApi, times(1)).generatePOLYANETBody(7, 3);
        verify(mockApi, times(1)).generatePOLYANETBody(4, 6);
        verify(mockApi, times(1)).generatePOLYANETBody(6, 4);

        // Verify if `postPOLYanetAPICall` was called with the correct body
        verify(mockApi, times(13)).postPOLYanetAPICall("mockedBody");
    }

    @Test
    void testNoExceptionsThrown() throws Exception {
        // Mock the API methods
        when(mockApi.generatePOLYANETBody(anyInt(), anyInt())).thenReturn("mockedBody");
        doNothing().when(mockApi).postPOLYanetAPICall(anyString());

        // Call the method and ensure no exceptions are thrown
        phase1.generateIndices();
    }
}
