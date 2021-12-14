
    /***
     * consulta un producto
     */
const obtenerControlPrecio = async(fechaInicial,fechaFinal,codigoArticulo) => {
        try {
        
            const rawResponse = await fetch('/api/articulo/listaControlPrecioPendienteAceptar?fechaInicioParam='+fechaInicial+'&fechaFinParam='+fechaFinal+'&codigoArticulo='+codigoArticulo );
            const data = await rawResponse.json();
           
       	    blockUILoad();
            console.log(data.aaData);
            return data.aaData;
        } catch (error) {
        	unBlockUIStop();
            console.log(error);



        }

    }
 




