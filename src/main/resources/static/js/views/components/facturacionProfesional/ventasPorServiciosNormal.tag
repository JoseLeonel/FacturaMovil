<ventasPorServiciosNormal>
    <div id="containerArticulos" show ={mostrarProductos}>
        <div  each={articulos.data}  onclick={__formularioDetalle}>
            <div  class="itemArticulos">
                 <div class="carrito" >
                    <img  style = "width:50px;height:50px;" alt="" class="img-responsive " src="data:image/png;base64,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">
                    <span class="label-titulos-articulo">{descripcion.length > 50 ? descripcion.substring(0,50) +"...":descripcion}</span>
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
                    <label class="box-title pull-right">Tipo Cambio Dolar $ {tipoCambio.total} Normal Banco Central</label>
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
                                    <option each={comboTipoDocumentos}  value="{estado}" selected="{factura.tipoDoc ==estado?true:false}" >{descripcion}</option>
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
                                    <option each={monedas}  value="{estado}" selected="{factura.codigoMoneda ==estado?true:false}" >{descripcion}</option>
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
                                <label class="titleListaPrecio">Actividades Comerciales </label>  
                                <select onchange= {__AsignarActividad} class="form-control selectActividadComercial"  name="selectActividadComercial" id="selectActividadComercial" >
                                    <option  each={empresaActividadComercial}  value="{codigo}"   >{codigo}-{descripcion}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.impuesto")}</label> 
                                <input type="text" step="any"  class="form-control "  value="{totalImpuesto}" readonly>
                            </div>
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.exoneracion")}</label> 
                                <input type="text" id = "montoExoneracion" step="any"  class="form-control "  value="{montoExoneracion}" readonly>
                            </div>

                        </div>    
                        

                        <div class="row" >
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.total.servicios")}</label> 
                                <input type="number" step="any"  class="form-control "  value="{factura.totalComprobante.toFixed(2)}" readonly>
                            </div>
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.efectivo")}</label> 
                                <input onkeyup={ __TotalDeEfectivoAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress} step="any"   class="form-control totalEfectivo" id="totalEfectivo"  name="totalEfectivo"  value="{factura.totalComprobante.toFixed(2)}" >
                            </div>

                        </div>    
                        <div class="row" >
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.tarjeta")}</label> 
                                <input onkeyup={ __TotalDeTarjetaAPagar } onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any" class="form-control totalTarjeta " id="totalTarjeta"  name="totalTarjeta"  value="0" >
                            </div>
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.banco")}</label> 
                                <input onkeyup={ __TotalDeBancoAPagar} onBlur = {__CalculaCambioAEntregarOnblur}  type="number" onkeypress = {__CalculaCambioAEntregarKeyPress}  step="any"  class="form-control totalBanco" id="totalBanco"  name="totalBanco"  value="0" >
                            </div>

                        </div>    
                        <div class="row" >
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.cambio")}</label> 
                                <input type="number" step="any"  class="form-control "  value="{totalCambioPagar}" readonly>
                            </div>
                        </div>   
                            <input type="hidden" id='codigoActividad'         name='codigoActividad'         value="{factura.codigoActividad}" >
                            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
                            <input type="hidden" id='cliente'                 name='cliente'                 value="{factura.cliente.id}" >
                            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
                            <input type="hidden" id='tipoCambioMoneda'        name='tipoCambioMoneda'        value="{tipoCambio.total}" >
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
                            <input type="hidden" id='totalDescuentos'          name='totalDescuentos'          value="{factura.totalDescuentos}" >
                            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
                            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
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
                <div class="row">
                     <div class="col-sx-12 col-sm-12 col-md-12 col-lg-12 ">
                        <table id="tableListaCliente" class="table responsive display table-striped table-hover nowrap tableListaCliente " cellspacing="0" width="100%">
                        <thead>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}          </th>
                                <th class="table-header">{$.i18n.prop("cliente.cedula")}            </th>
                                <th class="table-header">{$.i18n.prop("cliente.nombreCompleto")}    </th>
                                <th class="table-header">{$.i18n.prop("cliente.correoElectronico")} </th>
                                <th class="table-header">{$.i18n.prop("cliente.telefono")}          </th>
                            </thead>
                            <tfoot style="display: table-header-group;">
                                <tr>
                                    <th>                                          </th>
                                    <th>{$.i18n.prop("cliente.cedula")}           </th>
                                    <th>{$.i18n.prop("cliente.nombreCompleto")}   </th>
                                    <th>{$.i18n.prop("cliente.correoElectronico")}</th>
                                    <th>{$.i18n.prop("cliente.telefono")}         </th>
                                    
                                </tr>
                            </tfoot>                    
                        </table>
                    </div>
                </div>        
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-dark-gray btn-back pull-left"  data-dismiss="modal">{$.i18n.prop("btn.volver")}</button>
            </div>
        </div>
    </div>
