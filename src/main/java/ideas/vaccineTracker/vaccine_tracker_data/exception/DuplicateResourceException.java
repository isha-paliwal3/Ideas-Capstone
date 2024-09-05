package ideas.vaccineTracker.vaccine_tracker_data.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
