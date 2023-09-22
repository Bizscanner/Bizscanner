package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bizscanner.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
