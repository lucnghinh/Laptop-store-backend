package com.lucnghinh.laptop_store.service;


import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import com.nimbusds.jwt.JWTClaimsSet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Laptop-store-Lucnghinh.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
    try{
        jwsObject.sign(new MACSigner(secret.getBytes()));
        return  jwsObject.serialize();
    }catch (JOSEException e){
        log.error("cannot create token",e);
        throw new RuntimeException(e);
    }
    }


    public JWSObject verifyToken(String token){
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            boolean verifiedSignature = jwsObject.verify(new MACVerifier(secret.getBytes()));
            if (!verifiedSignature) {
                throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
            }
            Map<String, Object> payload = jwsObject.getPayload().toJSONObject();
            Number exp = (Number)  payload.get("exp");

            if (exp == null) {
                throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
            }

            long expirationTime = exp.longValue();
            long currentTime = Instant.now().getEpochSecond();
            if (currentTime > expirationTime) {
                throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
            }


            return jwsObject;
        }catch (ParseException | JOSEException exception){
            throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
        }
    }
}
