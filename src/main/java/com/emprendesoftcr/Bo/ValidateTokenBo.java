package com.emprendesoftcr.Bo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

public interface ValidateTokenBo {
	public  boolean validarTokenApis(ServletRequest servletRequest)
	        throws IOException, ServletException;
}
