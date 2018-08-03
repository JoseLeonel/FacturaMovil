<venta-servicios>
    <div class="row" show ={mostrarProductos}>
        <div  each={articulos.data} class="col-sx-12 col-sm-3 col-md-3 col-lg-3 " onclick={__formularioDetalle}>
            <div  class="thumbnail" style="background: #6dca42;">
                 <div class="caption">
                     <h3 class="title formItem">{descripcion}</h3>
                    
                </div>    
                
            </div>
        </div> 
    </div>    

<!-- Primer Paso -->

<div show={formularioDetalle}>
    <div class="row center "  >
        <div class=" col-sx-12 col-lg-2 "></div>
        <div class="col-md-12 col-lg-8 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">{$.i18n.prop("factura.primer.paso")} </h3>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario"   class="advanced-search-form formulario">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("factura.tipoDocumento")}</label> 
                                <select  onchange= {__formaReferencias} class="form-control has-success tipoDoc" id="tipoDoc" name="tipoDoc" >
                                    <option each={comboTipoDocumentos}  value="{estado}" selected="{factura.referenciaCodigo ==estado?true:false}" >{descripcion}</option>
                                </select>
                    
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("factura.detalle.descripcion.producto")} <span class="requeridoDato">*</span></label> 
                                <input type="text" class="form-control descripcionProducto" id="descripcionProducto" value = "{articulo.descripcion}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("factura.tipo.moneda")}</label> 
                                <select class="form-control has-success codigoMoneda" id="codigoMoneda" name="codigoMoneda" >
                                    <option each={monedas}  value="{estado}" selected="{factura.referenciaCodigo ==estado?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label">{$.i18n.prop("factura.detalle.total.producto")} <span class="requeridoDato">*</span></label> 
                                <input type="number" step="any" class="form-control precio" id="precio"  value = "{articulo.precioPublico}">
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarProductos}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={_PrimerPaso}  class="btn-green btn-siguiente pull-right " >  {$.i18n.prop("btn.siguiente")}</button>
                       </div>
                    </div>   
                </div>
            </div>   
        </div>
        <div class="col-lg-2 "></div>
    </div>
</div>




 

