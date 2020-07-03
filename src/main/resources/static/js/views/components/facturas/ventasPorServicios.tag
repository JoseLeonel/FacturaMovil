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
                    <label class="box-title pull-right">Tipo Cambio Dolar $ {tipoCambio.total} Banco Central</label>
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
                                <input type="number" step="any"  class="form-control "  value="{totalImpuesto}" readonly>
                            </div>
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6" >
                                <label class="knob-label ">{$.i18n.prop("factura.resumen.exoneracion")}</label> 
                                <input type="number" step="any"  class="form-control "  value="{montoExoneracion}" readonly>
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
        .titleListaPrecio{
            color:blue;
            text-decoration:underline;
        }

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
  //  var precioUnitario  = resultaMontoImpuesto > 0 ?getPrecioUnitario(precioT,resultaMontoImpuesto):precioT
    var precioUnitario  = precioT
    //resultaMontoImpuesto = parseFloat(self.articulo.impuesto1) 
    //precioUnitario      = resultaMontoImpuesto > 0 ?getPrecioUnitario(precioUnitario,resultaMontoImpuesto):precioUnitario
    var montoTotal      = getMontoTotal(precioUnitario,cantidad)
    var montoDescuento  = 0
    var naturalezaDescuento = ""
    var subTotal        = montoTotal
   // var montoImpuesto1  = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto1) ==null?0:parseFloat(self.articulo.impuesto1))
    //var montoImpuesto   = _calcularImpuesto(subTotal+montoImpuesto1,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    var montoImpuesto   = _calcularImpuesto(subTotal,parseFloat(self.articulo.impuesto) ==null?0:parseFloat(self.articulo.impuesto))
    //var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1  
    var montoTotalLinea = subTotal + montoImpuesto   
    var ganancia        = __ObtenerGananciaProductoNuevoIngresado(0,precioUnitario,self.articulo.costo ==null?0:parseFloat(self.articulo.costo),cantidad)
    self.detail.push({
       descripcion     : $('.descripcionProducto').val(),
       numeroLinea     : 1,
       pesoPrioridad   : 1,  
       tipoImpuesto    : self.articulo.tipoImpuesto ==null?" ":self.articulo.tipoImpuesto,
       tipoImpuesto1   : self.articulo.tipoImpuesto1 ==null?" ":self.articulo.tipoImpuesto1,
       iva             : parseFloat(self.articulo.impuesto),
       iva1            : parseFloat(self.articulo.impuesto1),
       codigo          : self.articulo.codigo,
       cantidad        : parseFloat(cantidad),
       precioUnitario  : parseFloat(precioUnitario),
       impuesto        : parseFloat(self.articulo.impuesto),
       impuesto1        : parseFloat(self.articulo.impuesto1),
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
 * calculo del impuesto iva
 * */
function _calcularImpuesto(precio,iva){
    if(iva == 0){
        return 0;
    }
    var impuesto = iva > 0 ?parseFloat(iva)/100:0
    impuesto = impuesto > 0 ?impuesto+1:0
    var total = precio * impuesto
    var total = total - precio 
    return total
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
    var totalVenta     = 0
    var subTotal       = 0
    var totalDescuento = 0
    var totalImpuesto  = 0
    var montoExoneracion = 0
    var montoExoneracion1 = 0
    var totalImpuesto1  = 0
    var totalMercanciasGravadas = 0
    var totalMercanciasExentas  = 0
    var totalServGravados       = 0
    var totalServExentos        = 0
    var totalGravado            = 0
    var totalExento             = 0
    var totalComprobante        = 0
    var totalventaNeta          = 0
    self.cantArticulos      = 0
    self.detail.forEach(function(e){
        totalMercanciasGravadas += e.montoImpuesto > 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasGravadas += e.montoImpuesto1 > 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto == 0 && e.tipoImpuesto != "07"?e.montoTotal:0
        totalMercanciasExentas  += e.impuesto1 == 0 && e.tipoImpuesto1 != "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto > 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServGravados       += e.montoImpuesto1 > 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto == 0 && e.tipoImpuesto == "07"?e.montoTotal:0
        totalServExentos        += e.impuesto1 == 0 && e.tipoImpuesto1 == "07"?e.montoTotal:0
        totalGravado            += e.impuesto > 0 ?e.montoTotal:0
        totalGravado            += e.impuesto1 > 0 ?e.montoTotal:0
        totalExento             += e.impuesto == 0 && e.impuesto1 == 0?e.montoTotal:0
        totalComprobante        += e.montoTotalLinea
        subTotal                += e.subTotal >0?e.subTotal:0
        totalDescuento          += e.montoDescuento >0?e.montoDescuento:0
        totalImpuesto           += __valorNumerico(e.montoImpuesto)
        totalImpuesto1          += __valorNumerico(e.montoImpuesto1)
        totalVenta              += e.montoTotal
        montoExoneracion        += parseFloat(e.montoExoneracion) 
        montoExoneracion        += parseFloat(e.montoExoneracion1) 
    });
    self.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas)
    self.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas)
    self.factura.totalServGravados       = __valorNumerico(totalServGravados)
    self.factura.totalServExentos        = __valorNumerico(totalServExentos)
    self.factura.totalGravado            = __valorNumerico(totalGravado)
    self.factura.totalExento             = __valorNumerico(totalExento)
    //cuando se aplica descuentos
    self.factura.totalVenta              = __valorNumerico(totalVenta)
    self.factura.totalDescuentos         = __valorNumerico(totalDescuento)
    self.factura.subTotal                = __valorNumerico(subTotal)
    self.factura.totalImpuesto           = __valorNumerico(totalImpuesto)
    self.factura.totalVentaNeta          = __valorNumerico(totalVenta-totalDescuento)
    self.factura.totalComprobante        = __valorNumerico(totalComprobante)
    self.totalComprobante                = formatoDecimales(self.factura.totalComprobante,2);
    self.totalDescuentos                 = formatoDecimales(self.factura.totalDescuentos,2);
    self.totalImpuesto                   = formatoDecimales(self.factura.totalImpuesto,2);
    self.montoExoneracion                = formatoDecimales(montoExoneracion,2);
    self.update(); 

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
                    self.item.porcentajeExoneracion = __valorNumerico(self.cliente.porcentajeExoneracion)
                    self.item.fechaEmisionExoneracion = self.cliente.fechaEmisionExoneracion
                    self.item.nombreInstitucionExoneracion = self.cliente.nombreInstitucionExoneracion
                    self.item.numeroDocumentoExoneracion = self.cliente.numeroDocumentoExoneracion
                    self.item.tipoDocumentoExoneracion = self.cliente.tipoDocumentoExoneracion
                    valorTotal = 0
                    self.item.montoExoneracion1 = 0
                     valorTotal = __valorNumerico(self.item.subTotal) * __valorNumerico(porcentaje)
                    self.item.montoExoneracion = valorTotal / 100
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
</venta-servicios>