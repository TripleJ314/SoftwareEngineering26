package threadharness;

import java.io.File;
import java.nio.file.Path;

import networkapi.ComputeEngineApi;
import networkapi.Delimiters;
import networkapi.DelimitersImpl;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;
import processapi.FileInput;
import processapi.FileOutput;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final ComputeEngineApi coordinator;

	public TestUser(ComputeEngineApi coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		
		// Create input and output sources from given data
		FileInput input = new FileInput(Path.of(inputPath));
		FileOutput output = new FileOutput(Path.of(outputPath));
		
		// Specify output delimiter with requested char
		Delimiters delim = new DelimitersImpl(String.valueOf(delimiter));
		
		// Create the job request
		JobRequest request = new JobRequest(input, output, delim);
		
		// Run the computation and store the job's response
		JobResponse response = coordinator.runJob(request);
		
		// Verify the job complete
		if (response.getResponseCode(request) != JobResponseCode.SUCCESS) {
			throw new IllegalStateException("Compute Job Failed");
		}
		
	}

}
