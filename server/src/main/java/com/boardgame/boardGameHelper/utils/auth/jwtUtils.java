package com.boardgame.boardGameHelper.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtils {

    private String secret = "AV12JJKSD3FDS41DF453SLKFJKLSDSDFFSDFWEWQEQW412341234LK341234SF23";

    public Date extractExpiration(String token) {
        var claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public Integer extractId(String token) {
        try {
            final var claims = extractAllClaims(token);
            return claims.get("id", Integer.class);
        } catch (Exception e) {
            System.out.println("Error extracting id: " + e.getMessage());
            return null;
        }
    }

    public String extractClaim(String token, String claim) {
        try {
            final var claims = extractAllClaims(token);
            return claims.get(claim, String.class);
        } catch (Exception e) {
            System.out.println("Error extracting claim: " + e.getMessage());
            return null;
        }
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Error extracting all claims: " + e.getMessage());
            return null;
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Map<String, Object> claims) {
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            Date expireDate = new Date(nowMillis + 1000 * 60 * 60 * 10);

            byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

            String compactJws = Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expireDate)
                    .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
                    .compact();

            return compactJws;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractClaim(token, username);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

