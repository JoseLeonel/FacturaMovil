   /***
     * consulta un producto
     */
const obtenerArticulo = async(idArticulo,idProveedor) => {
        try {
        
            const rawResponse = await fetch('findArticuloProveedorByCodigojax.do?codigoArticulo=' + idArticulo+'&idProveedor='+idProveedor);
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

const obtenerConsecutivoCompra = async(consecutivo) => {
    try {
    
        const rawResponse = await fetch('findConsecutivoCompra.do?consecutivo=' + consecutivo);
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