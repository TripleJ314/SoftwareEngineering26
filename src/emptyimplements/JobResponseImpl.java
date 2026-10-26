package emptyimplements;

import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;

public class JobResponseImpl implements JobResponse {
	// Store the requested computation's job status
	private final JobResponseCode responseCode;
	
	// Initialize the response code with a job status
	public JobResponseImpl(JobResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	@Override
	public JobResponseCode getResponseCode(JobRequest request) {
		// Return the job status
		return responseCode;
	}

}
