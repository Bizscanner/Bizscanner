package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Population;
import store.bizscanner.repository.mapping.TotalPopulationMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {

    Optional<Population> findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(String careaCode);

    List<TotalPopulationMapping> findByCareaCodeAndYearCodeGreaterThanOrderByYearCodeAscQuarterCodeAsc(String careaCode, String yearCode);
}
