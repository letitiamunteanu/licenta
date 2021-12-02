package licenta.licenta.Exception;

public class PatientExceptionNotFound extends RuntimeException{

    public  PatientExceptionNotFound(Integer id){
        super("Could not found the patient with id !" + id);
    }
}
