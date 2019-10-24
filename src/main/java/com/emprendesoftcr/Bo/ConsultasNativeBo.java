package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.ArticuloMinimoNative;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaComprasIvaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;
import com.emprendesoftcr.modelo.sqlNativo.DetallesFacturaNotaCreditoNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturaIDNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.GraficoCuentasPorCobrarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.modelo.sqlNativo.ListaNotasNative;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturaNCNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasImpuestoServicioNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

public interface ConsultasNativeBo {

	Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial, String fechaFinal);

	Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial, String fechaFinal, String cedula);

	Collection<ProformasByEmpresaAndEstadoAndUsuario> findByProformasByEmpresaAndEstadoAndUsuario(Empresa empresa, Integer estado, Integer idUsuario);

	Collection<ProformasByEmpresaAndFacturadaAndUsuario> findByProformasByEmpresaFacturadaAndUsuario(Empresa empresa, Integer idUsuario);

	Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado);

	Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa);

	Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa, Integer idusuario, String estado, String fecha);

	Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa, Integer idusuario, String estado, String fechaInicial, String fechaFinal, Long idCliente,String codigo);

	Collection<HaciendaComprobarNative> findByComprabarDocumentoPendienteaceptar();

	Collection<ConsultaIVANative> findByEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial);

	Collection<ConsultaComprasIvaNative> findByComprasEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial);

	Collection<CompraSimplificadaNative> findComprasSimplificadasByFechaAndEstadoAndEmpresa(Empresa empresa, String fechaInicial, String fechaFinal, Long idProveedor, Integer estado, Integer idUsuario);

	Collection<ListarFacturasNativa> findByFacturasAndFechaAndTipoDocAndUsuario(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, Cliente cliente, String tipoDocumento, String actividadComercial);

	Collection<ListarFacturasImpuestoServicioNativa> findByFacturasImpuestoServicio(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial);

	Collection<ConsultaGananciaNative> findByDetallesGanancia(Empresa empresa, Cliente cliente, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial, Integer idCategoria,String codigo);
	
	FacturaIDNativa findIdFactura(Long id);
	Collection<DetallesFacturaNotaCreditoNativa> findByFacturaParaNotaCredito(String consecutivo,Empresa empresa);
	
	Collection<ListarFacturaNCNativa> findByFacturasSinNotasCreditos(Empresa empresa,Integer idUsuario,Integer estado,String fechaInicial,String fechaFinal,Cliente cliente , String tipoDocumento,String actividadComercial);
	
	Collection<ListaNotasNative> findByNotasCreditoAndDebito(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, Cliente cliente, String tipoDocumento, String actividadComercial);
	Collection<ArticuloMinimoNative> findByAllArticulosMinimo(Empresa empresa);
	Collection<GraficoCuentasPorCobrarNative> findByGraficoCuentasXCobrar(Empresa empresa);
}
