package store.bizscanner.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import store.bizscanner.entity.enums.Role;
import store.bizscanner.global.oauth2.SocialType;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    @Pattern(regexp = "^[가-힣a-zA-Z ]{2,16}$", message = "2 ~ 16 글자 이내, 특수문자, 자음 , 모음 제외")
    private String nickname;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)



    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

}
