$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	__comboEstado();
	_consulta();
	
	  $('.estado').change(function () {
		_consulta();
	  })
	
}

var selectCategoria = $('<select id="estado"   class="form-control estado"></select>');

/**
* eSTADOS DE LAS PROFORMAS
**/
function __comboEstado(){
	
    $(".estado").append( '<option value="'+3+'">'+$.i18n.prop("combo.estado.pendiente")+'</option>' );  
	$(".estado").append('<option value="'+2+'">'+$.i18n.prop("combo.estado.convertidaEnFactura")+'</option>');   
	$(".estado").append('<option value="'+11+'">'+$.i18n.prop("combo.estado.anulado")+'</option>');   
	
  }
  



var proformas = {data:[]};

function _consulta(){
	proformas = {data:[]}
	var estado = $('.estado').val() == null?"":$('.estado').val();
	var parametros = {
		estado:estado,
	}
	__Inicializar_Table('.tableListar')  
	$.ajax({
	   url: "ListarProformasActivasAjax.do",
	   datatype: "json",
	   data:parametros ,
	   method:"GET",
	   success: function (result) {
		   if(result.aaData.length > 0){
			   loadListar(".tableListar",idioma_espanol,formato_tabla,result.aaData)
			   proformas.data = result.aaData
			   agregarInputsCombos();
			   EventoFiltro();
			   __imprimirPTV();
			   __BajarPDF();
			   __CambiarEstado();
			   __VerDetalle();
			   __EnviarCorreosCliente();
			   __CorreoAlternativo();
			   __Anular();
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

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [3, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}
/**
*  inicializar el listado
**/
function __Inicializar_Table(nombreTabla){
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [3, 'desc'],
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
	'<option value="5">5</option>' 
	+'<option value="10">10</option>'
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
                              
	{'data' :'consecutivoProforma',"name":"consecutivoProforma" ,"title" : "#Proforma" ,"autoWidth" :true },
	{'data' :'consecutivo'        ,"name":"consecutivo"     ,"title" : "#Factura","autoWidth" :true },
	{'data' :'usuarioCreacion'    ,"name":"usuarioCreacion" ,"title" : "Usuario","autoWidth" :true },
	{'data' :'fechaEmisionSTR'    ,"name":"fechaEmisionSTR" ,"title" : "Fecha Emision"    ,"autoWidth" :true },
  
	{'data' :'cliente' ,'data.display' :'cliente',"name":"cliente" ,"title" : "Cliente"   ,"autoWidth" :true ,
		"render":function(cliente,type, row){
			return cliente ==null?"":cliente.length > 50?cliente.substring(0,50):cliente;
		}
	},
	{'data' :'nombreFactura'  ,"name":"nombreFactura"                          ,"title" : "A nombre"   ,"autoWidth" :true ,
		"render":function(nombreFactura,type, row){
			return nombreFactura ==null?"":nombreFactura.length > 50 ? nombreFactura.substring(0,50):nombreFactura;
		}
	},
	{'data' :'totalImpuestoSTR'       ,"name":"totalImpuestoSTR"        ,"title" : "IVA"  ,"autoWidth" :true },
	{'data' :'totalDescuentosSTR'     ,"name":"totalDescuentosSTR"      ,"title" : "Descuento"  ,"autoWidth" :true },
	{'data' :'totalComprobanteSTR'    ,"name":"totalComprobanteSTR"     ,"title" : "Total" ,"autoWidth" :true },
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
    
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
	
	if(row.estado !=11 && row.estado !=2){

		menu += '<li><a href="#"  title="Cambia el Estado Proforma a Venta en espera" class="  btnPendiente" >Cambiar a venta en espera</a></li>'
		menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Envio Correo</a></li>'
		menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
		menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Envio de correo Alternativo</a></li>'
		menu += '<li><a href="#"  title="Anular la proforma" class="  btnAnular" >Anular</a></li>'
		menu += '<li><a href="#"  title="Imprimir Proforma" class="btnImprimir" >Imprimir</a></li>'

	}
	if(row.estado ==2){

		menu += '<li><a href="#"  title="Bajar PDF" class="btnPDF" >Bajar PDF</a></li>'
		
	}
    
    
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
       
        _actualizarEstado(data,11)
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
		if ( $(this).index() != 9    ){
			 $(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	  }
  })
}  


function mostrarListadoPrincipal(){
	$("#mostrarListado").show();
	_consulta()
}

function mostrarListadoPrincipalSinActualizar(){
	$("#mostrarListado").show();
	var table = $('#tableListar').DataTable();
	
}

