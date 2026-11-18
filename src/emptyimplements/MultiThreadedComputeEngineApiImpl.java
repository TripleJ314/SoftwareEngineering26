package emptyimplements;

import jobhandler.JobHandler;
import networkapi.ComputeEngineApi;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;
import networkapi.JobResponseImpl;
import processapi.DataInputSource;
import processapi.DataOutputDestination;

public class MultiThreadedComputeEngineApiImpl implements ComputeEngineApi{
	
	// Stores the JobHandler
	private final JobHandler job;
	
	// Constructor for connecting the JobHandler to the Network api.
	public MultiThreadedComputeEngineApiImpl(JobHandler job) {
		if (job == null) {
			throw new IllegalArgumentException("JobHandler cannot be null");
		}
		this.job = job;
	}
	@Override
	public JobResponse runJob(JobRequest request) {
		try {
			// Check request and input/output sources are valid
			if (request == null 
					|| !(request.getInputSource() instanceof DataInputSource)
					|| !(request.getOutputDest() instanceof DataOutputDestination)) {
				return new JobResponseImpl(JobResponseCode.INVALID_INPUT);
			}
			// Convert the Network APi sources to Process API source type
			DataInputSource input = (DataInputSource) request.getInputSource();
			DataOutputDestination output = (DataOutputDestination) request.getOutputDest();
			
			// Run job with JobHandler using the parallel computation method in JobHandler
			boolean status = job.runJobParallel(input, output, request.getDelimiter());
			
			// Return failure if reading, computation or writing failed
			if (status == false) {
				return new JobResponseImpl(JobResponseCode.FAILURE);
			}
			// Returns success response after parallel jobs finish
			return new JobResponseImpl(JobResponseCode.SUCCESS);
			
		} catch (RuntimeException e) {
			// Return failure response if any step in the jobs execution fails
			return new JobResponseImpl(JobResponseCode.FAILURE);
		}
	}

}
