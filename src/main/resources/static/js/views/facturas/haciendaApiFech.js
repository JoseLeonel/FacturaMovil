const getListaEnviosHacienda = async(fechaInicio, fechaFin, cedulaReceptor, tipoDocumento) => {
    try {

        const rawResponse = await fetch('/api/hacienda/ListarHaciendasAjax.do?fechaInicio=' + fechaInicio + '&&fechaFin=' + fechaFin + '&&cedulaReceptor=' + cedulaReceptor + '&&tipoDocumento=' + tipoDocumento);
        const data = await rawResponse.json();
        console.log(data.aaData);
        return data.aaData;
    } catch (error) {
        console.log(error);



    }

}