package conceptualapi;

import project.annotations.ConceptualAPIPrototype;
import jobhandler.JobHandler;
import processapi.DataInputSource;
import processapi.DataOutputDestination;

public class ComputeProcessApiPrototype {
    
	@ConceptualAPIPrototype
    public void prototype(ComputeProcessApi process) {
        // Create and set input and output sources
    	DataInputSource inputSource = new DataInputSource() {};
    	DataOutputDestination outputDestination = new DataOutputDestination() {};
 
    	// Create new JobHandler with data store and compute engine
    	JobHandler jobHandler = new JobHandler(process.getDataStore(), process);
 
    	// Run job
    	jobHandler.runJob(inputSource, outputDestination);
    }
}

