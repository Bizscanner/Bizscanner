package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.entity.Member;
import store.bizscanner.service.MemberService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("user/{userid}")
    public ResponseEntity<Member> getUser(@PathVariable("userid") Long userId) {
        return new ResponseEntity<>(memberService.findById(userId), HttpStatus.OK);
    }


}
