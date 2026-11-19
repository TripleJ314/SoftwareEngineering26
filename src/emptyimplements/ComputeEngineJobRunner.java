package emptyimplements;

import networkapi.Delimiters;
import networkapi.JobRequest;
import networkapi.JobResponse;
import networkapi.JobResponseCode;
import networkapi.JobResponseImpl;
import processapi.DataInputSource;
import processapi.DataOutputDestination;

public class ComputeEngineJobRunner {
	// Prevent creating instances of this utility class
	private ComputeEngineJobRunner() {
	}
	
	@FunctionalInterface
	public interface JobExecutor {
		boolean execute(DataInputSource input, 
				DataOutputDestination output, 
				Delimiters delimiter);
	}
	
	public static JobResponse run(JobRequest request, JobExecutor executor) {
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
			
			// Execute the selected JobHandler method
			// Will execute either runJob() or runJobParallel()
			boolean status = executor.execute(input, output, request.getDelimiter());
			
			// Return failure if job failed
			if (status == false) {
				return new JobResponseImpl(JobResponseCode.FAILURE);
			}
			// Returns success response after job finishes
			return new JobResponseImpl(JobResponseCode.SUCCESS);
			
		} catch (RuntimeException e) {
			// Return failure response if any step in the jobs execution fails
			return new JobResponseImpl(JobResponseCode.FAILURE);
		}
	}
}
