package com.example.demo.config.security;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.Duration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@Slf4j
@ConfigurationProperties("jwt")
public class JwtUtils {
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    private String secretKey;
    private String issuer;
    private String audience;
    private Duration duration;

    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setAudience(audience)
                .setExpiration(new Date(System.currentTimeMillis() + duration.getMilliseconds()))
                .signWith(ALGORITHM, getEncriptionKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getEncriptionKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("An error ocurred while trying to validate JWT token. Error: {}", e.getMessage(), e);
        }
        return false;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(getEncriptionKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    private SecretKeySpec getEncriptionKey() {
        return new SecretKeySpec(secretKey.getBytes(), ALGORITHM.getJcaName());
    }
}
