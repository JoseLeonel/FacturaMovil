<impentrada-salida>

<div class="modal fade imprimirModalEntradaSalidaDinero" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>Consecutivo#{salidaEntradaDinero.id}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirEntradaSalidaDinero} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir(F8)</a>
                <a href="#" class="boton-imprimir" id="boton-regresar" onclick = {__RegresarVentaImprimir}><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;Regresar(Esc)</a>
            </div>
            <section class="zona-impresion" id="imprimeEntradaSalidaDinero">
                <div class="forma-impresion">
                    <div class="ticket" > 
                        <table class = "forma-table" >
                           <thead>
                                <tr class = "forma-table">
                                    <th class="titulo">  </th>
                                    
                                </tr>
                            </thead>
                             <tbody>
                                <tr class="detalleTables">
                                    <td class="valorPro" > <span class="titulo">{titulo} #{salidaEntradaDinero.id} </span> </td>
                                </tr>
                                <tr class="detalleTables">
                                    <td class="valorPro" >  {$.i18n.prop("usuarioCaja.created_at")}: {salidaEntradaDinero.created_atSTR}</td>
                                </tr>

                                <tr class="detalleTables">
                                    <td class="valorPro" > <span class="titulo">{salidaEntradaDinero.usuarioResponsable.empresa.nombreComercial} </span> </td>
                                </tr>
                                <tr   class="detalleTables">
                                    <td class="valorPro" > <strong>Usuario:</strong>{salidaEntradaDinero.usuarioResponsable.nombreUsuario} </td>
                                </tr>
                            </tbody>
                            </table> 

                         <br>                                             
                        <table class = "forma-table" >
                           <thead>
                                <tr class = "forma-table">
                                    <th class="titulo">  </th>
                                    <th class="titulo"></th>
                                    <th class="titulo"> </th>
                                </tr>
                            </thead>
                             <tbody>
                                <tr  class="detalleTables">
                                    <td class="valorPro" > Descripcion</td>
                                </tr>
                                <tr   class="detalleTables">
                                    <td class="valorPro" > {salidaEntradaDinero.descripcion}</td>
                                </tr>

                                <tr  class="detalleTables">
                                    <td class="valorPro" > <h2>Total:</h2> </td>
                                    <td class="valorPro" ><h2>{salidaEntradaDinero.total}</h2></td>
                                </tr>
                            
                                 <tr class = "forma-table">
                                    <td><br><br><br><h3>Firma:___________________________</h3></td>
                                 </tr>


                            </tbody>
                            </table> 
                   </div>
                </div>
            </section>
            
        </div>
    </div>    

    </div>
  </div>
</div>


<style type="text/css">


  .fimarResponsable{
      padding: 6%;

  }
  .valorPro{
      font-size:16px!important;
      padding: 1%!important;;
  }
    .titulo{
        font-size: 20px!important;
        padding-right: 2%;
        font-weight: 900;

    }

  .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
        display:flex!important;
    }
    .elemen1{
        font-size:18px!important;
    }
    
    .wrap{
        max-width:1100;
        width:90%;
        margin:auto;
    }
    .wrap >h1{
        color: #494B4D;
        font-weight: 400;
        display: flex;
        flex-direction: column;
        text-align: center;
        margin: 15px 0px;
    }
    .wrap > h1:after{
        content: '';
        width: 100%;
        height: 1px;
        background: #C7C7C7;
	    margin: 20px 0;
    }
    .pantalla-imprimir{
        display: flex;
	    flex-wrap: wrap;
    }
    .botones-imprimir{
        display: flex;
	    flex-direction: column;
	    width: 20%;
    }
    .botones-imprimir .boton-imprimir{
        cursor: pointer;
        padding: 10px;
        margin: 15px;
        border: none;
        color: #fff;
        text-decoration:none;
        font-size: 18px;  
        padding-top:8px !important; 
        padding-bottom:8px !important; 
        margin-bottom: 8px;
        text-align: center !important;
        background-color: #6dca42 !important;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        border-radius: 30px;
        -webkit-transition: background-color 100ms linear;
        -moz-transition: background-color 100ms linear;
        -o-transition: background-color 100ms linear;
        -ms-transition: background-color 100ms linear;
        transition: background-color 100ms linear;
     }
   .ticket {
        height: auto;
        width: 377px;
        margin: 0px;
        padding: 0px;
        float: left;
        font-style: normal;
        line-height: normal;
        font-weight: normal;
        font-variant: normal;
        text-transform: none;
        color: #000;
        font-size: 14px;
        font-family: 'Times New Roman';
        
        max-width: 377px;
       
    }
    .ticket > table{
        border-top: 0px solid black;
        border-collapse: collapse;
    }
    .forma-table {
        border-collapse: collapse;
    }
    .ticket > td.producto,th.producto {
        width: 377px;
        max-width: 75px;
    }
    .ticket > td.cantidad,th.cantidad {
        width: 377px;
        word-break: break-all;
    }
    .ticket > td.precio,th.precio {
        width: 377px;
        word-break: break-all;
    }
    .encabezado {
        text-align: left;
        align-content: left;
    }
    .ticket > img {
        max-width: inherit;
        width: inherit;
    }
    @page{
       margin: 0;
       
    }
    
    .zona-impresion{
        width: 80%;
	    display: flex;
	    flex-wrap: wrap;
     }
     .zona-impresion .forma-impresion{
        width: 80%;
        margin-left: 1%;
        margin-bottom: 45px;
        box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.70);
        display: flex;
        flex-direction: column;
        align-items: center;
        align-self: flex-start;
        background-color: white !important;
        transition: all .4s;
     
    }
    .encabezado {
        text-align: left;
        align-content: left;
    }
    

</style>    

<script>

var selfImprimirSalida = this;
selfImprimirSalida.parametro = opts.parametros;  
selfImprimirSalida.salidaEntradaDinero = {
    id:null,
    total:0,
    descripcion:"",
    created_atSTR:""
}

selfImprimirSalida.titulo = "Entrada de Dinero"

selfImprimirSalida.on('mount',function(){

    selfImprimirSalida.salidaEntradaDinero  = selfImprimirSalida.parametro.salidaEntradaDinero
    if(selfImprimirSalida.salidaEntradaDinero.tipo == 2){
      selfImprimirSalida.titulo = "Salida de Dinero" 
    }
    selfImprimirSalida.update()
    $('.imprimirModalEntradaSalidaDinero').modal('show');
})
/**
*Imprimir factura
**/    
__ImprimirEntradaSalidaDinero(){
    var objeto=document.getElementById('imprimeEntradaSalidaDinero');  //obtenemos el objeto a imprimir
    var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
    ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
    ventana.document.close();  //cerramos el documento
    ventana.print();  //imprimimos la ventana
    ventana.close();  //cerramos la ventana
    $("#boton-regresar").focus()
}
</script>
</impentrada-salida>