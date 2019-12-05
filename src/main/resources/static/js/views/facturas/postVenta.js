$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimiento();
	consultaControlPago();
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento() {

	
	riot.mount('venta-factura');
}


