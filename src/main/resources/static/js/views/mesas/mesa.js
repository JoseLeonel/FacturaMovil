$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoMarcas()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoMarcas() {

	
	riot.mount('mesa-crud');
}


