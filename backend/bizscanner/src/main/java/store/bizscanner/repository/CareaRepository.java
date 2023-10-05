package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Carea;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareaRepository extends JpaRepository<Carea, Long> {
    Optional<Carea> findByCareaCode(String careaCode);

    List<Carea> findByDong(String dong);
}
