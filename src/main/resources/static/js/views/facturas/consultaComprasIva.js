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
		url:"listarConsutaComprasIvaAjax.do",
		titulo:"Compras"
	}
    riot.mount('consulta-iva',{parametros:parametros});   
	
	
}


