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
		// Verify the input source
		if (input == null) {
			throw new IllegalArgumentException("InputSource cannot be null");
		}
		
		// Verify this implementation supports the source
		// Current implementation only supports file sources
		if (!(input instanceof FileInput)) {
			throw new IllegalArgumentException("Unsupported input source");
		}
		
		// Convert general input source to FileInput source
		FileInput fileInput = (FileInput) input;
		
		// Verifies the input file in not null
		if (fileInput.getPath() == null) {
			throw new IllegalArgumentException("Input file path cannot be null");
		}
		
		try {
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
		} catch (IOException e) {
			// Reports failure if file is cannot be read
			throw new IllegalStateException("Failed to read input file: ", e);
		}
	}

	@Override
	public void write(IntegerData data, DataOutputDestination output, Delimiters delimiter) {
		// Verify the output source
		if (output == null) {
			throw new IllegalArgumentException("OutputDestination cannot be null");
		}
		
		// Verify this implementation supports the source
		// Current implementation only supports file sources
		if (!(output instanceof FileOutput)) {
			throw new IllegalArgumentException("Unsupported output source");
		}
		
		// Verifies integer data and delimiters are not null
		if (data == null) {
			throw new IllegalArgumentException("IntegerData cannot be null");
		} 
		
		// Verify the integer list is not null
		if (data.getIntegers() == null) {
			throw new IllegalArgumentException("Integer list cannot be null");
		}
		
		// Verify the delimiter is not null
		if (delimiter == null) {
			throw new IllegalArgumentException("Delimiter must be valid and cannot be null");
		}
		
		// Verify the field delimiter is not null
		if (delimiter.getFieldDelimiter() == null) {
			throw new IllegalArgumentException("Field delimiter cannot be null");
		}
		
		// Convert general output source to FileOutput source
		FileOutput fileOutput = (FileOutput) output;
		
		// Verify the output file path is not null
		if (fileOutput.getPath() == null) {
			throw new IllegalArgumentException("Output file path cannot be null");
		}
		
		// Create a list to store computed integers as Strings
		List<String> results = new ArrayList<>();
		
		// Convert each computed integer into a String
		for (Integer num: data.getIntegers()) {
			// Verify there are no null values in the output integers list
			if (num == null) {
				throw new IllegalArgumentException("Output integers cannot be null");
			}
			results.add(num.toString());
		}
		
		// Combine all results into one line with specified delimiters
		String outputToWrite = String.join(delimiter.getFieldDelimiter(), results);
		
		try {
			Files.writeString(fileOutput.getPath(), outputToWrite);
		} catch (IOException e) {
			// Report failure is file cannot be written to
			throw new IllegalStateException("Failed to write to output file: ", e);
		}
	}

}
