$(document).ready(function() {
	ListarArticulos(" ");
   _Init();
   
} );/*fin document*/

var _Init = function () {
    // agregarInputsCombos();
    EventoFiltro();
    $('#codigoArt').keypress(function (e) {
      if (e.keyCode == 13) {
         var valor = $('#codigoArt').val() == null?" ":$('#codigoArt').val();
            ListarArticulos(valor)
      }

    });
    $('.btncategoria').hide();

    $('.btncategoria').click(function () {
      enviarACambiarCategoria();
    })
    __listadoCategoriasActivas($("#categoria"));

   
   
}

var listaArticulosGrupales      = {data:[]}

var selectCategoria1 = $('<select id="categoria"   class="form-control categoria" data-live-search="true" ></select>');


var selectCategoria = $('<select id="categoriaInput"   class="form-control categoriaInput" ></select>');

var selecciono = false;

var ListarArticulos = function(codigo){
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
             "url":"ListarArticuloAjax.do?codigoArt="+codigo,
             "deferRender": true,
             "type":"POST",
                     "dataType": 'json',
                     
               },
     "columns" : informacion_tabla,
     "language" : idioma_espanol,
 } );//fin del table
 agregarInputsCombos();
 EventoFiltro();
 __MarcarArticulo();

}  

function enviarACambiarCategoria(){
   if(listaArticulosGrupales.data.length == 0 ){
      swal({
         title: '',
          text: 'No se realizo el cambio , no hay articulos seleccinados para la nueva categoria',
          type: 'error',
          showCancelButton: false,
          confirmButtonText: 'Aceptar',
      })
      
   }
   var json  = JSON.stringify( listaArticulosGrupales)
   $("#listaArticuloGrupales").val(json);
   var formulario = $("#filtros").serialize();
        swal({
           title: '',
           text: $.i18n.prop("categoria.cambio.aplicar"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            if(isConfirm){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'CambiarCategoriaArticulosGrupalAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
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
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
                            })
                           var table = $('#tableListar').DataTable();
                           table.ajax.reload( null, false);
                             
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
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
/**
*Formato del listado de los cambios .toFixed(2)
**/
var informacion_tabla = [ {'data' :'id'             ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : false,
                           "render":function(id,type, row){
                           return  __checkbox(row);
                           }
                           },
                               {'data' :'categoria'               ,"name":"categoria"              ,"title" : "Categoria"        ,"autoWidth" :true,
                               "render":function(categoria,type, row){
                                     return categoria ==null?"Sin Cantegoria":row.categoria.descripcion;
                                }},
                               {'data' :'codigo'                  ,"name":"codigo"                 ,"title" : "Codigo"           ,"autoWidth" :true },
                               {'data' :'descripcion'             ,"name":"descripcion"            ,"title" : "Descripcion"      ,"autoWidth" :true },
                               {'data' :'costo'                   ,"name":"costo"                  ,"title" : "Costo"            ,"autoWidth" :true
                               ,
                               "render":function(costo,type, row){
                                     return costo ==null?0:costo.toFixed(2);
                                },
                            },
                               {'data' :'impuesto'                ,"name":"impuesto"               ,"title" : "Impuesto"         ,"autoWidth" :true },
                               {'data' :'precioPublico'           ,"name":"precioPublico"          ,"title" : "Precio"           ,"autoWidth" :true ,
                               "render":function(precioPublico,type, row){
                                     return precioPublico ==null?0:precioPublico >0?precioPublico.toFixed(2):0;
                                },
                            },
                               {'data' :'cantidad'                ,"name":"cantidad"               ,"title" : "Cantidad"         ,"autoWidth" :true,
                               "render":function(cantidad,type, row){
                                     return cantidad ==null?0:cantidad.toFixed(2);
                                },
                            },
                               {'data' :'contable'                ,"name":"contable"               ,"title" : "Contable"         ,"autoWidth" :false },
                               {'data' : 'estado'                 ,"name":"estado"          ,"title" : "Estado"      ,"autoWidth" :false,
                               "render":function(estado,type, row){
                                 return estadosActivoInactivo(estado,row);//factura.js
                                }},
                           ];
/**
check de cuentas por cobrar
**/
function __checkbox(row){
   var idCheck = 'check-'+row.id ;
   var checked = " ";
   var inputcheck = '<div ><input type="checkbox" id="'+idCheck+'"  "  '+checked+'></div>'
   return  inputcheck ;
} 

/**
*  Seleccionar una cuenta por cobrar para el abono general
**/
function __MarcarArticulo() {
   $('#tableListar tbody').on('change','input[type="checkbox"]', function (e) {
       var check1 =  ($(this).attr('id'));
       var table = $('#tableListar').DataTable();
       if(table.row(this).child.isShown()){
           /*cuando el datatable esta en modo responsive*/
          var data = table.row(this).data();
       }else{  
          var data = table.row($(this).parents("tr")).data();
        }
        var chk1 =  document.getElementById(check1)
        if (chk1.checked == false){
            selecciono = false;
            __EliminarElemento(data.id);
       }
       else{
            selecciono = true;
            __incluir(data.id);
       }
       if(listaArticulosGrupales.data.length> 0){
         $('.btncategoria').show()
      }else{
         $('.btncategoria').hide();
      }   
   });
  
} 
/**
**  Selecionar el articulo a  cambiar a la categoria nueva
**/
function __incluir(elemento){
   if(listaArticulosGrupales.data.length == 0){
      __agregarElemento(elemento);
      return  
   }
   var resultado = false ;
   for (var count = 0; count < listaArticulosGrupales.data.length; count++) {
       if (listaArticulosGrupales.data[count].id == elemento ){// Si existe actualiza la cantidad
           resultado = true;
           break;
       }
   }
   if(resultado == false){
      __agregarElemento(elemento);  
   }
}
function __agregarElemento(elemento){
   listaArticulosGrupales.data.push(
      {
        id:elemento
      }
   );

}
function __EliminarElemento(elemento){
   var valor = {
      id:elemento
   }
   var index = listaArticulosGrupales.data.indexOf(valor);
   listaArticulosGrupales.data.splice(index, 1);
}

/**
 * Funcion para refrescar el listado
 */
function __mostrarListado(){
	var table = $('#tableListar').DataTable();
   table.ajax.reload( null, false);
   //ListarArticulos(" ");
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
        if ( $(this).index() != 9 && $(this).index() != 0    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
           // Select
    	if ($(this).index() == 9 ){
    	    var select = $('<select id="combo1" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
       }
       if ($(this).index() == 8 ){
         var select = $('<select id="combo2" class="form-control"><option value="">Todos</option></select>');
         // se cargan los valores por defecto que existen en el combo
           select.append( '<option value="'+$.i18n.prop("boolean.si")+'">'+$.i18n.prop("boolean.si")+'</option>' );
          select.append( '<option value="'+$.i18n.prop("boolean.no")+'">'+ $.i18n.prop("boolean.no") +'</option>' );
           $(this).html(select);
     }
       if ($(this).index() == 1 ){
         var select = $('<select id="combo3"   class="form-control"><option value="">Todos</option></select>');
         // se cargan los valores por defecto que existen en el combo
         select = __listadoCategorias(select);
         $(this).html(select);
     }
    })
}

/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategorias(select){
   $.ajax({
        url: "ListarCategoriasActivasAjax.do",
       datatype: "json",
       method:"GET",
       success: function (result) {
            if(result.aaData.length > 0){
             $.each(result.aaData, function( index, modeloTabla ) {
                select.append( '<option value="'+modeloTabla.id+'">'+modeloTabla.descripcion+"</optiondata-tokens>" );  
               
             })
          }
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
   });
   
   return select;
 }
 
/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(select){
  $.ajax({
       url: "ListarCategoriasActivasAjax.do",
      datatype: "json",
      method:"GET",
      success: function (result) {
           if(result.aaData.length > 0){
            $.each(result.aaData, function( index, modeloTabla ) {
               select.append( '<option value="'+modeloTabla.id+'">'+modeloTabla.descripcion+"</option>" );  
               
            })
            $('.categoria').selectpicker(
               {
                  style: 'btn-info',
                  size:10,
                  liveSearch: true
               }
           );
           $('.categoria').selectpicker('refresh');
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  });
  
  return select;
}


