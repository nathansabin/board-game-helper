package com.boardgame.boardGameHelper.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtils {

    private String secret = "AV12JJKSD3FDS41DF453SLKFJKLSDLK341234SF23";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
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
                    .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                    .compact();

            return compactJws;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

