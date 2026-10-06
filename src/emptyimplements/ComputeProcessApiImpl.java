package emptyimplements;

import conceptualapi.ComputeProcessApi;
import processapi.DataStoreApi;
import processapi.IntegerData;

public class ComputeProcessApiImpl implements ComputeProcessApi {

	@Override
	public IntegerData compute(IntegerData input) {
		// Return a default null as the logic is not yet implemented.
		return null;
	}

	@Override
	public DataStoreApi getDataStore() {
		// Return a default null as the logic is not yet implemented.
		return null;
	}

}
