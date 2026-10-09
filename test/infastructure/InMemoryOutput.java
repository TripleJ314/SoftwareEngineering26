package infastructure;

import java.util.ArrayList;
import java.util.List;

import networkapi.OutputDestination;
import processapi.DataOutputDestination;

public class InMemoryOutput implements OutputDestination, DataOutputDestination{
	// Stores the output strings for writing by DataStore
	private final List<String> output;
	
	// Initializes and empty list for the output to be written to
	public InMemoryOutput() {
		this.output = new ArrayList<>();
	}
	
	// Return the output list of strings for DataStore to write
	public List<String> getOutput() {
		return output;
	}
}
