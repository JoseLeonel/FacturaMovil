package com.factura.FacturaElectronica.Bo.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.UsuarioCajaBo;
import com.factura.FacturaElectronica.Dao.UsuarioCajaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

@Transactional
@EnableTransactionManagement
@Service("usuarioCajaBo")
public class UsuarioCajaBoImpl implements UsuarioCajaBo {

	@Autowired
	UsuarioCajaDao usuarioCajaDao;

	public void agregar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.agregar(usuarioCaja);
	}

	public void modificar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.modificar(usuarioCaja);
	}

	public void eliminar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.eliminar(usuarioCaja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public UsuarioCaja buscar(Integer id) {
		return usuarioCajaDao.buscar(id);
	}

	/**
	 * Buscar por usuario y estado
	 * @see com.factura.FacturaElectronica.Bo.UsuarioCajaBo#findByUsuarioAndEstado(com.factura.FacturaElectronica.modelo.Usuario, java.lang.String)
	 */
	@Override
	public UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado) {
		return usuarioCajaDao.findByUsuarioAndEstado(usuario, estado);
		
	}
	
/**
 * Cerrar la caja
 * @see com.factura.FacturaElectronica.Bo.UsuarioCajaBo#cierreCaja(com.factura.FacturaElectronica.modelo.UsuarioCaja)
 */
	@Override
	public void cierreCaja(UsuarioCaja usuarioCaja) {
		usuarioCaja.setUpdated_at(new Date());
		usuarioCaja.setEstado(Constantes.ESTADO_INACTIVO);
		usuarioCaja.setTotalNeto(usuarioCaja.getTotalBanco() + usuarioCaja.getTotalEfectivo()+ usuarioCaja.getTotalTarjeta()+usuarioCaja.getTotalFondoInicial());
		agregar(usuarioCaja);
		
		
	}
	
}