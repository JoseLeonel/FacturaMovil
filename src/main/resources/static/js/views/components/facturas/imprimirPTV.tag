<ptv-imprimir>

<div id="imprimirModal" class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="imprimirModal" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1 >{titulo}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirfacturaImpresa} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                
            </div>
            <section class="zona-impresion" id="imprimeme" name ="imprimeme">
                <div class="forma-impresion">
                    <div class="ticket" id="ticket" name="ticket" > 
                        <div class="encabezado" show = "{facturaImpresa.tipoDoc == '88'}"><strong> {$.i18n.prop("tikect.encabezado.proforma")} {facturaImpresa.id}                       </strong><br></div>
                        <div class="encabezado" show = "{facturaImpresa.tipoDoc == '87'}"><strong> {$.i18n.prop("factura.tipo.documento.factura.tiquete.uso.interno")} {facturaImpresa.id}                       </strong><br></div>
                        <div class="encabezado"><strong> {facturaImpresa.empresa.nombreComercial}                        </strong><br></div>
                        <div class="encabezado"><strong> {facturaImpresa.empresa.nombre}                        </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.cedula")}       </strong>{facturaImpresa.empresa.cedula}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.telefono")}     </strong>{facturaImpresa.empresa.telefono}<br></div>
                        <div class="encabezado">{facturaImpresa.empresa.otraSenas}                        <br></div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.fecha.emision")} </strong>{facturaImpresa.fechaEmisionSTR}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.condicion.venta")} </strong>{facturaImpresa.condicionVenta}</div>
                        <div class="encabezado" show ="{facturaImpresa.plazoCredito > 0}"><strong>{$.i18n.prop("tikect.encabezado.plazo.credito")} </strong>{facturaImpresa.plazoCredito}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>{facturaImpresa.tipoDoc}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.moneda")}        </strong>{facturaImpresa.codigoMoneda}</div>
                        <div class="encabezado" show = "{facturaImpresa.tipoDoc != '88'}"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{facturaImpresa.numeroConsecutivo}</div>
                        <div class=" encabezado" show = "{facturaImpresa.tipoDoc != '88'}"><strong>{$.i18n.prop("tikect.encabezado.clave")}</strong> </div>
                        <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88'}">{claveParteUno}</div>
                        <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88'}">{claveParteDos}</div>
                        <div class="encabezado" show ="{facturaImpresa.nombreFactura != ""}"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.nombreFactura}</div>
                        <div class="encabezado" show ="{facturaImpresa.nombreFactura ==null || facturaImpresa.nombreFactura == "" }"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.cliente.nombreCompleto}</div>
                        <div class="encabezado" show ="{facturaImpresa.nombreFactura ==null || facturaImpresa.nombreFactura == ""}"><strong>{$.i18n.prop("tikect.encabezado.receptor.cedula")}     </strong>{facturaImpresa.cliente.cedula}</div>
                        <table class = "forma-table"  >
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
                            </tr>
                            <tr>
                            <td colspan="3"></td>
                            </tr>
                            <tr class = "forma-table">
                            <td>*********</td>
                            <td >{$.i18n.prop("tikect.ultima.linea")}</td>
                            <td > *********  </td>
                            </tr>
                            <tr >
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.venta")}</strong></td>
                            <td ><strong>{facturaImpresa.totalVenta } </strong>  </td>
                            </tr>

                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.descuento")}</strong></td>
                            <td ><strong>{facturaImpresa.totalDescuentos}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td><strong>{$.i18n.prop("tikect.total.venta.neta")}</strong></td>
                            <td><strong>{facturaImpresa.totalVentaNeta}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.impuesto")}</strong></td>
                            <td ><strong>{facturaImpresa.totalImpuesto}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.exento")}</strong></td>
                            <td ><strong>{facturaImpresa.totalExento}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.comprobante")}</strong></td>
                            <td ><strong>{facturaImpresa.totalComprobante}</strong></td>
                            </tr>

                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.totalCambioPagar")}</strong></td>
                            <td ><strong>{facturaImpresa.totalCambioPagar}</strong></td>
                            </tr>                            
                                                 

                            <tr>
                            <td colspan="3"><div id="divQR" name="divQR"  class="divQR"></div></td>
                            </tr>
                            
                        </tbody>
                        </table> 
                        
                        <br>
                        <p  align="left" show = "{facturaImpresa.estado !=3 && facturaImpresa.estado !=4}">{$.i18n.prop("tikect.autorizado.parte.uno")}  <br>
                                         {$.i18n.prop("tikect.autorizado.parte.dos")}   
                                            <br>{$.i18n.prop("tikect.autorizado.parte.tres")}
                                            <br>{$.i18n.prop("tikect.autorizado.parte.cuatro")}</p>
                        <br>
                        <div class="encabezado">{$.i18n.prop("tikect.final")}</div>           
                    </div>
                </div>
            </section>
            
        </div>
    </div>    
    <div class="modal-footer">
              <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
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
    .tamanoClave{
        font-size: 10px;
    }
  
    .ticket
    {
        color: #000;
        float: left;
        font-family: "Times New Roman", Times, serif;
        font-size: 12px;
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
    body {
		background: #fff;
		color: #000;
	}   
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
self.facturaImpresa   = opts.factura;  
self.detalles = []
self.subTotalGeneral = 0
self.titulo = ""
self.claveParteUno =""
self.claveParteDos =""

self.on('mount',function(){
    
    if(self.facturaImpresa.id > 0){


       consultaFactura(self.facturaImpresa.id)
    }
   
   
   

})

function consultaFactura(idFactura){

     $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:idFactura},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                    self.facturaImpresa = modeloTabla
                    self.update()
                    self.detalles = []
                    self.detalles =self.facturaImpresa.detalles
                   
                    self.facturaImpresa.totalVenta       = formatoDecimales(self.facturaImpresa.totalVenta,2);
                    self.facturaImpresa.totalDescuentos  = formatoDecimales(self.facturaImpresa.totalDescuentos,2);
                    self.facturaImpresa.totalVentaNeta   = formatoDecimales(self.facturaImpresa.totalVentaNeta,2);
                    self.facturaImpresa.totalImpuesto    = formatoDecimales(self.facturaImpresa.totalImpuesto,2);
                    self.facturaImpresa.totalExento      = formatoDecimales(self.facturaImpresa.totalExento,2);
                    self.facturaImpresa.totalComprobante = formatoDecimales(self.facturaImpresa.totalComprobante,2);
                    self.facturaImpresa.totalCambioPagar = formatoDecimales(self.facturaImpresa.totalCambioPagar,2);
                    self.claveParteUno= self.facturaImpresa.clave.substring(0,24)
                    self.claveParteDos= self.facturaImpresa.clave.substring(25,51)
                    //detalles
                    self.facturaImpresa.detalles.forEach(function(elemen){
                        elemen.montoTotalLinea = formatoDecimales(elemen.montoTotalLinea,2);
                    })
                    self.update()
                    getSubTotalGeneral()
                    getMoneda()
                    __ComboTipoDocumentos()
                    buscarTipoDocumento()
                    __comboCondicionPago()
                    buscarCondicionPago()
                    if(self.facturaImpresa.estado ==2){
                        self.titulo = $.i18n.prop("tikect.encabezado.numeroFactura") + self.facturaImpresa.numeroConsecutivo
                    }
                    if(self.facturaImpresa.estado ==3){
                        self.titulo = $.i18n.prop("tikect.encabezado.proforma") + self.facturaImpresa.id
                    }
                    if(self.facturaImpresa.estado ==4){
                        self.titulo = $.i18n.prop("factura.tipo.documento.factura.tiquete.uso.interno") + self.facturaImpresa.id
                    }
                    self.update()
                  
                    });
                     $('.imprimirModal').modal('show'); 
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
     
}



