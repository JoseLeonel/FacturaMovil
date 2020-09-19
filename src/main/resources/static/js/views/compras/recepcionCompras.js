$(document).ready(function() {
    _Init();

}); /* fin document */

var _Init = function() {
    __Inicializar_Table('.tableListar');
    agregarInputsCombos();
    EventoFiltro();

    $('#panelFiltros').click(function() {
        var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
    });
    ListarRecepcionCompras();
}

/**
 * inicializar el listado
 */
function __Inicializar_Table(nombreTabla) {
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

var ListarRecepcionCompras = function() {
    var table = $('#tableListar').DataTable({
        "responsive": true,
        "bAutoWidth": true,
        "destroy": true,
        "order": [1, 'asc'],
        "bInfo": true,
        "bPaginate": true,
        "bFilter": true,
        "bDeferRender": true,
        "sDom": 'lrtip',
        "searching": true,
        "processing": false,
        "serverSide": true,
        "sort": "position",
        "lengthChange": true,
        "ajax": {
            "url": "listarRecepcionCompras.do",
            "deferRender": true,
            "type": "GET",
            "dataType": 'json',

        },
        "columns": formato_tabla,
        "language": idioma_espanol,
    }); // fin del table
    agregarInputsCombos();
    EventoFiltro();
    __MostrarPDF();
    __MostrarAceptarManual();

}

/**
 * Formato del listado
 */
var formato_tabla = [{
    'data': 'id',
    "name": "id",
    "title": "#id",
    "autoWidth": true
}, {
    'data': 'consecutivo',
    "name": "consecutivo",
    "title": "#Consecutivo",
    "autoWidth": true
}, {
    'data': 'fechaEmision',
    "name": "fechaEmision",
    "title": "Fecha Emision",
    "autoWidth": true
}, {
    'data': 'emisorFactura',
    "name": "emisorFactura",
    "title": "#Proveedor",
    "autoWidth": true
}, {
    'data': 'totalImpuestos',
    "name": "totalImpuestos",
    "title": "IVA",
    "autoWidth": true
}, {
    'data': 'moneda',
    "name": "moneda",
    "title": "Moneda",
    "autoWidth": true
}, {
    'data': 'totalComprobante',
    "name": "totalComprobante",
    "title": "Total",
    "autoWidth": true
}, {
    'data': 'id',
    "name": "id",
    "bSortable": false,
    "bSearchable": false,
    "autoWidth": true,
    "render": function(id, type, row) {
        return __Opciones(id, type, row);
    }
}];

/**
 * Opciones listado de los clientes
 */
function __Opciones(id, type, row) {
    let menu = '<div class="dropdown">'
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>'
    menu += '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
    menu += '<li><a href="#"  title="Aceptar Manual" class="btnAceptarXMLManual  btnBajarXML" >Aceptar Manual</a></li>'
    menu += '<li><a href="#"  title="Bajar XML Respuesta de Triburacion" class="  btnRespuestaHacienda" >XML Respuesta</a></li>'
    menu += "</ul></div>"
    return menu;
}

function __MostrarPDF() {
    $('.tableListar').on('click', '.btnPDF', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
        location.href = pathWebRecepcionCompras + "repositorio/" + data.facturaPdf;


    });
}




function __MostrarAceptarManual() {
    $('.tableListar').on('click', '.btnAceptarXMLManual', function(e) {
        var table = $('#tableListar').DataTable();
        if (table.row(this).child.isShown()) {
            //cuando el datatable esta en modo responsive
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
        gargarXML(data)

    });
}

function gargarXML(data) {
    $.ajax({
        url: "http://localhost:8083/api-v1/base64",
        datatype: "json",
        data: { ruta: data.facturaXml },
        method: "GET",
        success: function(data) {
            var parametros = {
                xml: data.xmlString,
                tipoEjecucion: 1
            }
            riot.mount('recepcion-api', { parametros: parametros });


        },
        error: function(xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });


}

/**
 * Formato de la fecha con hora
 */
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

function agregarInputsCombos() {
    // Agregar los input de busqueda
    $('.tableListar tfoot th').each(
        function(e) {
            var title = $('.tableListar thead th').eq($(this).index())
                .text();
            // No se toma en cuenta la columna de las acctiones(botones)
            if ($(this).index() != 7) {
                $(this).html(
                    '<input id = "filtroCampos" type="text" class="form-control"  placeholder="' +
                    title + '" />');
            }
        })
}
/**
 * Eventos del filtro
 */
function EventoFiltro() {
    // Busquedas por Inpus
    var table = $('#tableListar').DataTable();
    table.columns().every(function() {
        var dataTableColumns = this
        $('input', this.footer()).keypress(function(event) {
            if (event.which == 13) {
                if (dataTableColumns.search() !== this.value) {
                    dataTableColumns.search(this.value).draw();
                }
            }
        });
        var searchTextBoxes = $(this.header()).find('input');
        searchTextBoxes.on('keyup change', function() {
            dataTableColumns.search(this.value).draw();
        });
        $('select', this.footer()).click(function(event) {
            if (dataTableColumns.search() !== this.value) {
                dataTableColumns.search(this.value).draw();
            }
        });
        var searchTextBoxesSelect = $(this.header()).find('select');
        searchTextBoxes.on('keyup change', function() {
            dataTableColumns.search(this.value).draw();
        });
        searchTextBoxesSelect.on('click', function(e) {
            e.stopPrapagation();
        });
        searchTextBoxes.on('click', function(e) {
            e.stopPrapagation();
        });
    });
}