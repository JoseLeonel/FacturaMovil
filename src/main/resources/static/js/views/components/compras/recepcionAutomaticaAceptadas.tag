<recepcion-apiAceptadas>
 <!-- Titulos -->
    <div  class="titulo-encabezado"  >
        <div  >
            <h1 ><i class="fa fa-calculator"></i>&nbsp Compras aceptadas por correo automatico </h1>
        </div>
          <div id="totalsGenerales" >
	             <div>
	             	 <div >
	                    <span>Impuestos </span>  
	                    <input type="text"  class = "totalGeneral" value ="{total}" readonly >
	                 </div>
	             </div>
	             <div>
	                <div >
	                   <span>Total </span>  
	                   <input type="text" class = "totalAbonoGeneral" value ="{totalAbono}" readonly >
	                </div>  
	             </div>
	         
	        </div>
    </div>


			   <!-- Inicio Filtros-->
    <div>
        <div class="row" >
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div onclick={__mostrarFiltros} class="text-left advanced-search-grid" style="margin-bottom : {valorMarginBottom}; padding : 2px;  cursor: pointer;">
                    <h4> <i class="fa fa-filter" style="padding-left : 5px;"></i>&nbsp{$.i18n.prop("filtro")} <i id="advanced-search-collapse-icon" class="fa fa-expand pull-right" style="padding-right : 5px;"></i></h4>
                </div>  
                <div  show={mostrarFiltros}  class="advanced-search-grid text-left" style="padding-top : 5px; padding-bottom : 5px;">
                    <form id="filtros" name="filtros">              
                        <div class= "row">
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label  >{$.i18n.prop("fecha.inicial")} <span class="requeridoDato">*</span></label>
                                    <div  class="form-group input-group date datepickerFechaInicio" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                        <input type="text" class="form-control fechaInicioParam" id="fechaInicioParam"  name= "fechaInicioParam" readonly>
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
                                        <div  class="form-group input-group date datepickerFechaFinal" data-provide="datepicker"    data-date-format="yyyy-mm-dd">
                                            <input type="text" class="form-control fechaFinParam" id="fechaFinParam"  name= "fechaFinParam" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>	                             
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                                <div class="form-group">
                                    <label >Proveedores </label>
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
    </div>    
<!-- Fin Filtros-->

					<div class="row" >
						<div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 ">
							<div class="box">
								<div class="box-body">
									<div class="planel-body">
										<div class="row">
											<div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
												<table id="tableListar"
													class="display table responsive table-hover nowrap table-condensed tableListar ">
													<thead>
														<tr>
   														   
															<th style="width: 4%;" class="table-header">Fecha Emision </th>
															<th style="width: 4%;" class="table-header">Consecutivo </th>
															<th style="width: 4%;" class="table-header">Proveedor </th>
															<th style="width: 4%;" class="table-header">Impuesto </th>
															<th style="width: 4%;" class="table-header">Moneda </th>
															<th style="width: 4%;" class="table-header">Total </th>
															<th style="width: 4%;" class="table-header">{$.i18n.prop("listado.acciones")} </th>
														</tr>
													</thead>
													<tfoot style="display: table-header-group;">
														<tr>
															
															<th>Fecha Emision </th>
															<th>Consecutivo </th>
															<th>Proveedor </th>
															<th>Impuesto </th>
															<th>Monedas </th>
															<th>Total </th>
															<th style="width: 2%"></th>
														</tr>
													</tfoot>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Fin del Listado -->
				
<style type="text/css"  >


</style>
	<script>
		var self = this;
	    self.mostrarCargaArchivo = false;
	    self.mostrarCargaArchivoMensaje = false;
		self.mostrarFormulario    = false;	    
	    self.mostrarDetalle       = false;
		self.verAyuda             = false;
		self.tipoCedulas	   	  = {data:[]} 
		self.mediosPago	   		  = {data:[]}
		self.condicionesVenta	  = {data:[]}
		self.tiposMensajes		  = {data:[]}
		self.tiposGasto           = {data:[]}
		self.tiposCondiciones     = {data:[]}
		self.detalleServicio 	  = {data:[]}
		self.empresaActividadComercial= {}
		self.compras              = {aaData:[]}
		self.documentoXML = null;
		self.mensaje ={				
				facturaClave:"",
				mensaje:"",
		}	
		self.proveedores               = {data:[]}	
		
		self.comprasIngresadas = {dataFactura:[]}
		//Se cargan al montar el tag
		self.on('mount',function(){
			  $("#filtros").validate(reglasDeValidacion());
			__InformacionDataTableCuentas(); 
			listadoRecepcionCompras();
			__MostrarPDF()
			listaProveedoresActivos() 
			 $('.datepickerFechaFinal').datepicker(
   	         {
    	          format: 'yyyy-mm-dd',
        	      todayHighlight:true,
            	}
    		);
    		$('.datepickerFechaInicio').datepicker(
            	{
              	format: 'yyyy-mm-dd',
              	todayHighlight:true,
            	}
    		);
    
		    
		});

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicioParam : {
				required : true,
			},
			fechaFinParam : {
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
                $('.selectProveedores').selectpicker();
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}

