
//Se configura el properties
$.i18n.properties({
    name: 'factura',
    mode: 'map',
    path: 'message/',
    language: 'es',
});

var pathWebFactura  = 'factura';
var pathWebRecepcionCompras  = 'http://localhost:8083/api-v1/';

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



/**
 * Carga imagen
 * @param Data64
 * @returns
 */
function cargarImagen(data64,id){
    var imagenTemporal = data64;

    $(id).attr("width", "200").attr("height", "200")
    $(id).attr("src",imagenTemporal != null ? decodeBase64(imagenTemporal):'');

    
}
/************************************************************************************rutinas de factura de la venta ****************************************************************
/**
 * Aplicar a clientes exonerados
 * @param porcentajeExoneracion
 * @param impuesto
 * @returns
 */
function getPorcentajeExoneracion(porcentajeExoneracion, impuesto) {
	var valor = 0;
	porcentajeExoneracion = __valorNumerico(porcentajeExoneracion );
	if(porcentajeExoneracion != null) {
	  if(porcentajeExoneracion > 0) {
		if(impuesto < porcentajeExoneracion ) {
           valor = impuesto; 					
		}else {
			valor = porcentajeExoneracion;
		}
	  }
	}
	return valor;
}

function getMontoExoneracionSubTotal(tipoDocumentoExonerado,impuesto, porcentajeExoneracion, subTotal, montoImpuesto) {
	impuesto = __valorNumerico(impuesto);
	montoImpuesto = __valorNumerico(montoImpuesto);
	subTotal = __valorNumerico(subTotal);
	tipoDocumentoExonerado = tipoDocumentoExonerado == null ? "" : tipoDocumentoExonerado;
	porcentajeExoneracion = porcentajeExoneracion == null ? 0 : porcentajeExoneracion;
	if (tipoDocumentoExonerado.length == 0) {
		return 0;
	}
	var resultado = 0;
	resultado = subTotal * porcentajeExoneracion;
	resultado = resultado / 100;
	return resultado;	
}

/**
 * Aplicar a clientes exonerados
 * @param cliente
 * @param detail
 * @returns
 */
