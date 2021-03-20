package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.Dao.CabysDao;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.web.command.CabysHaciendaCommand;
import com.emprendesoftcr.web.command.ListCabysHacienda;
import com.google.gson.Gson;

/**
 * categorias se va dividir los articulos de una empresa CategoriaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("cabysBo")
public class CabysBoImpl implements CabysBo {

	@Autowired
	private CabysDao cabysDao;

	
	private Logger	log	= LoggerFactory.getLogger(this.getClass());
	@Transactional
	@Override
	public void agregar(Cabys cabys) {
		cabysDao.agregar(cabys);
	}

	@Override
	@Transactional
	public void modificar(Cabys cabys) {
		cabysDao.modificar(cabys);
	}

	@Override
	@Transactional
	public void eliminar(Cabys cabys) {
		cabysDao.eliminar(cabys);
	}

	@Override
	public Cabys buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return cabysDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	@Override
	public Cabys buscar(Long id) {
		return cabysDao.buscar(id);
	}

	@Override
	public Collection<Cabys> findByEmpresaAll(Integer idEmpresa) {
		return cabysDao.findByEmpresaAll(idEmpresa);
	}
	
	@Override
	public ListCabysHacienda obtieneListaCabysHacienda(String descripcion,String codigo, Integer cantidad) {
	ListCabysHacienda lista = new ListCabysHacienda();
	List<CabysHaciendaCommand> listaCodigos = new  ArrayList<CabysHaciendaCommand>();
		try {
			descripcion = descripcion == null?Constantes.EMPTY : descripcion;
			codigo = codigo == null? Constantes.EMPTY:codigo;
			Boolean aplicarURLPorDescripcion = Boolean.FALSE;
			if(!descripcion.equals(Constantes.EMPTY)) {
				aplicarURLPorDescripcion = Boolean.TRUE;
			}
			String url = Constantes.EMPTY;
			cantidad = cantidad != null ? cantidad:Constantes.ZEROS;
			if(aplicarURLPorDescripcion) {
				 url = cantidad.equals(Constantes.ZEROS)  ? Constantes.API_LISTA_CABYS_SIN_CANTIDAD + descripcion : Constantes.API_LISTA_CABYS_SIN_CANTIDAD + descripcion + "&top="+cantidad;
			}else {
				 url = cantidad.equals(Constantes.ZEROS)  ? Constantes.API_LISTA_CABYS_SIN_CODIGO + codigo : Constantes.API_LISTA_CABYS_SIN_CODIGO + codigo + "&top="+cantidad;
			}
			// create an instance of RestTemplate
			RestTemplate restTemplate = new RestTemplate();
			// make an HTTP GET request
			Object[] forNow;
			if(aplicarURLPorDescripcion) {
				lista = restTemplate.getForObject(url, ListCabysHacienda.class);	
			}else {
				Gson gson = new Gson();
				forNow = restTemplate.getForObject(url, Object[].class);
				Double total = 0d;
				for (int i = 0; i < forNow.length; i++) {
					Object object = forNow[i];
					String JSON = gson.toJson(object);
					total ++;
					
					CabysHaciendaCommand listCabysCodigo = gson.fromJson(JSON, CabysHaciendaCommand.class);
					listaCodigos.add(listCabysCodigo);
					
				}
				lista.setCabys(listaCodigos);
				lista.setTotal(total);
				
			
				
				
			//	listaPorCodigo = restTemplate.getForObject(url, ListCabysCodigo[].class);
			}
			
			System.out.println(lista.toString());

		} catch (Exception e) {
			log.error(String.format("--error consultar APi de hacienda de cabys :" + e.getMessage() + new Date()));
			throw e;

		}
		return lista;
	}

	@Override
	public Cabys findByCodigo(String codigo, Empresa empresa) {
		// TODO Auto-generated method stub
		return cabysDao.findByCodigo(codigo, empresa);
	}

}