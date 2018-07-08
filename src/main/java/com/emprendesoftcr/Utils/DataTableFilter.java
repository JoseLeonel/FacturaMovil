package com.emprendesoftcr.Utils;

import javax.servlet.http.HttpServletRequest;

public class DataTableFilter {

	private String	tabla;
	private String	campo;
	private String	valor;
	private String	operadorBusqueda;
	private String	tipoOrdenamiento;
	private String	propiedadOrdenamiento;

	private Integer	inicio;
	private Integer	fin;
	private Integer	pagina;
	private Integer	cantidadFilas;

	public DataTableFilter(HttpServletRequest request, @SuppressWarnings("rawtypes") Class clazz) {
		this.pagina = (request.getParameter("page") != null && !request.getParameter("page").equals(" ")) ? Integer.parseInt(request.getParameter("page")) : 0;
		this.cantidadFilas = (request.getParameter("rows") != null && !request.getParameter("rows").equals(" ")) ? Integer.parseInt(request.getParameter("rows")) : 0;

		this.inicio = (this.pagina * this.cantidadFilas) - this.cantidadFilas;
		this.fin = this.pagina * this.cantidadFilas;

		this.tipoOrdenamiento = (request.getParameter("sord") != null && !request.getParameter("sord").equals(" ")) ? request.getParameter("sord") : "desc";
		this.propiedadOrdenamiento = (request.getParameter("sidx") != null && !request.getParameter("sidx").equals(" ")) ? request.getParameter("sidx") : "id";
		this.campo = (request.getParameter("searchField") != null) ? request.getParameter("searchField") : "";
		this.valor = (request.getParameter("searchString") != null) ? request.getParameter("searchString") : "";
		this.operadorBusqueda = (request.getParameter("searchOper") != null) ? request.getParameter("searchOper") : "";
		this.tabla = clazz.getSimpleName();
	}

	public DataTableFilter(String campo, String valor, String operadorBusqueda) {
		this.campo = campo;
		this.operadorBusqueda = operadorBusqueda;
		this.valor = valor;
	}

	public Long calculaCantidadPaginas(Long total) {
		return Math.round(Math.ceil(new Double(total) / new Double(cantidadFilas)));
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getOperadorBusqueda() {
		return operadorBusqueda;
	}

	public void setOperadorBusqueda(String operadorBusqueda) {
		this.operadorBusqueda = operadorBusqueda;
	}

	public String getTipoOrdenamiento() {
		return tipoOrdenamiento;
	}

	public void setTipoOrdenamiento(String tipoOrdenamiento) {
		this.tipoOrdenamiento = tipoOrdenamiento;
	}

	public String getPropiedadOrdenamiento() {
		return propiedadOrdenamiento;
	}

	public void setPropiedadOrdenamiento(String propiedadOrdenamiento) {
		this.propiedadOrdenamiento = propiedadOrdenamiento;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	public Integer getFin() {
		return fin;
	}

	public void setFin(Integer fin) {
		this.fin = fin;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getCantidadFilas() {
		return cantidadFilas;
	}

	public void setCantidadFilas(Integer cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}

}