function getExoneracion(cliente,detail){
    var aplicaExo = false;
    var porcentaje = __valorNumerico(cliente.porcentajeExoneracion );
    if(porcentaje == 0){
        return detail;
    }
    var valorTotal = 0;
    for (var count = 0; count < detail.length; count++) {
        item = detail[count];
        cliente.porcentajeExoneracion = __valorNumerico(cliente.porcentajeExoneracion);
        if(item.montoImpuesto > 0  ){
           if(cliente.porcentajeExoneracion > 0  ){
             item.porcentajeExoneracion = getPorcentajeExoneracion( __valorNumerico(cliente.porcentajeExoneracion), item.impuesto);
             item.fechaEmisionExoneracion = cliente.fechaEmisionExoneracion;
             item.nombreInstitucionExoneracion = cliente.nombreInstitucionExoneracion;
             item.numeroDocumentoExoneracion = cliente.numeroDocumentoExoneracion;
             item.tipoDocumentoExoneracion = cliente.tipoDocumentoExoneracion;
             valorTotal = __valorNumerico(item.subTotal) * __valorNumerico(item.porcentajeExoneracion);
             item.montoExoneracion = valorTotal / 100;
             item.ImpuestoNeto = item.montoImpuesto - item.montoExoneracion;
             item.montoTotalLinea = item.subTotal +  item.ImpuestoNeto;
             detail[count] = item;
           }else{
             item.porcentajeExoneracion = 0;
             item.fechaEmisionExoneracion = null;
             item.nombreInstitucionExoneracion = "";
             item.numeroDocumentoExoneracion = "";
             item.tipoDocumentoExoneracion = "";
             item.montoExoneracion = 0;
             item.montoExoneracion1 = 0;
             item.ImpuestoNeto = __valorNumerico(item.montoImpuesto); 
             item.montoTotalLinea = item.subTotal +  item.ImpuestoNeto;
             detail[count] = item;
            }
        }else{
                item.porcentajeExoneracion = 0;
                item.fechaEmisionExoneracion = null;
                item.nombreInstitucionExoneracion = "";
                item.numeroDocumentoExoneracion = "";
                item.tipoDocumentoExoneracion = "";
                item.montoExoneracion = 0;
                item.montoExoneracion1 = 0;
        }
     }
        
    return detail;
}
/**
* calculacion de los detalle de la factura 
**/
function __ResumenFactura(detail,fact) {
	var objetos = {
			factura:fact,
			cantArticulos:0,
			totalGananciaByProducto:0,
			totalComprobante:0,
			totalDescuentos:0,
			totalImpuesto:0,
			montoExoneracion:0,
			subTotalGeneral:0,
			totalImpuesto:0,
			totalPesoByFactura:0
	};
	
    var totalVenta     = 0;
    var subTotal       = 0;
    var totalDescuentos = 0;
    var totalImpuesto  = 0;
    var totalMercanciasGravadas = 0;
    var totalMercanciasExentas  = 0;
    var totalServGravados       = 0;
    var totalServExentos        = 0;
    var totalGravado            = 0;
    var totalExento             = 0;
    var totalComprobante        = 0;
    var totalventaNeta          = 0;
    var totalGanancia           = 0;
    var tieneMesa = typeof objetos.factura.mesa !== 'undefined'?true:false;
    tieneMesa = objetos.factura.mesa == null?false:true

    objetos.cantArticulos      = 0;
    var montoExoneracion = 0;
    detail.forEach(function(e){
        totalMercanciasGravadas += e.tipoImpuesto.length > 0  && e.unidadMedida !="Sp"?e.montoTotal:0;
        totalMercanciasExentas  += e.tipoImpuesto.length == 0 && e.unidadMedida =="Sp"?e.montoTotal:0;
        totalServGravados       += e.tipoImpuesto.length > 0 && e.unidadMedida =="Sp"?e.montoTotal:0;
        totalServExentos        += e.tipoImpuesto.length == 0 && e.unidadMedida =="Sp"?e.montoTotal:0;
        totalGravado            += e.tipoImpuesto.length > 0 ?e.montoTotal:0;
        totalExento             += e.tipoImpuesto.length == 0 ?e.montoTotal:0;
        totalComprobante        += e.montoTotalLinea;
        subTotal                += e.subTotal >0?e.subTotal:0;
        totalDescuentos          += e.montoDescuento >0?e.montoDescuento:0;
        totalImpuesto           += __valorNumerico(e.montoImpuesto);
        totalVenta              += e.montoTotal;
        totalGanancia           +=__valorNumerico(e.ganancia);
        objetos.cantArticulos   += esEntero(e.cantidad) == true? e.cantidad:1; 
        objetos.montoExoneracion  = objetos.montoExoneracion + __valorNumerico(e.montoExoneracion); 
        objetos.totalPesoByFactura      += __valorNumerico(e.pesoTransporte) * __valorNumerico(e.cantidad);
    });
     
    objetos.totalGananciaByProducto = formatoDecimales(parseFloat(totalGanancia),2);
    objetos.factura.totalMercanciasGravadas = __valorNumerico(totalMercanciasGravadas);
    objetos.factura.totalMercanciasExentas  = __valorNumerico(totalMercanciasExentas);
    objetos.factura.totalServGravados       = __valorNumerico(totalServGravados);
    objetos.factura.totalServExentos        = __valorNumerico(totalServExentos);
    objetos.factura.totalGravado            = __valorNumerico(totalGravado);
    objetos.factura.totalExento             = __valorNumerico(totalExento);
    objetos.factura.totalVenta              = __valorNumerico(totalVenta);
    objetos.factura.totalDescuentos         = __valorNumerico(totalDescuentos);
    objetos.factura.subTotal                = __valorNumerico(subTotal);
    objetos.factura.totalImpuesto           = __valorNumerico(totalImpuesto); 
    objetos.factura.totalVentaNeta          = __valorNumerico(totalVenta-totalDescuentos);
//Se verifica si la mesa tiene impuestos
    var tieneMesa = typeof objetos.factura.mesa !== 'undefined'?true:false;
    tieneMesa = objetos.factura.mesa == null?false:true;
    var tieneImpuestoServiciot = false;
    if(tieneMesa){
      tieneImpuestoServiciot = typeof objetos.factura.mesa.impuestoServicio !== 'undefined'?true:false;  
    }
    if (tieneMesa && tieneImpuestoServiciot){
        if(objetos.factura.mesa.impuestoServicio == true){
        	objetos.factura.totalImpuestoServ = Math.round(__valorNumerico(subTotal * 0.10));
            objetos.factura.totalVentaNeta = Math.round(__valorNumerico(totalVenta-totalDescuentos) + __valorNumerico(objetos.factura.totalImpuestoServ));
            totalComprobante = Math.round(__valorNumerico(totalComprobante) + __valorNumerico(objetos.factura.totalImpuestoServ));
        }
    }    
    objetos.factura.totalComprobante = __valorNumerico(totalComprobante);
    objetos.totalComprobante = totalComprobante;
    objetos.totalDescuentos = totalDescuentos;
    objetos.totalImpuesto = totalImpuesto;
    
     
    var resultado = __valorNumerico(objetos.factura.subTotal) + __valorNumerico(objetos.factura.totalDescuentos);
    objetos.subTotalGeneral = resultado;
    objetos.factura.subTotal =  resultado;
    objetos.totalDescuentos = totalDescuentos;
    var resultadoTotalImpuesto = __valorNumerico(objetos.totalImpuesto);
    objetos.totalImpuesto   = totalImpuesto;
    return objetos;
}
/**
 * Calcula el descuento
 * @param precioUnitario
 * @param cantidad
 * @param porcentajeDesc
 * @param porcentajeGanancia
 * @returns
 */
function getMontoDescuento(precioUnitario,cantidad,porcentajeDesc,porcentajeGanancia){
   
    var totalDescuento =  precioUnitario * cantidad;
    totalDescuento = totalDescuento * porcentajeDesc;
    totalDescuento = totalDescuento /100;
    var resultado = totalDescuento;
    return resultado;
}

function getPorcentaje(temp){
	
	if(temp.porcentajeDesc == 0){
        return 0;
    }
     if(temp.porcentajeDesc > 100){
    	 temp.porcentajeDesc = 100;
    }
    var porcentaje =  temp.porcentajeGanancia;
    if(temp.porcentajeDesc <= temp.porcentajeGanancia){
       porcentaje =  temp.porcentajeDesc;
    }
    if(temp.porcentajeGanancia <=0){
        porcentaje =  temp.porcentajeDesc;
    }
    if(temp.porcentajeDesc ==100){
        porcentaje = 0;
    }
    
    return porcentaje;
}

