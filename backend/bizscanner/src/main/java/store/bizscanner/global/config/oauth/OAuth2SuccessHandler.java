package store.bizscanner.global.config.oauth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import store.bizscanner.entity.RefreshToken;
import store.bizscanner.entity.Member;
import store.bizscanner.global.config.jwt.TokenProvider;
import store.bizscanner.global.util.CookieUtil;
import store.bizscanner.repository.RefreshTokenRepository;
import store.bizscanner.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_URL = "http://localhost:3000"; // 리다이렉트할 URL

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final MemberService memberService;

    // 인증 성공 시 호출
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // email 기준으로 User 탐색
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Member member = memberService.findByEmail((String) oAuth2User.getAttributes().get("email"));

        // Refresh Token 생성 -> 저장 -> 쿠키에 저장
        String refreshToken = tokenProvider.generateToken(member, REFRESH_TOKEN_DURATION);
        saveRefreshToken(member.getUserId(), refreshToken);
        addRefreshTokenToCookie(request, response, refreshToken);


        // Access Token 생성 -> 경로에 Access Token 추가
        String accessToken = tokenProvider.generateToken(member, ACCESS_TOKEN_DURATION);
        String targetUrl = getTargetUrl(accessToken);
        // 인증 관련 설정값, 쿠키 제거
        clearAuthenticationAttributes(request, response);
        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    // 생성된 Refresh Token을 전달받아 데이터베이스에 저장
    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(userId, newRefreshToken));

        refreshTokenRepository.save(refreshToken);
    }

    // 생성된 Refresh Token을 쿠키에 저장
    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        // 기존 Refresh Token 쿠키 삭제
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        // 새로운 Refresh Token 쿠키 저장
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    // 인증 관련 설정값, 쿠키 제거
    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        // 인증 관련 쿠키 모두 제거
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // Access Token을 경로에 추가
    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam("token", token)
                .build()
                .toUriString();
    }

}

