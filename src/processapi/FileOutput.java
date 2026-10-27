package processapi;

import java.nio.file.Path;

import networkapi.Delimiters;
import networkapi.OutputDestination;

public class FileOutput implements OutputDestination, DataOutputDestination{
	// Store the location(path) of the output file
	private final Path path;
	
	// Constructor only needs output file path
	public FileOutput(Path path) {
		this.path = path;
	}
	
	// Returns the output files path
	public Path getPath() {
		return path;
	}	
}
