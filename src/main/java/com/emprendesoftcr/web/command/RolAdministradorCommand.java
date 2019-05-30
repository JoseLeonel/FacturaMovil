package com.emprendesoftcr.web.command;


public class RolAdministradorCommand {
	
	private Integer rolAdministrador;

	public RolAdministradorCommand(Integer rolAdministrador) {
		super();
		this.rolAdministrador = rolAdministrador;
	}

	
	public RolAdministradorCommand() {
		super();
	}


	public Integer getRolAdministrador() {
		return rolAdministrador;
	}

	
	public void setRolAdministrador(Integer rolAdministrador) {
		this.rolAdministrador = rolAdministrador;
	}
	
	
	

}
