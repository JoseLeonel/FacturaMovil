package com.emprendesoftcr.web.command;

public class ComandaCocinaCommand {

	private String	codigo;

	private String	descripcion;

	private String	comentario;

	private String	nombreImpresora;

	private Double	total;

	private Double	imp_serv;

	private Long		idMesa;

	private String	consecutivofactura;

	private Long		idProforma;

	private Long		idArticulo;

	public ComandaCocinaCommand() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNombreImpresora() {
		return nombreImpresora;
	}

	public void setNombreImpresora(String nombreImpresora) {
		this.nombreImpresora = nombreImpresora;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getImp_serv() {
		return imp_serv;
	}

	public void setImp_serv(Double imp_serv) {
		this.imp_serv = imp_serv;
	}

	public Long getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}

	public String getConsecutivofactura() {
		return consecutivofactura;
	}

	public void setConsecutivofactura(String consecutivofactura) {
		this.consecutivofactura = consecutivofactura;
	}

	public Long getIdProforma() {
		return idProforma;
	}

	public void setIdProforma(Long idProforma) {
		this.idProforma = idProforma;
	}

	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

}
