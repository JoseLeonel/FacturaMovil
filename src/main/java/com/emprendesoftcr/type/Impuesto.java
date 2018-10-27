package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Impuesto {

    String getCodigo();

    Double getTarifa();

    Double getMonto();

    Exoneracion getExoneracion();

    @Override
    String toString();

    static interface Builder {
        Builder codigo(String codigo);

        Builder codigo(JSONObject jsonObject);

        Builder monto(Double monto);

        Builder monto(JSONObject jsonObject);

        Builder tarifa(Double tarifa);

        Builder tarifa(JSONObject jsonObject);

        Builder exoneracion(Exoneracion exoneracion);

        Builder exoneracion(JSONObject jsonObject);

        Impuesto build();
    }
}
