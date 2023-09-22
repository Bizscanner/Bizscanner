package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.entity.Member;
import store.bizscanner.entity.Scrap;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByMemberAndCareaCodeAndJcategoryCode(Member member, String carea_code, String jcategory_code);

    boolean existsByMemberAndCareaCodeAndJcategoryCode(Member member, String carea_code, String jcategory_code);

    List<Scrap> findAllByMember(Member member);
}
