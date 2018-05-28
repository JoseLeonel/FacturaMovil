package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.ProveedorBo;
import com.factura.FacturaElectronica.modelo.Proveedor;

@Component
public class ProveedorPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ProveedorBo proveedorBo;

	@Override
	public String getAsText() {
		Proveedor proveedor = (Proveedor) getValue();
		return (proveedor == null ? "" : proveedor.getId().toString());
	}

	@Override
	public void setAsText(String idProveedor) throws IllegalArgumentException {
		if ((idProveedor == null) || !StringUtils.hasLength(idProveedor)) {
			setValue(null);
		} else {

			setValue(proveedorBo.buscar(Integer.parseInt(idProveedor)));

		}
	}

	public ProveedorBo getProveedorBo() {
		return proveedorBo;
	}

	public void setProveedorBo(ProveedorBo proveedorBo) {
		this.proveedorBo = proveedorBo;
	}

}
