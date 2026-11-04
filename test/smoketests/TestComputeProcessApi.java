package smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import emptyimplements.ComputeProcessApiImpl;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class TestComputeProcessApi {
	
	@Test
	public void testCompute() {
		
		// Create Conceptual api implementation
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Mock the IntegerData that is given to compute component
		IntegerData input = mock(IntegerData.class);
		
		// Specify what the mock returns
		when(input.getIntegers()).thenReturn(List.of(1, 10, 25));
		
		// Compute component takes data, computes and returns the output
		IntegerData result = computeProcess.compute(input);
		
		//Compute component should return data
		// Test will have it returned as null and fail
		assertNotNull(result);
	}
	
	@Test
	public void testLesserLargestPrime() {
		
		// Create the Conceptual api implementation(computation component)
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Provide input integers
		IntegerData input = new ListIntegerData(List.of(1, 10, 25));
		
		// Run and store the ouput of LesserLargestPrime 
		// computation for each input integer
		IntegerData results = computeProcess.compute(input);
		
		// Verify the largest prime less than input is correct
		assertEquals(List.of(0, 7, 23), results.getIntegers());
	}
	
	@Test
	public void testNullInput() {
		// Create the computation component
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Verify null IntegerData inputed is rejected
		assertThrows(IllegalArgumentException.class, () -> computeProcess.compute(null));
	}
	
	@Test
	public void testNullIntegerList() {
		// Mock IntegerData
		IntegerData input = mock(IntegerData.class);
		
		// Simulate IntegerData behavior to return null
		when(input.getIntegers()).thenReturn(null);
		
		// Create the computation component
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Verify null integer list is rejected
		assertThrows(IllegalArgumentException.class, () -> computeProcess.compute(input));
	}
	
	@Test
	public void testNullIntegerElements() {
		// Mock IntegerData
		IntegerData input = mock(IntegerData.class);
		
		// Simulate IntegerData behavior to return null
		when(input.getIntegers()).thenReturn(Arrays.asList(1, null, 25));
		
		// Create the computation component
		ComputeProcessApiImpl computeProcess = new ComputeProcessApiImpl();
		
		// Verify a null integer element within the list is rejected
		assertThrows(IllegalArgumentException.class, () -> computeProcess.compute(input));
	}
}
