$(document).ready(function() {
    _Init();
} );/*fin document*/

var _Init = function () {
	cargarClientes();
	cargarComboArticulos();
    $("#clientes").change(function() {
        if($("#clientes").val() !=null){
            ListarArticulos($("#clientes").val());
            includeActionsArticulo('.dataTables_wrapper','.dataTables_length');
            agregarInputsCombos();
            EventoFiltro();
            __MantenimientoAgregar();
            __EliminarRegistro_Listar();
        }
        
    });
    
    //Inicializar el Formulario
    $('.btn-green').on('click',function(e){
    	agregarArticulo();
    });
    
}

function agregarArticulo(){
	var parametros = {
			idArticulo : $('#articulo').val(),
			idCliente : $('#clientes').val()
	}
	
	$.ajax({
        url: "AgregarClienteArticulo.do",
       datatype: "json",
       data : parametros,
       method:"POST",
       success: function (data) {
    	   if (data.status != 200) {
           	serverMessageJson(data);
               if (data.message != null && data.message.length > 0) {
            	   mensajeAdvertencia(data.message)
               }
           } else {
           	serverMessageJson(data);
           	mensajeToasExito(data.message)
           	ListarArticulos($("#clientes").val());
           }
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })
}


function cargarComboArticulos(){
	$.ajax({
        url: "ListarArticulosActivosAjax.do",
       datatype: "json",
       method:"POST",
       success: function (result) {
            if(result.aaData.length > 0){
                $.each(result.aaData, function( index, modeloTabla ) {
                   var linea =  '<option value="' + modeloTabla.id +'"'+"data-tokens='" + modeloTabla.descripcion + "' >"+modeloTabla.descripcion+'</option>';
                   $('#articulo').append(linea);  
                })
                $('.articulo').selectpicker();
            }            
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })
}
/**
 * Lista de proveedores
 */
function cargarClientes(){
    $.ajax({
        url: "ListarClientesActivosAjax.do",
       datatype: "json",
       method:"GET",
       success: function (result) {
            if(result.aaData.length > 0){
                $.each(result.aaData, function( index, modeloTabla ) {
                   var linea =  '<option value="' + modeloTabla.id +'"'+"data-tokens='" + modeloTabla.nombreCompleto + "' >"+modeloTabla.nombreCompleto+'</option>';
                   $('#clientes').append(linea);  
                })
                $('.clientes').selectpicker();
                ListarArticulos($("#clientes").val());
                includeActionsArticulo('.dataTables_wrapper','.dataTables_length');
                agregarInputsCombos();
                EventoFiltro();
                __MantenimientoAgregar();
                __EliminarRegistro_Listar();
            }            
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    });

}
//datatable
//estandar
var ListarArticulos = function(filtro){
    if(filtro ==null){
        return
    }
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
             "url":"ListarClienteArticuloActivos.do?idCliente=" + filtro,
             "deferRender": true,
             "type":"GET",
                     "dataType": 'json',
                     
               },
     "columns" : informacion_tabla,
     "language" : idioma_espanol,
 } );//fin del table
  
} // fin variable listarSolicitudUsuarioQAM

// Cuando se presiona el keypress para los inputs en los filtros y select
//estandar
// Cuando se presiona el keypress para los inputs en los filtros y select
//estandar
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
     } );

     var searchTextBoxes = $(this.header()).find('input');
     searchTextBoxes.on('keyup change',function(){
        dataTableColumns.search(this.value).draw();
     });
      
     $( 'select', this.footer() ).click(function (event) {
         if ( dataTableColumns.search() !== this.value ) {
                dataTableColumns.search( this.value ).draw();
         }
      } );
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
*Formato del listado de los cambios .toFixed(2)
**/
var informacion_tabla = [ 
                               {'data' :'articulo.categoria.descripcion'   ,"name":"articulo.categoria.descripcion"  ,"title" : "Categoria" ,"autoWidth" :true},
                               {'data' :'codigo'                           ,"name":"codigo"                          ,"title" : "Codigo"           ,"autoWidth" :true },
                               {'data' :'articulo.descripcion'             ,"name":"articulo.descripcion"            ,"title" : "Descripcion"      ,"autoWidth" :true },
                                {'data' :'articulo.cantidad'    ,"name":"articulo.cantidad"           ,"title" : "Existencias"         ,"autoWidth" :true,
                                "render":function(cantidad,type, row){
                                      return cantidad ==null?0:cantidad.toFixed(2);
                                 }
                               },
                                {'data' :'costo' ,"name":"costo" ,"title" : "CostoXProveedor " ,"autoWidth" :true ,
                                    "render":function(costo,type, row){
                                        return costo ==null?0:costo.toFixed(2);
                                    },
                                },
                                {'data' :'articulo.precioPublico'   ,"name":"articulo.precioPublico" ,"title" : "Precio"  ,"autoWidth" :true ,
                                    "render":function(precioPublico,type, row){
                                     return precioPublico ==null?0:precioPublico.toFixed(2);
                                }
                               },
                              {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
                              }
	      		            ];

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
	var anular  = '<a href="#"  title="Eliminar Rubro" class="btn btn-danger  btn-anular btnAnularCaja btnNotaCredito" role="button"> </a>';
	return  anular ;    

}
/**
* incluir el boton agregar en cada mantenimiento 
**/
function includeActionsArticulo(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregar" ).remove();
    $( ".btn-agregarInventario" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregar' ><i class='fa fa-plus'></i>Agregar </div>";
    new_header += "</div>";
    parent.prepend(new_header);
}
/**
* Mostrar formulario de mantenimiento Agregar
**/
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
    	agregarItem();
    })
}

function agregarItem(){
	$('.modalAgregarArticulo').modal({backdrop: 'static', keyboard: true}) 
    $('.modalAgregarArticulo').on('shown.bs.modal', function () {
    	
     });
}

/**
 * Eliminar
 */
function __EliminarRegistro_Listar(){
	$('.tableListar').on('click','.btn-anular',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
        }
        __eliminar(data);
	});
}
/**
 * Eliminar un registro
 * @param {*} data 
 */
function __eliminar(data){
    $.ajax({
        type : "GET",
        dataType : "json",
        data : {idClienteArticulo : data.id},
        url : 'EliminarClienteArticuloAjax.do',
        success : function(data) {
            if (data.status != 200) {
                serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
             	   mensajeAdvertencia(data.message)
                }
            } else {
                serverMessageJson(data);
                mensajeToasExito(data.message);
                ListarArticulos($("#clientes").val());
                includeActionsArticulo('.dataTables_wrapper','.dataTables_length');
                agregarInputsCombos();
                EventoFiltro();
                __MantenimientoAgregar();
                __EliminarRegistro_Listar();
                }
            },
            error : function(xhr, status) {
                console.log(xhr);
                mensajeErrorServidor(xhr, status);
            }
        });
}

/**
*  Agregar los inpust  y select de las tablas
**/
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
* incluir el boton agregar en cada mantenimiento 
**/
function includeActionsArticulo(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregar" ).remove();
    $( ".btn-agregarInventario" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregar' ><i class='fa fa-plus'></i>Agregar </div>";
    new_header += "</div>";
    parent.prepend(new_header);
}