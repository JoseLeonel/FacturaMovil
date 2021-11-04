<consulta-pagar>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-calculator"></i>&nbsp {$.i18n.prop("cuentaPagar.titulo")}  </h1>
        </div>
          <div id="totalsGenerales" >
	             <div>
	             	 <div >
	                    <span>Facturacion </span>  
	                    <input type="text"  class = "totalGeneral" value ="{total}" readonly >
	                 </div>
	             </div>
	             <div>
	                <div >
	                   <span>Abonos </span>  
	                   <input type="text" class = "totalAbonoGeneral" value ="{totalAbono}" readonly >
	                </div>  
	             </div>
	             <div>
	              	<div >
	                    <span>Saldos </span>  
	                    <input type="text" class = "totalSaldoGeneral" value ="{totalSaldo}" readonly >
	                 </div>  
	             </div>
	        </div>
    </div>
    <!-- Modal correo alternativo-->
	<div class="modal fade" id="ModalCorreoAlternativo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	          
	                <h1 class="box-title"><i class="btn-correo"></i>&nbsp {$.i18n.prop("hacienda.titulo.correo.alternativo")}     </h1>
	          
	      </div>
	      <div class="modal-body">
	        <form id = "formulario" name ="formulario "   class="advanced-search-form">
	            <div class="row">   
	                <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
	                    <label class="knob-label" >{$.i18n.prop("hacienda.correo")}</label>
	                    <input type="email" class="form-control correoAlternativo" placeHolder ="{$.i18n.prop("hacienda.correo.ejemplo")}" id="correoAlternativo" name="correoAlternativo" value=""  >
	                </div>
	            </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <div class="row">
	            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
	                <button onclick ={__regresarAlListadoAlternativo}  type="button" class="btn-dark-gray btn-back  pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
	                    {$.i18n.prop("btn.volver")}
	                </button>
	            </div>
	            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
	                <button  onclick={__EnviarCorreoAlternativo}   class="btn-green btn-correo pull-right" >  {$.i18n.prop("btn.enviar.correo")}</button>
	            </div>
	         </div>		       
	      </div>
	    </div>
	  </div>
	</div>
    
 
    <!-- Inicio Filtros-->
        <div class="row" show={mostrarListado}>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
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
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
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
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label>{$.i18n.prop("proveedor.titulo")} </label>  
                                    <select  class="form-control selectProveedores" id="idProveedor" name="idProveedor" data-live-search="true">
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option  data-tokens="{nombreCompleto}" each={proveedores.data}  value="{id}"  >{nombreCompleto}</option>
                                    </select>
                                </div>  
                            </div>    
                            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <label>{$.i18n.prop("combo.estado")} </label>  
                                    <select  class="form-control selectEstado estado" id="estado" name="estado" >
                                        <option  data-tokens="{$.i18n.prop("todos.select")}"  value="0"  >{$.i18n.prop("todos.select")}</option>
                                        <option   each={estados}  value="{codigo}"  >{descripcion}</option>
                                    </select>
                                </div>  
                            </div>                   
                        </div>
                    </form>  
                </div>
            </div>
            <div class="col-xs-12 text-right">
                <a   show={hay_datos==true} onclick= {__CorreoAlternativo} class=" btn btn-success btn-correo"   title="Enviar Correo" href="#"> Enviar Correo</a>        
                <a   show={hay_datos==true} class=" btn btn-primary btn-bajar"  target="_blank" title="Descargar detalle transacciones" href="DescargarDetalleTotalCuentasXPagarAjax.do?fechaInicioParam={fechaInicio}&fechaFinParam={fechaFin}&idProveedorParam={idProveedor}&estadoParam={estado}"> Descargar</a>        
                <button onclick ={__Busqueda} type="button" class="btn btn-success btnBusquedaAvanzada" title ="Consultar" name="button" ><i class="fa fa-refresh"></i></button>
            	<button onclick ={__limpiarFiltros} show={mostrarFiltros} class="btn btn-warning btnLimpiarFiltros" title="LimpiarCampos" type="button"><i id="clear-filters" class="fa fa-eraser clear-filters"></i></button>            
            </div>
        </div>
  



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
                                    <input type="text" class="form-control fechaPago" placeHolder ="{$.i18n.prop("abonoPagar.fechaPago")}" id="fechaPago" name="fechaPago"  value="{abonoPagar.fechaPago}"  readonly={abonoPagar.id > 0}>
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
       </div>
         <div class="box">
						<div class="box-body">
							<div class="planel-body">

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
            </div>
        </div>    
    </div>
