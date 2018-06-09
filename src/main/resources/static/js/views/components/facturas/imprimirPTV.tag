<ptv-imprimir>

<div class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>{$.i18n.prop("tikect.titulo")} {factura.numeroConsecutivo}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirFactura} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir(F8)</a>
                <a href="#" class="boton-imprimir" id="boton-regresar" onclick = {__RegresarVentaImprimir}><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;Regresar(Esc)</a>
            </div>
            <section class="zona-impresion" id="imprimeme">
                <div class="forma-impresion">
                    <div class="ticket" > 
                        <div class="encabezado"><strong> {factura.empresa.nombre}                </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.cedula")}       </strong>{factura.empresa.cedula}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.telefono")}     </strong>{factura.empresa.telefono}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.celular")}      </strong>{factura.empresa.celular}<br></div>                                                 
                        <div class="encabezado"></strong>{factura.empresa.direccion}<br>                 </strong></div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{factura.numeroConsecutivo}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.fecha.emision")} </strong>{factura.fechaEmision}</div>
                        <table class = "forma-table"  >
                            <thead>
                                <tr class = "forma-table">
                                    <th class="cantidad">{$.i18n.prop("tikect.detalle.cantidad")}  </th>
                                    <th class="producto">{$.i18n.prop("tikect.detalle.descripcion")}</th>
                                    <th class="precio">  ₡{$.i18n.prop("tikect.detalle.subTotal")}</th>
                                </tr>
                            </thead>
                        <tbody>
                            <tr class = "" each={factura.detalles}>
                                <td class="cantidad">{cantidad}</td>
                                <td class="producto">{articulo.descripcion}</td>
                                <td class="precio">{montoTotalLinea.toLocaleString('de-DE')}</td>
                            </tr>
                            <tr class = "forma-table">
                            <td></td>
                            <td>{$.i18n.prop("tikect.detalle.subTotal")}</td>
                            <td>₡ {factura.subTotal.toLocaleString('de-DE') }  </td>
                            </tr>
                            <tr>
                            <td></td>
                            <td>{$.i18n.prop("tikect.detalle.descuento")}</td>
                            <td>₡ {factura.totalDescuento.toLocaleString('de-DE')}</td>
                            </tr>
                            <tr>
                            <td></td>
                            <td>{$.i18n.prop("tikect.detalle.impuesto")}</td>
                            <td>₡ {factura.totalImpuesto.toLocaleString('de-DE')}</td>
                            </tr>
                            <tr>
                            <td></td>
                            <td>{$.i18n.prop("tikect.detalle.total")}</td>
                            <td>₡ {factura.totalVentaNeta.toLocaleString('de-DE')}</td>
                            </tr>
                            <tr>
                            <td colspan="3"></td>
                            </tr>
                            
                        </tbody>
                        </table> 
                        <p  align="left">{$.i18n.prop("tikect.autorizado.parte.uno")}  <br>
                                         {$.i18n.prop("tikect.autorizado.parte.dos")}   
                                            <br>{$.i18n.prop("tikect.autorizado.parte.tres")}</p>
                        <br>
                        <div class="encabezado">{$.i18n.prop("tikect.final")}</div>           
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
self.factura   = opts.factura;  


self.on('mount',function(){
    if(self.factura.id > 0){
        self.factura.fechaEmision = displayDate_detail(self.factura.fechaEmision)
        self.update()
       $('.imprimirModal').modal('show'); 
    }
   

})

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
</ptv-imprimir>