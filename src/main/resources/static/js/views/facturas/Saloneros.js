$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	//factura.js
	minimizarMenu()
	cargaMantenimiento();
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento() {

	
	riot.mount('venta-saloneros');
}


