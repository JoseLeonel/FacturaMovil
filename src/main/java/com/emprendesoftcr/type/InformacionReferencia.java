package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface InformacionReferencia {

    String getTipoDoc();

    String getNumero();

    String getFechaEmision();

    String getCodigo();

    String getRazon();

    @Override
    String toString();

    static interface Builder {
        Builder tipoDoc(String tipoDoc);

        Builder tipoDoc(JSONObject jsonObject);

        Builder numero(String numero);

        Builder numero(JSONObject jsonObject);

        Builder fechaEmision(String fechaEmision);

        Builder fechaEmision(JSONObject jsonObject);

        Builder codigo(String codigo);

        Builder codigo(JSONObject jsonObject);

        Builder razon(String razon);

        Builder razon(JSONObject jsonObject);

        InformacionReferencia build();
    }
}
