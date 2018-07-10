package com.emprendesoftcr.type;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Clave {

    String codigoPais();

    String dia();

    String mes();

    String anio();

    String numeroIdentificacion();

    Consecutivo consecutivo();

    String strConsecutivo();

    String situacion();

    String codigoSeguridad();

    @Override
    String toString();
}
