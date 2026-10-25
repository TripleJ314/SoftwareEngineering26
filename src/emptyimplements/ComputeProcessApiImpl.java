package emptyimplements;

import java.util.ArrayList;
import java.util.List;

import conceptualapi.ComputeProcessApi;
import processapi.DataStoreApi;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class ComputeProcessApiImpl implements ComputeProcessApi {
	//Stores the data store component given through constructor
	private final DataStoreApi dataStore;
	
	// Constructor to initialize the computation component with its data store dependency
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
		// Returns data store component given through constructor
		return dataStore;
	}
	
	public boolean isPrime(int num) {
		// Verify input has a lesser prime available
		if (num < 2) {
			return false;
		}
		// Check for divisors up to the square root of the num
		// and if num has a divisor other than 1 or num, than its not prime
		for (int divisor = 2; divisor <= num / divisor; divisor++) {
			if (num % divisor == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int lesserLargestPrime(int num) {
		// Default: 0; for when no smaller prime exists
		int result = 0;
		
		// Update the result when a larger prime is found
		for (int i = 2; i < num; i++) {
			if(isPrime(i)) {
				result = i;
			}
		}
		return result;
	}

}
