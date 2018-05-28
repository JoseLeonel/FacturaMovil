/**
 * 
 * @param select  combo de empresa
 * @returns
 */
function cargaComboEmpresa(select) {
    $.ajax({
        url: "ListarEmpresasAjaxToCombo.do",
        datatype: "json",
        type: "GET",
        success: function (data) {
            select.append('<option value="' + 9999 + '">' + "Seleccionar" + '</option>');
            $.each(data.rows, function (index, empresa) {
                select.append('<option value="' + empresa.codigo + '">' + empresa.nombre + '</option>');
            });
            $("select option[value='9999']").attr("selected", "selected");
            select.selectpicker('refresh');
            return select;
        },
        error: function (xhr, status) {
            showMensajeError(xhr, status, $.i18n.prop("mensaje.error.general"));
        }
    });
}



/**
 * 
 * @param select  combo de usuarios
 * @returns
 */
function cargaComboUsuario(select) {
	return;
    $.ajax({
        url: "ListarUsuarioAjaxToCombo.do",
        datatype: "json",
        type: "GET",
        success: function (data) {
            select.append('<option value="' + 9999 + '">' + "Seleccionar" + '</option>');
            $.each(data.rows, function (index, usuario) {
                select.append('<option value="' + empresa.codigo + '">' + empresa.nombre + '</option>');
            });
            $("select option[value='9999']").attr("selected", "selected");
            select.selectpicker('refresh');
            return select;
        },
        error: function (xhr, status) {
            showMensajeError(xhr, status, $.i18n.prop("mensaje.error.general"));
        }
    });
}

function cargaComboTipoDocumento(select){
	select.append('<option value="' + 9999 + '">' + "Seleccionar" + '</option>');
    
	$("select option[value='9999']").attr("selected", "selected");
	select.append('<option value="' + 1 + '">' +  $.i18n.prop("auditoria.instalaciones") + '</option>');
	select.append('<option value="' + 2 + '">' +  $.i18n.prop("auditoria.aseguramiento") + '</option>');
    select.selectpicker('refresh');
    return select;


}

/**
 * Carga los pueblos por empresa
 * @param select
 * @param empresa
 * @param parametros = son los valores que se pasan en los filtros avanzados en los modulos de atencion y listados.
 * @returns
 */
function cargaComboPueblo(select, empresa,parametros) {
	if(empresa == ""){
		select.find('option').remove().end();
		select.append('<option value="' + 9999 + '">'
				+ $.i18n.prop("atencion.OST.todos") + '</option>');
		$("select option[value='9999']").attr("selected",
		"selected");
		select.selectpicker('refresh');
		return select;
		
	}
	var dataFiltro = parametros.serialize();

	$.ajax({
				data : dataFiltro,
				url : 'ListarPuebloPorEmpresaAjax.do',
				datatype : "json",
				type : "POST",
				success : function(data) {
					select.find('option').remove().end();
					select.append('<option value="' + 9999 + '">'
							+ $.i18n.prop("atencion.OST.todos") + '</option>');
					if (data.status != 200) {
						if (data.message != null && data.message.length > 0) {
							sweetAlert("Error...", data.message, "error");
							select.find('option').remove().end();
							select.append('<option value="' + 9999 + '">'
									+ $.i18n.prop("atencion.OST.todos") + '</option>');
							$("select option[value='9999']").attr("selected",
							"selected");
							select.selectpicker('refresh');
							return select;
						}
					} else {
						if (data.message != null && data.message.length > 0
								&& data.listaObjetos.length > 0) {
							$.each(data.listaObjetos, function(index, pueblo) {
								select.append('<option value="' + pueblo.codigo
										+ '">' + pueblo.nombre + '</option>');

							});
							$("select option[value='9999']").attr("selected",
									"selected");
							select.selectpicker('refresh');
							return select;

						} else {
							select.find('option').remove().end();
							select.append('<option value="' + 9999 + '">'
									+ $.i18n.prop("atencion.OST.todos") + '</option>');						}
						    $("select option[value='9999']").attr("selected",
						   "selected");
						   select.selectpicker('refresh');
						   return select;

					}

				},
				error : function(xhr, status) {
					sweetAlert("Error...", status, "error");
					select.find('option').remove().end();
					select.append('<option value="' + 9999 + '">'
							+ $.i18n.prop("atencion.OST.todos") + '</option>');
					$("select option[value='9999']").attr("selected",
					"selected");
					select.selectpicker('refresh');
					return select;
					
				}
			});
}



/**
 * Devuelve el valor del estado por descripcion
 * @param estado
 * @returns
 */
