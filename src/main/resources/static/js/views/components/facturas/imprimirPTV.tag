<ptv-imprimir>

<div class="modal fade imprimirModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div  class= "wrap">
        <h1>{$.i18n.prop("tikect.encabezado.numeroFactura")} {facturaImpresa.numeroConsecutivo}<h1>
        <div class="pantalla-imprimir">
            <div class="botones-imprimir">
                <a href="#" class="boton-imprimir"  onclick = {__ImprimirfacturaImpresa} ><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir(F8)</a>
                <a href="#" class="boton-imprimir" id="boton-regresar" onclick = {__RegresarVentaImprimir}><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;Regresar(Esc)</a>
            </div>
            <section class="zona-impresion" id="imprimeme">
                <div class="forma-impresion">
                    <div class="ticket" > 
                        <div class="encabezado"><strong> {factura.empresa.nombre}                        </strong><br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.cedula")}       </strong>{facturaImpresa.empresa.cedula}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.telefono")}     </strong>{facturaImpresa.empresa.telefono}<br></div>
                        <div class="encabezado"><strong> {$.i18n.prop("tikect.encabezado.celular")}      </strong>{facturaImpresa.empresa.celular}<br></div>                                                 
                        
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.numeroFactura")} </strong>{facturaImpresa.numeroConsecutivo}</div>
                        <div class="encabezado"><strong>{$.i18n.prop("tikect.encabezado.fecha.emision")} </strong>{facturaImpresa.fechaEmision}</div>
                        <div class="encabezado"></strong>{facturaImpresa.empresa.direccion}                     </strong></div><br>
                        <table class = "forma-table"  >
                            <thead>
                                <tr class = "forma-table">
                                    <th class="cantidad">{$.i18n.prop("tikect.detalle.cantidad")}  </th>
                                    <th class="producto">{$.i18n.prop("tikect.detalle.descripcion")}</th>
                                    <th class="precio"> {$.i18n.prop("tikect.detalle.subTotal")}</th>
                                </tr>
                            </thead>
                        <tbody>
                            <tr class = "" each={detalles} class="detalleTables">
                                <td class="cantidad">{cantidad}</td>
                                <td class="producto">{articulo.descripcion}</td>
                                <td class="precio">₡{subTotal.toLocaleString('de-DE')}</td>
                            </tr>
                            </tr>
                            <tr>
                            <td colspan="3"></td>
                            </tr>
                            <tr class = "forma-table">
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.detalle.subTotal")}</strong></td>
                            <td ><strong>₡{subTotalGeneral.toLocaleString('de-DE') } </strong>  </td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.detalle.descuento")}</strong></td>
                            <td ><strong>₡{facturaImpresa.totalDescuento.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td><strong>{$.i18n.prop("tikect.detalle.impuesto")}</strong></td>
                            <td><strong>₡{facturaImpresa.totalImpuesto.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.detalle.total")}</strong></td>
                            <td ><strong>₡{facturaImpresa.totalComprobante.toLocaleString('de-DE')}</strong></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.totalCambioPagar")}</strong></td>
                            <td ><strong>₡{facturaImpresa.totalCambioPagar.toLocaleString('de-DE')}</strong></td>
                            </tr>                            
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.tipoCambio")}</strong></td>
                            <td ><strong>₡{facturaImpresa.tipoCambio.toLocaleString('de-DE')}</strong></td>
                            </tr>                            
                            <tr>
                            <td></td>
                            <td ><strong>{$.i18n.prop("tikect.Cambio")}</strong></td>
                            <td ><strong>$ {facturaImpresa.cambioMoneda.toLocaleString('de-DE')}</strong></td>
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
   __Teclas()

})


function getSubTotalGeneral(){
    var resultado = __valorNumerico(self.facturaImpresa.subTotal) + __valorNumerico(self.facturaImpresa.totalDescuento)
    self.subTotalGeneral = redondearDecimales(resultado,5)
    self.update()
}

/**
*  teclas de la pantalla
**/      
function __Teclas(){
    window.addEventListener( "keydown", function(evento){
       var tecla = evento.keyCode; 
        if(tecla ==119){
            __imprimir()
        }    
    }, false );
 
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
*imprimir
**/
function __imprimir(){
    var objeto=document.getElementById('imprimeme');  //obtenemos el objeto a imprimir
    var ventana=window.open('','_blank');  //abrimos una ventana vacía nueva
    ventana.document.write(objeto.innerHTML);  //imprimimos el HTML del objeto en la nueva ventana
    ventana.document.close();  //cerramos el documento
    ventana.print();  //imprimimos la ventana
    ventana.close();  //cerramos la ventana
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