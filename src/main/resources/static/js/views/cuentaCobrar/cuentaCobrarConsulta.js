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
	//1 = Actualizacion 2 =Consulta

	riot.mount('cuenta-cobrar',{idTransaccion:2});
}


