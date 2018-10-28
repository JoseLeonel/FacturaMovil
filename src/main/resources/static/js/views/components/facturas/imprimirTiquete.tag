<tiquete-imprimir>

<div class="modal fade imprimirModalTiquete" id="imprimirModalTiquete" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1 >{$.i18n.prop("tikect.encabezado.tiquete")} {facturaImpresa.id}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimirTiquete"  onclick = {__ImprimirTiquete} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                
            </div>
            <section class="zona-impresion" id="imprimirtiqueteTemporal" name ="imprimirtiqueteTemporal">
                <div class="forma-impresion">
                    <div class="tiqueteTemporal" id="tiqueteTemporal" name="tiqueteTemporal" > 
                        <h1 >{$.i18n.prop("tikect.encabezado.tiquete")} {facturaImpresa.id}<h1>
                          <div class="encabezado" show ="{facturaImpresa.nombreFactura !=null}"><strong>{$.i18n.prop("tikect.encabezado.receptor")}     </strong>{facturaImpresa.nombreFactura}</div>
  
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
                            <tr>
                            	<td></td>
                            	<td ><strong>{$.i18n.prop("tikect.total.comprobante")}</strong></td>
                            	<td ><strong>{facturaImpresa.totalComprobante}</strong></td>
                            </tr>
                            <tr>
	                            <td colspan="3">
	                            	<div id="divQR1" name="divQR1"  class="divQR1">
	                            	</div>
		                            <br>                            
		                            <br>
	                            </td>
                            </tr>
                        </tbody>
                        </table> 
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
    .botones-imprimir .boton-imprimirTiquete
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

  
    .tiqueteTemporal
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
    .tiqueteTemporal > table
    {
        border-collapse: collapse;
        border-top: 0px solid black;
    }
    .forma-table
    {
        border-collapse: collapse;
        border-top: 1px solid black;
    }
    .tiqueteTemporal > td.producto,th.producto
    {
        max-width: 75px;
        width: 377px;
    }
    .tiqueteTemporal > td.cantidad,th.cantidad
    {
        width: 377px;
        word-break: break-all;
    }
    .tiqueteTemporal > td.precio,th.precio
    {
        width: 377px;
        word-break: break-all;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
    .tiqueteTemporal > img
    {
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
        
       $('.imprimirModalTiquete').modal('show'); 
    }
    getSubTotalGeneral()
    getMoneda()
    __ComboTipoDocumentos()
    buscarTipoDocumento()
    __comboCondicionPago()
    buscarCondicionPago()
    self.facturaImpresa.totalComprobante = formatoDecimales(self.facturaImpresa.totalComprobante,2);
    self.detalles.forEach(function(elemen){
    		console.log(elemen);
            elemen.montoTotalLinea = formatoDecimales(elemen.montoTotalLinea,2);
        }
    )
    self.update()


   
   

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
__ImprimirTiquete(){
    
     $('#imprimirModalTiquete').modal('toggle') 
    __imprimirTique()
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
     self.comboTipoDocumentos.push({
         estado:"88",
        descripcion:$.i18n.prop("factura.tipo.documento.factura.proforma")
    })
    self.update()
}

/**
*imprimir
**/
function __imprimirTique(){
    var objeto=document.getElementById('imprimirtiqueteTemporal');  //obtenemos el objeto a imprimir
     var div = document.querySelector("#imprimirtiqueteTemporal");
    imprimirTiqueteT(div)

}


function imprimirTiqueteT(elemento){
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
</tiquete-imprimir>