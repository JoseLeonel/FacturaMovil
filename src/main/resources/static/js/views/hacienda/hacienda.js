$(document).ready(function() {
	_Init();
} );/*fin document*/

var _Init = function () {
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
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
		    listar()
		}
    });
    $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
    });
    $("#filtros").validate(reglasDeValidacion());

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
var listar = function(){
	var fechaInicio=$('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
    var cedulaReceptor = $('#cliente').val();
	var table  =  $('#tableListar').DataTable( {
			"responsive": true,
			"bAutoWidth" : true,
			"destroy":true,
			"order": [ 0, 'asc' ],
					"bInfo": true,
					"bPaginate": true,
					"bFilter" : true,
					"bDeferRender": true ,
					"sDom": 'lrtip',
					"searching":true,
			"processing": false,
			"serverSide": true,
			"sort" : "position",
			"lengthChange": true,
			"ajax" : {
					"url":"ListarHaciendasAjax.do?fechaInicio=" + fechaInicio+"&"+"fechaFin="+fechaFin+"&"+"cedulaReceptor="+cedulaReceptor,
					"deferRender": true,
					"type":"GET",
								"dataType": 'json',
								
						},
			"columns" : informacion_tabla,
			"language" : idioma_espanol,
} );//fin del table
	__CorreoAlternativo()
	__BajarDocumentoXML()
	__EnviarCorreos()
	__EnviarAceptarHacienda()
	__EnviarHacienda()
	__RespuestaHacienda()
	__BajarPDFHacienda()
	EventoFiltro();

} 

// traducciones del table
var idioma_espanol = 
{
    "sProcessing":     "Procesando...",
    "sLengthMenu" : 'Mostrar <select>' + 
     '<option value="10">10</option>'
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
	menu += '<li><a href="#"  title="Bajar PDF" class="  btnBajarPDF" >PDF Documentos</a></li>'
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



