package emptyimplements;

import java.util.ArrayList;
import java.util.List;

import conceptualapi.ComputeProcessApi;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class ComputeProcessApiImpl implements ComputeProcessApi {
	
	@Override
	public IntegerData compute(IntegerData input) {
		// verifies the IntegerData input is not null
		if (input == null) {
			throw new IllegalArgumentException("IntegerData input cannot be null");
		}
		// Create and Retrieve the list of inputed integers
		List<Integer> inputNums = input.getIntegers();
		
		// verifies the list is not null
		if (inputNums == null) {
			throw new IllegalArgumentException("Input integer list cannot be null");
		}
		
		// Create a list to store the computed results
		List<Integer> results = new ArrayList<>();
		
		// Find and store the largest prime less than each integer 
		for (Integer num : inputNums) {
			
			// verifies that each integer is not null
			if (num == null) {
				throw new IllegalArgumentException("Input integer cannot be null");
			}
			int largestPrime = lesserLargestPrime(num);
			results.add(largestPrime);
		}
		
		// Return results wrapped in an IntegerData Implementation
		return new ListIntegerData(results);
	}
	
	public boolean isPrime(int num) {
		// Verify input has a lesser prime available
		// No other validation is required: values below 2 are not prime
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
		// No additional validation is required: all int values are accepted
		// Default: 0, for when no smaller prime exists for values <= 2
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
