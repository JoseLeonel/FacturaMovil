$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	
	cargaCombos(function(callback){
		cargaMantenimientoCategorias();
		console.log(callback);
	});
	
}
var tipoUnidades = null;
var marcas = null;
var categorias = null;

function cargaCombos(callback){

	__listadoMarcaCombo(function (data){
	    marcas = data;
	    
	 });
	__listadoCategoriasCombo(function (data){
	    categorias = data
	    
	});
	__listadoTipoUnidadesCombo(function(data){
	    tipoUnidades  = data;
	      
	});
	callback("listo");
	
}
/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoCategorias() {
	var parametros = {
			tipoEjecucion:1,
			categorias:categorias,
			marcas:marcas,
			tipoUnidades:tipoUnidades
	};
	  // here tags are compiled and riot.mount works synchronously
  var tags = riot.mount('cambiar-precio',{parametros:parametros})
 
}


