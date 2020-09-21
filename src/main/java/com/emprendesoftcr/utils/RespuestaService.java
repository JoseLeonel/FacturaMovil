package com.emprendesoftcr.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class RespuestaService implements Serializable {



	
	private static final long serialVersionUID = 2242217154424368808L;
	
	private Collection<Object>	rows;
	private Long								total;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	private Integer	page;
	private Long		records;

	public RespuestaService() {
		rows = new ArrayList<Object>();
	}

	public Collection<Object> getRows() {
		return rows;
	}

	public void setRows(Collection<Object> rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

}