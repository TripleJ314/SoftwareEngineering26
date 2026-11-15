package emptyimplements;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import networkapi.Delimiters;
import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;
import processapi.FileInput;
import processapi.FileOutput;
import processapi.IntegerData;
import processapi.ListIntegerData;

public class DataStoreApiImpl implements DataStoreApi {

	@Override
	public IntegerData read(DataInputSource input) {
		try {
			// Verify the input source
			if (input == null) {
				return null;
			}
			
			// Verify this implementation supports the source
			// Current implementation only supports file sources
			if (!(input instanceof FileInput)) {
				return null;
			}
			
			// Convert general input source to FileInput source
			FileInput fileInput = (FileInput) input;
			
			// Verifies the input file in not null
			if (fileInput.getPath() == null) {
				return null;
			}
			
			// Read all line from input file
			List<String> lines = Files.readAllLines(fileInput.getPath());
				
			// Create a list to store integers read from input file
			List<Integer> numbers = new ArrayList<>();
				
			// Convert each line into an integer
			for (String line : lines) {
				numbers.add(Integer.parseInt(line.trim()));
			}
			
			// Return numbers wrapped in IntegerData interface
			return new ListIntegerData(numbers);
		} catch (IOException | RuntimeException e) {
			// Returns null on failure if file is cannot be read
			return null;
		}
	}

	@Override
	public boolean write(IntegerData data, DataOutputDestination output, Delimiters delimiter) {		
		try {
			// Verify the output source
			if (output == null) {
				return false;
			}
			
			// Verify this implementation supports the source
			// Current implementation only supports file sources
			if (!(output instanceof FileOutput)) {
				return false;
			}
			
			// Verifies integer data and delimiters are not null
			if (data == null) {
				return false;
			} 
			
			// Store
			List<Integer> integers = data.getIntegers();
			
			// Verify the integer list is not null
			if (integers == null) {
				return false;
			}
			
			// Verify the delimiter is not null
			if (delimiter == null) {
				return false;
			}
			
			// Verify the field delimiter is not null
			if (delimiter.getFieldDelimiter() == null) {
				return false;
			}
			
			// Convert general output source to FileOutput source
			FileOutput fileOutput = (FileOutput) output;
			
			// Verify the output file path is not null
			if (fileOutput.getPath() == null) {
				return false;
			}
			
			// Create a list to store computed integers as Strings
			List<String> results = new ArrayList<>();
			
			// Convert each computed integer into a String
			for (Integer num: integers) {
				// Verify there are no null values in the output integers list
				if (num == null) {
					return false;
				}
				results.add(num.toString());
			}
			
			// Combine all results into one line with specified delimiters
			String outputToWrite = String.join(delimiter.getFieldDelimiter(), results);
			Files.writeString(fileOutput.getPath(), outputToWrite);
			return true;
		} catch (IOException | RuntimeException e) {
			// Report failure is file cannot be written to
			return false;
		}
	}

}
