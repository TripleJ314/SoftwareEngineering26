package networkapi;

public class JobRequest {
	private final InputSource inputSource;
	private final OutputDestination outputDestination;
	private final Delimiters delimiters;
	
	public JobRequest(InputSource inputSource, 
					OutputDestination outputDestination, 
					Delimiters delimiters) {
		this.inputSource = inputSource;
		this.outputDestination = outputDestination;
		this.delimiters= delimiters;
	}
	
	public InputSource getInputSource() {
		return inputSource;
	}
	
	public OutputDestination getOutputDest() {
		return outputDestination;
	}
	
	public Delimiters getDelimiter() {
		return delimiters;
	}
}
