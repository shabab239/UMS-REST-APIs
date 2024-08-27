package com.shabab.UniversityManagementSystem.security.service;

import com.shabab.UniversityManagementSystem.admin.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 26/08/2024
 */

@Service
public class JwtService {

    private String secretKey = "";

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateJwt(User user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", user.getId());
        claims.put("universityId", user.getUniversity().getId());

        Date now = new Date();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 30 * 60 * 1000))
                .and()
                .signWith(generateSigningKey())
                .compact();

    }

    private SecretKey generateSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public Long extractUserId(String jwt) {
        return extractClaim(jwt, claims -> claims.get("userId", Long.class));
    }

    public Long extractUniversityId(String jwt) {
        return extractClaim(jwt, claims -> claims.get("universityId", Long.class));
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean validateJwt(String jwt, UserDetails userDetails) {
        final String userName = extractUsername(jwt);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
