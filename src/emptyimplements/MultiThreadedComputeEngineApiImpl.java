package emptyimplements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jobhandler.JobHandler;
import networkapi.ComputeEngineApi;
import networkapi.JobRequest;
import networkapi.JobResponse;

public class MultiThreadedComputeEngineApiImpl implements ComputeEngineApi{
	
	// Stores the JobHandler
	private final JobHandler job;
	
	// Maximum number of workers in the thread pool
	private static final int MAX_THREADS = 4;
	
	// Store the thread pool used for parallel computation
	// Reusing the pool prevents each job from creating its own workers
	private final ExecutorService threadPool;
	
	// Constructor for connecting the JobHandler to the Network api.
	public MultiThreadedComputeEngineApiImpl(JobHandler job) {
		if (job == null) {
			throw new IllegalArgumentException("JobHandler cannot be null");
		}
		this.job = job;
		
		// Create the fixed thread pool once so concurrent job requests share
		// the same worker limit instead of creating separate thread pools
		this.threadPool = Executors.newFixedThreadPool(MAX_THREADS);
	}
	@Override
	public JobResponse runJob(JobRequest request) {
		// Lambda supplies the shared executor to runJobParallel()
		// which takes an additional parameter
		return ComputeEngineJobRunner.run(request, 
				(input, output, delimiter) -> 
				job.runJobParallel(input, output, delimiter, threadPool));
	}
	
	public void shutDown() {
		// Stops accepting new tasks and allows previously submitted threads to finish
		// shutdown() is managed here because the engine owns the shared pool
		threadPool.shutdown();
	}

}
