package licenta.licenta.Repository;

import licenta.licenta.Model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {

    Patients findByNumeContaining(String nume);

    Patients findByCnpContaining(String cnp);
}
