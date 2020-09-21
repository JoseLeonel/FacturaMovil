package com.emprendesoftcr.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class DataTableDelimitador {

	private String											tabla;
	private Integer											start;
	private String											orderColumnIndex;
	private String											columnOrderDir;
	private String											columnData;
	private String											searchValue;
	private Integer											length;
	private ArrayList<String>						listaLeftJoin;
	private ArrayList<JqGridFilter>	filtros;
	private int													maxParamsToCheck	= 0;

	public DataTableDelimitador(HttpServletRequest request, String tabla) {

		/**
		 * Filtrado de la pagina por Ejemplo que enga 10 registros por pagina , 20 registros pagina
		 */
		this.length = (request.getParameter("length") != null && !request.getParameter("length").equals(" ")) ? Integer.parseInt(request.getParameter("length")) : 0;
		/**
		 * Indica de donde empieza la pagina por Ejemplo si estamos en la primara pagina empieza en 0, pero si damos click segunda pagina dice que empieza en 10(o lo que tenga parametros cantidad de registros) , si el filtrado es por 10 registros
		 */
		this.start = (request.getParameter("start") != null && !request.getParameter("start").equals(" ")) ? Integer.parseInt(request.getParameter("start")) : 0;
		/**
		 * Me indica si es ASC o DESC en la tabla
		 */
		this.columnOrderDir = (request.getParameter("order[0][dir]") != null && !request.getParameter("order[0][dir]").equals(" ")) ? request.getParameter("order[0][dir]") : "asc";
		/**
		 * searchValue me retorna el valor que se digita en el campo de busqueda principal
		 */
		this.searchValue = (request.getParameter("search[value]") != null && !request.getParameter("search[value]").equals(" ")) ? request.getParameter("search[value]") : " ";

		/**
		 * Posicion de la columna que se va ordenar
		 */
		this.orderColumnIndex = (request.getParameter("order[0][column]") != null && !request.getParameter("order[0][column]").equals(" ")) ? request.getParameter("order[0][column]") : "asc";

		/**
		 * Obtengo el campo por el cual se va ordenar el select de la tabla
		 */
		this.columnData = request.getParameter("columns[" + this.orderColumnIndex + "][name]");
		/**
		 * Nombre de la tabla de base de datos
		 */
		this.tabla = tabla;

		/**
		 * cargar los filtros individuales si se han digitado del hsql
		 */
		if (this.searchValue.equals(null) || this.searchValue.isEmpty()) {
			getValoresFiltroIndividual(request);
		} else {
			/**
			 * Se busca por search Principal , el unico problema es no poder buscar por el valor de un i1 , nota no se han realizado pruebas
			 */
			getValoresFiltro(request);
		}

	}

	private void getValoresFiltroIndividual(HttpServletRequest request) {
		/**
		 * las columnas por las que se debe buscar cuando se aplica el filtro, Verifica cuales campos de la base de datos se pintaron el datatable
		 */
		// Isolate all the parameters that begin with "columns"
		String campo = "";
		String valor = "";
		String operadorBusqueda = " like ";

		this.maxParamsToCheck = getNumberOfColumns(request);

		for (int i = 0; i < this.maxParamsToCheck; i++) {
			campo = "";
			valor = request.getParameter("columns[" + i + "][search][value]");
			if (!valor.equals(null) && !valor.isEmpty()) {
				campo = request.getParameter("columns[" + i + "][name]");
				valor = request.getParameter("columns[" + i + "][search][value]");
				addFiltro(new JqGridFilter(campo, valor, operadorBusqueda));
			}
		}

	}

	/**
	 * Metodo para obtener los campos creados en la tabla del frondend
	 * @param request
	 */
	private void getValoresFiltro(HttpServletRequest request) {
		/**
		 * las columnas por las que se debe buscar cuando se aplica el filtro, Verifica cuales campos de la base de datos se pintaron el datatable
		 */
		// Isolate all the parameters that begin with "columns"
		String campo = "";
		String valor = "";
		String operadorBusqueda = " like ";

		this.maxParamsToCheck = getNumberOfColumns(request);

		for (int i = 0; i < this.maxParamsToCheck; i++) {
			campo = "";
			valor = this.searchValue;
			campo = request.getParameter("columns[" + i + "][name]");
			addFiltro(new JqGridFilter(campo, valor, operadorBusqueda));
		}

	}

	/**
	 * Obtengo el numero maximo de columnas enviadas en el request para cargar el filtro
	 * @param request
	 * @return
	 */
	private int getNumberOfColumns(HttpServletRequest request) {
		Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames();
		List<String> lstOfParams = new ArrayList<String>();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			Matcher m = p.matcher(paramName);
			if (m.matches()) {
				lstOfParams.add(paramName);
			}
		}
		return lstOfParams.size();
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

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getOrderColumnIndex() {
		return orderColumnIndex;
	}

	public void setOrderColumnIndex(String orderColumnIndex) {
		this.orderColumnIndex = orderColumnIndex;
	}

	public String getColumnData() {
		return columnData;
	}

	public void setColumnData(String columnData) {
		this.columnData = columnData;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public ArrayList<String> getListaLeftJoin() {
		return listaLeftJoin;
	}

	public void setListaLeftJoin(ArrayList<String> listaLeftJoin) {
		this.listaLeftJoin = listaLeftJoin;
	}

	public String getColumnOrderDir() {
		return columnOrderDir;
	}

	public void setColumnOrderDir(String columnOrderDir) {
		this.columnOrderDir = columnOrderDir;
	}

	public ArrayList<JqGridFilter> getFiltros() {
		return filtros;
	}

	public void setFiltros(ArrayList<JqGridFilter> filtros) {
		this.filtros = filtros;
	}

	public int getMaxParamsToCheck() {
		return maxParamsToCheck;
	}

	public void setMaxParamsToCheck(int maxParamsToCheck) {
		this.maxParamsToCheck = maxParamsToCheck;
	}

}
