package emptyimplements;

import jobhandler.JobHandler;
import networkapi.ComputeEngineApi;
import networkapi.JobRequest;
import networkapi.JobResponse;

public class ComputeEngineApiImpl implements ComputeEngineApi {
	
	// Stores the JobHandler
	private final JobHandler job;
	
	// Constructor for connecting the JobHandler in network api tests.
	public ComputeEngineApiImpl(JobHandler job) {
		this.job = job;
	}
	
	// Run JobRequest and return null for tests
	@Override
	public JobResponse runJob(JobRequest request) {
		// Return a default null as the logic is not yet implemented.
		return null;
	}

}
