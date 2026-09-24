package conceptualapi;

import project.annotations.ConceptualAPIPrototype;
import processapi.IntegerData;

public class ComputeProcessApiPrototype {
    
    @ConceptualAPIPrototype
    public void prototype (ComputeProcessApi process) {
        // Initialize the job
        process.initialize();
        
        // Read data
        IntegerData data = process.read();
        
        // Perform computation(LargestPrimeLessThanN)
        // TBD
        
        // Write 
        process.write(data);
    }
}
