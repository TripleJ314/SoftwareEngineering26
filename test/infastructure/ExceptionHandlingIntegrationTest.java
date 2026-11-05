package infastructure;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import emptyimplements.ComputeEngineApiImpl;
import emptyimplements.ComputeProcessApiImpl;
import emptyimplements.DataStoreApiImpl;
import jobhandler.JobHandler;
import networkapi.Delimiters;
import networkapi.DelimitersImpl;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;
import processapi.FileInput;
import processapi.FileOutput;

public class ExceptionHandlingIntegrationTest {

	@Test
	public void testMissingInputFile(@TempDir Path tempDir) {
		// Instantiate real components
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		JobHandler job = new JobHandler(dataStore, computeProcess);
		ComputeEngineApiImpl computeEngine = new ComputeEngineApiImpl(job);
		
		// Create non-existing input file
		FileInput input = new FileInput(tempDir.resolve("non-existingInput.txt"));
		FileOutput output = new FileOutput(tempDir.resolve("output.txtx"));
		Delimiters delimiter = new DelimitersImpl(",");
		
		// Create the job request
		JobRequest request = new JobRequest(input, output, delimiter);
		
		// Verify no exception reaches the caller/user
		JobResponse response = assertDoesNotThrow(() -> {
			return computeEngine.runJob(request);
		});
		
		// Verify exception was converted to a failure response
		assertEquals(JobResponseCode.FAILURE, response.getResponseCode(request));
	}
}