function valueEstadoForStorageOST(estado) {
    switch (estado) {
        case $.i18n.prop("orden.estado.pendiente"):
            return 1;
            break;
        case $.i18n.prop("orden.estado.aprovisionado"):
            return 2;
            break;
        case $.i18n.prop("orden.estado.enCampo"):
            return 3;
            break;
        case $.i18n.prop("orden.estado.finalizado"):
            return 4;
            break;
        case $.i18n.prop("orden.estado.devuelta.simo"):
            return 5;
            break;
        case $.i18n.prop("orden.estado.devuelta.rdt"):
            return 6;
            break;
        default:
            return estado;
    }
}

/**
 *Descripcion del estado de la OST 
 * @param estado
 * @returns
 */
function descripcionEstadoOST(estado) {
    switch (estado) {
        case 1:
            return $.i18n.prop("orden.estado.pendiente");
            break;
        case 2:
            return $.i18n.prop("orden.estado.aprovisionado");
            break;
        case 3:
            return $.i18n.prop("orden.estado.enCampo");
            break;
        case 4:
            return $.i18n.prop("orden.estado.finalizado");
            break;
        case 5:
            return $.i18n.prop("orden.estado.devuelta.simo");
            break;
        case 6:
            return $.i18n.prop("orden.estado.devuelta.rdt");
            break;
        default:
            return estado;
    }
}

/**
 * Descripcion del Estado de Averias
 * @param estado
 * @returns
 */

function descipcionEstadoAveria(estado) {
    switch (estado) {
        case 1:
            return $.i18n.prop("averia.estado.pendiente");
            break;
        case 2:
            return $.i18n.prop("averia.estado.enCampo");
            break;
        case 3:
            return $.i18n.prop("averia.estado.finalizada");
            break;            
        default:
            return estado;
    }
}



/**
 * Descripcion del valor de estado en averias
 * @param estado
 * @returns
 */
function valueEstadoForStorageAveria(estado) {
    switch (estado) {
        case $.i18n.prop("averia.estado.pendiente"):
            return 1;
            break;
        case $.i18n.prop("averia.estado.enCampo"):
            return 2;
            break;
        case $.i18n.prop("averia.estado.finalizada"):
            return 3;
            break;

        default:
            return estado;
    }
}


/**
 * Funcion para crear el combo de estados
 * @param select
 * @returns
 */
function cargaComboEstadoOST(select,tipo) {
	select.find('option').remove().end();
	select.append('<option value="' + 9999 + '">'
			+ $.i18n.prop("atencion.OST.todos") + '</option>');
	
    
    select.append('<option value="' + '1' + '">' + $.i18n.prop("orden.estado.pendiente") + '</option>');
    select.append('<option value="' + '2' + '">' + $.i18n.prop("orden.estado.aprovisionado") + '</option>');
    select.append('<option value="' + '3' + '">' + $.i18n.prop("orden.estado.enCampo") + '</option>');
    
    select.append('<option value="' + '5' + '">' + $.i18n.prop("orden.estado.devuelta.simo") + '</option>');
    if(tipo == 1 ){
    	select.append('<option value="' + '6' + '">' + $.i18n.prop("orden.estado.devuelta.rdt") + '</option>');
    }
    if(tipo == 1 ){
    	select.append('<option value="' + '4' + '">' + $.i18n.prop("orden.estado.finalizado") + '</option>');		
    }
    $("select option[value='9999']").attr("selected",
	"selected");
    select.selectpicker('refresh');
    return select; 
}

/**            funciones de averias    **/

/**
 * Crear el combo de estados para averias
 * @param select
 * @returns
 */
function cargaComboEstadoAverias(select,tipo) {
	select.find('option').remove().end();
	select.append('<option value="' + 9999 + '">'
			+ $.i18n.prop("atencion.OST.todos") + '</option>');
    select.append('<option value="' + '1' + '">' + $.i18n.prop("averia.estado.pendiente") + '</option>');
    select.append('<option value="' + '2' + '">' + $.i18n.prop("averia.estado.enCampo") + '</option>');
    
    if(tipo == 1 ){
    	select.append('<option value="' + '3' + '">' + $.i18n.prop("averia.estado.finalizada") + '</option>');		
    }
    $("select option[value='9999']").attr("selected",
	"selected");
    select.selectpicker('refresh');
    return select; 

}

//combo de estados
function valueEstadoForStorageAverias(estado) {
	switch (estado) {
	case $.i18n.prop("averia.estado.pendiente"):
		return 1;
		break;
	case $.i18n.prop("averia.estado.enCampo"):
		return 2;
		break;
	case $.i18n.prop("averia.estado.finalizada"):
		return 3;
		break;
	
	default:

		return estado;
	}
}

/**
 * Descripcion de cada estado de la verias
 * 
 * @param estado
 * @returns
 */
function descripcionEstadoAveria(estado) {
	switch (estado) {
	case 1:
		return $.i18n.prop("averia.estado.pendiente");
		break;
	case 2:
		return $.i18n.prop("averia.estado.enCampo");
		break;
	case 3:
		return $.i18n.prop("averia.estado.finalizada");
		break;

	default:
		return estado;
	}

}

