package com.emprendesoftcr.fisco;


import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.type.FacturaElectronicaPrueba;
import com.emprendesoftcr.type.Impuesto;
import com.emprendesoftcr.type.Linea;
import com.emprendesoftcr.type.Receptor;
import com.google.common.collect.ImmutableList;


public class XMLFacturaElectronica {
	
	
    
    private XMLFacturaElectronica() {
        
    }
    
    public static XMLFacturaElectronica instance () {
        return new XMLFacturaElectronica();
    }
    
    public String build ( FacturaElectronicaPrueba factura) {
        return generateXMLHacienda(factura);
    }
    
    private String generateXMLHacienda(FacturaElectronicaPrueba factura) {
        String xml = "<FacturaElectronica xmlns=\"" + Constantes.DOCXMLS + "\" " +
                    "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
            "<Clave>" + factura.getClave() + "</Clave>" +
            "<NumeroConsecutivo>" + factura.getNumeroConsecutivo() + "</NumeroConsecutivo>" +
            "<FechaEmision>" + factura.getFechaEmision() + "</FechaEmision>" +
            "<Emisor>" +
                "<Nombre>" + factura.getEmisor().getNombre() + "</Nombre>" +
                "<Identificacion>" +
                    "<Tipo>" + factura.getEmisor().getIdentificacion().getTipo() + "</Tipo>" +
                    "<Numero>" + factura.getEmisor().getIdentificacion().getNumero() + "</Numero>" +
                "</Identificacion>" +
                "<Ubicacion>" +
                    "<Provincia>" + factura.getEmisor().getUbicacion().getProvincia() + "</Provincia>" +
                    "<Canton>" + factura.getEmisor().getUbicacion().getCanton() + "</Canton>" +
                    "<Distrito>" + factura.getEmisor().getUbicacion().getDistrito() + "</Distrito>" +
                    "<Barrio>" + factura.getEmisor().getUbicacion().getBarrio() + "</Barrio>" +
                    "<OtrasSenas>" + factura.getEmisor().getUbicacion().getOtrasSenas() + "</OtrasSenas>" +
                "</Ubicacion>" +
                "<CorreoElectronico>" + factura.getEmisor().getCorreoElectronico() + "</CorreoElectronico>" +
            "</Emisor>" +
            xmlReceptor(factura) +
                "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
                "<MedioPago>" + factura.getMedioPago() + "</MedioPago>" +
                (factura.getCondicionVenta().equals("02") ? "<PlazoCredito>" + factura.getPlazoCredito() + "</PlazoCredito>" : "") +
                "<DetalleServicio>" + xmlDetalleServicio(factura) + "</DetalleServicio>" +
                "<ResumenFactura>" +
                    "<CodigoMoneda>" + factura.getResumenFactura().getCodigoMoneda() + "</CodigoMoneda>" +
                    "<TipoCambio>" + factura.getResumenFactura().getTipoCambio() + "</TipoCambio>" +
                    "<TotalServGravados>" + factura.getResumenFactura().getTotalServGravados() + "</TotalServGravados>" +
                    "<TotalServExentos>" + factura.getResumenFactura().getTotalServExentos() + "</TotalServExentos>" +
                    "<TotalMercanciasGravadas>" + factura.getResumenFactura().getTotalMercanciasGravadas() + "</TotalMercanciasGravadas>" +
                    "<TotalMercanciasExentas>" + factura.getResumenFactura().getTotalMercanciasExentas() + "</TotalMercanciasExentas>" +
                    "<TotalGravado>" + factura.getResumenFactura().getTotalGravado() + "</TotalGravado>" +
                    "<TotalExento>" + factura.getResumenFactura().getTotalExento() + "</TotalExento>" +
                    "<TotalVenta>" + factura.getResumenFactura().getTotalVenta() + "</TotalVenta>" +
                    "<TotalDescuentos>" + factura.getResumenFactura().getTotalDescuentos() + "</TotalDescuentos>" +
                    "<TotalVentaNeta>" + factura.getResumenFactura().getTotalVentaNeta() + "</TotalVentaNeta>" +
                    "<TotalImpuesto>" + factura.getResumenFactura().getTotalImpuesto() + "</TotalImpuesto>" +
                    "<TotalComprobante>" + factura.getResumenFactura().getTotalComprobante() + "</TotalComprobante>" +
                "</ResumenFactura>" +
                "<Normativa>" +
                    "<NumeroResolucion>" + factura.getNormativa().getNumeroResolucion() + "</NumeroResolucion>" +
                    "<FechaResolucion>" + factura.getNormativa().getFechaResolucion() + "</FechaResolucion>" +
                "</Normativa>" +
            "</FacturaElectronica>";
        return xml;
    }
    
