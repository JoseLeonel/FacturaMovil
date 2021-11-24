const obtenerVentasEnEsperaApi = async() => {
        try {
        	
            const resGet = await fetch('ListarFacturasEsperaActivasAjax')
            const resGetJason = await resGet.json();
            blockUILoad();
            return resGetJason.aaData;
        } catch (error) {
            console.log(error);
        }


    }
    /***
     * consulta un producto
     */
const obtenerArticulo = async(idArticulo) => {
        try {
        
            const rawResponse = await fetch('/api/articulo/findInventarioArticuloByCodigojax.do?codigoArticulo=' + idArticulo);
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
    /**
     * Retorna las facturas del dia del cajero
     * @returns 
     */

const getFacturasDelDia = async() => {

        try {
        	
            const rawResponse = await fetch('/api/factura/ListarFacturasDelDiaAjax.do');
            const data = await rawResponse.json();
            console.log(data.aaData);
            blockUILoad();
            return data.aaData;
            
        } catch (error) {
        	unBlockUIStop();
            console.log(error);
        }


    }
  