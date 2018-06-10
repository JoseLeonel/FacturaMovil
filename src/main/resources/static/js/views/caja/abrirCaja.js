$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoCajas()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoCajas() {

	
	riot.mount('abrir-caja');
}



