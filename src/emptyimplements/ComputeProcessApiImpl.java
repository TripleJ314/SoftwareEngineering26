package emptyimplements;

import java.util.ArrayList;
import java.util.List;

import conceptualapi.ComputeProcessApi;
import processapi.DataStoreApi;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class ComputeProcessApiImpl implements ComputeProcessApi {
	private final DataStoreApi dataStore;
	
	public ComputeProcessApiImpl(DataStoreApi dataStore) {
		this.dataStore = dataStore;
	}
	
	@Override
	public IntegerData compute(IntegerData input) {
		// Create and Retrieve the list of inputed integers
		List<Integer> inputNums = input.getIntegers();
		
		// Create a list to store the computed results
		List<Integer> results = new ArrayList<>();
		
		// Find and store the largest prime less than each integer 
		for (Integer num : inputNums) {
			int largestPrime = lesserLargestPrime(num);
			results.add(largestPrime);
		}
		
		// Return results wrapped in an IntegerData Implementation
		return new ListIntegerData(results);
	}

	@Override
	public DataStoreApi getDataStore() {
		return dataStore;
	}
	
	public boolean isPrime(int num) {
		// Verify input has a lesser prime available
		if (num < 2) {
			return false;
		}
		
		for (int divisor = 2; divisor <= num / divisor; divisor++) {
			if (num % divisor == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int lesserLargestPrime(int num) {
		int result = 0;
		
		for (int i = 2; i < num; i++) {
			if(isPrime(i)) {
				result = i;
			}
		}
		return result;
	}

}
