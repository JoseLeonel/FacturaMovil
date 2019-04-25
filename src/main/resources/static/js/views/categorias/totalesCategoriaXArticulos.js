$(document).ready(function() {

   _Init();
   __InicializarTabla('.tableListar');
   
   
} );/*fin document*/

var _Init = function () {
   EventoFiltro();
   selectCategoria = __listadoCategoriasActivas(selectCategoria);
   $(".categoria").html(selectCategoria);
   __ComboEstados();
   __ComboMinimoMaximo();
   $('#panelFiltros').click(function () {
      var advanced_search_section = $('#filtrosAvanzados');
      advanced_search_section.slideToggle(750);
   });
   $('#bontonBusqueda').click(function () {
      __ListaArticulos()
   });
  
}

var listaArticulosGrupales      = {data:[]}

var selectCategoria = $('<select id="categoria"   class="form-control categoria"></select>');

var selecciono = false;

 var dataResultado  = {aaData:[]};
/**
*  Lista de los artculos
**/
function __ListaArticulos(){
   $(".totalCosto").val(null);
   $(".totalImpuestaEsperada").val(null);
   $(".totalGananciaEsperada").val(null);
   $(".totalVentaEsperada").val(null);
   $(".tableListar").dataTable().fnClearTable(); 
   __InicializarTabla('.tableListar')  
   var formulario = $("#filtros").serialize();
   $.ajax({
       url: 'ListarArticuloXCategoriaAjax.do',
       datatype: "json",
       data : formulario,
       global: false,
       method:"POST",
       success: function (result) {
           if(result.aaData.length > 0){
               dataResultado.aaData =result.aaData;
               loadListar(".tableListar",idioma_espanol,informacion_tabla,result.aaData);
               agregarInputsCombos();
               EventoFiltro();
               __TotalesGenerales();
           }
       },
       error: function (xhr, status) {
           console.log(xhr);
           mensajeErrorServidor(xhr, status);
       }
   });
   return
}
/**
 * Funcion para obtener el data y realizar la suma de los totales
 */
function __TotalesGenerales(){
   var totalImpuesto = 0;
   var totalVenta = 0;
   var totalGanancia = 0;
   var totalCosto = 0;
   $.each(dataResultado.aaData, function( index, modeloTabla ) {

      totalImpuesto = __valorNumerico(modeloTabla.totalImpuesto) + totalImpuesto;
      totalVenta = __valorNumerico(modeloTabla.totalVenta) + totalVenta;
      totalGanancia = __valorNumerico(modeloTabla.totalGanancia) + totalGanancia;
      totalCosto =  __valorNumerico(modeloTabla.totalCosto) + totalCosto;
   });

   $(".totalCosto").val(formatoDecimales(totalCosto,2));
   $(".totalImpuestaEsperada").val(formatoDecimales(totalImpuesto,2));
   $(".totalGananciaEsperada").val(formatoDecimales(totalGanancia,2));
   $(".totalVentaEsperada").val(formatoDecimales(totalVenta,2));

}
/**
* cargar combo de estados
**/
function __ComboEstados(){
   $('.estado').append('<option value="'+0+'">'+$.i18n.prop("todos.select")+ '</option>');
   $('.estado').append('<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+ '</option>');
	$('.estado').append('<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+ '</option>');
}
/**
* cargar combo de estados
**/
function __ComboMinimoMaximo(){
   $('.minimoMaximo').append('<option value="'+0+'">'+$.i18n.prop("todos.select")+ '</option>');
   $('.minimoMaximo').append('<option value="'+1+'">'+$.i18n.prop("inventario.menor.igual.minimo")+ '</option>');
	$('.minimoMaximo').append('<option value="'+2+'">'+$.i18n.prop("inventario.mayor.igual.minimo")+ '</option>');
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
                           {'data' :'totalCostoSTR'    ,"name":"totalCostoSTR"    ,"title" : "Total Costo"       ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalImpuestoSTR' ,"name":"totalImpuestoSTR" ,"title" : "Impuesto Esperado" ,"autoWidth" :true , "bSortable": false },
                           {'data' :'totalVentaSTR'    ,"name":"totalVentaSTR"    ,"title" : "Venta Esperada"    ,"autoWidth" :true, "bSortable": false },
                           {'data' :'totalGananciaSTR' ,"name":"totalGananciaSTR" ,"title" : "Ganancia Esperada" ,"autoWidth" :true, "bSortable": false },
                        ];
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
   
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
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  });
  return select;
}