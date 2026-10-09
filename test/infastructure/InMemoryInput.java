package infastructure;

import java.util.List;

import networkapi.InputSource;
import processapi.DataInputSource;

public class InMemoryInput implements InputSource, DataInputSource {
	
	// Stores the input integers for tests
	private final List<Integer> input;
	
	// Initializes the input with a list of integers
	public InMemoryInput(List<Integer> input) {
		this.input = input;
	}
	
	// Returns the integers for DataStore to read
	public List<Integer> getInput() {
		return input;
	}
	
}
