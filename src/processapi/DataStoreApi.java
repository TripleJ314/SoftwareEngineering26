package processapi;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStoreApi {
    
    IntegerData read(DataInputSource input);
    
    void write(IntegerData data, DataOutputDestination output);
}
