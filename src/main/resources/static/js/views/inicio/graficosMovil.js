$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	getTipoCambioDolar();
	cargaMantenimientoMarcas()
	
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoMarcas() {

	
	riot.mount('grafico-inicioMovil');
}