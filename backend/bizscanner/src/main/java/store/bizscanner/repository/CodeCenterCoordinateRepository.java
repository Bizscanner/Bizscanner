package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bizscanner.entity.CodeCenterCoordinate;


public interface CodeCenterCoordinateRepository extends JpaRepository<CodeCenterCoordinate, String> {
}
