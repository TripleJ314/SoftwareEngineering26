package infastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeEngineApiImpl;
import emptyimplements.ComputeProcessApiImpl;
import jobhandler.JobHandler;
import networkapi.Delimiters;
import networkapi.DelimitersImpl;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;

public class ComputeEngineIntegrationTest {
	
	@Test
	public void testComputeEngineIntegration() {
		// Create the test input given in directions to be read
		InMemoryInput input = new InMemoryInput(List.of(1, 10, 25));
		
		// Create an empty test output to be written to
		InMemoryOutput output = new InMemoryOutput();
		
		// Create the Process Api implementation for test
		InMemoryDataStore dataStore = new InMemoryDataStore();
		
		// Create the Conceptual Api implementation for test
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Create JobHandler using the Process and Conceptual API
		JobHandler job = new JobHandler(dataStore, computeProcess);
		
		// Create the Network Api implementation for test
		ComputeEngineApiImpl computeEngine = new ComputeEngineApiImpl(job);
		
		// Create the output delimiter without specification
		// for default delimiter 
		Delimiters delimiter = new DelimitersImpl(null);
		
		// Create a job request without any specified delimiters
		JobRequest request = new JobRequest(input, output, delimiter);
		
		// Execute JobHandler through the Network Api
		JobResponse response = computeEngine.runJob(request);
		
		//Verify the job returns a response
		assertNotNull(response);
				
		// Check the response of the compute engine
		// Verify the input types create intended response
		assertEquals(JobResponseCode.SUCCESS, response.getResponseCode(request));
			
		// Verify that the output matches the computation results
		assertEquals(List.of("0", "7", "23"), output.getOutput());
	}
}
