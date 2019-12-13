$(document).ready(function() {
	ListarControlPago(" ");
	_Init();
} );/*fin document*/
var _Init = function () {
	agregarInputsCombos();
	EventoFiltro();
	__MantenimientoAgregar();
	__modificarRegistro_Listar();
}
var ListarControlPago = function(){
     var table  =  $('#tableListar').DataTable( {
     "responsive": true,
      "bAutoWidth" : true,
     "destroy":true,
     "order": [ 0, 'asc' ],
             "bInfo": true,
             "bPaginate": true,
             "bFilter" : true,
             "bDeferRender": true ,
             "sDom": 'lfrtip',
             "searching":true,
     "processing": false,
     "serverSide": false,
     "sort" : "position",
     "lengthChange": true,
     "ajax" : {
             "url":"ListarControlPagoAjax.do",
             "deferRender": true,
             "type":"POST",
                     "dataType": 'json',
                     
               },
     "columns" : informacion_tabla,
     "language" : idioma_espanol,
 } );//fin del table
  includeActionsArticulo('.dataTables_wrapper','.dataTables_length');
  agregarInputsCombos();
  EventoFiltro();
  __MantenimientoAgregar();
  __modificarRegistro_Listar();
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
*Formato del listado de los cambios .toFixed(2)
**/
var informacion_tabla = [ 
                               {'data' :'empresa' ,"name":"empresa" ,"title" : "Empresa"        ,"autoWidth" :true,
                               "render":function(empresa,type, row){
                                    var nombreComercial  = row.empresa.nombreComercial = null?"":row.empresa.nombreComercial
                                     return nombreComercial.length > 0?row.empresa.nombreComercial:row.empresa.nombre;
                                }},
                               
                                {'data' :'fechaPagoSTR' ,"name":"fechaPagoSTR" ,"title" : "Fecha Pago","autoWidth" :true },
                               {'data' :'fechaLimiteSTR',"name":"fechaLimiteSTR","title" : "Fecha Limite" ,"autoWidth" :true },
                               {'data' :'totalDolarSTR' ,"name":"totalDolarSTR","title" : "Dolares","autoWidth" :true },
                               {'data' :'totalColonesSTR' ,"name":"totalColonesSTR","title" : "Colones","autoWidth" :true },
                               {'data' :'tipoCambioSTR' ,"name":"tipoCambioSTR","title" : "Tipo Cambio","autoWidth" :true },
                               {'data' :'estadoSTR' ,"name":"estadoSTR","title" : "Estado","autoWidth" :true },
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
    	$('#mostrarListado').hide();
		riot.compile(function() {
			var parametros = {
					tipoEjecucion:1
			};
     	  // here tags are compiled and riot.mount works synchronously
		  var tags = riot.mount('controlPago-crud',{parametros:parametros})
  
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
		$('#mostrarListado').hide();
		riot.compile(function() {
			var parametros = {
				tipoEjecucion:2,
				controlPago:data
			};
			 // here tags are compiled and riot.mount works synchronously
			  var tags = riot.mount('controlPago-crud',{parametros:parametros});

			  
		});
       
	});
}
/**
 * Funcion para refrescar el listado
 */
function __mostrarListado(){
	var table = $('#tableListar').DataTable();
   table.ajax.reload( null, false);
   $('#mostrarListado').show();
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
               // Select
    	if ($(this).index() == 6 ){
          var select = $('<select id="combo1" class="form-control"><option value="">Todos</option></select>');
          // se cargan los valores por defecto que existen en el combo
              select.append( '<option value="'+"Pendiente"+'">'+"Pendiente"+'</option>' );
         select.append( '<option value="'+"Pagado"+'">'+"Pagado"+'</option>' );
              $(this).html(select);
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