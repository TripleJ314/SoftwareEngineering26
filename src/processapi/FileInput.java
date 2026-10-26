package processapi;

import java.nio.file.Path;

import networkapi.InputSource;

public class FileInput implements InputSource, DataInputSource{
	// Store the file input path
	private final Path path;
	
	// Initializes the input source with specified path
	public FileInput(Path path) {
		this.path = path;
	}
	
	// Return the location(path) of the input file
	public Path getPath() {
		return path;
	}
}
