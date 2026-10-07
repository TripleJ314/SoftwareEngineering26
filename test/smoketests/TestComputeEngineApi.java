package smoketests;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeEngineApiImpl;
import networkapi.Delimiters;
import networkapi.InputSource;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.OutputDestination;

public class TestComputeEngineApi {
	@Test
	public void testRunJob() {
		// Create network api implementation
		ComputeEngineApiImpl computeEngine = new ComputeEngineApiImpl() {};
		
		// Mock the interfaces needed for JobRequest
		InputSource input = mock(InputSource.class);
		OutputDestination output = mock(OutputDestination.class);
		Delimiters delimiters = mock(Delimiters.class);
		
		// Create the JobRequest the user send to the compute engine
		JobRequest request = new JobRequest(input, output, delimiters);
	
		// Call Network API
		JobResponse response = computeEngine.runJob(request);
		
		// Check the response of the compute engine
		// For the tests this should fail/not pass
		if (response == null) {
			fail();
		}
	}
}
