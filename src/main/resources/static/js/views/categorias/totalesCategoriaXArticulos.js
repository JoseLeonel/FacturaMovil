$(document).ready(function() {

   _Init();
   
} );/*fin document*/

var _Init = function () {
    EventoFiltro();
    $('#categoria').change(function (e) {
      ListarArticulos();
    });
    selectCategoria = __listadoCategoriasActivas(selectCategoria);
    $(".categoria").html(selectCategoria);
    __ComboEstados();
    __ComboMinimoMaximo();
   
}

var listaArticulosGrupales      = {data:[]}

var selectCategoria = $('<select id="categoria"   class="form-control categoria"></select>');

var selecciono = false;

var ListarArticulos = function(){
   var valor = $('#categoria').val() == null?" ":$('#categoria').val();
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
             "url":"ListarArticuloXCategoriaAjax.do?codigoCategoria="+valor,
             "deferRender": true,
             "type":"POST",
                     "dataType": 'json',
                     
               },
     "columns" : informacion_tabla,
     "language" : idioma_espanol,
 } );//fin del table
 agregarInputsCombos();
 EventoFiltro();

}  

/**
* cargar combo de estados
**/
function __ComboEstados(){
   $('.estado').append('<option value="'+$.i18n.prop("todos.select")+'">'+$.i18n.prop("todos.select")+ '</option>');
   $('.estado').append('<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+ '</option>');
	$('.estado').append('<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+ '</option>');
    
}

/**
* cargar combo de estados
**/
function __ComboMinimoMaximo(){
   $('.minimoMaximo').append('<option value="'+$.i18n.prop("todos.select")+'">'+$.i18n.prop("todos.select")+ '</option>');
   $('.minimoMaximo').append('<option value="'+1+'">'+$.i18n.prop("inventario.minimo")+ '</option>');
	$('.minimoMaximo').append('<option value="'+2+'">'+$.i18n.prop("inventario.maximo")+ '</option>');
    
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
                           {'data' :'codigo'           ,"name":"codigo"           ,"title" : "Codigo"            ,"autoWidth" :true , "bSortable": false },
                           {'data' :'descripcion'      ,"name":"descripcion"      ,"title" : "Descripcion"       ,"autoWidth" :true , "bSortable": false },
                           {'data' :'cantidadSTR'      ,"name":"cantidad"         ,"title" : "Cantidad"          ,"autoWidth" :true, "bSortable": false },
                           {'data' :'minimo'           ,"name":"minimo"           ,"title" : "Minimo"            ,"autoWidth" :true, "bSortable": false },
                           {'data' :'maximo'           ,"name":"maximo"           ,"title" : "Maximo"            ,"autoWidth" :true, "bSortable": false },
                           {'data' :'costoSTR'         ,"name":"costo"            ,"title" : "Costo"             ,"autoWidth" :true, "bSortable": false },
                           {'data' :'precioPublicoSTR' ,"name":"precioPublico"    ,"title" : "Precio Publico"    ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalCosto'       ,"name":"totalCosto"       ,"title" : "Total Costo"       ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalImpuesto'    ,"name":"totalImpuesto"    ,"title" : "Impuesto Esperada" ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalVenta'       ,"name":"totalVenta"       ,"title" : "Venta Esperada"    ,"autoWidth" :true, "bSortable": false },
                           {'data' :'totalGanancia'    ,"name":"totalGanancia"    ,"title" : "Ganancia Esperada" ,"autoWidth" :true, "bSortable": false },
                        ];


/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 2  && $(this).index() != 3  && $(this).index() != 4  && $(this).index() != 5 && $(this).index() != 6 && $(this).index() != 7 
            && $(this).index() != 8 && $(this).index() != 9 && $(this).index() != 10  ){
         $(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
        }
      	
    
    })
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
               select.append( '<option value="'+modeloTabla.id+'">'+modeloTabla.descripcion+'</option>' );  
               $('#categoria').append('<option value="'+modeloTabla.id+'">'+modeloTabla.descripcion+'</option>');   
               
            })
            ListarArticulos();
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  });
  
  return select;
}


