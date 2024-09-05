package ideas.vaccineTracker.vaccine_tracker_data.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
