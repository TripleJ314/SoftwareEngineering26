package emptyimplements;

import processapi.DataInputSource;
import processapi.DataOutputDestination;
import processapi.DataStoreApi;
import processapi.IntegerData;

public class DataStoreApiImpl implements DataStoreApi {

	@Override
	public IntegerData read(DataInputSource input) {
		// Return a default null as the logic is not yet implemented.
		return null;
	}

	@Override
	public void write(IntegerData data, DataOutputDestination output) {
		// No return value
	}

}
