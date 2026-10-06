package emptyimplements;

import networkapi.ComputeEngineApi;
import networkapi.JobRequest;
import networkapi.JobResponse;

public class ComputeEngineApiImpl implements ComputeEngineApi {
	@Override
	public JobResponse runJob(JobRequest request) {
		// Return a default null as the logic is not yet implemented.
		return null;
	}

}
