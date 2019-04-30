$(document).ready(function() {
	_Init();
	
} );/*fin document*/

var _Init = function () {
	//cargaMantenimiento()
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	EventoFiltro();
	$('.datepickerFechaFinal').datepicker(
		{
		  format: 'yyyy-mm-dd',
		  todayHighlight:true,
		}
	);
	$('.datepickerFechaInicial').datepicker(
 	{
	 format: 'yyyy-mm-dd',
	 todayHighlight:true,
 	}
	);
	$('#panelFiltros').click(function () {
        var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
	});
	$('#bontonBusqueda').click(function () {
		if ($("#filtros").valid()) {
		    ListarFacturas()
		}
    });
    $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
        $("#fechaFinal").val(null);
    });
    $("#filtros").validate(reglasDeValidacion());
	__ListaProveedores();
	}

/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			fechaInicial : {
				required : true,
			},
			fechaFinal : {
				required : true,
            }   
                                            
                        
		},
		ignore : []

	});
	return validationOptions;
};

/**
*  inicializar el listado
**/
function __Inicializar_Table(nombreTabla){
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

var ListarFacturas = function(){
	var fechaInicial=$('.fechaInicial').val();
    var fechaFinal = $('.fechaFinal').val();
	var idProveedor = $('#idProveedor').val();
	var table  =  $('#tableListar').DataTable( {
	"responsive": true,
	 "bAutoWidth" : true,
	"destroy":true,
	"order": [ 1, 'asc' ],
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
			"url":"ListarComprasNoAnuladasAjax.do?fechaInicio=" + fechaInicial+"&"+"fechaFin="+fechaFinal+"&"+"idProveedor="+idProveedor,
			"deferRender": true,
			"type":"GET",
					"dataType": 'json',
					
			  },
	"columns" : formato_tabla,
	"language" : idioma_espanol,
} );//fin del table
agregarInputsCombos();
EventoFiltro();
__AnularCompra();

}

/**
*  Lista de los Proveedores
**/
function __ListaProveedores(){
    $.ajax({
        url: 'ListarProveedoresAjax.do',
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
				$.each(result.aaData, function( index, modeloTabla ) {
				   $('.selectidProveedor').append('<option value="'+modeloTabla.id+'">'+modeloTabla.nombreCompleto+ '</option>');
				});
				$('.selectidProveedor').selectpicker();
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}
/**
*Formato del listado 
**/
var formato_tabla = [ 
	{'data' :'proveedor'   ,"name":"proveedor"    ,"title" : "Proveedor"     ,"autoWidth" :true ,
	"render":function(proveedor,type, row){
			return row.proveedor !=null?row.proveedor.nombreCompleto > 50 ?row.proveedor.nombreCompleto.substring(0,50):row.proveedor.nombreCompleto:"Sin Asociar";
		}
   },
   {'data' :'consecutivo'                ,"name":"consecutivo"                 ,"title" : "Consecutivo"   ,"autoWidth" :true },
   {'data' :'fechaCompra'                ,"name":"fechaCompra"                 ,"title" : "Fecha Compra"  ,"autoWidth" :true ,
		"render":function(fechaCompra,type, row){
			return __displayDate_detail(fechaCompra);
		 }
   },
   {'data' :'fechaIngreso'               ,"name":"fechaIngreso"                ,"title" : "Fecha Ingreso" ,"autoWidth" :true ,
		"render":function(fechaIngreso,type, row){
			return fechaIngreso !=null?formatoFechaHora(fechaIngreso):null;
		 }
   
   },
   {'data' :'fechaCredito'               ,"name":"fechaCredito"                ,"title" : "Fecha Credito" ,"autoWidth" :true ,
		"render":function(fechaCredito,type, row){
			return fechaCredito !=null? __displayDate_detail(fechaCredito):null;
		 }
   
   },
   {'data' :'totalCompra'             ,"name":"totalCompraSTR"              ,"title" : "Total"         ,"autoWidth" :true },
   {'data' :'usuarioIngresoInventario.nombreUsuario'   ,"name":"usuarioIngresoInventario.nombreUsuario"    ,"title" : "Usuario"    ,"autoWidth" :true },
   {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
	"render":function(id,type, row){
		  return __Opciones(id,type,row);
	 }
  }];

  /**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    var anular  = '<a href="#"  title="Anular Compra" class="btn btn-danger  btn-anular btnAnularCompra" role="button"> </a>';
  return  anular ;        
}

/**
 * anular Compra
 */
function __AnularCompra(){
	$('.tableListar').on('click','.btnAnularCompra',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
		}
		swal({
			title: '',
			text: "Deseas anular la compra?",
			 type: "warning",
			 showCancelButton: true,
			 confirmButtonColor: '#00539B',
			 cancelButtonColor: '#d33',
			 confirmButtonText:$.i18n.prop("confirmacion.si"),
			 cancelButtonText: $.i18n.prop("confirmacion.no"),
			 confirmButtonClass: 'btn btn-success',
			 cancelButtonClass: 'btn btn-danger',
		 }).then(function (isConfirm) {
			 //Ajax__inicializarTabla();
			 if(isConfirm){
				__AplicarAnularCompra(data);
			 }
		 });
   
 	});
}

function __AplicarAnularCompra(data){
	console.log(data);
	var formulario= {
		idCompra:data.id
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		data : formulario,
		url : "AnularCompraAjax.do",
		success : function(data) {
			if (data.status != 200) {
				serverMessageJson(data);
				if (data.message != null && data.message.length > 0) {
				   // swal('',data.message,'error');
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
		console.log(status);
		mensajeErrorServidor(xhr, status);
	}
});

}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY ');
}

function agregarInputsCombos(){
	// Agregar los input de busqueda 
  $('.tableListar tfoot th').each( function (e) {
		var title = $('.tableListar thead th').eq($(this).index()).text();      
		//No se toma en cuenta la columna de las acctiones(botones)
		if ( $(this).index() != 9    ){
			 $(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	  }
  })
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