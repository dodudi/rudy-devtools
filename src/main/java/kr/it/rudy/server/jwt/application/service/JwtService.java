package kr.it.rudy.server.jwt.application.service;

import kr.it.rudy.server.jwt.application.dto.JwtDecodeResponse;
import kr.it.rudy.server.jwt.application.dto.JwtVerifyResponse;
import kr.it.rudy.server.jwt.domain.model.Jwt;
import kr.it.rudy.server.jwt.domain.service.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtValidator jwtValidator;

    public JwtDecodeResponse decodeJwt(String token) {
        // Domain 객체 생성 (파싱 및 기본 검증)
        Jwt jwt = Jwt.from(token);

        // DTO로 변환하여 반환
        return new JwtDecodeResponse(
                jwt.getHeader(),
                jwt.getPayload(),
                jwt.getSignature(),
                true,
                jwt.getAlgorithm(),
                jwt.getType(),
                jwt.getIssuedAt(),
                jwt.getExpiresAt(),
                jwt.isExpired()
        );
    }

    public JwtVerifyResponse verifyJwt(String token, String secret) {
        // Domain 객체 생성
        Jwt jwt = Jwt.from(token);

        // Domain 서비스를 통한 검증
        boolean isValid = jwtValidator.validate(jwt, secret);

        // DTO로 변환하여 반환
        return new JwtVerifyResponse(isValid);
    }
}
