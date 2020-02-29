$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	__MantenimientoAgregar();
}

 /* Mostrar formulario de mantenimiento Agregar
 * */
function __MantenimientoAgregar(){
	riot.compile(function() {
		var parametros = {
			tipoEjecucion:2,
			data:null
		};
		 // here tags are compiled and riot.mount works synchronously
		var tags = riot.mount('cliente-nuevo',{parametros:parametros});
	});   
}

