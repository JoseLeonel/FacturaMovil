$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	ListarCajas()
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

var ListarCajas = function(){
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
			"url":"ListarUsuariosCajasCerradasAjax.do",
			"deferRender": true,
			"type":"GET",
					"dataType": 'json',
					
			  },
	"columns" : informacion_tabla,
	"language" : idioma_espanol,
} );//fin del table
 
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
                               {'data' : 'caja'        ,"name":"caja"  ,"title" : "Caja"  ,"autoWidth" :false,
                                    "render":function(caja,type, row){
                                        return caja == null?"":caja.descripcion;
                                    }
                                },
                               {'data' : 'created_atSTR'   ,"name":"created_at"  ,"title" : "Fecha Creacion"      ,"autoWidth" :false
                                },
                                {'data' : 'updated_atSTR'  ,"name":"updated_at"  ,"title" : "Fecha Finalizacion"  ,"autoWidth" :false
                                },
                               {'data' : 'usuario'         ,"name":"usuario"        ,"title" : "Usuario"             ,"autoWidth" :false,
                                    "render":function(usuario,type, row){
                                        return usuario.nombreUsuario;
                                    }
                               },
                               {'data' : 'totalFondoInicialSTR' ,"name":"totalFondoInicial"  ,"title" : "Fondo Inicial"  ,"autoWidth" :false},
                               {'data' : 'totalNetoSTR'         ,"name":"totalNeto"          ,"title" : "Total"          ,"autoWidth" :false},
                               {'data' : 'estado'               ,"name":"estado"             ,"title" : "Estado"         ,"autoWidth" :false},
                               {'data' : 'id'                   ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];



function agregarInputsCombos(){
	// Agregar los input de busqueda 
  $('.tableListar tfoot th').each( function (e) {
		var title = $('.tableListar thead th').eq($(this).index()).text();      
		//No se toma en cuenta la columna de las acctiones(botones)
		if ( $(this).index() != 7    ){
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




var  informacion_tabla = [ 
	{'data' : 'caja'        ,"name":"caja"  ,"title" : $.i18n.prop("usuarioCaja.caja")  ,"autoWidth" :false,
		 "render":function(caja,type, row){
			 return caja == null?"":caja.descripcion;
		 }
	 },
	{'data' : 'created_atSTR'  ,"name":"created_atSTR" ,"title" : $.i18n.prop("usuarioCaja.created_at")  ,"autoWidth" :false
	 },
	 {'data' : 'updated_atSTR' ,"name":"updated_atSTR" ,"title" : $.i18n.prop("usuarioCaja.updated_at")  ,"autoWidth" :false
	 },
	{'data' : 'usuario'       ,"name":"usuario"        ,"title" : $.i18n.prop("usuarioCaja.usuario")     ,"autoWidth" :false,
		 "render":function(usuario,type, row){
			 return usuario.nombreUsuario;
		 }
	},
	{'data' : 'totalFondoInicialSTR' ,"name":"totalFondoInicialSTR" ,"title" : $.i18n.prop("usuarioCaja.fondoIncial")   ,"autoWidth" :false},
	{'data' : 'totalNetoSTR'         ,"name":"totalNetoSTR"         ,"title" : $.i18n.prop("usuarioCaja.totalNeto")     ,"autoWidth" :false},
	{'data' : 'estado'               ,"name":"estado"               ,"title" : $.i18n.prop("usuarioCaja.estado")        ,"autoWidth" :false},
	{'data' : 'id'                   ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
	 "render":function(id,type, row){
		   return __Opciones(id,type,row);
	  }
   }];

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type, row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="   btnVerDetalle" >Ver Detalle</a></li>'
    menu += '<li><a href="#"  title="Imprimir" class="  btnImprimir" >Imprimir</a></li>'
    menu += '<li><a href="#"  title="Facturas" class="  btnFacturas" >Facturas</a></li>'
  //  menu += '<li><a href="#"  title="Facturas" class="  btnProductos">Productos</a></li>'
    menu += "</ul></div>"  
    return menu;          
}