<div  show={formularioSegundoPaso}>
    <div class="row center "  >
        <div class=" col-sx-12 col-lg-2 "></div>
        <div class="col-md-12 col-lg-8 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">{$.i18n.prop("factura.segundo.paso")} </h3>
                </div>
                <div class="box-body">
                    <form id = "formularioPaso2" name ="formularioPaso2"   class="advanced-search-form">
                     
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>

                       
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label " >{$.i18n.prop("factura.condicion.pago")}</label>
                                <select onchange= {__formaPago} class="form-control has-success" id="condicionVenta" name="condicionVenta" >
                                    <option each={comboCondicionPagos}  value="{estado}" selected="{factura.condicionVenta ==estado?true:false}" >{descripcion}</option>
                                </select>
                            
                            </div>
                        </div>
                        <div class="row" show = {mostrarCamposIngresoCredito}>
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label " >{$.i18n.prop("factura.fecha.credito")}</label>
                                <div  class="form-group input-group date" data-provide="datepicker"  data-date-start-date="0d" data-date-format="yyyy-mm-dd">
                                    <input type="text" class="form-control fechaCredito" name="fechaCredito" id="fechaCredito" value="{factura.fechaCredito}" >
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" show = {mostrarCamposIngresoCredito}>
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.plazoCredito")}</label> 
                                <input type="number" id = "plazoCredito"  name "plazoCredito" class="form-control plazoCredito" value="{factura.plazoCredito}" >
                            </div>
                        </div>    
                        <div class="row" show={mostrarReferencias == false}>
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                <label class="knob-label ">{$.i18n.prop("factura.cliente")} <span class="requeridoDato">*</span></label> 
                                <input onclick = {_EscogerClientes}  type="text" id="nombreCliente" name="nombreCliente" class="form-control"  value="{cliente.nombreCompleto}" readonly>
                            </div>
                        </div>    
                        <div show={mostrarReferencias}>
                            <div class="row" >
                                <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <h3><label class="text-primary">{$.i18n.prop("informacion.referencias")}</label> </h3>
                                </div>
                            </div>    
                            <div class="row" >
                                <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <label class="knob-label ">{$.i18n.prop("informacion.numero")}</label> 
                                    <input type="text" onkeypress={__consultarConsecutivo} name = "referenciaNumero" id="referenciaNumero" class="form-control has-success referenciaNumero" value="{factura.referenciaNumero}" >
                                </div>
                            </div>    
                                <div class="row" >
                                <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <label class="knob-label">{$.i18n.prop("informacion.TipoDoc")}</label> 
                                    <select onchange= {__formaReferencias} class="form-control has-success" id="referenciaTipoDoc" name="referenciaTipoDoc" >
                                        <option each={comboTipoDocumentos} value="{estado}" selected="{factura.referenciaTipoDoc ==estado?true:false}" >{descripcion}</option>
                                    </select>
                                </div>
                            </div>    
                            <div class="row" >
                                <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <label class="knob-label">{$.i18n.prop("informacion.FechaEmision")}</label> 
                                    <div  class="form-group input-group date" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control referenciaFechaEmision" name="referenciaFechaEmision" id="referenciaFechaEmision" value="{factura.fechaCredito}" >
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>    
                            <div class="row" >
                                <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                    <label class="knob-label">{$.i18n.prop("informacion.codigo")}</label> 
                                    <select class="form-control has-success" id="referenciaCodigo" name="referenciaCodigo" >
                                        <option each={codigosReferencias}  value="{estado}" selected="{factura.referenciaCodigo ==estado?true:false}" >{descripcion}</option>
                                    </select>

                                </div>
                            </div>    

                        </div> 
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.total")}</label> 
                                <input type="number" step="any"  class="form-control "  value="{factura.totalComprobante.toLocaleString('de-DE')}" readonly>
                            </div>
                        </div>    
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.efectivo")}</label> 
                                <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress} step="any"   class="form-control totalEfectivo" id="totalEfectivo"  name="totalEfectivo"  value="0" >
                            </div>
                        </div>    
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.tarjeta")}</label> 
                                <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any" class="form-control totalTarjeta " id="totalTarjeta"  name="totalTarjeta"  value="0" >
                            </div>
                        </div>    
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.banco")}</label> 
                                <input onkeyup={ __TotalDeBancoAPagar} onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalBanco" id="totalBanco"  name="totalBanco"  value="0" >
                            </div>
                        </div>    
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.cambio")}</label> 
                                <input type="number" step="any"  class="form-control "  value="{factura.totalCambioPagar.toLocaleString('de-DE')}" readonly>
                            </div>
                        </div>   
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='cliente'                 name='cliente'                 value="{factura.cliente.id}" >
                            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
                            <input type="hidden" id='tipoDoc'                 name='tipoDoc'                 value="{factura.tipoDoc}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
                            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
                            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
                            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
                            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
                            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
                            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
                            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
                            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
                            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
                            <input type="hidden" id='totalDescuento'          name='totalDescuento'          value="{factura.totalDescuento}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
                            <input type="hidden" id='totalEfectivo'           name='totalEfectivo'           value="{factura.totalEfectivo}" >
                            <input type="hidden" id='totalTarjeta'            name='totalTarjeta'            value="{factura.totalTarjeta}" >
                            <input type="hidden" id='totalBanco'              name='totalBanco'              value="{factura.totalBanco}" >
                            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
                            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarPaso1}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__AplicarYcrearFactura}   class="btn-green btn-siguiente pull-right " >  {$.i18n.prop("btn.aplicar")}</button>
                       </div>
                    </div>   
                    <div class=" col-sx-12 col-lg-2 "></div>
                </div>
            </div>   
        </div>
      
    </div>
</div>






<!--Modal mostrar  -->
<div id="modalClientes" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header with-border table-header" >
                <h4 class="modal-title" id="title-add-note"> <i class='fa fa-th '></i> {$.i18n.prop("cliente.lista")}   </h4>
            </div>
            <div class="modal-body">
                <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                   <thead>
                        <th class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                        <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                        <th class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                        <th class="table-header">{$.i18n.prop("cliente.telefono")}          </th>
                        <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                    </thead>
                    <tfoot style="display: table-header-group;">
                        <tr>
                            <th>{$.i18n.prop("cliente.cedula")}           </th>
                            <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                            <th>{$.i18n.prop("cliente.correoElectronico")}</th>
                            <th>{$.i18n.prop("cliente.telefono")}         </th>
                            <th>                                          </th>
                        </tr>
                    </tfoot>                    
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>
<!--fin del modal-->


