$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoTarifas()
}
/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoTarifas() {
	riot.mount('tarifa-crud');
}


