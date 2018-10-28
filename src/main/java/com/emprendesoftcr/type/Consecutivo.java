package com.emprendesoftcr.type;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface Consecutivo {

    String local();

    String terminal();

    String tipoDocumento();

    String numeracion();

    @Override
    String toString();
}