function ActualizarLineaDEtalle(itemtemp){
	var  resultado = {
			item:itemtemp,
			
	};
	resultado.item.porcentajeDesc = getPorcentaje(resultado.item);
	
    var montoTotal = getMontoTotal(resultado.item.precioUnitario,resultado.item.cantidad);
                                  //factura.js
    var montoDescuento = getMontoDescuento(resultado.item.precioUnitario,resultado.item.cantidad,resultado.item.porcentajeDesc,resultado.item.porcentajeGanancia);
    var subTotal = montoTotal > montoDescuento?montoTotal - montoDescuento: montoDescuento-montoTotal;
    montoImpuesto1 = 0;
    var resultadoMontoImpuesto1 = montoImpuesto1 + subTotal;
    var montoImpuesto = _calcularImpuesto(resultadoMontoImpuesto1,resultado.item.impuesto ==null?0:resultado.item.impuesto);
    var montoTotalLinea = subTotal + montoImpuesto + montoImpuesto1;
    resultado.item.pesoTransporteTotal = __valorNumerico(resultado.item.cantidad) *  __valorNumerico(resultado.item.pesoTransporte);

    resultado.item.montoTotal       = montoTotal;
    resultado.item.montoDescuento   = montoDescuento;
    resultado.item.subTotal         = subTotal;
    resultado.item.montoImpuesto    = montoImpuesto;
    resultado.item.montoImpuesto1   = montoImpuesto1;

    resultado.item.montoTotalLinea  = montoTotalLinea;
    resultado.item.ganancia         = __ObtenerGananciaProductoNuevoIngresado(montoDescuento,resultado.item.precioUnitario,resultado.item.costo ==null?0:__valorNumerico(resultado.item.costo),resultado.item.cantidad);
    resultado.item.montoGanancia    = resultado.item.ganancia;
    return resultado.item;
}



/*--------LocalStorage------------------*/
	function __SetUltimoArticuloIngresado(articulo){
	   localStorage.setItem('ultimoArticulo', JSON.stringify(articulo));
	}

	function __getUltimoArticuloIngresado(){
	    return JSON.parse(localStorage.getItem('ultimoArticulo'));
	}

	function __DeleteUltimoArticuloIngresado(){
	    localStorage.removeItem('ultimoArticulo');
	}

	function __SetUltimoItemIngresado(item){
	   localStorage.setItem('ultimoItem', JSON.stringify(item));
	}

	function __getUltimoItemIngresado(){
	    return JSON.parse(localStorage.getItem('ultimoItem'));
	}

	function __DeleteUltimoItemIngresado(){
	    localStorage.removeItem('ultimoItem');
	}

/****************************************************************************************************************************************************************************************/

function cargarImagenBusca(data64,id){
    var imagenTemporal = data64;

    $(id).attr("width", "200").attr("height", "200")
    $(id).attr("src",imagenTemporal != null ? imagenTemporal:'');

    
}

/**
 * Convertir  a base 64
 * @param element
 * @returns
 */
function encodeImagetoBase64(element) {
    var file = element[0].files;
    var reader = new FileReader();
    reader.onloadend = function() {
        $(".fileB64").val(reader.result);
    }
    reader.readAsDataURL(file[0]);
}
//Permite realizar Decode de un Encode en Base64
function decodeBase64(encode64) {
    var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}
    return Base64.decode(encode64);
}





function minimizarMenu(){
	$( "body" ).removeClass( "skin-blue  sidebar-mini sidebar-collapse" ).addClass( "skin-blue  sidebar-mini sidebar-collapse" );
}


/**
* Tipo de Documento
**/
function __TipoDocumentos(numeroConsecutivo,row){
    switch(row.tipoDoc) {
    case "04":
          return  "Tiq:"+numeroConsecutivo
        break;
    case "01":
        return  "Fact:"+numeroConsecutivo
        break;
    case "02":
        return  "N.Debito:"+numeroConsecutivo
        break;
    case "03":
        return  "N.Credito:"+numeroConsecutivo
        break;
    case "88":
        return  "Proforma:"+row.id
        break;
    case "87":
        return  "TiqueteInterno:"+row.id
        break;
    case "86":
        return  "NC.Interno:"+row.numeroConsecutivo
        break;
    default:
        return  numeroConsecutivo
}
}


/**
*Tipo Cambio del Dolar 
**/
function getTipoCambioDolar(){
	  $.ajax({
	        url: "tipoCambioBancoCentral.do",
	        datatype: "json",
	        global: false,
	        method:"GET",
	        success: function (data) {
	            if (data.status != 200) {
	                if (data.message != null && data.message.length > 0) {
	                	__TipoCambio()
	                }
	            }else{
	                if (data.message != null && data.message.length > 0) {
	                    $.each(data.listaObjetos, function( index, modeloTabla ) {
	                        localStorage.setItem('tipoCambioTotal', __valorNumerico(modeloTabla.dolar.venta.valor));
	                        localStorage.setItem('tipoCambioCompra', __valorNumerico(modeloTabla.dolar.compra.valor));
	                    });
	                }
	            }
	        },
	        error: function (xhr, status) {
	            mensajeErrorServidor(xhr, status);
	            
	        }
	    });
   
}
/**
* Tipo Cambio de moneda
**/
function __TipoCambio(){
    $.ajax({
        url: "MostrarTipoCambioActivoAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    mensajeAdvertencia(data.message);
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                       localStorage.setItem('tipoCambioTotal', __valorNumerico(modeloTabla.dolar.venta.valor));
                       localStorage.setItem('tipoCambioCompra', __valorNumerico(modeloTabla.total));
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            
        }
    });
}

