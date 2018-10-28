package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Receptor {

    String getNombre();

    Identificacion getIdentificacion();

    String getIdentificacionExtranjero();

    String getNombreComercial();

    Ubicacion getUbicacion();

    Telefono getTelefono();

    Telefono getFax();

    String getCorreoElectronico();

    @Override
    String toString();

    static interface Builder {

        Builder nombre(String nombre);

        Builder nombre(JSONObject jsonObject);

        Builder identificacion(Identificacion identificacion);

        Builder identificacion(JSONObject jsonObject);

        Builder identificacion(String tipo, String numero);

        Builder identificacionExtranjero(String identificacionExtranjero);

        Builder identificacionExtranjero(JSONObject jsonObject);

        Builder nombreComercial(String nombreComercial);

        Builder nombreComercial(JSONObject jsonObject);

        Builder ubicacion(Ubicacion numTelefono);

        Builder ubicacion(JSONObject jsonObject);

        Builder telefono(Telefono telefono);

        Builder telefono(String codigoPais, String numTelefono);

        Builder telefono(JSONObject jsonObject);

        Builder fax(Telefono fax);

        Builder fax(String codigoPais, String numTelefono);

        Builder fax(JSONObject jsonObject);

        Builder correoElectronico(String correoElectronico);

        Builder correoElectronico(JSONObject jsonObject);

        Receptor build();
    }
}
