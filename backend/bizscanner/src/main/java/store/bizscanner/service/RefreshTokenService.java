package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.entity.RefreshToken;
import store.bizscanner.repository.RefreshTokenRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveOrUpdateRefreshToken(String email, String refreshToken) {

        refreshTokenRepository.findByEmail(email).ifPresentOrElse(
                token -> token.setRefreshToken(refreshToken),
                () -> refreshTokenRepository.save(new RefreshToken(email, refreshToken))
        );
    }

}
