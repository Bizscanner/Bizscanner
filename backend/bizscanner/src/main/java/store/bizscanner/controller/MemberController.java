package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.request.MemberSignupRequest;
import store.bizscanner.dto.request.UpdateNicknameRequest;
import store.bizscanner.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<Void> signUp(@RequestBody MemberSignupRequest memberSignupRequest) throws Exception {
        memberService.signUp(memberSignupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> updateNickname(@Valid @RequestBody UpdateNicknameRequest updateNicknameRequest) {
        memberService.updateNickname(updateNicknameRequest.getEmail(), updateNicknameRequest.getNickname());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