</div>


<!--fin del modal-->
<STYLE TYPE="text/css" rel="stylesheet" type="text/css" media="all" >
       

</STYLE>
<script>
    var self = this;
    self.articulos             = {data:[]}
    self.mostrarReferencias    = false
    self.actividadesComerciales        = []
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
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:0,
            nombreCompleto:""
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }

    } 
    self.actividadComercial = {
        codigo:"",
        descripcion:""
    }
    self.montoExoneracion     = 0
    self.montoExoneracion1    = 0    
    self.tipoCambio = {
        total:0,
        id:null
    }
self.on('mount',function(){
    limpiar()
     $("#formulario").validate(reglasDeValidacion());
    __ListaDeArticulosPorEmpresa()
    __comboMonedas()
    __ComboTipoDocumentos(1)
    __comboCondicionPago()
    __ListaDeClientes()
    __comboCondicionPago()
    __ListaActividadesComercales()
    __combocodigosReferencia()
    getTipoCambioDolar()
      __seleccionarClientes()
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
    
})

/**
*Tipo Cambio del Dolar 
**/
function getTipoCambioDolar(){
    $.ajax({
    "url": "https://api.hacienda.go.cr/indicadores/tc",
    "method": "GET",
     global: false,
    statusCode: {
        
        404: function() {
             __TipoCambio()
        }
    }
    }).done(function (response) {
        self.tipoCambio.total = __valorNumerico(response.dolar.venta.valor)
        self.tipoCambio.totalCompra = __valorNumerico(response.dolar.compra.valor)
        self.update()
    
    });
}
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    self.tipoCambio = {
        total:0,
        id:null
    }
    self.update()
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.tipoCambio = modeloTabla
                       self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}

/**
*  Obtiene el valor de lo digitado en el campo de efectivo
**/
__TotalDeEfectivoAPagar(e){
    self.totalCambioPagarSTR = 0
    self.factura.totalEfectivo = __valorNumerico($("#totalEfectivo").val()) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Tarjeta
**/
__TotalDeTarjetaAPagar(e){
    self.factura.totalTarjeta = __valorNumerico($("#totalTarjeta").val()) 
    self.update()
    _calculoEnterPago()
}
/**
*  Obtiene el valor de lo digitado en el campo de Banco
**/
__TotalDeBancoAPagar(e){
    self.factura.totalBanco = __valorNumerico($(".totalBanco").val()) 
   
    self.update()
    _calculoEnterPago()
}
/**
*   Calculo del cambio entregar en el evento onblur
**/
__CalculaCambioAEntregarOnblur(e){
    _calculoEnterPago()
}


function _calculoEnterPago(){
        var sumaMontosEntregadosParaCambios  = __valorNumerico($('.totalTarjeta').val())
        sumaMontosEntregadosParaCambios += __valorNumerico($('#totalBanco').val()) 
        sumaMontosEntregadosParaCambios += __valorNumerico($('.totalEfectivo').val())   
        if(sumaMontosEntregadosParaCambios == 0){
            self.factura.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagar =  __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)) * -1
            self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)    
            self.update()
            return
        }
        self.factura.totalCambioPagar = 0
        var totalEntregado = __valorNumerico(redondeoDecimales(sumaMontosEntregadosParaCambios,2))
        var totalFactura   = __valorNumerico(redondeoDecimales(self.factura.totalComprobante,2))
        totalEntregado     = __valorNumerico(totalEntregado)
        totalFactura       = __valorNumerico(totalFactura)  
        self.factura.totalCambioPagar = totalEntregado - totalFactura
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
        self.totalCambioPagar = __valorNumerico(redondeoDecimales(self.factura.totalCambioPagar,2))
        self.totalCambioPagarSTR = formatoDecimales(self.totalCambioPagar,2)
        self.update()
}