function __getTipoCambioCompra(){
    return JSON.parse(localStorage.getItem('tipoCambioCompra'));
} 

function __getTipoCambioTotal(){
    return JSON.parse(localStorage.getItem('tipoCambioTotal'));
} 


/**
* Tipo codigo del producto/servicio del articulo
**/
function __CombotipoCodigo(){
    var tipoCodigos =[]
    tipoCodigos.push({
        codigo: '01',
        descripcion:$.i18n.prop("articulo.tipo.codigo.vendedor")
    });
    tipoCodigos.push({
        codigo: '04',
        descripcion:$.i18n.prop("articulo.tipo.codigo.uso.interno")
     });
    return tipoCodigos;
}

/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboImpuestos(){
    var impuestos =[]
    impuestos.push({
        codigo: "",
        descripcion:"Exento"
     });

    impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
    impuestos.push({
        codigo: '07',
        descripcion:$.i18n.prop("tipo.impuesto.servicio")
     });
    impuestos.push({
        codigo: '99',
        descripcion:$.i18n.prop("tipo.impuesto.otros")
     });
   
    return impuestos;
}

/**
*  Mostrar listado datatable Categorias Actimpuestos
**/
function __listadoMarcasActivas(callback){
    $.ajax({
         url: "ListarMarcasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
            	callback(result.aaData);
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    });
    callback(null);
}

