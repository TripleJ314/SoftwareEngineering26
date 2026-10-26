package emptyimplements;

import jobhandler.JobHandler;
import networkapi.ComputeEngineApi;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;
import processapi.DataInputSource;
import processapi.DataOutputDestination;

public class ComputeEngineApiImpl implements ComputeEngineApi {
	
	// Stores the JobHandler
	private final JobHandler job;
	
	// Constructor for connecting the JobHandler in network api tests.
	public ComputeEngineApiImpl(JobHandler job) {
		this.job = job;
	}
	
	// Run JobRequest and return job completion status
	@Override
	public JobResponse runJob(JobRequest request) {
		// Check request and input/output sources are valid
		if (request == null 
				|| !(request.getInputSource() instanceof DataInputSource)
				|| !(request.getOutputDest() instanceof DataOutputDestination)) {
			return new JobResponseImpl(JobResponseCode.INVALID_INPUT);
		}
		try {
			// Convert the Network APi sources to Process API source type
			DataInputSource input = (DataInputSource) request.getInputSource();
			DataOutputDestination output = (DataOutputDestination) request.getOutputDest();
			
			// Run job with JobHandler
			job.runJob(input, output);
			
			// Returns success response after job finishes
			return new JobResponseImpl(JobResponseCode.SUCCESS);
			
		} catch (RuntimeException e) {
			// Return failure response if any step in the jobs execution fails
			return new JobResponseImpl(JobResponseCode.FAILURE);
		}
	}

}
