package com.emprendesoftcr.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.google.common.collect.ImmutableList;

public class RespuestaServiceValidator<T extends Object> implements Serializable {

	private static final long	serialVersionUID	= 8287810971324131662L;
	private Integer						status;
	private String						message;
	private Collection<T>			listaObjetos;

	public Collection<T> getListaObjetos() {
		return listaObjetos;
	}

	public void setListaObjetos(Collection<T> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	public RespuestaServiceValidator() {
		this.listaObjetos = new HashSet<T>();
	}

	public void addObjeto(T object) {
		listaObjetos.add(object);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("all")
	public static RespuestaServiceValidator OK() {
		return new Builder().ok().build();
	}

	@SuppressWarnings("all")
	public static RespuestaServiceValidator OK(String msg, Object obj) {
		return obj instanceof Collection ? new Builder().ok().setMsg(msg).setObjects((Collection) obj).build() : new Builder().ok().setMsg(msg).setObjects(ImmutableList.of(obj)).build();
	}

	@SuppressWarnings("all")
	public static RespuestaServiceValidator OK(Object obj) {
		return obj instanceof Collection ? new Builder().ok().setObjects((Collection) obj).build() : new Builder().ok().setObjects(ImmutableList.of(obj)).build();
	}

	@SuppressWarnings("all")
	public static RespuestaServiceValidator ERROR(Exception e) {
		return new Builder().error().setMsg(e.getMessage()).build();
	}

	@SuppressWarnings("all")
	public static RespuestaServiceValidator ERROR(String msg, List<? extends Object> errors) {
		return new Builder().error().setMsg(msg).setObjects((Collection<Object>) errors).build();
	}

	@SuppressWarnings("all")
	public static class EMPTY {

		public static RespuestaServiceValidator OK(String msg) {
			return new Builder().ok().setMsg(msg).build();
		}

		public static RespuestaServiceValidator ERROR(String msg) {
			return new Builder().error().setMsg(msg).build();
		}
	}

	public static class BUNDLE_MSG_SOURCE {

		@SuppressWarnings("all")
		public static RespuestaServiceValidator OK(String msg) {
			return RespuestaServiceValidator.OK(Constantes.RESOURCE_BUNDLE.getString(msg));
		}

		@SuppressWarnings("all")
		public static RespuestaServiceValidator OK(String msg, Object obj) {
			return RespuestaServiceValidator.OK(Constantes.RESOURCE_BUNDLE.getString(msg), obj);
		}

		@SuppressWarnings("all")
		public static RespuestaServiceValidator ERROR(String msg, Object obj) {
			return RespuestaServiceValidator.OK(Constantes.RESOURCE_BUNDLE.getString(msg), obj);
		}

		@SuppressWarnings("all")
		public static RespuestaServiceValidator ERROR(String msg) {
			return RespuestaServiceValidator.EMPTY.ERROR(Constantes.RESOURCE_BUNDLE.getString(msg));
		}

		@SuppressWarnings("all")
		public static RespuestaServiceValidator ERROR(String msg, List<? extends Object> errors) {
			return RespuestaServiceValidator.ERROR(Constantes.RESOURCE_BUNDLE.getString(msg), errors);
		}
	}

	private static class Builder<T> {

		private final RespuestaServiceValidator<T> response;

		@SuppressWarnings("all")
		private Builder() {
			this.response = new RespuestaServiceValidator();
		}

		@SuppressWarnings("all")
		private Builder ok() {
			response.setStatus(HttpStatus.OK.value());
			return this;
		}

		@SuppressWarnings("all")
		private Builder error() {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return this;
		}

		@SuppressWarnings("all")
		private Builder setMsg(String msg) {
			response.setMessage(msg);
			return this;
		}

		@SuppressWarnings("all")
		private Builder setObjects(Collection<T> objs) {
			response.setListaObjetos((Collection<T>) objs);
			return this;
		}

		@SuppressWarnings("all")
		private RespuestaServiceValidator build() {
			return response;
		}

	}
}
