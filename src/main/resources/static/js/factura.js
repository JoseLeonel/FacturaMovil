
//Se configura el properties
$.i18n.properties({
    name: 'factura',
    mode: 'map',
    path: 'message/',
    language: 'es',
});

// Se configura un bloqueo para todo el ajax que se utiliza en la aplicacion
$(document)

        .ajaxStart(
                function () {
                    //$.blockUI({message : "<h4>Procesando..  <i class='fa fa-spinner fa-spin' style='font-size:48px;color:#009688'></i></h4>"});
                    $.blockUI({message: "<div style='padding: 5px; z-index: 99999;'Factura<div class='spinner'><div class='rect1'></div><div class='rect2'></div><div class='rect3'></div><div class='rect4'></div><div class='rect5'></div></div></div>",
                        css: {backgroundColor: '#00539B', color: '#FFD204', borderRadius: '10px', borderColor: '#FFD204'},
                        baseZ: 11000}
                    );
                }).ajaxStop($.unblockUI);




//traducciones del table
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
	
	function redondeoDecimales(numero,decimales)
	{
		var original=parseFloat(numero);
		return original.toFixed(decimales);
	}

function formatoDecimales(amount, decimals){
		 amount += ''; // por si pasan un numero en vez de un string
		    amount = parseFloat(amount.replace(/[^0-9\.]/g, '')); // elimino cualquier cosa que no sea numero o punto

		    decimals = decimals || 0; // por si la variable no fue fue pasada

		    // si no es un numero o es igual a cero retorno el mismo cero
		    if (isNaN(amount) || amount === 0) 
		        return parseFloat(0).toFixed(decimals);

		    // si es mayor o menor que cero retorno el valor formateado como numero
		    amount = '' + amount.toFixed(decimals);

		    var amount_parts = amount.split('.'),
		        regexp = /(\d+)(\d{3})/;

		    while (regexp.test(amount_parts[0]))
		        amount_parts[0] = amount_parts[0].replace(regexp, '$1' + ',' + '$2');

		return amount_parts.join('.');
	}


/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
    return isNumber(valor)?parseFloat(valor):0 ;
}
/**
*  Validar si es numero
**/
function isNumber(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}


/**
*
**/
function redondearDecimales(numero, decimales) {
    numeroRegexp = new RegExp('\\d\\.(\\d){' + decimales + ',}');   // Expresion regular para numeros con un cierto numero de decimales o mas
    if (numeroRegexp.test(numero)) {         // Ya que el numero tiene el numero de decimales requeridos o mas, se realiza el redondeo
        return Number(numero.toFixed(decimales));
    } else {
        return Number(numero.toFixed(decimales)) === 0 ? 0 : numero;  // En valores muy bajos, se comprueba si el numero es 0 (con el redondeo deseado), si no lo es se devuelve el numero otra vez.
    }
}

