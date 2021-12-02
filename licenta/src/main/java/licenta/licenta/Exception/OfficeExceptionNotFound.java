package licenta.licenta.Exception;

public class OfficeExceptionNotFound extends RuntimeException{

    public OfficeExceptionNotFound(String id){
        super("Could not find the office with id!" + id);
    }
}
