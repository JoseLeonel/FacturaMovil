$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	
	cargaMantenimiento()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento() {
	var parametros = {
		codigoMoneda :'USD'
	}
	riot.mount('punto-venta',{parametros:parametros});

}


