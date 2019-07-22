package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Dao.ConsultasNativeDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

/**
 * Caja es las diferentes cajas de una empresa lo importante es la terminal 00001 son 5 digitos
 * CajaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("consultasNativeBo")
public class ConsultasNativeBoImpl implements ConsultasNativeBo {

	@Autowired
	private ConsultasNativeDao consultasNativeDao;

	@Override
	public Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial , String fechaFinal) {
		
		return consultasNativeDao.findByEmpresaAndEstado(empresa,fechaInicial,fechaFinal);
	}

	@Override
	public Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial , String fechaFinal,String cedula){
		return consultasNativeDao.findByEmpresaAndFechaAndCliente(empresa, fechaInicial, fechaFinal, cedula);
	}

	@Override
	public Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado){
		return consultasNativeDao.findByProformasByEmpresaAndEstado(empresa, estado);
	}
	@Override
	public Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa){
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
	public Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa, Integer idusuario, Integer estado,String fecha) {
		
		return consultasNativeDao.findByFacturasDelDia(empresa, idusuario, estado,fecha);
	}

	@Override
	public Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa, Integer idusuario, Integer estado, String fechaInicial, String fechaFinal, Long idCliente) {
		
		return consultasNativeDao.findByFacturasAnulacion(empresa, idusuario, estado, fechaInicial, fechaFinal, idCliente);
	}

	
}