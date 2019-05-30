package com.emprendesoftcr.Utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfView extends AbstractPdfViewJasperReports {

	private static PdfView instance;

	public PdfView() {
		if (instance == null) {
			instance = this;
		}
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	}

}