<STYLE TYPE="text/css" rel="stylesheet" type="text/css" media="all" >

        .formItem {
            color: #ffffff !important;
            display: block;
            text-align: center;
            line-height: 150%;
            font-size: .85em;
        }

        .tituloparrafo {
            margin-left: 10em;
            position: relative;
            color:#6dca42;
        }
        .parrafoservicio {
            margin-left: 1em;
            position: relative;
            color:#6dca42;
            
        }
        .price{
            font-weight:bold;
            font-size:20px;
            color: #0C9C22;
        }
        .bottonCar{
            font-weight:bold;
            font-size:14px;
            color: #6dca42;
        }
        
        .thumbnail .description{
            
            font-size:16px;
        }
        .title {
            margin-left: 0.5em;
            position: relative;
            color:#303a5b;
            margin: 0;
            font-size: 20px;
            font-weight: normal;
        }
        .view{
            position: relative;
            color:#21870a;
            margin: 0;
            font-size: 18px;
            font-weight: normal;
        }
        .ec-stars-wrapper {
            font-size: 50;
            display: inline-block;
        }
        .ec-stars-wrapper a {
            text-decoration: none;
            display: inline-block;
            /* Volver a dar tamaño al texto */
            font-size: 50px;
            font-size: 4rem;
            
            color: #888;
        }

        .ec-stars-wrapper:hover a {
            color: rgb(39, 130, 228);
        }
        /*
        * El selector de hijo, es necesario para aumentar la especifidad
        */
        .ec-stars-wrapper > a:hover ~ a {
            color: #888;
        }

</STYLE>
<script>
    var self = this;
    self.articulos             = {data:[]}
    self.mostrarReferencias    = false
    self.formularioDetalle     = false
    self.mostrarProductos      = true
    self.formularioSegundoPaso = false
    
    self.mostrarCamposIngresoCredito = false
    self.articulo             = {}
    self.detail                = []
    self.articulos             = {data:[]}
    self.clientes              = {data:[]}
    self.detalleFactura        = {data:[]}
    self.cliente               = {}
    

self.on('mount',function(){
    limpiar()
     $("#formulario").validate(reglasDeValidacion());
    __ListaDeArticulosPorEmpresa()
    __comboMonedas()
    __ComboTipoDocumentos()
    __comboCondicionPago()
    __ListaDeClientes()
    __comboCondicionPago()
    __combocodigosReferencia()
    
   
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
    
})

/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.factura.totalEfectivo = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico(e.target.value) 
    self.update()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico(e.target.value) 
    self.update()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    var sumaMontosEntregadosParaCambios =__valorNumerico(self.factura.totalTarjeta)
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
    sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
    //Si no ingresado montos no realiza las operaciones de calculos
    if(sumaMontosEntregadosParaCambios == 0){
        return
    }
    self.factura.totalCambioPagar = 0
    self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalComprobante ? sumaMontosEntregadosParaCambios - self.factura.totalComprobante:sumaMontosEntregadosParaCambios - self.factura.totalComprobante    
    self.update()
}
/**
*   Calculo del cambio entregar en el evento keyPress
**/
__CalculaCambioAEntregarKeyPress(e){
    var sumaMontosEntregadosParaCambios =0
    if (e.keyCode == 13) {
        sumaMontosEntregadosParaCambios  = __valorNumerico(self.factura.totalTarjeta)
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalBanco) 
        sumaMontosEntregadosParaCambios += __valorNumerico(self.factura.totalEfectivo) 
        if(sumaMontosEntregadosParaCambios == 0){
            return
        }
        self.factura.totalCambioPagar = 0
        self.factura.totalCambioPagar = sumaMontosEntregadosParaCambios > self.factura.totalComprobante ? sumaMontosEntregadosParaCambios - self.factura.totalComprobante:sumaMontosEntregadosParaCambios - self.factura.totalComprobante    
        self.update()
    }
}

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			descripcionProducto : {
				required : true,
                maxlength:250,
                minlength:1,
			},                                   
			precio : {
				required : true,
                minlength:1,
                 numeroMayorCero:true,
               
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};



__formaReferencias(e){
if($('#tipoDoc').val() !="01" && $('#tipoDoc').val() !="04"){
       self.mostrarReferencias            = true
       self.update()  
    }else{
        self.mostrarReferencias            = false
        self.update()
        $(".referenciaFechaEmision").val(null)
        $('.referenciaNumero').val(null)
        $('.referenciaRazon').val(null)
        $('.referenciaTipoDoc').prop("selectedIndex", 0);
        $('.referenciaCodigo').prop("selectedIndex", 0);
    }
}   


