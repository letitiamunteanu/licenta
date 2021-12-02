package licenta.licenta.Services;

import licenta.licenta.Exception.PatientExceptionNotFound;
import licenta.licenta.Model.Patients;
import licenta.licenta.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientsService {

    @Autowired
    private final PatientsRepository patientsRepository;


    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    //Create - add a new patient => POST
    public Patients addPatient(Patients patient){

        if(patientsRepository.findById(patient.getId()).isPresent()){
            throw new IllegalStateException("The patient already exists!");
        }
        else if(patient.getId() != 0 && patient.getNume() != null && patient.getPrenume() != null && patient.getVarsta() != null
               && patient.getGreutate() != null && patient.getInaltime() != null && patient.getNrTelefon() != null
               && patient.getSimptome() != null && patient.getGrupaSange() != null && patient.getCnp() != null && patient.getDataNastere() != null){

                patientsRepository.save(patient);
        }

        return patient;
    }

    //Read - get a patient by id => GET
    public Patients getPatientById(Integer id){

        return patientsRepository.findById(id).orElseThrow(() -> new PatientExceptionNotFound(id));
    }

    //Read - get all patients
    public List<Patients> getAllPatients(){

        return patientsRepository.findAll();
    }

    //Read - get a patient by name
    public Patients getPatientbyName(String name){

        return patientsRepository.findByNumeContaining(name);
    }

    //Read - get a patient by cnp
    public Patients getPatientByCnp(String cnp){

        return patientsRepository.findByCnpContaining(cnp);
    }

    //Update - update a patient by id => PUT
    public Patients update(Integer id, Patients patientToUpdate){

        return patientsRepository.findById(id).map(p -> {

            p.setNume(patientToUpdate.getNume());
            p.setPrenume(patientToUpdate.getPrenume());
            p.setCnp(patientToUpdate.getCnp());
            p.setDataNastere(patientToUpdate.getDataNastere());
            p.setVarsta(patientToUpdate.getVarsta());
            p.setInaltime(patientToUpdate.getInaltime());
            p.setGreutate(patientToUpdate.getGreutate());
            p.setNrTelefon(patientToUpdate.getNrTelefon());
            p.setDomiciliu(patientToUpdate.getDomiciliu());
            p.setGrupaSange(patientToUpdate.getGrupaSange());
            p.setSimptome(patientToUpdate.getSimptome());
            p.setDiagnostic(patientToUpdate.getDiagnostic());
            p.setSpecificatii(patientToUpdate.getSpecificatii());

            return patientsRepository.save(patientToUpdate);
        }).orElseGet(() -> {
            patientToUpdate.setId(id);
            return patientsRepository.save(patientToUpdate);
        });
    }

    //Delete -  delete a patient by id => DELETE
    public void delete(Integer id){

        if(patientsRepository.findById(id).isPresent()){
            patientsRepository.delete(patientsRepository.getById(id));
        }
        else{
            throw new IllegalStateException("This patient does not exist in our database!");
        }
    }

}
