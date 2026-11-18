package jobhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import conceptualapi.ComputeProcessApi;
import networkapi.Delimiters;
import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class JobHandler {
	private final DataStoreApi dataStore;
	private final ComputeProcessApi computeProcess;
	
	public JobHandler(DataStoreApi dataStore, ComputeProcessApi computeProcess) {
		// Validates the DataStore and ComputeProcess are not null
		if (dataStore == null || computeProcess == null) {
			throw new IllegalArgumentException("DataStore and/or ComputeProcess cannot be null");
		}
		this.dataStore = dataStore;
		this.computeProcess = computeProcess;
	}
	
	public boolean runJob(DataInputSource inputSource, DataOutputDestination outputDestination, Delimiters delimiter) {
		// Validates the Delimiters, InputSource and OutputDestination are not null
		if (inputSource == null || outputDestination == null || delimiter == null) {
			throw new IllegalArgumentException("Delmiter, InputSource and/or OutputDestination cannot be null");
		}
		// Read input data
		IntegerData input = dataStore.read(inputSource);
		
		// Check and stop if reading failed
		if (input == null) {
			return false;
		}
		
		// Perform computation
		IntegerData result = computeProcess.compute(input);
		
		// Check and stop if computation failed
		if (result == null) {
			return false;
		}
		
		return dataStore.write(result, outputDestination, delimiter);
	}
	
	// Run a job using multiple threads to compute the integers in parallel
	public boolean runJobParallel(DataInputSource inputSource, DataOutputDestination outputDestination, Delimiters delimiter) {
		// Validates the parameters(Delimiters, InputSource and OutputDestination) are not null
		if (inputSource == null || outputDestination == null || delimiter == null) {
			throw new IllegalArgumentException("Delmiter, InputSource and/or OutputDestination cannot be null");
		}
		// Read input data once before beginning parallel computation
		// Prevents multiple threads from reading the same input source
		IntegerData input = dataStore.read(inputSource);
		
		// Check and stop the job if the DataStore failed to read
		if (input == null) {
			return false;
		}
		
		// Retrieve and store the list of input integers
		List<Integer> numbers = input.getIntegers();
		
		// Check and stop if the input list is null
		if (numbers == null) {
			return false;
		}
		
		// Create a fixed number pool of threads
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		
		try {
			// Store computation in original input order
			// Future allows the retrieved results after thread submissions
			// to be stored in their original ordering
			List<Future<IntegerData>> futures = new ArrayList<>();
			
			// 
			for (Integer num : numbers) {
				// Wrap the current integers into IntegerData implementation
				// As Conceptual API accepts IntegerData
				IntegerData threadInput = new ListIntegerData(java.util.Collections.singletonList(num));
				
				// Submit the computation to the ThreadPool
				Future<IntegerData> future = threadPool.submit(() -> computeProcess.compute(threadInput));
				
				// Store the Future in the original order
				futures.add(future);
			}
			
			// Create a List to store the resulting computations
			List<Integer> results = new ArrayList<>();
			
			// Iterate through the results from each submitted computation
			for (Future<IntegerData> future : futures) {
				
				// Wait for the task to finish and retrieve its result
				// If task is completed, get() returns immediately
				IntegerData result = future.get();
				
				// Validate the computation returned not null results
				if (result == null || result.getIntegers() == null || result.getIntegers().size() != 1) {
					return false;
				}
				// Add the computed	integer to the results list
				results.add(result.getIntegers().get(0));
			}
			
			// Wrap computed results into an IntegerData object
			// DataStore API requires IntegerData object to write
			IntegerData outputData = new ListIntegerData(results);
			
			// Write the completed results to the specified output destination
			return dataStore.write(outputData, outputDestination, delimiter);
		} catch (InterruptedException e) {
			// Report failed computations
			Thread.currentThread().interrupt();
			return false;
			
		} catch (ExecutionException | RuntimeException e) {
			// Report failed computations
			return false;
			
		} finally {
			// Always request shutdown of ThreadPool after job finishes
			threadPool.shutdownNow();
		}
	}
}

