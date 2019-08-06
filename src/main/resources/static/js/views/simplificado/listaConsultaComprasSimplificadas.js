$(document).ready(function() {
	$('#fechaInicial').click(function () {
		$(".enviarCorreo").hide();
		$(".descargar").hide();
	});

	$('#fechaFinal').click(function () {
		$(".enviarCorreo").hide();
		$(".descargar").hide();
	});
	_Init();
	
} );/*fin */

var _Init = function () {
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	EventoFiltro();
	__ComboEstado();
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
		    _consulta()
		}
	});

	$('.enviarCorreo').click(function () {
	
		__EnviarCorreo();
		
	});


	

	$(".enviarCorreo").hide();
	$(".descargar").hide();
    $('.btnLimpiarFiltros').click(function () {
        $("#fechaInicial").val(null);
		$("#fechaFinal").val(null);
		$(".enviarCorreo").hide();
		$(".descargar").hide();
		var table = $('.tableListar').DataTable();
		table
	   .clear()
	   .draw();
		$(".totalComprobante").val(null);
		$(".totalImpuestos").val(null);
		$(".totalImpuestos").val(null);
    });
    $("#filtros").validate(reglasDeValidacion());
	__ListaProveedores();
}


function __EnviarCorreo(){
	var fechaInicio=$('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
	var idProveedor = $('#idProveedor').val();
	var estado  = $('#estado').val();
	var totalDescuentos  = $('.totalDescuentos').val();
	var totalImpuesto  = $('.totalImpuestos').val();
	var total  = $('.totalComprobante').val();
var parametros = {
	fechaInicio:fechaInicio,
	fechaFin:fechaFin,
	idProveedor:idProveedor,
	estado:estado,
	totalDescuentos:totalDescuentos,
	totalImpuesto:totalImpuesto,
	total:total
}

	riot.mount('consulta-simpli',{parametros:parametros});
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



function _consulta(){
	$(".enviarCorreo").hide();
	$(".descargar").hide();
	$(".totalComprobante").val(null);
	$(".totalImpuestos").val(null);
	$(".totalImpuestos").val(null);
	var table = $('.tableListar').DataTable();
 	table
    .clear()
    .draw();
	haciendas = {data:[]}
	var fechaInicio=$('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
	var idProveedor = $('#idProveedor').val();
	var estado  = $('#estado').val();
	var parametros = {
		fechaInicio:fechaInicio,
		fechaFin:fechaFin,
		idProveedor:idProveedor,
		estado : estado
	}
	__Inicializar_Table('.tableListar')  
	$.ajax({
	   url: "ListarComprasSimplificadasAjax.do",
	   datatype: "json",
	   data:parametros ,
	   method:"GET",
	   success: function (result) {
		   if(result.aaData.length > 0){
			   loadListar(".tableListar",idioma_espanol,informacion_tabla,result.aaData)
			   agregarInputsCombos();
			   EventoFiltro();
			   __AnularCompra();
			   _totales(result.aaData)
			   BajarExcel()
			   $(".enviarCorreo").show();
			   $(".descargar").show();
		   
			   
		   }else{
			__Inicializar_Table('.tableListar')  
		   }           
	   },
	   error: function (xhr, status) {
		   mensajeErrorServidor(xhr, status);
		   console.log(xhr);
	   }
	});
}
function _totales(data){
	var totalComprobante = 0
	var totalImpuestos = 0
	var totalDescuentos = 0
	$.each(data, function( index, modeloTabla ) {
		totalComprobante = totalComprobante + __valorFloat(modeloTabla.totalComprobante)
		totalImpuestos = totalImpuestos + __valorFloat(modeloTabla.totalImpuesto)
		totalDescuentos = totalDescuentos + __valorFloat(modeloTabla.totalDescuentos)
	})

	$(".totalComprobante").val(formatoDecimales(totalComprobante,2));
	$(".totalImpuestos").val(formatoDecimales(totalImpuestos,2));
	$(".totalImpuestos").val(formatoDecimales(totalDescuentos,2));
}

/**
*  Bajar Excel 
**/
function BajarExcel(){
	var fechaInicio=$('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
	var idProveedor = $('#idProveedor').val();
	var estado  = $('#estado').val();
	var tem = "DescargarComprasSimplificadasAjax.do?fechaInicio=" + fechaInicio+"&"+"fechaFin="+fechaFin+"&"+"idProveedor="+idProveedor+"&"+"estado="+estado

	$(".descargar").attr("href", tem)
}

/**
* Estados
**/
function __ComboEstado(){
	$('.estado').append('<option value="'+"2"+'">'+"Facturado"+ '</option>');
	$('.estado').append('<option value="'+"5"+'">'+"Anulado"+ '</option>');
    
}

/**
*  Lista de los Proveedores
**/
function __ListaProveedores(){
    $.ajax({
        url: 'ListarProveedorSimplificadoActivosAjax.do',
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
var informacion_tabla = [ 
	{'data' :'nombreProveedor'    ,"name":"nombreProveedor"    ,"title" : "Proveedor"  ,"autoWidth" :true },
	{'data' :'numeroConsecutivo'  ,"name":"numeroConsecutivo"  ,"title" : "Consecutivo","autoWidth" :true },
	{'data' :'created_atSTR'      ,"name":"created_atSTR"      ,"title" : "Fecha Emision"  ,"autoWidth" :true },
	{'data' :'totalDescuentoSTR'  ,"name":"totalDescuentoSTR"  ,"title" : "Descuento"  ,"autoWidth" :true },
	{'data' :'totalImpuestoSTR'   ,"name":"totalImpuestoSTR"   ,"title" : "IVAI"       ,"autoWidth" :true },
	{'data' :'totalComprobanteSTR',"name":"totalComprobanteSTR","title" : "Total"      ,"autoWidth" :true },
    {'data' :'nombreUsuario'      ,"name":"nombreUsuario"      ,"title" : "Usuario"    ,"autoWidth" :true }
  ];

  /**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    var anular  ="";
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
			text: "Deseas anular la compra simplificada, Tener presente que el documento se encuentra registrado en hacienda?",
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
		idFactura:data.id
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		data : formulario,
		url : "AnularCompraSimplificadaAjax.do",
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
					_consulta()
				
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
		if ( $(this).index() != 7){
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