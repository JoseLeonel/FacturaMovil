package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
<<<<<<< HEAD
import com.emprendesoftcr.modelo.sqlNativo.ConsultaComprasIvaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;
=======
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
>>>>>>> 0a7653d7e17dc8d16953b0a74e3b38fb48706f2b
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

public interface  ConsultasNativeDao {

	Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial , String fechaFinal);
	
	Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial , String fechaFinal,String cedula);

	Collection<ProformasByEmpresaAndEstadoAndUsuario> findByProformasByEmpresaAndEstadoAndUsuario(Empresa empresa, Integer estado,Integer idUsuario);
	
	Collection<ProformasByEmpresaAndFacturadaAndUsuario> findByProformasByEmpresaFacturadaAndUsuario(Empresa empresa, Integer idUsuario);

	Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado);
	
	Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa);
	
	Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa,Integer idusuario,Integer estado,String fecha);

	Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa,Integer idusuario,Integer estado,String fechaInicial,String fechaFinal,Long idCliente);
	
	Collection<HaciendaComprobarNative> findByComprabarDocumentoPendienteaceptar();
	
<<<<<<< HEAD
	Collection<ConsultaIVANative> findByEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial , String fechaFinal, Integer estado, Integer codigoActividadComercial);
	
	Collection<ConsultaComprasIvaNative> findByComprasEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial , String fechaFinal, Integer estado, Integer codigoActividadComercial);
=======
	Collection<CompraSimplificadaNative> findComprasSimplificadasByFechaAndEstadoAndEmpresa(Empresa empresa, String fechaInicial , String fechaFinal,Long idProveedor,Integer estado,Integer idUsuario);
>>>>>>> 0a7653d7e17dc8d16953b0a74e3b38fb48706f2b
}
