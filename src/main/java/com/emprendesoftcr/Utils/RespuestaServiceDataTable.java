package com.emprendesoftcr.Utils;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

public class RespuestaServiceDataTable<T extends Object> implements Serializable {

	private static final long	serialVersionUID	= 7725183457535925681L;

	private int								draw;
	private Long							recordsTotal;
	private Long							recordsFiltered;

	private Collection<T>			aaData;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public Collection<T> getAaData() {
		return aaData;
	}

	public void setAaData(Collection<T> aaData) {
		this.aaData = aaData;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public static class Builder<T extends Object> {

		private final RespuestaServiceDataTable response;

		public Builder(HttpServletRequest request, Collection<T> objs, Long total) {
			this.response = new RespuestaServiceDataTable();
			response.setRecordsTotal(total);
			response.setRecordsFiltered(total);
			if (request.getParameter("draw") != null) {
				if (!request.getParameter("draw").equals(" ")) {
					response.setDraw(Integer.parseInt(request.getParameter("draw")));

				}
			} else {
				response.setDraw(0);
			}

			response.setAaData(objs);
		}

		public RespuestaServiceDataTable build() {
			return response;
		}
	}
}