$(document).ready(function() {
	_Init();
//	minimizarMenu()
		//__MensajesToasControlPago()
	consultaControlPago();
	
} );/*fin document*/

var _Init = function () {
	__TipoCambio();  // obtiene tipo de cambio de la empresa como recibe el dolar
	//getTipoCambioDolar();// obtiene el tipo cambio del banco central
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

