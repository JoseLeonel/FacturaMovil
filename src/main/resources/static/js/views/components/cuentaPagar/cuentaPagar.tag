<cuenta-pagar>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("cuentaPagar.titulo")}  </h1>
        </div>
        <div class=" col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
  <br>
    <br><br>
    <!-- Inicio Filtros-->
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicio" id="fechaInicio"  name= "fechaInicio" readonly>
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-th"></span>
                                        </div>
                                    </div>	                             
                                </div>  
                            </div>             
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label  >{$.i18n.prop("fecha.final")} <span class="requeridoDato">*</span></label>
                                        <div  class="form-group input-group date" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
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
                                    <label>{$.i18n.prop("proveedor.titulo")} </label>  
                                    <select  class="form-control selectProveedores" id="idProveedor" name="idProveedor" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={proveedores.data}  value="{id}"  >{nombreCompleto}</option>
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
  
<!-- Fin Filtros-->
<br>



<!-- Listado de abonoPagars  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListadoAbonoPagar}   >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                       <h1 class="box-title" ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abonoPagar.detalle")}{cuentaPagar.id}  {$.i18n.prop("cuentaPagar.total")}:₡{cuentaPagar.total.toFixed(2)}  {$.i18n.prop("cuentaPagar.totalSaldo")}:₡ {cuentaPagar.totalSaldo.toFixed(2)} </h1>
                    </div>
                    <div class="box-body">
                        <div class="planel-body" >
                            <div class="row" >
                                <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                                    <table id="tableListaAbonoPagar" class="display table responsive table-hover nowrap table-condensed tableListaAbonoPagar"   cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.id")}           </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.fechaPago")}     </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.transferencia")} </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.recibo")}        </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.total")}         </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.estado")}        </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.created_at")}    </th>
                                                <th class="table-header" >{$.i18n.prop("abonoPagar.updated_at")}    </th>
                                                <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                                            </tr>
                                        </thead>
                                        <tfoot style="display: table-header-group;">
                                            <tr>
                                                <th >{$.i18n.prop("abonoPagar.id")}            </th>
                                                <th >{$.i18n.prop("abonoPagar.fechaPago")}     </th>
                                                <th >{$.i18n.prop("abonoPagar.transferencia")} </th>
                                                <th >{$.i18n.prop("abonoPagar.recibo")}        </th>
                                                <th >{$.i18n.prop("abonoPagar.total")}         </th>
                                                <th >{$.i18n.prop("abonoPagar.estado")}        </th>
                                                <th >{$.i18n.prop("abonoPagar.created_at")}    </th>
                                                <th >{$.i18n.prop("abonoPagar.updated_at")}    </th>
                                                <th >                 </th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                     <h2 class="pull-right"> {$.i18n.prop("cuentaPagar.totalAbono")}: {cuentaPagar.totalAbono.toFixed(2)} </h2>
                                </div>    
                            </div>
                        </div>    
                        <div class="box-footer">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
<!-- Fin del Listado de abonoPagars-->

