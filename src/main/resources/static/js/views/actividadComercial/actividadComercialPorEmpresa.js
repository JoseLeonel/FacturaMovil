$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoActividadComercialPorEmpresa()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoActividadComercialPorEmpresa() {

	
	riot.mount('actividad-empresa');
}

