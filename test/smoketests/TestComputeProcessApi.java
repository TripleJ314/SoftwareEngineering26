package smoketests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeProcessApiImpl;
import processapi.DataStoreApi;
import processapi.IntegerData;

public class TestComputeProcessApi {
	
	@Test
	public void testCompute() {
		// Create Conceptual api implementation
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Mock the IntegerData that is given to compute component
		IntegerData input = mock(IntegerData.class);
		
		// Compute component takes data, computes and returns the output
		IntegerData result = computeProcess.compute(input);
		
		//Compute component shoudl return data
		// Test will have it returned as null and fail
		assertNotNull(result);
	}
	
	@Test
	public void testGetDataStore() {
		// Create Conceptual api implementation
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Get the dataStore used by this process
		DataStoreApi dataStore = computeProcess.getDataStore();
		
		assertNotNull(dataStore);
	}
}
