package com.emprendesoftcr.modelo;


import java.io.Serializable;

import javax.mail.util.ByteArrayDataSource;

/**
 * Clase para manejar archivos adjuntos a enviar por correo
 **/
public class Attachment implements Serializable {

	private static final long serialVersionUID = -8087014841067488211L;

	private String nombre;
	private ByteArrayDataSource attachment;

	public Attachment(String nombre, ByteArrayDataSource attachment) {
		this.nombre = nombre;
		this.attachment = attachment;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ByteArrayDataSource getAttachment() {
		return attachment;
	}

	public void setAttachment(ByteArrayDataSource attachment) {
		this.attachment = attachment;
	}
}