package com.logesta.svcauth.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logesta.svcauth.model.Usuario;
import com.logesta.svcauth.security.SecurityConstants;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario credenciales = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
            .setIssuedAt(new Date())
                .setIssuer(SecurityConstants.ISSUER_INFO)
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SUPER_SECRET_KEY)
                .compact();
                
		response.addHeader(
            SecurityConstants.HEADER_AUTHORIZACION_KEY, 
			SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(
					"{\"" + SecurityConstants.HEADER_AUTHORIZACION_KEY + "\":\"" + SecurityConstants.TOKEN_BEARER_PREFIX+token + "\"}"
			);	
	}
}