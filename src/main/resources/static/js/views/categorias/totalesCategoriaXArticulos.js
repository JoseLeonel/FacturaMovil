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
                           {'data' :'codigo'       ,"name":"codigo"      ,"title" : "Codigo"         ,"autoWidth" :true },
                           {'data' :'descripcion'  ,"name":"descripcion" ,"title" : "Descripcion"    ,"autoWidth" :true },
                           {'data' :'cantidadSTR'  ,"name":"cantidadSTR" ,"title" : "Cantidad"       ,"autoWidth" :true},
                           {'data' :'costoSTR'     ,"name":"costoSTR"    ,"title" : "Costo"          ,"autoWidth" :true },
                           {'data' :'precioPublicoSTR'     ,"name":"precioPublicoSTR"    ,"title" : "Precio Venta"          ,"autoWidth" :true },
                           {'data' :'totalCosto'   ,"name":"totalCosto"  ,"title" : "Total Costo"    ,"autoWidth" :true },
                           {'data' :'impuesto'     ,"name":"impuesto"    ,"title" : "Total Impuesto" ,"autoWidth" :true },
                           {'data' :'totalVenta'   ,"name":"totalVenta"  ,"title" : "Total Venta"    ,"autoWidth" :true},
                           {'data' :'totalGanancia'   ,"name":"totalGanancia"  ,"title" : "Total Ganancia"    ,"autoWidth" :true},
                        ];


/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
    
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