/**
*  Mostrar listado datatable unidades de medidas activas
**/
function __listadoTipoUnidadesActivas(callback){
    $.ajax({
         url: "ListarTipoUnidadesAjax.do",
        datatype: "json",
        global: false,
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
            	 callback(result.aaData); 
                
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
    callback(null); 
}


/**
 * Tipo de Articulo 0 = Siempre debe facturar mayor a cero
 * @returns
 */
function ___ComboTipoFacturarArticulo(){
	var generico =[]
	generico.push({
        codigo: "0",
        descripcion:"Facturar Mayor Cero"
     });

	generico.push({
        codigo: '1',
        descripcion:"Facturar en Cero"
     });

    return generico;
}
/**
*  Crear el combo base imponible
**/
function __ComboBaseImponibles(){
	var generico =[]
	generico.push({
        codigo: 0,
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
	generico.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
     return generico;
}
/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboContables(){
	var generico =[];
	generico.push({
        codigo: $.i18n.prop("boolean.no"),
        descripcion: $.i18n.prop("boolean.no") 
     });
	generico.push({
        codigo: $.i18n.prop("boolean.si"),
        descripcion:$.i18n.prop("boolean.si")
     });
    return generico;
}
/**
*  Crear el combo de estados
**/
function __ComboComanda(){
	var generico =[]
	generico.push({
        codigo: 0,
        descripcion: "No enviar"
     });

	generico.push({
        codigo: 1,
        descripcion: $.i18n.prop("combo.comanda.cocina.1")
     });
	generico.push({
        codigo:2,
        descripcion:$.i18n.prop("combo.comanda.cocina.2")
     });
    return generico;
}
/**
*  Crear el combo comanda
**/
function __ComboEstados(){
	var generico =[]
	generico.push({
        codigo: $.i18n.prop("combo.estado.Activo"),
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
	generico.push({
        codigo: $.i18n.prop("combo.estado.Inactivo"),
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
    return generico;
}

/**
* Combo para verificar si es contabilizado en el inventario o no
**/
function __ComboImpuestosMaG(){
    var impuestos =[]
    impuestos.push({
        codigo: "",
        descripcion:"Exento"
     });

    impuestos.push({
        codigo: '01',
        descripcion:$.i18n.prop("tipo.impuesto.ventas")
     });
   
    return impuestos;
}



var myControlPago;
//$.toast().reset('all');
setInterval(function() {
	 consultaControlPago()
 }.bind(this),900000)

 
var bloqueoPorControlPago = false; 
 
function __MensajesToasControlPago(){
	
	
	
}

function consultaControlPago(){
	 $.ajax({
         type : "POST",
         global: false,
         dataType : "json",
         url : 'ControlPagoEmpresaAjax.do',
         success : function(data) {
             if (data.status != 200) {
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
            	 if (data.message != null && data.message.length > 0) {
                     $.each(data.listaObjetos, function( index, modeloTabla ) {
                    	 mensajePagos(modeloTabla.mensaje,modeloTabla.totalDolarSTR,modeloTabla.totalColonesSTR,modeloTabla.tipoCambioSTR,modeloTabla.fechaPagoSTR,modeloTabla.fechaLimiteSTR);
                    	 bloqueoPorControlPago = modeloTabla.bloqueo = 1 ? true:false; 
                     })
            	 }    
            	 
                 
             }
     },
         error : function(xhr, status) {
         console.log(status);
         mensajeErrorServidor(xhr, status);
     }
 });
}

function mensajeAdvertencia(mensaje){
	$.toast({
	    heading: 'Advertencia 警告 ',
	    text: mensaje  ,
	    icon: 'error',
	    loader: true,        // Change it to false to disable loader
	    showHideTransition: 'slide',
	    loaderBg: '#9EC600',  // To change the background
	    hideAfter: 1500,   // in milli seconds
	    position: 'top-right',
	    bgColor: 'red',
	    textColor: 'white'

	});
	

}



function mensajeToasExito(mensaje){
	$.toast({
	    heading: 'Transaccion Exitomente realizada 成功交易 ',
	    text: mensaje  ,
	    icon: 'success',
	    loader: true,        // Change it to false to disable loader
	    showHideTransition: 'slide',
	    loaderBg: '#9EC600',  // To change the background
	    hideAfter: 1500,   // in milli seconds
	    position: 'top-right',
	    bgColor: '#4cae4c',
	    textColor: 'white'

	});
	

}



function mensajePagos(mensaje,dolares,colones,cambio,fechaPago,fechaLimite){
	$.toast({
	    heading: 'Pendiente Pago ',
	    text: mensaje  ,
	    icon: 'error',
	    loader: true,        // Change it to false to disable loader
	    showHideTransition: 'slide',
	    loaderBg: '#9EC600',  // To change the background
	    hideAfter: 300000,   // in milli seconds
	    position: 'bottom-right',
	    bgColor: 'red',
	    textColor: 'white'
	    
	})
	
}


function __informacionData_formato_cliente(){
    var informacion_tabla_clientes = [	
							        {"bSortable" : false, "bSearchable" : false, 'data' : 'id',"autoWidth" : true,"name" : "id",
								            "render":function(id,type, row){
									            return __Opcionesclientes(id,type,row);
								                }	 
							            },
                                        {'data' : 'cedula'           ,"name":"cedula"            ,"title" : $.i18n.prop("cliente.cedula")            ,"autoWidth":false,
        									"render":function(cedula,type, row){
        										return stringVacio(cedula)?cedula:row.identificacionExtranjero;
        									}
                                        
                                        },
                                        
                                        {'data' : 'nombreCompleto'   ,"name":"nombreCompleto"    ,"title" : $.i18n.prop("cliente.nombreCompleto")    ,"autoWidth":false,
									            "render":function(id,type, row){
										            return __TamanoNombre(row.nombreCompleto);
	 							                }	 
                                        },
                                        {'data' : 'correoElectronico',"name":"correoElectronico" ,"title" : $.i18n.prop("cliente.correoElectronico") ,"autoWidth":false,
    								            "render":function(id,type, row){
    									            return __TamanoNombre(row.correoElectronico);
	 							                }	 
                                        },
                                        {'data' : 'nombreComercial'  ,"name":"nombreComercial"    ,"title" : $.i18n.prop("cliente.nombreComercial")    ,"autoWidth":false,
    								            "render":function(id,type, row){
    									            return __TamanoNombre(row.nombreComercial);
	 							                }	 
                                        }


                                        ];                              
   
   return informacion_tabla_clientes
}

function financial(x) {
	if(x == null || typeof x == "undefined"  ){
        return 0;
    } 	
  return Number.parseFloat(x).toFixed(2);
}

/**
 * Obtener la cedula de extranjero o fisica o emprsa
 * @param mensaje
 * @returns
 */
function getCedulaOrIdentificacionExtranjero(cedula,identificacionExtranjero){
	var resultado = "";
	if(stringVacio(cedula) == false){
		return identificacionExtranjero;
	}else{
		return cedula
	}
	
	
	
	return resultado;
}

function mensajeExito(mensaje){
	 swal({
         type: 'success',
         title:mensaje,
         showConfirmButton: false,
         timer: 1500
     });
}

function mensajeErrorTiempo(mensaje){
	 swal({
        type: 'error',
        title:mensaje,
        showConfirmButton: false,
        timer: 1500
    });
}

/**
* Opciones del modal de clientes
*/
function __Opcionesclientes(){
  var agregar  = '<a href="#"  title="Seleccionar Cliente" class="btn btnAgregar btn-success form-control" title="Seleccione el cliente de la factura" role="button"> <i class="glyphicon glyphicon-plus"></i></a>';
  return  agregar;

}

function __TamanoNombre(nombre){
    var nombreCliente  = nombre  == null?"":nombre
    if(nombreCliente == null){
        return ""
    }
    return nombreCliente.length > 26 ?nombreCliente.substring(0,26) + "..." :nombreCliente;
}

function stringVacio(object){
	if(object == null || typeof object == "undefined"  ){
        return false;
    }
	return object.length > 0 ? true: false;
}
/**
 * Es Grabado por servicio
 */
function esGravadoServicio(unidad ){
	if(unidad == null || typeof unidad == "undefined"  ){
        valorUnidad = "";
    }else{
    	valorUnidad = unidad;
    }
	if(valorUnidad == 'Sp' || valorUnidad == 'Spe'  || valorUnidad == 'Os' || valorUnidad == 'St'){
		return true;
	}
	return false;
}

/**
 * Es exento
 */
function esExento(tipoImpuesto){
	if(tipoImpuesto == null || typeof tipoImpuesto == "undefined"  ){
       return true;
    }
	return tipoImpuesto.length == 0 ? true:false
	
}
/**
 * Valida si esta e la cocina 1
 * @param object
 * @returns
 */
function esCocina1(object){
	if(object == null || typeof object == "undefined"  ){
        return false;
    }
	return __valorNumerico(object) == 1 ? true: false;
}
/**
 * Valida si es la cocina 2
 * @param object
 * @returns
 */
function esCocina2(object){
	if(object == null || typeof object == "undefined"  ){
        return false;
    }
	return __valorNumerico(object) == 2 ? true: false;
}


function verificarClienteFrecuente(clienteFrecuente){
    if(clienteFrecuente == null || typeof clienteFrecuente == "undefined"  ){
        return true;
    }
    if(typeof clienteFrecuente.id == "undefined"  ){
        return true;
    }
    if(typeof clienteFrecuente.nombreCompleto == "undefined"  ){
        return true;
    }

    if(clienteFrecuente.id == 0 ){
        return true
    }
    if(clienteFrecuente.nombreCompleto.indexOf("CLIENTE_FRECUENTE") != -1){
        return true;        
    }
    if(clienteFrecuente.cedula.indexOf("999999999999") != -1){
       return true; 
    }
    return false;
}

function verificarSiClienteFrecuente(clienteFrecuente){
    if(clienteFrecuente == null || typeof clienteFrecuente == "undefined"  ){
       return true;
   }
   if(typeof clienteFrecuente.id == "undefined"  ){
       return true;
   }
   if(typeof clienteFrecuente.nombreCompleto == "undefined"  ){
       return true;
   }

   if(clienteFrecuente.id == 0 ){
       return true
   }

   if (typeof clienteFrecuente == "undefined"  ){
       return true
   }
   if(clienteFrecuente.id == 0 ){
       return true
   }
   if(clienteFrecuente.nombreCompleto.indexOf("CLIENTE_FRECUENTE") != -1 || clienteFrecuente.nombreCompleto.indexOf("CLIENTE_CREDITO") != -1){
       return true
   }
   if(clienteFrecuente.cedula.indexOf("999999999999") != -1 || clienteFrecuente.cedula.indexOf("888888888888") != -1){
       return true
   }
   return false;
}


//traducciones del table
	var idioma_espanol = 
			{
			    "sProcessing":     "Procesando...",
			    "sLengthMenu" : 'Mostrar <select>' + 
				 '<option value="5">5</option>'
			    + '<option value="10">10</option>'
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

	function estados(estado,row){
		if(estado == "Firmado XML"){
			return '<span class="aceptadoFirma">Firmado XML</span>';
		}
		if(estado == "Aceptada"){
			return '<span class="aceptadoEstado">Aceptada</span>';
		}
		if(estado == "Facturada"){
			return '<span class="aceptadoFirma">Facturada</span>';
		}
		if(estado == "Rechazada"){
			return '<span class="aceptadoRechazado">Rechazado</span>';
		}
		if(estado == "Enviada"){
			return '<span class="aceptadoEnviado">Enviado</span>';
		}
		if(estado == "Anulada"){
			return '<span class="badge badge-warning">Anulado</span>';
		}
		return estado;

	}
	
	function estadosActivoInactivo(estado,row){
		
		if(estado == "Activo"){
			return '<span class="aceptadoEstado">Activo</span>';
		}
		if(estado == "Inactivo"){
			return '<span class="aceptadoRechazado">Inactivo</span>';
		}
		return estado;

	}
	
	/***
 * Funcion llamaa de venta nueva post  y restaurante
 * 
 * **/
	function mostrarMensajeCreacionConsecutivo(data){
	    var mensaje = ""  
	    if(data.tipoDoc == "01" && data.estado == 2 ){
	        mensaje  = "Factura :" + data.numeroConsecutivo
	    }else if(data.tipoDoc == "04" && data.estado == 2){
	        mensaje  = "Tiquete :" + data.numeroConsecutivo
	    }else  if(data.tipoDoc == "88" && data.estado == 3){
	        mensaje  = "Proforma:" + data.consecutivoProforma
	    }else {
	        mensaje = "En Espera:" + + data.id
	    }
	    return mensaje;
	}
	
	/***
	 * Funcion llamaa de venta nueva post  y restaurante
	 * 
	 * **/
		function mostrarMensajeCreacionConsecutivoRestaurante(data){
		    var mensaje = ""  
		    if(data.tipoDoc == "01" && data.estado == 2 ){
		        mensaje  = "Factura :" + data.numeroConsecutivo
		    }else if(data.tipoDoc == "04" && data.estado == 2){
		        mensaje  = "Tiquete :" + data.numeroConsecutivo
		    }else  if(data.tipoDoc == "88" && data.estado == 3){
		        mensaje  = "Proforma:" + data.consecutivoProforma
		    }else {
		        mensaje = "Proforma gravada exitosamente en la mesa numero:" + + data.id
		    }
		    return mensaje;
		}	
	
function cambioColonesADolar(valor,venta){
  var resultado = parseFloat(valor)/parseFloat(venta);
  return resultado;
	
}



	
	
/** Funciones en ventas nueva , venta post , restaurante comunes **/
	/**
	* Monto de Total
	**/
	function getMontoTotal(precioUnitario,cantidad){
	    var resultado = parseFloat(precioUnitario) * parseFloat(cantidad)
	    return resultado
	}
	/**
	* Obtiene el precio unitario sin descuento sin impuesto
	**/
	function getPrecioUnitario(precio ,impuesto){
	   var porcentajeImpuesto = 0
	   var resultado  = 0
	   if(impuesto > 0){
	      porcentajeImpuesto = impuesto / 100
	      porcentajeImpuesto =  porcentajeImpuesto + 1
	      resultado  =  precio  / porcentajeImpuesto
	   }else{
	       resultado  =  precio
	   }
	   return resultado     
	}
	/**
	 * calculo del impuesto iva
	 * */
	function _calcularImpuesto(precio,iva){
	    if(iva == 0){
	        return 0;
	    }
	    var impuesto = iva ;
	    var resultado = precio * impuesto;
		resultado = resultado / 100;
	    return resultado;
	}
	
	/**
	* Monto a pagar en la linea el cliente
	**/
	function getMontoTotalLinea(subTotal,totalImpuesto){
	  return subTotal == 0?0:subTotal + totalImpuesto
	}
	/**
	*  Obtener el subtotal sin el impuesto
	**/
	function getSubTotal(precio,cantidad){
	    var valor = __valorNumerico(precio) * __valorNumerico(cantidad)
	    return valor
	}
	/**
	* calcular el descuento
	**/
	function getTotalDescuento(precio,cantidad,porcentajeDesc){
	    var porcentaje = __valorNumerico(porcentajeDesc)/100
	    var valor =  0
	    if(porcentajeDesc == 100){
	       valor = 0
	    }else{
	       var valor =  __valorNumerico(precio) * porcentaje   
	    }
	    var valor =  __valorNumerico(precio) * porcentaje
	    return valor * cantidad
	}

/** Fin **/
	
	
	
	/**
	 * autor : Leonel Hernandez Chaverri
	*Funcion para mostrar la ganancia en la pantalla
	*Venta nueva
	*Venta Post 
	*Venta Restaurante
	**/
	function __ObtenerGananciaProductoNuevoIngresado(montoDescuento,precioUnitario,costo,cantidad){
	
		var precioUnitarioTemp = precioUnitario * cantidad;
		var costoTemp = costo * cantidad;
		// si el costo es mayor al precio unitario se deja en cero debido a que se trata de productos de uso internos
	    if(costoTemp > precioUnitarioTemp){
	    	costoTemp = 0;
	    }
	   
	    var totalGanancia = precioUnitarioTemp - costoTemp;
	    if(redondeoDecimales(montoDescuento,2) == redondeoDecimales(totalGanancia,2)){
	        return 0;
	    }
//	    totalGanancia = totalGanancia * cantidad;
	    return  parseFloat(redondeoDecimales(totalGanancia,2) -  redondeoDecimales(montoDescuento,2));
	   
	 
	}
	
	
	
	
	
	
	function redondeoDecimales(numero,decimales)
	{
		var original=parseFloat(numero);
		return original.toFixed(decimales);
	}

function formatoDecimales(amount, decimals){
	     positivoNegativo = amount > 0?"":"-"
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

		return positivoNegativo + amount_parts.join('.');
	}


/**
*  retorna el valor numerico o cero sino es numerico
**/
function __valorNumerico(valor){
	if (typeof valor == 'undefined') {
	    return 0;
	}

    return isNumber(valor)?parseFloat(valor):0 ;
}

function __valorFloat(valor){
	if (typeof valor == 'undefined') {
	    return 0.0;
	}
    return isNumber(valor)?parseFloat(valor):0.0 ;
}
function __valorEnterot(valor){
	if (typeof valor == 'undefined') {
	    return 0;
	}
    return isNumber(valor)?parseInt(valor):0 ;
}

function __valorString(valor){
	if(isNumber(valor) == false){
		if (typeof valor == 'undefined') {
		    return "";
		}
	}
    return valor ;
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
		window.location.href = "403?exceptionMessage="
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
                            mensajeToasExito(data.message);
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
	                                
	                                mensajeToasExito(data.message);
	                                
	                                	
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


function mensajeEstatico(mensaje){
	swal({
          title: '',
          text: mensaje,
          type: 'error',
          showCancelButton: false,
          confirmButtonText: 'Aceptar',
               	  
        })
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
                           
                            mensajeToasExito(data.message);
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
	                                
	                                mensajeToasExito(data.message);
	                                
                                    
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
        "language": idioma_espanol,
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
	$(nombreTabla).dataTable().fnClearTable();
	$(nombreTabla).DataTable().destroy();
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
	var table = $(nombreTabla).DataTable();
 	table
    .clear()
    .draw();

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
        window.location.href = "403?exceptionMessage="
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

function formatoFechaF(fecha) {
    return fecha == null?"":moment(fecha).format('YYYY-MM-DD');
}
function formatoFechaYYYY(fecha) {
    return fecha == null?"":moment(fecha).format('YYYY-MM-DD');
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


/**
 * Functiones de Articulo
 * **/

function getMontoTarifa(tipoImpuesto,codigoTarifa,array) {
	  return array.filter(
	    function(data) {
	      return data.tipoImpuesto == tipoImpuesto && data.tarifaIVAI.codigoTarifa == codigoTarifa?data.monto:0
	    }
	  );
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

/**
* autor : Leonel Hernandez Chaverri
* Fecha : 23-06-17
* obtener la ganancia del precio en decimal
**/
function _porcentajeGanancia(costo,impuesto,impuesto1,precioVenta) {
	  var porcentajeGanancia = 0;
	  var precioSinImpuesto  = 0;
	  if(costo == 0){
	      return 100
	  } 
	  if(precioVenta == 0){
	    return 0;
	  }
	  if(costo == precioVenta){
	      return 0
	  }
	  var resultado = 0
	  if(impuesto == 0 || impuesto == null ){
	      if(costo == precioVenta){
	          resultado = 0
	      }else{
	        resultado =  costo / precioVenta 
	        resultado = 1- resultado  
	      }
	    porcentajeGanancia  = resultado;
	  }else{ 
	    if(costo == precioVenta){
	       porcentajeGanancia  = 0; 
	    }else{
	    	var resultadoImpuesto = impuesto;
	        precioSinImpuesto = __valorNumerico(redondeoDecimales(precioVenta/((resultadoImpuesto/100) + 1),5));
	    	resultadoImpuesto = impuesto1;
	        precioSinImpuesto = __valorNumerico(redondeoDecimales(precioSinImpuesto/((resultadoImpuesto/100) + 1),5));
	        if(precioSinImpuesto < costo){
	        	return 0
	        }

	        if(precioSinImpuesto ==  costo){
	            resultado = 0
	        }else{
	        resultado =   costo / precioSinImpuesto 
	        resultado = 1-resultado  
	        }
	        porcentajeGanancia  = resultado;

	    } 
	  }
	  return __valorNumerico(porcentajeGanancia * 100);
	}	


function esEntero(numero){
    if (isNaN(numero)){
        return false
    } else {
        // es entero
        if (numero % 1 == 0) {
            return true
            
        } else {
            return false
        }
    }
}

/**
* autor : Leonel Hernandez Chaverri
* Fecha : 23-06-17
* obtener Precio Publico con ganancia
**/
function _PrecioPublicoConGanancia(costo,impuesto,impuesto1,ganancia){
	 
	  if(costo == 0){
	      return 0;
	  } 
	  
	  var porcentajeGanancia = ganancia > 0?ganancia/100:0;
	  if(ganancia > 0){
	    porcentajeGanancia = 1 - porcentajeGanancia
	  }
	  
	  var totalImpuesto1 = __valorNumerico(impuesto1);
	  totalImpuesto1 = totalImpuesto1/100;
	  totalImpuesto1 = totalImpuesto1 == 0 ?0:totalImpuesto1 + 1
	  // aplicando el 13
	  var totalImpuesto = __valorNumerico(impuesto)+__valorNumerico(impuesto1);
	  totalImpuesto = totalImpuesto/100;
	  totalImpuesto = totalImpuesto == 0 ?0:totalImpuesto + 1
	  var precio  = 0
	  if(ganancia > 0){
	    if(porcentajeGanancia < 1){
	        precio = costo / porcentajeGanancia
	    }else{
	        if(porcentajeGanancia == 1){
	            precio = costo * 2 
	        }else{
	            precio = costo * porcentajeGanancia
	        }
	    }
	  }
	  
	  precio = totalImpuesto1 >0? costo * totalImpuesto1:precio;
	  precio = totalImpuesto >0? costo * totalImpuesto:precio;
	  return __valorNumerico(redondeoDecimales(precio,0));
	}

function aplicarRedondeo(){
    return 8
}

/**
* autor : Leonel Hernandez Chaverri
* Fecha : 23-06-17
* obtener Precio General Ganancia Margen bruto
**/
function _ObtenerPrecio(costo,impuesto,impuesto1,ganancia){
	 
	  if(costo == 0){
	      return 0;
	  } 
	  if(ganancia == 0){
	      return 0;
	  } 
	  
	  var porcentajeGanancia = ganancia > 0?ganancia/100:0;
	  if(porcentajeGanancia < 1  ){
	    porcentajeGanancia = 1 - porcentajeGanancia
	  }
	  
	  var totalImpuesto1 = __valorNumerico(impuesto1);
	  totalImpuesto1 = totalImpuesto1/100;
	  totalImpuesto1 = totalImpuesto1 == 0 ?0:totalImpuesto1 + 1
	  // aplicando el 13
	  var totalImpuesto = __valorNumerico(impuesto)+__valorNumerico(impuesto1);
	  totalImpuesto = totalImpuesto/100;
	  totalImpuesto = totalImpuesto == 0 ?0:totalImpuesto + 1
	  var precio  = 0
	  if(ganancia > 0){
	    if(porcentajeGanancia < 1){
	        precio = costo / porcentajeGanancia
	    }else{
	        if(porcentajeGanancia == 1){
	            precio = costo * 2 
	        }else{
	            precio = costo * porcentajeGanancia
	        }
	    }
	  }
	  
	  precio = totalImpuesto1 >0? precio * totalImpuesto1:precio;
	  precio = totalImpuesto >0? precio	 * totalImpuesto:precio;
	  return __valorNumerico(redondeoDecimales(precio,0));
	}
/**
 * Calcula la ganancia del precio 
 * @param impuesto
 * @param costo
 * @param precioMayorista
 * @returns
 */
function __CalcularGanancia(impuesto,costo,precio){
    var impuesto  =  __valorNumerico(impuesto);
    var costo     =  __valorNumerico(costo);
    var precioMayorista    =  __valorNumerico(precio);
    return  precio >0?_porcentajeGanancia(costo,impuesto,0,precioMayorista):100;
}
