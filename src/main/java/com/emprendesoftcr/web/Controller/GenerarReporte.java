package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public final class GenerarReporte {
	
	public static byte[] jasperPDFBytes(Map<String, Object> parameters, String rutajasper, List<?> entity ) throws Exception {

	        byte[] bytes = null;
	        try {
	            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(entity);
	            JasperPrint jasperPrint = JasperFillManager.fillReport(rutajasper, parameters,
	                    entity == null ? new JREmptyDataSource() : source);

	            bytes = JasperExportManager.exportReportToPdf(jasperPrint);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	        return bytes;
	    }

	public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> parameters, String pathJasper) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(pathJasper, parameters, new JREmptyDataSource());

		JasperExportManager.exportReportToPdfStream(jasperPrint, os);
		os.flush();
		os.close();
		return os;
	}

	public static ByteArrayInputStream getInputStreamFromReport(Map<String, Object> parameters, String pathJasper, Collection<?> collection) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		JasperPrint jasperPrint = JasperFillManager.fillReport(pathJasper, parameters, collection == null ? new JREmptyDataSource() : new JRBeanCollectionDataSource(collection));

		JasperExportManager.exportReportToPdfStream(jasperPrint, os);
		os.flush();
		os.close();
		return new ByteArrayInputStream(os.toByteArray());
	}



}