function limpiar(){
    $(".referenciaFechaEmision").val(null)
    $('.referenciaNumero').val(null)
    $('.referenciaRazon').val(null)
    $('.fechaCredito').val(null)
    $('.totalEfectivo').val(null)
    $('.totalBanco').val(null)
    $('.totalTarjeta').val(null)
    $('.plazoCredito').val(null)
    $('.referenciaNumero').val(null)
    $('.descripcionProducto').val(null)
    $('.precio').val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.tipoDoc').prop("selectedIndex", 0);
    $('.codigoMoneda').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    self.detail                = []
    self.factura = {}
    self.cliente = {}
    self.update()

      
}

/**
** Se aplica o se crea una Factura cargada en la pantalla
**/
__AplicarYcrearFactura(){
 aplicarFactura()
}

/**
* Aplicar la factura
**/
function aplicarFactura(){
    if(self.detail.length == 0 ){
        mensajeError($.i18n.prop("factura.alert.sin.detalles"))
        return
    }
    if($('#condicionVenta').val() == "02"  ){
        if($('#fechaCredito').val() == null || $('#fechaCredito').val() == 0){
           mensajeError($.i18n.prop("factura.alert.fechaCredito"))
            return
        }
        if($('#plazoCredito').val() < 0 || $('#plazoCredito').val() == null || $('#plazoCredito').val() == 0){
           mensajeError($.i18n.prop("factura.alert.plazoCredito"))
            return
        }
    }
     
if ($("#formularioPaso2").valid()) {
        swal({
           title: '',
           text: $.i18n.prop("factura.alert.crear"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            //Ajax__inicializarTabla();
            if(isConfirm){
               crearFactura()  
              
            }
        });
    }


}

/**
*  Crear Factura nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date() 
     var JSONDetalles = JSON.stringify( self.detalleFactura );
    
    self.factura.id = self.factura.id
    self.factura.cliente = self.cliente
    self.factura.estado = 2
    self.factura.codigoMoneda = $('#codigoMoneda').val()
    self.factura.tipoDoc = $('#tipoDoc').val()
    self.factura.condicionVenta = $('#condicionVenta').val()
    self.factura.fechaCredito =fechaCreditoTemporal.toString()
    self.factura.referenciaFechaEmision =fechaReferencia
    self.factura.totalEfectivo =$('#totalEfectivo').val()
    self.factura.totalTarjeta = redondearDecimales(__valorNumerico($('#totalTarjeta').val())) 
    self.factura.totalBanco = redondearDecimales(__valorNumerico($('#totalBanco').val()))
    self.factura.detalleFactura =JSONDetalles
    self.update();
    
    var formulario = $("#formularioPaso2").serialize();
     
                    
    $.ajax({
        type : "POST",
        dataType : "json",
        data : formulario,
        url : "CrearFacturaAjax",
        success : function(data) {
            if (data.status != 200) {
               	serverMessageJsonClase(data);
                if (data.message != null && data.message.length > 0) {
                    mensajeError(data.message)
                }
            } else {
               	serverMessageJsonClase(data);
                //evaluarFactura(data)
                 swal({
	                title: '',
	                text: $.i18n.prop("factura.creacion.exitosa"),
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
                })
                __regresarProductosF()
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
*Si fue facturada o tiquete
**/
function evaluarFactura(data){
   if (data.message != null && data.message.length > 0) {
        $.each(data.listaObjetos, function( index, modeloTabla ) {
            self.facturaImprimir   = modeloTabla
            self.update()
            if(self.facturaImprimir.estado == 2){
                limpiar()
                //Envia a la pantalla de impresion
                 riot.mount('ptv-imprimir',{factura:self.facturaImprimir});
                 
            }else{
                swal({
	                title: '',
	                text: data.message,
	                type: 'success',
	                showCancelButton: false,
	                confirmButtonText: $.i18n.prop("btn.aceptar"),
                })
                limpiar()
                
            }
        });
    }


}
/**
*  Agregar un articulo si existe se suma la cantidad y no existe se agrega en el detalle
**/
function __agregarArticulo(cantidad){
    self.detail = []
    self.update()
     __nuevoArticuloAlDetalle(cantidad);
    __calculate(); 
}
/**
*   agregar Articulos nuevos en el detalle de la factura
**/
function __nuevoArticuloAlDetalle(cantidad){
    if(self.articulo.descripcion == null){
        return;
    }
    if(self.articulo.descripcion == ""){
        return;
    }
    var precioT = parseFloat($('#precio').val())
    var precioUnitario  = getPrecioUnitario(precioT)
    
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
    var montoImpuesto   = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto 
    self.detail.push({
       numeroLinea     : 1,
       tipoImpuesto    : self.articulo.tipoImpuesto,
       iva             : parseFloat(self.articulo.impuesto),
       codigo          : self.articulo.codigo,
       descripcion     : $('.descripcionProducto').val(),
       cantidad        : parseFloat(cantidad),
       precioUnitario  : precioUnitario,
       impuesto        : parseFloat(self.articulo.impuesto),
       montoImpuesto   : montoImpuesto,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       subTotal        : subTotal,
       montoTotalLinea : montoTotalLinea,
       montoTotal      :montoTotal
    });
    var cont = 0;
    self.detail.forEach(function(elemen){
          elemen.numeroLinea = cont + 1
          cont = elemen.numeroLinea
        }
    )
    self.update()
}

function getMontoTotal(precioUnitario,cantidad){
    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
    return redondearDecimales(resultado ,5);
}


/**
* Obtiene el precio unitario sin descuento sin impuesto
**/
function getPrecioUnitario(precio){
   return redondearDecimales(precio,5)     
  
}
/**
 * calculo del impuesto iva
 * */
function _calcularImpuesto(precio,iva){
    if(iva == 0){
        return 0;
    }
    var impuesto = iva > 0 ?parseFloat(iva)/100:0
    impuesto = impuesto > 0 ?impuesto:0
    var total = precio * impuesto
    return redondearDecimales(total ,5)
}


/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.total           = 0;
    self.factura.totalDescuento  = 0;
    self.factura.totalImpuesto   = 0;
    self.factura.subTotal        = 0;
    self.update()
    totalVenta     = 0
    subTotal       = 0
    totalDescuento = 0
    totalImpuesto  = 0
    totalMercanciasGravadas = 0
    totalMercanciasExentas  = 0

    totalServGravados       = 0
    totalServGravados       = 0
    totalGravado            = 0
    totalExento             = 0
    totalComprobante        = 0
    totalventaNeta          = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0 && e.tipoImpuesto != "07"?e.subTotal:0
        totalMercanciasExentas  += e.impuesto == 0 && e.tipoImpuesto != "07"?e.subTotal:0
        totalServGravados       += e.montoImpuesto > 0 && e.tipoImpuesto == "07"?e.subTotal:0
        totalServExentos        += e.impuesto == 0 && e.tipoImpuesto == "07"?e.subTotal:0
        totalGravado            += e.impuesto > 0 ?e.subTotal:0
        totalExento             += e.impuesto == 0?e.subTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += e.montoImpuesto >0?e.montoImpuesto:0
        totalVenta              += e.montoTotal
    });
    self.factura.totalMercanciasGravadas = redondearDecimales(__valorNumerico(totalMercanciasGravadas),5)
    self.factura.totalMercanciasExentas  = redondearDecimales(__valorNumerico(totalMercanciasExentas),5)
    self.factura.totalServGravados       = redondearDecimales(__valorNumerico(totalServGravados),5)
    self.factura.totalServExentos        = redondearDecimales(__valorNumerico(totalServExentos),5)

    self.factura.totalGravado            = redondearDecimales(__valorNumerico(totalGravado),5)
    self.factura.totalExento             = redondearDecimales(__valorNumerico(totalExento),5)
    self.factura.totalVenta              = redondearDecimales(totalVenta,5)
    self.factura.totalDescuento          = redondearDecimales(__valorNumerico(totalDescuento),5)
    self.factura.subTotal                = redondearDecimales(__valorNumerico(subTotal),5)
    self.factura.totalImpuesto           = redondearDecimales(__valorNumerico(totalImpuesto),5)
    self.factura.totalVentaNeta          = redondearDecimales(__valorNumerico(subTotal),5)
    self.factura.totalComprobante        = redondearDecimales(__valorNumerico(totalComprobante),5)
    self.update(); 
}




