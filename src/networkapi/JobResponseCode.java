package networkapi;

public enum JobResponseCode {
    // States the job can be in
    PENDING(false),
    RUNNING(false),
    SUCCESS(true),
    FAILURE(true),
    INVALID_INPUT(true);

    private boolean success;
    
    private JobResponseCode(boolean success) {
        this.success = success;
    }
    
    public boolean success() {
        return success;
    }
}