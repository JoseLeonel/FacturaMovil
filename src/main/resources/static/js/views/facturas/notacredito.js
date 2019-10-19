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
		codigoMoneda :'CRC'
	}
	riot.mount('nota-credito',{parametros:parametros});

}

