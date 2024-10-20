package group14.backend.lms.exception;

public abstract class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(Long id) {
        super("Could not find record with id " + id);
    }
}
