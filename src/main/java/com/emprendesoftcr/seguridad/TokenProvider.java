package com.emprendesoftcr.seguridad;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "auth";
	private static final String BASE64_SECRET = "ZTg1OTBiOTFiNzNiYTBlNzYwMDdmZjgyMTNhZGMyMmFlOGM4YzcxMmU1NTZlYWQxZWNiZGEyYzA1NTUyZGQ4OTI2OTk0ZGZlMzYwNDI0ZDVhMDg5NjY4NmIxOTVlNmJmMDQ4YmZmMjk0ZTViY2IyODRlZmI2ZDBhNGJmYTM1NTA=";
	private static final Long TOKEN_VALIDITY_SECONDS = 86400L;
	private static final Long TOKEN_VALIDITU_SECONDS_REMENBER_ME = 2592000L;
	private final Key key;
	private final JwtParser jwtParser;
	private final long tokenValidityInMilliseconds;
	private final long tokenValidityInMillisecondsForRememberMe;

	public TokenProvider() {
		byte[] keyBytes;
		String secret = "";
		if (!ObjectUtils.isEmpty(secret)) {
			log.warn("Warning: the JWT key used is not Base64-encoded. "
					+ "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
			keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		} else {
			log.debug("Using a Base64-encoded JWT secret key");
			keyBytes = Decoders.BASE64.decode(BASE64_SECRET);
		}
		key = Keys.hmacShaKeyFor(keyBytes);
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
		this.tokenValidityInMilliseconds = 1000 * TOKEN_VALIDITY_SECONDS;
		this.tokenValidityInMillisecondsForRememberMe = 1000 * TOKEN_VALIDITU_SECONDS_REMENBER_ME;
	}

	public String createToken(Authentication authentication, boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
		} else {
			validity = new Date(now + this.tokenValidityInMilliseconds);
		}

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = jwtParser.parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).filter(auth -> !auth.trim().isEmpty())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
