package processapi;

import project.annotations.ProcessAPIPrototype;

public class DataStoreApiPrototype {
    @ProcessAPIPrototype
    
    public void prototype(DataStoreApi process) {
        
        // Specify the input source
        DataInputSource inputSource = null;
        
        // Specify output destination
        DataOutputDestination outputDestination = null;
        
        // Read integers from the data storage system
        IntegerData data = process.read(inputSource);
        
        // Write integers to the data storage system
        process.write(data, outputDestination);
    }
}
