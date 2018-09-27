package com.emprendesoftcr.Utils;

import java.util.ResourceBundle;

public class Constantes {

	public static final ResourceBundle	RESOURCE_BUNDLE																			= ResourceBundle.getBundle("factura");

	// Tipo de codigos de productos
	public static final String					TIPO_CODIGO_ARTICULO_POR_SERVICIO										= "07";
	public static final String					TIPO_CODIGO_ARTICULO_USO_INTERNO										= "04";

	// Semaforo
	public static final Integer					BLOQUES_DOCUMENTOS_A_PROCESAR												= 200;
	// 48 horas cada 5 minutos
	public static final Integer					MAXIMO_REINTENTOS_ACEPTACION												= 60;
	public static final String					MAXIMO_REINTENTOS_ACEPTACION_STR												= "Tope de intentos de aceptacion,no envio respuesta";

	public static final Integer					SEMAFORO_ESTADO_REINTENTOS													= 3;
	public static final Integer					SEMAFORO_ESTADO_FIRMADO															= 1;
	public static final Integer					SEMAFORO_ESTADO_ENVIO																= 2;
	public static final Integer					SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS								= 3;
	public static final Integer					SEMAFORO_ESTADO_ENVIAR_CORREOS											= 4;

	public static final String					IDP_URI_PRODUCCION																	= "https://idp.comprobanteselectronicos.go.cr/auth/realms/rut/protocol/openid-connect";
	public static final String					IDP_URI_DOCUMENTOS_PRODUCCION												= "https://api.comprobanteselectronicos.go.cr/recepcion/v1/recepcion";
	public static final String					IDP_CLIENT_ID_PRODUCCION														= "api-prod";

	// Consecutivo inicial de las empresa
	public static final Integer					CONSECUTIVO_INICIAL_FACTURA													= 0000000001;
	public static final String					CASA_MATRIZ_INICIAL_FACTURA													= "001";
	public static final String					TERMINAL_INICIAL_FACTURA														= "00001";

	public static final String					DOCXMLS_FACTURA																			= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica";
	public static final String					DOCXMLS_NOTA_DEBITO																	= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/notaDebitoElectronica";
	public static final String					DOCXMLS_NOTA_CREDITO																= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/notaCreditoElectronica";
	public static final String					DOCXMLS_RESPUESTA_HACIENDA													= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/mensajeHacienda";
	public static final String					DOCXMLS_RECEPCION_FACTURA														= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/mensajeReceptor";

	public static final String					DOCXMLS_TIQUETE																			= "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/tiqueteElectronico";

	public static final String					PRIVATE_KEY																					= "privateKey";
	public static final String					CERTIFICATE																					= "certificate";
	public static final String					X509_SERIAL_NUMBER																	= "x509SerialNumber";
	public static final String					X509_ISSUER_NAME																		= "x509IssuerName";
	public static final String					CERT_HASH																						= "certHash";

	// Formatos electronicos
	public static final String					FORMATO_PROVINCIA																		= "0";
	public static final String					FORMATO_CANTON																			= "00";
	public static final String					FORMATO_DISTRITO																		= "00";
	public static final String					FORMATO_BARRIO																			= "00";
	public static final String					FORMATO_TELEFONO																		= "00000000";
	public static final String					FORMATO_CODIGO_PAIS																	= "000";
	public static final String					FORMATO_MEDIOPAGO																		= "00";
	public static final String					FORMATO_PLAZO_CREDITO																= "00";
	public static final String					CODIGO_PRODUCTO_VENDEDOR														= "01";
	public static final Integer					PLAZO_CREDITO																				= 15;
	public static final String					OTROS_TEXTOS																				= "Obs";
	public static final String					VENTAS_PRODUCTOS																		= "Ventas de productos";
	public static final String					MEDIO_PAGO_EFECTIVO																	= "01";
	public static final String					MEDIO_PAGO_TARJETA																	= "02";
	public static final String					MEDIO_PAGO_BANCO																		= "04";

	public static final String					NUMERO_RESOLUCION																		= "DGT-R-48-2016";
	public static final String					FECHA_RESOLUCION																		= "07-10-2016 00:00:00";

	public static final String					FORMATO_NATURALEZA_DESCUENTO												= "Descuento Comercial";

