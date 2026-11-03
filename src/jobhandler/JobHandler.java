package jobhandler;

import conceptualapi.ComputeProcessApi;
import networkapi.Delimiters;
import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;
import processapi.IntegerData;

public class JobHandler {
	private final DataStoreApi dataStore;
	private final ComputeProcessApi computeProcess;
	
	public JobHandler(DataStoreApi dataStore, ComputeProcessApi computeProcess) {
		// Validates the DataStore and ComputeProcess are not null
		if (dataStore == null || computeProcess == null) {
			throw new IllegalArgumentException("DataStore and/or ComputeProcess cannot be null");
		}
		this.dataStore = dataStore;
		this.computeProcess = computeProcess;
	}
	
	public void runJob(DataInputSource inputSource, DataOutputDestination outputDestination, Delimiters delimiter) {
		// Validates the Delimiters, InputSource and OutputDestination are not null
		if (inputSource == null || outputDestination == null || delimiter == null) {
			throw new IllegalArgumentException("Delmiter, InputSource and/or OutputDestination cannot be null");
		}
		IntegerData input = dataStore.read(inputSource);
		
		IntegerData result = computeProcess.compute(input);
		
		dataStore.write(result, outputDestination, delimiter);
	}
}

