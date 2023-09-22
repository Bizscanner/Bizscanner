package store.bizscanner.repository;

import org.springframework.data.repository.CrudRepository;
import store.bizscanner.entity.RedisRefreshToken;

import java.util.Optional;

public interface RedisRefreshTokenRepository extends CrudRepository<RedisRefreshToken, String> {

    // accessToken으로 refreshToken 찾기
    Optional<RedisRefreshToken> findByAccessToken(String accessToken);
}
