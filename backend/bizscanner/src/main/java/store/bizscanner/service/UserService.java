package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.request.AddUserRequest;
import store.bizscanner.dto.response.AuthUserResponse;
import store.bizscanner.entity.User;
import store.bizscanner.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // DB에 User 객체 저장
    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(encoder.encode(dto.getPassword()))
                .build()).getUserId();
    }

    // userId 기준으로 User 탐색
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // email 기준으로 User 탐색
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public AuthUserResponse getEmailAndNicknameByUserId(Long userId) {
        User user =  userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
        System.out.println(userId + " " + user);
        return AuthUserResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