function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
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

/**
*    Muesta el campo de la fecha de credito
**/
__formaPago(e){
    //Contado /sin cobro
    if(e.currentTarget.value == "01" || e.currentTarget.value == "04" ){
        

        self.mostrarCamposIngresoCredito = false
    }else{
        self.mostrarCamposIngresoCredito = true 
    }
}    
__regresarProductos(){

  __regresarProductosF()
}
function __regresarProductosF(){
    self.mostrarProductos  = true
    self.formularioSegundoPaso = false
    self.formularioDetalle = false
    self.cliente = {}
    self.articulo ={}
    self.update()
    limpiar()

}

__regresarPaso1(){
    
        self.formularioSegundoPaso = false
        self.formularioDetalle = true
        self.update()

     
}

__formularioDetalle(e){
    self.articulo =e.item;
    self.formularioDetalle = true
    self.mostrarProductos  = false
    self.update()
    __agregarArticulo(1)

}


_PrimerPaso(){
   validar()    


}

function validar(){
    if($('.precio').val() == null){
         mensajeError($.i18n.prop("factura.error.precio.igual.cero"))
        return
    }

    if($('.precio').val() <= 0){
         mensajeError($.i18n.prop("factura.error.precio.igual.cero"))
        return
    }
    if($('.descripcionProducto').val() == null){
         mensajeError($.i18n.prop("factura.error.descripcion.igual.blanco"))
        return
    }

    if($('.descripcionProducto').val() == ""){
         mensajeError($.i18n.prop("factura.error.descripcion.igual.blanco"))
        return
    }
     if ($(".formulario").valid()) {
        self.formularioSegundoPaso = true
        self.formularioDetalle = false
        self.update()
        __agregarArticulo(1)
     }  

}

