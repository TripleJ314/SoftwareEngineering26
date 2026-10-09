package smoketests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeEngineApiImpl;
import jobhandler.JobHandler;
import networkapi.Delimiters;
import networkapi.InputSource;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.OutputDestination;

public class TestComputeEngineApi {
	@Test
	public void testRunJob() {
		// Mock JobHandler for the ComputeEngine
		JobHandler job = mock(JobHandler.class);
		
		// Mock the InputSource and OutputDestination
		// interfaces needed for JobRequest
		InputSource input = mock(InputSource.class);
		OutputDestination output = mock(OutputDestination.class);
		
		// Mock the output Delimiters
		Delimiters delimiters = mock(Delimiters.class);
		
		// Create network api implementation
		ComputeEngineApiImpl computeEngine = new ComputeEngineApiImpl(job) {};
		
		// Create the JobRequest the user send to the compute engine
		JobRequest request = new JobRequest(input, output, delimiters);
				
		// Call Network API
		JobResponse response = computeEngine.runJob(request);
		
		// Check the response of the compute engine
		// For the tests this should fail/not pass
		assertNotNull(response);
	}
}
