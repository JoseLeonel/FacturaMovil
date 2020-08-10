package com.emprendesoftcr.web.command;

import java.util.ArrayList;
import java.util.List;

public class ClienteMagList {
  private List<ClienteMag>  listaDatosMAG;
  

	
	public ClienteMagList() {
		super();
		listaDatosMAG = new ArrayList<>();
	}


	public List<ClienteMag> getListaDatosMAG() {
		return listaDatosMAG;
	}

	
	public void setListaDatosMAG(List<ClienteMag> listaDatosMAG) {
		this.listaDatosMAG = listaDatosMAG;
	}
  
}
