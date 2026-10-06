package conceptualapi;

import project.annotations.ConceptualAPI;
import processapi.DataStoreApi;
import processapi.IntegerData;

@ConceptualAPI
public interface ComputeProcessApi {
    IntegerData compute(IntegerData input);
    
    DataStoreApi getDataStore();
}

