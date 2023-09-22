package store.bizscanner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Scrap extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Column(nullable = false)
    private String careaCode;

    @Column(nullable = false)
    private String jcategoryCode;

    @Builder
    public Scrap(Member member, String careaCode, String jcategoryCode) {
        this.member = member;
        this.careaCode = careaCode;
        this.jcategoryCode = jcategoryCode;
    }

}
