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
		url:"listarConsutaIvaAjax.do",
		titulo:"Ventas"
	}
    riot.mount('consulta-iva',{parametros:parametros});   
	
	
}