<!-- Fin del Listado -->



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
    self.parametros   = opts.idTransaccion;  
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del LiUtils.roundFactura(tado de la Tabla 
    self.estados                   =[]
    self.vendedores                = {data:[]}
    self.proveedores               = {data:[]}
    self.empresas                  = {data:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.hay_datos                 = false         
    self.mostrarListadoAbonoPagar      = false
    self.mostrarCrearAbonoPagar         = false
    self.cuentaPagar                   ={
        id:null,
        recibo:"",
        letraCambio:"",
        factura:"",
        totalComision:0,
        descuento:0,
        cantidadPagos:0,
        montoCouta:0,
        total:0,
        totalAbono:0,
        totalSaldo:0,
        nota:"",
        tipo:"",
        estado:"",
        fechaPlazo:null,
        
        fechaEntrega:null,
        proveedor:{
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
       fechaPago:null,
       total:0,
       estado:"",
	   cuentaPagar:{
           id:null
       }

    }
    self.idProveedor = 0
self.on('mount',function(){
        $("#filtros").validate(reglasDeValidacionParametros());
        $("#formularioAbonoPagar").validate(reglasDeValidacionabonoPagar());
        __InicializarTabla('.tableListar')
        __InicializarTabla  ('.tableListaAbonoPagar')
        agregarInputsCombos();
        
        listaProveedoresActivos() 
        if(self.parametros ==1){
           __MantenimientoAgregarAbonoPagar()
        }
        
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
        __ComboEstadosCuentaCobrar()
        window.addEventListener( "keydown", function(evento){
                $(".errorServerSideJgrid").remove();
            }, false );
    })
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
/**
*  Crear el combo de estados
**/
function __ComboEstadosCuentaCobrar(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Pendiente",
        descripcion:$.i18n.prop("cuentaCobrar.estado.pendiente")
     });
    self.estados.push({
        codigo: "Cerrada",
        descripcion:$.i18n.prop("cuentaCobrar.estado.cerrada")
     });
    
    self.update();
}
/**
*correoalternativo
**/
__CorreoAlternativo(){
	$('#correoAlternativo').val(null);
    $('#ModalCorreoAlternativo').modal({
	    backdrop: 'static',
        keyboard: false
    });
    $('#ModalCorreoAlternativo').modal('show');      
}
/**
* Enviar el correo
**/
__EnviarCorreoAlternativo(){
    if ($("#formulario").valid()) {
    	 __EnviarPorCorreo()
     }
}
/**
*  Regresar al listado
**/
__regresarAlListadoAlternativo(){
    $('#ModalCorreoAlternativo').modal('hide')
}
/**
*  Busqueda de la informacion y la envia por correo
**/
function __EnviarPorCorreo(){
    if ($("#filtros").valid()) {
        var parametros = {
           	correoAlternativo:$('#correoAlternativo').val(),		
           	fechaInicioParam:$('#fechaInicio').val(),
       	    fechaFinParam:$('#fechaFinal').val(),
            idProveedorParam:$('#idProveedor').val(),
            estadoParam : $('.estado').val(),
            total:$('.totalGeneral').val(),
            abono:$('.totalAbonoGeneral').val(),
            saldo:$('.totalSaldoGeneral').val()
		};
		$.ajax({
		    url: "EnvioDetalleCuentasXPagarCorreoAjax.do",
		        datatype: "json",
		        data:parametros ,
		        method:"GET",
		    success: function (data) {
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
                   swal({
	                    title: '',
	                    text: "Enviado el correo Exitosamente",
	                    type: 'success',
	                    showCancelButton: false,
	                    confirmButtonText: 'Aceptar',
	                })
                }
	        },
	        error: function (xhr, status) {
	            console.log(xhr);
	            mensajeErrorServidor(xhr, status);
	        }
        });
 	}		
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
	$("#filtros").validate(reglasDeValidacionParametros());
     if ($("#filtros").valid()) {
         listadoConsulta()
     }
}
/**
* Consulta de las cuentas por pagar
**/
function listadoConsulta(){
        var formulario = $("#filtros").serialize();
        self.fechaInicio =$('.fechaInicio').val()
        self.fechaFin =$('.fechaFinal').val()
      
        self.estado = $('.estado').val()
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
        $.ajax({
            url: "ListarCuentaPagarAjax.do",
            datatype: "json",
            data:formulario ,
            method:"GET",
            success: function (result) {
                if(result.aaData.length > 0){
                    self.hay_datos                 = true       
                    self.fechaInicio =$('.fechaInicio').val()
                    self.fechaFin =$('.fechaFinal').val()
                    self.estado = $('.estado').val()
                    self.idProveedor = $('.selectProveedores').val()
                    self.update()
                    __InformacionDataTable();
                    loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                    agregarInputsCombos();
                    ActivarEventoFiltro(".tableListar")
                    TotalesGenerales(result.aaData)
                    __mostrarAbonos()

                    
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
*  Suma de totales de cuenta por cobrar 
**/
function TotalesGenerales(data){
     self.total                     = 0
    self.totalAbono                = 0
    self.totalSaldo                = 0
    self.totalSTR                     = 0
    self.totalAbonoSTR                = 0
    self.totalSaldoSTR                = 0
    self.update()
    
    for(var i in data) { 
        self.total      += data[i].total;
        self.totalAbono += data[i].totalAbono;
        self.totalSaldo += data[i].totalSaldo;
     }
     self.total = formatoDecimales(self.total,2)
     self.totalAbono = formatoDecimales(self.totalAbono,2)
     self.totalSaldo = formatoDecimales(self.totalSaldo,2)
     self.update()
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
                            __LimpiarAbonoPagar()
                            listaAbonoPorCuentaPorPagar()
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
*  Obtiene la lista de los proveedores activos
**/
function listaProveedoresActivos(){
    self.provedores                = {data:[]}
    self.update()
    $.ajax({
        url: "ListarProveedoresAjax.do",
        global: false,
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
                            {'data' :'created_atSTR'             ,"name":"created_atSTR"                   ,"title" : $.i18n.prop("cuentaPagar.created_at")   ,"autoWidth" :true           },
                            {'data' :'id'                        ,"name":"id"                              ,"title" : $.i18n.prop("cuentaPagar.id")      ,"autoWidth" :false },
                            {'data' :'proveedor.nombreCompleto'  ,"name":"proveedor.nombreCompleto"        ,"title" : $.i18n.prop("cuentaPagar.proveedor")   ,"autoWidth" :false },
                            {'data' :'consecutivo'               ,"name":"consecutivo"                     ,"title" : $.i18n.prop("cuentaPagar.consecutivo")      ,"autoWidth" :false },
                            {'data' : 'totalSTR'                 ,"name":"totalSTR"                        ,"title" : $.i18n.prop("cuentaPagar.total")        ,"autoWidth" :false},
                            {'data' : 'totalSaldoSTR'            ,"name":"totalSaldoSTR"                   ,"title" : $.i18n.prop("cuentaPagar.totalSaldo")   ,"autoWidth" :false},
                            {'data' : 'totalAbonoSTR'            ,"name":"totalAbonoSTR"                   ,"title" : $.i18n.prop("cuentaPagar.totalAbono")   ,"autoWidth" :false},
                            {'data' : 'estado'                   ,"name":"estado"                     ,"title" : $.i18n.prop("cuentaPagar.estado")       ,"autoWidth" :false},
                            {'data' : 'id'                       ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
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
  var abonoPagar   = '<a href="#"  title="Ver/Crear abono" class="btn btn-success btnVerAbono"  role="button"><i class="fa fa-bank"></i></a>';
  return abonoPagar;
}
/**
*  Mostrar listado de abonos
**/
function __mostrarAbonos(){
    $('.tableListar').on('click','.btnVerAbono',function(e){
        var table = $('.tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        $(".tableListaAbonos").dataTable().fnClearTable()
        if(self.parametros ==1){
            includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')
        }
        self.cuentaPagar               = data    
        self.error                     = false
        self.errors                    = [];
        self.modificar                 = false
        self.agregar                   = false 
        self.mostrarFormulario         = false
        self.mostrarListado            = false 
        self.mostrarListadoAbonoPagar  = true
        self.mostrarCrearAbonoPagar    = false
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
    var parametros = {idCuentaPagar:self.cuentaPagar.id}
    $.ajax({
        url: "ListarAbonosPorCuentaPagarAjax.do",
        datatype: "json",
        method:"GET",
        data:parametros,
        success: function (result) {
            $('.tableListaAbonoPagar').DataTable().destroy();
            $('.tableListaAbonoPagar').dataTable().fnClearTable();
            if(result.aaData.length > 0){

                __InformacionTabla_lista_AbonoPagar();
                loadListar(".tableListaAbonoPagar",idioma_espanol,self.informacion_tabla_AbonoPagar,result.aaData)
                if(self.cuentaPagar.totalSaldo == 0){
                    $( ".btn-agregarAbonoPagar" ).remove();
                }else{
                    if(self.parametros ==1){
                       includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')  
                    }
                }
                agregarInputsCombosAbonoPagar();
                ActivarEventoFiltro(".tableListaAbonoPagar")
            }else{
                if(self.parametros ==1){
                   includeActionsAbonoPagar('.dataTables_wrapper','.dataTables_length')  
                }
                agregarInputsCombosAbonoPagar();
                ActivarEventoFiltro(".tableListaAbonoPagar")

            }
            if(self.parametros ==1){
              __Anular()
              __MantenimientoAgregarAbonoPagar()
            }
            __verAbonoPagar()
              
         
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
                                       {'data' : 'totalSTR'       ,"name":"totalSTR"         ,"title" : $.i18n.prop("abonoPagar.total")},
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
  var anular  = '<a href="#"  title="Anular abono" class="btn btn-danger anularAbono"  role="button"><i class="fa fa-trash"></i></a>';
  anular = row.estado =="Anulado"?"":anular
  if(self.parametros !=1){
      anular = ""
  }
  return  verAbonoPagar + " " + anular + " ";
}

/**
 * mostrar la abonoPagar
 */
function __verAbonoPagar(){
	$('#tableListaAbonoPagar').on('click','.verAbonoPagar',function(e){
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
    
	$('#tableListaAbonoPagar').on('click','.anularAbono',function(e){
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
        swal({
           title: '',
           text: $.i18n.prop("abonoPagar.mensaje.alert.anulada"),
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
             __AnularAbonoPagar()
             }
            });
        
    
       
	});
}

/**
*   anular el abono a pagar 
**/
function __AnularAbonoPagar(){
    var formulario = $("#formularioAbonoPagar").serialize();
    $.ajax({
        type : "GET",
        dataType : "json",
        data : formulario,
        url : 'anularAbonoPagarAjax.do',
        success : function(data) {
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
            listaAbonoPorCuentaPorPagar()
        }
     },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
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
</consulta-pagar>