$(document).ready(function() {
    _Init();

}); /* fin document */

var comprasData = { data: [] };

var _Init = function() {
    __Inicializar_Table('.tableListar');
    agregarInputsCombos();
    EventoFiltro();


    //  ListarRecepcionCompras();
    agregarInputsCombos();
    EventoFiltro();
    __MostrarPDF();
    __MostrarAceptarManual();
    checkBoxSelected()
    _marcarTodos();
    riot.mount('recepcion-compra');
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



function listadoRecepcionCompras() {

    $.ajax({
        url: 'listarRecepcionCompras.do',
        datatype: "json",
        method: "GET",
        success: function(result) {
            console.log(result);
            if (result.rows[0].length > 0) {
                console.log(result)
                self.compras.data = result.rows[0]
                __cargarTablaCompras()

            }
        }


    });
}

function __cargarTablaCompras() {
    $("#tableListarSIM").dataTable().fnClearTable();
    __InformacionDataTableCuentas();
    $('#tableListarSIM').DataTable().destroy();
    $("#tableListarSIM").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": formato_tabla,
    })
    $("#tableListarSIM").dataTable().fnAddData(self.sim.data);
}

function checkBoxSelected() {

    //Loop through all checked CheckBoxes in GridView.
    $('#tableListar tbody').on('change', 'input[type="checkbox"]', function(e) {
        var check1 = ($(this).attr('id'));
        var table = $('#tableListarSIM').DataTable();
        if (table.row(this).child.isShown()) {
            /*cuando el datatable esta en modo responsive*/
            var data = table.row(this).data();
        } else {
            var data = table.row($(this).parents("tr")).data();
        }
        var chk1 = document.getElementById(check1)
            // Este IF es para cuando usuario deschequea el SIM y se debe reversar el estado
        if (chk1.checked == false) {
            //reversar
            console.log(chk1.checked);
        } else {
            console.log(chk1.checked);
        }
        alert(1);
    });
}


function _marcarTodos() {
    if ($('#marcarDatos').is(':checked')) {
        $("input[type=checkbox]").prop('checked', true); //todos los check
    } else {
        $("input[type=checkbox]").prop('checked', false); //todos los check
    }
}
/**
 * Formato del listado
 */
var formato_tabla = [{
        'data': 'id',
        "name": "id",
        "bSortable": false,
        "bSearchable": false,
        "autoWidth": false,
        "render": function(id, type, row) {
            return __checkbox(row);
        }
    },
    { 'data': 'id', "name": "id", "title": "#id", "autoWidth": true },
    { 'data': 'consecutivo', "name": "consecutivo", "title": "#Consecutivo", "autoWidth": true },
    { 'data': 'fechaEmision', "name": "fechaEmision", "title": "Fecha Emision", "autoWidth": true },
    { 'data': 'emisorFactura', "name": "emisorFactura", "title": "#Proveedor", "autoWidth": true },
    { 'data': 'totalImpuestos', "name": "totalImpuestos", "title": "IVA", "autoWidth": true },
    { 'data': 'moneda', "name": "moneda", "title": "Moneda", "autoWidth": true },
    { 'data': 'totalComprobante', "name": "totalComprobante", "title": "Total", "autoWidth": true },
    {
        'data': 'id',
        "name": "id",
        "bSortable": false,
        "bSearchable": false,
        "autoWidth": true,
        "render": function(id, type, row) {
            return __Opciones(id, type, row);
        }
    }
];

/**
check de cuentas por cobrar
**/
function __checkbox(row) {
    var idCheck = 'check-' + row.id;
    var checked = " ";
    var inputcheck = '<div ><input type="checkbox" id="' + idCheck + '"  "  ' + checked + '></div>'
    return inputcheck;
}
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
            var name = '<input id = "filtroCampos' + e + '"';
            var title = $('.tableListar thead th').eq($(this).index())
                .text();
            // No se toma en cuenta la columna de las acctiones(botones)
            if ($(this).index() != 8 || $(this).index() != 0) {
                $(this).html(name + 'type="text" class="form-control"  placeholder="' + title + '" />');
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