/**
*  Lista de los clientes
**/
function __ListaActividadesComercales(){
    $.ajax({
        url: 'ListaEmpresaActividadComercialPorPricipalAjax.do',
        datatype: "json",
         global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.empresaActividadComercial   = result.aaData
                self.update()
                BuscarActividadComercial()

            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
    return
}

__AsignarActividad(e){
    BuscarActividadComercial()
}

function BuscarActividadComercial(){
    var codigo =$('#selectActividadComercial').val()
    if(self.empresaActividadComercial == null){
       return    
    }
    if(self.empresaActividadComercial.length == 0){
       return    
    }
    $.each(self.empresaActividadComercial, function( index, modeloTabla ) {
        if(modeloTabla.codigo == codigo  ){
           self.actividadComercial.descripcion = modeloTabla.codigo +"-" + modeloTabla.descripcion
            self.actividadComercial.codigo =  codigo
            self.factura.codigoActividad = codigo
            self.update()
        }

    })
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
        self.factura.totalCambioPagar = self.factura.totalComprobante * -1
        self.update()
        return
    }
    self.factura.totalCambioPagar = 0
    var totalEntregado = redondeoDecimales(sumaMontosEntregadosParaCambios,2)
    var totalFactura   = redondeoDecimales(self.factura.totalComprobante,2)
    totalEntregado     = __valorNumerico(totalEntregado)
    totalFactura       = __valorNumerico(totalFactura)  
    self.factura.totalCambioPagar = totalEntregado - totalFactura
    self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
    self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
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
            self.factura.totalCambioPagar = self.factura.totalComprobante * -1
            self.update()
            return
        }
        self.factura.totalCambioPagar = 0
        var totalEntregado = redondeoDecimales(sumaMontosEntregadosParaCambios,2)
        var totalFactura   = redondeoDecimales(self.factura.totalComprobante,2)
        totalEntregado     = __valorNumerico(totalEntregado)
        totalFactura       = __valorNumerico(totalFactura)  
        self.factura.totalCambioPagar = totalEntregado - totalFactura
        self.factura.totalCambioPagar =__valorNumerico(self.factura.totalCambioPagar)   
        self.totalCambioPagar = redondeoDecimales(self.factura.totalCambioPagar,2)
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
                maxlength:160,
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
}
/**
* Datos de referencia cuando se aplica una nota de credito
**/
__formaReferencias(e){
    if($('#tipoDoc').val() !="01" && $('#tipoDoc').val() !="04"){
       self.mostrarReferencias  = true
       self.update()  
    }else{
        self.mostrarReferencias = false
        self.update()
        $(".referenciaFechaEmision").val(null)
        $('.referenciaNumero').val(null)
        $('.referenciaRazon').val(null)
        $('.referenciaTipoDoc').prop("selectedIndex", 0);
        $('.referenciaCodigo').prop("selectedIndex", 0);
    }
}   
/**
limpiar datos
**/
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
    $(".descripcionProducto").attr("maxlength",160 );
    $('.precio').val(null)
    $('.referenciaTipoDoc').prop("selectedIndex", 0);
    $('.tipoDoc').prop("selectedIndex", 0);
    $('.codigoMoneda').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.condicionVenta').prop("selectedIndex", 0);
    $('.selectActividadComercial').prop("selectedIndex", 0);
    self.detail                = []
    self.cliente = {}
    self.factura                = {
        id:null,
	   fechaCredito:null,
	   fechaEmision:null,
	   condicionVenta:"",
	    plazoCredito:0,
	    tipoDoc:"",
	    medioPago:"",
	    nombreFactura:"",
	    direccion:"",
	    nota:"",
	    comanda:"",
	    subTotal:0,
	    totalTransporte:0,
	    total:0,
	    totalServGravados:0,
	    totalServExentos:0,
	    totalMercanciasGravadas:0,
	    totalMercanciasExentas:0,
	    totalGravado:0,
	    totalExento:0,
	    totalVenta:0,
	    totalDescuentos:0,
	    totalVentaNeta:0,
	    totalImpuesto:0,
	    totalComprobante:0,
	    totalEfectivo:0,
        totalTarjeta:0,
        totalCambioPagar:0,
	    totalBanco:0,
	    totalCredito:0,
	    montoCambio:0,
	    totalCambio:0,
	    codigoMoneda:"",
	    estado:1,
	    cliente:{
            id:0,
            nombreCompleto:""
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    } 
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

    if($("#tipoDoc").val() !="88"){
        if(__valorNumerico($('#totalTarjeta').val()) == 0 && __valorNumerico($('#totalBanco').val()) == 0 && __valorNumerico($('#totalEfectivo').val()) == 0){
            mensajeError($.i18n.prop("error.factura.monto.ingresado"))
            return
        }
        var montoEntregado = __valorNumerico($('#totalTarjeta').val())  + __valorNumerico($('#totalBanco').val()) + __valorNumerico($('#totalEfectivo').val())
        montoEntregado = redondeoDecimales(__valorNumerico(montoEntregado),2)
        if(montoEntregado > 20000000){
           mensajeError("Monto entregado es muy alto")
           return
        }
        var resultado  = redondeoDecimales( __valorNumerico(self.factura.totalComprobante),2)
        if(__valorNumerico(resultado) > __valorNumerico(montoEntregado)  ){
            mensajeError($.i18n.prop("error.factura.monto.ingresado.es.menor.ala.venta"))
            return
        }
        //Si el cliente esta pagando con tajeta, banco debe ser igual a la venta
        var tarjeta = __valorNumerico(self.factura.totalTarjeta)
        var banco = __valorNumerico(self.factura.totalBanco)
        if(tarjeta != 0 || banco !=0){
            if(resultado != montoEntregado  ){
                mensajeError($.i18n.prop("error.factura.monto.tarjeta.banco.igual.venta"))
                return
            }
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
    BuscarActividadComercial()
    if( self.factura.codigoActividad.length == 0 ){
      mensajeError($.i18n.prop("error.factura.actividad.comercial.no.existe"))
      return
    }
    self.detalleFactura.data =self.detail
    self.update() 
    var fechaCreditoTemporal =condicionVenta.value == "02"?fechaCredito.value:new Date() 
    var fechaReferencia =$('#referenciaFechaEmision').val() !=null?referenciaFechaEmision.value:new Date() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.id = self.factura.id
    self.factura.cliente = self.cliente
    self.factura.estado = 2
    self.factura.codigoMoneda = $('#codigoMoneda').val()
    self.factura.tipoCambio = self.tipoCambio.total
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
                evaluarFactura(data)
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
                 riot.mount('fact-servImprimir',{factura:self.facturaImprimir});
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
    var resultaMontoImpuesto = parseFloat(self.articulo.impuesto)
    var precioUnitario  = precioT
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
      var montoImpuesto   = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoTotalLinea = subTotal + montoImpuesto   
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:parseFloat(self.articulo.costo),cantidad)
    self.detail.push({
       descripcion     : $('.descripcionProducto').val(),
       numeroLinea     : 1,
       pesoPrioridad   : 1,  
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?" ":self.articulo.tipoImpuesto,
       tipoImpuesto1   : " ",
       iva             : parseFloat(self.articulo.impuesto),
       iva1            : 0,
       codigo          : self.articulo.codigo,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : parseFloat(precioUnitario),
       impuesto        : parseFloat(self.articulo.impuesto),
       impuesto1        : 0,
       montoImpuesto   : parseFloat(montoImpuesto),
       montoImpuesto1  : 0,
       montoDescuento  : 0,
       porcentajeDesc  : 0,
       ganancia        : parseFloat(ganancia),
       subTotal        : parseFloat(subTotal),
       montoTotalLinea : parseFloat(montoTotalLinea),
       montoTotal      : parseFloat(montoTotal),
       costo           : self.articulo.costo ==null?0:parseFloat(self.articulo.costo),
       porcentajeGanancia :   self.articulo.gananciaPrecioPublico ==null?0:parseFloat(self.articulo.gananciaPrecioPublico),
        montoExoneracion:0,
       montoExoneracion1:0,
       porcentajeExoneracion:0,
       fechaEmisionExoneracion:null,
       nombreInstitucionExoneracion:"",
       numeroDocumentoExoneracion:"",
       tipoDocumentoExoneracion:""
    });
    self.update()
}
/**
* Calcular el monto Total
**/
function getMontoTotal(precioUnitario,cantidad){
    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
    return resultado ;
}
/**
* Obtiene el precio unitario sin descuento sin impuesto
**/
function getPrecioUnitario(precio ,impuesto){
   var porcentajeImpuesto = 0
   var resultado  = 0
   if(impuesto > 0){
      porcentajeImpuesto = impuesto / 100
      porcentajeImpuesto =  porcentajeImpuesto + 1
      resultado  =  precio  / porcentajeImpuesto
   }else{
       resultado  =  precio
   }
   return resultado     
}

/**
* calculacion de los detalle de la factura 
**/
function __calculate() {
    self.factura.total            = 0;
    self.factura.totalDescuentos  = 0;
    self.factura.totalImpuesto    = 0;
    self.factura.subTotal         = 0;
    self.update()
                      //Factura.js
    var resultado = __ResumenFactura(self.detail,self.factura);
    self.factura = resultado.factura
    self.cantArticulos = resultado.cantArticulos
    self.totalGananciaByProducto = formatoDecimales(parseFloat(resultado.totalGananciaByProducto),2)
    self.totalPesoByFactura = __valorNumerico(resultado.totalPesoByFactura)
    self.totalPesoByFacturaSTR = formatoDecimales(resultado.totalPesoByFactura,2);
    self.totalComprobante = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto = formatoDecimales(self.factura.totalImpuesto,2);
    self.montoExoneracion = resultado.montoExoneracion > 0 ?formatoDecimales(resultado.montoExoneracion,2):"";
    $('#montoExoneracion').val(self.montoExoneracion);
    
    self.totalDescuentos = formatoDecimales(self.factura.totalDescuentos,2)
    self.subTotalGeneral = resultado.subTotalGeneral
    var resultadoTotalImpuesto = __valorNumerico(self.factura.totalImpuesto) 
    self.totalImpuesto = resultado.totalImpuesto
    self.update()

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
/**
*Regresar a la pantalla de producto
**/
function __regresarProductosF(){
    self.mostrarProductos  = true
    self.formularioSegundoPaso = false
    self.formularioDetalle = false
    self.cliente = {}
    self.articulo ={}
    self.update()
    limpiar()
}
/**
* Regresar al paso 2
**/
__regresarPaso1(){
    self.formularioSegundoPaso = false
    self.formularioDetalle = true
    self.update()
}
/**
* Formulario de detalle
**/
__formularioDetalle(e){
    self.articulo =e.item;
    self.formularioDetalle = true
    self.mostrarProductos  = false
    self.update()
    __agregarArticulo(1)
}
/**
* Primer paso 
**/
_PrimerPaso(){
   validar()    
}
/**
* validaciones
**/
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
     $('.totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
}
/**
* Segundo paso
**/
_SegundoPaso(){
    self.formularioSegundoPaso = false
    self.update()
     $('.totalEfectivo').val(self.factura.totalComprobante.toFixed(2))
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
            estado:'01',
            descripcion:$.i18n.prop("referencia.anula.documento")
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
         global: false,
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                __informacionData()
                loadListar(".tableListaCliente",idioma_espanol,self.informacion_tabla_clientes,result.aaData)
                agregarInputsCombos_Clientes()
                ActivarEventoFiltro(".tableListaCliente")
              
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
                                       {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
									            "render":function(id,type, row){
										            return __Opcionesclientes(id,type,row);
	 							                }	 
								            },
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false,
        									"render":function(cedula,type, row){
        										return stringVacio(cedula)?cedula:row.identificacionExtranjero;
        									}
                                        
                                        },
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false},
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false},
                                        {'data' : 'telefono'         ,"name":"telefono"          ,"title" : $.i18n.prop("cliente.telefono")          ,"autoWidth":false},                                
                                       
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
         if(!verificarSiClienteFrecuente(self.cliente)){
            self.factura.tipoDoc ='01'
            if(stringVacio(self.cliente.identificacionExtranjero)== false){
               self.factura.tipoDoc ='01'
               self.update()
               if(self.item != null){
                if(self.item.tipoDocumentoExoneracion !=null){
                    if(self.item.tipoDocumentoExoneracion =='02'){
                        self.factura.tipoDoc ='04'  
                        self.update()
                        }
                }
               }
               __aplicarExoneracionPorCliente()
               __ComboTipoDocumentos(1)
            }else{
               self.factura.tipoDoc ='04'
               self.update()
               __ComboTipoDocumentos(0)
            }
            
           
        }else{
            self.factura.tipoDoc = "04";
            __ComboTipoDocumentos(0)
        }
         $('#modalClientes').modal('hide') 
    });
}
/**
* Aplicar la exoneracion de detalles
**/
/**
* Aplicar la exoneracion de detalles
**/

