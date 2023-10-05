package store.bizscanner.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberSignupRequest {

    private String email;
    private String password;
    @Pattern(regexp = "^[가-힣a-zA-Z ]{2,16}$", message = "2 ~ 16 글자 이내, 특수문자, 자음 , 모음 제외")
    private String nickname;
}