	// Cliente /VENDEDOR Frecuente de una empresa
	public static final String					NOMBRE_CLIENTE_FRECUENTE														= "CLIENTE_FRECUENTE";
	public static final String					CEDULA_CLIENTE_FRECUENTE														= "999999999999";
	public static final String					CORREO_CLIENTE_FRECUENTE														= "CLIENTE_FRECUENTE@FRECUENTE.COM";
	public static final String					NOMBRE_VENDEDOR_FRECUENTE														= "VENDEDOR_FRECUENTE";
	public static final String					CEDULA_VENDEDOR_FRECUENTE														= "99999999999999";
	public static final String					CORREO_VENDEDOR_FRECUENTE														= "VENDEDOR_FRECUENTE@FRECUENTE.COM";
	public static final String					LLAVES_CRIPTOGRAFICAS																= "/home/jose/Escritorio/Proyecto Factura Electronica/";

	// Hacienda Estados
	public static final Integer					HACIENDA_ESTADO_PENDIENTE_FIRMAR										= 1;
	public static final Integer					HACIENDA_ESTADO_FIRMARDO_XML												= 2;
	public static final Integer					HACIENDA_ESTADO_ENVIADO_HACIENDA										= 3;
	public static final Integer					HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR							= 4;
	public static final Integer					HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS		= 5;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_HACIENDA										= 6;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_RECHAZADO									= 7;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_PARCIAL										= 8;
	public static final Integer					HACIENDA_ESTADO_ANULADA															= 9;
	public static final Integer					HACIENDA_ESTADO_ERROR																= 10;
	public static final String					HACIENDA_ESTADO_ENVIADO_HACIENDA_str								= "Documento eviado a hacienda.";
	public static final String					HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR								= "OK";
	public static final String					HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR							= "ERROR";
	public static final String					HACIENDA_ESTADO_ACEPTADO_RECIBIDO										= "recibido";
	public static final Integer					HACIENDA_ESTADO_ACEPTACION_HACIENDA_TOPE_REINTENTOS	= 8;
	// Hacienda
	public static final Integer					HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE								= 1;
	public static final Integer					HACIENDA_NOTIFICAR_CLIENTE_ENVIADO									= 2;

	public static final String					ESTADO_HACIENDA_ACEPTADO														= "1";
	public static final String					ESTADO_HACIENDA_ACEPTADO_PARCIAL										= "2";
	public static final String					ESTADO_HACIENDA_RECHAZADO														= "3";

	// Vendedor Frecuente de una empresa

	// Codigo de moneda
	public static final String					CODIGO_MONEDA_COSTA_RICA														= "CRC";
	public static final Double					CODIGO_MONEDA_COSTA_RICA_CAMBIO											= 1.000d;
	public static final String					CODIGO_MONEDA_DOLAR																	= "USD";

	// SP del sistema
	public static final String					SP_ELIMINAR_DETALLES_COMPRAS												= "sp_eliminarDetallesCompra";
	public static final String					SP_ELIMINAR_DETALLES_COMPRAS_ID_COMPRA_PARAM				= "idCompra_param";

	public static final String					SP_ELIMINAR_DETALLES_FACTURA												= "sp_eliminarDetallesFactura";
	public static final String					SP_ELIMINAR_DETALLES_FACTURA_ID_FACTURA_PARAM				= "idFactura_param";

	// Kardex
	public static final String					OBSERVACION_INICIAL_INVENTARIO_NUEVO								= "Ingreso inicial al inventario";
	public static final String					CONSECUTIVO_INICIAL_INVENTARIO_NUEVO								= "99999";
	public static final String					KARDEX_TIPO_ENTRADA																	= "Entrada";
	public static final String					KARDEX_TIPO_SALIDA																	= "Salida";
	public static final String					MOTIVO_INICIAL_INVENTARIO_NUEVO											= "Nuevo inventario a la sucursal";
	public static final String					MOTIVO_INGRESO_INVENTARIO_COMPRA										= "Ingreso del proveedor:";
	public static final String					MOTIVO_INGRESO_INVENTARIO_NOTA_CREDITO							= "Ingreso por nota de credito:";
	public static final String					MOTIVO_SALIDA_INVENTARIO_VENTA											= "Salida por venta:";
	// Contabiliza el inventario
	public static final String					CONTABLE_SI																					= "Si";
	public static final String					CONTABLE_NO																					= "No";
	public static final Double					INVENTARIO_MINIMO																		= 5d;
	public static final Double					INVENTARIO_MAXIMO																		= 10d;
	// Cuenta por cobrar
	public static final String					CUENTA_POR_COBRAR_ESTADO_PENDIENTE									= "Pendiente";
	public static final String					CUENTA_POR_COBRAR_DESCRIPCION_AUTOMATICO						= "Realizada por ventas";
	public static final String					CUENTA_POR_COBRAR_NOTA_AUTOMATICO										= "Realizada por ventas";
	public static final String					CUENTA_POR_COBRAR_ESTADO_CERRADO										= "Cerrada";
	public static final String					CUENTA_POR_COBRAR_ESTADO_ANULADA										= "Anulada";
	// Abono
	public static final String					ABONO_ESTADO_PAGADO																	= "Pagado";
	public static final String					ABONO_ESTADO_ANULADO																= "Anulado";

