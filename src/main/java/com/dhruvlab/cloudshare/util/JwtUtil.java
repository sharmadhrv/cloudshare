package com.dhruvlab.cloudshare.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "MySecretKeyForCloudShare112233445566778899";
    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email)
    {
            return Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
                    .signWith(SignatureAlgorithm.HS256,key)
                    .compact();
    }

    public String extractEmail(String token){
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
    }
}
