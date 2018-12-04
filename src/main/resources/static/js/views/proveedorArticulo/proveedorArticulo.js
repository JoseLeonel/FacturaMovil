$(document).ready(function() {
    _Init();
} );/*fin document*/

var _Init = function () {
	cargarProveedores() 
    $("#proveedores").change(function() {
        if($("#proveedores").val() !=null){
            if($("#proveedores").val() !='0'){
                ListarArticulos($("#proveedores").val())
            }else{
                ListarArticulos(null)
            }
        }
        
    });
    
}
/**
 * Lista de proveedores
 */
function cargarProveedores(){
    $.ajax({
        url: "ListarProveedoresActivosAjax.do",
       datatype: "json",
       method:"GET",
       success: function (result) {
            if(result.aaData.length > 0){
                $.each(result.aaData, function( index, modeloTabla ) {
                   var linea =  '<option value="' + modeloTabla.id +'"'+"data-tokens='" + modeloTabla.nombreCompleto + "' >"+modeloTabla.nombreCompleto+'</option>';
                   $('#proveedores').append(linea);  
                })
                $('.proveedores').selectpicker();
                ListarArticulos($("#proveedores").val());
                includeActionsArticulo('.dataTables_wrapper','.dataTables_length');
                agregarInputsCombos();
                EventoFiltro();
                __MantenimientoAgregar();
                __modificarRegistro_Listar();
                __EliminarRegistro_Listar();
            }            
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
    })

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
             "url":"ListarProveedorArticuloAjax.do?idProveedor=" + filtro,
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
                               {'data' :'articulo.costo'                   ,"name":"articulo.costo"                  ,"title" : "Costo Inventario" ,"autoWidth" :true
                               ,
                               "render":function(costoInventario,type, row){
                                     return costoInventario ==null?0:costoInventario.toFixed(2);
                                },
                                },
                                {'data' :'costo' ,"name":"costo" ,"title" : "CostoXProveedor " ,"autoWidth" :true ,
                                    "render":function(costo,type, row){
                                        return costo ==null?0:costo.toFixed(2);
                                    },
                                },
                                {'data' :'articulo.precioPublico'   ,"name":"articulo.precioPublico" ,"title" : "Precio"  ,"autoWidth" :true ,
                                    "render":function(precioPublico,type, row){
                                     return precioPublico ==null?0:precioPublico.toFixed(2);
                                },
                               },
                               {'data' :'articulo.cantidad'    ,"name":"articulo.cantidad"           ,"title" : "Existencias"         ,"autoWidth" :true,
                               "render":function(cantidad,type, row){
                                     return cantidad ==null?0:cantidad.toFixed(2);
                                },
                            },
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = ' <div class="dropdown"> ' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    
    menu += '<li><a href="#"  title="Modificar" class="  btnModificar" >Modificar</a></li>'
    menu += '<li><a href="#"  title="Eliminar" class="  btnEliminar" >Eliminar</a></li>'
    menu += "</ul></div>"  
     return menu;          
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
    	var parametros = {
    			tipoEjecucion:1
    	}
    	
    	$('.mostrarListado').hide();
		riot.compile(function() {
			var parametros = {
                    tipoEjecucion:1,
                    idProveedor:$('#proveedores').val()
			};
			
			  // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('proveedor-articulo',{parametros:parametros})

			  
		});
    })
}
/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('.tableListar').on('click','.btnModificar',function(e){
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
		$('.mostrarListado').hide();
		riot.compile(function() {
			var parametros = {
                tipoEjecucion:2,
                idProveedor:$('#proveedores').val(),
                proveedorArticulo:data
			};
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('proveedor-articulo',{parametros:parametros});

			  
		});
       
	});
}
/**
 * Eliminar
 */
function __EliminarRegistro_Listar(){
	$('.tableListar').on('click','.btnEliminar',function(e){
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
        data : {idProveedorArticulo : data.id},
        url : 'EliminarProveedorArticuloAjax.do',
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
                }
                __mostrarListado()
            },
            error : function(xhr, status) {
                console.log(xhr);
                mensajeErrorServidor(xhr, status);
            }
        });
}
/**
 * Funcion para refrescar el listado
 */
function __mostrarListado(){
	var table = $('#tableListar').DataTable();
	table.ajax.reload( null, false);
	$('.mostrarListado').show();
}
/**
 * Funcion para regresar el listado
 */
function __mostrarRegresarAlListado(){
	$('.mostrarListado').show();
}
/**
*  Agregar los inpust  y select de las tablas
**/
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