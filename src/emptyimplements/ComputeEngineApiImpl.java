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
		if (job == null) {
			throw new IllegalArgumentException("JobHandler cannot be null");
		}
		this.job = job;
	}
	
	// Run JobRequest and return job completion status
	@Override
	public JobResponse runJob(JobRequest request) {
		return ComputeEngineJobRunner.run(request, job::runJob);
	}

}
