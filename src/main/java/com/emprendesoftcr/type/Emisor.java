package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Emisor {

    String getNombre();

    Identificacion getIdentificacion();

    String getNombreComercial();

    Ubicacion getUbicacion();

    String getCorreoElectronico();

    Telefono getTelefono();

    Telefono getFax();

    static interface Builder {

        Builder nombre(String nombre);

        Builder nombre(JSONObject jsonObject);

        Builder identificacion(Identificacion identificacion);

        Builder identificacion(String tipo, String numero);

        Builder identificacion(JSONObject jsonObject);

        Builder nombreComercial(String nombreComercial);

        Builder nombreComercial(JSONObject jsonObject);

        Builder ubicacion(Ubicacion ubicacion);

        Builder ubicacion(String provincia, String canton, String distrito, String barrio, String otrasSenas);

        Builder ubicacion(JSONObject jsonObject);

        Builder telefono(Telefono telefono);

        Builder telefono(String codigoPais, String numTelefono);

        Builder telefono(JSONObject jsonObject);

        Builder fax(Telefono fax);

        Builder fax(String codigoPais, String numTelefono);

        Builder fax(JSONObject jsonObject);

        Builder correoElectronico(String correoElectronico);

        Builder correoElectronico(JSONObject jsonObject);

        Emisor build();
    }

    @Override
    String toString();
}
