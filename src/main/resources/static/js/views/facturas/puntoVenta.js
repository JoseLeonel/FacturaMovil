$(document).ready(function() {
	_Init();
//	minimizarMenu()
		//__MensajesToasControlPago()
	consultaControlPago();
	
} );/*fin document*/

var _Init = function () {
	
	cargaMantenimiento();
}
/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento() {
	var parametros = {
		codigoMoneda :'CRC'
	}
	riot.mount('punto-venta',{parametros:parametros});

}

