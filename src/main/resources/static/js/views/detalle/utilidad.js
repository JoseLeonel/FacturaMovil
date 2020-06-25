$(document).ready(function() {
	_Init();
} );/*fin document*/

var _Init = function () {
	var advanced_search_section = $('#filtrosAvanzados');
    advanced_search_section.slideToggle(750);
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	__ComboTipoDocumentosPara()
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
	listaClientesActivos()
   $('#panelFiltros').click(function () {
        var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
    });

    $('#bontonBusqueda').click(function () {
		if ($("#filtros").valid()) {
			__Inicializar_Table('.tableListar');  
		    _consulta();
		}
	});
	
	$('.descargarExcel').click(function () {
		if ($("#filtros").valid()) {
			
		    __bajarExcel();
		}
    });
	
	$('.btn-correo').click(function () {
		__EnviarCorreoAlternativo();
		
    });
   
	$('.enviarCorreo').click(function () {
		__correoAlternativo();
    });
	
	$('.btn-back').click(function () {
		volverListado();
    });
   
	
	
	
    $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
    });
    $("#filtros").validate(reglasDeValidacion());
    listaCategorias();
    
    $("#formularioEnviarCorreoUtilidad").validate(reglasDeValidacionCorreo());	
     $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
        $("#idArticulo").val(null);
        $("#numeroFactura").val(null);
        $("#totalVenta").val(null);
        $("#totalCosto").val(null);
        $("#utilidadBruta").val(null);
	});
     ocultarDescargaEnvioCorreo();
}

var haciendas = {data:[]};

function volverListado(){
	
	$('#ModalCorreoAlternativoFactura').modal('hide')
	
}




/**
*  inicializar el listado
**/
function __Inicializar_Table(nombreTabla){
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "scrollY":        "200px",
        "scrollCollapse": true,
        "sDom": 'lrtip',
        "order": [1, 'desc'],
        "bPaginate": true,
        "paging":         false,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });    
}

var reglasDeValidacionCorreo = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			correoAlternativoUtilidad : {
				required : true,
                email:true,
                maxlength:240,
                minlength:1,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

function ocultarDescargaEnvioCorreo(){
	
	$('.descargarExcel').hide();
	$('.enviarCorreo').hide();
}

function mostrarDescargaEnvioCorreo(){
	
	$('.descargarExcel').show();
	$('.enviarCorreo').show();
}
/**
 *Enviar el correo  con la informacion de la utilidad
 * @returns
 */
function __EnviarCorreoAlternativo(){
	var fechaInicio=$('.fechaInicial').val();
	var fechaFin = $('.fechaFinal').val();
	var cedulaReceptor = $('#cliente').val();
	var tipoDocumento = $('.tipoDocumento').val();
	var parametros = {
		fechaInicioParam:fechaInicio,
		fechaFinParam:fechaFin,
		estado :$('#estado').val(),
		tipoDoc :$('#tipoDocumento').val(),
		idCliente:cedulaReceptor,
		tipoDocumento:tipoDocumento,
		actividadEconomica:$('#actividadEconomica').val(),
		idCategoria: $('#idCategoria').val(),
		codigo: $('#idArticulo').val(),
		correoAlternativo: $('#correoAlternativoUtilidad').val(),
		totalVenta:$('#totalVenta').val(),
		totalCosto:	$('#totalCosto').val(),
		totalUtilidad:$('#utilidadBruta').val(),
		numeroFactura:$('#numeroFactura').val(),
		
	}
	if ($("#formularioEnviarCorreoUtilidad").valid()) {
		$.ajax({
		   url: "EnvioUtilidadXCCorreoAjax.do",
		   datatype: "json",
		   data:parametros ,
		   method:"GET",
		   success: function (result) {
			   if (result.status != 200) {
	                if (result.message != null && result.message.length > 0) {
	                	mensajeAdvertencia(result.message);
	                }
	            }else{
	            	mensajeToasExito(result.message);
	            }
		   },
		   error: function (xhr, status) {
			   mensajeErrorServidor(xhr, status);
			   console.log(xhr);
		   }
		});
	}
}

function _consulta(){
	ocultarDescargaEnvioCorreo();
	var table = $('.tableListar').DataTable();
 	table
    .clear()
    .draw();
	haciendas = {data:[]}
	var fechaInicio=$('.fechaInicial').val();
	var fechaFin = $('.fechaFinal').val();
	var cedulaReceptor = $('#cliente').val();
	var tipoDocumento = $('.tipoDocumento').val();
	var parametros = {
		fechaInicioParam:fechaInicio,
		fechaFinParam:fechaFin,
		estado :$('#estado').val(),
		tipoDoc :$('#tipoDocumento').val(),
		idCliente:cedulaReceptor,
		tipoDocumento:tipoDocumento,
		actividadEconomica:$('#actividadEconomica').val(),
		idCategoria: $('#idCategoria').val(),
		codigo: $('#idArticulo').val(),
		numeroFactura:$('#numeroFactura').val(),
		
	}
	__Inicializar_Table('.tableListar')  
	$.ajax({
	   url: "ListaUtilidadAjax.do",
	   datatype: "json",
	   data:parametros ,
	   method:"GET",
	   success: function (result) {
		   if(result.aaData.length > 0){
			   loadListar(".tableListar",idioma_espanol,informacion_tabla,result.aaData)
			   haciendas.data = result.aaData
			   sumarTotales(result.aaData);
			   agregarInputsCombos();
			   ActivarEventoFiltro(".tableListar")
				__BajarPDFHacienda()
				EventoFiltro();
		   }else{
			__Inicializar_Table('.tableListar')  
		   }           
	   },
	   error: function (xhr, status) {
		   mensajeErrorServidor(xhr, status);
		   console.log(xhr);
	   }
	});
}

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}

