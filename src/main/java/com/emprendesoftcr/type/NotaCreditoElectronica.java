package com.emprendesoftcr.type;

import org.json.JSONObject;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public interface NotaCreditoElectronica {

    String getClave();

    String getNumeroConsecutivo();

    String getFechaEmision();

    Emisor getEmisor();

    Receptor getReceptor();

    String getCondicionVenta();

    String getPlazoCredito();

    String getMedioPago();

    Iterable<Linea> getDetalle();

    ResumenFactura getResumenFactura();

    InformacionReferencia getInformacionReferencia();

    Normativa getNormativa();

    Otros getOtros();

    @Override
    String toString();

    static interface Builder {

        public Builder clave(String clave);

        public Builder clave(JSONObject jsonObject);

        public Builder numeroConsecutivo(String numeroConsecutivo);

        public Builder numeroConsecutivo(JSONObject jsonObject);

        public Builder fechaEmision(String fechaEmision);

        public Builder fechaEmision(JSONObject jsonObject);

        public Builder emisor(Emisor emisor);

        public Builder emisor(JSONObject jsonObject);

        public Builder receptor(Receptor receptor);

        public Builder receptor(JSONObject jsonObject);

        public Builder condicionVenta(String condicionVenta);

        public Builder condicionVenta(JSONObject jsonObject);

        public Builder plazoCredito(String plazoCredito);

        public Builder plazoCredito(JSONObject jsonObject);

        public Builder medioPago(String medioPago);

        public Builder medioPago(JSONObject jsonObject);

        public Builder detalle(Iterable<Linea> detalle);

        public Builder detalle(JSONObject jsonObject);

        public Builder resumenFactura(ResumenFactura resumenFactura);

        public Builder resumenFactura(JSONObject jsonObject);

        public Builder informacionReferencia(InformacionReferencia informacionReferencia);

        public Builder informacionReferencia(JSONObject jsonObject);

        public Builder normativa(Normativa normativa);

        public Builder normativa(JSONObject jsonObject);

        public Builder otros(Otros otros);

        public Builder otros(JSONObject jsonObject);

        public NotaCreditoElectronica build();
    }
}
