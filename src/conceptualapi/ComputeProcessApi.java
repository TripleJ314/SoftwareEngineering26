package conceptualapi;

import project.annotations.ConceptualAPI;
import processapi.IntegerData;

@ConceptualAPI
public interface ComputeProcessApi {
    IntegerData compute(IntegerData input);
}
