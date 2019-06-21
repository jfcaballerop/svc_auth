package com.mrknight.svcauth.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			token = token.replace(SecurityConstants.TOKEN_BEARER_PREFIX, "");
			final JwtParser jwtParser = Jwts.parser().setSigningKey(SecurityConstants.SUPER_SECRET_KEY);
			final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
			final Claims claims = claimsJws.getBody();

			// Se procesa el token y se recupera el usuario.
			String user = claims.getSubject();

			if (user != null) {

				final Collection<SimpleGrantedAuthority> authorities = Arrays
						.stream(claims.get(SecurityConstants.AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				return new UsernamePasswordAuthenticationToken(user, "", authorities);
			}
			return null;
		}
		return null;
	}
}
