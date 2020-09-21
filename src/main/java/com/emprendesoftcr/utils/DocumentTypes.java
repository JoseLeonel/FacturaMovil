package com.emprendesoftcr.utils;

/**
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public final class DocumentTypes {

    public static final String XMLNS_FacturaElectronica = System.getenv("HACIENDA_XMLNS_FACTURA_ELECTRONICA") != null
            ? System.getenv("HACIENDA_XMLNS_FACTURA_ELECTRONICA")
            : "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica";
    public static final String XMLNS_NotaDebitoElectronica = System.getenv("HACIENDA_XMLNS_NOTA_DEBITO_ELECTRONICA") != null
            ? System.getenv("HACIENDA_XMLNS_NOTA_DEBITO_ELECTRONICA")
            : "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/notaDebitoElectronica";
    public static final String XMLNS_NotaCreditoElectronica = System.getenv("HACIENDA_XMLNS_NOTA_CREDITO_ELECTRONICA") != null
            ? System.getenv("HACIENDA_XMLNS_NOTA_CREDITO_ELECTRONICA")
            : "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/notaCreditoElectronica";
    public static final String XMLNS_TiqueteElectronico = System.getenv("HACIENDA_XMLNS_TIQUETE_ELECTRONICO") != null
            ? System.getenv("HACIENDA_XMLNS_TIQUETE_ELECTRONICO")
            : "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/tiqueteElectronico";
}
