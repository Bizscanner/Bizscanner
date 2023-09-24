package store.bizscanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bizscanner.entity.Comment;
import store.bizscanner.entity.Member;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCareaCodeAndJcategoryCode(String careaCode, String jcategoryCode);
    List<Comment> findByMember(Member member);
}
