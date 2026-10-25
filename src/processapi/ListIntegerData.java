package processapi;

import java.util.List;

public class ListIntegerData implements IntegerData {
	
	private List<Integer> integers;
	
	public ListIntegerData(List<Integer> integers) {
		this.integers = integers;
	}
	
	@Override
	public List<Integer> getIntegers() {
		return integers;
	}

}
