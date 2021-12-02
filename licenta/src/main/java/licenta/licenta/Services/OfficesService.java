package licenta.licenta.Services;

import licenta.licenta.Exception.OfficeExceptionNotFound;
import licenta.licenta.Model.Offices;
import licenta.licenta.Repository.OfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficesService {

    @Autowired
    private final OfficesRepository officesRepository;

    public OfficesService(OfficesRepository officesRepository) {
        this.officesRepository = officesRepository;
    }

    //Create - add a new office => POST
    public Offices addOffice(Offices office){

        if(officesRepository.findById(office.getId()).isPresent()){
            throw new IllegalStateException("This office already exists!");
        }
        else if(office.getId() != null && office.getIdDoctor() != null && office.getLocatie() != null){
            officesRepository.save(office);
        }

        return office;
    }

    //Read - get an office by id => GET
    public Offices getOfficeById(String id){

        return officesRepository.findById(id).orElseThrow(() -> new OfficeExceptionNotFound(id));
    }

    //Read - get an office by location => GET
    public Offices getOfficeByLocation(String location){

        return officesRepository.findByLocationContaining(location);
    }

    //Update - update an office => PUT
    public Offices updateOffice(String id, Offices officeToUpdate){

        return officesRepository.findById(id).map(o -> {

            o.setLocatie(officeToUpdate.getLocatie());
            o.setIdDoctor(officeToUpdate.getIdDoctor());
            return officesRepository.save(officeToUpdate);

        }).orElseGet(() -> {
            officeToUpdate.setId(id);
            return officesRepository.save(officeToUpdate);
        });
    }

    //Delete - delete an office by id => DELETE
    public void deleteOffice(String id){

        if(officesRepository.findById(id).isPresent()){
            officesRepository.delete(officesRepository.getById(id));
        }
        else{
            throw new IllegalStateException("This office does not exist!");
        }
    }
}
