$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	ListarFacturas()
}




var ListarFacturas = function(){
	var table  =  $('#tableListar').DataTable( {
	"responsive": true,
	 "bAutoWidth" : true,
	"destroy":true,
	"order": [ 3, 'desc' ],
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
			"url":"ListarProformasActivasAjax.do",
			"deferRender": true,
			"type":"GET",
					"dataType": 'json',
					
			  },
	"columns" : formato_tabla,
	"language" : idioma_espanol,
} );//fin del table
agregarInputsCombos();
EventoFiltro();
__imprimirPTV();
__BajarPDF();
__CambiarEstado();
__VerDetalle();
__EnviarCorreosCliente();
__CorreoAlternativo();
__Anular();

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
var formato_tabla = [ 
                              
	{'data' :'id'                              ,"name":"id"             ,"title" : "Id"     ,"autoWidth" :true },
	{'data' :'usuarioCreacion.nombreUsuario'   ,"name":"usuarioCreacion.nombreUsuario" ,"title" : "Usuario"    ,"autoWidth" :true },
	{'data' :'fechaEmisionSTR'                 ,"name":"fechaEmisionSTR" ,"title" : "Fecha Emision"    ,"autoWidth" :true },
  
	{'data' :'id'                              ,"name":"id"              ,"title" : "Documento" ,"autoWidth" :true ,
		"render":function(id,type, row){
			 return __TipoDocumentos(id,row)
		  }
	},
	{'data' :'cliente'                    ,"name":"cliente"             ,"title" : "Cliente"   ,"autoWidth" :true ,
		"render":function(cliente,type, row){
			 return cliente ==null?"":cliente.nombreCompleto;
		  }
	},
	{'data' :'totalImpuestoSTR'       ,"name":"totalImpuestoSTR"        ,"title" : "Impuesto"  ,"autoWidth" :true },
	{'data' :'totalDescuentosSTR'     ,"name":"totalDescuentosSTR"      ,"title" : "Descuento"  ,"autoWidth" :true },
	{'data' :'totalComprobanteSTR'    ,"name":"totalComprobanteSTR"     ,"title" : "Total" ,"autoWidth" :true },
	{'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
	 "render":function(id,type, row){
		   return __Opciones(id,type,row);
	  }
   }];

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
    case "88":
        return  "Proforma:"+numeroConsecutivo
        break;
    default:
        return  numeroConsecutivo
}
}


   /**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Mostrar" class="  btnImprimir" >Imprimir</a></li>'
	menu += '<li><a href="#"  title="Cambia el Estado Proforma a Venta en espera" class="  btnPendiente" >Cambiar a venta en espera</a></li>'
	menu += '<li><a href="#"  title="Anular la proforma" class="  btnAnular" >Anular</a></li>'
    menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'
    menu += "</ul></div>"  

     return menu;          
}

/**
*  imprimir impresora punto de venta
**/
function __imprimirPTV(){
	$('.tableListar').on('click','.btnImprimir',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        riot.compile(function() {
			
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('proforma-imprimir',{factura:data});
		});    
	});
}
/**
 * mostrar la abono
 */
function __BajarPDF(){
	$('.tableListar').on('click','.btnPDF',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        location.href = "generaProformasPDF.do?idFactura=" + data.id
	});
}

/**
*  Cambiar Estado de Proforma a venta en espera
**/
function __CambiarEstado(){
	$('.tableListar').on('click','.btnPendiente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
       
        _actualizarEstado(data,1)
	});
}

/**
*  Cambiar Estado de Proforma a venta en espera
**/
function __Anular(){
	$('.tableListar').on('click','.btnAnular',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
       
        _actualizarEstado(data,5)
	});
}


function _actualizarEstado(data,estado){
    $.ajax({
        url: "CambiarEstadoProformaAPedienteAjax.do",
        datatype: "json",
        data: {idFactura:data.id,estado:estado},
        method:"POST",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
				sweetAlert("", data.message, "info");
				mostrarListadoPrincipal()
                
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
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
				tipoEjecucion:3,
				data:data
			};
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('lista-proformas',{parametros:parametros});
		});  
          
	});
}

/**
 * Envio del correo al emisor y receptor
 */
function __EnviarCorreosCliente(){
	$('.tableListar').on('click','.btnEnvioCorreoCliente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        riot.compile(function() {
			var parametros = {
				tipoEjecucion:2,
				data:data
			};
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('lista-proformas',{parametros:parametros});
		});  
        
        
	});
}




/**
 * mostrar la abono
 */
function __VerDetalle(){
	$('.tableListar').on('click','.btnMostrar',function(e){
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
			  var tags = riot.mount('lista-proformas',{parametros:parametros});
		});  
	});
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


function mostrarListadoPrincipal(){
	$("#mostrarListado").show();
	var table = $('#tableListar').DataTable();
	table.ajax.reload();
}

function mostrarListadoPrincipalSinActualizar(){
	$("#mostrarListado").show();
	var table = $('#tableListar').DataTable();
	
}

