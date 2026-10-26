package emptyimplements;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
		// Convert general input source to FileInput source
		FileInput fileInput = (FileInput) input;
		
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
	public void write(IntegerData data, DataOutputDestination output) {
		// Convert general output source to FileOutput source
		FileOutput fileOutput = (FileOutput) output;
		
		// Create a list to store computed integers as Strings
		List<String> results = new ArrayList<>();
		
		// Convert each computed integer into a String
		for (Integer num: data.getIntegers()) {
			results.add(num.toString());
		}
		
		// Combine all results into one line with specified delimiters
		String outputToWrite = String.join(fileOutput.getDelimiter(), results);
		
		try {
			Files.writeString(fileOutput.getPath(), outputToWrite);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to write to output file: ", e);
		}
	}

}
