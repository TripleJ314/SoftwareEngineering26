package processapi;

import java.nio.file.Path;

import networkapi.Delimiters;
import networkapi.OutputDestination;

public class FileOutput implements OutputDestination, DataOutputDestination{
	// Store the location(path) of the output file
	private final Path path;
	
	// Store the specified delimiter from user
	private final String delimiter;
	
	public FileOutput(Path path, String delimiter) {
		this.path = path;
		// Checks if a delimiter was specified
		// If not then default delimiter is selected
		if (delimiter == null) {
			this.delimiter = Delimiters.DEFAULT_FIELD;
		} else {
			this.delimiter = delimiter;
		}
	}
	
	public Path getPath() {
		return path;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	
}
