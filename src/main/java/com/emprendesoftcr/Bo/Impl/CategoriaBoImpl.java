package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Dao.CategoriaDao;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CategoriaCommand;

/**
 * categorias se va dividir los articulos de una empresa CategoriaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("categoriaBo")
public class CategoriaBoImpl implements CategoriaBo {

	@Autowired
	private CategoriaDao categoriaDao;

	@Transactional
	@Override
	public void agregar(Categoria categoria) {
		categoriaDao.agregar(categoria);
	}

	@Override
	@Transactional
	public void modificar(Categoria categoria) {
		categoriaDao.modificar(categoria);
	}

	@Override
	@Transactional
	public void eliminar(Categoria categoria) {
		categoriaDao.eliminar(categoria);
	}

	/**
	 * Buscar por descripcion
	 * @see com.factura.bo.CategoriaBo#buscarPorDescripcionYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return categoriaDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.CategoriaBo#buscar(java.lang.Integer)
	 */
	@Override
	public Categoria buscar(Long id) {
		return categoriaDao.buscar(id);
	}

	@Override
	public Collection<Categoria> findByEmpresaAll(Integer idEmpresa) {
		return categoriaDao.findByEmpresaAll(idEmpresa);
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregar(HttpServletRequest request, Categoria categoria, BindingResult result,Usuario usuario) {
		
		try {
			
			Categoria categoriaBd = buscarPorDescripcionYEmpresa(categoria.getDescripcion(), usuario.getEmpresa());
			if (categoriaBd != null) {
				result.rejectValue("descripcion", "error.categoria.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			categoria.setEmpresa(usuario.getEmpresa());
			categoria.setCreated_at(new Date());
			categoria.setUpdated_at(new Date());
			
			agregar(categoria);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.agregar.correctamente", categoria);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> modificar(HttpServletRequest request, Categoria categoria, BindingResult result,Usuario usuario) {
		try {
		if (result.hasErrors()) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("categoria.no.modificado", result.getAllErrors());
		}
		
		Categoria categoriaBD = buscar(categoria.getId());

		if (categoriaBD == null) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
		} else {
			Categoria categoriaValidar = null;
			if (!categoria.getDescripcion().equals(categoriaBD.getDescripcion())) {
				categoriaValidar = buscarPorDescripcionYEmpresa(categoria.getDescripcion(), usuario.getEmpresa());
				if (categoriaValidar != null) {
					result.rejectValue("descripcion", "error.categoria.descripcion.existe");
				}
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			categoriaBD.setDescripcion(categoria.getDescripcion());
			categoriaBD.setPrioridad(categoria.getPrioridad());
			categoriaBD.setUpdated_at(new Date());
			categoriaBD.setEstado(categoria.getEstado());
			modificar(categoriaBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.modificado.correctamente", categoriaBD);
		}

	} catch (Exception e) {
		return RespuestaServiceValidator.ERROR(e);
	}
	}

	@Override
	public RespuestaServiceValidator<?> mostrar(HttpServletRequest request, Categoria categoria, BindingResult result) {
		try {
			CategoriaCommand categoriaCommand = new CategoriaCommand(buscar(categoria.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", categoriaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	

}