	// Tipo de cuenta por Cobrar
	public static final String					CUENTA_POR_COBRAR_TIPO_Automatica										= "Automatica";
	public static final String					CUENTA_POR_COBRAR_TIPO_MANUAL												= "Manual";
	// Provincias
	public static final String					PROVINCIA_SAN_JOSE																	= "1";
	public static final String					PROVINCIA_ALAJUELA																	= "2";
	public static final String					PROVINCIA_CARTAGO																		= "3";
	public static final String					PROVINCIA_HEREDIA																		= "4";
	public static final String					PROVINCIA_GUANACASTE																= "5";
	public static final String					PROVINCIA_PUNTARENAS																= "6";
	public static final String					PROVINCIA_LIMON																			= "7";

	public static final String					PROVINCIA_SAN_JOSE_STR															= "San Jose";
	public static final String					PROVINCIA_ALAJUELA_STR															= "Alajuela";
	public static final String					PROVINCIA_CARTAGO_STR																= "Cartago";
	public static final String					PROVINCIA_HEREDIA_STR																= "Heredia";
	public static final String					PROVINCIA_GUANACASTE_STR														= "Guanacaste";
	public static final String					PROVINCIA_PUNTARENAS_STR														= "Puntarenas";
	public static final String					PROVINCIA_LIMON_STR																	= "Limon";

	// Estados
	public static final String					ESTADO_ACTIVO																				= "Activo";
	public static final String					ESTADO_INACTIVO																			= "Inactivo";
	//
	public static final String					EMPTY																								= "";
	public static final String					BLANK																								= " ";
	public static final Integer					ZEROS																								= 0;
	public static final Long						ZEROS_LONG																					= 0l;
	public static final Float						ZEROS_FLOAT																					= 0F;
	public static final Double					ZEROS_DOUBLE																				= 0D;
	public static final String					SEMI_COLON																					= ";";
	public static final String					PUNTO																								= ".";

	// Mensajes de Error Genericos
	public static final String					KEY_ERROR_LEGACY																		= "error.legacyException";
	public static final String					KEY_LONGITUD_INCORRECTA															= "error.longitud.incorrecta";
	public static final String					KEY_REQUERIDO																				= "error.campo.requerido";
	public static final String					EMAIL_INVALID																				= "error.correo.formatoErroneo";
	public static final String					KEY_NO_NUMERICO																			= "error.no.numerico";
	public static final String					KEY_YA_REGISTRADO																		= "error.campo.ya.registrado";

	// Mensajes de Error Especificos
	public static final String					KEY_CEDULA_JURIDICA_INVALIDA												= "error.cedula.juridica.invalida";
	public static final String					KEY_CEDULA_FISICA_INVALIDA													= "error.cedula.fisica.invalida";
	public static final String					KEY_CEDULA_OTRA_INVALIDA														= "error.cedula.otra.invalida";
	public static final String					KEY_SERVICIO_NO_EXISTE															= "error.servicio.no.existe";
	public static final String					KEY_CORREO_ERRONEO																	= "error.correo.erroneo";

	// Otros
	public static final String					KEY_PERFIL_RANGO_INICIAL_INVALIDO										= "error.campo.perfil.rangoInicial.invalido";
	public static final String					KEY_PERFIL_RANGO_INICIAL_CONTENIDO									= "error.campo.perfil.rangoInicial.contenido";
	public static final String					KEY_PERFIL_RANGO_FINAL_CONTENIDO										= "error.campo.perfil.rangoFinal.contenido";

	// Validator
	public static final String					MENSAJE_LONGITUD_INCORRECTA_GENERICO								= "public static final Longitud Incorrecta";
	public static final String					PATRON_EMAIL																				= "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$";
	public static final Integer					LONGITUD_TELEFONO																		= 8;
	public static final Integer					DATO_ERRONEO																				= -1;
	public static final String					DOMIMIO_CORREO_ELECTRONICO_ICE											= "ice.co.cr";

