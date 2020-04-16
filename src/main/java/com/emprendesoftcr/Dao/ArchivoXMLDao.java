package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ArchivoXML;
import com.emprendesoftcr.modelo.Empresa;

public interface ArchivoXMLDao {
	
	void agregar( ArchivoXML  archivoXML);

	void modificar(ArchivoXML archivoXML);

	void eliminar(ArchivoXML archivoXML);

	ArchivoXML findById(Long id);

	ArchivoXML findByIdFactura(Empresa empresa,Long idFactura);
	
	ArchivoXML findByClave(Empresa empresa,String clave);

}
