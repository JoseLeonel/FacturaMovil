package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Dao.ConsultasNativeDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaComprasIvaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;
import com.emprendesoftcr.modelo.sqlNativo.DetallesFacturaNotaCreditoNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturaIDNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturaNCNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasImpuestoServicioNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

/**
 * Caja es las diferentes cajas de una empresa lo importante es la terminal 00001 son 5 digitos CajaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("consultasNativeBo")
public class ConsultasNativeBoImpl implements ConsultasNativeBo {

	@Autowired
	private ConsultasNativeDao consultasNativeDao;

	@Override
	public Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial, String fechaFinal) {

		return consultasNativeDao.findByEmpresaAndEstado(empresa, fechaInicial, fechaFinal);
	}

	@Override
	public Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial, String fechaFinal, String cedula) {
		return consultasNativeDao.findByEmpresaAndFechaAndCliente(empresa, fechaInicial, fechaFinal, cedula);
	}

	@Override
	public Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado) {
		return consultasNativeDao.findByProformasByEmpresaAndEstado(empresa, estado);
	}

	@Override
	public Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa) {
		return consultasNativeDao.findByProformasByEmpresaFacturada(empresa);
	}

	@Override
	public Collection<ProformasByEmpresaAndEstadoAndUsuario> findByProformasByEmpresaAndEstadoAndUsuario(Empresa empresa, Integer estado, Integer idUsuario) {
		return consultasNativeDao.findByProformasByEmpresaAndEstadoAndUsuario(empresa, estado, idUsuario);
	}

	@Override
	public Collection<ProformasByEmpresaAndFacturadaAndUsuario> findByProformasByEmpresaFacturadaAndUsuario(Empresa empresa, Integer idUsuario) {

		return consultasNativeDao.findByProformasByEmpresaFacturadaAndUsuario(empresa, idUsuario);
	}

	@Override
	public Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa, Integer idusuario, String estado, String fecha) {

		return consultasNativeDao.findByFacturasDelDia(empresa, idusuario, estado, fecha);
	}

	@Override
	public Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa, Integer idusuario, String estado, String fechaInicial, String fechaFinal, Long idCliente,String codigo) {

		return consultasNativeDao.findByFacturasAnulacion(empresa, idusuario, estado, fechaInicial, fechaFinal, idCliente,codigo);
	}

	@Override
	public Collection<HaciendaComprobarNative> findByComprabarDocumentoPendienteaceptar() {

		return consultasNativeDao.findByComprabarDocumentoPendienteaceptar();
	}

	@Override
	public Collection<ConsultaIVANative> findByEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial) {
		return consultasNativeDao.findByEmpresaAndEstadoAndFechasAndActividadComercial(empresa, fechaInicial, fechaFinal, estado, codigoActividadComercial);
	}

	@Override
	public Collection<ConsultaComprasIvaNative> findByComprasEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial) {
		return consultasNativeDao.findByComprasEmpresaAndEstadoAndFechasAndActividadComercial(empresa, fechaInicial, fechaFinal, estado, codigoActividadComercial);
	}

	@Override
	public Collection<CompraSimplificadaNative> findComprasSimplificadasByFechaAndEstadoAndEmpresa(Empresa empresa, String fechaInicial, String fechaFinal, Long idProveedor, Integer estado, Integer idUsuario) {
		return consultasNativeDao.findComprasSimplificadasByFechaAndEstadoAndEmpresa(empresa, fechaInicial, fechaFinal, idProveedor, estado, idUsuario);
	}

	@Override
	public Collection<ListarFacturasNativa> findByFacturasAndFechaAndTipoDocAndUsuario(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, Cliente cliente, String tipoDocumento, String actividadComercial) {

		return consultasNativeDao.findByFacturasAndFechaAndTipoDocAndUsuario(empresa, idUsuario, estado, fechaInicial, fechaFinal, cliente, tipoDocumento, actividadComercial);
	}

	@Override
	public Collection<ListarFacturasImpuestoServicioNativa> findByFacturasImpuestoServicio(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial) {
		return consultasNativeDao.findByFacturasImpuestoServicio(empresa, idUsuario, estado, fechaInicial, fechaFinal, actividadComercial);
	}

	@Override
	public Collection<ConsultaGananciaNative> findByDetallesGanancia(Empresa empresa, Cliente cliente, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial, Integer idCategoria,String codigo) {
		return consultasNativeDao.findByDetallesGanancia(empresa, cliente, estado, fechaInicial, fechaFinal, actividadComercial, idCategoria,codigo);
	}

  @Transactional
	@Override
	public FacturaIDNativa findIdFactura(Long id) {
		return consultasNativeDao.findIdFactura(id);
	}

	@Override
	public Collection<DetallesFacturaNotaCreditoNativa> findByFacturaParaNotaCredito(String consecutivo, Empresa empresa) {
				return consultasNativeDao.findByFacturaParaNotaCredito(consecutivo, empresa);
	}

	@Override
	public Collection<ListarFacturaNCNativa> findByFacturasSinNotasCreditos(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, Cliente cliente, String tipoDocumento, String actividadComercial) {
		
		return consultasNativeDao.findByFacturasSinNotasCreditos(empresa, idUsuario, estado, fechaInicial, fechaFinal, cliente, tipoDocumento, actividadComercial);
	}

}