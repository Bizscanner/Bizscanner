package store.bizscanner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String careaCode;
    private String jcategoryCode;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Builder
    public Comment(String careaCode, String jcategoryCode, String contents, Member member) {
        this.careaCode = careaCode;
        this.jcategoryCode = jcategoryCode;
        this.contents = contents;
        this.member = member;
    }

    public Comment update(String contents) {
        this.contents = contents;
        return this;
    }
}
