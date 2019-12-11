$(document).ready(function() {
	_Init();
} );/*fin document*/

var _Init = function () {
	consultaControlPago();
	cargaMantenimientoCategorias()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoCategorias() {

	riot.mount('venta-servicios');
}



