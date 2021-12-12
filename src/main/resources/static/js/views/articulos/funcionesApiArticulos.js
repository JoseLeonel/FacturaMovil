
    /***
     * consulta un producto
     */
const obtenerControlPrecio = async(fechaInicial,fechaFinal,idUsuario,idArticulo) => {
        try {
        
            const rawResponse = await fetch('/api/articulo/listaControlPrecioPendienteAceptar' );
            const data = await rawResponse.json();
           
       	    blockUILoad();
            console.log(data.aaData);
            return data.aaData;
        } catch (error) {
        	unBlockUIStop();
            console.log(error);



        }

    }
 




