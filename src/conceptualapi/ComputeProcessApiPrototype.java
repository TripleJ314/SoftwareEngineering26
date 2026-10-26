package conceptualapi;

import project.annotations.ConceptualAPIPrototype;

import java.util.List;

import processapi.IntegerData;
import processapi.ListIntegerData;

public class ComputeProcessApiPrototype {
    
	@ConceptualAPIPrototype
    public void prototype(ComputeProcessApi process) {
    	//Create IntegerData to represent the input from DataStore
		IntegerData input = new ListIntegerData(List.of(1,10,25));
 
    	// Create new JobHandler with data store and compute engine
    	IntegerData result = process.compute(input);
 
    	// Store returned computed results
    	List<Integer> computedResults = result.getIntegers();
    }
}

