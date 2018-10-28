package com.emprendesoftcr.service;

import com.emprendesoftcr.fisco.OpenIDConnectHacienda;

public interface  OpenIDConnectHaciendaService {
	
	//Realiza la conexion con hacienda para obtener el token
	OpenIDConnectHacienda findbyAcceso(String usuario , String clave) throws Exception;;

}
