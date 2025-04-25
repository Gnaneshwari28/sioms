package com.sioms.inventory.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

   private static final String SECRET_KEY = "mySuperSecretKey1234567890123456"; // This is a shared secret key (must be at least 256-bits for HS256)

    // Return the secret key for signing and verification
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate the JWT token with the user details and expiration time
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with the shared secret key using HS256
                .compact();
    }

    // Extract claims from the token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Use the same secret key to verify
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate the token based on username and expiration
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
