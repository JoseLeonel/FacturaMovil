package com.emprendesoftcr.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JqGridDelimitador {

	private String									tabla;
	private String									tipoOrdenamiento;
	private String									propiedadOrdenamiento;

	private Integer									inicio;
	private Integer									fin;
	private Integer									pagina;
	private Integer									cantidadFilas;

	private ArrayList<JqGridFilter>	filtros;
	ArrayList<String>								listaLeftJoin;

	public JqGridDelimitador(HttpServletRequest request, String tabla) throws ParseException {

		this.pagina = (request.getParameter("page") != null && !request.getParameter("page").equals(" ")) ? Integer.parseInt(request.getParameter("page")) : 0;
		this.cantidadFilas = (request.getParameter("rows") != null && !request.getParameter("rows").equals(" ")) ? Integer.parseInt(request.getParameter("rows")) : 0;
		this.inicio = (this.pagina * this.cantidadFilas) - this.cantidadFilas;
		this.fin = this.pagina * this.cantidadFilas;

		this.tipoOrdenamiento = (request.getParameter("sord") != null && !request.getParameter("sord").equals(" ")) ? request.getParameter("sord") : "desc";
		this.propiedadOrdenamiento = (request.getParameter("sidx") != null && !request.getParameter("sidx").equals(" ")) ? request.getParameter("sidx") : "id";

		this.tabla = tabla;

		// Se crean los filtros
		creaFiltroBusquedaUnica(request);
		creaFiltroBusquedaCompuesta(request);
	}

	private void creaFiltroBusquedaUnica(HttpServletRequest request) {
		String campo = "";
		String valor = "";
		String operadorBusqueda = "";
		if (request.getParameter("searchField") != null) {
			campo = (request.getParameter("searchField") != null) ? request.getParameter("searchField") : "";
			valor = (request.getParameter("searchString") != null) ? request.getParameter("searchString") : "";
			operadorBusqueda = (request.getParameter("searchOper") != null) ? request.getParameter("searchOper") : "";
			addFiltro(new JqGridFilter(campo, valor, operadorBusqueda));
		}
	}

	private void creaFiltroBusquedaCompuesta(HttpServletRequest request) throws ParseException {
		if (request.getParameter("filters") != null && request.getParameter("filters").length() > 0) {
			String campo = "";
			String valor = "";
			String operadorBusqueda = "";
			// Se obtienen los datos del body json
			JSONObject json = (JSONObject) new JSONParser().parse(request.getParameter("filters"));

			// Se obtiene los ids de articulos
			JSONArray jsonArray = (JSONArray) json.get("rules");
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonRule = (JSONObject) jsonArray.get(i);
					campo = (String) jsonRule.get("field");
					operadorBusqueda = (String) jsonRule.get("op");
					valor = (String) jsonRule.get("data");
					addFiltro(new JqGridFilter(campo, valor, operadorBusqueda));
				}
			}
		}
	}

	public void addFiltro(JqGridFilter filtro) {
		if (filtros == null) {
			filtros = new ArrayList<JqGridFilter>();
		}
		filtros.add(filtro);
	}

	public void addLeftJoin(String leftJoin) {
		if (listaLeftJoin == null) {
			listaLeftJoin = new ArrayList<String>();
		}
		listaLeftJoin.add(leftJoin);
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

	public ArrayList<JqGridFilter> getFiltros() {
		return filtros;
	}

	public void setFiltros(ArrayList<JqGridFilter> filtros) {
		this.filtros = filtros;
	}

	public ArrayList<String> getListaLeftJoin() {
		return listaLeftJoin;
	}

	public void setListaLeftJoin(ArrayList<String> listaLeftJoin) {
		this.listaLeftJoin = listaLeftJoin;
	}

}
