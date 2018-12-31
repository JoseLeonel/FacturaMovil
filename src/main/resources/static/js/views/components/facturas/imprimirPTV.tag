<ptv-imprimir>

<div id="imprimirModal" class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="imprimirModal" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <div class="row">
            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                <h2 >{titulo}</h2>
                <div class="pantalla-imprimir">
                    <div class="botones-imprimir">
                        <a href="#" class="boton-imprimir"  onclick = {__ImprimirfacturaImpresa} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                    
                    </div>
                    <section class="" >
                        <div class="forma-impresion " id="imprimeme" name ="imprimeme">
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
                                <div class="encabezado" show ="{facturaImpresa.plazoCredito > 0}"><strong>{$.i18n.prop("tikect.encabezado.plazo.credito")} dias </strong>{facturaImpresa.plazoCredito}</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 0}"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>{facturaImpresa.tipoDoc}</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '02' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Nota Debito</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '03' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Nota Credito</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '01' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Factura</div>
                                <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.moneda")}        </strong>{facturaImpresa.codigoMoneda}</div>
                                <div class="encabezado" show = "{facturaImpresa.tipoDoc != '88' &&  facturaImpresa.numeroConsecutivo != ""}"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{facturaImpresa.numeroConsecutivo}</div>
                                <div class=" encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0  &&  facturaImpresa.clave != ""}"><strong>{$.i18n.prop("tikect.encabezado.clave")}</strong> </div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0 }">{claveParteUno}</div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0}">{claveParteDos}</div>
                                <div class="encabezado" show ="{facturaImpresa.nombreFactura != ""}"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.nombreFactura}</div>
                                <div class="encabezado" show ="{facturaImpresa.nombreFactura ==null || facturaImpresa.nombreFactura == "" }"><strong show={facturaImpresa.cliente.nombreCompleto != 'CLIENTE_FRECUENTE'}>{$.i18n.prop("tikect.encabezado.receptor")}     {facturaImpresa.cliente.nombreCompleto}</strong ></div>
                                <div class="encabezado" show ="{facturaImpresa.nombreFactura ==null || facturaImpresa.nombreFactura == ""}"><strong show={facturaImpresa.cliente.cedula != '999999999999'}>{$.i18n.prop("tikect.encabezado.receptor.cedula")}  {facturaImpresa.cliente.cedula}   </strong></div>
                                <div class="encabezado" show ='{ facturaImpresa.nota != "" && facturaImpresa.nota !=null}'> Nota:   {facturaImpresa.nota}  </div>
                                <table class = "forma-table">
                                    <thead>
                                        <tr class = "forma-table">
                                            <th class="cantidad">{$.i18n.prop("tikect.detalle.cantidad")}  </th>
                                            <th class="producto">{$.i18n.prop("tikect.detalle.descripcion")}</th>
                                            <th class="precio"> {$.i18n.prop("tikect.total.linea")} </th>

                                        </tr>
                                    </thead>
                                <tbody>
                                    <tr class = "" each={detalles} class="detalleTables">
                                        <td class="cantidad" show={codigo !='8888'}>{cantidad}</td>
                                        <td class="producto" show={codigo !='8888'}>{descripcion}</td>
                                        <td class="precio"   show={codigo !='8888'}>{montoTotalSTR} {montoImpuesto>0?"G":"E"}</td>
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
                                    <td ><strong>{$.i18n.prop("tikect.detalle.subTotal")}</strong></td>
                                    <td ><strong>{facturaImpresa.subTotalGeneralSTR } </strong>  </td>
                                    </tr>
                                    <tr show={totalImpuestoServicio > 0} >
                                    <td></td>
                                    <td ><strong>{$.i18n.prop("tikect.detalle.impuestoServicio")}</strong></td>
                                    <td ><strong>{totalImpuestoServicioSTR} </strong>  </td>
                                    </tr>

                                    <tr show={facturaImpresa.totalDescuentos >0}>
                                    <td></td>
                                    <td ><strong>{$.i18n.prop("tikect.total.descuento")}</strong></td>
                                    <td ><strong>{facturaImpresa.totalDescuentosSTR}</strong></td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td ><strong>{$.i18n.prop("tikect.total.impuesto")}</strong></td>
                                    <td ><strong>{facturaImpresa.totalImpuestoSTR}</strong></td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td ><h3><strong>{$.i18n.prop("tikect.total.final")}</strong></h3></td>
                                    <td ><h3><strong>{facturaImpresa.totalComprobanteSTR}</strong></h3></td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td ><strong>{$.i18n.prop("tikect.totalCambioPagar")}</strong></td>
                                    <td ><strong>{facturaImpresa.totalCambioPagarSTR}</strong></td>
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
                                </tbody>
                                </table> 
                                
                                <p  align="left" show = "{facturaImpresa.estado != 3 && facturaImpresa.estado != 4 && facturaImpresa.empresa.noFacturaElectronica == 0}">E=Excento G=Gravado  
                                
                                <p  align="left" show = "{facturaImpresa.estado != 3 && facturaImpresa.estado != 4 && facturaImpresa.empresa.noFacturaElectronica == 0}">{$.i18n.prop("tikect.autorizado.parte.uno")}  <br>
                                                {$.i18n.prop("tikect.autorizado.parte.dos")}   
                                                    <br>{$.i18n.prop("tikect.autorizado.parte.tres")}
                                                    <br>{$.i18n.prop("tikect.autorizado.parte.cuatro")}</p>
                                <p  align="left" show = "{facturaImpresa.estado != 3 && facturaImpresa.estado != 4 && facturaImpresa.empresa.noFacturaElectronica == 1}">{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.uno")}  <br>
                                                {$.i18n.prop("tikect.autorizado.no.factura.elec.parte.dos")}   
                                                    <br>{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.tres")}
                                                    <br>{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.cuatro")}</p>

                            
                                <div class="encabezado">{$.i18n.prop("tikect.final")}</div>
                                <div class="encabezado">{$.i18n.prop("emprendesoft.factura")}</div>
                                <div class="encabezado">{$.i18n.prop("emprendesoft.mensaje")}</div> 
                                <div class="encabezado">{$.i18n.prop("emprendesoft.correo")}</div>           
                            </div>
                        </div>
                    </section>
                    
                </div>
                </div>
                </div>
    </div>    
    <div class="modal-footer">
              <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
    </div>
    </div>
  </div>
</div>

 



<style type="text/css"  >
  
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
self.parametro   = opts.parametros;  
self.detalles = []
self.titulo = ""
self.claveParteUno =""
self.claveParteDos =""
self.totalImpuestoServicio = 0
self.subTotal = 0
self.facturaActiva = 0
self.facturaImpresa = {
    empresa:{
        imprimirDirecto:0
    }
}

self.on('mount',function(){
    document.getElementById('divQR').innerHTML = '';
    if(self.parametro.factura.id > 0){
       consultaFactura(self.parametro.factura.id)
       if(self.parametro.factura.empresa.noFacturaElectronica == 0){
           qr()    
        }
                    
    }
     
    
})




function qr(){
     var options = {
        // render method: 'canvas', 'image' or 'div'
        render: 'div',
        // version range somewhere in 1 .. 40
        minVersion: 1,
        maxVersion: 40,
        // error correction level: 'L', 'M', 'Q' or 'H'
        ecLevel: 'L',
        // offset in pixel if drawn onto existing canvas
        left: 0,
        top: 0,
        // size in pixel
        size: 100,
        // code color or image element
        fill: '#000',
        // background color or image element, null for transparent background
        background: null,
        // content
        text: self.parametro.factura.clave,
        // corner radius relative to module width: 0.0 .. 0.5
        radius: 0,

        // quiet zone in modules
        quiet: 0,

        // modes
        // 0: normal
        // 1: label strip
        // 2: label box
        // 3: image strip
        // 4: image box
        mode: 0,

        mSize: 0.1,
        mPosX: 0.5,
        mPosY: 0.5,

        label: self.parametro.factura.clave,
        fontname: 'sans',
        fontcolor: '#000',

        image: null
    }
   
   
    $('#divQR').qrcode(options);
}


/**
*consultar Facturas
**/
function consultaFactura(idFactura){
     self.facturaImpresa =null
     self.update()
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
                    console.log(self.facturaImpresa)
                    self.update()
                    self.detalles = []
                    self.detalles =self.facturaImpresa.detalles
                   
                    self.claveParteUno= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(0,24):""
                    self.claveParteDos= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(25,51):""
                    //detalles
                    self.totalImpuestoServicio = 0
                    self.subTotal = 0
                    self.update()
                    self.facturaImpresa.detalles.forEach(function(elemen){
                        if(elemen.codigo == "8888"){
                            self.totalImpuestoServicio = __valorNumerico(elemen.montoTotalLinea)
                            self.update()
                        }
                        elemen.montoTotal = redondearDecimales(elemen.montoTotal,0);
                         self.update()
                    })
                   self.totalImpuestoServicioSTR = ""
                   self.totalImpuestoServicioSTR =  formatoDecimales(self.totalImpuestoServicio,2)  
                   self.update()
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
                    if(self.facturaImpresa.estado == 4){
                        self.titulo = $.i18n.prop("factura.tipo.documento.factura.tiquete.uso.interno") + self.facturaImpresa.id
                    }
                    self.update()
                    });
                    if (self.facturaImpresa.empresa.imprimirDirecto == 0 || self.parametro.facturaDia ==1){
                        $('.imprimirModal').modal('show');   
                    }else{
                     //   __imprimir()
                    }
                     if (self.parametro.factura.empresa.imprimirDirecto == 1 && self.parametro.facturaDia ==0 ){
                      __imprimir()
                     }
                    
                    

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
        if (self.comboTipoDocumentos[count].estado == self.facturaImpresa.tipoDoc ){
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
//window.print();
//return
    // var canvas=document.getElementById("leo");
    
  
  var ventana = window.open('', 'PRINT', 'height=400,width=600');
  var html = "<!DOCTYPE HTML>";
  html += '<html><head><title>' + "" + '</title>'
  html += '</head><body id="imprimirLaFactura" >'
  ventana.document.write(html);
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