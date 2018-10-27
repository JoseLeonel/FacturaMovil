package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Linea {

    Integer getNumeroLinea();

    Codigo getCodigo();

    Double getCantidad();

    String getUnidadMedida();

    String getUnidadMedidaComercial();

    String getDetalle();

    Double getPrecioUnitario();

    Double getMontoTotal();

    Double getMontoDescuento();

    String getNaturalezaDescuento();

    Double getSubTotal();

    Impuesto getImpuesto();

    Double getMontoTotalLinea();

    @Override
    String toString();

    static interface Builder {
        Builder numeroLinea(Integer numeroLinea);

        Builder numeroLinea(JSONObject jsonObject);

        Builder codigo(Codigo codigo);

        Builder codigo(JSONObject jsonObject);

        Builder cantidad(Double cantidad);

        Builder cantidad(JSONObject jsonObject);

        Builder unidadMedida(String unidadMedida);

        Builder unidadMedida(JSONObject jsonObject);

        Builder unidadMedidaComercial(String unidadMedidaComercial);

        Builder unidadMedidaComercial(JSONObject jsonObject);

        Builder detalle(String detalle);

        Builder detalle(JSONObject jsonObject);

        Builder precioUnitario(Double precioUnitario);

        Builder precioUnitario(JSONObject jsonObject);

        Builder montoTotal(Double montoTotal);

        Builder montoTotal(JSONObject jsonObject);

        Builder montoDescuento(Double montoDescuento);

        Builder montoDescuento(JSONObject jsonObject);

        Builder naturalezaDescuento(String naturalezaDescuento);

        Builder naturalezaDescuento(JSONObject jsonObject);

        Builder subTotal(Double subTotal);

        Builder subTotal(JSONObject jsonObject);

        Builder impuesto(Impuesto impuesto);

        Builder impuesto(JSONObject jsonObject);

        Builder montoTotalLinea(Double montoTotalLinea);

        Builder montoTotalLinea(JSONObject jsonObject);

        Linea build();
    }
}
