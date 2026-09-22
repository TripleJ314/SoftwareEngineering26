package networkapi;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface ComputeEngineApi {
	
	JobResponse runJob(JobRequest request);
	
}
