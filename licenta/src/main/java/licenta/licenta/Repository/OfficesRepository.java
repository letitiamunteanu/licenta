package licenta.licenta.Repository;

import licenta.licenta.Model.Offices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficesRepository extends JpaRepository<Offices, String> {
    Offices findByLocationContaining(String location);
}
