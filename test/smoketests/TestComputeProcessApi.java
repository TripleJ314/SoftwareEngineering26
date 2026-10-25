package smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeProcessApiImpl;
import processapi.DataStoreApi;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class TestComputeProcessApi {
	
	@Test
	public void testCompute() {
		// Mock the data storage dependency
		DataStoreApi dataStore = mock(DataStoreApi.class);
		
		// Create Conceptual api implementation
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl(dataStore);
		
		// Mock the IntegerData that is given to compute component
		IntegerData input = mock(IntegerData.class);
		
		// Specify what the mock returns
		when(input.getIntegers()).thenReturn(List.of(1, 10, 25));
		
		// Compute component takes data, computes and returns the output
		IntegerData result = computeProcess.compute(input);
		
		//Compute component should return data
		// Test will have it returned as null and fail
		assertNotNull(result);
	}
	
	@Test
	public void testGetDataStore() {
		// Mock the data storage dependency
		DataStoreApi dataStore = mock(DataStoreApi.class);
		
		// Create Conceptual api implementation
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl(dataStore);
		
		// Verify the same data store is returned
		assertSame(dataStore, computeProcess.getDataStore());
	}
	
	@Test
	public void testLesserLargestPrime() {
		// Mock the data storage dependency
		DataStoreApi dataStore = mock(DataStoreApi.class);
				
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl(dataStore);
		
		IntegerData input = new ListIntegerData(List.of(1, 10, 25));
		
		IntegerData results = computeProcess.compute(input);
		
		assertEquals(List.of(0, 7, 23), results.getIntegers());
	}
}
