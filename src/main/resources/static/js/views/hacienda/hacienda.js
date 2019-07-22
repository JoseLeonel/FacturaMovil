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
		    _consulta()
		}
    });
    $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
    });
    $("#filtros").validate(reglasDeValidacion());

}

var haciendas = {data:[]};

function _consulta(){
	haciendas = {data:[]}
	var fechaInicio=$('.fechaInicial').val();
	var fechaFin = $('.fechaFinal').val();
	var cedulaReceptor = $('#cliente').val();
	var tipoDocumento = $('.tipoDocumento').val();
	var parametros = {
		fechaInicio:fechaInicio,
		fechaFin:fechaFin,
		cedulaReceptor:cedulaReceptor,
		tipoDocumento:tipoDocumento,
	}
	__Inicializar_Table('.tableListar')  
	$.ajax({
	   url: "ListarHaciendasAjax.do",
	   datatype: "json",
	   data:parametros ,
	   method:"GET",
	   success: function (result) {
		   if(result.aaData.length > 0){
			   loadListar(".tableListar",idioma_espanol,informacion_tabla,result.aaData)
			   haciendas.data = result.aaData
			   agregarInputsCombos();
			   ActivarEventoFiltro(".tableListar")
			   __CorreoAlternativo()
				__BajarDocumentoXML()
				__EnviarCorreos()
				__EnviarAceptarHacienda()
				__EnviarHacienda()
				__RespuestaHacienda()
				__BajarPDFHacienda()
				EventoFiltro();
		   }else{
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
						$('.selectCliente').append('<option value="'+modeloTabla.cedula+'">'+modeloTabla.nombreCompleto+ '</option>');
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
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentosPara(){

	$('.tipoDocumento').append('<option value="'+"04"+'">'+$.i18n.prop("factura.tipo.documento.factura.tiquete")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"01"+'">'+$.i18n.prop("factura.tipo.documento.factura.electronica")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"02"+'">'+$.i18n.prop("referencia.tipo.documento.nota.debito")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"03"+'">'+$.i18n.prop("referencia.tipo.documento.nota.credito")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"88"+'">'+$.i18n.prop("compras.compras")+ '</option>');
    
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
		if ( $(this).index() != 6    ){
			 $(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
		}
		if ($(this).index() == 1 ){
			var select = $('<select id="combo3"   class="form-control"><option value="">Todos</option></select>');
			// se cargan los valores por defecto que existen en el combo
			select.append( '<option value="'+$.i18n.prop("factura.tipo.documento.factura.tiquete")+'">'+$.i18n.prop("factura.tipo.documento.factura.tiquete")+'</option>' );       
			select.append( '<option value="'+$.i18n.prop("factura.tipo.documento.factura.electronica")+'">'+$.i18n.prop("factura.tipo.documento.factura.electronica")+'</option>' );       

			select.append( '<option value="'+$.i18n.prop("referencia.tipo.documento.nota.credito")+'">'+$.i18n.prop("referencia.tipo.documento.nota.credito")+'</option>' );       

			select.append( '<option value="'+$.i18n.prop("referencia.tipo.documento.nota.debito")+'">'+$.i18n.prop("referencia.tipo.documento.nota.debito")+'</option>' );       
			select.append( '<option value="'+$.i18n.prop("compras.compras") +'">'+$.i18n.prop("compras.compras")+'</option>' );       
			$(this).html(select);
		}
	})
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
        if ( event.which == 13 ) {
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
										{'data' :'fechaEmisor'    ,"name":"fechaEmisor"    ,"title" : "Fecha Emision"   ,"autoWidth" :true},
										{'data' :'tipoDoc'        ,"name":"tipoDoc"        ,"title" : "Tipo"           ,"autoWidth" :true },
										{'data' :'consecutivo'    ,"name":"consecutivo"    ,"title" : "Consecutivo"    ,"autoWidth" :true},
										{'data' :'nombreReceptor' ,"name":"nombreReceptor" ,"title" : "Receptor"       ,"autoWidth" :true},
										{'data' :'totalReceptor'  ,"name":"totalReceptor"  ,"title" : "Total"          ,"autoWidth" :true ,
											  "render":function(totalReceptor,type, row){
										return formatoDecimales(totalReceptor,2);
									 }
										},
										{'data' :'estado'         ,"name":"estado"         ,"title" : "Estado"      ,"autoWidth" :true},
										{'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
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
	
	if(row.estado == 2){
	  menu += '<li><a href="#"  title="Envio Manual a Tributacion" class="  btnEnvioManual" >Envio Manual</a></li>'
	}
	
	if(row.estado == "Aceptado"){
		 menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo al Cliente</a></li>'
		 menu += '<li><a href="#"  title="Bajar XML" class="  btnBajarXML" >XML Documentos</a></li>'
		 menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
		 menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'
	}
	if(row.estado == "Rechazado"){
		 menu += '<li><a href="#"  title="Bajar XML" class="  btnBajarXML" >XML Documentos</a></li>'
		 menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
	}
	if(row.estado == "Enviado"){
		 menu += '<li><a href="#"  title="Aceptacion Manual a Tributacion" class="  btnAceptacionManual" >Aceptacion Manual</a></li>'
	}
	if(row.tipoDocumento !=" "){
		menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPDF" >PDF Documentos</a></li>'
	}
	
	menu += "</ul></div>"  
	 return menu;          
}
/**
 * Fecha de emision
 * @param {*} fecha 
 */
function formatoFechaHacienda(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}
/**
*  Enviar a correo alternativo
**/
function __CorreoAlternativo(){
  $('.tableListar').on('click','.btnEnvioCorreoAlternativo',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		riot.compile(function() {
			var parametros = {
				tipoEjecucion:1,
				hacienda:data
			};
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('resultado-hacienda',{parametros:parametros});
		});  
  });
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
		 BajarArchivos("bajarPDFComprobanteAjax",data)
  });
}
/**
* Respuesta de Hacienda
*/
function __RespuestaHacienda(){
  $('.tableListar').on('click','.btnRespuestaHacienda',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 BajarArchivos("bajarXMLRespuestaAjax",data)
  });
}
/**
* Envio Manual hacia Hacienda
*/
function __EnviarHacienda(){
  $('.tableListar').on('click','.btnEnvioManual',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 __consultar("EnviarAceptarHaciendaAjax",data)
  });
}
/**
* Envio Manual para revisar la aceptacion del documento por Hacienda
*/
function __EnviarAceptarHacienda(){
  $('.tableListar').on('click','.btnAceptacionManual',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 __consultar("AceptarHaciendaAjax",data)
  });
}
/**
* Envio del correo al emisor y receptor
*/
function __EnviarCorreos(){
  $('.tableListar').on('click','.btnEnvioCorreoCliente',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 __consultar("EnviarCorreoClienteAndEmisorAjax",data)
  });
}
/**
* bajar el xml del documento
*/
function __BajarDocumentoXML(){
  $('.tableListar').on('click','.btnBajarXML',function(e){
	  var table = $('#tableListar').DataTable();
	  if(table.row(this).child.isShown()){
		  //cuando el datatable esta en modo responsive
			var data = table.row(this).data();
		}else{	
			var data = table.row($(this).parents("tr")).data();
		}
		 BajarArchivos("bajarXMLComprobanteAjax",data)
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
	location.href = url + "?idHacienda=" + objeto.id
	
}