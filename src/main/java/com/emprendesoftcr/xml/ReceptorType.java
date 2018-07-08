//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.06.09 a las 05:40:57 PM CST 
//

package com.emprendesoftcr.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ReceptorType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReceptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nombre">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Identificacion" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}IdentificacionType" minOccurs="0"/>
 *         &lt;element name="IdentificacionExtranjero" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NombreComercial" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Ubicacion" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}UbicacionType" minOccurs="0"/>
 *         &lt;element name="Telefono" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}TelefonoType" minOccurs="0"/>
 *         &lt;element name="Fax" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}TelefonoType" minOccurs="0"/>
 *         &lt;element name="CorreoElectronico" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
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
@XmlType(name = "ReceptorType", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", propOrder = {
    "nombre",
    "identificacion",
    "identificacionExtranjero",
    "nombreComercial",
    "ubicacion",
    "telefono",
    "fax",
    "correoElectronico"
})
public class ReceptorType {

    @XmlElement(name = "Nombre", required = true)
    protected String nombre;
    @XmlElement(name = "Identificacion")
    protected IdentificacionType identificacion;
    @XmlElement(name = "IdentificacionExtranjero")
    protected String identificacionExtranjero;
    @XmlElement(name = "NombreComercial")
    protected String nombreComercial;
    @XmlElement(name = "Ubicacion")
    protected UbicacionType ubicacion;
    @XmlElement(name = "Telefono")
    protected TelefonoType telefono;
    @XmlElement(name = "Fax")
    protected TelefonoType fax;
    @XmlElement(name = "CorreoElectronico")
    protected String correoElectronico;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad identificacion.
     * 
     * @return
     *     possible object is
     *     {@link IdentificacionType }
     *     
     */
    public IdentificacionType getIdentificacion() {
        return identificacion;
    }

    /**
     * Define el valor de la propiedad identificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificacionType }
     *     
     */
    public void setIdentificacion(IdentificacionType value) {
        this.identificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad identificacionExtranjero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificacionExtranjero() {
        return identificacionExtranjero;
    }

    /**
     * Define el valor de la propiedad identificacionExtranjero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificacionExtranjero(String value) {
        this.identificacionExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreComercial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Define el valor de la propiedad nombreComercial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComercial(String value) {
        this.nombreComercial = value;
    }

    /**
     * Obtiene el valor de la propiedad ubicacion.
     * 
     * @return
     *     possible object is
     *     {@link UbicacionType }
     *     
     */
    public UbicacionType getUbicacion() {
        return ubicacion;
    }

    /**
     * Define el valor de la propiedad ubicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link UbicacionType }
     *     
     */
    public void setUbicacion(UbicacionType value) {
        this.ubicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link TelefonoType }
     *     
     */
    public TelefonoType getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link TelefonoType }
     *     
     */
    public void setTelefono(TelefonoType value) {
        this.telefono = value;
    }

    /**
     * Obtiene el valor de la propiedad fax.
     * 
     * @return
     *     possible object is
     *     {@link TelefonoType }
     *     
     */
    public TelefonoType getFax() {
        return fax;
    }

    /**
     * Define el valor de la propiedad fax.
     * 
     * @param value
     *     allowed object is
     *     {@link TelefonoType }
     *     
     */
    public void setFax(TelefonoType value) {
        this.fax = value;
    }

    /**
     * Obtiene el valor de la propiedad correoElectronico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Define el valor de la propiedad correoElectronico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

}
