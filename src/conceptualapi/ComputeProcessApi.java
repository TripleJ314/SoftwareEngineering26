package conceptualapi;

import project.annotations.ConceptualAPI;
import processapi.IntegerData;

@ConceptualAPI
public interface ComputeProcessApi {
    // Initialize job
    void initialize();
    
    // Read job
    IntegerData read();
    
    // Writing job
    void write(IntegerData data);
}
