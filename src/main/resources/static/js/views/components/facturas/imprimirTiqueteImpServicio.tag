<tiquete-imprimir>

<div class="modal fade imprimirModalTiquete" tabindex="-1" role="dialog" aria-labelledby="imprimirModalTiquete" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h2 >{$.i18n.prop("tikect.encabezado.comprobante")} {facturaImpresa.id}<h2>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#"  class="boton-imprimir" onclick = {__ImprimirTiqPrint} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                
            </div>
            <section class="zona-impresion" id="imprimemeTempo" name ="imprimemeTempo">
                <div class="">
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
	                            	<td class="text-left" colspan="2"><h2><strong>{$.i18n.prop("tikect.total")}:</strong></h2></td>
	                            	<td colspan="1"><h2><strong>{facturaImpresa.totalComprobanteSTR}</strong></h2></td>
	                            </tr>
                                <tr show={facturaImpresa.tipoCambio > 1}>
                                    <td></td>
                                    <td class="precio" ><strong>{$.i18n.prop("tipoCambio.cambioDolar")}</strong></td>
                                    <td class="precio" ><strong>{facturaImpresa.tipoCambioSTR}</strong></td>
                                    <br>
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
self.facturaImpresa = opts.factura;  
self.detalles = []
self.subTotalGeneralImp = 0
self.totalComprobanteImp = 0
self.on('mount',function(){
    if(self.facturaImpresa.id > 0){
        self.detalles = []
        self.detalles = self.facturaImpresa.detalles
        self.facturaImpresa.fechaEmision = displayDate_detailPrint(self.facturaImpresa.fechaEmision)
        self.update()
        if (self.facturaImpresa.empresa.imprimirDirecto == 0 ){
           $('.imprimirModalTiquete').modal('show');  
        }    
         
    }
    getSubTotalGeneralPrint()
    getMonedaPrint()
    __ComboTipoDocumentosPrint()
    buscarTipoDocumentoPrint()
    __comboCondicionPagoPrint()
    buscarCondicionPagoPrint()
    self.facturaImpresa.totalComprobanteSTR = formatoDecimales(self.facturaImpresa.totalComprobante,2);
    self.detalles.forEach(function(elemen){
        elemen.montoTotalLinea = formatoDecimales(elemen.montoTotalLinea,2);
    })
    self.update()
    if (self.facturaImpresa.empresa.imprimirDirecto == 1 ){
        __imprimirPrint()
    }
})
/**
*
**/
function getMonedaPrint() {
	var resultado = "CRC-Colones Costa Rica";
	if(self.facturaImpresa.codigoMoneda == "CRC") {
		resultado = "CRC-Colones Costa Rica";
	}else if(self.facturaImpresa.codigoMoneda == "USD") {
		resultado = "USD-Dolares";
	}
	
    self.facturaImpresa.codigoMoneda = resultado
    self.update()
}

function getSubTotalGeneralPrint(){
    var resultado = __valorNumerico(self.facturaImpresa.subTotal) + __valorNumerico(self.facturaImpresa.totalDescuentos)
    self.subTotalGeneralImp = redondearDecimales(resultado,5)
    self.update()
}
/**
*Formato de Fecha
**/
function displayDate_detailPrint(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}
/**
*Imprimir facturaImpresa
**/    
__ImprimirTiqPrint(){
    __imprimirPrint()
    $("#boton-regresar").focus()
}
/**
 * Buscar la condicion de Pago
 * **/
function buscarCondicionPagoPrint(){
    for (var count = 0; count < self.comboCondicionPagos.length; count++) {
        if (self.comboCondicionPagos[count].condicionVenta == self.facturaImpresa.condicionVenta ){// Si existe actualiza la cantidad
            self.facturaImpresa.condicionVenta =self.comboCondicionPagos[count].descripcion
            self.update()
            break;
        }
    }
}
/**
* cargar los estados de la factura
**/
function __comboCondicionPagoPrint(){
    self.comboCondicionPagos = []
    self.update()
    self.comboCondicionPagos.push({
        estado:"01",
        descripcion:$.i18n.prop("factura.codicion.venta.contado")
    })
    self.comboCondicionPagos.push({
        estado:"02",
        descripcion:$.i18n.prop("factura.codicion.venta.credito")
    })
    self.update()
}
/**
 * Buscar el tipo de documento
 * **/
function buscarTipoDocumentoPrint(){
    for (var count = 0; count < self.comboTipoDocumentos.length; count++) {
        if (self.comboTipoDocumentos[count].estado == self.facturaImpresa.tipoDoc ){// Si existe actualiza la cantidad
            self.facturaImpresa.tipoDoc =self.comboTipoDocumentos[count].descripcion
            self.update()
            break;
        }
    }
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentosPrint(){
    self.comboTipoDocumentos = []
    self.update()
    self.comboTipoDocumentos.push({
        estado:"04",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
    })
    self.comboTipoDocumentos.push({
         estado:"01",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
    })
    self.comboTipoDocumentos.push({
         estado:"02",
        descripcion:$.i18n.prop("factura.tipo.documento.nota.debito")
    })
    self.comboTipoDocumentos.push({
         estado:"03",
        descripcion:$.i18n.prop("factura.tipo.documento.nota.credito")
    })
     self.comboTipoDocumentos.push({
         estado:"88",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
    })
    self.update()
}
/**
*imprimir
**/
function __imprimirPrint(){
    var objeto=document.getElementById('imprimemeTempo');  //obtenemos el objeto a imprimir
     var div = document.querySelector("#imprimemeTempo");
    imprimirElementoPrint(div)

}


function imprimirElementoPrint(elemento){
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