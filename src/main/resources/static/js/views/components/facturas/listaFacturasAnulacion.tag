<lista-facturas>

<!-- Modal correo alternativo-->
<div class="modal fade" id="ModalAnularDocumento" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          
            <h2 class="box-title"><i class=" btn-anular"></i>&nbsp {$.i18n.prop("referencia.anula")} {factura.numeroConsecutivo}     </h2>
          
      </div>
      <div class="modal-body">
        <form id = "formulario" name ="formulario "   class="advanced-search-form">
            <div class="row">   
                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                    <label class="knob-label" >{$.i18n.prop("informacion.razon")}</label>
                    <input type="text" class="form-control " id"referenciaRazon" name= "referenciaRazon" >
                </div>
            </div>
            <input type="hidden" id='tipoDoc'                 name='tipoDoc'                 value="{factura.tipoDoc}" >
            <input type="hidden" id='totalCredito'            name='totalCredito'            value="{factura.totalCredito}" >
            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           value="{factura.totalImpuesto}" >
            <input type="hidden" id='totalImpuestoServ'       name='totalImpuestoServ'       value="{factura.totalImpuestoServ}" >
            <input type="hidden" id='medioPago'               name='medioPago'               value="{factura.medioPago}" >
            <input type="hidden" id='mesa'                    name='mesa'                    value="{factura.mesa}" >
            <input type="hidden" id='nombreFactura'           name='nombreFactura'           value="{factura.nombreFactura}" >
            <input type="hidden" id='montoCambio'             name='montoCambio'             value="{factura.montoCambio}" >
            <input type="hidden" id='codigoMoneda'            name='codigoMoneda'            value="{factura.codigoMoneda}" >
            <input type="hidden" id='cliente'                 name='cliente'                 value="{factura.cliente}" >
            
            <input type="hidden" id='plazoCredito'            name='plazoCredito'            value="{factura.plazoCredito}" >
            <input type="hidden" id='fechaCredito'            name='fechaCredito'            value="{factura.fechaCredito}" >
            <input type="hidden" id='condicionVenta'          name='condicionVenta'          value="{factura.condicionVenta}" >
            <input type="hidden" id='estado'                  name='estado'                  value="{factura.estado}" >
            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
            <input type="hidden" id='subTotal'                name='subTotal'                value="{factura.subTotal}" >
            <input type="hidden" id='totalTransporte'         name='totalTransporte'         value="{factura.totalTransporte}" >
            <input type="hidden" id='totalComprobante'        name='totalComprobante'        value="{factura.totalComprobante}" >
            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
            <input type="hidden" id='totalMercanciasGravadas' name='totalMercanciasGravadas' value="{factura.totalMercanciasGravadas}" >
            <input type="hidden" id='totalMercanciasExentas'  name='totalMercanciasExentas'  value="{factura.totalMercanciasExentas}" >
            <input type="hidden" id='totalServGravados'       name='totalServGravados'       value="{factura.totalServGravados}" >
            <input type="hidden" id='totalServExentos'        name='totalServExentos'        value="{factura.totalServExentos}" >
            <input type="hidden" id='totalGravado'            name='totalGravado'            value="{factura.totalGravado}" >
            <input type="hidden" id='totalExento'             name='totalExento'             value="{factura.totalExento}" >
            <input type="hidden" id='totalVenta'              name='totalVenta'              value="{factura.totalVenta}" >
            <input type="hidden" id='totalDescuentos'         name='totalDescuentos'        value="{factura.totalDescuentos}" >
            <input type="hidden" id='totalVentaNeta'          name='totalVentaNeta'          value="{factura.totalVentaNeta}" >
            <input type="hidden" id='totalImpuesto'           name='totalImpuesto'           valmodalue="{factura.totalImpuesto}" >
            <input type="hidden" id='totalEfectivo'           name='totalEfectivo'           value="{factura.totalEfectivo}" >
            <input type="hidden" id='totalTarjeta'            name='totalTarjeta'            value="{factura.totalTarjeta}" >
            <input type="hidden" id='totalBanco'              name='totalBanco'              value="{factura.totalBanco}" >
            <input type="hidden" id='totalCambioPagar'        name='totalCambioPagar'        value="{factura.totalCambioPagar}" >
            <input type="hidden" id='referenciaCodigo'        name='referenciaCodigo'        value="{factura.referenciaCodigo}" >
            <input type="hidden" id='referenciaTipoDoc'       name='referenciaTipoDoc'        value="{factura.referenciaTipoDoc}" >
            <input type="hidden" id='referenciaNumero'        name='referenciaNumero'        value="{factura.referenciaNumero}" >
            <input type="hidden" id='referenciaFechaEmision'  name='referenciaFechaEmision'   value="{factura.referenciaFechaEmision}" >
            <input type="hidden" id='detalleFactura'          name='detalleFactura'          value="{factura.detalleFactura}" >
        </form>
      </div>
      <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button onclick ={__regresarAlListadoAnulacion}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
            </div>
            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                <button  onclick={__Anular}   class=" btn-red pull-right" >  {$.i18n.prop("btn.anular")}</button>
            </div>
         </div>
       
      </div>
    </div>
  </div>
