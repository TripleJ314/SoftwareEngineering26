package networkapi;

public interface JobRequest {
	InputSource getInputSource();
	
	OutputDestination getOutputDestination();
	
	Delimiters getDelimiter();
}
