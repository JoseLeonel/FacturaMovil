$(document).ready(function() {
	var advanced_search_section = $('#filtrosAvanzados');
        advanced_search_section.slideToggle(750);
	_Init();
	
} );/*fin document*/

var _Init = function () {
	//cargaMantenimiento()
	__Inicializar_Table('.tableListar');
	agregarInputsCombos();
	__ComboTipoDocumentosPara()
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
	listaClientesActivos()
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
	listaActividadEconomica();
	listaUsuarios();
}

/**
* Reglas aplicadas
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
        "order": [1, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        
    });    
}
/**
 * Listar las actividades economicas 
 */
function listaActividadEconomica(){
	$.ajax({
		 url: "ListaEmpresaActividadComercialPorPricipalAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
					$.each(result.aaData, function( index, modeloTabla ) {
						$('.selectActividadEconocimica').append('<option value="'+modeloTabla.codigo+'">'+modeloTabla.descripcion+ '</option>');
					});
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}
/**
*  Obtiene la lista de los clientes activos
**/
function listaClientesActivos(){
	$.ajax({
		 url: "ListarClientesActivosAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
					$.each(result.aaData, function( index, modeloTabla ) {
						$('.selectCliente').append('<option value="'+modeloTabla.id+'">'+modeloTabla.nombreCompleto+ '</option>');
					});
					$('.selectCliente').selectpicker();
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}

/**
*  Obtiene la lista de los clientes activos
**/
function listaUsuarios(){
	$.ajax({
		 url: "ListarUsuariosByEmpresaAjax.do",
		 datatype: "json",
		 global: false,
		 method:"GET",
		 success: function (result) {
				if(result.aaData.length > 0){
					$.each(result.aaData, function( index, modeloTabla ) {
						$('.selectUsuarios').append('<option value="'+modeloTabla.id+'">'+modeloTabla.nombreUsuario+ '</option>');
					});
					$('.selectUsuarios').selectpicker();
				} 
		 },
		 error: function (xhr, status) {
			  mensajeErrorServidor(xhr, status);
			  console.log(xhr);
		 }
	})
}



var facturas = {data:[]};

function ListarFacturas(){
	$('.total').val(0)   
	var fechaInicio=$('.fechaInicial').val();
    var fechaFin = $('.fechaFinal').val();
	var idCliente = $('#cliente').val();
	var tipoDocumento=$('#tipoDocumento').val();
	var actividadEconomica=$('.selectActividadEconocimica').val();
	var estado =$('.selectEstado').val();
	var selectUsuarios = $('#usuario').val();
	var table = $('.tableListar').DataTable();
 	table
    .clear()
    .draw();
	facturas = {data:[]}
	var parametros = {
		fechaInicio: fechaInicio,
		fechaFin:fechaFin,
		idCliente:idCliente,
		tipoDocumento:tipoDocumento,
		actividadEconomica:actividadEconomica,
		estado:estado,
		idUsuario:selectUsuarios
	}
	__Inicializar_Table('.tableListar')  
	$.ajax({
	   url: "ListarFacturasNotasCreditoAjax.do",
	   datatype: "json",
	   data:parametros ,
	   method:"GET",
	   success: function (result) {
		   if(result.aaData.length > 0){
			   loadListar(".tableListar",idioma_espanol,informacion_tabla,result.aaData)
			   facturas.data = result.aaData
			    __BajarPDF();
			    __imprimirPTV();
                __CorreoAlternativo();
                __VerDetalle();
                __EnviarCorreos();
				EventoFiltro();
				__NotaCredito()
		   }else{
			__Inicializar_Table('.tableListar');  
		   }           
	   },
	   error: function (xhr, status) {
		   mensajeErrorServidor(xhr, status);
		   console.log(xhr);
	   }
	});
}
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
// traducciones del table
var idioma_espanol = 
{
    "sProcessing":     "Procesando...",
    "sLengthMenu" : 'Mostrar <select>' + 
     '<option value="10">10</option>'
    + '<option value="25">25</option>'
    + '<option value="50">50</option>'
    + '<option value="100">100</option>'
    + '</select> registros',
    "sZeroRecords":    "No se encontraron resultados.",
    "sEmptyTable":     "Ning\u00FAn dato disponible en esta tabla.",
    "sInfo":           "Registros del _START_ al _END_ de un total de _TOTAL_ ",
    "sInfoEmpty":      "Registros del 0 al 0 de un total de 0 ",
    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
    "sInfoPostFix":    "",
    "sSearch":         "Buscar:",
    "sUrl":            "",
    "sInfoThousands":  ",",
    "sLoadingRecords": "Cargando...",
    "oPaginate": {
        "sFirst":    "Primero",
        "sLast":     "\u00DAltimo",
        "sNext":     "Siguiente",
        "sPrevious": "Anterior"
    },
    "oAria": {
        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    }
}
/**
* cargar los tipos de Documento de la factura
**/
function __ComboTipoDocumentosPara(){
	$('.tipoDocumento').append('<option value="'+"04"+'">'+$.i18n.prop("factura.tipo.documento.factura.tiquete")+ '</option>');
	$('.tipoDocumento').append('<option value="'+"01"+'">'+$.i18n.prop("factura.tipo.documento.factura.electronica")+ '</option>');
}

function agregarInputsCombos(){
	// Agregar los input de busqueda 
  $('.tableListar tfoot th').each( function (e) {
		var title = $('.tableListar thead th').eq($(this).index()).text();      
		//No se toma en cuenta la columna de las acctiones(botones)
		if ( $(this).index() != 10    ){
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

/**
*Formato del listado 
**/
var informacion_tabla = [ 
                        {'data' :'nombreUsuario'  ,"name":"nombreUsuario"    ,"title" : "Usuario"         ,"autoWidth" :true },
                        {'data' :'fechaEmisionSTR'                ,"name":"fechaEmision"                  ,"title" : "Fecha Emisi\u00F3n"   ,"autoWidth" :true },
                        {'data' :'numeroConsecutivo'              ,"name":"numeroConsecutivo"                ,"title" : "No.Consecutivo"  ,"autoWidth" :true },
						{'data' :'tipoDocSTR'           ,"name":"tipoDocSTR"       ,"title" : "Tipo documento"    ,"autoWidth" :true  },
						{'data' :'condicionVentaSTR'           ,"name":"condicionVentaSTR"       ,"title" : "Cond.Pago"    ,"autoWidth" :true  },
                        {'data' :'nombreCompleto' ,"name":"nombreCompleto"  ,"title" : "Cliente"   ,"autoWidth" :true ,
                            "render":function(nombreCompleto,type, row){
    						    return nombreCompleto ==null?"":nombreCompleto.length > 40?nombreCompleto.substring(0,40)+"....":nombreCompleto;
						    }
						},
						
                        {'data' :'nombreFactura'  ,"name":"nombreFactura"                          ,"title" : "A nombre"   ,"autoWidth" :true ,
                            "render":function(nombreFactura,type, row){
    						    return nombreFactura ==null?"":nombreFactura.length > 40 ? nombreFactura.substring(0,40)+"...":nombreFactura;
						    }
                        },
						{'data' :'totalComprobanteSTR'        ,"name":"totalComprobante"    ,"title" : "Total"       ,"autoWidth" :true },
						{'data' :'estadoSTR'               ,"name":"estadoSTR"           ,"title" : "Estado"      ,"autoWidth" :true ,
						"render":function(estadoSTR,type, row){
						  return estados(estadoSTR,row); //factura.js
						}},
                       
                        {'data' : 'id'                        ,"name":"id"                          ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                            "render":function(id,type, row){
                                return __Opciones(id,type,row); 
                            }
   		            }];

/**
* Opciones listado de los clientes
*/
function __Opciones(id,type,row){
    let menu = '<div class="dropdown">' 
    menu += '       <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' 
    menu += '             <span class="glyphicon glyphicon-list"></span> <span class="caret"></span></button>' 
    menu +=        '<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel"> ';
    menu += '<li><a href="#"  title="Mostrar" class="  btnMostrar" >Mostrar</a></li>'
    menu += '<li><a href="#"  title="Mostrar" class="  btnImprimir" >Imprimir</a></li>'
	menu += '<li><a href="#"  title="Bajar PDF" class="  btnPDF" >Bajar PDF</a></li>'
	menu += '<li><a href="#"  title="Nota Credito" class="  btnNotaCredito" >Nota Credito</a></li>'
    if(row.noFacturaElectronica == 0){
        menu += '<li><a href="#"  title="Envio del correo al cliente" class="  btnEnvioCorreoCliente" >Correo al Cliente</a></li>'
        menu += '<li><a href="#"  title="Envio de correo Alternativo" class="  btnEnvioCorreoAlternativo" >Correo Alternativo</a></li>'
    }
    menu += "</ul></div>"  
    return menu;          
}

/**
 * Nota de credito
 */
function __NotaCredito(){
	$('.tableListar').on('click','.btnNotaCredito',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
		$('.mostrarListadoDeFacturas').hide();
		var parametros = {
			consecutivo:data.numeroConsecutivo,
			codigoMoneda :'CRC'
		}
		riot.mount('nota-credito',{parametros:parametros});   
	});
}

/**
 * mostrar la abono
 */
function __BajarPDF(){
	$('.tableListar').on('click','.btnPDF',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        location.href = "generaFacturaPDF?idFactura=" + data.id
	});
}
/**
*  imprimir impresora punto de venta
**/
function __imprimirPTV(){
	$('.tableListar').on('click','.btnImprimir',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
		}
		__FacturaEnEspera(data);
	});
}

/**
*  Enviar a correo alternativo
**/
function __CorreoAlternativo(){
	$('.tableListar').on('click','.btnEnvioCorreoAlternativo',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        cargaMantenimiento(1,data);  
	});
}
/**
 * mostrar la abono
 */
function __VerDetalle(){
	$('.tableListar').on('click','.btnMostrar',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
		}
		$('.mostrarListadoDeFacturas').hide();
        cargaMantenimiento(2,data);  
	});
}
/**
 * Envio del correo al emisor y receptor
 */
function __EnviarCorreos(){
	$('.tableListar').on('click','.btnEnvioCorreoCliente',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        cargaMantenimiento(3,data)
	});
}
/**
 * LLamar al componente Riot 
 * @returns
 */
