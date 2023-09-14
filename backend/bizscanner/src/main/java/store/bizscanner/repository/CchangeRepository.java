package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Cchange;

import java.util.Optional;

@Repository
public interface CchangeRepository extends JpaRepository<Cchange, Long> {
    Optional<Cchange> findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(String careaCode);
}
