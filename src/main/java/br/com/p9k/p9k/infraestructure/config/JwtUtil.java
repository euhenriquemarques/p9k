// JwtUtil.java
package br.com.p9k.p9k.infraestructure.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "p9k_@_MorcegO_pk9f_ValidAnd0_token_peR&!r@_#_$";
    private final long EXPIRATION = 3600000; // 1 hora

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username, int userId, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("idUsuario", userId) // Inclui o ID do usuário no token
                .claim("role", role)        // Inclui a role do usuário no token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public int extractUserId(String token) {
        long id=  Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("idUsuario", Long.class); // Recupera o ID do usuário

        return (int) id;
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Log a exceção se necessário
            return false;
        }
    }
}