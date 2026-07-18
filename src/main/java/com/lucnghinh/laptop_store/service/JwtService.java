package com.lucnghinh.laptop_store.service;


import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.repository.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import com.nimbusds.jwt.JWTClaimsSet;

import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {

    @Value("${jwt.secret}")
    @NonFinal
    String secret;

    @Value("${jwt.access-token-expires-in-ms}")
    @NonFinal
    long accessTokenExpiresInMs;


    @Value("${jwt.refresh-token-expires-in-ms}")
    @NonFinal
    long refreshTokenExpiresInMs;

    InvalidatedTokenRepository invalidatedTokenRepository;

        public String generateAccessToken(User user) throws JOSEException {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer("Laptop-store-Lucnghinh.com")
                    .issueTime(new Date())
                    .expirationTime(new Date(Instant.now().plus(accessTokenExpiresInMs, ChronoUnit.MILLIS).toEpochMilli()))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope", buildScope(user))
                    .claim("type", "access")
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(secret.getBytes()));

            return  jwsObject.serialize();
        }

        public String generateRefreshToken(User user) throws JOSEException {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .jwtID(UUID.randomUUID().toString())
                    .issueTime(new Date())
                    .issuer("Laptop-store-Lucnghinh.com")
                    .expirationTime(new Date(Instant.now().plus(refreshTokenExpiresInMs,ChronoUnit.MILLIS).toEpochMilli()))
                    .claim("type","refresh")
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            jwsObject.sign(new MACSigner(secret.getBytes()));

            return jwsObject.serialize();
        }


        public SignedJWT verifyToken(String token) {

                try {
                SignedJWT signedJWT = SignedJWT.parse(token);
                boolean verifiedSignature = signedJWT.verify(new MACVerifier(secret.getBytes()));

                Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();

                    if (!verifiedSignature) {
                        throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
                    }

                    if (exp == null || exp.before(new Date())) {
                    throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
                }

                    if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
                        throw new AuthenticationException(ErrorCode.UNAUTHENTICATED);
                    }

                return signedJWT;

            }catch (ParseException | JOSEException exception){
                    log.error("Xảy ra lỗi khi verify hoặc parse token: {}", exception.getMessage());
                throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
            }
        }

    public SignedJWT verifyAccessToken(String token) throws ParseException {
            SignedJWT signedJWT = verifyToken(token);

            String type = signedJWT.getJWTClaimsSet().getStringClaim("type");

            if(!"access".equals(type)){
                throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
            }
            return signedJWT;
    }

    public SignedJWT verifyRefreshToken(String token) throws ParseException {
        SignedJWT signedJWT = verifyToken(token);

        String type = signedJWT.getJWTClaimsSet().getStringClaim("type");

        if(!"refresh".equals(type)){
            throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        return signedJWT;
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());

                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));
            });
        return stringJoiner.toString();
    }
}
