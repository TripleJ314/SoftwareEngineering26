package project.checkpointtests;

import java.nio.file.Path;

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

public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
    	// TODO 1:
        //Instantiate a real implementation of Conceptual API
    	ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
    	
    	//Instantiate a real implementation of Process API
    	DataStoreApiImpl dataStore = new DataStoreApiImpl();
    	
    	// Connect Process and Conceptual APIs in the JobHandler
    	JobHandler job = new JobHandler(dataStore, computeProcess);
    	
    	//Instantiate a real implementation of Network API
    	ComputeEngineApiImpl computeEngine = new ComputeEngineApiImpl(job);
    	
    	// TODO 2:
    	//Specify the input and output files
    	FileInput input = new FileInput(Path.of(INPUT));
    	FileOutput output = new FileOutput(Path.of(OUTPUT));
    	
    	// Specify "," delimiter as test instructed
    	Delimiters delimiter = new DelimitersImpl(",");
    	
    	//Create JobRequest with specified input/output and delimiter
    	JobRequest request = new JobRequest(input, output, delimiter);
    	
    	//Run the computation and store response
    	JobResponse response = computeEngine.runJob(request);
    	
    	// Verify the computation was a success
    	if (response.getResponseCode(request) != JobResponseCode.SUCCESS) {
    		throw new IllegalStateException("Manual computation test failed");
    	}
    }
}
