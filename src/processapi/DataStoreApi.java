package processapi;

import networkapi.Delimiters;
import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStoreApi {
    // Return null of reading fails
    IntegerData read(DataInputSource input);
    
    // Return true on success and false on failure
    boolean write(IntegerData data, DataOutputDestination output, Delimiters delimiter);
}
