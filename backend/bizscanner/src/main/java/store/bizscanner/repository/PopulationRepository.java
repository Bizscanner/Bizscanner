package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Population;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {

    Population findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(String careaCode);
}
