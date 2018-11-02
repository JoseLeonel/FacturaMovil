<tiquete-imprimir>

<div class="modal fade imprimirModalTiquete" tabindex="-1" role="dialog" aria-labelledby="imprimirModalTiquete" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1 >{$.i18n.prop("tikect.encabezado.comprobante")} {facturaImpresa.id}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirTiq} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                
            </div>
            <section class="zona-impresion" id="imprimemeTempo" name ="imprimemeTempo">
                <div class="forma-impresion">
                    <div class="ticket" id="ticket" name="ticket" > 
                        <h1 >{$.i18n.prop("tikect.encabezado.comprobante")} {facturaImpresa.id}<h1>
                        <div class="encabezado" show ="{facturaImpresa.nombreFactura !=null}"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.nombreFactura}</div>
  
                        <table class="forma-table" >
                            <thead>
                                <tr class = "forma-table">
                                    <th class="cantidad">{$.i18n.prop("tikect.detalle.cantidad")}  </th>
                                    <th class="producto">{$.i18n.prop("tikect.detalle.descripcion")}</th>
                                    <th class="precio"> {$.i18n.prop("tikect.total.linea")}</th>
                                </tr>
                            </thead>
	                        <tbody>
	                            <tr class = "" each={detalles} class="detalleTables">
	                                <td class="cantidad">{cantidad}</td>
	                                <td class="producto">{descripcion}</td>
	                                <td class="precio">{montoTotalLinea}</td>
	                            </tr>
	                            <tr>
		                            <td colspan="3"></td>
	                            </tr>
	                            <tr class = "forma-table">
	                            	<td>*********</td>
	                            	<td >{$.i18n.prop("tikect.ultima.linea")}</td>
	                            	<td > *********  </td>
	                            </tr>
	                            <tr>
	                            	<td class="text-right" colspan="2"><strong>{$.i18n.prop("tikect.total")}</strong></td>
	                            	<td colspan="1"><strong>{totalComprobanteImp}</strong></td>
	  
                                </tr>
	                            <tr>
		                            <td colspan="3"><div id="divQR" name="divQR"  class="divQR"></div></td>
	                            </tr>                        
	                            <tr>
		                            <td colspan="3"><div id="divQR" name="divQR"  class="divQR"></div></td>
	                            </tr>                        
	  
                                <tr>
		                            <td colspan="3"><div id="divQR" name="divQR"  class="divQR"></div></td>
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
    .fondoEncabezado
    {
        background: #00539B;
        color: #f9fafc;
    }
    .wrap
    {
        margin: auto;
        max-width: 1100;
        width: 90%;
    }
    .wrap >h1
    {
        color: #494B4D;
        display: flex;
        flex-direction: column;
        font-weight: 400;
        margin: 15px 0px;
        text-align: center;
    }
    .wrap > h1:after
    {
        background: #C7C7C7;
        content: '';
        height: 1px;
        margin: 20px 0;
        width: 100%;
    }
    .pantalla-imprimir
    {
        display: flex;
        flex-wrap: wrap;
    }
    .botones-imprimir
    {
        display: flex;
        flex-direction: column;
        width: 20%;
    }
    .botones-imprimir .boton-imprimir
    {
        background-color: #6dca42 !important;
        border: none;
        border-radius: 30px;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        color: #fff;
        cursor: pointer;
        font-size: 18px;
        margin: 15px;
        margin-bottom: 8px;
        moz-transition: background-color 100ms linear;
        ms-transition: background-color 100ms linear;
        o-transition: background-color 100ms linear;
        padding: 10px;
        padding-bottom: 8px !important;
        padding-top: 8px !important;
        text-align: center !important;
        text-decoration: none;
        transition: background-color 100ms linear;
        webkit-transition: background-color 100ms linear;
    }

  
    .ticket
    {
        color: #000;
        float: left;
        font-family: 'Times New Roman';
        font-size: 14px;
        font-style: normal;
        font-variant: normal;
        font-weight: normal;
        height: auto;
        line-height: normal;
        margin: 0px;
        max-width: 377px;
        padding: 0px;
        text-transform: none;
        width: 377px;
    }
    .ticket > table
    {
        border-collapse: collapse;
        border-top: 0px solid black;
    }
    .forma-table
    {
        border-collapse: collapse;
        border-top: 1px solid black;
    }
    .ticket > td.producto,th.producto
    {
        max-width: 75px;
        width: 377px;
    }
    .ticket > td.cantidad,th.cantidad
    {
        width: 377px;
        word-break: break-all;
    }
    .ticket > td.precio,th.precio
    {
        width: 377px;
        word-break: break-all;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
    .ticket > img
    {
        max-width: inherit;
        width: inherit;
    }
    @page
    {
        margin: 0;
    }
    .zona-impresion
    {
        display: flex;
        flex-wrap: wrap;
        width: 80%;
    }
    .zona-impresion .forma-impresion
    {
        align-items: center;
        align-self: flex-start;
        background-color: white !important;
        box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.70);
        display: flex;
        flex-direction: column;
        margin-bottom: 45px;
        margin-left: 1%;
        transition: all .4s;
        width: 80%;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
  @media print {
* {
    -webkit-print-color-adjust: exact !important; /*Chrome, Safari */
    color-adjust: exact !important;  /*Firefox*/
  }
}
@media only print
{
    body * { display: none !important; }
    body:after { content: "Don't waste paper!"; }
}

</style>    
<script>

var self = this;
self.facturaImpresa   = opts.parametro;  
self.detalles = []
self.subTotalGeneralImp = 0
self.totalComprobanteImp = 0
self.on('mount',function(){
    
    if(self.facturaImpresa.id > 0){
        self.detalles = []
        self.detalles =self.facturaImpresa.detalles
        self.totalComprobanteImp = formatoDecimales(self.facturaImpresa.totalComprobante,2);
        self.detalles.forEach(function(elemen){
                console.log(elemen);
                elemen.montoTotalLinea = formatoDecimales(elemen.montoTotalLinea,2);
            }
        )
        self.update()
         getSubTotalGeneral()
  
        $('.imprimirModalTiquete').modal('show'); 
    }
   
  

   
   

})



function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.facturaImpresa.subTotal) + __valorNumerico(self.facturaImpresa.totalDescuentos)
    self.subTotalGeneralImp = redondearDecimales(resultado,5)
    self.update()
}


/**
*Imprimir facturaImpresa
**/    
__ImprimirTiq(){
    __imprimir()
    $("#boton-regresar").focus()
}


/**
*imprimir
**/
function __imprimir(){
    var objeto=document.getElementById('imprimemeTempo');  //obtenemos el objeto a imprimir
     var div = document.querySelector("#imprimemeTempo");
    imprimirElemento(div)

}


function imprimirElemento(elemento){
  var ventana = window.open('', 'PRINT', 'height=400,width=600');
  ventana.document.write('<html><head><title>' + "" + '</title>');
  ventana.document.write('</head><body >');
  ventana.document.write(elemento.innerHTML);
  ventana.document.write('</body></html>');
  ventana.document.close();
  ventana.focus();
  ventana.print();
  ventana.close();
  return true;
}



</script>
</tiquete-imprimir>