function getMoneda() {
	var resultado = "CRC-Colones Costa Rica";
	if(self.facturaImpresa.codigoMoneda == "CRC") {
		resultado = "CRC-Colones Costa Rica";
	}else if(self.facturaImpresa.codigoMoneda == "USD") {
		resultado = "USD-Dolares";
	}
	
    self.facturaImpresa.codigoMoneda = resultado
    self.update()
}

function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.facturaImpresa.subTotal) + __valorNumerico(self.facturaImpresa.totalDescuentos)
    self.subTotalGeneral = redondearDecimales(resultado,5)
    self.update()
}




/**
*Imprimir facturaImpresa
**/    
__ImprimirfacturaImpresa(){
    __imprimir()
    $("#boton-regresar").focus()
    $('.codigo').select()
    $(".codigo").focus() 
}

/**
 * Buscar la condicion de Pago
 * **/
function buscarCondicionPago(){
    for (var count = 0; count < self.comboCondicionPagos.length; count++) {
        if (self.comboCondicionPagos[count].estado == self.facturaImpresa.condicionVenta ){// Si existe actualiza la cantidad
            self.facturaImpresa.condicionVenta =self.comboCondicionPagos[count].descripcion
            self.update()
            break;
        }
    }

}

/**
* cargar los estados de la factura
**/
function __comboCondicionPago(){
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
function buscarTipoDocumento(){
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
function __ComboTipoDocumentos(){
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
     self.comboTipoDocumentos.push({
         estado:"87",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete.uso.interno")
    })
   
    self.update()
}

/**
*imprimir
**/
function __imprimir(){
    var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
     var div = document.querySelector("#imprimeme");
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
</ptv-imprimir>