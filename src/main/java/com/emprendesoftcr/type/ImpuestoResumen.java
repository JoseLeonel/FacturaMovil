package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface ImpuestoResumen {

    String getCodigo();

    Double getTarifa();

    Double getMonto();

    @Override
    String toString();

    static interface Builder {
        Builder codigo(String codigo);

        Builder codigo(JSONObject jsonObject);

        Builder monto(Double monto);

        Builder monto(JSONObject jsonObject);

        Builder tarifa(Double tarifa);

        Builder tarifa(JSONObject jsonObject);

        ImpuestoResumen build();
    }
}
