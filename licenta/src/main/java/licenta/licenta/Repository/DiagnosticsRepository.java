package licenta.licenta.Repository;

import licenta.licenta.Model.Diagnostics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticsRepository extends JpaRepository<Diagnostics, String> {
}
