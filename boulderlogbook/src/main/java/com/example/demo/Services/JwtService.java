package main.java.com.example.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final Key key;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long userId) {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }
}
