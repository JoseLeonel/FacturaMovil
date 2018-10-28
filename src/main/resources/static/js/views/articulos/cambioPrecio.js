$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoCategorias()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoCategorias() {

	
	riot.mount('cambiar-precio');
}