	public static final String					PATRON_CEDULA_EXTRANJEROS_RESIDENCIA								= "^[cC]\\d{1,17}$";
	public static final String					PATRON_CEDULA_EXTRANJEROS_PASAPORTE									= "^[pP]\\d{1,17}$";
	public static final String					PATRON_CEDULA_FISICA_NACIONALES											= "^[1-7]\\d{8}$";
	public static final String					PATRON_CEDULA_FISICA_NACIONALES_TODOS								= "^[1-9]\\d{8}$";
	public static final String					PATRON_CEDULA_FISICA_OTROS													= "^[89]\\d{8}$";
	public static final String					PATRON_CEDULA_JURIDICA_AUTONOMA											= "^4\\d{9}$";
	public static final String					PATRON_CEDULA_JURIDICA_GOBIERNO											= "^2\\d{9}$";
	public static final String					PATRON_CEDULA_JURIDICA_PERSONERIA										= "^3\\d{9}$";
	public static final String					PATRON_CEDULA_JURIDICA_TODAS												= "^[2-4]\\d{9}$";
	public static final String					PATRON_TELEFONOS																		= "^[8]\\d{7}$";
	public static final String					PATRON_TELEFONOS_ICE																= "^[1-9]\\d{7}$";
	public static final String					PATRON_TELEFONOS_ICE2																= "^[1-9]\\d{7}$";
	public static final String					PATRON_NOMBRES_USUARIO															= "\\w{5,}";
	public static final String					PATRON_NOMBRE																				= "^[a-zA-Z][a-zA-Z\\-' ]*[a-zA-Z ]$";

	// Roles de usuario
	public static final String					ROL_ADMINISTRADOR_SISTEMA														= "Administrador_Sistema";
	// Usuario
	public static final String					WEB_SESSION_USUARIO																	= "usuario";
	public static final Long						ID_USUARIO_SISTEMA																	= -1L;

	// Errores en el Broker
	public static final String					ERROR_POR_COMUNICACION															= "2033";
	public static final String					MQ_NO_REASON_CODE																		= "0000";

	public static final String					DATE_FORMAT1																				= "dd/MM/yyyy hh:mm:ss";
	public static final String					DATE_1900																						= "01/01/1990 00:00:00.0";
	public static final String					DATE_FORMAT2																				= "dd/MM/yyyy";
	public static final String					DATE_FORMAT3																				= "dd-MM-yyyy";
	public static final String					DATE_FORMAT5																				= "yyyy-MM-dd 00:00:00";
	public static final String					DATE_FORMAT6																				= "yyyy-MM-dd";
	public static final String					DATE_FORMAT4																				= "yyyyMMdd";
	public static final String					DATE_FORMAT7																				= "yyyy-MM-dd";

	public static final String					DATE_FORMAT_TIME																		= "HH:mm:ss";
	public static final String					DATE_FORMAT_TIME2																		= "HH:mm";
	public static final String					DATE_FORMAT_MIN																			= "00/00/0000 ";

	// Tipo de cedula
	public static final String					TIPO_CEDULA_FISICA																	= "01";
	public static final String					TIPO_CEDULA_JURIDICA																= "02";
	public static final String					TIPO_CEDULA_DIMEX																		= "03";
	public static final String					TIPO_CEDULA_NITE																		= "04";

	public static final String					TIPO_CEDULA_FISICA_STR															= "tipo.cedula.fisica";
	public static final String					TIPO_CEDULA_JURIDICA_STR														= "tipo.cedula.juridica";
	public static final String					TIPO_CEDULA_DIMEX_STR																= "tipo.cedula.dimex";
	public static final String					TIPO_CEDULA_NITE_STR																= "tipo.cedula.nite";
	// usuario
	public static final Integer					USUARIO_ESTADO_ACTIVO																= 1;
	public static final Integer					USUARIO_ESTADO_INACTIVO															= 2;
	public static final String					KEY_USUARIO_NO_EXISTE																= "error.login.usuarioNoExiste";

	// Compra

	public static final Integer					COMPRA_ESTADO_PENDIENTE															= 1;
	public static final Integer					COMPRA_ESTADO_INGRESADA_INVENTARIO									= 2;
	public static final Integer					COMPRA_ESTADO_CHEQUEDO_MERCANCIA										= 3;
	public static final Integer					COMPRA_ESTADO_ANULADA																= 4;

	public static final String					COMPRA_ESTADO_PENDIENTE_STR													= "Pendiente";
	public static final String					COMPRA_ESTADO_INGRESADA_INVENTARIO_STR							= "Ingresada";
	public static final String					COMPRA_ESTADO_CHEQUEDO_MERCANCIA_STR								= "Chequeo";
	public static final String					COMPRA_ESTADO_ANULADA_STR														= "Anulada";