//Modal o redirect en caso de error
function mensajeErrorServidor(xhr, status) {
	console.log(status);
	console.log("-------------------" + xhr.getResponseHeader("UNAUTHORIZED"));
	if(xhr.getResponseHeader("UNAUTHORIZED")=="true"){
        window.location.href = "login";
	}else if (xhr.status == 500) {
		window.location.href = "ErrorPage.jsp?exceptionMessage="
			+ xhr.getResponseHeader("exception");
    }else {
    	 mensajeAlertErrorOConfirmacion('error',$.i18n.prop("mensaje.error.general"));    	
    } 
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie() {
    var user = getCookie("username");
    if (user != "") {
        alert("Welcome again " + user);
    } else {
        user = prompt("Please enter your name:", "");
        if (user != "" && user != null) {
            setCookie("username", user, 365);
        }
    }
}


//Funcion estandar para presentar los errores validaciones del servidor
function serverMessageJson(data) {
	// Se elimina el class de error anterior
	$(".errorServerSideJgrid").remove();
    $(".errorServerSideJgrid").show();

	// Si existe un error se despliega debajo de cada campo
	if (data.status != null && data.status != 200) {
        data.listaObjetos.forEach(function(fieldError) {
				// Id del campo en el formulario
				var selector = "#" + fieldError.field;
        		console.log(selector);
				// Leonel Para poder borrar los mensajes de error al presionar
				// el clic
				var selectorMensaje = "error" + fieldError.field;
        		console.log(selectorMensaje);
				// Se recorren los mensajes para cada propiedad se buscan los
				// mensajes que coinciden
				var encontrado = false;
				
                fieldError.codes.forEach(function(contCodeError) {        
					var error = contCodeError
					var propertieText = $.i18n.prop(error);
					// console.log(propertieText);
					if (propertieText.indexOf(error) < 1) {
						$(selector).after(
								"<div id = '" + selectorMensaje
										+ "' class='errorServerSideJgrid'>"
										+ $.i18n.prop(error) + "</div>");
						encontrado = true;
					}
				});
		});
		return [ false, data.message, "" ];
	} else {
		return [ true, data.message, "" ]; // response should be interpreted as
											// successful
	}
}


//Funcion estandar para presentar los errores validaciones del servidor
function serverMessageJsonClase(data) {
	// Se elimina el class de error anterior
	$(".errorServerSideJgrid").remove();
    $(".errorServerSideJgrid").show();

	// Si existe un error se despliega debajo de cada campo
	if (data.status != null && data.status != 200) {
        data.listaObjetos.forEach(function(fieldError) {
        		console.log(fieldError);
				// Id del campo en el formulario
				var selector = "." + fieldError.field;
        		console.log(selector);
				// Leonel Para poder borrar los mensajes de error al presionar
				// el clic
				var selectorMensaje = "error" + fieldError.field;
        		console.log(selectorMensaje);
				// Se recorren los mensajes para cada propiedad se buscan los
				// mensajes que coinciden
				var encontrado = false;
				
                fieldError.codes.forEach(function(contCodeError) {        
					var error = contCodeError
					var propertieText = $.i18n.prop(error);
					// console.log(propertieText);
					if (propertieText.indexOf(error) < 1) {
						$(selector).after(
								"<div id = '" + selectorMensaje
										+ "' class='errorServerSideJgrid'>"
										+ $.i18n.prop(error) + "</div>");
						encontrado = true;
					}
				});
		});
		return [ false, data.message, "" ];
	} else {
		return [ true, data.message, "" ]; // response should be interpreted as
											// successful
	}
}

/**
*   Agregar cuando quiero sacar los errores marcando el campo en la clase del html
**/
function __agregarPorClase(formulario,mensajeAlerAgregar,urlAgregar,urlListar,nombretable){
    if ($(formulario).valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        swal({
           title: '',
           text: mensajeAlerAgregar,
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
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : urlAgregar,
                    success : function(data) {
                        console.log(data);
                        if (data.status != 200) {
                        	serverMessageJsonClase(data);
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
                        	serverMessageJsonClase(data);
                            var table = $(nombretable).DataTable();
                            table.ajax.url(urlListar)
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                                	  
	                         })
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
   
}

function mensajeError(mensaje){
    swal({
        title: '',
        text: mensaje,
        type: 'error',
        showCancelButton: false,
        confirmButtonText: 'Aceptar',
                                          
    })
                 

}


function __modificarClase(formulario,mensajeAlerModificar,urlModificar,urlListar,nombretable){
	
    if ($(formulario).valid()) {
            // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        console.log(formulario)
        swal({
                title: "", 
                text: mensajeAlerModificar, 
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#00539B',
                cancelButtonColor: '#d33',
                confirmButtonText:$.i18n.prop("confirmacion.si"),
                cancelButtonText: $.i18n.prop("confirmacion.no"),
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                }).then(function (isConfirm) {
                    //Ajax
                    if(isConfirm){
                        $.ajax({
                            type : "POST",
                            dataType : "json",
                            data : formulario,
                            url : urlModificar,
                            success : function(data) {
                                if (data.status != 200) {
                                	serverMessageJsonClase(data);
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
                                    // Mensaje de exito
                                	serverMessageJsonClase(data)
                                    var table = $(nombretable).DataTable();
	                                table.ajax.url(urlListar)
	                                
	                                swal({
	                                	  title: '',
	                                	  text: data.message,
	                                	  type: 'success',
	                                	  showCancelButton: false,
	                                	  confirmButtonText: 'Aceptar',
	                                	  
	                                	})
	                                
	                                	
                                }
                        },
                            error : function(xhr, status) {
                            console.log(status);
                            mensajeErrorServidor(xhr, status);
                        }
                    });
                }
            });    
        
    }
}


/**
*   Agregar 
**/
function __agregarRegistro(formulario,mensajeAlerAgregar,urlAgregar,urlListar,nombretable){
    if ($(formulario).valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        swal({
           title: '',
           text: mensajeAlerAgregar,
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
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : urlAgregar,
                    success : function(data) {
                        console.log(data);
                        if (data.status != 200) {
                        	serverMessageJson(data);
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
                        	serverMessageJson(data);
                            var table = $(nombretable).DataTable();
                           
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                                	  
	                         })
	                         table.ajax.url(urlListar)
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
   
}

function mensajeAlertErrorOConfirmacion(tipo,mensaje){
	swal({
          title: '',
          text: mensaje,
          type: tipo,
          showCancelButton: false,
          confirmButtonText: 'Aceptar',
               	  
        })
	
}

function __modificarRegistro(formulario,mensajeAlerModificar,urlModificar,urlListar,nombretable){
	
    if ($(formulario).valid()) {
            // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $(formulario).serialize();
        console.log(formulario)
                        $.ajax({
                            type : "POST",
                            dataType : "json",
                            data : formulario,
                            url : urlModificar,
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
                                    // Mensaje de exito
                                    serverMessageJson(data)
                                    var table = $(nombretable).DataTable();
	                                table.ajax.url(urlListar)
	                                
	                                swal({
	                                	  title: '',
	                                	  text: data.message,
	                                	  type: 'success',
	                                	  showCancelButton: false,
	                                	  confirmButtonText: 'Aceptar',
	                                	  
	                                	})
	                                
                                    
                                }
                        },
                            error : function(xhr, status) {
                            console.log(status);
                            mensajeErrorServidor(xhr, status);
                        }
                    });
        
    }
}


/**
* incluir el boton agregar en cada mantenimiento 
**/
function includeActions(dataTables_wrapper,dataTables_length) {
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    console.log(header_length)
//    header_pointer.remove();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    //new_header += header_length;
   
    new_header += "<div class='add-new btn-agregar' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
    
    
}

/**
*  Activar Filtros del listado 
**/
function ActivarEventoFiltro(tabla){
	// Busquedas por Inpus
	var table = $(tabla).DataTable();
   // Busquedas por Inpus

    table.columns().every( function () {
        var dataTableColumns = this
   
        var searchTextBoxes = $(this.footer()).find('input');
        searchTextBoxes.on('keyup change',function(){
     	   dataTableColumns.search(this.value).draw();
        });
        $( 'select', this.footer() ).click(function (event) {
            if ( dataTableColumns.search() !== this.value ) {
            	
           	   dataTableColumns .search("^"+this.value, true, false ).draw();
            }
         } );
        
        var searchTextBoxesSelect = $(this.header()).find('select');
        searchTextBoxes.on('keyup change',function(){
     	   dataTableColumns.search(this.value).draw();
        });

        searchTextBoxesSelect.on('click',function(e){
     	   e.stopPrapagation();
        });
        
    } );
}

/**
 *  Carga el listar de los mantenimientos
 */

function loadListar(table,idioma,formatoTabla,data){
	$(table).DataTable().destroy();
        $(table).DataTable({
        destroy: true,
        "language": idioma,
        "sDom": 'lrtip',
        "order": [0, 'desc'],
        "bPaginate": true,
        'responsive': true,
        "bAutoWidth": true,
        "lengthChange": true,
       
        "columns": formatoTabla,
    });  
    $(table).dataTable().fnClearTable();
    $(table).dataTable().fnAddData(data);        
}


/**
*  Listar la tabla a aplicar el mantenimiento
**/
function __InicializarTabla(nombreTabla){
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




function caracteresInvalidos(value, colName) {
    var regEx = /^\w*$/; // \w = Alfanumericos y underscore _ unicamente. ^ =
    // inicio de cadena, * = contiene, $ = fin de cadena
    var cadena = quitarAcentos(value);
    if (regEx.test(cadena)) {
        return [true, " "];
    } else {
        return [false, colName + ": Caracteres inv\u00E1lidos"];
    }
}

function mensajeErrorQAManager(mensaje) {
    $.alert({
        icon: 'fa fa-warning',
        title: 'Error!',
        type: 'red',
        typeAnimated: true,
        content: mensaje,
    });
}

function mensajeExitoQAManager(mensaje) {
    $.alert({
        icon: 'fa fa-check',
        title: '\u00a1Transacc\u00F3n Exitosa!',
        type: 'green',
        typeAnimated: true,
        content: mensaje,
    });
}

function mensajeExitoRedirectQAManager(mensaje, urlRedirect) {
    result = $.confirm({
        title: '\u00C9xito!',
        content: mensaje,
        type: 'green',
        buttons: {
            confirm: {
                text: 'Continuar',
                btnClass: 'btn-green',
                action: function () {
                    window.location = urlRedirect;
                }
            }
        }
    });
}

// Modal o redirect en caso de error
function mensajeErrorServidorQAManager(xhr, status, mensaje) {
    if (xhr.getResponseHeader("UNAUTHORIZED") == "true") {
        window.location.href = "Login.do";
    } else if (xhr.status == 500) {
        window.location.href = "ErrorPage.jsp?exceptionMessage="
                + xhr.getResponseHeader("exception");
    } else {
        mensajeErrorQAManager(mensaje);
    }
}

function verificaSiContinua(mensajeConfirmacion, nombreFuncion) {
    result = $.confirm({
        title: '\u00a1Confirmaci\u00F3n!',
        content: mensajeConfirmacion,
        type: 'green',
        buttons: {
            cancel: {
                text: 'Cancelar',
            },
            confirm: {
                text: 'Aceptar',
                btnClass: 'btn-green',
                action: function () {
                    // Llama a la funcion que se pasa por parametro
                    eval(nombreFuncion + "()");
                }
            }
        }
    });
}

/*
 * Sustituye los carcateres especiales permitidos por los indicados en el
 * siguiente listado para que se validen correctament con la expresion regular
 * (var regEx = /^\w*$/;) Lo que se guarda en base de datos es lo que el usuario
 * digite y no lo que esta funcion sustituya.
 */
function quitarAcentos(cadena) { // Se debe agregar el codigo del caracter
    // que se desea validar, ya que solamente se
    // aceptan alfanumericos y underscore
    cadena = cadena.replace(/\u00C1/g, 'A');
    cadena = cadena.replace(/\u00E1/g, 'a');
    cadena = cadena.replace(/\u00C9/g, 'E');
    cadena = cadena.replace(/\u00E9/g, 'e');
    cadena = cadena.replace(/\u00CD/g, 'I');
    cadena = cadena.replace(/\u00ED/g, 'i');
    cadena = cadena.replace(/\u00D3/g, 'O');
    cadena = cadena.replace(/\u00F3/g, 'o');
    cadena = cadena.replace(/\u00D6/g, 'O');
    cadena = cadena.replace(/\u00F6/g, 'o');
    cadena = cadena.replace(/\u00DA/g, 'U');
    cadena = cadena.replace(/\u00FA/g, 'u');
    cadena = cadena.replace(/\u00DC/g, 'U');
    cadena = cadena.replace(/\u00FC/g, 'u');
    cadena = cadena.replace(/\u00D1/g, 'N');
    cadena = cadena.replace(/\u00F1/g, 'n');
    cadena = cadena.replace(/\u0020/g, '_'); // Cambia espacios en blanco por
    // underscore '_'
    cadena = cadena.replace(/\u002E/g, '_'); // Cambia puntos en blanco por
    // underscore '_'
    return cadena;
}

// Formateador de moneda
/*
 * http://stackoverflow.com/questions/19424076/jqgrid-using-a-predefined-formatter-inside-a-custom-formatter
 * return $.fmatter.util.NumberFormat(cellValue, $.jgrid.formatter.currency)
 *
 */
function formatearMoneda(cellvalue, options, rowObject) {
    var valor = $.fmatter.util.NumberFormat(cellvalue,
            $.jgrid.formatter.currency);
    if (rowObject["tipoMonedaRecibo"] == 2) { // Dolares
        valor = "&#36;" + valor;
    } else {
        valor = "&#162;" + valor;
    }
    return valor;
}


/**
 * Confirmar actualizaciones de mantenimientos 
 */
function _confirmacion(formulario, accion, mantenimiento) {

    swal({
        title: "\u00BFEst\u00E1s seguro?",
        text: "Estar\u00EDas modificando la informaci\u00F3n actual de " + mantenimiento + ".",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '\u00a1S\u00ED, modificarlo!',
        cancelButtonText: '\u00a1No, quiero volver!',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function () {
        $('#' + formulario).attr('action', accion);
        $('#' + formulario).submit();
        formulario = "";
        accion = "";
        mantenimiento = "";
    }, function () {
    });
    return false;
}

function formatoFecha(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY');
}

function formatoFechaHora(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY HH:mm:ss');
}

function displayDate_detail(fecha) {
    return fecha == null?"":moment(fecha).format('DD/MM/YYYY h:mm:ss a');
}

function semaforoUI(semaforo) {
    
    var color = semaforo == 3 ? '#d62e2e' : semaforo == 2 ? '#ceb026' : '#008000';
    var icon = semaforo == 3 ? '<i class="fa fa-times"></i>' : semaforo == 2 ? '<i class="fa fa-exclamation-triangle"></i>' : '<i class="fa fa-check"></i>';
    return '<div style="margin-left: 10px; border-radius: 50px; width: 22px; height: 22px; text-align: center; line-height: 1.5; font-size: 16px; background-color: ' + color + '; color: #FFF;">' + icon + '</div>';
}

/**
 * Funcion para iniciar los rangos de fechas , es utilizada
 * en los modulos de consulta y atencion
 * @param element
 * @returns
 */

function initDateRangePicker(element) {
    var start = moment().subtract(7, 'days');
    var end = moment(); 
    element.daterangepicker({
        "locale": { 
            "format": "DD/MM/YYYY",
            "separator": " - ",
            "applyLabel": "Aplicar",
            "cancelLabel": "Cancelar",
            "fromLabel": "Desde",
            "toLabel": "Hasta",
            "customRangeLabel": "Custom",
            "daysOfWeek": [
                "Do",
                "Lu",
                "Ma",
                "Mi",
                "Ju",
                "Vi",
                "Sa"
            ],
            "monthNames": [
                "Ene",
                "Feb",
                "Mar",
                "Abr",
                "May",
                "Jun",
                "Jul",
                "Ago",
                "Set",
                "Oct",
                "Nov",
                "Dic"
            ],
            "firstDay": 1
        },
        "startDate": start.format('DD/MM/YYYY'),
        "endDate": end.format('DD/MM/YYYY'),
    });
}

