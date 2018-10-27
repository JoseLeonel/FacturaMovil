package com.emprendesoftcr.type;

import org.w3c.dom.Element;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface MensajeHacienda {

    String clave();

    String nombreEmisor();

    String tipoIdentificacionEmisor();

    String numeroCedulaEmisor();

    String nombreReceptor();

    String tipoIdentificacionReceptor();

    String numeroCedulaReceptor();

    String mensaje();

    String detalleMensaje();

    Double montoTotalImpuesto();

    Double totalFactura();

    @Override
    String toString();

    static interface Builder {

        public Builder clave(String clave);

        public Builder clave(Element jsonObject);

        public Builder nombreEmisor(String nombreEmisor);

        public Builder nombreEmisor(Element jsonObject);

        public Builder tipoIdentificacionEmisor(String tipoIdentificacionEmisor);

        public Builder tipoIdentificacionEmisor(Element jsonObject);

        public Builder numeroCedulaEmisor(String numeroCedulaEmisor);

        public Builder numeroCedulaEmisor(Element jsonObject);

        public Builder nombreReceptor(String nombreReceptor);

        public Builder nombreReceptor(Element jsonObject);

        public Builder tipoIdentificacionReceptor(String tipoIdentificacionReceptor);

        public Builder tipoIdentificacionReceptor(Element jsonObject);

        public Builder numeroCedulaReceptor(String numeroCedulaReceptor);

        public Builder numeroCedulaReceptor(Element jsonObject);

        public Builder mensaje(String mensaje);

        public Builder mensaje(Element jsonObject);

        public Builder detalleMensaje(String detalleMensaje);

        public Builder detalleMensaje(Element jsonObject);

        public Builder montoTotalImpuesto(Double montoTotalImpuesto);

        public Builder montoTotalImpuesto(Element jsonObject);

        public Builder totalFactura(Double totalFactura);

        public Builder totalFactura(Element jsonObject);

        public MensajeHacienda build();
    }
}
