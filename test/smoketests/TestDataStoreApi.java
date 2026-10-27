package smoketests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import emptyimplements.DataStoreApiImpl;
import networkapi.Delimiters;
import networkapi.DelimitersImpl;
import processapi.FileInput;
import processapi.FileOutput;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class TestDataStoreApi {

	@Test
	public void testRead(@TempDir Path tempFile) throws Exception {
		// Create process api implementation
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		
		// Create temporary file to store input integers
		Path inputPath = tempFile.resolve("input.txt");
		
		// Write the integers to the temporary file
		Files.writeString(inputPath, "1\n10\n25");
		
		// Create the input source file
		FileInput input = new FileInput(inputPath);
		
		// Have dataStore read the input source
		IntegerData data = dataStore.read(input);
		
		// Verify reading returns not null data
		assertNotNull(data);
		
		// Verify the returned integers from being read 
		// match the files integers
		assertEquals(List.of(1, 10, 25), data.getIntegers());
	}
	
	@Test
	public void testWrite(@TempDir Path tempFile) throws Exception {
		// Create process api implementation
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		
		// Create a temporary output file path
		Path outputPath = tempFile.resolve("output.txt");
		
		// Create a file with output destination path and delimiters
		FileOutput outputFile = new FileOutput(outputPath);
		
		// Specify delimiter for output
		Delimiters delimiter = new DelimitersImpl(",");
		
		// Create IntegerData containing computed integers
		IntegerData data = new ListIntegerData(List.of(0, 7, 23));
		
		// Write the computed results to the output file
		dataStore.write(data, outputFile, delimiter);
		
		// Verify the output matches the intended format
		assertEquals("0,7,23", Files.readString(outputPath));
	}
	
	@Test
	public void testWriteWithDefaultDelimiters(@TempDir Path tempFile) throws Exception {
		// Create process api implementation
		DataStoreApiImpl dataStore = new DataStoreApiImpl();
		
		// Create a temporary output file path
		Path outputPath = tempFile.resolve("outputDefault.txt");
		
		// Create a file with output destination path and no delimiters
		FileOutput outputFile = new FileOutput(outputPath);
		
		// No delimiter specified for output
		Delimiters delimiter = new DelimitersImpl(null);
		
		// Create IntegerData containing computed integers
		IntegerData data = new ListIntegerData(List.of(0, 7, 23));
		
		// Write the computed results to the output file
		dataStore.write(data, outputFile, delimiter);
		
		// Verify the output matches the intended format
		assertEquals("0,7,23", Files.readString(outputPath));
	}
}
