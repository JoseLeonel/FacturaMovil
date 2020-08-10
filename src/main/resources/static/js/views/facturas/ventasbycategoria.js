$(document).ready(function() {
    var advanced_search_section = $('#filtrosAvanzados');
    advanced_search_section.slideToggle(750);
    _Init();

}); /*fin document*/

var _Init = function() {
    __Inicializar_Table('.tableListar');
    $('.totalExonerado').val(0);
    $('.totalCosto').val(0);
    $('.totalDescuento').val(0);
    $('.totalIVA').val(0);
    $('.totalVenta').val(0);
    agregarInputsCombos();
    $('.datepickerFechaFinal').datepicker({
        format: 'yyyy-mm-dd',
        todayHighlight: true,
    });
    $('.datepickerFechaInicial').datepicker({
        format: 'yyyy-mm-dd',
        todayHighlight: true,
    });
    ListarCategoriasActivos()
    $('#panelFiltros').click(function() {
        var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
    });

    $('#bontonBusqueda').click(function() {
        if ($("#filtros").valid()) {
            ListarFacturas()
        }
    });
    $('.btnLimpiarFiltros').click(function() {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
    });



    $("#filtros").validate(reglasDeValidacion());
    var advanced_search_section = $('#filtrosAvanzados');
    advanced_search_section.slideToggle(750);
}


/**
 * Reglas aplicadas
 **/
var reglasDeValidacion = function() {
    var validationOptions = $.extend({}, formValidationDefaults, {
        rules: {
            fechaInicial: {
                required: true,
            },
            fechaFinal: {
                required: true,
            }
        },
        ignore: []
    });
    return validationOptions;
};


/**
 *  inicializar el listado
 **/
function __Inicializar_Table(nombreTabla) {
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [1, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,

    });
}


/**
 *  Obtiene la lista de los clientes activos
 **/
function ListarCategoriasActivos() {
    $.ajax({
        url: "ListarCategoriasAjax.do",
        datatype: "json",
        global: false,
        method: "GET",
        success: function(result) {
            if (result.aaData.length > 0) {
                $.each(result.aaData, function(index, modeloTabla) {
                    $('.selectCategoria').append('<option value="' + modeloTabla.id + '">' + modeloTabla.descripcion + '</option>');
                });
                $('.selectCategoria').selectpicker();
            }
        },
        error: function(xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
}




var facturas = { data: [] };

function ListarFacturas() {
    $('.totalExonerado').val(0);
    $('.totalCosto').val(0);
    $('.totalDescuento').val(0);
    $('.totalIVA').val(0);
    $('.totalVenta').val(0);

    var fechaInicio = $('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
    var idCliente = $('#categoria').val();
    var estado = $('.selectEstado').val();
    var table = $('.tableListar').DataTable();
    table
        .clear()
        .draw();
    facturas = { data: [] }
    var parametros = {
        fechaInicioParam: fechaInicio,
        fechaFinParam: fechaFin,
        idCaegoria: idCliente,
        estado: estado,
    }
    __Inicializar_Table('.tableListar')
    $.ajax({
        url: "ListaVentasByCategoria.do",
        datatype: "json",
        data: parametros,
        method: "GET",
        success: function(result) {
            if (result.aaData.length > 0) {
                loadListar(".tableListar", idioma_espanol, informacion_tabla, result.aaData)
                facturas.data = result.aaData
                console.log(facturas)
                    //__CorreoAlternativo();
                suma(facturas.data);

            } else {
                __Inicializar_Table('.tableListar');
            }
        },
        error: function(xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}


/**
 * 
 * suma de los tiquetes , facturas excluye notas de credito y debito y estados anulados 
 */
function suma(data) {
    var total = 0
    var totalExonerado = 0
    var totalCosto = 0
    var totalDescuento = 0
    var totalIVAI = 0
    var totalVenta = 0
    $.each(data, function(index, modeloTabla) {
        var estado = $('.selectEstado').val()
        if (modeloTabla.estado == 2 || modeloTabla.estado == 7 || modeloTabla.estado == 6) {
            totalExonerado = __valorFloat(modeloTabla.mont_exon) + totalExonerado;
            totalCosto = __valorFloat(modeloTabla.total_costo) + totalCosto;
            totalDescuento = __valorFloat(modeloTabla.total_desc) + totalDescuento;
            totalIVAI = __valorFloat(modeloTabla.total_neto) + totalIVAI;
            totalVenta = __valorFloat(modeloTabla.total_linea) + totalVenta;
        }


    })
    $('.totalExonerado').val(formatoDecimales(totalExonerado, 2))
    $('.totalCosto').val(formatoDecimales(totalCosto, 2))
    $('.totalDescuento').val(formatoDecimales(totalDescuento, 2))
    $('.totalIVA').val(formatoDecimales(totalIVAI, 2))
    $('.totalVenta').val(formatoDecimales(totalVenta, 2))

}

// traducciones del table
var idioma_espanol = {
    "sProcessing": "Procesando...",
    "sLengthMenu": 'Mostrar <select>' +
        '<option value="10">10</option>' +
        '<option value="25">25</option>' +
        '<option value="50">50</option>' +
        '<option value="100">100</option>' +
        '</select> registros',
    "sZeroRecords": "No se encontraron resultados.",
    "sEmptyTable": "Ning\u00FAn dato disponible en esta tabla.",
    "sInfo": "Registros del _START_ al _END_ de un total de _TOTAL_ ",
    "sInfoEmpty": "Registros del 0 al 0 de un total de 0 ",
    "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
    "sInfoPostFix": "",
    "sSearch": "Buscar:",
    "sUrl": "",
    "sInfoThousands": ",",
    "sLoadingRecords": "Cargando...",
    "oPaginate": {
        "sFirst": "Primero",
        "sLast": "\u00DAltimo",
        "sNext": "Siguiente",
        "sPrevious": "Anterior"
    },
    "oAria": {
        "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    }
}


function agregarInputsCombos() {
    // Agregar los input de busqueda 
    $('.tableListar tfoot th').each(function(e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();
        //No se toma en cuenta la columna de las acctiones(botones)
        //if ($(this).index() != 9) {
        var name = '<input id = "filtroCampos' + e + '"';
        $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
        //}
    })
}


var informacion_tabla = [
    { 'data': 'estadoSTR', "name": "estadoSTR", "title": "Estado", "autoWidth": true },
    { 'data': 'descrpcion', "name": "descrpcion", "title": "Descrip\u00F3n", "autoWidth": true },
    { 'data': 'tipoImpuestoSTR', "name": "tipoImpuestoSTR", "title": "Tipo Impuesto", "autoWidth": true },
    { 'data': 'codigoTarifaSTR', "name": "codigoTarifaSTR", "title": "Tarifa", "autoWidth": true },
    { 'data': 'impuesto', "name": "impuesto", "title": "Impuesto", "autoWidth": true },
    { 'data': 'totalCostoSTR', "name": "totalCostoSTR", "title": "Costo", "autoWidth": true },
    { 'data': 'totalDesccuentoSTR', "name": "totalDesccuentoSTR", "title": "Descuento", "autoWidth": true },
    { 'data': 'totalNetoSTR', "name": "totalNetoSTR", "title": "Total IVA", "autoWidth": true },
    { 'data': 'totalLineaSTR', "name": "totalLineaSTR", "title": "Total", "autoWidth": true },
    { 'data': 'montoExoneracionSTR', "name": "montoExoneracionSTR", "title": "Total Exonerado", "autoWidth": true },
];