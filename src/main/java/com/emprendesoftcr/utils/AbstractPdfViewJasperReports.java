package com.emprendesoftcr.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.PdfExporterConfiguration;

public abstract class AbstractPdfViewJasperReports extends AbstractPdfView {

	private Logger							logger	= LoggerFactory.getLogger(this.getClass());


	
	public ByteArrayOutputStream buildJasperReport(Map<String, Object> parameters, String nombreReporte, String version) {
		try {
			List<String> list = new ArrayList<String>();

			ClassLoader loader = this.getClass().getClassLoader();

			parameters.put("SUBREPORT_DIR", ((URL) loader.getResource("/reportes/")).getPath());
			parameters.put("JASPER_REPORT", list);

			String template = getAbsoluteTemplateName(nombreReporte, parameters, version);

			JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(template));
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters);

			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.exclude.key.TransparentImage", "");
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.tagged", "true");
			jasperPrint.setProperty(PdfExporterConfiguration.PROPERTY_PDF_VERSION, "1.0");

			return processReportIfNotNull(jasperPrint);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error construyendo el Jasper Report: " + nombreReporte + ". Message: " + ex.getMessage());
		}

		return null;
	}

	private String getAbsoluteTemplateName(String nombreReporte, Map<String, Object> params, String version) {
		logger.info("Plantilla usada para la generacion del pdf: resources-jasper/jasper/templates/" + nombreReporte + "/v" + version + "/" + nombreReporte + ".jasper");

		String path = (String) params.get("SUBREPORT_DIR") + nombreReporte + "/v" + version + "/";
		params.put("SUBREPORT_DIR", path);
		return path + nombreReporte + ".jasper";
	}

	private ByteArrayOutputStream processReportIfNotNull(JasperPrint jasperPrint) throws IOException {
		if (jasperPrint != null) {
			PipedOutputStream pos = null;
			PipedInputStream pis = null;

			try {
				byte[] result = JasperExportManager.exportReportToPdf(jasperPrint);
				pos = new PipedOutputStream();
				pis = new PipedInputStream(pos, result.length);

				for (byte b : result) {
					pos.write(b);
				}
				pos.flush();

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error procesando reporte. Message: " + e.getMessage());
			} finally {
				if (pis != null) {
					pis.close();
					pos.close();
				}
			}
		}

		return null;
	}

	

}
