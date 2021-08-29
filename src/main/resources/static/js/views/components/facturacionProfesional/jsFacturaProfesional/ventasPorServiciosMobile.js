$(document).ready(function() {
	_Init();
} );/*fin document*/

var _Init = function () {
	//factura.js
	minimizarMenu()

	consultaControlPago();
	cargaMantenimientoCategorias()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoCategorias() {

	riot.mount('ventasPorServiciosMobile');
}



