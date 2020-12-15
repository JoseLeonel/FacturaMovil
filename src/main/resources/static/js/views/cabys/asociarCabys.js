$(document).ready(function() {
	//__ListaArticulos(" ");
   _Init();
   
} );/*fin document*/

var tarifas    = {aaData:[]};

var _Init = function () {
    // agregarInputsCombos();
    EventoFiltro();
    $('#codigo').keypress(function (e) {
      if (e.keyCode == 13) {
         __ListaArticulos()
      }

    });
    $('#descripcion').keypress(function (e) {
        if (e.keyCode == 13) {
           __ListaArticulos()
        }

      });
    

    
    $('.btn-consulta').click(function () {
    	__ListaArticulos();
      })

    $('.btncabysAsociar').hide();
    $('.btncabysAsociar').click(function () {
      enviarACambiarCategoria();
    })
    __listadoCategoriasActivas($(".caBys"),function(resultado){
    	impuestoCabys();
    	console.log(resultado);
    });
    comboTipoImpuesto(); 
    $(".tipoIVA").change(function() {
    	comboTarifa();
    })
    $("#caBys").change(function() {
    	impuestoCabys();
    })
    
    
  
}


function impuestoCabys(){
	$('.impuestoCabys').val(0);
	var cabysTemp = null;
	$.ajax({
        url: "MostrarCabysAjax.do",
       datatype: "json",
        data: {idCabys:$('#caBys').val()},
       method:"GET",
       success: function (data) {
    	   if (data.status != 200) {
               if (data.message != null && data.message.length > 0) {
                   sweetAlert("", data.message, "error");
               }
           }else{
        	   if (data.message != null && data.message.length > 0) {
        		   $.each(data.listaObjetos, function( index, modeloTabla ) {
        			   cabysTemp = modeloTabla;
        			   $('.impuestoCabys').val(cabysTemp.impuesto);
        			   actualizarComboTarifaConCabys(cabysTemp.impuesto);
        			   
        			   
        		   })
        	   }
           }
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
   })

}

function getMontoImpuesto(tipoImpuesto,codigoTarifa,array){
    if(tipoImpuesto.length ==0){
        return 0
    }
    if(tipoImpuesto ==null){
        return 0
    }
    var valor = getMontoTarifa(tipoImpuesto,codigoTarifa,array);
    valor = valor !=null?valor[0]:null
    return valor == null?0:valor.monto
}



function comboTipoImpuesto(){
	 var select = $('.tipoIVA');
	 select.empty();
     // se cargan los valores por defecto que existen en el combo
     select.append( '<option value="">'+'Exento</option>' );
     select.append( '<option value="'+"01"+'">'+ $.i18n.prop("tipo.impuesto.ventas") +'</option>' );
     select.append( '<option value="'+"07"+'">'+ $.i18n.prop("tipo.impuesto.servicio") +'</option>' );
     select.append( '<option value="'+"99"+'">'+ $.i18n.prop("tipo.impuesto.otros") +'</option>' );
     $('.tipoIVA').val("01")
     comboTarifa();
}

function actualizarComboTarifaConCabys(valor){

	if(valor == 0){
		$('.tarifa').val("01")
	}else if(valor == 1){
		$('.tarifa').val("02");
	}else if(valor == 2){
		$('.tarifa').val("03");
	}else if(valor == 4){
		$('.tarifa').val("04")
	}else if(valor == 8){
		$('.tarifa').val("07")
	}else if(valor == 13){
		$('.tarifa').val("08")
	}
}

function comboTarifa(){
	var select = $('.tarifa');
	select.empty();
	$.ajax({
        url: "ListarTarifasByTipoImpuestoAjax.do",
       datatype: "json",
        data: {tipoImpuesto:$('.tipoIVA').val()},
       method:"GET",
       success: function (result) {
           if(result.aaData.length > 0){
        	   tarifas =result.aaData; 
               $.each(result.aaData, function( index, modeloTabla ) {
                   select.append( '<option value="'+modeloTabla.tarifaIVAI.codigoTarifa+'">'+modeloTabla.tarifaIVAI.descripcion+"</optiondata-tokens>" );  
                })
           }            
       },
       error: function (xhr, status) {
           console.log(xhr);
            mensajeErrorServidor(xhr, status);
       }
   })
	
}
var listaArticulosGrupales      = {data:[]}


var selecciono = false;




function __ListaArticulos(){
	__InicializarTabla('.tableListar');  
	$("#tableListar").dataTable().fnClearTable();
	$('#tableListar').DataTable().destroy();
    var parametros = {
    		codigo:$('.codigo').val(),
    		descripcion:$('.descripcion').val(),
    		tipo:$('.tipo').val(),
    		cantidad:$('.cantidadLista').val()
    }  

    $.ajax({
        url: 'ListarArticuloCabysAjax.do',
        datatype: "json",
        method:"POST",
        data :parametros,
        success: function (result) {
            if(result.aaData.length > 0){
            	__cargarTablaArticulos(result)
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

function __cargarTablaArticulos(data) {
    __InicializarTabla('.tableListar')  
    $("#tableListar").dataTable().fnClearTable();
    $('#tableListar').DataTable().destroy();
    $("#tableListar").DataTable({
        destroy: true,
        "aLengthMenu": [
            [5, 10, 15, 25, -1],
            [5, 10, 15, 25, "All"]
        ],
        "language": idioma_espanol,
        "sDom": 'lrtip',
        "order": [],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
        "columns": informacion_tabla ,
    })
    $("#tableListar").dataTable().fnAddData(data.aaData);
    
    agregarInputsCombos();
    EventoFiltro();
    __MarcarArticulo();


}


function enviarACambiarCategoria(){
   if(listaArticulosGrupales.data.length == 0 ){
      swal({
         title: '',
          text: 'No se realizo el cambio , no hay articulos seleccinados para la nueva categoria',
          type: 'error',
          showCancelButton: false,
          confirmButtonText: 'Aceptar',
      })
      
   }
   var json  = JSON.stringify( listaArticulosGrupales)
   
   $("#listaArticuloGrupales").val(json);
   $("#tipoIVAParametros").val($("#tipoIVA").val());
   $("#tarifaParametros").val($("#tarifa").val());
   $("#impuestoParametros").val(getMontoImpuesto($("#tipoIVAParametros").val(),$("#tarifaParametros").val(),tarifas));
   
   var formulario = $("#filtros").serialize();
        swal({
           title: '',
           text: $.i18n.prop("cabys.cambio.aplicar"),
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#00539B',
            cancelButtonColor: '#d33',
            confirmButtonText:$.i18n.prop("confirmacion.si"),
            cancelButtonText: $.i18n.prop("confirmacion.no"),
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
        }).then(function (isConfirm) {
            if(isConfirm){
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AsociarCabysArticulosGrupalAjax.do',
                    success : function(data) {
                        if (data.status != 200) {
                            if (data.message != null && data.message.length > 0) {
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
                          __ListaArticulos("")
                           $("#listaArticuloGrupales").val(null); 
                           $("#impuestoParametros").val(null)
                           listaArticulosGrupales = {data:[]}
                             
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
                    }
                });
            }
        });
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
var informacion_tabla = [ {'data' :'id'             ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : false,
                           "render":function(id,type, row){
                           return  __checkbox(row);
                           }
                           },
                               {'data' :'nomb_cate'               ,"name":"nomb_cate"              ,"title" : "Categoria"        ,"autoWidth" :true,
                               "render":function(nomb_cate,type, row){
                                     return nomb_cate ==null?"Sin Cantegoria":row.nomb_cate;
                                }},
                               {'data' :'codigo'                  ,"name":"codigo"                 ,"title" : "Codigo"           ,"autoWidth" :true },
                               {'data' :'cod_cabys'                  ,"name":"cod_cabys"                 ,"title" : "Cabys"           ,"autoWidth" :true },
                               {'data' :'descripcion'             ,"name":"descripcion"            ,"title" : "Descripcion"      ,"autoWidth" :true },
                               {'data' :'impuesto'                ,"name":"impuesto"               ,"title" : "Impuesto"         ,"autoWidth" :true },
                               {'data' : 'estado'                 ,"name":"estado"          ,"title" : "Estado"      ,"autoWidth" :false,
                               "render":function(estado,type, row){
                                 return estadosActivoInactivo(estado,row);//factura.js
                                }},
                           ];
/**
check de cuentas por cobrar
**/
function __checkbox(row){
   var idCheck = 'check-'+row.id ;
   var checked = " ";
   var inputcheck = '<div ><input class = "checkFormato" type="checkbox" id="'+idCheck+'"  "  '+checked+'></div>'
   return  inputcheck ;
} 

/**
*  Seleccionar una cuenta por cobrar para el abono general
**/
function __MarcarArticulo() {
   $('#tableListar tbody').on('change','input[type="checkbox"]', function (e) {
       var check1 =  ($(this).attr('id'));
       var table = $('#tableListar').DataTable();
       if(table.row(this).child.isShown()){
           /*cuando el datatable esta en modo responsive*/
          var data = table.row(this).data();
       }else{  
          var data = table.row($(this).parents("tr")).data();
        }
        var chk1 =  document.getElementById(check1)
        if (chk1.checked == false){
            selecciono = false;
            __EliminarElemento(data.id);
       }
       else{
            selecciono = true;
            __incluir(data.id);
       }
       if(listaArticulosGrupales.data.length> 0){
         $('.btncabysAsociar').show()
      }else{
         $('.btncabysAsociar').hide();
      }   
   });
  
} 
/**
**  Selecionar el articulo a  cambiar a la categoria nueva
**/
function __incluir(elemento){
   if(listaArticulosGrupales.data.length == 0){
      __agregarElemento(elemento);
      return  
   }
   var resultado = false ;
   for (var count = 0; count < listaArticulosGrupales.data.length; count++) {
       if (listaArticulosGrupales.data[count].id == elemento ){// Si existe actualiza la cantidad
           resultado = true;
           break;
       }
   }
   if(resultado == false){
      __agregarElemento(elemento);  
   }
}
function __agregarElemento(elemento){
   listaArticulosGrupales.data.push(
      {
        id:elemento
      }
   );

}
function __EliminarElemento(elemento){
   var valor = {
      id:elemento
   }
   var index = listaArticulosGrupales.data.indexOf(valor);
   listaArticulosGrupales.data.splice(index, 1);
}

/**
 * Funcion para refrescar el listado
 */
function __mostrarListado(){
	var table = $('#tableListar').DataTable();
   table.ajax.reload( null, false);
   //ListarArticulos(" ");
	$('.mostrarListado').show();
}
/**
 * Funcion para regresar el listado
 */
function __mostrarRegresarAlListado(){
	$('.mostrarListado').show();
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 6 && $(this).index() != 0    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
           // Select
    	if ($(this).index() == 6 ){
    	    var select = $('<select id="combo1" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
       }
     
       if ($(this).index() == 1 ){
         var select = $('<select id="combo3"   class="form-control"><option value="">Todos</option></select>');
         // se cargan los valores por defecto que existen en el combo
         select = __listadoCategoriasActivasCombo(select);
         $(this).html(select);
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
  //      if ( event.which == 13 ) {
             if ( dataTableColumns.search() !== this.value ) {
                dataTableColumns.search( this.value ).draw();
             }
  //      }
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
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivasCombo(select){
  $.ajax({
       url: "ListarCategoriasActivasAjax.do",
      datatype: "json",
      method:"GET",
      success: function (result) {
           if(result.aaData.length > 0){
            $.each(result.aaData, function( index, modeloTabla ) {
               select.append( '<option value="'+modeloTabla.descripcion+'">'+modeloTabla.descripcion+'</option>' );       
            })
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  })
  return select;
}
/**
*  Mostrar listado datatable Categorias activas
**/
function __listadoCategoriasActivas(select,callback){
  $.ajax({
       url: "ListarCabysActivasAjax.do",
      datatype: "json",
      method:"GET",
      success: function (result) {
           if(result.aaData.length > 0){
            $.each(result.aaData, function( index, modeloTabla ) {
               select.append( '<option value="'+modeloTabla.id+'">'+ modeloTabla.codigo+"-"+modeloTabla.descripcion+"</option>" );  
               
            })
            select.selectpicker(
               {
                  style: 'btn-info',
                  size:10,
                  liveSearch: true
               }
           );
            select.selectpicker('refresh');
            callback("exitoso");
         }
      },
      error: function (xhr, status) {
          console.log(xhr);
           mensajeErrorServidor(xhr, status);
      }
  });
  
  return select;
}


