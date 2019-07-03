$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoActividadComercial()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoActividadComercial() {

	
	riot.mount('actividad-comercial');
}

