package networkapi;

import project.annotations.NetworkAPIPrototype;

public class ComputeEngineApiPrototype {
    @NetworkAPIPrototype
    
    public void prototype(ComputeEngineApi computeEngine) {
        //  Specify the input source
        InputSource input = new InputSource() {};
        
        // Specify the output source
        OutputDestination output = new OutputDestination() {};
        
        // Specify the output delimiters
        // If is not specified, defaults are used
        Delimiters delimiter = new Delimiters() {};
        
        // Create JobRequest
        JobRequest request = new JobRequest(input, output, delimiter);
        
        // Run job
        JobResponse response = computeEngine.runJob(request);
    }
}
