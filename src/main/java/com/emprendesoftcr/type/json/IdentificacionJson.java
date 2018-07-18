package com.emprendesoftcr.type.json;

import static com.emprendesoftcr.fisco.Checks.*;
import static com.emprendesoftcr.fisco.FacturaElectronicaUtils.*;
import static com.emprendesoftcr.fisco.Keys.*;
import static com.emprendesoftcr.fisco.MapEnums.ENUM_TIPOS_IDENTIFICACION;

import org.json.JSONObject;

import com.emprendesoftcr.type.Identificacion;

/**
 *
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class IdentificacionJson implements Identificacion {

    private final static Builder DEFAULT_BUILDER = new Builder();
    private final String tipo;
    private final String numero;

    private IdentificacionJson(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    /**
     * Crea una instancia de tipo Identificacion
     * @param tipo Tipo de identificación, enum
     *             01=Cedula Fisica,
     *             02=Cedula Juridica,
     *             03=DIMEX,
     *             04=NITE
     * @param numero Numero de identificación, String no nulo que debe cumplir con la expresión regular \d{9,12}
     * @return Identificacion
     */
    public static IdentificacionJson instance(String tipo, String numero) {
        checkEnum(ENUM_TIPOS_IDENTIFICACION, tipo);
        checkRegex(numero, "\\d{9,12}", false, "Número de identificación");
        return new IdentificacionJson(tipo, numero);
    }

    /**
     * Retorna el tipo de identificación
     * @return String
     */
    @Override
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna el número de identificación
     * @return String
     */
    @Override
    public String getNumero() {
        return Long.toString(Long.parseLong(numero));
    }

    /**
     * Construye una identificación a partir de su representación json
     * @param json Representacion json de la identificación
     * @return Identificacion
     */
    public static Identificacion from (String json) {
        JSONObject jsonObject = new JSONObject(json);
        return DEFAULT_BUILDER.tipo(jsonObject).numero(jsonObject).build();
    }

    public static class Builder implements Identificacion.Builder {
        private String tipo;
        private String numero;

        /**
         * Añade el tipo a la identificación
         * @param tipo Tipo de identificación, enum
         *             01=Cedula Fisica,
         *             02=Cedula Juridica,
         *             03=DIMEX,
         *             04=NITE
         * @return Identificacion.Builder
         */
        @Override
        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        @Override
        public Builder tipo(JSONObject jsonObject) {
            this.tipo = getJsonObject(jsonObject,TIPO) ? jsonObject.getString(TIPO) : null;
            return this;
        }

        /**
         * Añade el número a la identificación
         * @param numero Numero de identificación, String no nulo que debe cumplir con la expresión regular \d{9,12}
         * @return Identificacion.Builder
         */
        @Override
        public Builder numero(String numero) {
            this.numero = numero;
            return this;
        }

        @Override
        public Builder numero(JSONObject jsonObject) {
            this.numero = getJsonObject(jsonObject,NUMERO) ? jsonObject.getString(NUMERO) : null;
            return this;
        }

        /**
         * Costruye la identificación
         * @return Identificacion
         */
        @Override
        public Identificacion build() {
            return IdentificacionJson.instance(this.tipo, this.numero);
        }
    }

    @Override
    public String toString() {
        return "{" 
                + jsonElement(TIPO, true) + jsonElement(tipo, false) + ","
                + jsonElement(NUMERO, true) + jsonElement(numero, false)
                + "}";
    }
}