_SegundoPaso(){
   
    self.formularioSegundoPaso = false
    self.update()

}

/**
*  Muestra la lista de clientes
**/
_EscogerClientes(){
    $('#modalClientes').modal('show')  
}


/**
* cargar los codigos de referencias
**/
function __combocodigosReferencia(){
    self.codigosReferencias = []
    self.update()
    self.codigosReferencias.push({
         estado:"01",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.electronica")
    })
    self.codigosReferencias.push({
         estado:"02",
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.debito")
    })
    self.codigosReferencias.push({
         estado:"03",
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.credito")
    })
     self.codigosReferencias.push({
        estado:"04",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.tiquete")
    })

     self.codigosReferencias.push({
        estado:"05",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.nota.despacho")
    })
    self.codigosReferencias.push({
        estado:"06",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.contrato")
    })
    self.codigosReferencias.push({
        estado:"07",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.procedimiento")
    })
    self.codigosReferencias.push({
        estado:"08",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.comprobante.contigencia")
    })
    self.codigosReferencias.push({
        estado:"99",
        descripcion:$.i18n.prop("referencia.tipo.documento.factura.otros")
    })
    self.update()
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
*  Lista de los clientes
**/
function __ListaDeClientes(){
    $.ajax({
        url: 'ListarClientesActivosAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
                loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
                agregarInputsCombos_Clientes()
                ActivarEventoFiltro(".tableListaCliente")
                __seleccionarClientes()
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
* formato de la tabla de clientes
**/
function __informacionData(){
    self.informacion_tabla_clientes = [	
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false},
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("cliente.telefono")          ,"autoWidth":false},                                
                                        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opcionesclientes(id,type,row);
	 							                }	 
								            },
                                        ];                              
   
}
/**
* Opciones del modal de clientes
*/
function __Opcionesclientes(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente de la factura" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}
/**
* Agregar codigos a la factura desde modal de articulos
**/
function __seleccionarClientes() {
     $('#tableListaCliente').on('click', '.btnAgregar', function (e) {
         var table = $('#tableListaCliente').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	     }
        self.cliente = data
        self.update();
    });
}

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Clientes(){
     // Agregar los input de busqueda 
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 6    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 

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
* cargar los codigos de tipo de documentos
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
* cargar los codigos de monedas
**/
function __comboMonedas(){
    self.monedas = []
    self.update()
    self.monedas.push({
        estado:"CRC",
        descripcion:$.i18n.prop("factura.moneda.cr")
    })
    self.monedas.push({
        estado:"USD",
        descripcion:$.i18n.prop("factura.moneda.dollar")
    })    
 
    self.update()
}

/**
* mostrar la lista de articulos de la empresa
**/
function __ListaDeArticulosPorEmpresa(){
    self.articulos             = {data:[]}
    self.update()
    $.ajax({
        url: 'ListarArticuloAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.articulos.data           = result.aaData
                self.update()
                
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
</script>
</venta-servicios>