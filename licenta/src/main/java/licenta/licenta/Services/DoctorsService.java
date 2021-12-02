package licenta.licenta.Services;

import licenta.licenta.Exception.DoctorExceptionNotFound;
import licenta.licenta.Model.Doctors;
import licenta.licenta.Repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorsService {

    @Autowired
    private final DoctorsRepository doctorsRepository;


    public DoctorsService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    //Create - add a new doctor => POST
    public Doctors addNewDoctor(Doctors doctor){

        if(doctorsRepository.findById(doctor.getId()).isPresent()){
            throw new IllegalStateException("This doctor already exists!");
        }
        else if(doctor.getId() != null && doctor.getNume() != null && doctor.getPrenume() != null && doctor.getSpecializare() != null
                && doctor.getSediu() != null && doctor.getSala() != null){

            doctorsRepository.save(doctor);
        }

        return doctor;
    }

    //Read - get a doctor by id => GET
    public Doctors getDoctorById(String id){

        return doctorsRepository.findById(id).orElseThrow(() -> new DoctorExceptionNotFound(id));
    }

    //Read - get a doctor by name => GET
    public Doctors getDoctorByName(String name){

        return doctorsRepository.findByNameContaining(name);
    }

    //Read - get a doctor by office => GET
    public Doctors getDoctorByOffice(String office){

        return doctorsRepository.findByOfficeContaining(office);
    }

    //Update 
}
