<ptv-imprimir>

<div id="imprimirModal" class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="imprimirModal" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
        <h2 class="tituloPrincipal" >{titulo}</h2>
      <div  class= "wrap">
        <div class="row">
            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                <div class="row">
                    <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                        <a href="#" class="botonImprimir "  onclick = {__ImprimirfacturaImpresa} ><i class="glyphicon glyphicon glyphicon-print"></i>&nbsp;Imprimir</a>
                        <button type="button" class="btn btn-danger boton-imprimirCelular " onclick="printJS('imprimeme', 'html')" show={mostrarImprimiCelular == true}>Imprimir desde el <br>Celular</button>    
                    </div>
                    <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                        <button type="button" class="btn-dark-gray btn-back  botonregreso"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
                    </div>
                </div>     
            </div>
            </div>
            <div class="row">
            <div class="col-md-4 col-sx-12 col-sm-12 col-lg-4"></div>
            <div class="col-md-4 col-sx-12 col-sm-12 col-lg-4">
                    <section class="">
                        <div class="forma-impresion " id="imprimeme" name ="imprimeme">                        
                            <div class="ticket" id="ticket" name="ticket" > 
                                
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 0}"><strong> {documentoElectronico} </strong><br></div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1}"><strong> {$.i18n.prop("regimen.simplificado")} </strong><br></div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 2}"><strong> Recibo por reintegro de Gastos </strong><br></div>
                                <div class="encabezado" show = "{facturaImpresa.tipoDoc == '87'}"><strong> {$.i18n.prop("factura.tipo.documento.factura.tiquete.uso.interno")} {facturaImpresa.id}                       </strong><br></div>
                                
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica != 2}"><strong> {$.i18n.prop("emisor.codigoActividad")} : </strong>{facturaImpresa.codigoActividad}</div> 
                                <div class="encabezado"><strong> {facturaImpresa.empresa.nombreComercial}                        </strong><br></div>
                                <div class="encabezado"><strong> {facturaImpresa.empresa.nombre}                        </strong></div>
                                <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.cedula")}  </strong>{facturaImpresa.empresa.cedula} <strong>{$.i18n.prop("tikect.encabezado.telefono")}</strong> {facturaImpresa.empresa.telefono}</strong></div>
                                <div class="encabezado" show = {facturaImpresa.empresa.correoElectronico != ""} >{facturaImpresa.empresa.correoElectronico} <br></div>
                                <div class="encabezado" >{facturaImpresa.empresa.otraSenas.length>39?facturaImpresa.empresa.otraSenas.substring(0, 39):facturaImpresa.empresa.otraSenas} </div>
                                <div class="encabezado" >{facturaImpresa.empresa.otraSenas.length>39?facturaImpresa.empresa.otraSenas.substring(40, facturaImpresa.empresa.otraSenas.length):''} </div>
                                
                                <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.fecha.emision")} </strong>{facturaImpresa.fechaEmisionSTR} </div>
                                <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.condicion.venta")} </strong>{facturaImpresa.condicionVenta}</div>
                                <div class="encabezado"><strong>{$.i18n.prop("factura.medioPago")} </strong>{facturaImpresa.medioEfectivo} {facturaImpresa.medioTarjeta} {facturaImpresa.medioBanco}</div>
                                <div class="encabezado" show ="{facturaImpresa.plazoCredito > 0}"><strong>{$.i18n.prop("tikect.encabezado.plazo.credito")} dias </strong>{facturaImpresa.plazoCredito}</div>
                                <div class="encabezado" > <strong>Usuario:</strong>   {facturaImpresa.usuarioCreacion.nombreUsuario}  </div>  
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 0}"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>{facturaImpresa.tipoDoc}</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '02' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Nota Debito</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '03' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Nota Credito</div>
                                <div class="encabezado" show="{facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc == '01' }"><strong>{$.i18n.prop("tikect.encabezado.tipo.documento")}</strong>Factura</div>
                                <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.moneda")} </strong>{facturaImpresa.codigoMoneda}</div>
                                <div class="encabezado" show = "{facturaImpresa.tipoDoc != '88' &&  facturaImpresa.numeroConsecutivo != ""}"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{facturaImpresa.numeroConsecutivo}</div>
                                <div class="encabezado" show = "{facturaImpresa.tipoDoc != '88' &&  facturaImpresa.consecutivoProforma.length > 0}"><strong> {$.i18n.prop("tikect.encabezado.proforma")} {facturaImpresa.consecutivoProforma}                       </strong><br></div>
                                <div class=" encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0  &&  facturaImpresa.clave != ""}"><strong>{$.i18n.prop("tikect.encabezado.clave")}</strong> </div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0 }">{claveParteUno}</div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.tipoDoc != '88' && facturaImpresa.empresa.noFacturaElectronica == 0}">{claveParteDos}</div>
                                <div class="encabezado" show ="{facturaImpresa.cliente.cedula != '999999999999' && facturaImpresa.cliente.cedula != '888888888888'}"><strong show={facturaImpresa.cliente.cedula != '999999999999'}>{$.i18n.prop("tikect.encabezado.receptor.cedula")}  {cedula}   </strong></div>
                                <div class="encabezado" show ="{facturaImpresa.cliente.cedula != '999999999999' && facturaImpresa.cliente.cedula != '888888888888'  }"><strong show={facturaImpresa.cliente.nombreCompleto != 'CLIENTE_FRECUENTE'}>{$.i18n.prop("tikect.encabezado.receptor")}     {facturaImpresa.cliente.nombreCompleto}</strong ></div>
                                <div class="encabezado" show ="{facturaImpresa.nombreFactura != "" || facturaImpresa.nombreFactura ==null }"><strong>A nombre:     </strong>{facturaImpresa.nombreFactura}</div>
                                <div class="encabezado" show ="{facturaImpresa.cliente.cedula != '999999999999' && facturaImpresa.cliente.cedula != '888888888888'  }"><strong show={facturaImpresa.cliente.nombreComercial.length > 0}>{facturaImpresa.cliente.nombreComercial}</strong ></div>
                                <div class="encabezado" show ="{facturaImpresa.cliente.cedula != '999999999999' && facturaImpresa.cliente.cedula != '888888888888'}"><strong show={facturaImpresa.cliente.cedula != '999999999999'}> {facturaImpresa.cliente.correoElectronico}   </strong></div>
                                <div class="encabezado" show ="{facturaImpresa.cliente.correoAlternativo != ''}"><strong show={facturaImpresa.cliente.cedula != '999999999999'}> {facturaImpresa.correoAlternativo}   </strong></div>
                                <div class="tamanoClaveencabezado" show ="{facturaImpresa.referenciaNumero.length > 0}"><strong > {$.i18n.prop("informacion.numero.referencia")} </strong></div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.referenciaNumero.length > 0}">{claveParteUnoRef}</div>
                                <div class="tamanoClave encabezado" show = "{facturaImpresa.referenciaNumero.length > 0}">{claveParteDosRef}</div>
                                <div class="encabezado" show ='{ facturaImpresa.nota != "" && facturaImpresa.nota !=null}'> Nota:   {facturaImpresa.nota}  </div>
                                <div class="encabezado" show="{facturaImpresa.mesa != null}"><strong> {facturaImpresa.mesa.descripcion} </strong><br></div> 
                                <table class = "forma-table">
                                    <thead>
                                        <tr class = "forma-table">
                                            <th class="cantidad">{$.i18n.prop("tikect.detalle.cantidad")}  </th>
                                            <th class="producto">{$.i18n.prop("tikect.detalle.descripcion")}</th>
                                            <th class="precio"> {$.i18n.prop("tikect.total.linea")} </th>

                                        </tr>
                                    </thead>
                                <tbody>
                                    <tr  each={detalles} class="detalleTables">
                                        <td class="cantidad" show={codigo !='8888'}>{cantidadSTR}</td>
                                        <td class="producto" show={codigo !='8888'}>{descripcion.length>22?descripcion.substring(0, 22):descripcion}</td>
                                        <td class="precio"   show={codigo !='8888'}>{montoTotalSTR} {montoImpuesto>0?"G":"E"}</td>
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
                                    <td ><strong>{facturaImpresa.totalDescuentoSTR}</strong></td>
                                                                   
                                    </tr>
                                    
                                     <tr each={totalesIVAI}>
                                        <td></td>
                                        <td ><strong>{descripcion}</strong></td>
                                        <td ><strong>{totalSTR}</strong></td>
                                    </tr>

                                    <tr show={montoExoneracion > 0}>
                                    <td></td>
                                    <td ><strong>{$.i18n.prop("factura.resumen.exoneracion")}</strong></td>
                                    <td ><strong>{montoExoneracionSTR}</strong></td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td ><div class="formatoTotal"><strong>{$.i18n.prop("tikect.total.final")}</strong></div></td>
                                    <td ><div class="formatoTotal"><strong>{facturaImpresa.totalComprobanteSTR}</strong></div></td>
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
                                <p  align="left" show = "{facturaImpresa.estado != 3 && facturaImpresa.estado != 4 && facturaImpresa.empresa.noFacturaElectronica == 1 && facturaImpresa.tipoDoc != '87'}">{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.uno")}  <br>
                                                {$.i18n.prop("tikect.autorizado.no.factura.elec.parte.dos")}   
                                                    <br>{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.tres")}
                                                    <br>{$.i18n.prop("tikect.autorizado.no.factura.elec.parte.cuatro")}</p>

                                <br>
                                <div class="encabezado">{$.i18n.prop("tikect.final")}</div>
                                <div class="encabezado">{$.i18n.prop("emprendesoft.factura")}</div>
                                <div class="encabezado">{$.i18n.prop("emprendesoft.mensaje")}</div> 
                                <div class="encabezado">{$.i18n.prop("emprendesoft.correo")}</div>      
                                <br show={mostrarImprimiCelular == true}>
                                <br show={mostrarImprimiCelular == true}>
                                <br show={mostrarImprimiCelular == true}>
                                <br show={mostrarImprimiCelular == true}>     
                                 <div class="encabezado" show={mostrarImprimiCelular == true}>Recibido Por:___________________________</div>           
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-4 col-sx-12 col-sm-12 col-lg-4"></div>
                </div>
    </div>    
   
    </div>
  </div>
</div>
<div show="{mostrarPDF == true}">
 <iframe style="width: 100%; height: 500px" id="loadPdfFactura" src="">
 </iframe>
</div>
<style type="text/css"  >
* {
    font-size: 12px !important;
    font-family: 'Times New Roman' !important;
}

.modal-content{
    overflow: scroll !important;
}
@media (min-width: 992px){
.modal-lg {
    width: 600px !important;
}

}

.botonregreso{
   margin-left: 15px !important;
   padding-right: 65px!important;
}
.botonImprimir{
    background-color: #66b12f;
    border-radius: 5px;
    color: white;
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    font-size: 30px !important;
    font-weight: 800;
    margin-right: 15px;
    border: none;
    float: left;
    margin-left: 15px;
    cursor: pointer;

}
    .tituloPrincipal{
        text-align: center;
    }
    .formatoTotal{
        
    } 
    .fondoEncabezado
    {
        background: #00539B;
        color: #f9fafc;
    }
    .wrap
    {
        margin: auto;
       
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
    .boton-imprimirCelular
    {
        border: none;
        border-radius: 30px;
        box-shadow: 0 0px 4px 0 rgba(0, 0, 0, 0.1), 0 3px 5px 0 rgba(0, 0, 0, 0.20);
        cursor: pointer;
        font-size: 14px;
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
        font-size: 13px;
        font-style: normal;
        font-variant: normal;
        font-weight: normal;
        height: auto;
        line-height: normal;
        margin: 0px;
      
        padding: 0px;
        text-transform: none;
      
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
        
    }
    .ticket > td.cantidad,th.cantidad
    {
      
        word-break: break-all;
    }
    .ticket > td.precio,th.precio
    {
        
        word-break: break-all;
    }
    .encabezado
    {
        align-content: left;
        text-align: left;
        padding-top: 3px;
        
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
    
    .encabezado
    {
        align-content: left;
        text-align: left;
    }
@page
    {
        margin: 0;
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

#imprimeme {
    height: 100%;
    width: 100%;
    background: white;
   
}
</style>    
<script>
var self = this;
self.parametro   = opts.parametros;  
self.detalles = []
self.montoExoneracion = 0
self.montoImpuesto = 0
self.montoExoneracionSTR = ""
self.montoImpuestoSTR = ""
self.mostrarImprimiCelular = false;
self.titulo = ""
self.claveParteUno =""
self.claveParteDos =""
self.claveParteUnoRef =""
self.claveParteDosRef =""
self.totalImpuestoServicio = 0
self.subTotal = 0
self.cedula = ""
self.facturaActiva = 0
self.facturaImpresa = {
    id:null,
    nota:"",
    tipoDoc:"",
    plazoCredito:0,
    codigoActividad:"",
    tipoCambio:0,
    nombreFactura:"",
    totalDescuentos:0,
    cliente:{
       cedula:"",
       nombreCompleto:""  
    },
    empresa:{
        nombreComercial:"",
        imprimirDirecto:0,
        noFacturaElectronica:0,
        correoElectronico:"",
        otraSenas:"",
        telefono:0
    },
    referenciaNumero:""

}
self.impuesto13 = 0
self.impuestoReducida0 = 0
self.impuestoReducida1 = 0
self.impuestoReducida2 = 0
self.impuestoReducida4 = 0
self.impuestoTransitorio0 = 0
self.impuestoTransitorio4 = 0
self.impuestoTransitorio8 = 0
self.totalesIVAI    = []
self.pdf = false;
self.mostrarPDF = false  
self.on('mount',function(){
    self.claveParteUnoRef =""
    self.claveParteDosRef =""
    self.update()
    self.pdf = false;

   // document.getElementById('divQR').innerHTML = '';
    if(typeof self.parametro.factura.id != 'undefined' && self.parametro.facturaDia !=3 &&  self.pdf == false){
        if(self.parametro.factura.id > 0){
           consultaFactura(self.parametro.factura.id) 
        }
       
    }else if(typeof self.parametro.factura.consecutivo != 'undefined' && self.parametro.facturaDia !=3 &&  self.pdf == false){
        if(self.parametro.factura.consecutivo.length > 0){
           consultaFacturaPorConsecutivo(self.parametro.factura.consecutivo)
        }
       
    }
    if(self.pdf == false){
      llamarQR()
    }
    
    if(self.parametro.facturaDia == 3 &&  self.pdf == false){
        consultaDetalles(self.parametro.factura)
    }
    
})

function  llamarQR(){
    if (typeof self.parametro.factura.empresa != 'undefined' )  {
        if (self.parametro.factura.empresa.noFacturaElectronica ==0) {
           qr()
        }
     }else if (typeof self.parametro.factura.noFacturaElectronica != 'undefined' ) {
        if (self.parametro.factura.noFacturaElectronica ==0) {
           qr()
        }
     }

}
/**
* Muestra el QR
**/
function qr(){
    if(self.parametro.factura.clave == null){
        return false
    }
    if(self.parametro.factura.clave.length ==0){
        return false
    }
    var imprimirCelular = 0
    if (typeof self.parametro.factura.empresa != 'undefined') {
	    imprimirCelular = self.parametro.factura.empresa.imprimirCelular;
	}
    if (typeof self.parametro.factura.imprimirCelular != 'undefined') {
	    imprimirCelular = self.parametro.factura.imprimirCelular;
	}
     var options = {
        // render method: 'canvas', 'image' or 'div'
        render: imprimirCelular == 1?'image':'div',
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
        mPosX: 2.5,
        mPosY: 2.5,

        label: self.parametro.factura.clave,
        fontname: 'sans',
        fontcolor: '#000',
        image: null
    }
    $('#divQR').qrcode(options);
}
/**
* Consulta detalles
**/
function consultaDetalles(data){
    if(data.length > 0){
       self.detalles = []
       self.detalles =data
       self.detalles.sort(function(a,b) {
          if ( a.numeroLinea < b.numeroLinea )
               return -1;
            if ( a.numeroLinea > b.numeroLinea )
                return 1;
            return 0;
        }   );
                    
                    self.detalles.forEach(function(elemen){
                        if(elemen.codigo == "8888"){
                            self.totalImpuestoServicio = __valorNumerico(elemen.montoTotalLinea)
                            self.update()
                        }
                        elemen.montoTotal = redondearDecimales(elemen.montoTotal,0);
                        self.update()
                        sumarImpuesto(elemen)
                    })
                   self.montoExoneracion = 0
                   self.montoImpuesto = 0
                   self.montoExoneracionSTR = ""
                   self.montoImpuestoSTR = ""
                   self.update()
                    $.each(data, function( index, modeloTabla ) {
                        self.montoExoneracion = self.montoExoneracion + parseFloat(modeloTabla.montoExoneracion)
                        self.montoImpuesto = self.montoImpuesto + parseFloat(modeloTabla.montoImpuesto + modeloTabla.montoImpuesto1)
                      if(self.facturaImpresa.id == null){
                            self.facturaImpresa = modeloTabla.factura 
                            //factura.js
                            self.cedula = getCedulaOrIdentificacionExtranjero(self.facturaImpresa.cliente.cedula,self.facturaImpresa.cliente.identificacionExtranjero)
                            if(self.facturaImpresa.empresa.imprimirCelular == 1){
                                self.mostrarImprimiCelular = true
                            }
                            console.log(self.facturaImpresa)
                            self.update()
                            self.claveParteUno= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(0,24):""
                            self.claveParteDos= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(25,51):""
                            self.claveParteUnoRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(0,24):""
                            self.claveParteDosRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(25,51):""
                              self.subTotal = 0
                            self.update()
                            getMoneda()
                            _VersionTiquete()
                            __ComboTipoDocumentos()
                            buscarTipoDocumento()
                            __comboCondicionPago()
                            buscarCondicionPago()
                            getMedioPago()
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
                            
                      }
                    });
                   self.montoExoneracionSTR = formatoDecimales(self.montoExoneracion,0);
                   self.montoImpuestoSTR = formatoDecimales(self.montoImpuesto,0);

                    if(self.totalImpuestoServicio == 0 ){
                        self.totalImpuestoServicio = self.facturaImpresa.totalOtrosCargos
                    }
                   self.totalImpuestoServicioSTR = ""
                   self.totalImpuestoServicioSTR =  formatoDecimales(self.totalImpuestoServicio,2)  
                   self.update()
                    __imprimir()
            }

}


function sumarImpuesto(detalle){
    if(detalle.tipoImpuesto == 'undefined'){
       return 
    }

    if(detalle.tipoImpuesto.length == 0){
       return 
    }
    var encontrado = false
     if(self.totalesIVAI[0] == null ){
         encontrado = true
        __nuevoImpuesto(detalle)
     }else{ // buscar y sumar si ya existe en el array
        for (var count = 0; count < self.totalesIVAI.length; count++) {
            if (self.totalesIVAI[count].impuesto == detalle.impuesto ){
                var itemIVA = self.totalesIVAI[count];
                itemIVA.total = itemIVA.total + __valorNumerico(detalle.montoImpuesto)
                itemIVA.totalSTR = formatoDecimales(itemIVA.total,2)
                self.totalesIVAI[count] = itemIVA;
                self.update()
                encontrado = true
            }    
        }
     }
     if(encontrado == false){
        __nuevoImpuesto(detalle);
    }
}
function __nuevoImpuesto(detalle){

    self.totalesIVAI.push({
        impuesto:detalle.impuesto,
        descripcion:"Imp " + detalle.impuesto + "%",
        total : detalle.montoImpuesto,
        totalSTR: formatoDecimales(detalle.montoImpuesto,2)
    })

}
/**
*consultar Facturas
**/
function consultaFactura(idFactura){
     self.facturaImpresa =null
     self.update()
     $.ajax({
        url: "ListarDetlleByFacturaAjax.do",
        datatype: "json",
        data: {idFactura:idFactura},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
                    self.detalles = []
                    self.detalles =data.aaData
                    self.detalles.sort(function(a,b) {
                        if ( a.numeroLinea < b.numeroLinea )
                            return -1;
                        if ( a.numeroLinea > b.numeroLinea )
                            return 1;
                        return 0;
                    } );
                    self.detalles.forEach(function(elemen){
                        if(elemen.codigo == "8888"){
                            self.totalImpuestoServicio = __valorNumerico(elemen.montoTotalLinea)
                            self.update()
                        }
                        elemen.montoTotal = redondearDecimales(elemen.montoTotal,0);
                        self.update()
                        sumarImpuesto(elemen)
                    })
                   self.montoExoneracion = 0
                   self.montoImpuesto = 0
                   self.montoExoneracionSTR = ""
                   self.montoImpuestoSTR = ""
                   self.update()
                    $.each(data.aaData, function( index, modeloTabla ) {
                        self.montoExoneracion = self.montoExoneracion + parseFloat(modeloTabla.montoExoneracion)
                        self.montoImpuesto = self.montoImpuesto + parseFloat(modeloTabla.montoImpuesto + modeloTabla.montoImpuesto1)
                      if(self.facturaImpresa == null){
                            self.facturaImpresa = modeloTabla.factura 
                            //factura.js
                            self.cedula = getCedulaOrIdentificacionExtranjero(self.facturaImpresa.cliente.cedula,self.facturaImpresa.cliente.identificacionExtranjero)
                            if(self.facturaImpresa.empresa.imprimirCelular == 1){
                                self.mostrarImprimiCelular = true
                            }
                            console.log(self.facturaImpresa)
                            self.update()
                            self.claveParteUno= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(0,24):""
                            self.claveParteDos= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(25,51):""
                            self.claveParteUnoRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(0,24):""
                            self.claveParteDosRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(25,51):""
                              self.subTotal = 0
                            self.update()
                            getMoneda()
                            _VersionTiquete()
                            __ComboTipoDocumentos()
                            buscarTipoDocumento()
                            __comboCondicionPago()
                            buscarCondicionPago()
                            getMedioPago()
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
                            
                      }
                    });
                   self.montoExoneracionSTR = formatoDecimales(self.montoExoneracion,0);
                   self.montoImpuestoSTR = formatoDecimales(self.montoImpuesto,0);

                    if(self.totalImpuestoServicio == 0 ){
                        self.totalImpuestoServicio = self.facturaImpresa.totalOtrosCargos
                    }
                   self.totalImpuestoServicioSTR = ""
                   self.totalImpuestoServicioSTR =  formatoDecimales(self.totalImpuestoServicio,2)  
                   self.update()
         
                    if (self.facturaImpresa.empresa.imprimirDirecto == 0 || self.parametro.facturaDia ==1){
                        $('.imprimirModal').modal('show');   
                    }
                   
                     if (self.facturaImpresa.empresa.imprimirDirecto == 1 && self.parametro.facturaDia ==0 ){
                      __imprimir()
                     }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
     

}

/**
*consultar Facturas por Consecutivo
**/
function consultaFacturaPorConsecutivo(consecutivo){
     self.facturaImpresa =null
     self.update()
     $.ajax({
        url: "ListarDetlleByFacturaConsecutivoAjax.do",
        datatype: "json",
        data: {consecutivo:consecutivo},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
                    self.detalles = []
                    self.detalles =data.aaData
                    self.detalles.sort(function(a,b) {
                        if ( a.numeroLinea < b.numeroLinea )
                            return -1;
                        if ( a.numeroLinea > b.numeroLinea )
                            return 1;
                        return 0;
                    } );
                    self.detalles.forEach(function(elemen){
                        if(elemen.codigo == "8888"){
                            self.totalImpuestoServicio = __valorNumerico(elemen.montoTotalLinea)
                            self.update()
                        }
                        elemen.montoTotal = redondearDecimales(elemen.montoTotal,0);
                        self.update()
                    })
                   self.montoExoneracion = 0
                   self.montoImpuesto = 0
                   self.montoExoneracionSTR = ""
                   self.montoImpuestoSTR = ""
                   self.update()
                    $.each(data.aaData, function( index, modeloTabla ) {
                        self.montoExoneracion = self.montoExoneracion + parseFloat(modeloTabla.montoExoneracion)
                        self.montoImpuesto = self.montoImpuesto + parseFloat(modeloTabla.montoImpuesto + modeloTabla.montoImpuesto1)
                      if(self.facturaImpresa == null){
                            self.facturaImpresa = modeloTabla.factura
                            if(self.facturaImpresa.empresa.imprimirCelular == 1){
                                self.mostrarImprimiCelular = true
                            }
                            console.log(self.facturaImpresa)
                            self.update()
                            self.claveParteUno= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(0,24):""
                            self.claveParteDos= self.facturaImpresa.clave !=null ?self.facturaImpresa.clave.substring(25,51):""
                            self.claveParteUnoRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(0,24):""
                            self.claveParteDosRef= self.facturaImpresa.referenciaNumero !=null ?self.facturaImpresa.referenciaNumero.substring(25,51):""
                              self.subTotal = 0
                            self.update()
                            getMoneda()
                            _VersionTiquete()
                            __ComboTipoDocumentos()
                            buscarTipoDocumento()
                            __comboCondicionPago()
                            buscarCondicionPago()
                            getMedioPago()
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
                            
                      }
                    });
                   self.montoExoneracionSTR = formatoDecimales(self.montoExoneracion,0);
                   self.montoImpuestoSTR = formatoDecimales(self.montoImpuesto,0);

                    if(self.totalImpuestoServicio == 0 ){
                        self.totalImpuestoServicio = self.facturaImpresa.totalOtrosCargos
                    }
                   self.totalImpuestoServicioSTR = ""
                   self.totalImpuestoServicioSTR =  formatoDecimales(self.totalImpuestoServicio,2)  
                   self.update()
         
                    if (self.facturaImpresa.empresa.imprimirDirecto == 0 || self.parametro.facturaDia ==1){
                        $('.imprimirModal').modal('show');   
                    }
                   
                     if (self.facturaImpresa.empresa.imprimirDirecto == 1 && self.parametro.facturaDia ==0 ){
                      __imprimir()
                     }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
     

}


function _VersionTiquete(){
    if(self.facturaImpresa.tipoDoc == "01"){
        self.documentoElectronico = self.facturaImpresa.versionEsquemaXML == 1 ?$.i18n.prop("documento.electronico.factura"):$.i18n.prop("documento.electronico.factura.version3");
    }else if(self.facturaImpresa.tipoDoc == "02"){
        self.documentoElectronico = self.facturaImpresa.versionEsquemaXML == 1 ? $.i18n.prop("documento.electronico.nota.debito"):$.i18n.prop("documento.electronico.nota.debito.version3");
    }else if(self.facturaImpresa.tipoDoc == "03"){
        self.documentoElectronico = self.facturaImpresa.versionEsquemaXML == 1 ?$.i18n.prop("documento.electronico.nota.credito"):$.i18n.prop("documento.electronico.nota.credito.version3");
    }else if(self.facturaImpresa.tipoDoc == "04"){
        self.documentoElectronico = self.facturaImpresa.versionEsquemaXML == 1 ?$.i18n.prop("documento.electronico.tiquete"):$.i18n.prop("documento.electronico.tiquete.version3");
    }else if(self.facturaImpresa.tipoDoc == "86"){
        self.documentoElectronico = self.facturaImpresa.versionEsquemaXML == 1 ?$.i18n.prop("documento.electronico.nota.credito.interna"):$.i18n.prop("documento.electronico.nota.credito.interna");
    }
    self.update()
}

function getMedioPago() {

	if(self.facturaImpresa.medioEfectivo == "01") {
		self.facturaImpresa.medioEfectivo = $.i18n.prop("medio.pago.efectivo");
	}
    if(self.facturaImpresa.medioTarjeta == "02") {
		self.facturaImpresa.medioTarjeta = $.i18n.prop("medio.pago.tarjeta");
	}
    if(self.facturaImpresa.medioBanco == "04") {
	  self.facturaImpresa.medioBanco  = $.i18n.prop("medio.pago.transferencia");
	}
   self.update()
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

   // if (typeof self.parametro.factura.imprimirCelular != 'undefined') {
   //         if(typeof self.parametro.factura.id != 'undefined' && self.parametro.factura.imprimirCelular ==0){
   //             if(self.parametro.factura.id > 0){
   //               //if(self.parametro.factura.tipoDoc =='04' && typeof self.parametro.factura.tipoDoc != 'undefined' ) {
   //                   self.pdf = true;
   //                   self.mostrarPDF = true
   //                   self.update()
                      
    //                  imprimirPFD(function(resultado){
    //                       self.mostrarPDF = false
    //                        self.update()
    //                        console.log(resultado)
    //                  })

                 // }
    //            }
    //        }    
    //       
    //    }
     //   if (typeof self.parametro.factura.empresa.imprimirCelular != 'undefined') {
      //      if(typeof self.parametro.factura.id != 'undefined' && self.parametro.factura.empresa.imprimirCelular ==0){
      //          if(self.parametro.factura.id > 0){
                  //if(self.parametro.factura.tipoDoc =='04' && typeof self.parametro.factura.tipoDoc != 'undefined' ) {
        //              self.pdf = true;
          //            self.mostrarPDF = true
            //          self.update()
                      
                       
              //        imprimirPFD(function(resultado){
                //           self.mostrarPDF = false
                  //          self.update()
                    //        console.log(resultado)
                    //  })

                 // }
             //   }
          //  }    
           
       // }
    //if(self.mostrarPDF == false){
        var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
        // var div = document.querySelector("#imprimeme");
        imprimirElemento(objeto)
   // }
   

   
}
function imprimirElemento(elemento){
 // var originalContents = document.body.innerHTML;
  var ventana =window.open("", "MsgWindow", "width=600,height=400");
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
 // document.body.innerHTML = originalContents;
  
//  printJS('imprimeme', 'html');
  
  return true;
}


function imprimirPFD(callback){
    var href =  'GenerarTikect1.do?idFactura='+self.parametro.factura.id + '&t=' + $.now() +'&tipoFactura=1'+'&subTotalGeneralSTR='+"" + '&totalImpuestoRestSTR='+""+'&impServicioTotalSTR='+""+'&totalComprobanteSTR='+ ""+'&totalDescuentosProformaREstSTR='+""
	$('#loadPdfFactura').attr("src", href );	
    printFrame =  $('#loadPdfFactura')
    Print.send(printFrame)
    callback("exitoso")
}

function cargarFrame(){
   // CREATE iFrame 
    let iframe = document.createElement("iframe"); 
    iframe.setAttribute('id', 'printerIFrame'); 
    iframe.setAttribute('name', 'printerIFrame'); 
    iframe.setAttribute('style', 'display: hidden;');
    
}


const Print = {
    send: (printFrame) => {
        // Get iframe element
        const iframeElement = document.getElementById("loadPdfFactura")

         // Append iframe element to document body
     //   document.getElementsByTagName('body')[0].appendChild(printFrame)
         // Wait for iframe to load all content
        iframeElement.onload = () => {
            if (Browser.isFirefox()) {
                
              setTimeout(() => performPrint(iframeElement),1000)
            } else {
              performPrint(iframeElement)
            }
            return

        }
       

    }
}
function performPrint(iframeElement){
     
     try {
        
        iframeElement.focus()
        iframeElement.contentWindow.print()
       

     } catch (error) {
        console.log(error)
  } finally {

  }
}

const Browser = {
  // Firefox 1.0+
  isFirefox: () => {
    return typeof InstallTrigger !== 'undefined'
  },
  // Internet Explorer 6-11
  isIE: () => {
    return navigator.userAgent.indexOf('MSIE') !== -1 || !!document.documentMode
  },
  // Edge 20+
  isEdge: () => {
    return !Browser.isIE() && !!window.StyleMedia
  },
  // Chrome 1+
  isChrome: (context = window) => {
    return !!context.chrome
  },
  // At least Safari 3+: "[object HTMLElementConstructor]"
  isSafari: () => {
    return Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0 ||
        navigator.userAgent.toLowerCase().indexOf('safari') !== -1
  },
  // IOS Chrome
  isIOSChrome: () => {
    return navigator.userAgent.toLowerCase().indexOf('crios') !== -1
  }
}


</script>
</ptv-imprimir>