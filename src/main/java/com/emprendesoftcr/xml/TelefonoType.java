//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.06.09 a las 05:40:57 PM CST 
//


package com.emprendesoftcr.xml;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TelefonoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TelefonoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoPais">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NumTelefono">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelefonoType", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", propOrder = {
    "codigoPais",
    "numTelefono"
})
public class TelefonoType {

    @XmlElement(name = "CodigoPais", required = true)
    protected BigInteger codigoPais;
    @XmlElement(name = "NumTelefono", required = true)
    protected BigInteger numTelefono;

    /**
     * Obtiene el valor de la propiedad codigoPais.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodigoPais() {
        return codigoPais;
    }

    /**
     * Define el valor de la propiedad codigoPais.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodigoPais(BigInteger value) {
        this.codigoPais = value;
    }

    /**
     * Obtiene el valor de la propiedad numTelefono.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumTelefono() {
        return numTelefono;
    }

    /**
     * Define el valor de la propiedad numTelefono.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumTelefono(BigInteger value) {
        this.numTelefono = value;
    }

}
