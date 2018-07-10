package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface ResumenFactura {

    String getCodigoMoneda();

    Double getTipoCambio();

    Double getTotalServGravados();

    Double getTotalServExentos();

    Double getTotalMercanciasGravadas();

    Double getTotalMercanciasExentas();

    Double getTotalGravado();

    Double getTotalExento();

    Double getTotalVenta();

    Double getTotalDescuentos();

    Double getTotalVentaNeta();

    Double getTotalImpuesto();

    Double getTotalComprobante();

    @Override
    String toString();

    static interface Builder {

        Builder codigoMoneda(String codigoMoneda);

        Builder codigoMoneda(JSONObject jsonObject);

        Builder tipoCambio(Double tipoCambio);

        Builder tipoCambio(JSONObject jsonObject);

        Builder totalServGravados(Double totalServGravados);

        Builder totalServGravados(JSONObject jsonObject);

        Builder totalServExentos(Double totalServExentos);

        Builder totalServExentos(JSONObject jsonObject);

        Builder totalMercanciasGravadas(Double totalMercanciasGravadas);

        Builder totalMercanciasGravadas(JSONObject jsonObject);

        Builder totalMercanciasExentas(Double totalMercanciasExentas);

        Builder totalMercanciasExentas(JSONObject jsonObject);

        Builder totalGravado(Double totalGravado);

        Builder totalGravado(JSONObject jsonObject);

        Builder totalExento(Double totalExento);

        Builder totalExento(JSONObject jsonObject);

        Builder totalVenta(Double totalVenta);

        Builder totalVenta(JSONObject jsonObject);

        Builder totalDescuentos(Double totalDescuentos);

        Builder totalDescuentos(JSONObject jsonObject);

        Builder totalVentaNeta(Double totalVentaNeta);

        Builder totalVentaNeta(JSONObject jsonObject);

        Builder totalImpuesto(Double totalImpuesto);

        Builder totalImpuesto(JSONObject jsonObject);

        Builder totalComprobante(Double totalComprobante);

        Builder totalComprobante(JSONObject jsonObject);

        ResumenFactura build();
    }
}
