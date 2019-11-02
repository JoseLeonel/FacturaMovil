$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	__Inicializar_Table('.tableListar');
	
  
	agregarInputsCombos();
    Listar()
}


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

var Listar = function(){
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
			"url":"ListarClientesAjax.do",
			"deferRender": true,
			"type":"GET",
					"dataType": 'json',
					
			  },
	"columns" : informacion_tabla,
	"language" : idioma_espanol,
} );//fin del table
  includeActions('.dataTables_wrapper','.dataTables_length')
  __modificarRegistro_Listar()
  agregarInputsCombos()
  EventoFiltro()
  __MantenimientoAgregar()
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

 var informacion_tabla = [ 
								{'data' :'cedula'             ,"name":"cedula"               ,"title" : "Cedula"              ,"autoWidth" :false ,
									"render":function(cedula,type, row){
										return stringVacio(cedula)?cedula:row.identificacionExtranjero;
									}
							     },
								{'data' :'nombreCompleto'     ,"name":"nombreCompleto"       ,"title" : "Nombre"              ,"autoWidth" :false },
								{'data' :'nombreComercial'    ,"name":"nombreComercial"      ,"title" : "Nombre Comercial"    ,"autoWidth" :false },
								{'data' : 'celular'           ,"name":"celular"              ,"title" : "Celular"             ,"autoWidth" :false},
								{'data' : 'telefono'          ,"name":"telefono"             ,"title" : "Telefono"            ,"autoWidth" :false},
								{'data' : 'estado'            ,"name":"estado"               ,"title" : "Estado"              ,"autoWidth" :false,
								"render":function(estado,type, row){
									return estadosActivoInactivo(estado,row);//factura.js
								   }},
								{'data' : 'id'                ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
									"render":function(id,type, row){
										  return __Opciones(id,type,row);
									 }
								  }];

/**
* Opciones listado de los clientes
*/
function __Opciones(){
	var modificar  = '<a href="#"  title="Modificar" class="btn btn-warning  btn-edit btnModificar" role="button"> </a>';
	return  modificar ;
  }

  /**
 * Mostrar formulario de mantenimiento Agregar
 * */
function __MantenimientoAgregar(){
	//Inicializar el Formulario
  $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
	$("#mostrarListado").hide();
	riot.compile(function() {
		var parametros = {
			tipoEjecucion:2,
			data:null
		};
		 // here tags are compiled and riot.mount works synchronously
		var tags = riot.mount('cliente-crud',{parametros:parametros});
	});   
  })
}

  /**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
		$("#mostrarListado").hide();
        riot.compile(function() {
			var parametros = {
				tipoEjecucion:1,
				data:data
			};
			 // here tags are compiled and riot.mount works synchronously
			var tags = riot.mount('cliente-crud',{parametros:parametros});
		});  
	});
}

function agregarInputsCombos(){
	// Agregar los input de busqueda 
  $('.tableListar tfoot th').each( function (e) {
	var title = $('.tableListar thead th').eq($(this).index()).text();     
	        //No se toma en cuenta la columna de las acctiones(botones)
			if ( $(this).index() != 6    ){
				$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
		  }
		   // Select
		  if ($(this).index() == 5  ){
			  var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
			  // se cargan los valores por defecto que existen en el combo
				 select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
			  select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
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


function mostrarListadoPrincipal(){
	$("#mostrarListado").show();
	var table = $('#tableListar').DataTable();
	table.ajax.reload();
}