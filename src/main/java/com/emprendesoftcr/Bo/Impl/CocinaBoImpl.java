package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CocinaBo;
import com.emprendesoftcr.Dao.CocinaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Cocina;
import com.emprendesoftcr.modelo.CocinaComanda;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.ComandaCocinaCommand;
import com.emprendesoftcr.web.command.ComandaNuevaCommand;
import com.google.gson.Gson;

@EnableTransactionManagement
@Service("cocinaBo")
public class CocinaBoImpl implements CocinaBo {

	@Autowired
	CocinaDao cocinaDao;
	
	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	
	@Override
	public void agregar(Cocina cocina) {
		cocinaDao.agregar(cocina);

	}

	@Override
	public void modificar(Cocina cocina) {
		cocinaDao.modificar(cocina);

	}

	@Override
	public void eliminar(Cocina cocina) {
		cocinaDao.eliminar(cocina);

	}

	@Override
	public Cocina buscar(Long id) {
		
		return cocinaDao.buscar(id);
	}

	@Override
	public void agregarNuevaCocinaDia(UsuarioCaja usuarioCaja) {
				Cocina cocina  = new Cocina();
				cocina.setCreated_at(new Date());
				cocina.setEstado(Constantes.COCINA_ACTIVO);
				cocina.setUpdated_at(new Date());;
				cocina.setIdUsuarioCaja(usuarioCaja.getId());
				cocina.setEmpresa(usuarioCaja.getCaja().getEmpresa());
	}

	@Override
	public void agregarNuevaComandaDia(Cocina cocina, ArrayList<ComandaCocinaCommand> comandaCocinaCommands ) throws Exception {
		
		
		
		
		for (ComandaCocinaCommand comandaCocinaCommand :comandaCocinaCommands) {
			CocinaComanda cocinaComanda = new CocinaComanda();
			cocinaComanda.setCocina(cocina);
			cocinaComanda.setCodigo(comandaCocinaCommand.getCodigo());
			cocinaComanda.setComentario(comandaCocinaCommand.getComentario());
			cocinaComanda.setDescripcion(comandaCocinaCommand.getDescripcion());
			cocinaComanda.setConsecutivofactura(comandaCocinaCommand.getCodigo());
			cocinaComanda.setEstado(Constantes.COMMANDA_COCINA_ACTIVO);
			cocinaComanda.setIdArticulo(comandaCocinaCommand.getIdArticulo());
			cocinaComanda.setIdMesa(comandaCocinaCommand.getIdMesa());
			cocinaComanda.setTotal(comandaCocinaCommand.getTotal());
			
			cocina.addComanda(cocinaComanda);
		}
		
	}
	
	private ArrayList<ComandaCocinaCommand> formaCommandCocina(ComandaNuevaCommand comandaNuevaCommand) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front
		JSONObject json = null;
		ArrayList<ComandaCocinaCommand> comandaCocinaCommands = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(comandaNuevaCommand.getDetalles());
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					ComandaCocinaCommand comandaCocinaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), ComandaCocinaCommand.class);
					
					comandaCocinaCommands.add(comandaCocinaCommand);
					
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			log.error(String.format("--error formaDetallesCommand :" + e.getMessage() + new Date()));
			throw e;
		
		}
		return comandaCocinaCommands;
	}

	@Override
	public Cocina buscarCocinaActiva(UsuarioCaja usuarioCaja) {
		return cocinaDao.buscarCocinaActiva(usuarioCaja);
	}

}
