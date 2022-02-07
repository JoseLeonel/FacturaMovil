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

	
	riot.mount('ajuste-Inventario');
}


const obtenerArticuloInventario = async(codigoArticulo) => {
    try {
    
        const rawResponse = await fetch('findArticuloByCodigojax.do?codigoArticulo=' + codigoArticulo);
        const data = await rawResponse.json();
        var articuloTem = null
        $.each(data.listaObjetos, function(index, modeloTabla) {

            articuloTem = modeloTabla;
        });
   	    blockUILoad();
        console.log(articuloTem);
        return articuloTem;
    } catch (error) {
    	unBlockUIStop();
        console.log(error);



    }

}