<!-- Formulario del abonoPagar-->
<div show ={mostrarCrearAbonoPagar} >
    <div class="row center ">
        <div class="col-md-12 col-lg-12 col-sx-12 col-sm-1">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title" show="{abonoPagar.id == null}"><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abonoPagar.detalle.agregar")} {cuentaPagar.id}  {$.i18n.prop("cuentaPagar.total")}:{cuentaPagar.total}  {$.i18n.prop("cuentaPagar.totalSaldo")}:{cuentaPagar.totalSaldo.toFixed(2)} </h1>
                    <h1 class="box-title" show="{abonoPagar.id != null}"><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("abonoPagar.detalle.id")} {abonoPagar.id} {$.i18n.prop("abonoPagar.detalle.cuenta")} {cuentaPagar.id} {$.i18n.prop("cuentaPagar.total")}:{cuentaPagar.total.toFixed(2)} {$.i18n.prop("cuentaPagar.totalSaldo")}:{cuentaPagar.totalSaldo.toFixed(2)}</h1>
                </div>
                <div class="box-body">
                    <form id = "formularioAbonoPagar" name ="formularioAbonoPagar " class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{abonoPagar.id}">
                        <input type="hidden" name="cuentaPagar" id="cuentaPagar" value="{cuentaPagar.id}">
                        <input type="hidden" name="idCuentaPagar" id="idCuentaPagar" value="{cuentaPagar.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label"  show={abonoPagar.id==0}>{$.i18n.prop("mensaje.campos.obligatorios")}</label>
                                
                            </div>
                            <div class="col-md-6  col-sm-6 col-lg-6"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                                <label >{$.i18n.prop("cuentaPagar.proveedor")} </label>
                                <input type="text" class="form-control" value="{cuentaPagar.proveedor.nombreCompleto}" readonly={cuentaPagar.id > 0} >                        
                            </div>
                           
                        </div>
                        <div class="row">
                             <div class="col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.fechaPago")} <span class="requeridoDato">*</span></label>
                                 <div  class="form-group input-group fechaPagoDataPicker date" data-provide="datepicker"   data-date-format="dd-mm-yyyy">
                                    <input type="text" class="form-control fechaPago" placeHolder ="{$.i18n.prop("abono.fechaPago")}" id="fechaPago" name="fechaPago"  value="{abono.fechaPago}"  readonly={abono.id > 0}>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.recibo")} </label>
                                <input type="text" class="form-control recibo" placeHolder ="{$.i18n.prop("abonoPagar.recibo")}"  id="recibo" name="recibo"  value="{abonoPagar.recibo}" readonly={abonoPagar.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.transferencia")} </label>
                                <input type="text" class="form-control transferencia" placeHolder ="{$.i18n.prop("abonoPagar.transferencia")}" id="transferencia" name="transferencia" value="{abonoPagar.transferencia}" readonly={abonoPagar.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-4 col-sm-4 col-lg-4">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("cuentaPagar.totalSaldo")} </label>
                                <input  type="number" step="any" class="form-control" placeHolder ="{$.i18n.prop("cuentaPagar.totalSaldo")}"  value="{ cuentaPagar.totalSaldo.toFixed(2)}" readonly>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.totalEfectivo")}</span> </label>
                                <input  type="number" step="any" placeHolder ="{$.i18n.prop("abonoPagar.totalEfectivo")}" id="totalEfectivo" name="totalEfectivo" onkeyup = {__TotalEfectivo}  class="form-control totalEfectivo"   value="{abonoPagar.totalEfectivo}" readonly={abonoPagar.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.totalTarjeta")}</span> </label>
                                <input  type="number" step="any" class="form-control totalTarjeta" placeHolder ="{$.i18n.prop("abonoPagar.totalTarjeta")}"  id="totalTarjeta" name="totalTarjeta" onkeyup = {__TotalTarjeta} value="{abonoPagar.totalTarjeta}" readonly={abonoPagar.id > 0}>                        
                            </div>
                        </div>    
                        <div class="row">    
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.totalBanco")}</span> </label>
                                <input  type="number" step="any" class="form-control totalBanco" placeHolder ="{$.i18n.prop("abonoPagar.totalBanco")}"  id="totalBanco" name="totalBanco" onkeyup = {__TotalBanco}  value="{abonoPagar.totalBanco}" readonly={abonoPagar.id > 0}>                        
                            </div>
                            <div class="col-md-4 col-sx-6 col-sm-4 col-lg-4">
                                <label >{$.i18n.prop("abonoPagar.total")} <span class="requeridoDato">*</span> </label>
                                <input  type="number" step="any" class="form-control total" placeHolder ="{$.i18n.prop("abonoPagar.total")}" id="total" name="total"  value="{abonoPagar.total}"   readonly>                        
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("abonoPagar.nota")} </label>
                                <input type="textArea" class="form-control nota" placeHolder ="{$.i18n.prop("abonoPagar.nota")}" id="nota" name="nota" value="{abonoPagar.nota}"  readonly={abonoPagar.id > 0}>
                            </div>
                        </div>
                        <br>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListadoAbono}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}</buuton>
                    </button> <button show={botonAgregar} onclick={__agregarAbonoPagar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
                </div>
            </div>   
        </div>
    </div>
</div>
<!-- Fin Formulario -->   
    <!-- Listado  -->
    <div classs="contenedor-listar container" id="container"  show={mostrarListado}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.created_at")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.id")}         </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.proveedor")}   </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.consecutivo")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.total")}      </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.totalAbono")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.totalSaldo")} </th>
                                <th class="table-header">{$.i18n.prop("cuentaPagar.estado")}     </th>
                                <th class="table-header">{$.i18n.prop("listado.acciones")}        </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th >{$.i18n.prop("cuentaPagar.created_at")} </th>
                                <th >{$.i18n.prop("cuentaPagar.id")}         </th>
                                <th >{$.i18n.prop("cuentaPagar.proveedor")}   </th>
                                <th >{$.i18n.prop("cuentaPagar.consecutivo")} </th>
                                <th >{$.i18n.prop("cuentaPagar.total")}      </th>
                                <th >{$.i18n.prop("cuentaPagar.totalAbono")} </th>
                                <th >{$.i18n.prop("cuentaPagar.totalSaldo")} </th>
                                <th >{$.i18n.prop("cuentaPagar.estado")}     </th>
                                <th>   </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
    </div>
