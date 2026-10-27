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
		this.dataStore = dataStore;
		this.computeProcess = computeProcess;
	}
	
	public void runJob(DataInputSource inputSource, DataOutputDestination outputDestination, Delimiters delimiter) {
		IntegerData input = dataStore.read(inputSource);
		
		IntegerData result = computeProcess.compute(input);
		
		dataStore.write(result, outputDestination, delimiter);
	}
}

