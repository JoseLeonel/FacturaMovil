$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	cargaMantenimientoUsuarios()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoUsuarios() {
  riot.mount('crud-Usuario');
}