<!-- Fin del Listado -->

<div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp Consulta de la cuenta por pagar     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{cuentaPagar.id}">
                        <input type="hidden" name="cliente" id="cliente" value="{cuentaPagar.cliente.id}">
                        <input type="hidden" name="vendedor" id="vendedor" value="{cuentaPagar.vendedor.id}">
                        <input type="hidden" id="letraCambio" name="letraCambio" value="{cuentaPagar.letraCambio}"  >
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12" >
                                <label  >{$.i18n.prop("cuentaPagar.proveedor")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control "  value="{cuentaPagar.cliente.nombreCompleto}" readonly>
                            </div>
                        </div>
                        <div class="row">    
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                    <label  >{$.i18n.prop("cuentaPagar.fechaEntrega")} <span class="requeridoDato">*</span></label>
                                    <input type="text" class="form-control fechaEntrega" placeHolder ="{$.i18n.prop('cuentaPagar.fechaEntrega')}"  id="fechaEntrega"  name= "fechaEntrega" value="{cuentaPagar.fechaEntrega}"  readonly>
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label  >{$.i18n.prop("cuentaPagar.fechaPlazo")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control" placeHolder ="{$.i18n.prop('cuentaPagar.fechaPlazo')}" id="fechaPlazo" name="fechaPlazo"  value="{cuentaPagar.fechaPlazo}" readonly>
                            </div>                            

                        </div>


                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                    </div>   
                
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>

<imprimir-abonoPagar></imprimir-abonoPagar>

<style type ="text/css">
        .fondoEncabezado {
            background: #00539B;
            color: #f9fafc;
        }
        .requeridoDato {
                color: red;
                text-align: left;
                font-weight: 500;
                font-size: 13px;
        }
        .fondoFacturacion {
            background: rgb(247, 244, 244);
            color: #f9fafc;
            border-style: solid;
            border-width: 5px;cliente
        }
        .wrap{
            max-width:1100px;
            width:100%;
        }
        body {
            overflow: hidden;
            background:white;
            font-size: 12px !important;
        }
        .contenedor-listar{
            width:100%;
        }
        .input-table-search{
            margin-left: 15px;
            margin-right: 15px;
            width:100%;
        }
        .botonConsulta{
            margin-top:28px;
        }
        
        table td{ 
            text-align: center;
            font-size: 12px;
            
                }
        table th {
                text-align: center;
                font-size: 12px;
        }
        th, td {
            white-space: nowrap;
        }

</style>

<script>
    var self = this
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del LiUtils.roundFactura(tado de la Tabla 
    self.estados                   =[]
    self.vendedores                = {data:[]}
    self.proveedores               = {data:[]}
    self.empresas                  = {data:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    
    self.mostrarListadoAbonoPagar      = false
    self.mostrarCrearAbonoPagar         = false
    self.cuentaPagar                   ={
        id:null,
        recibo:"",
        letraCambio:"",
        factura:"",
        facturaManual:"",
        totalComision:0,
        descuento:0,
        cantidadPagos:0,
        montoCouta:0,
        total:0,
        totalAbono:0,
        totalSaldo:0,
        descripcionArticulo:"",
        nota:"",
        tipo:"",
        estado:"",
        fechaPlazo:null,
        fechaEntrega:null,
        cliente:{
            id:null
        },
         vendedor:{
            id:null
        }
    }
    self.abonoPagar ={
	   id:null,
       nota:"",
       recibo:"",
       transferencia:"",
       fechaPago:null,
       totalEfectivo:0,
       totalTarjeta:0,
	   totalBanco:0,
       total:0,
       estado:"",
	   cuentaPagar:{
           id:null
       }
    }
    self.on('mount',function(){
        $("#filtros").validate(reglasDeValidacionParametros());
        $("#formulario").validate(reglasDeValidacion());
        $("#formularioAbonoPagar").validate(reglasDeValidacionabonoPagar());
        __InicializarTabla('.tableListar')
        __InicializarTabla  ('.tableListaAbonoPagar')
        agregarInputsCombos();
        listaProveedoresActivos() 
        __Eventos()
        __MantenimientoAgregarAbonoPagar()
        __verAbonoPagar()
        __Anular()
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

         $('.fechaPagoDataPicker').datepicker(
        {
            format: 'yyyy-mm-dd',
            todayHighlight:true,
        }
        );
        window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
    })
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			recibo : {
                maxlength:25,
                minlength:1,
			},
            letraCambio : {
                maxlength:25,
                minlength:1,
			},
            facturaManual : {
                numeroMayorCero:true,
                maxlength:11,
                minlength:1,
			},
            total: {
                numeroMayorCero:true,
                required : true,
			},
            totalComision : {
                numeroMayorCero:true,
			},
            descuento : {
                maxlength:8,
			},
            cantidadPagos : {
                numeroMayorCero:true,
                required : true,
			},
            montoCouta : {
                required : true,
                numeroMayorCero:true,
			},
            descripcionArticulo : {
                required : true,
                maxlength:255,
                minlength:1,
			},
            nota : {
                maxlength:255,
                minlength:1,
			},
            fechaEntrega : {
                required : true,
			},
            fechaPlazo : {
                required : true,
			},
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Reglas de validacion para el abonoPagar
**/
var reglasDeValidacionabonoPagar = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			recibo : {
                maxlength:25,
                minlength:1,
			},
            total: {
                numeroMayorCero:true,
                required : true,
			},
            totalEfectivo: {
                number:true,
             
			},            
            totalTarjeta: {
                number:true,
               
			},            
            totalBanco: {
                number:true,
               
			},            

            nota : {
                maxlength:255,
                minlength:1,
			},
            fechaPago : {
                required : true,
			}
		},
		ignore : []

	});
	return validationOptions;
}

