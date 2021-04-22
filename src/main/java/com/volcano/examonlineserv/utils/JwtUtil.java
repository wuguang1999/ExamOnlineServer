package com.volcano.examonlineserv.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Jwt工具类
 */
@Configuration
public class JwtUtil {

    public static final long EXPIRATION_TIME = 3600_000_000L;
    public static final String SECRET = "EXAM_ONLINE_SECRET";

    /**
     * 生成jwtToken
     */
    public static String generateToken(Integer id) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        Date expireTime = new Date(System.currentTimeMillis()+EXPIRATION_TIME);
        JwtBuilder builder = Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, SECRET)
                .setExpiration(expireTime);
        return builder.compact();
    }


    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
    public static Integer validateToken(String token) {
        if (token != null) {
            Claims claims;
            try {
                claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();
            }catch (Exception e) {
                claims = null;
            }
            Integer id = Integer.parseInt(claims.getSubject());
            if (id == null) {
                throw new TokenValidationException("Wrong User");
            } else {
                return id;
            }
        } else {
            throw new TokenValidationException("Missing token");
        }
    }
    /**
     * 异常类，负责处理token的异常
     */
    static class TokenValidationException extends RuntimeException {
        private static final long serialVersionUID = -7946690694369283250L;

        public TokenValidationException(String msg) {
            super(msg);
        }
    }

    public static long getEXPIRATION_TIME(){
        return JwtUtil.EXPIRATION_TIME;
    }
}

