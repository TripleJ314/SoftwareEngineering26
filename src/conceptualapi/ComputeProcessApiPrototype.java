package conceptualapi;

import project.annotations.ConceptualAPIPrototype;
import jobhandler.JobHandler;
import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;

public class ComputeProcessApiPrototype {
 
    @ConceptualAPIPrototype
    public void prototype(ComputeProcessApi process, DataStoreApi dataStore) {
        // Create and set input and output sources
    	DataInputSource inputSource = new DataInputSource() {};
    	DataOutputDestination outputDestination = new DataOutputDestination() {};
 
    	// Create new JobHandler with data store and compute engine
    	JobHandler jobHandler = new JobHandler(dataStore, process);
 
    	// Run job
    	jobHandler.runJob(inputSource, outputDestination);
    }
}

