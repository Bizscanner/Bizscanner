package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.bizscanner.entity.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