    private String xmlDetalleServicio(FacturaElectronicaPrueba factura) {
        ImmutableList<Linea> invoiceDetail = ImmutableList.copyOf(factura.getDetalle());
        String lineas = "";
        lineas = invoiceDetail.stream().map((line) -> "<LineaDetalle>" +
                "<NumeroLinea>" + line.getNumeroLinea() + "</NumeroLinea>" +
                "<Codigo>" +
                "<Tipo>" + line.getCodigo().getTipo() + "</Tipo>" +
                "<Codigo>" + line.getCodigo().getCodigo() + "</Codigo>" +
                "</Codigo>" +
                "<Cantidad>" + line.getCantidad() + "</Cantidad>" +
                "<UnidadMedida>" + line.getUnidadMedida() + "</UnidadMedida>" +
                "<Detalle>" + line.getDetalle() + "</Detalle>" +
                "<PrecioUnitario>" + line.getPrecioUnitario() + "</PrecioUnitario>" +
                "<MontoTotal>" + line.getMontoTotal() + "</MontoTotal>" +
                "<MontoDescuento>" + line.getMontoDescuento() + "</MontoDescuento>" +
                "<NaturalezaDescuento>" + line.getNaturalezaDescuento() + "</NaturalezaDescuento>" +
                "<SubTotal>" + line.getSubTotal() + "</SubTotal>" +
                xmlImpuestos(line.getImpuesto()) +
                "<MontoTotalLinea>" + line.getMontoTotalLinea() + "</MontoTotalLinea>" +
                "</LineaDetalle>").reduce(lineas, String::concat);
        
        return lineas;
    }
    
    private String xmlReceptor(FacturaElectronicaPrueba factura) {
        if (factura.getReceptor() != null) {
            return "<Receptor>" +
                    "<Nombre>" + factura.getReceptor().getNombre() + "</Nombre>" +
                    xmlIdentificacion(factura.getReceptor()) +
                    "<Ubicacion>" +
                        "<Provincia>" + factura.getReceptor().getUbicacion().getProvincia() + "</Provincia>" +
                        "<Canton>" + factura.getReceptor().getUbicacion().getCanton() + "</Canton>" +
                        "<Distrito>" + factura.getReceptor().getUbicacion().getDistrito() + "</Distrito>" +
                        "<Barrio>" + factura.getReceptor().getUbicacion().getBarrio() + "</Barrio>" +
                        "<OtrasSenas>" + factura.getReceptor().getUbicacion().getOtrasSenas() + "</OtrasSenas>" +
                    "</Ubicacion>" +
                    "<CorreoElectronico>" + factura.getReceptor().getCorreoElectronico() + "</CorreoElectronico>" +
                "</Receptor>";
        } else {
            return "";
        }
    }

    private String xmlIdentificacion (Receptor receptor) {
        if (receptor.getIdentificacion() != null) {
            return "<Identificacion>" +
                        "<Tipo>" + receptor.getIdentificacion().getTipo() + "</Tipo>" +
                        "<Numero>" + receptor.getIdentificacion().getNumero() + "</Numero>" +
                    "</Identificacion>";
        } else {
            return "";
        }
    }
    
    private String xmlImpuestos(Impuesto impuesto) {
        String imp = "<Impuesto>" +
            "<Codigo>" + Utils.zeroPad(impuesto.getCodigo(), 2) + "</Codigo>" +
            "<Tarifa>" + impuesto.getTarifa() + "</Tarifa>" +
            "<Monto>" + impuesto.getMonto() + "</Monto>";
        if(impuesto.getExoneracion() != null) {
            imp += "<Exoneracion>" +
                "<TipoDocumento>" + impuesto.getExoneracion().getTipoDocumento() + "</TipoDocumento>" +
                "<NumeroDocumento>" + impuesto.getExoneracion().getNumeroDocumento() + "</NumeroDocumento>" +
                "<NombreInstitucion>" + impuesto.getExoneracion().getNombreInstitucion() + "</NombreInstitucion>" +
                "<FechaEmision>" + impuesto.getExoneracion().getFechaEmision() + "</FechaEmision>" +
                "<MontoImpuesto>" + impuesto.getExoneracion().getMontoImpuesto() + "</MontoImpuesto>" +
                "<PorcentajeCompra>" + impuesto.getExoneracion().getPorcentajeCompra() + "</PorcentajeCompra>" +
            "</Exoneracion>";
        }
        imp += "</Impuesto>";
        return imp;
    }
}
