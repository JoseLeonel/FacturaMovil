package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Telefono {

    String getCodigoPais();

    String getNumTelefono();

    @Override
    String toString();

    static interface Builder {

        Builder codigoPais(String codigoPais);

        Builder codigoPais(JSONObject jsonObject);

        Builder numTelefono(String numTelefono);

        Builder numTelefono(JSONObject jsonObject);

        Telefono build();
    }
}
