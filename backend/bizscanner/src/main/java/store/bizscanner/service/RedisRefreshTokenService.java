package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.entity.RedisRefreshToken;
import store.bizscanner.repository.RedisRefreshTokenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RedisRefreshTokenService {

    private final RedisRefreshTokenRepository redisRefreshTokenRepository;

    @Transactional
    public void saveRedisRefreshToken(String email, String accessToken, String refreshToken) {

        redisRefreshTokenRepository.save(new RedisRefreshToken(email, accessToken, refreshToken));
    }
}
