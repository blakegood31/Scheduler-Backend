package superfrog_scheduler.backend.system.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String objectName, String id) {
        super("Could not find " + objectName + " with ID " + id + " :(");
    }

    public ObjectNotFoundException(String objectName, Integer id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }
}