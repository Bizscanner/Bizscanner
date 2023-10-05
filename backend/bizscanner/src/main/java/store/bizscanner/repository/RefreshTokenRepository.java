package store.bizscanner.repository;

import org.springframework.data.repository.CrudRepository;
import store.bizscanner.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