	public static final Integer					COMPRA_TIPO_DOCUMENTO_FACTURA												= 1;
	public static final Integer					COMPRA_TIPO_DOCUMENTO_BOLETA												= 2;

	public static final String					COMPRA_TIPO_DOCUMENTO_FACTURA_STR										= "Factura";
	public static final String					COMPRA_TIPO_DOCUMENTO_BOLETA_STR										= "Boleta";

	public static final Integer					COMPRA_FORMA_PAGO_CONTADO														= 1;
	public static final Integer					COMPRA_FORMA_PAGO_CREDITO														= 2;

	public static final String					COMPRA_FORMA_PAGO_CONTADO_STR												= "Contado";
	public static final String					COMPRA_FORMA_PAGO_CREDITO_STR												= "Credito";

	// Factura
	public static final String					FACTURA_CONDICION_VENTA_CONTADO											= "01";
	public static final String					FACTURA_CONDICION_VENTA_CREDITO											= "02";

	public static final String					FACTURA_CONDICION_VENTA_CONTADO_STR									= "factura.codicion.venta.contado";
	public static final String					FACTURA_CONDICION_VENTA_CREDITO_STR									= "factura.codicion.venta.credito";

	public static final String					FACTURA_TIPO_DOC_TIQUETE														= "04";
	public static final String					FACTURA_TIPO_DOC_PROFORMAS													= "88";
	public static final String					FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO								= "87";
	public static final String					FACTURA_TIPO_DOC_FACTURA_ELECTRONICA								= "01";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO								= "02";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO								= "03";
	public static final String					FACTURA_TIPO_DOC_COMPRAS														= " ";

	public static final String					RECEPCION_FACTURA_TIPO_DOC_ACEPTADO									= "05";
	public static final String					RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL					= "06";
	public static final String					RECEPCION_FACTURA_TIPO_DOC_RECHAZADO								= "07";

	public static final String					FACTURA_TIPO_DOC_TIQUETE_STR												= "factura.tipo.documento.factura.tiquete";
	public static final String					FACTURA_TIPO_DOC_PROFORMAS_STR											= "factura.tipo.documento.factura.proforma";
	public static final String					FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO_STR						= "factura.tipo.documento.factura.tiquete.uso.interno";
	public static final String					FACTURA_TIPO_DOC_FACTURA_ELECTRONICA_STR						= "factura.tipo.documento.factura.electronica";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_STR						= "factura.tipo.documento.nota.debito";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO_STR						= "factura.tipo.documento.nota.credito";

	public static final String					RECEPCION_FACTURA_MENSAJE_ACEPTADO									= "01";
	public static final String					RECEPCION_FACTURA_MENSAJE_ACEPTADO_PARCIAL					= "02";
	public static final String					RECEPCION_FACTURA_MENSAJE_RECHAZADO									= "03";

	public static final String					FACTURA_MEDIO_PAGO_EFECTIVO													= "01";
	public static final String					FACTURA_MEDIO_PAGO_TARJETA													= "02";
	public static final String					FACTURA_MEDIO_PAGO_CHEQUE														= "03";
	public static final String					FACTURA_MEDIO_PAGO_TRANSFERENCIA										= "04";

	public static final String					FACTURA_MEDIO_PAGO_EFECTIVO_STR											= "Efectivo";
	public static final String					FACTURA_MEDIO_PAGO_TARJETA_STR											= "Tarjeta";
	public static final String					FACTURA_MEDIO_PAGO_CHEQUE_STR												= "Cheque";
	public static final String					FACTURA_MEDIO_PAGO_TRANSFERENCIA_STR								= "Transferencia";

	public static final Integer					FACTURA_ESTADO_PENDIENTE														= 1;
	public static final Integer					FACTURA_ESTADO_PROFORMAS														= 3;
	public static final Integer					FACTURA_ESTADO_FACTURADO														= 2;
	public static final Integer					FACTURA_ESTADO_TIQUETE_USO_INTERNO									= 4;
	public static final Integer					FACTURA_ESTADO_ANULADA            									= 5;

	public static final Integer					FACTURA_ESTADO_FIRMA_PENDIENTE											= 1;
	public static final Integer					FACTURA_ESTADO_REFIRMAR_DOCUMENTO										= 8;
	public static final Integer					FACTURA_ESTADO_FIRMA_COMPLETO												= 2;

	public static final String					FACTURA_ESTADO_PENDIENTE_STR												= "factura.estado.pendiente";
	public static final String					FACTURA_ESTADO_FACTURADO_STR												= "factura.estado.facturado";

}
