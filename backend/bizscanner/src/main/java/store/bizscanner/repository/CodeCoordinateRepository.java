package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bizscanner.entity.CodeCoordinate;

import java.util.List;

public interface CodeCoordinateRepository extends JpaRepository<CodeCoordinate, Long> {

    List<CodeCoordinate> findByCode(String code);
}
