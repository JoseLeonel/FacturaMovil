package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class TotalbyImpuestosCommand {

	 private Date fecha_emision;
	 private String tipo_doc;
	 private Double tipo_cambio;
	 private String numero_consecutivo;
	 private String clave;
	 private String consecutivo_proforma;
	 private String cedula;
	 private String identificacion_extranjero;
	private String nombre_completo;
	private String nombre_factura;
	private Double total_exo;
	private Double total_otros_cargos;
	private Double total_serv_gravados;
	private Double total_serv_exentos;
	private Double total_impuesto;
	private Double total_merc_exo;
	private Double total_serv_exo;
       private Double total_mercancias_gravadas;
       private Double total_mercancias_exentas;
       private Double total_exento;
       private Double total_gravado;
       private Double total_comprobante;
       private Double  imp_01;
       private Double imp_02;
       private Double  imp_03;
       private Double imp_04;
       private Double imp_05;
       private Double imp_06;
       private Double imp_07;
       private Double imp_08;
	

	public String getFechaEmisionSTR() {
		if (this.fecha_emision != null) {
			return Utils.getFechaGeneraReporte(this.fecha_emision);
		}
		return Constantes.EMPTY;
	}
	public String getTipo_doc() {
		return tipo_doc;
	}
	
	
	
	public Double getTotal_merc_exo() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_merc_exo != null ? this.total_merc_exo * -1 * this.tipo_cambio : this.total_merc_exo * this.tipo_cambio;
		} else {
			return this.total_merc_exo;
		}
	
	}
	
	public void setTotal_merc_exo(Double total_merc_exo) {
		
		this.total_merc_exo = total_merc_exo;
	}
	
	public Double getTotal_serv_exo() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_serv_exo != null ? this.total_serv_exo * -1 * this.tipo_cambio : this.total_serv_exo * this.tipo_cambio;
		} else {
			return this.total_serv_exo;
		}
	
	}
	
	public void setTotal_serv_exo(Double total_serv_exo) {
		this.total_serv_exo = total_serv_exo;
	}
	public Double getTotal_exento() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_exento != null ? this.total_exento * -1 * this.tipo_cambio : this.total_exento * this.tipo_cambio;
		} else {
			return this.total_exento;
		}

	
	}

	
	public void setTotal_exento(Double total_exento) {
		this.total_exento = total_exento;
	}

	public String getTipoDocSTR() {
		return MapEnums.ENUM_TIPO_DOC.get(this.tipo_doc);
	}
	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	
	public String getNumero_consecutivo() {
		return numero_consecutivo;
	}
	
	public void setNumero_consecutivo(String numero_consecutivo) {
		this.numero_consecutivo = numero_consecutivo;
	}
	
	
	public String getConsecutivo_proforma() {
		return consecutivo_proforma;
	}

	
	public void setConsecutivo_proforma(String consecutivo_proforma) {
		this.consecutivo_proforma = consecutivo_proforma;
	}

	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getIdentificacion_extranjero() {
		return identificacion_extranjero;
	}
	
	public void setIdentificacion_extranjero(String identificacion_extranjero) {
		this.identificacion_extranjero = identificacion_extranjero;
	}
	
	public String getNombre_completo() {
		return nombre_completo;
	}
	
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	
	public String getNombre_factura() {
		return nombre_factura;
	}
	
	public void setNombre_factura(String nombre_factura) {
		this.nombre_factura = nombre_factura;
	}
	
	
	
	public Double getTotal_impuesto() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_impuesto != null ? this.total_impuesto * -1 * this.tipo_cambio : this.total_impuesto * this.tipo_cambio;
		} else {
			return this.total_impuesto;
		}

		
	}
	
	public void setTotal_impuesto(Double total_impuesto) {
		this.total_impuesto = total_impuesto;
	}
	
	public Double getTotal_gravado() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_gravado != null ? this.total_impuesto * -1 * this.tipo_cambio : this.total_gravado * this.tipo_cambio;
		} else {
			return this.total_gravado;
		}

	}
	
	public void setTotal_gravado(Double total_gravado) {
		this.total_gravado = total_gravado;
	}
	
	public Date getFecha_emision() {
		return fecha_emision;
	}
	
	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	
	public Double getTotal_exo() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_exo != null ? this.total_impuesto * -1 * this.tipo_cambio : this.total_exo * this.tipo_cambio;
		} else {
			return this.total_exo;
		}

	}
	
	public void setTotal_exo(Double total_exo) {
		this.total_exo = total_exo;
	}
	
	public Double getTotal_otros_cargos() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_otros_cargos != null ? this.total_otros_cargos * -1 * this.tipo_cambio : this.total_otros_cargos * this.tipo_cambio;
		} else {
			return this.total_otros_cargos;
		}
	}
	
	public void setTotal_otros_cargos(Double total_otros_cargos) {
		this.total_otros_cargos = total_otros_cargos;
	}
	
	public Double getTotal_serv_gravados() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_serv_gravados != null ? this.total_serv_gravados * -1 * this.tipo_cambio : this.total_serv_gravados * this.tipo_cambio;
		} else {
			return this.total_serv_gravados;
		}
		
	}
	
	public void setTotal_serv_gravados(Double total_serv_gravados) {
		this.total_serv_gravados = total_serv_gravados;
	}
	
	public Double getTotal_serv_exentos() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_serv_exentos != null ? this.total_serv_exentos * -1 * this.tipo_cambio : this.total_serv_exentos * this.tipo_cambio;
		} else {
			return this.total_serv_exentos;
		}
		
	}
	
	public void setTotal_serv_exentos(Double total_serv_exentos) {
		this.total_serv_exentos = total_serv_exentos;
	}
	
	public Double getTotal_mercancias_gravadas() {
		
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_mercancias_gravadas != null ? this.total_mercancias_gravadas * -1 * this.tipo_cambio : this.total_mercancias_gravadas * this.tipo_cambio;
		} else {
			return this.total_mercancias_gravadas;
		}
		
	}
	
	public void setTotal_mercancias_gravadas(Double total_mercancias_gravadas) {
		this.total_mercancias_gravadas = total_mercancias_gravadas;
	}
	
	public Double getTotal_mercancias_exentas() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_mercancias_exentas != null ? this.total_mercancias_exentas * -1 * this.tipo_cambio : this.total_mercancias_exentas * this.tipo_cambio;
		} else {
			return this.total_mercancias_exentas;
		}

	
	}
	
	public void setTotal_mercancias_exentas(Double total_mercancias_exentas) {
		this.total_mercancias_exentas = total_mercancias_exentas;
	}
	
	public Double getTotal_comprobante() {
		if (tipo_doc != null && tipo_doc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.total_comprobante != null ? this.total_comprobante * -1 * this.tipo_cambio : this.total_comprobante * this.tipo_cambio;
		} else {
			return this.total_comprobante;
		}

	}
	
	public void setTotal_comprobante(Double total_comprobante) {
		this.total_comprobante = total_comprobante;
	}
	
	public Double getImp_01() {
		return imp_01;
	}
	
	public void setImp_01(Double imp_01) {
		this.imp_01 = imp_01;
	}
	
	public Double getImp_02() {
		return imp_02;
	}
	
	public void setImp_02(Double imp_02) {
		this.imp_02 = imp_02;
	}
	
	public Double getImp_03() {
		return imp_03;
	}
	
	public void setImp_03(Double imp_03) {
		this.imp_03 = imp_03;
	}
	
	public Double getImp_04() {
		return imp_04;
	}
	
	public void setImp_04(Double imp_04) {
		this.imp_04 = imp_04;
	}
	
	public Double getImp_05() {
		return imp_05;
	}
	
	public void setImp_05(Double imp_05) {
		this.imp_05 = imp_05;
	}
	
	public Double getImp_06() {
		return imp_06;
	}
	
	public void setImp_06(Double imp_06) {
		this.imp_06 = imp_06;
	}
	
	public Double getImp_07() {
		return imp_07;
	}
	
	public void setImp_07(Double imp_07) {
		this.imp_07 = imp_07;
	}
	
	public Double getImp_08() {
		return imp_08;
	}
	
	public void setImp_08(Double imp_08) {
		this.imp_08 = imp_08;
	}
	
	public Double getTipo_cambio() {
		return tipo_cambio;
	}
	
	public void setTipo_cambio(Double tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}
	
	
	
	
	 
}
