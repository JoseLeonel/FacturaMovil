$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
//	cargaMantenimiento()
}


/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento() {

	$('#tableListar').DataTable();
	
}

function __InicializarTabla(nombreTabla){
//	$(nombreTabla).dataTable().fnClearTable();
	$(nombreTabla).DataTable().destroy();
    $(nombreTabla).DataTable({
        destroy: true,
        "language": idioma_espanol,
        "sDom": 'lfrtip',
        "order": [0, 'asc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });
	var table = $(nombreTabla).DataTable();
 	table
    .clear()
    .draw();

}