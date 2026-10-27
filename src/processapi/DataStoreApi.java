package processapi;

import networkapi.Delimiters;
import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStoreApi {
    
    IntegerData read(DataInputSource input);
    
    void write(IntegerData data, DataOutputDestination output, Delimiters delimiter);
}
