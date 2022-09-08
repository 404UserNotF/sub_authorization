package com.example.sub_authorization.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SALT = "HIOS---FHEKVP!??KJFO";

    public static String getToken(Map<String, String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v)->{
            builder.withClaim(k, v);
        });
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(Algorithm.HMAC256(SALT)).toString();
    }

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }

    public static DecodedJWT getDecodedJWT(String token){
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }
}
