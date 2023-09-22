package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.response.AuthUserResponse;
import store.bizscanner.entity.Member;
import store.bizscanner.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    // userId 기준으로 User 탐색
    public Member findById(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // email 기준으로 User 탐색
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public AuthUserResponse getEmailAndNicknameByUserId(Long userId) {
        Member member =  memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        System.out.println(userId + " " + member);
        return AuthUserResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
