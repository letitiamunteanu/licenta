package licenta.licenta.Repository;

import licenta.licenta.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, String> {
    Doctors findByNameContaining(String name);

    Doctors findByOfficeContaining(String office);
}
