package infastructure;

import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;
import processapi.IntegerData;

public class InMemoryDataStore implements DataStoreApi{

	@Override
	public IntegerData read(DataInputSource input) {
		
		// Casts the general DataInputSource 
		// to the InMemoryInput for tests only
		InMemoryInput inputSource = (InMemoryInput) input;
		
		// Returns an input list wrapped in an IntegerData
		return new InMemoryIntegerData(inputSource.getInput());
	}

	@Override
	public void write(IntegerData data, DataOutputDestination output) {
		
		// Casts the general IntegerData data to access its store integers
		InMemoryIntegerData outputData = (InMemoryIntegerData) data;
		
		// Casts the general DataOutputDestination
		// to the InMemoryOutput for tests only
		InMemoryOutput outputDestination = (InMemoryOutput) output;
		
		// Iterate through the list and write it to the output list
		// while converting each Integer to a String
		for (Integer integer : outputData.getIntegers()) {
			outputDestination.getOutput().add(integer.toString());
		}
	}
	
}
