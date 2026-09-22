package networkapi;

import project.annotations.NetworkAPIPrototype;

public class ComputeEngineApiPrototype {
	@NetworkAPIPrototype
	
	public void prototype(ComputeEngineApi computeEngine) {
		//  Specify the input source
		InputSource input;
		// Specify the output source
		OutputDestination output;
		// Specify the output delimiters
		// If not specified, defaults are used
		Delimiters delimiter;
		// Create JobRequest
		JobRequest request = null;
		
		// Run job
		JobResponse response = computeEngine.runJob(request);

	}

}
