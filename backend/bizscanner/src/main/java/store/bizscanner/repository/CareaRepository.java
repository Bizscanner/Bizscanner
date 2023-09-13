package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Carea;

@Repository
public interface CareaRepository extends JpaRepository<Carea, Long> {
}