function sumarTotales(data){
	var utilidadBruta = 0;
	var totalCosto = 0;
	var totalVenta = 0;
	$.each(data, function( index, modeloTabla ) {
		utilidadBruta = utilidadBruta + modeloTabla.totalUtilidad;
		totalCosto = totalCosto + modeloTabla.totalCosto;
		totalVenta = totalVenta + modeloTabla.venta;
	});
	if(totalVenta > 0){
		mostrarDescargaEnvioCorreo();
	}
	
	$('#totalVenta').val(formatoDecimales(__valorNumerico(redondeoDecimales(totalVenta)),2));
	$('#totalCosto').val(formatoDecimales(__valorNumerico(redondeoDecimales(totalCosto)),2));
	$('#utilidadBruta').val(formatoDecimales(__valorNumerico(redondeoDecimales(utilidadBruta)),2));
}
/**
* Reglas aplicadas
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
*  inicializar el listado
**/
function __Inicializar_Table(nombreTabla){
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
    });    
}
/**
*  Obtiene la lista de los clientes activos
**/
function listaClientesActivos(){
	$.ajax({
		 url: "ListarClientesActivosAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
					$.each(result.aaData, function( index, modeloTabla ) {
						$('.selectCliente').append('<option value="'+modeloTabla.id+'">'+modeloTabla.nombreCompleto+ '</option>');
					});
					$('.selectCliente').selectpicker();
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}


function listaCategorias(){
	$.ajax({
		 url: "ListarCategoriasActivasAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
					$.each(result.aaData, function( index, modeloTabla ) {
						$('.selectCategorias').append('<option value="'+modeloTabla.id+'">'+modeloTabla.descripcion+ '</option>');
					});
					$('.selectCategorias').selectpicker();
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentosPara(){

	$('.tipoDocumento').append('<option value="'+"04"+'">'+$.i18n.prop("factura.tipo.documento.factura.tiquete")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"01"+'">'+$.i18n.prop("factura.tipo.documento.factura.electronica")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"02"+'">'+$.i18n.prop("referencia.tipo.documento.nota.debito")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"03"+'">'+$.i18n.prop("referencia.tipo.documento.nota.credito")+ '</option>');
    
}
// traducciones del table
var idioma_espanol = 
{
    "sProcessing":     "Procesando...",
    "sLengthMenu" : 'Mostrar <select>' + 
	 '<option value="5">5</option>'
	+ '<option value="10">10</option>' 
    + '<option value="25">25</option>'
    + '<option value="50">50</option>'
    + '<option value="100">100</option>'
    + '</select> registros',
    "sZeroRecords":    "No se encontraron resultados.",
    "sEmptyTable":     "Ning\u00FAn dato disponible en esta tabla.",
    "sInfo":           "Registros del _START_ al _END_ de un total de _TOTAL_ ",
    "sInfoEmpty":      "Registros del 0 al 0 de un total de 0 ",
    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
    "sInfoPostFix":    "",
    "sSearch":         "Buscar:",
    "sUrl":            "",
    "sInfoThousands":  ",",
    "sLoadingRecords": "Cargando...",
    "oPaginate": {
        "sFirst":    "Primero",
        "sLast":     "\u00DAltimo",
        "sNext":     "Siguiente",
        "sPrevious": "Anterior"
    },
    "oAria": {
        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    }
}

function agregarInputsCombos(){
	// Agregar los input de busqueda 
  $('.tableListar tfoot th').each( function (e) {
		var title = $('.tableListar thead th').eq($(this).index()).text();      
		//No se toma en cuenta la columna de las acctiones(botones)
		if ( $(this).index() != 9    ){
			 $(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
		}
		   if ($(this).index() == 1 ){
		         var select = $('<select id="combo3"   class="form-control"><option value="">Todos</option></select>');
		         // se cargan los valores por defecto que existen en el combo
		         select = __listadoCategoriasActivas(select);
		         $(this).html(select);
		     }
	})
} 

function __listadoCategoriasActivas(select){
	  $.ajax({
	       url: "ListarCategoriasActivasAjax.do",
	      datatype: "json",
	      method:"GET",
	      success: function (result) {
	           if(result.aaData.length > 0){
	            $.each(result.aaData, function( index, modeloTabla ) {
	               select.append( '<option value="'+modeloTabla.descripcion+'">'+modeloTabla.descripcion+'</option>' );       
	            })
	         }
	      },
	      error: function (xhr, status) {
	          console.log(xhr);
	           mensajeErrorServidor(xhr, status);
	      }
	  })
	  return select;
}


/**
 * Eventos del filtro
 */
function EventoFiltro(){
   // Busquedas por Inpus
   var table = $('#tableListar').DataTable();
   table.columns().every( function () {
   var dataTableColumns = this
   $( 'input', this.footer() ).keypress(function (event) {
        if ( event.which == 14 ) {
             if ( dataTableColumns.search() !== this.value ) {
                dataTableColumns.search( this.value ).draw();
             }
        }
   });
   var searchTextBoxes = $(this.header()).find('input');
     searchTextBoxes.on('keyup change',function(){
        dataTableColumns.search(this.value).draw();
   });
   $( 'select', this.footer() ).click(function (event) {
      if ( dataTableColumns.search() !== this.value ) {
         dataTableColumns.search( this.value ).draw();
      }
   });
   var searchTextBoxesSelect = $(this.header()).find('select');
     searchTextBoxes.on('keyup change',function(){
        dataTableColumns.search(this.value).draw();
   });
   searchTextBoxesSelect.on('click',function(e){
        e.stopPrapagation();
   });
   searchTextBoxes.on('click',function(e){
        e.stopPrapagation();
   });
 } );
}
/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimientoEmpresas() {
   riot.mount('resultado-hacienda');
}
/**
*Formato del listado 
**/


var informacion_tabla = [ 
							{'data' :'fechaEmision'   ,"name":"fechaEmision"    ,"title" : "Fecha Emision"   ,"autoWidth" :true},
							{'data' :'nombreCategoria',"name":"nombreCategoria" ,"title" : "Categoria"       ,"autoWidth" :true},
							{'data' :'codigo' ,"name":"codigo" ,"title" : "Codigo"       ,"autoWidth" :true},
							{'data' :'nombreArticulo'         ,"name":"nombreArticulo" ,"title" : "Descripcion"       ,"autoWidth" :true},
						    {'data' :'totalCostoSTR'          ,"name":"totalCostoSTR" ,"title" : "Costo"       ,"autoWidth" :true},
							{'data' :'ventaSTR'               ,"name":"ventaSTR" ,"title" : "Venta"       ,"autoWidth" :true},
							{'data' :'totalUtilidadSTR'       ,"name":"totalUtilidadSTR" ,"title" : "Util.Bruta"       ,"autoWidth" :true},
							{'data' : 'id'                    ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
								 "render":function(id,type, row){
								 return __Opciones(id,type,row);
							 }
					  }];

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
	let menu = '<div class="dropdown">' 
	menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
	menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
	menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
	
	
		menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPDF" >PDF Documentos</a></li>'   
	
	menu += "</ul></div>"  
	 return menu;          
}

function __bajarExcel(){
	var fechaInicioParam=$('.fechaInicial').val();
	var	fechaFinParam=$('.fechaFinal').val();
	var	estado =$('#estado').val();
	var	tipoDoc =$('#tipoDocumento').val();
	var	idCliente=$('#cliente').val();
	var	actividadEconomica=$('#actividadEconomica').val();
	var	idCategoria= $('#idCategoria').val();
	var	codigo= $('#idArticulo').val();
	var numeroFactura =$('#numeroFactura').val();
    location.href = "DescargarUtilidadAjax.do?fechaInicioParam="+fechaInicioParam +"&" +"fechaFinParam="+ fechaFinParam + "&" + "idCliente="+idCliente+"&estado="+estado+"&tipoDoc="+tipoDoc+"&actividadEconomica="+actividadEconomica+"&idCategoria="+idCategoria+"&codigo="+codigo +"&numeroFactura="+numeroFactura;
}
/**
 * Fecha de emision
 * @param {*} fecha 
 */
function formatoFechaHacienda(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}


/**
* Enviar el correo alternativo
**/
function __correoAlternativo(){
    $('.correoAlternativoFactura').val(null);
    $('#ModalCorreoAlternativoFactura').modal({
        backdrop: 'static',
        keyboard: false
    });
    $('#ModalCorreoAlternativoFactura').modal('show');      
}

/**
* Bajar PDF
*/
function __BajarPDFHacienda(){
  $('.tableListar').on('click','.btnBajarPDF',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 BajarArchivos("generaFacturaPDF",data)
  });
}

/**
*  Enviar 
**/
function __consultar(url,objeto){
	$.ajax({
		 url: url,
		 datatype: "json",
		 data: {idHacienda:objeto.id},
		 method:"GET",
		 success: function (data) {
			  if (data.status != 200) {
					if (data.message != null && data.message.length > 0) {
						 sweetAlert("", data.message, "error");
					}
			  }else{
					sweetAlert("", data.message, "info");
			  }
			  
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	});
}
/**
*  BajarDocumentos 
**/
function BajarArchivos(url,objeto){
	location.href = url + "?consecutivo=" + objeto.numeroConsecutivo
	
}