/**
* Camps requeridos
**/
var reglasDeValidacionParametros = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicio : {
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
* limpiar los filtros
**/
__limpiarFiltros(){
    $('#fechaInicio').val(null)
    $('#fechaFinal').val(null)
}

/**
*  Busqueda de la informacion por rango de fechas
**/
__Busqueda(){
	$("#filtros").validate(reglasDeValidacion());
     if ($("#filtros").valid()) {
         listadoConsulta()

     }

}

function listadoConsulta(){
         var formulario = $("#filtros").serialize();
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarCuentaPagarAjax.do",
            datatype: "json",
            data:formulario ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    __mostrarListadoAbonoPagar()
                    __mostrarCuentaPorPagar()
                    
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

/**
*  Suma del monto de tarjeta
**/
__TotalTarjeta(e){
    self.abonoPagar.totalTarjeta = __valorNumerico(e.target.value) 
    self.abonoPagar.total = self.abonoPagar.totalBanco + self.abonoPagar.totalEfectivo + self.abonoPagar.totalTarjeta
    self.update()
}

/**
* Suma del monto de Efectivo
**/
__TotalEfectivo(e){
    self.abonoPagar.totalEfectivo = __valorNumerico(e.target.value) 
    self.abonoPagar.total = self.abonoPagar.totalBanco  + self.abonoPagar.totalEfectivo + self.abonoPagar.totalTarjeta
    self.update()
}

/**
* Suma del monto de cheque
**/
__TotalBanco(e){
    self.abonoPagar.totalBanco = __valorNumerico(e.target.value) 
    self.abonoPagar.total = self.abonoPagar.totalBanco  + self.abonoPagar.totalEfectivo + self.abonoPagar.totalTarjeta
    self.update()
}

/**
*  Regresar al listado
**/
__regresarAlListado(){
   
    self.mostrarListado       = true
    self.botonAgregar         = false
    self.botonModificar       = false   
    self.mostrarFormulario    = false 
    self.mostrarListadoAbonoPagar = false
    self.mostrarCrearAbonoPagar    = false
    self.update()
    listadoConsulta();

   
}
/**
* Regresar al listado de los abonoPagars 
**/
__regresarAlListadoAbono(){
    __regresar()
}
/**
* Regresar al listado de abonoPagars
**/
function __regresar(){
   
    self.mostrarListado       = false
    self.botonAgregar         = false
    self.botonModificar       = false   
    self.mostrarFormulario    = false 
    self.mostrarListadoAbonoPagar = true
    self.mostrarCrearAbonoPagar    = false
    self.update()
    listadoConsulta();

}

/**
* Calculo del monto de la cuota con base al total a pagar
**/
__MontoCuota(e){
    self.cuentaPagar.total = __valorNumerico($('.total_cuentaPagar').val()) 
    if(__valorNumerico($('.cantidadPagos').val())==0){
        return
    }
    self.cuentaPagar.montoCouta =self.cuentaPagar.total / __valorNumerico($('.cantidadPagos').val())
    self.update()
}


/**
*   Agregar 
**/
__agregarAbonoPagar(){
    __agregarRegistro(2,"#formularioAbonoPagar",$.i18n.prop("abonoPagar.mensaje.alert.agregar"),'AgregarAbonoPagarAjax.do','ListarAbonosPorCuentaPagarAjax.do','#tableListaAbonoPagar')
}
/**
*   Agregar 
**/
function __agregarRegistro(transaccion,formulario,mensajeAlerAgregar,urlAgregar,urlListar,nombretable){
    if ($(formulario).valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        swal({
           title: '',
           text: mensajeAlerAgregar,
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            if(isConfirm){
                $.ajax({
                    type : "GET",
                    dataType : "json",
                    data : formulario,
                    url : urlAgregar,
                    success : function(data) {

                        console.log(data.listaObjetos);
                        if (data.status != 200) {
                        	serverMessageJson(data);
                            if (data.message != null && data.message.length > 0) {
                            	swal({
      	                           title: '',
      	                           text: data.message,
      	                           type: 'error',
      	                           showCancelButton: false,
      	                           confirmButtonText: 'Aceptar',
      	                         })
                            }
                        } else {
                        	serverMessageJson(data);
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                         })
                             if(transaccion == 1){
                                __LimpiarCuentasPorPagar() 
                             }else{
                                 __LimpiarAbonoPagar()
                                  listaAbonoPorCuentaPorPagar()
                             }
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
        });
    }
   
}
/**
*  Activar Eventos de cuentas por pagar
**/
function __Eventos(){
   $("#formulario").validate(reglasDeValidacion());
}
/**
* Eventos de los abonoPagars
**/
function __Eventos_abonoPagars(){
    $("#formularioAbonoPagar").validate(reglasDeValidacionabonoPagar());
    $("#recibo").attr("maxlength", 25);
    $("#nota").attr("maxlength", 255);
    $('#descuento').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}
/**
*  Crear el combo de estados
**/
function __ComboEstadoscuentaPagarPendiente(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Pendiente",
        descripcion:$.i18n.prop("cuentaPagar.estado.pendiente")
     });
     self.update();

}

/**
*  Obtiene la lista de los clientes activos
**/
function listaProveedoresActivos(){
    self.provedores                = {data:[]}
    self.update()
    $.ajax({
        url: "ListarProveedoresAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                self.proveedores.data = result.aaData
                self.update()
                $('.selectProveedor').selectpicker();
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })

}

/**
*Formato del listado de los cambios
**/
function __InformacionDataTable(){
    self.informacion_tabla = [ 
                            {'data' :'created_atSTR'             ,"name":"created_atSTR"             ,"title" : $.i18n.prop("cuentaPagar.created_at")   ,"autoWidth" :true           },
                            {'data' :'id'  ,"name":"id"  ,"title" : $.i18n.prop("cuentaPagar.id")      ,"autoWidth" :false },
                            {'data' :'proveedor.nombreCompleto'  ,"name":"proveedor.nombreCompleto"  ,"title" : $.i18n.prop("cuentaPagar.proveedor")   ,"autoWidth" :false },
                            {'data' :'consecutivo'               ,"name":"consecutivo"               ,"title" : $.i18n.prop("cuentaPagar.consecutivo")      ,"autoWidth" :false },
                            {'data' : 'total'                    ,"name":"total"                     ,"title" : $.i18n.prop("cuentaPagar.total")        ,"autoWidth" :false,
                                "render":function(total,type, row){
									    return  total;
                                 }
                            },
                            {'data' : 'totalSaldo'            ,"name":"totalSaldo"                   ,"title" : $.i18n.prop("cuentaPagar.totalSaldo")   ,"autoWidth" :false,
                                "render":function(totalSaldo,type, row){
    							    return totalSaldo;
                             }
                            },
                            {'data' : 'totalAbono'            ,"name":"totalAbono"                  ,"title" : $.i18n.prop("cuentaPagar.totalAbono")   ,"autoWidth" :false,
                                "render":function(totalAbono,type, row){
    							    return  totalAbono;
                                 }
                            
                            },
                            {'data' : 'estado'                ,"name":"estado"                     ,"title" : $.i18n.prop("cuentaPagar.estado")       ,"autoWidth" :false},
                            {'data' : 'id'                    ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

/**
*Limpiar cuentas por pagar
**/
function __LimpiarCuentasPorPagar(){
    self.cuentaPagar  ={
        id:null,
        recibo:"",
        letraCambio:"",
        factura:"",
        facturaManual:"",
        totalComision:0,
        descuento:0,
        cantidadPagos:0,
        montoCouta:0,
        total:0,
        totalAbono:0,
        totalSaldo:0,
        descripcionArticulo:"",
        nota:"",
        tipo:"",
        estado:"",
        fechaPlazo:null,
        fechaEntrega:null,
        cliente:{
            id:null
        },
        vendedor:{
            id:null
        }
    }
    self.update()  
    $("#fechaEntrega").val(null)
    $("#fechaPlazo").val(null)
    $("#recibo").val(null)
    $("#letraCambio").val(null)
    $("#facturaManual").val(null)
    $("#totalComision").val(null)
    $("#descuento").val(null)
    $("#cantidadPagos").val(null)
    $("#montoCouta").val(null)
    $("#total_cuentaPagar").val(null)
    $("#descripcionArticulo").val(null)
    $("#nota").val(null)  
}
/**
* Limpiar abonoPagars
**/             
function __LimpiarAbonoPagar(){
    self.abonoPagar ={
        id:null,
        nota:"",
        recibo:"",
        transferencia:"",
        fechaPago:null,
        totalEfectivo:0,
        totalTarjeta:0,
        totalBanco:0,
        total:0,
        estado:"",
        cuentaPagar:{
                id:null
        }
    }
    self.update()
    $('#fechaPago').val(null)
    $('#recibo').val(null)
    $("#transferencia").val(null)
    $("#totalEfectivo").val(null)
    $("#totalTarjeta").val(null)
    $("#totalBanco").val(null)
    $("#total").val(null)
    $("#nota").val(null)
}                      
/**
* Opciones listado de los clientes
*/
function __Opciones(){
  var modificar  = '<a href="#"  title="Mostrar la cuenta por pagar" class="btn btn-primary  btn-buscar btnVer" role="button"> </a>';
  var abonoPagar   = '<a href="#"  title="Ver/Crear abono" class="btn btn-success btnlistdoAbonoPagar"  role="button"><i class="fa fa-bank"></i></a>';
  return  modificar +" " + abonoPagar;
}

/**
 * Funcion para Modificar del Listar
 */
function __mostrarCuentaPorPagar(){
	$('#tableListar').on('click','.btnVer',function(e){
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        __LimpiarCuentasPorPagar() 
        self.cuentaPagar = data
        self.update()
        __consultar()
	});
}
/**
*  Mostrar listado de abonoPagars
**/
function __mostrarListadoAbonoPagar(){
    $('.tableListar').on('click','.btnlistdoAbonoPagar',function(e){
        var table = $('.tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".tableListaAbonoPagar").dataTable().fnClearTable()
        includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')
        __LimpiarCuentasPorPagar() 
        self.cuentaPagar = data    
        self.error                     = false
        self.errors                    = [];
        self.modificar                 = false
        self.agregar                   = false 
        self.mostrarFormulario         = false
        self.mostrarListado            = false 
        self.mostrarListadoAbonoPagar  = true
        
        self.mostrarCrearAbonoPagar         = false
        self.update()
        listaAbonoPorCuentaPorPagar()
    })
}

/**
*  incluir el boto agregar en el modulo de abonoPagars
**/
function includeActionsAbonoPagar(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregarAbonoPagar" ).remove();
    $( ".btn-agregar" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregarAbonoPagar' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
}
/**
* Consultar una cuenta por pagar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarcuentaPagarAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        $(".errorServerSideJgrid").remove();
                        $("#formulario").validate(reglasDeValidacion());
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        self.botonModificar   = true;
                        self.botonAgregar = false;
                        self.cuentaPagar  =  modeloTabla
                        self.cuentaPagar.fechaPlazo = __displayDate_detail(self.cuentaPagar.fechaPlazo)
                        self.cuentaPagar.fechaEntrega = __displayDate_detail(self.cuentaPagar.fechaEntrega)
                        self.update()
                         listaClientesActivos() 
                        __ComboEstadoscuentaPagarModificar()
                    });
                }
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}
/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}
/**
*  Crear el combo de estados
**/
function __ComboEstadoscuentaPagarModificar(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Pendiente",
        descripcion:$.i18n.prop("cuentaPagar.estado.pendiente")
     });
    self.estados.push({
        codigo: "Cerrada",
        descripcion:$.i18n.prop("cuentaPagar.estado.cerrada")
     });
    self.estados.push({
        codigo: "Anulada",
        descripcion:$.i18n.prop("cuentaPagar.estado.anulada")
     });
    self.update();
}

/**
*Funciones de los abonoPagars de la cuenta por pagar
**/
function listaAbonoPorCuentaPorPagar(){
    if(self.cuentaPagar.totalSaldo == 0){
        $( ".btn-agregarAbonoPagar" ).remove();
    }
    $('.tableListaAbonoPagar').DataTable().destroy();
     $('.tableListaAbonoPagar').dataTable().fnClearTable();
    var parametros = {idCuentaPagar:self.cuentaPagar.id}

    $.ajax({
        url: "ListarAbonosPorCuentaPagarAjax.do",
        datatype: "json",
        method:"GET",
        data:parametros,
        success: function (result) {
            if(result.aaData.length > 0){
                __InformacionTabla_lista_AbonoPagar();
                loadListar(".tableListaAbonoPagar",idioma_espanol,self.informacion_tabla_AbonoPagar,result.aaData)
                if(self.cuentaPagar.totalSaldo == 0){
                    $( ".btn-agregarAbonoPagar" ).remove();
                }else{
                   includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')  
                }
                agregarInputsCombosAbonoPagar();
                ActivarEventoFiltro(".tableListaAbonoPagar")
            }else{
                includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')  
                agregarInputsCombosAbonoPagar();
                ActivarEventoFiltro(".tableListaAbonoPagar")

            }
            __MantenimientoAgregarAbonoPagar()
            __verAbonoPagar()
            __Anular()
            __imprimirAbonoPagar()
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}

/**
*  Informacion de la lista de los abonoPagars
**/
function __InformacionTabla_lista_AbonoPagar(){
    self.informacion_tabla_AbonoPagar  = [ 
                                       {'data':  'id'             ,"name":"id"            ,"title" : $.i18n.prop("abonoPagar.id")},
                                       {'data' : 'fechaPago'      ,"name":"fechaPago"     ,"title" : $.i18n.prop("abonoPagar.fechaPago"),
									     "render":function(fechaPago,type, row){
										        return __displayDate_detail(fechaPago);
	 							             }
                                       },
                                       {'data' : 'transferencia'  ,"name":"transferencia" ,"title" : $.i18n.prop("abonoPagar.transferencia")},
                                       {'data' : 'recibo'         ,"name":"recibo"        ,"title" : $.i18n.prop("abonoPagar.recibo")},
                                       {'data' : 'total'          ,"name":"total"         ,"title" : $.i18n.prop("abonoPagar.total"),
                                            "render":function(total,type, row){
                                                return formatoDecimales(total,2);
                                            }
                                       
                                       },
                                       {'data' : 'estado'         ,"name":"estado"        ,"title" : $.i18n.prop("abonoPagar.estado")},
                                       {'data' : 'created_atSTR'  ,"name":"created_atSTR"    ,"title" : $.i18n.prop("abonoPagar.created_at")
                                       },
                                       {'data' : 'updated_atSTR'  ,"name":"updated_atSTR"    ,"title" : $.i18n.prop("abonoPagar.updated_at") 
                                       },
                                       {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"name" : "id",
									     "render":function(id,type, row){
										        return __OpcionesAbonoPagar(id,type,row);
	 							             }
							        }];
    self.update();
}
/**
* Opciones listado de los abonoPagars
*/
function __OpcionesAbonoPagar(id,type,row){
  var verAbonoPagar  = '<a href="#"  title="Ver abonos" class="btn btn-success verAbonoPagar"  role="button"><i class="fa fa-search-plus"></i></a>';
  var anular  = '<a href="#"  title="Anular abonoPagar" class="btn btn-danger anularAbonoPagar"  role="button"><i class="fa fa-trash"></i></a>';
  var imprimir  = '<a href="#"  title="Imprimir" class="btn btn-primary  btn-imprimir btnImprimir" role="button"> </a>';
  anular = row.estado =="Anulado"?"":anular
  self.cuentaPagar = row.cuentaPagar
  self.update()
  return  verAbonoPagar + " " + anular + " "+ imprimir;
}

/**
 * mostrar la abonoPagar
 */
function __imprimirAbonoPagar(){
	$('.tableListaAbonoPagar').on('click','.btnImprimir',function(e){
		var table = $('#tableListaAbonoPagar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        riot.mount('imprimir-abonoPagar',{abonoPagar:data});
	});
}

/**
 * mostrar la abonoPagar
 */
function __verAbonoPagar(){
	$('#tableListaAbonoPagar').on('click','.verAbonoPagar',function(e){
        $("#formularioAbonoPagar").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListaAbonoPagar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.abonoPagar = data
        self.update()
        consultaAbono()
	});
}

/**
* Consultar los abonoPagars
**/
function consultaAbono(){
    var formulario = $('#formularioAbonoPagar').serialize();
    $.ajax({
        url: "MostrarAbonoPagarAjax.do",
        datatype: "json",
        data: formulario,
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        //Inicializar el Formulario
                        $(".errorServerSideJgrid").remove();
                        $("#formularioAbonoPagar").validate(reglasDeValidacionabonoPagar());
                        self.mostrarListado            = false
                        self.botonModificar            = false
                        self.botonAgregar              = false
                        self.mostrarListadoAbonoPagar      = false
                        self.mostrarCrearAbonoPagar         = true
                        __LimpiarAbonoPagar()
                        self.abonoPagar  =  modeloTabla
                        self.update()
                    });
                }
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}
/**
 * mostrar la abonoPagar
 */
function __Anular(){
	$('#tableListaAbonoPagar').on('click','.anularAbonoPagar',function(e){
        $("#formularioAbonoPagar").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListaAbonoPagar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.abonoPagar = data
        self.update()
        __agregarRegistro(2,"#formularioAbonoPagar",$.i18n.prop("abonoPagar.mensaje.alert.anulada"),'anularAbonoPagarAjax.do','ListarAbonosPorCuentaPagarAjax.do','#tableListaAbonoPagar')
        
	});
}
/**
* Mostrar formulario de mantenimiento Agregar
**/
function __MantenimientoAgregarAbonoPagar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregarAbonoPagar',function(e){
        __LimpiarAbonoPagar()
        
        self.mostrarListado       = false
        self.botonAgregar         = true
        self.botonModificar       = false   
        self.mostrarFormulario    = false 
        self.mostrarListadoAbonoPagar = false
        self.mostrarCrearAbonoPagar    = true
        self.update()
        $(".errorServerSideJgrid").remove();
        $("#formularioAbonoPagar").validate(reglasDeValidacionabonoPagar());

    })
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 9    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombosAbonoPagar(){
     // Agregar los input de busqueda 
    $('.tableListaAbonoPagar tfoot th').each( function (e) {
        var title = $('.tableListaAbonoPagar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}

</script>    
</cuenta-pagar>