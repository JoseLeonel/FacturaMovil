package com.emprendesoftcr.type.json;

import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.jsonElement;
import static com.emprendesoftcr.fisco.Keys.XML_CLAVE;
import static com.emprendesoftcr.fisco.Keys.XML_DETALLE_MENSAJE;
import static com.emprendesoftcr.fisco.Keys.XML_MENSAJE;
import static com.emprendesoftcr.fisco.Keys.XML_MENSAJE_HACIENDA;
import static com.emprendesoftcr.fisco.Keys.XML_MONTO_TOTAL_IMPUESTO;
import static com.emprendesoftcr.fisco.Keys.XML_NOMBRE_EMISOR;
import static com.emprendesoftcr.fisco.Keys.XML_NOMBRE_RECEPTOR;
import static com.emprendesoftcr.fisco.Keys.XML_NUMERO_CEDULA_EMISOR;
import static com.emprendesoftcr.fisco.Keys.XML_NUMERO_CEDULA_RECEPTOR;
import static com.emprendesoftcr.fisco.Keys.XML_TIPO_IDENTIFICACION_EMISOR;
import static com.emprendesoftcr.fisco.Keys.XML_TIPO_IDENTIFICACION_RECEPTOR;
import static com.emprendesoftcr.fisco.Keys.XML_TOTAL_FACTURA;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.emprendesoftcr.type.MensajeHacienda;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class MensajeHaciendaXml implements MensajeHacienda {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String clave;
    private final String nombreEmisor;
    private final String tipoIdentificacionEmisor;
    private final String numeroCedulaEmisor;
    private final String nombreReceptor;
    private final String tipoIdentificacionReceptor;
    private final String numeroCedulaReceptor;
    private final String mensaje;
    private final String detalleMensaje;
    private final Double montoTotalImpuesto;
    private final Double totalFactura;

    private MensajeHaciendaXml(String clave, String nombreEmisor, String tipoIdentificacionEmisor,
                               String numeroCedulaEmisor, String nombreReceptor,
                               String tipoIdentificacionReceptor, String numeroCedulaReceptor,
                               String mensaje, String detalleMensaje,
                               Double montoTotalImpuesto, Double totalFactura) {
        this.clave = clave;
        this.nombreEmisor = nombreEmisor;
        this.tipoIdentificacionEmisor = tipoIdentificacionEmisor;
        this.numeroCedulaEmisor = numeroCedulaEmisor;
        this.nombreReceptor = nombreReceptor;
        this.tipoIdentificacionReceptor = tipoIdentificacionReceptor;
        this.numeroCedulaReceptor = numeroCedulaReceptor;
        this.mensaje = mensaje;
        this.detalleMensaje = detalleMensaje;
        this.montoTotalImpuesto = montoTotalImpuesto;
        this.totalFactura = totalFactura;
    }

    public static MensajeHaciendaXml instance(String clave, String nombreEmisor, String tipoIdentificacionEmisor,
                               String numeroCedulaEmisor, String nombreReceptor,
                               String tipoIdentificacionReceptor, String numeroCedulaReceptor,
                               String mensaje, String detalleMensaje,
                               Double montoTotalImpuesto, Double totalFactura) {
        return new MensajeHaciendaXml(clave, nombreEmisor, tipoIdentificacionEmisor,
                numeroCedulaEmisor, nombreReceptor, tipoIdentificacionReceptor, numeroCedulaReceptor,
                mensaje, detalleMensaje, montoTotalImpuesto,  totalFactura);
    }

    @Override
    public String clave() {
        return clave;
    }

    @Override
    public String nombreEmisor() {
        return nombreEmisor;
    }

    @Override
    public String tipoIdentificacionEmisor() {
        return tipoIdentificacionEmisor;
    }

    @Override
    public String numeroCedulaEmisor() {
        return numeroCedulaEmisor;
    }

    @Override
    public String nombreReceptor() {
        return nombreReceptor;
    }

    @Override
    public String tipoIdentificacionReceptor() {
        return tipoIdentificacionReceptor;
    }

    @Override
    public String numeroCedulaReceptor() {
        return numeroCedulaReceptor;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }

    @Override
    public String detalleMensaje() {
        return detalleMensaje;
    }

    @Override
    public Double montoTotalImpuesto() {
        return montoTotalImpuesto;
    }

    @Override
    public Double totalFactura() {
        return totalFactura;
    }

    public static MensajeHacienda from (String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
                dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            doc = dBuilder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            Element ele = doc.getDocumentElement();
            return DEFAULT_BUILDER.clave(ele).nombreEmisor(ele).tipoIdentificacionEmisor(ele).
                    numeroCedulaEmisor(ele).nombreReceptor(ele).tipoIdentificacionReceptor(ele).
                    numeroCedulaReceptor(ele).mensaje(ele).detalleMensaje(ele).
                    montoTotalImpuesto(ele).totalFactura(ele).build();
        } catch (ParserConfigurationException | SAXException | IOException e) {
           return null;
        }
    }

    public static class Builder implements MensajeHacienda.Builder {

        private XPath xpath;
        private String clave;
        private String nombreEmisor;
        private String tipoIdentificacionEmisor;
        private String numeroCedulaEmisor;
        private String nombreReceptor;
        private String tipoIdentificacionReceptor;
        private String numeroCedulaReceptor;
        private String mensaje;
        private String detalleMensaje;
        private Double montoTotalImpuesto;
        private Double totalFactura;

        public Builder() {
            XPathFactory xpf = XPathFactory.newInstance();
            this.xpath = xpf.newXPath();
        }

        @Override
        public MensajeHacienda.Builder clave(String clave) {
            this.clave = clave;
            return this;
        }

        @Override
        public MensajeHacienda.Builder clave(Element ele) {
            try {
                this.clave = xpath.evaluate(getXmlPath(XML_CLAVE), ele);
            } catch (XPathExpressionException exc) {
                this.clave = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder nombreEmisor(String nombreEmisor) {
            this.nombreEmisor = nombreEmisor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder nombreEmisor(Element ele) {
            try {
                this.nombreEmisor = xpath.evaluate(getXmlPath(XML_NOMBRE_EMISOR), ele);
            } catch (XPathExpressionException exc) {
                this.nombreEmisor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder tipoIdentificacionEmisor(String tipoIdentificacionEmisor) {
            this.tipoIdentificacionEmisor = tipoIdentificacionEmisor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder tipoIdentificacionEmisor(Element ele) {
            try {
                this.tipoIdentificacionEmisor = xpath.evaluate(getXmlPath(XML_TIPO_IDENTIFICACION_EMISOR), ele);
            } catch (XPathExpressionException exc) {
                this.tipoIdentificacionEmisor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder numeroCedulaEmisor(String numeroCedulaEmisor) {
            this.numeroCedulaEmisor = numeroCedulaEmisor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder numeroCedulaEmisor(Element ele) {
            try {
                this.numeroCedulaEmisor = xpath.evaluate(getXmlPath(XML_NUMERO_CEDULA_EMISOR), ele);
            } catch (XPathExpressionException exc) {
                this.numeroCedulaEmisor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder nombreReceptor(String nombreReceptor) {
            this.nombreReceptor = nombreReceptor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder nombreReceptor(Element ele) {
            try {
                this.nombreReceptor = xpath.evaluate(getXmlPath(XML_NOMBRE_RECEPTOR), ele);
            } catch (XPathExpressionException exc) {
                this.nombreReceptor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder tipoIdentificacionReceptor(String tipoIdentificacionReceptor) {
            this.tipoIdentificacionReceptor = tipoIdentificacionReceptor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder tipoIdentificacionReceptor(Element ele) {
            try {
                this.tipoIdentificacionReceptor = xpath.evaluate(getXmlPath(XML_TIPO_IDENTIFICACION_RECEPTOR), ele);
            } catch (XPathExpressionException exc) {
                this.tipoIdentificacionReceptor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder numeroCedulaReceptor(String numeroCedulaReceptor) {
            this.numeroCedulaReceptor = numeroCedulaReceptor;
            return this;
        }

        @Override
        public MensajeHacienda.Builder numeroCedulaReceptor(Element ele) {
            try {
                this.numeroCedulaReceptor = xpath.evaluate(getXmlPath(XML_NUMERO_CEDULA_RECEPTOR), ele);
            } catch (XPathExpressionException exc) {
                this.numeroCedulaReceptor = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        @Override
        public MensajeHacienda.Builder mensaje(Element ele) {
            try {
                this.mensaje = xpath.evaluate(getXmlPath(XML_MENSAJE), ele);
            } catch (XPathExpressionException exc) {
                this.mensaje = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder detalleMensaje(String detalleMensaje) {
            this.detalleMensaje = detalleMensaje;
            return this;
        }

        @Override
        public MensajeHacienda.Builder detalleMensaje(Element ele) {
            try {
                this.detalleMensaje = xpath.evaluate(getXmlPath(XML_DETALLE_MENSAJE), ele);
            } catch (XPathExpressionException exc) {
                this.detalleMensaje = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder montoTotalImpuesto(Double montoTotalImpuesto) {
            this.montoTotalImpuesto = montoTotalImpuesto;
            return this;
        }

        @Override
        public MensajeHacienda.Builder montoTotalImpuesto(Element ele) {
            try {
                String value = xpath.evaluate(getXmlPath(XML_MONTO_TOTAL_IMPUESTO), ele);
                this.montoTotalImpuesto = (value != null && !value.equals("")) ? Double.parseDouble(value) : 0;
            } catch (XPathExpressionException exc) {
                this.montoTotalImpuesto = null;
            }
            return this;
        }

        @Override
        public MensajeHacienda.Builder totalFactura(Double totalFactura) {
            this.totalFactura = totalFactura;
            return this;
        }

        @Override
        public MensajeHacienda.Builder totalFactura(Element ele) {
            try {
                String value = xpath.evaluate(getXmlPath(XML_TOTAL_FACTURA), ele);
                this.totalFactura = (value != null && !value.equals("")) ? Double.parseDouble(value) : 0;
            } catch (XPathExpressionException exc) {
                this.totalFactura = null;
            }
            return this;
        }

        private String getXmlPath (String key) {
            return "/" + XML_MENSAJE_HACIENDA + "/" + key;
        }

        @Override
        public MensajeHacienda build() {
            return MensajeHaciendaXml.instance(this.clave, this.nombreEmisor, this.tipoIdentificacionEmisor,
                    this.numeroCedulaEmisor, this.nombreReceptor, this.tipoIdentificacionReceptor,
                    this.numeroCedulaReceptor, this.mensaje, this.detalleMensaje, this.montoTotalImpuesto,
                    this.totalFactura);
        }
    }

    @Override
    public String toString() {
        return "{"
                + jsonElement(XML_CLAVE, true) + jsonElement(clave, false) + ","
                + jsonElement(XML_NOMBRE_EMISOR, true) + jsonElement(nombreEmisor, false) + ","
                + jsonElement(XML_TIPO_IDENTIFICACION_EMISOR, true) + jsonElement(tipoIdentificacionEmisor, false) + ","
                + jsonElement(XML_NUMERO_CEDULA_EMISOR, true) + jsonElement(numeroCedulaEmisor, false) + ","
                + jsonElement(XML_NOMBRE_RECEPTOR, true) + jsonElement(nombreReceptor, false) + ","
                + jsonElement(XML_TIPO_IDENTIFICACION_RECEPTOR, true) + jsonElement(tipoIdentificacionReceptor, false) + ","
                + jsonElement(XML_NUMERO_CEDULA_RECEPTOR, true) + jsonElement(numeroCedulaReceptor, false) + ","
                + jsonElement(XML_MENSAJE, true) + jsonElement(mensaje, false) + ","
                + jsonElement(XML_DETALLE_MENSAJE, true) + jsonElement(detalleMensaje, false) + ","
                + jsonElement(XML_MONTO_TOTAL_IMPUESTO, true) + montoTotalImpuesto + ","
                + jsonElement(XML_TOTAL_FACTURA, true) + totalFactura
                + "}";
    }
}
