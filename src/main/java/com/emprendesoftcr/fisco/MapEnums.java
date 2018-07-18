package com.emprendesoftcr.fisco;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.OK;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class MapEnums {

    public static final Map<String, String> ENUM_TIPOS_IDENTIFICACION = ImmutableMap.of("01", "Cedula Fisica",
            "02", "Cedula Juridica",
            "03", "DIMEX",
            "04", "NITE");
    public static final Map<String, String> ENUM_CONDICION_VENTA = ImmutableMap.<String, String>builder().put("01", "Contado").
            put("02", "Crédito").
            put("03", "Consignación").
            put("04", "Apartado").
            put("05", "Arrendamiento con opción de compra").
            put("06", "Arrendamiento en función financiera").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_MEDIO_PAGO = ImmutableMap.<String, String>builder().put("01", "Efectivo").
            put("02", "Tarjeta").
            put("03", "Cheque").
            put("04", "Transferencia").
            put("05", "Recaudado por terceros").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_CODIGO = ImmutableMap.<String, String>builder().put("01", "Código del producto del vendedor").
            put("02", "Código del producto del comprador").
            put("03", "Código del producto asignado por la industria").
            put("04", "Código de uso interno").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_UNIDAD_MEDIDA = ImmutableMap.<String, String>builder().
            put("Sp", "Servicios Profesionales").put("m", "Metro").
            put("kg", "Kilogramo").put("s", "Segundo").put("A", "Ampere").put("K", "Kelvin").
            put("mol", "Mol").put("cd", "Candela").put("m²", "metro cuadrado").put("m³", "metro cúbico").
            put("m/s", "metro por segundo").put("m/s²", "metro por segundo cuadrado").put("1/m", "1 por metro").
            put("kg/m³", "kilogramo por metro cúbico").put("A/m²", "ampere por metro cuadrado").
            put("A/m", "ampere por metro").put("mol/m³", "mol por metro cúbico").put("cd/m²", "candela por metro cuadrado").
            put("1", "uno (indice de refracción)").put("rad", "radián").put("sr", "estereorradián").
            put("Hz", "hertz").put("N", "newton").put("Pa", "pascal").put("J", "Joule").put("W", "Watt").
            put("C", "coulomb").put("V", "volt").put("F", "farad").put("Ω", "ohm").put("S", "siemens").
            put("Wb", "weber").put("T", "tesla").put("H", "henry").put("°C", "grado Celsius").put("lm", "lumen").
            put("lx", "lux").put("Bq", "Becquerel").put("Gy", "gray").put("Sv", "sievert").put("kat", "katal").
            put("Pa·s", "pascal segundo").put("N·m", "newton metro").put("N/m", "newton por metro").
            put("rad/s", "radián por segundo").put("rad/s²", "radián por segundo cuadrado").
            put("W/m²", "watt por metro cuadrado").put("J/K", "joule por kelvin").
            put("J/(kg·K)", "joule por kilogramo kelvin").put("J/kg", "joule por kilogramo").
            put("W/(m·K)", "watt por metro kevin").put("J/m³", "joule por metro cúbico").
            put("V/m", "volt por metro").put("C/m³", "coulomb por metro cúbico").
            put("C/m²", "coulomb por metro cuadrado").put("F/m", "farad por metro").put("H/m", "henry por metro").
            put("J/mol", "joule por mol").put("J/(mol·K)", "joule por mol kelvin").
            put("C/kg", "coulomb por kilogramo").put("Gy/s", "gray por segundo").put("W/sr", "watt por estereorradián").
            put("W/(m²·sr)", "watt por metro cuadrado estereorradián").put("kat/m³", "katal por metro cúbico").put("min", "minuto").
            put("h", "hora").put("d", "día").put("º", "grado").put("´", "minuto").put("´´", "segundo").
            put("L", "litro").put("t", "tonelada").put("Np", "neper").put("B", "bel").put("eV", "electronvolt").
            put("u", "unidad de masa atómica unificada").put("ua", "unidad astronómica").put("Unid", "unidad").
            put("Gal", "galón").put("g", "gramo").put("Km", "kilometro").put("ln", "pulgada").
            put("cm", "centimetro").put("mL", "mililitro").put("mm", "milimetro").put("Oz", "onzas").
            put("Otros", "Otros").build();
    public static final Map<String, String> ENUM_CODIGO_MONEDA = ImmutableMap.<String, String>builder().put("AED", "").
            put("AFN", "").put("ALL", "").put("AMD", "").put("ANG", "").put("AOA", "").put("ARS", "").put("AUD", "").
            put("AWG", "").put("AZN", "").put("BAM", "").put("BBD", "").put("BDT", "").put("BGN", "").put("BHD", "").
            put("BIF", "").put("BMD", "").put("BND", "").put("BOB", "").put("BOV", "").put("BRL", "").put("BSD", "").
            put("BTN", "").put("BWP", "").put("BYR", "").put("BZD", "").put("CAD", "").put("CDF", "").put("CHE", "").
            put("CHF", "").put("CHW", "").put("CLF", "").put("CLP", "").put("CNY", "").put("COP", "").put("COU", "").
            put("CRC", "").put("CUC", "").put("CUP", "").put("CVE", "").put("CZK", "").put("DJF", "").put("DKK", "").
            put("DOP", "").put("DZD", "").put("EGP", "").put("ERN", "").put("ETB", "").put("EUR", "").put("FJD", "").
            put("FKP", "").put("GBP", "").put("GEL", "").put("GHS", "").put("GIP", "").put("GMD", "").put("GNF", "").
            put("GTQ", "").put("GYD", "").put("HKD", "").put("HNL", "").put("HRK", "").put("HTG", "").put("HUF", "").
            put("IDR", "").put("ILS", "").put("INR", "").put("IQD", "").put("IRR", "").put("ISK", "").put("JMD", "").
            put("JOD", "").put("JPY", "").put("KES", "").put("KGS", "").put("KHR", "").put("KMF", "").put("KPW", "").
            put("KRW", "").put("KWD", "").put("KYD", "").put("KZT", "").put("LAK", "").put("LBP", "").put("LKR", "").
            put("LRD", "").put("LSL", "").put("LYD", "").put("MAD", "").put("MDL", "").put("MGA", "").put("MKD", "").
            put("MMK", "").put("MNT", "").put("MOP", "").put("MRO", "").put("MUR", "").put("MVR", "").put("MWK", "").
            put("MXN", "").put("MXV", "").put("MYR", "").put("MZN", "").put("NAD", "").put("NGN", "").put("NIO", "").
            put("NOK", "").put("NPR", "").put("NZD", "").put("OMR", "").put("PAB", "").put("PEN", "").put("PGK", "").
            put("PHP", "").put("PKR", "").put("PLN", "").put("PYG", "").put("QAR", "").put("RON", "").put("RSD", "").
            put("RUB", "").put("RWF", "").put("SAR", "").put("SBD", "").put("SCR", "").put("SDG", "").put("SEK", "").
            put("SGD", "").put("SHP", "").put("SLL", "").put("SOS", "").put("SRD", "").put("SSP", "").put("STD", "").
            put("SVC", "").put("SYP", "").put("SZL", "").put("THB", "").put("TJS", "").put("TMT", "").put("TND", "").
            put("TOP", "").put("TRY", "").put("TTD", "").put("TWD", "").put("TZS", "").put("UAH", "").put("UGX", "").
            put("USD", "").put("USN", "").put("UYI", "").put("UYU", "").put("UZS", "").put("VEF", "").put("VND", "").
            put("VUV", "").put("WST", "").put("XAF", "").put("XAG", "").put("XAU", "").put("XBA", "").put("XBB", "").
            put("XBC", "").put("XBD", "").put("XCD", "").put("XDR", "").put("XOF", "").put("XPD", "").put("XPF", "").
            put("XPT", "").put("XSU", "").put("XTS", "").put("XUA", "").put("XXX", "").put("YER", "").put("ZAR", "").
            put("ZMW", "").put("ZWL", "").build();
    public static final Map<String, String> ENUM_TIPO_DOC = ImmutableMap.<String, String>builder().put("01", "Factura electrónica").
            put("02", "Nota de debido electrónica").
            put("03", "Nota de crédito electrónica").
            put("04", "Tiquete electrónico").
            put("05", "Nota de despacho").
            put("06", "Contrato").
            put("07", "Procedimiento").
            put("08", "Comprobante emitido en contigencia").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_TIPO_DOC_EXONERACION = ImmutableMap.<String, String>builder().put("01", "Compras Autorizadas").
            put("02", "Ventas exentas a diplomáticos").
            put("03", "Orden de compra (instituciones publicas y otros organismos").
            put("04", "Exenciones Direccion General de Hacienda").
            put("05", "Zonas Francas").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_CODIGO_REFERENCIA = ImmutableMap.<String, String>builder().put("01", "Anula documento de referencia").
            put("02", "Corrige texto de ocumento de referencia").
            put("03", "Corrige monto").
            put("04", "Referencia a otro documento").
            put("05", "Sustituye comprobante provisional por contigencia").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_CODIGO_IMPUESTO = ImmutableMap.<String, String>builder().put("01", "Impuesto General sobre las ventas").
            put("02", "Impuesto Selectivo de Consumo").
            put("03", "Impuesto ünico a los combustivos").
            put("04", "Impuesto especifico de bebidas alcohólicas").
            put("05", "Impuesto especifico sobre las bebidas envasadas sin contenido alcoholico y jabones de tocador").
            put("06", "Impuesto a los productos de tabaco").
            put("07", "Servicios").
            put("08", "Impuesto General a las Ventas Diplomaticos").
            put("09", "Impuesto General sobre Ventas compras autorizadas").
            put("10", "Impuesto General sobre las ventas instituciones publicas y otros organismos").
            put("11", "Impuesto Selectivo de consumo compras autorizadas").
            put("99", "Otros").build();
    public static final Map<String, String> ENUM_CODIGO_RESPUESTA_HACIENDA = ImmutableMap.<String, String>builder().put("aceptado", OK).
            put("rechazado", ERROR).build();
}