</div>

   <!-- Titulos -->
    <div  class="row " show="mostrarListado" >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("facturas.anular")} </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
        </div>
    </div>
   <br>
    <!-- Inicio Filtros-->
    <div>
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid " style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter " style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left " style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label class="knob-label" >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicial" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicial" id="fechaInicial"  name= "fechaInicial" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label class="knob-label" >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"   data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinal" id="fechaFinal"  name= "fechaFinal" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("cliente.titulo")} </label>  
                                    <select  class="form-control selectCliente" id="cliente" name="cliente" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"   value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={clientes.data}  value="{cedula}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>
                                              
                        </div>
                        <div class="row">
                             <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label>{$.i18n.prop("factura.tipo.documento")} </label>  
                                    <select  class="form-control tipoDocumento" id="tipoDocumento" name="tipoDocumento" >
                                        <option  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option each={comboTipoDocumentos} value="{estado}"  >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>
                        </div>    
                      
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
    </div>    
<!-- Fin Filtros-->

    <br>
  <!-- Listado  -->
    <div classs="contenedor-listar "  show={mostrarListado} >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box">
                    <div class="box-body">
                        <div class="planel-body" >
                        
                            <div class="row" >        
                                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar "   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class = "table-header" >{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.fecha.emision")}            </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.documento")}                </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.cliente")}                  </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                                <th class = "table-header" >{$.i18n.prop("tikect.moneda")}                    </th>
                                                <th class = "table-header" >{$.i18n.prop("factura.total")}                    </th>
                                                <th class = "table-header" >{$.i18n.prop("listado.acciones")}                 </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th>{$.i18n.prop("usuario.nombreUsuario")}            </th>
                                                <th>{$.i18n.prop("factura.fecha.emision")}            </th>
                                                <th>{$.i18n.prop("factura.documento")}                </th>
                                                <th>{$.i18n.prop("factura.cliente")}                  </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.impuesto")}   </th>
                                                <th>{$.i18n.prop("factura.linea.detalle.descuento")}  </th>
                                                <th>{$.i18n.prop("tikect.moneda")}                    </th>
                                                <th>{$.i18n.prop("factura.total")}                    </th>
                                                <th>                                                  </th>
                                            </tr>
                                        </tfoot>
                                    </table>

                                </div>   

                            </div> 
  
                        </div>    
                    </div>
                </div>
            </div>
            <div class="col-md-1 col-lg-1 "> </div>
        </div>
    </div>
    <!-- Fin del Listado -->




<style type="text/css">
.clickable {
        cursor: pointer;
    }
    .btn-success {
        color: #e7e7e7;
        background-color: #00a65a !important;
        border-color: #008d4c;
    }
    
</style>
<script>
self = this
self.totalDescuentos       = 0
self.totalImpuestos        = 0
self.total                 = 0
self.mostrarListado        = true
self.mostrarDetalle        = false
self.clientes                  = {data:[]}
self.detail                = []
    self.facturas_espera       = {data:[]}  
    self.factura                = {
        id:null,
        estado :1,
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
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        ={data:[]}
   self.on('mount',function(){
    $("#filtros").validate(reglasDeValidacion());
    $("#formulario").validate(reglasDeValidacionAnular());
    __InformacionDataTable()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    listaClientesActivos()
    __ComboTipoDocumentos()
    __Init()
    $('.datepickerFechaFinal').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
    );
    $('.datepickerFechaInicial').datepicker(
        {
           format: 'yyyy-mm-dd',
           todayHighlight:true,
        }
    );
})

/**
*  Regresar al listado
**/
__regresarAlListadoAnulacion(){
    $('#ModalAnularDocumento').modal('hide')
}

/**
* Anular Documento
**/
__Anular(){
     if ($("#formulario").valid()) {
         crearFactura()
     }

}
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('YYYY-MM-DD ');
}

