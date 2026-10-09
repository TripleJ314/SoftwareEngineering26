package infastructure;

import java.util.List;

import processapi.IntegerData;

public class InMemoryIntegerData implements IntegerData{
	
	// Stores the integers in a list while general 
	// IntegerData interface is being implemented
	private final List<Integer> integers;
	
	// Initializes the InMemoryIntegerData object 
	// with a list of integers
	public InMemoryIntegerData(List<Integer> integers) {
		this.integers = integers;
	}
	
	// Returns the list of integers for reading and writing(InMemory)
	public List<Integer> getIntegers() {
		return integers;
	}
}
