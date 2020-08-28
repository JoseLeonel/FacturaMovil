package com.emprendesoftcr.Bo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaUtilidadNative;
import com.emprendesoftcr.web.command.TotalDetallesCommand;

public interface DetalleBo {

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);

	Integer eliminarDetalleFactura(Factura factura) throws Exception;

	Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa);

	Collection<Detalle> facturasRango(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa, String tipoImpuesto, String actividadEconomica);

	Collection<Detalle> findByFactura(Factura factura);

	TotalDetallesCommand totalVentasPorDetalle(Empresa empresa, Date fechaInicio, Date FechaFinal, String tipoImpuesto, Integer estado, String actividadEconomica);

	Detalle findByCodigoAndEmpresa(String codigo, Empresa empresa);
	
	Detalle findById(Long  idDetalle);
	
  Collection<Detalle>  findbyIdFactura (Long idFactura);
	
	Collection<Detalle>  findbyConsecutivoAndEmpresa (String consecutivo,Empresa empresa);
	
	
	
	ByteArrayInputStream createExcelUtilidad(Collection<ConsultaUtilidadNative> facturas,Empresa empresa,String fechaInicio,String fechaFin) throws Exception ;
	
	ByteArrayInputStream createExcelVentasXCodigo(Collection<Detalle> detalles,String fechaInicio, String fechaFinal, Empresa empresa, String actividadEconomica) throws Exception ;

	ByteArrayInputStream  ventasbyCategoriaExcel(String fechaInicial ,String fechaFinal,Integer estado,Long idCategoria,Empresa empresa) throws IOException;
	List<Map<String, Object>>  ventasbyCategoria(String fechaInicial ,String fechaFinal,Integer estado,Long idCategoria,Integer idEmpresa);
	
	
}