/**
*  Crear Factura nueva
**/
function crearFactura(){
    self.detalleFactura.data =self.detail
    self.update() 
    var JSONDetalles = JSON.stringify( self.detalleFactura );
    self.factura.referenciaCodigo = "01"
    self.factura.tipoDoc = "03"
    self.factura.referenciaFechaEmision = null
    self.factura.referenciaTipoDoc = self.factura.tipoDoc
    self.factura.referenciaNumero = self.factura.numeroConsecutivo 
    self.factura.detalleFactura =JSONDetalles
    self.update();
    var formulario = $("#formulario").serialize();
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
                 $('#ModalAnularDocumento').modal('hide')
               	serverMessageJsonClase(data);
                 swal({
	                    title: '',
	                    text: "Anulacion Exitosamente",
	                    type: 'success',
	                    showCancelButton: false,
	                    confirmButtonText: 'Aceptar',
	                })   
               evaluarFactura(data)
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
            __Init()
                //Envia a la pantalla de impresion
            self.facturaReimprimir = modeloTabla
            self.update()
            riot.mount('ptv-imprimir',{factura:self.facturaImprimir}); 
            __eliminarFacturaListado()
            
        });
    }
}

/**
* eliminar un detalle factura
**/
function __eliminarFacturaListado() {
    $("#tableListar").dataTable().fnClearTable(); 
    __InicializarTabla('.tableListar') 
    var index = 0
    for (var count = 0; count < self.listaFacturas.data.length; count++) {
        var item = self.listaFacturas.data[count]
        if ( item.id == self.idFactura ){
            index = count
        }
    }
    self.listaFacturas.data.splice(index, 1);
    self.update()
    loadListar(".tableListar",idioma_espanol,self.formato_tabla,self.listaFacturas.data)
    agregarInputsCombos();
    ActivarEventoFiltro(".tableListar")
    __AnularFactura()
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
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.debito")
    })
    self.comboTipoDocumentos.push({
         estado:"03",
        descripcion:$.i18n.prop("referencia.tipo.documento.nota.credito")
    })
    self.update()
}

