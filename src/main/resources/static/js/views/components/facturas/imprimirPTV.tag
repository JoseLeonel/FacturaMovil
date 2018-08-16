<ptv-imprimir>

<div class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>{$.i18n.prop("tikect.encabezado.numeroFactura")} {facturaImpresa.numeroConsecutivo}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirfacturaImpresa} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                
            </div>
            <section class="zona-impresion" id="imprimeme" name ="imprimeme">
                <div class="forma-impresion">
                    <div class="ticket" id="ticket" name="ticket" > 
                        <div class="encabezado"><strong> {factura.empresa.nombre}                        </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.cedula")}       </strong>{facturaImpresa.empresa.cedula}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.telefono")}     </strong>{facturaImpresa.empresa.telefono}<br></div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.fecha.emision")} </strong>{facturaImpresa.fechaEmision}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.condicion.venta")} </strong>{facturaImpresa.condicionVenta}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.plazo.credito")} </strong>{facturaImpresa.plazoCredito}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>{facturaImpresa.tipoDoc}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.moneda")}        </strong>{facturaImpresa.codigoMoneda}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{facturaImpresa.numeroConsecutivo}</div>
                        
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.cliente.nombreCompleto}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.receptor.cedula")}     </strong>{facturaImpresa.cliente.cedula}</div>
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
                                <td class="precio">{montoTotalLinea.toLocaleString('de-DE')}</td>
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
                            <td ><strong>{facturaImpresa.totalVenta.toLocaleString('de-DE') } </strong>  </td>
                            </tr>

                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.descuento")}</strong></td>
                            <td ><strong>{facturaImpresa.totalDescuentos.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td><strong>{$.i18n.prop("tikect.total.venta.neta")}</strong></td>
                            <td><strong>{facturaImpresa.totalVentaNeta.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.impuesto")}</strong></td>
                            <td ><strong>{facturaImpresa.totalImpuesto.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.exento")}</strong></td>
                            <td ><strong>{facturaImpresa.totalExento.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.total.comprobante")}</strong></td>
                            <td ><strong>{facturaImpresa.totalComprobante.toLocaleString('de-DE')}</strong></td>
                            </tr>

                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.totalCambioPagar")}</strong></td>
                            <td ><strong>{facturaImpresa.totalCambioPagar.toLocaleString('de-DE')}</strong></td>
                            </tr>                            
                                                 

                            <tr>
                            <td colspan="3"><div id="divQR" name="divQR"  class="divQR"></div></td>
                            </tr>
                            
                        </tbody>
                        </table> 
                        
                        <br>
                        <p  align="left">{$.i18n.prop("tikect.autorizado.parte.uno")}  <br>
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
self.facturaImpresa   = opts.factura;  
self.detalles = []
self.subTotalGeneral = 0

self.on('mount',function(){
    
    if(self.facturaImpresa.id > 0){
        self.detalles = []
        self.detalles =self.facturaImpresa.detalles
        self.facturaImpresa.fechaEmision = displayDate_detail(self.facturaImpresa.fechaEmision)
        self.update()
       $('.imprimirModal').modal('show'); 
    }
    getSubTotalGeneral()
    getMoneda()
    __ComboTipoDocumentos()
    buscarTipoDocumento()
    __comboCondicionPago()
    buscarCondicionPago()
   
   

})


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
*Formato de Fecha
**/
function displayDate_detail(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

/**
*Imprimir facturaImpresa
**/    
__ImprimirfacturaImpresa(){
    __imprimir()
    $("#boton-regresar").focus()
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


/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}

</script>
</ptv-imprimir>