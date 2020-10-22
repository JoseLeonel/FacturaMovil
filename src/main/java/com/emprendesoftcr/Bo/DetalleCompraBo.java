package com.emprendesoftcr.Bo;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;

public interface DetalleCompraBo {
	

	void agregar(DetalleCompra detalleCompra);

	void modificar(DetalleCompra detalleCompra);

	void eliminar(DetalleCompra detalleCompra);
	
	Collection<DetalleCompra> findByCompra(Compra compra);
	
	ByteArrayInputStream  createExcelDetalleCompra(Collection<RecepcionFacturaDetalle> lista, String fechaInicio, String fechaFin, Empresa empresa) throws Exception ;
	
	public List<Map<String, Object>>  detalleCompraSinIngresar(Long idCompra);
	
	DetalleCompra findById(Long idDetalleCompra);

}
