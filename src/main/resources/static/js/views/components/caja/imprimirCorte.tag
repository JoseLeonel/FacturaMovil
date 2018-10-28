<imprimir-caja>

<div class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>{$.i18n.prop("imprimir.caja.titulo")}#{usuarioCaja.id}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirFactura} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir(F8)</a>
                <a href="#" class="boton-imprimir" id="boton-regresar" onclick = {__RegresarVentaImprimir}><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;Regresar(Esc)</a>
            </div>
            <section class="zona-impresion" id="imprimeme">
                <div class="forma-impresion">
                    <div class="ticket" > 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.titulo")}     </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("usuarioCaja.created_at")}      </strong>{usuarioCaja.created_atSTR}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("usuarioCaja.updated_at")}      </strong>{usuarioCaja.updated_atSTR}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.usuario")}       </strong>{usuarioCaja.usuario.nombre} {usuarioCaja.usuario.primerApellido} {usuarioCaja.usuario.segundoApellido}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.caja")}          </strong>{usuarioCaja.caja.descripcion}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.fondoIncial")}  : </strong>{usuarioCaja.totalFondoInicialSTR}<br></div>                                                 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.totalEfectivo")}: </strong>{usuarioCaja.totalEfectivoSTR}<br></div>                                                 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.totalTarjeta")} : </strong>{usuarioCaja.totalTarjetaSTR}<br></div>                                                 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.totalBanco")}    : </strong>{usuarioCaja.totalBancoSTR}<br></div>                                                 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.totalAbono")} : </strong>{usuarioCaja.totalAbonoSTR}<br></div>                                                 
                        <div class="encabezado"><strong> {$.i18n.prop("imprimir.caja.totalNeto")}    : </strong>{usuarioCaja.totalNetoSTR}<br></div>                                                 
                        <br>
                       
                    </div>
                </div>
            </section>
            
        </div>
    </div>    

    </div>
  </div>
</div>


<style type="text/css">
  .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
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
        border-top:1px solid black;
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

var self = this;
self.usuarioCaja   = opts.usuarioCaja;  


self.on('mount',function(){
    if(self.usuarioCaja.id > 0){
        self.usuarioCaja.created_at = displayDate_detail(self.usuarioCaja.created_at)
        self.usuarioCaja.updated_at = displayDate_detail(self.usuarioCaja.updated_at)
        self.update()
       $('.imprimirModal').modal('show'); 
    }
    __Teclas()
   

})

/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
        var tecla = evento.keyCode; 
    if(tecla ==119){
        //Pantalla de imprimir F8 imprimir
        var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
        var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
        ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
        ventana.document.close();  //cerramos el documento
        ventana.print();  //imprimimos la ventana
        ventana.close();  //cerramos la ventana
         }    

    
    }, false );
  
   

}

function displayDate_detail(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

/**
*Imprimir factura
**/    
__ImprimirFactura(){
    var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
          var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
          ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
          ventana.document.close();  //cerramos el documento
          ventana.print();  //imprimimos la ventana
          ventana.close();  //cerramos la ventana

           $("#boton-regresar").focus()
}

</script>

</imprimir-caja>