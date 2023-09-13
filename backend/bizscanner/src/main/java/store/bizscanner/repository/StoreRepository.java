package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.bizscanner.entity.Store;
import store.bizscanner.repository.mapping.TotalStoreMapping;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s.jcategoryName FROM Store s " +
            "WHERE s.careaCode = :careaCode AND s.yearCode = :yearCode " +
            "AND s.storeCount = (SELECT MAX(s2.storeCount) FROM Store s2 " +
            "WHERE s2.careaCode = :careaCode AND s2.yearCode = :yearCode)")
    List<String> findMaxStoreCount(@Param("careaCode") String careaCode, @Param("yearCode") String yearCode);

    @Query("SELECT s.jcategoryName FROM Store s " +
            "WHERE s.careaCode = :careaCode AND s.yearCode = :yearCode " +
            "AND s.openStoreCount = (SELECT MAX(s2.openStoreCount) FROM Store s2 " +
            "WHERE s2.careaCode = :careaCode AND s2.yearCode = :yearCode)")
    List<String> findMaxOpenStoreCount(@Param("careaCode") String careaCode, @Param("yearCode") String yearCode);

    @Query("SELECT s.jcategoryName FROM Store s " +
            "WHERE s.careaCode = :careaCode AND s.yearCode = :yearCode " +
            "AND s.closeStoreCount = (SELECT MAX(s2.closeStoreCount) FROM Store s2 " +
            "WHERE s2.careaCode = :careaCode AND s2.yearCode = :yearCode)")
    List<String> findMaxCloseStoreCount(@Param("careaCode") String careaCode, @Param("yearCode") String yearCode);

    List<TotalStoreMapping> findByCareaCodeAndJcategoryCodeAndYearCodeGreaterThanOrderByStoreIdDesc(String careaCode, String jcategoryCode, String yearCode);
}
