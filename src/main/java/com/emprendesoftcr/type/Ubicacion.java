package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Ubicacion {

    String getProvincia();

    String getCanton();

    String getDistrito();

    String getBarrio();

    String getOtrasSenas();

    @Override
    String toString();

    static interface Builder {
        Builder provincia(String provincia);

        Builder provincia(JSONObject jsonObject);

        Builder canton(String canton);

        Builder canton(JSONObject jsonObject);

        Builder distrito(String distrito);

        Builder distrito(JSONObject jsonObject);

        Builder barrio(String barrio);

        Builder barrio(JSONObject jsonObject);

        Builder otrasSenas(String otrasSenas);

        Builder otrasSenas(JSONObject jsonObject);

        Ubicacion build();
    }
}