/**
* Camps requeridos
**/
var reglasDeValidacionAnular = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			referenciaRazon : {
				required : true,
                maxlength:240,
                minlength:1,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicial : {
				required : true,
			},
			fechaFinal : {
				required : true,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};
/**
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicial').val(null)
    $('#fechaFinal').val(null)
    
}
/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
   _consulta()

}

function _consulta(){
 self.listaFacturas = {data:[]}
    self.update()
    var inicial  =$('.fechaInicial').val()
     if ($("#filtros").valid()) {
        var parametros = {
            fechaInicio:inicial,
            fechaFin:$('.fechaFinal').val(),
            idCliente:$('#cliente').val(),
            tipoDocumento:$('#tipoDocumento').val(),
          
        };
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "listarFacturasActivasSinNotasCreditosCompletasAjax.do",
            datatype: "json",
            data:parametros ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.formato_tabla,result.aaData)
                    self.listaFacturas.data = result.aaData
                    self.update()
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                      __AnularFactura()

                }else{
                    __InformacionDataTable();
                     agregarInputsCombos();

                }           
            },
            error: function (xhr, status) {
                mensajeErrorServidor(xhr, status);
                console.log(xhr);
            }
        });

     }
}


/**
*  Obtiene la lista de los clientes activos
**/
function listaClientesActivos(){
    self.clientes                  = {data:[]}
    self.update()
    $.ajax({
        url: "ListarClientesActivosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.clientes.data = result.aaData
                self.update()
                $('.selectCliente').selectpicker();
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}
/*
 * Muestra los filtros avanzados
 */
 __mostrarFiltros(){
    if(self.mostrarFiltros){
        self.mostrarFiltros = false;
        self.valorMarginBottom  = '10px'
    }else{
        self.mostrarFiltros = true;
        self.valorMarginBottom  = '0px'
    }
    self.update();
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.detail                = []
    self.mostrarListado        = true
    self.mostrarDetalle        = false
    self.update()
    __listado();

}

/**
*Formato del listado 
**/
function __InformacionDataTable(){
    self.formato_tabla = [ 
                               {'data' :'usuarioCreacion.nombreUsuario'   ,"name":"usuarioCreacion.nombreUsuario"    ,"title" : $.i18n.prop("usuario.nombreUsuario")     ,"autoWidth" :true },
                               {'data' :'fechaEmisionSTR'   ,"name":"fechaEmisionSTR"                  ,"title" : $.i18n.prop("factura.fecha.emision")     ,"autoWidth" :true },
                             
                               {'data' :'numeroConsecutivo'                    ,"name":"numeroConsecutivo"                     ,"title" : $.i18n.prop("factura.documento")   ,"autoWidth" :true ,
                                   "render":function(numeroConsecutivo,type, row){
									    return __TipoDocumentos(numeroConsecutivo,row)
	 							    }
                               },
                               {'data' :'cliente'                    ,"name":"cliente"                 ,"title" : $.i18n.prop("factura.cliente")   ,"autoWidth" :true ,
                                   "render":function(cliente,type, row){
									    return cliente ==null?"":cliente.cedula != "999999999999"?cliente.nombreCompleto:row.nombreFactura;
	 							    }
                               },
                               {'data' :'totalImpuestoSTR'           ,"name":"totalImpuestoSTR"       ,"title" : $.i18n.prop("factura.linea.detalle.impuesto")     ,"autoWidth" :true  },
                               {'data' :'totalDescuentosSTR'         ,"name":"totalDescuentosSTR"     ,"title" : $.i18n.prop("factura.linea.detalle.descuento")  ,"autoWidth" :true },
                               {'data' :'codigoMoneda'               ,"name":"codigoMoneda"           ,"title" : $.i18n.prop("tikect.encabezado.moneda") ,"autoWidth" :true },
                               {'data' :'totalComprobanteSTR'        ,"name":"totalComprobanteSTR"    ,"title" : $.i18n.prop("factura.total") ,"autoWidth" :true },

                               {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
Tipo de documento
**/
function __TipoDocumentos(numeroConsecutivo,row){
    switch(row.tipoDoc) {
    case "04":
          return  "Tiq:"+numeroConsecutivo
        break;
    case "01":
        return  "Fact:"+numeroConsecutivo
        break;
    case "02":
        return  "N.Debito:"+numeroConsecutivo
        break;
    case "03":
        return  "N.Credito:"+numeroConsecutivo
        break;

    default:
        return  numeroConsecutivo
    }
}
/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    var anular  = '<a href="#"  title="Anular Venta" class="btn btn-danger  btn-anular btnAnularCaja" role="button"> </a>';
  return  anular ;        
}
/**
 * anular Factura
 */
function __AnularFactura(){
	$('.tableListar').on('click','.btnAnularCaja',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
         __Init()
        self.factura = data
        self.idFactura = self.factura.id
        self.update()
        __FacturaEnEspera()
	});
}

/**
*  Inicializar las variables de trabajos
**/
function __Init(){
    
    self.detail                = []
    self.facturas_espera       = {data:[]}  
    self.factura                = {
        id:null,
        estado :1,
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
        totalCambioPagar:0,
	    codigoMoneda:"",
	    estado:0,
	    cliente:{
            id:0,
            nombreCompleto:"",
        },
	    vendedor:{
            id:0,
            nombreCompleto:""
        }
    }                            
    self.item                  = null;
    self.articulo              = null;
    self.detalleFactura        ={data:[]}
    self.update();
 
}

/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(){
   
    $.ajax({
        url: "MostrarFacturaAjax",
        datatype: "json",
        data: {idFactura:self.factura.id},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       self.factura = modeloTabla
                       self.factura.fechaCredito = self.factura.fechaCredito !=null?__displayDate_detail(self.factura.fechaCredito):null
                       self.cliente  = modeloTabla.cliente
                       self.vendedor = modeloTabla.vendedor
                       self.update()
                    });
                }
                cargarDetallesFacturaEnEspera()
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}
/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(){
    self.detail = [];
    self.update()
    self.factura.detalles.forEach(function(e){
        self.detail.push({
            codigo          : e.codigo,
            tipoImpuesto    : e.tipoImpuesto,
            descripcion     : e.descripcion,
            cantidad        : parseFloat(e.cantidad),
            precioUnitario  : parseFloat(e.precioUnitario),
            impuesto        : parseFloat(e.impuesto),
            montoImpuesto   : parseFloat(e.montoImpuesto),
            montoDescuento  : parseFloat(e.montoDescuento),
            porcentajeDesc  : parseFloat(e.porcentajeDesc),
            subTotal        : parseFloat(e.subTotal),
            montoTotalLinea : parseFloat(e.montoTotalLinea),
            montoTotal      : parseFloat(e.montoTotal)
        });
        self.update()
    })
    self.update()
    $('#ModalAnularDocumento').modal({
         backdrop: 'static',
         keyboard: false
    })
    $('#ModalAnularDocumento').modal('show')      

     
}


function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
    })
}
</script>
</lista-facturas>