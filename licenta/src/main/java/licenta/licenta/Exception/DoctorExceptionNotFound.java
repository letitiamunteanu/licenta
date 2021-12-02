package licenta.licenta.Exception;

public class DoctorExceptionNotFound extends RuntimeException{

    public DoctorExceptionNotFound(String id){
        super("Could not find the doctor with id " + id);
    }
}