function __aplicarExoneracionPorCliente(){
    var aplicaExo = false
    var porcentaje = __valorNumerico(self.cliente.porcentajeExoneracion )
    if(porcentaje == 0){
        return
    }
    var valorTotal = 0
    for (var count = 0; count < self.detail.length; count++) {
        self.item          = self.detail[count];
        self.cliente.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
            if(self.item.montoImpuesto > 0 || self.item.montoImpuesto1 > 0 ){
                if(self.cliente.porcentajeExoneracion > 0  ){
                    self.item.porcentajeExoneracion = getPorcentajeExoneracion(__valorNumerico(self.cliente.porcentajeExoneracion),self.item.impuesto )
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    self.item.montoExoneracion = getMontoExoneracionSubTotal(self.item.tipoDocumentoExoneracion,self.item.impuesto, self.item.porcentajeExoneracion, self.item.subTotal, self.item.montoImpuesto)   
                    self.item.ImpuestoNeto = self.item.montoImpuesto - self.item.montoExoneracion
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.update();
                    aplicaExo= true
                }else{

                    self.item.porcentajeExoneracion = 0
                    self.item.fechaEmisionExoneracion = null
                    self.item.nombreInstitucionExoneracion = ""
                    self.item.numeroDocumentoExoneracion = ""
                    self.item.tipoDocumentoExoneracion = ""
                    self.item.montoExoneracion = 0
                    self.item.montoExoneracion1 = 0
                    self.item.ImpuestoNeto = __valorNumerico(self.item.montoImpuesto) 
                    self.item.montoTotalLinea = self.item.subTotal +  self.item.ImpuestoNeto
                    self.detail[count] = self.item;
                    self.totalCambioPagar = 0
                    self.totalCambioPagarSTR = 0
                    self.factura.totalEfectivo =0
                    self.factura.totalTarjeta =0
                    self.factura.totalBanco =0
                    self.factura.totalCambioPagar = self.factura.totalComprobante
                    self.update();

                    aplicaExo = true
                }

            }else{
                self.item.porcentajeExoneracion = 0
                self.item.fechaEmisionExoneracion = null
                self.item.nombreInstitucionExoneracion = ""
                self.item.numeroDocumentoExoneracion = ""
                self.item.tipoDocumentoExoneracion = ""
                self.item.montoExoneracion = 0
                self.item.montoExoneracion1 = 0

            }
    }
    
    __calculate()
    if(aplicaExo == true){
       self.factura.totalCambioPagar = self.factura.totalComprobante
       self.factura.totalEfectivo =0
       self.factura.totalTarjeta =0
       self.factura.totalBanco =0
       self.totalCambioPagar = 0
       self.totalCambioPagarSTR = 0
       self.update();
    }
    $('#totalEfectivo').val(__valorNumerico(redondeoDecimales(self.factura.totalComprobante,2)))
    $('#totalTarjeta').val(null)
    $('#totalBanco').val(null)
    $('#totalEfectivo').focus()
    $('#totalEfectivo').select()
}

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos_Clientes(){
     // Agregar los input de busqueda 
    $('.tableListaCliente tfoot th').each( function (e) {
        var title = $('.tableListaCliente thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 0    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
} 
/**
*Condicion de Pago
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
* cargar los codigos de tipo de documentos
**/
function __ComboTipoDocumentos(valor){
    self.comboTipoDocumentos = []
    self.update()
     // Tipo documento unicamente proforma y factura 
    if(valor == 1){
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })

    } 
    if(valor == 0){
        self.comboTipoDocumentos.push({
            estado:"04",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.tiquete")
        })
        self.comboTipoDocumentos.push({
            estado:"01",
            descripcion:$.i18n.prop("factura.tipo.documento.factura.electronica")
        })


    } 
 //   self.comboTipoDocumentos.push({
 //       estado:"03",
 //       descripcion:$.i18n.prop("factura.tipo.documento.nota.credito")
 //   })

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
        url: 'ListarArticulosActivosAjax.do',
        datatype: "json",
        method:"POST",
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
</ventasPorServiciosNormal>