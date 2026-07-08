package com.lucnghinh.laptop_store.security;

import com.lucnghinh.laptop_store.config.JwtAuthenticationEntryPoint;
import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.service.JwtService;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.text.ParseException;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    JwtService jwtService;
    CustomUserDetailsService customUserDetailsService;
    JwtAuthenticationEntryPoint  jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt =  authHeader.substring(7);

        try{
            JWSObject jwsObject = jwtService.verifyToken(jwt);

            JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            String username = jwtClaimsSet.getSubject();

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (AuthenticationException e) {
        request.setAttribute("exception", e);
        } catch (ParseException e) {
        request.setAttribute("exception", new AuthenticationException(ErrorCode.INVALID_TOKEN));
    }
        filterChain.doFilter(request, response);
    }
}
