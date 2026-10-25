package processapi;

import java.util.List;

public class ListIntegerData implements IntegerData {
	// Store integers given when object is created
	private List<Integer> integers;
	
	// Initializes the IntegerData object with a list of integers
	public ListIntegerData(List<Integer> integers) {
		this.integers = integers;
	}
	
	@Override
	public List<Integer> getIntegers() {
		// Return stored integers when called
		return integers;
	}

}
