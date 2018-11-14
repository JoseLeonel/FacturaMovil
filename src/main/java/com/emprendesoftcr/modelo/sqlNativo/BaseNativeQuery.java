package com.emprendesoftcr.modelo.sqlNativo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BaseNativeQuery {
	public String name();
  public String query();
}
