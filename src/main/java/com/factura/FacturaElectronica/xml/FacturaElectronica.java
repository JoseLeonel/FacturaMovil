//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.06.09 a las 05:40:57 PM CST 
//

package com.factura.FacturaElectronica.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Clave" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}ClaveType"/>
 *         &lt;element name="NumeroConsecutivo" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}NumeroConsecutivoType"/>
 *         &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Emisor" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}EmisorType"/>
 *         &lt;element name="Receptor" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}ReceptorType" minOccurs="0"/>
 *         &lt;element name="CondicionVenta">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="01"/>
 *               &lt;enumeration value="02"/>
 *               &lt;enumeration value="03"/>
 *               &lt;enumeration value="04"/>
 *               &lt;enumeration value="05"/>
 *               &lt;enumeration value="06"/>
 *               &lt;enumeration value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PlazoCredito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MedioPago" maxOccurs="4">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="01"/>
 *               &lt;enumeration value="02"/>
 *               &lt;enumeration value="03"/>
 *               &lt;enumeration value="04"/>
 *               &lt;enumeration value="05"/>
 *               &lt;enumeration value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DetalleServicio">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LineaDetalle" maxOccurs="1000">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="NumeroLinea" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *                             &lt;element name="Codigo" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}CodigoType" maxOccurs="5" minOccurs="0"/>
 *                             &lt;element name="Cantidad">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="16"/>
 *                                   &lt;fractionDigits value="3"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="UnidadMedida" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}UnidadMedidaType"/>
 *                             &lt;element name="UnidadMedidaComercial" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="20"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="Detalle">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="160"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="PrecioUnitario" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                             &lt;element name="MontoTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                             &lt;element name="MontoDescuento" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                             &lt;element name="NaturalezaDescuento" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;maxLength value="80"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SubTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                             &lt;element name="Impuesto" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}ImpuestoType" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;element name="MontoTotalLinea" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ResumenFactura">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CodigoMoneda" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="AED"/>
 *                         &lt;enumeration value="AFN"/>
 *                         &lt;enumeration value="ALL"/>
 *                         &lt;enumeration value="AMD"/>
 *                         &lt;enumeration value="ANG"/>
 *                         &lt;enumeration value="AOA"/>
 *                         &lt;enumeration value="ARS"/>
 *                         &lt;enumeration value="AUD"/>
 *                         &lt;enumeration value="AWG"/>
 *                         &lt;enumeration value="AZN"/>
 *                         &lt;enumeration value="BAM"/>
 *                         &lt;enumeration value="BBD"/>
 *                         &lt;enumeration value="BDT"/>
 *                         &lt;enumeration value="BGN"/>
 *                         &lt;enumeration value="BHD"/>
 *                         &lt;enumeration value="BIF"/>
 *                         &lt;enumeration value="BMD"/>
 *                         &lt;enumeration value="BND"/>
 *                         &lt;enumeration value="BOB"/>
 *                         &lt;enumeration value="BOV"/>
 *                         &lt;enumeration value="BRL"/>
 *                         &lt;enumeration value="BSD"/>
 *                         &lt;enumeration value="BTN"/>
 *                         &lt;enumeration value="BWP"/>
 *                         &lt;enumeration value="BYR"/>
 *                         &lt;enumeration value="BZD"/>
 *                         &lt;enumeration value="CAD"/>
 *                         &lt;enumeration value="CDF"/>
 *                         &lt;enumeration value="CHE"/>
 *                         &lt;enumeration value="CHF"/>
 *                         &lt;enumeration value="CHW"/>
 *                         &lt;enumeration value="CLF"/>
 *                         &lt;enumeration value="CLP"/>
 *                         &lt;enumeration value="CNY"/>
 *                         &lt;enumeration value="COP"/>
 *                         &lt;enumeration value="COU"/>
 *                         &lt;enumeration value="CRC"/>
 *                         &lt;enumeration value="CUC"/>
 *                         &lt;enumeration value="CUP"/>
 *                         &lt;enumeration value="CVE"/>
 *                         &lt;enumeration value="CZK"/>
 *                         &lt;enumeration value="DJF"/>
 *                         &lt;enumeration value="DKK"/>
 *                         &lt;enumeration value="DOP"/>
 *                         &lt;enumeration value="DZD"/>
 *                         &lt;enumeration value="EGP"/>
 *                         &lt;enumeration value="ERN"/>
 *                         &lt;enumeration value="ETB"/>
 *                         &lt;enumeration value="EUR"/>
 *                         &lt;enumeration value="FJD"/>
 *                         &lt;enumeration value="FKP"/>
 *                         &lt;enumeration value="GBP"/>
 *                         &lt;enumeration value="GEL"/>
 *                         &lt;enumeration value="GHS"/>
 *                         &lt;enumeration value="GIP"/>
 *                         &lt;enumeration value="GMD"/>
 *                         &lt;enumeration value="GNF"/>
 *                         &lt;enumeration value="GTQ"/>
 *                         &lt;enumeration value="GYD"/>
 *                         &lt;enumeration value="HKD"/>
 *                         &lt;enumeration value="HNL"/>
 *                         &lt;enumeration value="HRK"/>
 *                         &lt;enumeration value="HTG"/>
 *                         &lt;enumeration value="HUF"/>
 *                         &lt;enumeration value="IDR"/>
 *                         &lt;enumeration value="ILS"/>
 *                         &lt;enumeration value="INR"/>
 *                         &lt;enumeration value="IQD"/>
 *                         &lt;enumeration value="IRR"/>
 *                         &lt;enumeration value="ISK"/>
 *                         &lt;enumeration value="JMD"/>
 *                         &lt;enumeration value="JOD"/>
 *                         &lt;enumeration value="JPY"/>
 *                         &lt;enumeration value="KES"/>
 *                         &lt;enumeration value="KGS"/>
 *                         &lt;enumeration value="KHR"/>
 *                         &lt;enumeration value="KMF"/>
 *                         &lt;enumeration value="KPW"/>
 *                         &lt;enumeration value="KRW"/>
 *                         &lt;enumeration value="KWD"/>
 *                         &lt;enumeration value="KYD"/>
 *                         &lt;enumeration value="KZT"/>
 *                         &lt;enumeration value="LAK"/>
 *                         &lt;enumeration value="LBP"/>
 *                         &lt;enumeration value="LKR"/>
 *                         &lt;enumeration value="LRD"/>
 *                         &lt;enumeration value="LSL"/>
 *                         &lt;enumeration value="LYD"/>
 *                         &lt;enumeration value="MAD"/>
 *                         &lt;enumeration value="MDL"/>
 *                         &lt;enumeration value="MGA"/>
 *                         &lt;enumeration value="MKD"/>
 *                         &lt;enumeration value="MMK"/>
 *                         &lt;enumeration value="MNT"/>
 *                         &lt;enumeration value="MOP"/>
 *                         &lt;enumeration value="MRO"/>
 *                         &lt;enumeration value="MUR"/>
 *                         &lt;enumeration value="MVR"/>
 *                         &lt;enumeration value="MWK"/>
 *                         &lt;enumeration value="MXN"/>
 *                         &lt;enumeration value="MXV"/>
 *                         &lt;enumeration value="MYR"/>
 *                         &lt;enumeration value="MZN"/>
 *                         &lt;enumeration value="NAD"/>
 *                         &lt;enumeration value="NGN"/>
 *                         &lt;enumeration value="NIO"/>
 *                         &lt;enumeration value="NOK"/>
 *                         &lt;enumeration value="NPR"/>
 *                         &lt;enumeration value="NZD"/>
 *                         &lt;enumeration value="OMR"/>
 *                         &lt;enumeration value="PAB"/>
 *                         &lt;enumeration value="PEN"/>
 *                         &lt;enumeration value="PGK"/>
 *                         &lt;enumeration value="PHP"/>
 *                         &lt;enumeration value="PKR"/>
 *                         &lt;enumeration value="PLN"/>
 *                         &lt;enumeration value="PYG"/>
 *                         &lt;enumeration value="QAR"/>
 *                         &lt;enumeration value="RON"/>
 *                         &lt;enumeration value="RSD"/>
 *                         &lt;enumeration value="RUB"/>
 *                         &lt;enumeration value="RWF"/>
 *                         &lt;enumeration value="SAR"/>
 *                         &lt;enumeration value="SBD"/>
 *                         &lt;enumeration value="SCR"/>
 *                         &lt;enumeration value="SDG"/>
 *                         &lt;enumeration value="SEK"/>
 *                         &lt;enumeration value="SGD"/>
 *                         &lt;enumeration value="SHP"/>
 *                         &lt;enumeration value="SLL"/>
 *                         &lt;enumeration value="SOS"/>
 *                         &lt;enumeration value="SRD"/>
 *                         &lt;enumeration value="SSP"/>
 *                         &lt;enumeration value="STD"/>
 *                         &lt;enumeration value="SVC"/>
 *                         &lt;enumeration value="SYP"/>
 *                         &lt;enumeration value="SZL"/>
 *                         &lt;enumeration value="THB"/>
 *                         &lt;enumeration value="TJS"/>
 *                         &lt;enumeration value="TMT"/>
 *                         &lt;enumeration value="TND"/>
 *                         &lt;enumeration value="TOP"/>
 *                         &lt;enumeration value="TRY"/>
 *                         &lt;enumeration value="TTD"/>
 *                         &lt;enumeration value="TWD"/>
 *                         &lt;enumeration value="TZS"/>
 *                         &lt;enumeration value="UAH"/>
 *                         &lt;enumeration value="UGX"/>
 *                         &lt;enumeration value="USD"/>
 *                         &lt;enumeration value="USN"/>
 *                         &lt;enumeration value="UYI"/>
 *                         &lt;enumeration value="UYU"/>
 *                         &lt;enumeration value="UZS"/>
 *                         &lt;enumeration value="VEF"/>
 *                         &lt;enumeration value="VND"/>
 *                         &lt;enumeration value="VUV"/>
 *                         &lt;enumeration value="WST"/>
 *                         &lt;enumeration value="XAF"/>
 *                         &lt;enumeration value="XAG"/>
 *                         &lt;enumeration value="XAU"/>
 *                         &lt;enumeration value="XBA"/>
 *                         &lt;enumeration value="XBB"/>
 *                         &lt;enumeration value="XBC"/>
 *                         &lt;enumeration value="XBD"/>
 *                         &lt;enumeration value="XCD"/>
 *                         &lt;enumeration value="XDR"/>
 *                         &lt;enumeration value="XOF"/>
 *                         &lt;enumeration value="XPD"/>
 *                         &lt;enumeration value="XPF"/>
 *                         &lt;enumeration value="XPT"/>
 *                         &lt;enumeration value="XSU"/>
 *                         &lt;enumeration value="XTS"/>
 *                         &lt;enumeration value="XUA"/>
 *                         &lt;enumeration value="XXX"/>
 *                         &lt;enumeration value="YER"/>
 *                         &lt;enumeration value="ZAR"/>
 *                         &lt;enumeration value="ZMW"/>
 *                         &lt;enumeration value="ZWL"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TipoCambio" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalServGravados" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalServExentos" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalMercanciasGravadas" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalMercanciasExentas" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalGravado" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalExento" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalVenta" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                   &lt;element name="TotalDescuentos" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalVentaNeta" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                   &lt;element name="TotalImpuesto" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
 *                   &lt;element name="TotalComprobante" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InformacionReferencia" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TipoDoc">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="01"/>
 *                         &lt;enumeration value="02"/>
 *                         &lt;enumeration value="03"/>
 *                         &lt;enumeration value="04"/>
 *                         &lt;enumeration value="05"/>
 *                         &lt;enumeration value="06"/>
 *                         &lt;enumeration value="07"/>
 *                         &lt;enumeration value="08"/>
 *                         &lt;enumeration value="99"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Numero">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="Codigo">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="01"/>
 *                         &lt;enumeration value="02"/>
 *                         &lt;enumeration value="03"/>
 *                         &lt;enumeration value="04"/>
 *                         &lt;enumeration value="05"/>
 *                         &lt;enumeration value="99"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Razon">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="180"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Normativa">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NumeroResolucion">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="13"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="FechaResolucion">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Otros" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtroTexto" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="OtroContenido" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;any processContents='lax' namespace='##other'/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clave",
    "numeroConsecutivo",
    "fechaEmision",
    "emisor",
    "receptor",
    "condicionVenta",
    "plazoCredito",
    "medioPago",
    "detalleServicio",
    "resumenFactura",
    "informacionReferencia",
    "normativa",
    "otros",
    "signature"
})
@XmlRootElement(name = "FacturaElectronica", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
public class FacturaElectronica {

    @XmlElement(name = "Clave", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected String clave;
    @XmlElement(name = "NumeroConsecutivo", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected String numeroConsecutivo;
    @XmlElement(name = "FechaEmision", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEmision;
    @XmlElement(name = "Emisor", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected EmisorType emisor;
    @XmlElement(name = "Receptor", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
    protected ReceptorType receptor;
    @XmlElement(name = "CondicionVenta", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected String condicionVenta;
    @XmlElement(name = "PlazoCredito", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
    protected String plazoCredito;
    @XmlElement(name = "MedioPago", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected List<String> medioPago;
    @XmlElement(name = "DetalleServicio", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected FacturaElectronica.DetalleServicio detalleServicio;
    @XmlElement(name = "ResumenFactura", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected FacturaElectronica.ResumenFactura resumenFactura;
    @XmlElement(name = "InformacionReferencia", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
    protected List<FacturaElectronica.InformacionReferencia> informacionReferencia;
    @XmlElement(name = "Normativa", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
    protected FacturaElectronica.Normativa normativa;
    @XmlElement(name = "Otros", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
    protected FacturaElectronica.Otros otros;
    @XmlElement(name = "Signature", required = true)
    protected SignatureType signature;

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroConsecutivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    /**
     * Define el valor de la propiedad numeroConsecutivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroConsecutivo(String value) {
        this.numeroConsecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEmision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Define el valor de la propiedad fechaEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEmision(XMLGregorianCalendar value) {
        this.fechaEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad emisor.
     * 
     * @return
     *     possible object is
     *     {@link EmisorType }
     *     
     */
    public EmisorType getEmisor() {
        return emisor;
    }

    /**
     * Define el valor de la propiedad emisor.
     * 
     * @param value
     *     allowed object is
     *     {@link EmisorType }
     *     
     */
    public void setEmisor(EmisorType value) {
        this.emisor = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorType }
     *     
     */
    public ReceptorType getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorType }
     *     
     */
    public void setReceptor(ReceptorType value) {
        this.receptor = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionVenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionVenta() {
        return condicionVenta;
    }

    /**
     * Define el valor de la propiedad condicionVenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionVenta(String value) {
        this.condicionVenta = value;
    }

    /**
     * Obtiene el valor de la propiedad plazoCredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlazoCredito() {
        return plazoCredito;
    }

    /**
     * Define el valor de la propiedad plazoCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlazoCredito(String value) {
        this.plazoCredito = value;
    }

    /**
     * Gets the value of the medioPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the medioPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedioPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMedioPago() {
        if (medioPago == null) {
            medioPago = new ArrayList<String>();
        }
        return this.medioPago;
    }

    /**
     * Obtiene el valor de la propiedad detalleServicio.
     * 
     * @return
     *     possible object is
     *     {@link FacturaElectronica.DetalleServicio }
     *     
     */
    public FacturaElectronica.DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    /**
     * Define el valor de la propiedad detalleServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link FacturaElectronica.DetalleServicio }
     *     
     */
    public void setDetalleServicio(FacturaElectronica.DetalleServicio value) {
        this.detalleServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad resumenFactura.
     * 
     * @return
     *     possible object is
     *     {@link FacturaElectronica.ResumenFactura }
     *     
     */
    public FacturaElectronica.ResumenFactura getResumenFactura() {
        return resumenFactura;
    }

    /**
     * Define el valor de la propiedad resumenFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link FacturaElectronica.ResumenFactura }
     *     
     */
    public void setResumenFactura(FacturaElectronica.ResumenFactura value) {
        this.resumenFactura = value;
    }

    /**
     * Gets the value of the informacionReferencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informacionReferencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformacionReferencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FacturaElectronica.InformacionReferencia }
     * 
     * 
     */
    public List<FacturaElectronica.InformacionReferencia> getInformacionReferencia() {
        if (informacionReferencia == null) {
            informacionReferencia = new ArrayList<FacturaElectronica.InformacionReferencia>();
        }
        return this.informacionReferencia;
    }

    /**
     * Obtiene el valor de la propiedad normativa.
     * 
     * @return
     *     possible object is
     *     {@link FacturaElectronica.Normativa }
     *     
     */
    public FacturaElectronica.Normativa getNormativa() {
        return normativa;
    }

    /**
     * Define el valor de la propiedad normativa.
     * 
     * @param value
     *     allowed object is
     *     {@link FacturaElectronica.Normativa }
     *     
     */
    public void setNormativa(FacturaElectronica.Normativa value) {
        this.normativa = value;
    }

    /**
     * Obtiene el valor de la propiedad otros.
     * 
     * @return
     *     possible object is
     *     {@link FacturaElectronica.Otros }
     *     
     */
    public FacturaElectronica.Otros getOtros() {
        return otros;
    }

    /**
     * Define el valor de la propiedad otros.
     * 
     * @param value
     *     allowed object is
     *     {@link FacturaElectronica.Otros }
     *     
     */
    public void setOtros(FacturaElectronica.Otros value) {
        this.otros = value;
    }

    /**
     * Obtiene el valor de la propiedad signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define el valor de la propiedad signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="LineaDetalle" maxOccurs="1000">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="NumeroLinea" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
     *                   &lt;element name="Codigo" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}CodigoType" maxOccurs="5" minOccurs="0"/>
     *                   &lt;element name="Cantidad">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="16"/>
     *                         &lt;fractionDigits value="3"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="UnidadMedida" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}UnidadMedidaType"/>
     *                   &lt;element name="UnidadMedidaComercial" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="20"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="Detalle">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="160"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="PrecioUnitario" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *                   &lt;element name="MontoTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *                   &lt;element name="MontoDescuento" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *                   &lt;element name="NaturalezaDescuento" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;maxLength value="80"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SubTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *                   &lt;element name="Impuesto" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}ImpuestoType" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element name="MontoTotalLinea" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "lineaDetalle"
    })
    public static class DetalleServicio {

        @XmlElement(name = "LineaDetalle", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected List<FacturaElectronica.DetalleServicio.LineaDetalle> lineaDetalle;

        /**
         * Gets the value of the lineaDetalle property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the lineaDetalle property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLineaDetalle().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FacturaElectronica.DetalleServicio.LineaDetalle }
         * 
         * 
         */
        public List<FacturaElectronica.DetalleServicio.LineaDetalle> getLineaDetalle() {
            if (lineaDetalle == null) {
                lineaDetalle = new ArrayList<FacturaElectronica.DetalleServicio.LineaDetalle>();
            }
            return this.lineaDetalle;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="NumeroLinea" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
         *         &lt;element name="Codigo" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}CodigoType" maxOccurs="5" minOccurs="0"/>
         *         &lt;element name="Cantidad">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="16"/>
         *               &lt;fractionDigits value="3"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="UnidadMedida" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}UnidadMedidaType"/>
         *         &lt;element name="UnidadMedidaComercial" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="20"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="Detalle">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="160"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="PrecioUnitario" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
         *         &lt;element name="MontoTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
         *         &lt;element name="MontoDescuento" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
         *         &lt;element name="NaturalezaDescuento" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;maxLength value="80"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SubTotal" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
         *         &lt;element name="Impuesto" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}ImpuestoType" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element name="MontoTotalLinea" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "numeroLinea",
            "codigo",
            "cantidad",
            "unidadMedida",
            "unidadMedidaComercial",
            "detalle",
            "precioUnitario",
            "montoTotal",
            "montoDescuento",
            "naturalezaDescuento",
            "subTotal",
            "impuesto",
            "montoTotalLinea"
        })
        public static class LineaDetalle {

            @XmlElement(name = "NumeroLinea", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger numeroLinea;
            @XmlElement(name = "Codigo", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
            protected List<CodigoType> codigo;
            @XmlElement(name = "Cantidad", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected BigDecimal cantidad;
            @XmlElement(name = "UnidadMedida", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected String unidadMedida;
            @XmlElement(name = "UnidadMedidaComercial", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
            protected String unidadMedidaComercial;
            @XmlElement(name = "Detalle", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected String detalle;
            @XmlElement(name = "PrecioUnitario", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected BigDecimal precioUnitario;
            @XmlElement(name = "MontoTotal", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected BigDecimal montoTotal;
            @XmlElement(name = "MontoDescuento", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
            protected BigDecimal montoDescuento;
            @XmlElement(name = "NaturalezaDescuento", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
            protected String naturalezaDescuento;
            @XmlElement(name = "SubTotal", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected BigDecimal subTotal;
            @XmlElement(name = "Impuesto", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
            protected List<ImpuestoType> impuesto;
            @XmlElement(name = "MontoTotalLinea", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
            protected BigDecimal montoTotalLinea;

            /**
             * Obtiene el valor de la propiedad numeroLinea.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getNumeroLinea() {
                return numeroLinea;
            }

            /**
             * Define el valor de la propiedad numeroLinea.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setNumeroLinea(BigInteger value) {
                this.numeroLinea = value;
            }

            /**
             * Gets the value of the codigo property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the codigo property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCodigo().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link CodigoType }
             * 
             * 
             */
            public List<CodigoType> getCodigo() {
                if (codigo == null) {
                    codigo = new ArrayList<CodigoType>();
                }
                return this.codigo;
            }

            /**
             * Obtiene el valor de la propiedad cantidad.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCantidad() {
                return cantidad;
            }

            /**
             * Define el valor de la propiedad cantidad.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCantidad(BigDecimal value) {
                this.cantidad = value;
            }

            /**
             * Obtiene el valor de la propiedad unidadMedida.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnidadMedida() {
                return unidadMedida;
            }

            /**
             * Define el valor de la propiedad unidadMedida.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnidadMedida(String value) {
                this.unidadMedida = value;
            }

            /**
             * Obtiene el valor de la propiedad unidadMedidaComercial.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnidadMedidaComercial() {
                return unidadMedidaComercial;
            }

            /**
             * Define el valor de la propiedad unidadMedidaComercial.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnidadMedidaComercial(String value) {
                this.unidadMedidaComercial = value;
            }

            /**
             * Obtiene el valor de la propiedad detalle.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDetalle() {
                return detalle;
            }

            /**
             * Define el valor de la propiedad detalle.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDetalle(String value) {
                this.detalle = value;
            }

            /**
             * Obtiene el valor de la propiedad precioUnitario.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPrecioUnitario() {
                return precioUnitario;
            }

            /**
             * Define el valor de la propiedad precioUnitario.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPrecioUnitario(BigDecimal value) {
                this.precioUnitario = value;
            }

            /**
             * Obtiene el valor de la propiedad montoTotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoTotal() {
                return montoTotal;
            }

            /**
             * Define el valor de la propiedad montoTotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoTotal(BigDecimal value) {
                this.montoTotal = value;
            }

            /**
             * Obtiene el valor de la propiedad montoDescuento.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoDescuento() {
                return montoDescuento;
            }

            /**
             * Define el valor de la propiedad montoDescuento.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoDescuento(BigDecimal value) {
                this.montoDescuento = value;
            }

            /**
             * Obtiene el valor de la propiedad naturalezaDescuento.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNaturalezaDescuento() {
                return naturalezaDescuento;
            }

            /**
             * Define el valor de la propiedad naturalezaDescuento.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNaturalezaDescuento(String value) {
                this.naturalezaDescuento = value;
            }

            /**
             * Obtiene el valor de la propiedad subTotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSubTotal() {
                return subTotal;
            }

            /**
             * Define el valor de la propiedad subTotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSubTotal(BigDecimal value) {
                this.subTotal = value;
            }

            /**
             * Gets the value of the impuesto property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the impuesto property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getImpuesto().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ImpuestoType }
             * 
             * 
             */
            public List<ImpuestoType> getImpuesto() {
                if (impuesto == null) {
                    impuesto = new ArrayList<ImpuestoType>();
                }
                return this.impuesto;
            }

            /**
             * Obtiene el valor de la propiedad montoTotalLinea.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoTotalLinea() {
                return montoTotalLinea;
            }

            /**
             * Define el valor de la propiedad montoTotalLinea.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoTotalLinea(BigDecimal value) {
                this.montoTotalLinea = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="TipoDoc">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="01"/>
     *               &lt;enumeration value="02"/>
     *               &lt;enumeration value="03"/>
     *               &lt;enumeration value="04"/>
     *               &lt;enumeration value="05"/>
     *               &lt;enumeration value="06"/>
     *               &lt;enumeration value="07"/>
     *               &lt;enumeration value="08"/>
     *               &lt;enumeration value="99"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Numero">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="FechaEmision" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="Codigo">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="01"/>
     *               &lt;enumeration value="02"/>
     *               &lt;enumeration value="03"/>
     *               &lt;enumeration value="04"/>
     *               &lt;enumeration value="05"/>
     *               &lt;enumeration value="99"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Razon">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="180"/>
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
    @XmlType(name = "", propOrder = {
        "tipoDoc",
        "numero",
        "fechaEmision",
        "codigo",
        "razon"
    })
    public static class InformacionReferencia {

        @XmlElement(name = "TipoDoc", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String tipoDoc;
        @XmlElement(name = "Numero", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String numero;
        @XmlElement(name = "FechaEmision", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar fechaEmision;
        @XmlElement(name = "Codigo", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String codigo;
        @XmlElement(name = "Razon", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String razon;

        /**
         * Obtiene el valor de la propiedad tipoDoc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoDoc() {
            return tipoDoc;
        }

        /**
         * Define el valor de la propiedad tipoDoc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoDoc(String value) {
            this.tipoDoc = value;
        }

        /**
         * Obtiene el valor de la propiedad numero.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumero() {
            return numero;
        }

        /**
         * Define el valor de la propiedad numero.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumero(String value) {
            this.numero = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaEmision.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaEmision() {
            return fechaEmision;
        }

        /**
         * Define el valor de la propiedad fechaEmision.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaEmision(XMLGregorianCalendar value) {
            this.fechaEmision = value;
        }

        /**
         * Obtiene el valor de la propiedad codigo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigo() {
            return codigo;
        }

        /**
         * Define el valor de la propiedad codigo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigo(String value) {
            this.codigo = value;
        }

        /**
         * Obtiene el valor de la propiedad razon.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRazon() {
            return razon;
        }

        /**
         * Define el valor de la propiedad razon.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRazon(String value) {
            this.razon = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="NumeroResolucion">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="13"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="FechaResolucion">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="20"/>
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
    @XmlType(name = "", propOrder = {
        "numeroResolucion",
        "fechaResolucion"
    })
    public static class Normativa {

        @XmlElement(name = "NumeroResolucion", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String numeroResolucion;
        @XmlElement(name = "FechaResolucion", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected String fechaResolucion;

        /**
         * Obtiene el valor de la propiedad numeroResolucion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroResolucion() {
            return numeroResolucion;
        }

        /**
         * Define el valor de la propiedad numeroResolucion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroResolucion(String value) {
            this.numeroResolucion = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaResolucion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaResolucion() {
            return fechaResolucion;
        }

        /**
         * Define el valor de la propiedad fechaResolucion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaResolucion(String value) {
            this.fechaResolucion = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="OtroTexto" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="OtroContenido" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;any processContents='lax' namespace='##other'/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "otroTexto",
        "otroContenido"
    })
    public static class Otros {

        @XmlElement(name = "OtroTexto", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected List<FacturaElectronica.Otros.OtroTexto> otroTexto;
        @XmlElement(name = "OtroContenido", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected List<FacturaElectronica.Otros.OtroContenido> otroContenido;

        /**
         * Gets the value of the otroTexto property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otroTexto property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtroTexto().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FacturaElectronica.Otros.OtroTexto }
         * 
         * 
         */
        public List<FacturaElectronica.Otros.OtroTexto> getOtroTexto() {
            if (otroTexto == null) {
                otroTexto = new ArrayList<FacturaElectronica.Otros.OtroTexto>();
            }
            return this.otroTexto;
        }

        /**
         * Gets the value of the otroContenido property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otroContenido property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtroContenido().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FacturaElectronica.Otros.OtroContenido }
         * 
         * 
         */
        public List<FacturaElectronica.Otros.OtroContenido> getOtroContenido() {
            if (otroContenido == null) {
                otroContenido = new ArrayList<FacturaElectronica.Otros.OtroContenido>();
            }
            return this.otroContenido;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;any processContents='lax' namespace='##other'/>
         *       &lt;/sequence>
         *       &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "any"
        })
        public static class OtroContenido {

            @XmlAnyElement(lax = true)
            protected Object any;
            @XmlAttribute(name = "codigo")
            protected String codigo;

            /**
             * Obtiene el valor de la propiedad any.
             * 
             * @return
             *     possible object is
             *     {@link Element }
             *     {@link Object }
             *     
             */
            public Object getAny() {
                return any;
            }

            /**
             * Define el valor de la propiedad any.
             * 
             * @param value
             *     allowed object is
             *     {@link Element }
             *     {@link Object }
             *     
             */
            public void setAny(Object value) {
                this.any = value;
            }

            /**
             * Obtiene el valor de la propiedad codigo.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodigo() {
                return codigo;
            }

            /**
             * Define el valor de la propiedad codigo.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodigo(String value) {
                this.codigo = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class OtroTexto {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "codigo")
            protected String codigo;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad codigo.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodigo() {
                return codigo;
            }

            /**
             * Define el valor de la propiedad codigo.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodigo(String value) {
                this.codigo = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CodigoMoneda" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="AED"/>
     *               &lt;enumeration value="AFN"/>
     *               &lt;enumeration value="ALL"/>
     *               &lt;enumeration value="AMD"/>
     *               &lt;enumeration value="ANG"/>
     *               &lt;enumeration value="AOA"/>
     *               &lt;enumeration value="ARS"/>
     *               &lt;enumeration value="AUD"/>
     *               &lt;enumeration value="AWG"/>
     *               &lt;enumeration value="AZN"/>
     *               &lt;enumeration value="BAM"/>
     *               &lt;enumeration value="BBD"/>
     *               &lt;enumeration value="BDT"/>
     *               &lt;enumeration value="BGN"/>
     *               &lt;enumeration value="BHD"/>
     *               &lt;enumeration value="BIF"/>
     *               &lt;enumeration value="BMD"/>
     *               &lt;enumeration value="BND"/>
     *               &lt;enumeration value="BOB"/>
     *               &lt;enumeration value="BOV"/>
     *               &lt;enumeration value="BRL"/>
     *               &lt;enumeration value="BSD"/>
     *               &lt;enumeration value="BTN"/>
     *               &lt;enumeration value="BWP"/>
     *               &lt;enumeration value="BYR"/>
     *               &lt;enumeration value="BZD"/>
     *               &lt;enumeration value="CAD"/>
     *               &lt;enumeration value="CDF"/>
     *               &lt;enumeration value="CHE"/>
     *               &lt;enumeration value="CHF"/>
     *               &lt;enumeration value="CHW"/>
     *               &lt;enumeration value="CLF"/>
     *               &lt;enumeration value="CLP"/>
     *               &lt;enumeration value="CNY"/>
     *               &lt;enumeration value="COP"/>
     *               &lt;enumeration value="COU"/>
     *               &lt;enumeration value="CRC"/>
     *               &lt;enumeration value="CUC"/>
     *               &lt;enumeration value="CUP"/>
     *               &lt;enumeration value="CVE"/>
     *               &lt;enumeration value="CZK"/>
     *               &lt;enumeration value="DJF"/>
     *               &lt;enumeration value="DKK"/>
     *               &lt;enumeration value="DOP"/>
     *               &lt;enumeration value="DZD"/>
     *               &lt;enumeration value="EGP"/>
     *               &lt;enumeration value="ERN"/>
     *               &lt;enumeration value="ETB"/>
     *               &lt;enumeration value="EUR"/>
     *               &lt;enumeration value="FJD"/>
     *               &lt;enumeration value="FKP"/>
     *               &lt;enumeration value="GBP"/>
     *               &lt;enumeration value="GEL"/>
     *               &lt;enumeration value="GHS"/>
     *               &lt;enumeration value="GIP"/>
     *               &lt;enumeration value="GMD"/>
     *               &lt;enumeration value="GNF"/>
     *               &lt;enumeration value="GTQ"/>
     *               &lt;enumeration value="GYD"/>
     *               &lt;enumeration value="HKD"/>
     *               &lt;enumeration value="HNL"/>
     *               &lt;enumeration value="HRK"/>
     *               &lt;enumeration value="HTG"/>
     *               &lt;enumeration value="HUF"/>
     *               &lt;enumeration value="IDR"/>
     *               &lt;enumeration value="ILS"/>
     *               &lt;enumeration value="INR"/>
     *               &lt;enumeration value="IQD"/>
     *               &lt;enumeration value="IRR"/>
     *               &lt;enumeration value="ISK"/>
     *               &lt;enumeration value="JMD"/>
     *               &lt;enumeration value="JOD"/>
     *               &lt;enumeration value="JPY"/>
     *               &lt;enumeration value="KES"/>
     *               &lt;enumeration value="KGS"/>
     *               &lt;enumeration value="KHR"/>
     *               &lt;enumeration value="KMF"/>
     *               &lt;enumeration value="KPW"/>
     *               &lt;enumeration value="KRW"/>
     *               &lt;enumeration value="KWD"/>
     *               &lt;enumeration value="KYD"/>
     *               &lt;enumeration value="KZT"/>
     *               &lt;enumeration value="LAK"/>
     *               &lt;enumeration value="LBP"/>
     *               &lt;enumeration value="LKR"/>
     *               &lt;enumeration value="LRD"/>
     *               &lt;enumeration value="LSL"/>
     *               &lt;enumeration value="LYD"/>
     *               &lt;enumeration value="MAD"/>
     *               &lt;enumeration value="MDL"/>
     *               &lt;enumeration value="MGA"/>
     *               &lt;enumeration value="MKD"/>
     *               &lt;enumeration value="MMK"/>
     *               &lt;enumeration value="MNT"/>
     *               &lt;enumeration value="MOP"/>
     *               &lt;enumeration value="MRO"/>
     *               &lt;enumeration value="MUR"/>
     *               &lt;enumeration value="MVR"/>
     *               &lt;enumeration value="MWK"/>
     *               &lt;enumeration value="MXN"/>
     *               &lt;enumeration value="MXV"/>
     *               &lt;enumeration value="MYR"/>
     *               &lt;enumeration value="MZN"/>
     *               &lt;enumeration value="NAD"/>
     *               &lt;enumeration value="NGN"/>
     *               &lt;enumeration value="NIO"/>
     *               &lt;enumeration value="NOK"/>
     *               &lt;enumeration value="NPR"/>
     *               &lt;enumeration value="NZD"/>
     *               &lt;enumeration value="OMR"/>
     *               &lt;enumeration value="PAB"/>
     *               &lt;enumeration value="PEN"/>
     *               &lt;enumeration value="PGK"/>
     *               &lt;enumeration value="PHP"/>
     *               &lt;enumeration value="PKR"/>
     *               &lt;enumeration value="PLN"/>
     *               &lt;enumeration value="PYG"/>
     *               &lt;enumeration value="QAR"/>
     *               &lt;enumeration value="RON"/>
     *               &lt;enumeration value="RSD"/>
     *               &lt;enumeration value="RUB"/>
     *               &lt;enumeration value="RWF"/>
     *               &lt;enumeration value="SAR"/>
     *               &lt;enumeration value="SBD"/>
     *               &lt;enumeration value="SCR"/>
     *               &lt;enumeration value="SDG"/>
     *               &lt;enumeration value="SEK"/>
     *               &lt;enumeration value="SGD"/>
     *               &lt;enumeration value="SHP"/>
     *               &lt;enumeration value="SLL"/>
     *               &lt;enumeration value="SOS"/>
     *               &lt;enumeration value="SRD"/>
     *               &lt;enumeration value="SSP"/>
     *               &lt;enumeration value="STD"/>
     *               &lt;enumeration value="SVC"/>
     *               &lt;enumeration value="SYP"/>
     *               &lt;enumeration value="SZL"/>
     *               &lt;enumeration value="THB"/>
     *               &lt;enumeration value="TJS"/>
     *               &lt;enumeration value="TMT"/>
     *               &lt;enumeration value="TND"/>
     *               &lt;enumeration value="TOP"/>
     *               &lt;enumeration value="TRY"/>
     *               &lt;enumeration value="TTD"/>
     *               &lt;enumeration value="TWD"/>
     *               &lt;enumeration value="TZS"/>
     *               &lt;enumeration value="UAH"/>
     *               &lt;enumeration value="UGX"/>
     *               &lt;enumeration value="USD"/>
     *               &lt;enumeration value="USN"/>
     *               &lt;enumeration value="UYI"/>
     *               &lt;enumeration value="UYU"/>
     *               &lt;enumeration value="UZS"/>
     *               &lt;enumeration value="VEF"/>
     *               &lt;enumeration value="VND"/>
     *               &lt;enumeration value="VUV"/>
     *               &lt;enumeration value="WST"/>
     *               &lt;enumeration value="XAF"/>
     *               &lt;enumeration value="XAG"/>
     *               &lt;enumeration value="XAU"/>
     *               &lt;enumeration value="XBA"/>
     *               &lt;enumeration value="XBB"/>
     *               &lt;enumeration value="XBC"/>
     *               &lt;enumeration value="XBD"/>
     *               &lt;enumeration value="XCD"/>
     *               &lt;enumeration value="XDR"/>
     *               &lt;enumeration value="XOF"/>
     *               &lt;enumeration value="XPD"/>
     *               &lt;enumeration value="XPF"/>
     *               &lt;enumeration value="XPT"/>
     *               &lt;enumeration value="XSU"/>
     *               &lt;enumeration value="XTS"/>
     *               &lt;enumeration value="XUA"/>
     *               &lt;enumeration value="XXX"/>
     *               &lt;enumeration value="YER"/>
     *               &lt;enumeration value="ZAR"/>
     *               &lt;enumeration value="ZMW"/>
     *               &lt;enumeration value="ZWL"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TipoCambio" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalServGravados" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalServExentos" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalMercanciasGravadas" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalMercanciasExentas" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalGravado" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalExento" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalVenta" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *         &lt;element name="TotalDescuentos" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalVentaNeta" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *         &lt;element name="TotalImpuesto" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType" minOccurs="0"/>
     *         &lt;element name="TotalComprobante" type="{https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica}DecimalDineroType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "codigoMoneda",
        "tipoCambio",
        "totalServGravados",
        "totalServExentos",
        "totalMercanciasGravadas",
        "totalMercanciasExentas",
        "totalGravado",
        "totalExento",
        "totalVenta",
        "totalDescuentos",
        "totalVentaNeta",
        "totalImpuesto",
        "totalComprobante"
    })
    public static class ResumenFactura {

        @XmlElement(name = "CodigoMoneda", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected String codigoMoneda;
        @XmlElement(name = "TipoCambio", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal tipoCambio;
        @XmlElement(name = "TotalServGravados", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalServGravados;
        @XmlElement(name = "TotalServExentos", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalServExentos;
        @XmlElement(name = "TotalMercanciasGravadas", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalMercanciasGravadas;
        @XmlElement(name = "TotalMercanciasExentas", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalMercanciasExentas;
        @XmlElement(name = "TotalGravado", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalGravado;
        @XmlElement(name = "TotalExento", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalExento;
        @XmlElement(name = "TotalVenta", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected BigDecimal totalVenta;
        @XmlElement(name = "TotalDescuentos", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalDescuentos;
        @XmlElement(name = "TotalVentaNeta", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected BigDecimal totalVentaNeta;
        @XmlElement(name = "TotalImpuesto", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica")
        protected BigDecimal totalImpuesto;
        @XmlElement(name = "TotalComprobante", namespace = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica", required = true)
        protected BigDecimal totalComprobante;

        /**
         * Obtiene el valor de la propiedad codigoMoneda.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoMoneda() {
            return codigoMoneda;
        }

        /**
         * Define el valor de la propiedad codigoMoneda.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoMoneda(String value) {
            this.codigoMoneda = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoCambio.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTipoCambio() {
            return tipoCambio;
        }

        /**
         * Define el valor de la propiedad tipoCambio.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTipoCambio(BigDecimal value) {
            this.tipoCambio = value;
        }

        /**
         * Obtiene el valor de la propiedad totalServGravados.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalServGravados() {
            return totalServGravados;
        }

        /**
         * Define el valor de la propiedad totalServGravados.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalServGravados(BigDecimal value) {
            this.totalServGravados = value;
        }

        /**
         * Obtiene el valor de la propiedad totalServExentos.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalServExentos() {
            return totalServExentos;
        }

        /**
         * Define el valor de la propiedad totalServExentos.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalServExentos(BigDecimal value) {
            this.totalServExentos = value;
        }

        /**
         * Obtiene el valor de la propiedad totalMercanciasGravadas.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalMercanciasGravadas() {
            return totalMercanciasGravadas;
        }

        /**
         * Define el valor de la propiedad totalMercanciasGravadas.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalMercanciasGravadas(BigDecimal value) {
            this.totalMercanciasGravadas = value;
        }

        /**
         * Obtiene el valor de la propiedad totalMercanciasExentas.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalMercanciasExentas() {
            return totalMercanciasExentas;
        }

        /**
         * Define el valor de la propiedad totalMercanciasExentas.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalMercanciasExentas(BigDecimal value) {
            this.totalMercanciasExentas = value;
        }

        /**
         * Obtiene el valor de la propiedad totalGravado.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalGravado() {
            return totalGravado;
        }

        /**
         * Define el valor de la propiedad totalGravado.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalGravado(BigDecimal value) {
            this.totalGravado = value;
        }

        /**
         * Obtiene el valor de la propiedad totalExento.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalExento() {
            return totalExento;
        }

        /**
         * Define el valor de la propiedad totalExento.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalExento(BigDecimal value) {
            this.totalExento = value;
        }

        /**
         * Obtiene el valor de la propiedad totalVenta.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalVenta() {
            return totalVenta;
        }

        /**
         * Define el valor de la propiedad totalVenta.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalVenta(BigDecimal value) {
            this.totalVenta = value;
        }

        /**
         * Obtiene el valor de la propiedad totalDescuentos.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalDescuentos() {
            return totalDescuentos;
        }

        /**
         * Define el valor de la propiedad totalDescuentos.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalDescuentos(BigDecimal value) {
            this.totalDescuentos = value;
        }

        /**
         * Obtiene el valor de la propiedad totalVentaNeta.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalVentaNeta() {
            return totalVentaNeta;
        }

        /**
         * Define el valor de la propiedad totalVentaNeta.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalVentaNeta(BigDecimal value) {
            this.totalVentaNeta = value;
        }

        /**
         * Obtiene el valor de la propiedad totalImpuesto.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalImpuesto() {
            return totalImpuesto;
        }

        /**
         * Define el valor de la propiedad totalImpuesto.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalImpuesto(BigDecimal value) {
            this.totalImpuesto = value;
        }

        /**
         * Obtiene el valor de la propiedad totalComprobante.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalComprobante() {
            return totalComprobante;
        }

        /**
         * Define el valor de la propiedad totalComprobante.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalComprobante(BigDecimal value) {
            this.totalComprobante = value;
        }

    }

}