function cargaMantenimiento(tipoEjec,data) {
	riot.compile(function() {
		var parametros = {
			tipoEjecucion:tipoEjec,
			factura:data
		};
		 // here tags are compiled and riot.mount works synchronously
		var tags = riot.mount('lista-facturas',{parametros:parametros});
	});  
}
/**
 * Mostrar listado
 */
function __MostrarListado(){
	$('.mostrarListadoDeFacturas').show();

}

/**
 * Mostrar listado y actualizar
 */
function __MostrarListadoActualizado(){
	$('.mostrarListadoDeFacturas').show();
	ListarFacturas()

}

/**
*Si fue facturada o tiquete
**/
function evaluarFactura(data){
	if (data.message != null && data.message.length > 0) {
		 $.each(data.listaObjetos, function( index, modeloTabla ) {
			 //Envia a la pantalla de impresion
			 var parametros = {
					 factura:modeloTabla,
					 facturaDia:0
			 }    
			 riot.mount('ptv-imprimir',{parametros:parametros});
		 });
	 }
 }

/**
*  Factura en espera ,cliente y sus  detalles desde back end  Facturas que se encuentran Pendientes de Facturar
**/
function __FacturaEnEspera(factura){
    $.ajax({
        url: "ListarDetlleByFacturaAjax.do", 
        datatype: "json",
        data: {idFactura:factura.id},
        method:"POST",
        success: function (data) {
            if(data.aaData.length > 0){
               cargarDetallesFacturaEnEspera(data.aaData)
            }
            
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}


/**
*  Cargar detalles Factura en espera
**/
function cargarDetallesFacturaEnEspera(data){
    var factura = null
     $.each(data, function( index, modeloTabla ) {
        if(factura == null){
		   factura = modeloTabla.factura
        }
	})
	var parametros = {
		factura:factura,
		facturaDia:1
	}
    riot.mount('ptv-imprimir',{parametros:parametros});   

}
