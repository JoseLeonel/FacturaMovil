package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Exoneracion {

    String getTipoDocumento();

    String getNumeroDocumento();

    String getNombreInstitucion();

    String getFechaEmision();

    Double getMontoImpuesto();

    Integer getPorcentajeCompra();

    @Override
    String toString();

    static interface Builder {
        Builder tipoDocumento(String tipoDocumento);

        Builder tipoDocumento(JSONObject jsonObject);

        Builder numeroDocumento(String numeroDocumento);

        Builder numeroDocumento(JSONObject jsonObject);

        Builder nombreInstitucion(String nombreInstitucion);

        Builder nombreInstitucion(JSONObject jsonObject);

        Builder fechaEmision(String fechaEmision);

        Builder fechaEmision(JSONObject jsonObject);

        Builder montoImpuesto(Double montoImpuesto);

        Builder montoImpuesto(JSONObject jsonObject);

        Builder porcentajeCompra(Integer porcentajeCompra);

        Builder porcentajeCompra(JSONObject jsonObject);

        Exoneracion build();
    }
}
