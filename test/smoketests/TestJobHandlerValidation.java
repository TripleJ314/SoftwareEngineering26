package smoketests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeProcessApiImpl;
import emptyimplements.DataStoreApiImpl;
import jobhandler.JobHandler;
import networkapi.Delimiters;
import processapi.DataInputSource;
import processapi.DataOutputDestination;

public class TestJobHandlerValidation {
	@Test
	public void testNullDataStore() {
		// Mock the computation component
		ComputeProcessApiImpl computeProcess = mock(ComputeProcessApiImpl.class);
		
		// Verify the null DataStore is rejected
		assertThrows(IllegalArgumentException.class, () -> {
			new JobHandler(null, computeProcess);
		});
	}
	
	@Test
	public void testNullComputeProcess() {
		// Mock the data storage component
		DataStoreApiImpl dataStore = mock(DataStoreApiImpl.class);
				
		// Verify the null ComputeProcess is rejected
		assertThrows(IllegalArgumentException.class, () -> {
			new JobHandler(dataStore, null);
		});
	}
	
	@Test
	public void testNullInputSource() {
		// Mock dependencies and parameters
		ComputeProcessApiImpl computeProcess = mock(ComputeProcessApiImpl.class);

		DataStoreApiImpl dataStore = mock(DataStoreApiImpl.class);
		
		DataOutputDestination output = mock(DataOutputDestination.class);
		
		Delimiters delimiter = mock(Delimiters.class);
		
		JobHandler job = new JobHandler(dataStore, computeProcess);
		
		// verify that a null input source is rejected
		assertThrows(IllegalArgumentException.class, () -> {
			job.runJob(null, output, delimiter);
		});
		
		
	}
	
	@Test
	public void testNullOutputDestination() {
		// Mock dependencies and parameters
		ComputeProcessApiImpl computeProcess = mock(ComputeProcessApiImpl.class);

		DataStoreApiImpl dataStore = mock(DataStoreApiImpl.class);
				
		DataInputSource input = mock(DataInputSource.class);
				
		Delimiters delimiter = mock(Delimiters.class);
				
		JobHandler job = new JobHandler(dataStore, computeProcess);
				
		// verify that a null output destination is rejected
		assertThrows(IllegalArgumentException.class, () -> {
			job.runJob(input, null, delimiter);
		});
	}
	
	@Test
	public void testNullDelmiter() {
		// Mock dependencies and parameters
		ComputeProcessApiImpl computeProcess = mock(ComputeProcessApiImpl.class);

		DataStoreApiImpl dataStore = mock(DataStoreApiImpl.class);
				
		DataInputSource input = mock(DataInputSource.class);

		DataOutputDestination output = mock(DataOutputDestination.class);
	
		JobHandler job = new JobHandler(dataStore, computeProcess);
				
		// verify that a null delimiter is rejected
		assertThrows(IllegalArgumentException.class, () -> {
			job.runJob(input, output, null);
		});
	}
}
