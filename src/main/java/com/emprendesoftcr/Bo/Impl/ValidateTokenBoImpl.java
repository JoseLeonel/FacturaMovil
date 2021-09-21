package com.emprendesoftcr.Bo.Impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.seguridad.TokenProvider;

@EnableTransactionManagement
@Service("validateTokenBo")
public class ValidateTokenBoImpl implements ValidateTokenBo {

	public final String AUTHORIZATION_HEADER = "Authorization";
	@Autowired
	TokenProvider tokenProvider;

	public  boolean validarTokenApis(ServletRequest servletRequest)
		        throws IOException, ServletException {
		  boolean resultado = false;
		        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		        String jwt = resolveToken(httpServletRequest);
		        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
		        	resultado = true;
		        }
				return resultado;
		        
		    }

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