/**
aplicar compras
**/

/**
Listado de recepcion de compras
**/		
function listadoRecepcionCompras() {
	inicializarlista(function(resultado){
       listaRecepcionCompras()
	})
}
__Busqueda(){
     self.fechaInicioParam =$('.fechaInicioParam').val()
    self.fechaFinParam    =$('.fechaFinParam').val()
    self.idProveedor =$('#idProveedor').val()
    self.hay_datos  = false
    self.total          = 0
    self.totalImpuestos = 0
    self.update()
    if ($("#filtros").valid()) {
       
        $("#tableListar").dataTable().fnClearTable(); 
        __InicializarTabla('.tableListar')  
       listaRecepcionCompras()
    }
    
}
function listaRecepcionCompras(){
     var formulario = $("#filtros").serialize();
    $.ajax({
        url: 'listarRecepcionComprasAceptadas.do',
        datatype: "json",
         data:formulario ,
        method: "GET",
        success: function(result) {
            if (result.aaData.length > 0) {
                self.compras.aaData = result.aaData
                __cargarTablaCompras()
            }else{
				agregarInputsCombos()
				ActivarEventoFiltro(".tableListar")
			}
        }
    });
    
}
function agregarInputsCombos() {
    // Agregar los input de busqueda
    $('.tableListar tfoot th').each(
        function(e) {
            var name = '<input id = "filtroCampos' + e + '"';
            var title = $('.tableListar thead th').eq($(this).index())
                .text();
            // No se toma en cuenta la columna de las acctiones(botones)
            if ($(this).index() != 8 && $(this).index() != 0) {
                $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
            }
        })
}
/**
Carga Tablas de compras
**/
function __cargarTablaCompras() {
   inicializarlista(function(resultado){
    $("#tableListar").dataTable().fnAddData(self.compras.aaData);
	
	agregarInputsCombos()
	ActivarEventoFiltro(".tableListar")

   })
	
}

function inicializarlista(callback){
 $("#tableListar").dataTable().fnClearTable();
    __InformacionDataTableCuentas();
    $('#tableListar').DataTable().destroy();
    $("#tableListar").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": self.formato_tabla ,
    })
    callback("listo")
}
/**
 * Formato del listado
 */
function __InformacionDataTableCuentas(){
	self.formato_tabla = [
		{ 'data': 'consecutivo', "name": "consecutivo", "title": "#Consecutivo", "autoWidth": true },
		{ 'data': 'fechaEmision', "name": "fechaEmision", "title": "Fecha Emision", "autoWidth": true },
		{ 'data': 'emisorFactura', "name": "emisorFactura", "title": "#Proveedor", "autoWidth": true },
		{ 'data': 'totalImpuestos', "name": "totalImpuestos", "title": "IVA", "autoWidth": true },
		{ 'data': 'moneda', "name": "moneda", "title": "Moneda", "autoWidth": true },
		{ 'data': 'totalComprobante', "name": "totalComprobante", "title": "Total", "autoWidth": true },
		{
			'data': 'id',
			"name": "id",
			"bSortable": false,
			"bSearchable": false,
			"autoWidth": true,
			"render": function(id, type, row) {
				return __Opciones(id, type, row);
			}
		}
	];
	self.update()
}

/**
 * Opciones listado de los clientes
 */
function __Opciones(id, type, row) {
    let menu = '<div class="dropdown">'
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>'
    menu += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += "</ul></div>"
    return menu;
}

function __MostrarPDF() {
    $('.tableListar').on('click', '.btnPDF', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
      	var parametros = {
            direccion: "bajarArchivo.do?filename=" + data.facturaPdf,
            stylemodal: "modal-xl"
        }
        riot.mount('view-pdf', { datos: parametros });

    });
}

/**
Agregar detalles
**/




</script>
</recepcion-apiAceptadas>