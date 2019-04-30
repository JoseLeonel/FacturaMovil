package com.emprendesoftcr.Utils;

import java.util.ResourceBundle;

public class Constantes {

	public static final ResourceBundle	RESOURCE_BUNDLE																			= ResourceBundle.getBundle("factura");

	//Inventario
	public static final String					ARTICULO_MINIMO																			= "1";
	public static final String					ARTICULO_MAXIMO																			= "2";
	public static final String					PLANTILLA_CORREO_CUENTAS_POR_COBRAR									= "email/cuentasxcobrar.vm";
	public static final String					PLANTILLA_CORREO_VENTA_POR_CODIGO										= "email/ventasXCodigo.vm";
	public static final String					PLANTILLA_CORREO_COMPRAS_ACEPTADAS									= "email/emailResumenCompras.vm";
	public static final String					PLANTILLA_CORREO_RESUMEN_VENTAS_RANGO_FECHA         = "email/emailResumenFactura.vm";

// Version de los tipos de documentos 
	public static final String					TIQUETE_ELECTRONICO_VERSION													= "Tiquete Electrónico ver 4.2";
	public static final String					FACTURA_ELECTRONICO_VERSION													= "Factura Electrónica ver 4.2";
	public static final String					NOTA_CREDITO_ELECTRONICO_VERSION										= "Nota Credito Electrónica ver 4.2";
	public static final String					NOTA_DEBITO_ELECTRONICO_VERSION											= "Nota Debito Electrónica ver 4.2";
	public static final String					PROFORMA_VERSION																		= "Proforma";
	// callbackurl 1 = si 0= No
	public static final Integer					CALLBACKURL_SI																			= 1;
	public static final Integer					CALLBACKURL_NO																			= 0;
	public static final Integer					EMPRESA_VIVIANA_MARTINEZ_8085												= 12;

	// Tipo de mostrar el articulo de impuesto de servicio 10 porciento
	public static final Integer					NO_MOSTRAR_IMPUESTO_10_PORCIENTO										= 1;
	public static final Integer					SI_MOSTRAR_IMPUESTO_10_PORCIENTO										= 2;
	public static final String					COMBO_TODOS																					= "0";
	// Empresa aplica factura Electronica
	public static final Integer					NO_APLICA_FACTURA_ELECTRONICA												= 1;
	public static final Integer					SI_APLICA_FACTURA_ELECTRONICA												= 0;
	public static final Integer					SI_ENVIAR_CORREO_CLIENTE_FRECUENTE									= 1;
	public static final Integer					NO_ENVIAR_CORREO_CLIENTE_FRECUENTE									= 0;
//cantidad de dias de un credito minimo
	public static final Integer					CANTIDAD_DIAS_MINIMO_CREDITO												= 1;
	// Tipo de codigos de productos
	public static final String					TIPO_IMPUESTO_VENTA_ARTICULO												= "01";
	public static final String					TIPO_CODIGO_ARTICULO_POR_SERVICIO										= "07";
	public static final String					TIPO_CODIGO_ARTICULO_USO_INTERNO										= "04";
	public static final String					TIPO_CODIGO_ARTICULO_CODIGO_VENDEDOR								= "01";
	public static final String					TIPO_CODIGO_ARTICULO_CODIGO_COMPRADOR								= "02";
	public static final String					TIPO_CODIGO_ARTICULO_CODIGO_ASIGNADO_POR_INDUSTRIAS	= "03";
	public static final String					TIPO_CODIGO_ARTICULO_CODIGO_OTROS										= "99";
	public static final String					CODIGO_ARTICULO_IMPUESTO_SERVICIO										= "8888";

	// Direcciones de callback
	public static final String					URL_ALAJUELA_CALLBACK																= "http://www.emprendesoftcr.com:8081/service/callback.do";
	public static final String					URL_GUANACASTE_CALLBACK															= "http://www.emprendesoftcr.com:8084/service/callback.do";
	public static final String					URL_INVENTARIO_CALLBACK															= "http://www.emprendesoftcr.com:8080/service/callback.do";
	public static final String					URL_JACO_CALLBACK																		= "http://www.emprendesoftcr.com:8082/service/callback.do";
	public static final String					URL_JACODOS_CALLBACK																= "http://www.emprendesoftcr.com:8086/service/callback.do";
	public static final String					URL_SANTA_ANA_CALLBACK															= "http://www.emprendesoftcr.com:8083/service/callback.do";
	public static final String					URL_PRUEBAS_CALLBACK																= "http://www.emprendesoftcr.com:8085/service/callback.do";
////	public static final String					URL_ALAJUELA_CALLBACK																= "http://190.124.250.142:8081/service/callback.do";
//	public static final String					URL_GUANACASTE_CALLBACK															= "http://190.124.250.142:8084/service/callback.do";
//	public static final String					URL_INVENTARIO_CALLBACK															= "http://190.124.250.142:8080/service/callback.do";
//	public static final String					URL_JACO_CALLBACK																		= "http://190.124.250.142:8082/service/callback.do";
//	public static final String					URL_JACODOS_CALLBACK																= "http://190.124.250.142:8086/service/callback.do";
//	public static final String					URL_SANTA_ANA_CALLBACK															= "http://190.124.250.142:8083/service/callback.do";
//	public static final String					URL_PRUEBAS_CALLBACK																= "http://190.124.250.142:8085/service/callback.do";

	// Semaforo
	public static final Integer					BLOQUES_DOCUMENTOS_A_PROCESAR												= 250;
	public static final Integer					BLOQUES_DOCUMENTOS_A_PROCESAR_CORREOS								= 40;

	// 48 horas cada 5 minutos

	public static final Integer					MAXIMO_REINTENTOS_ACEPTACION												= 8;
	public static final String					MAXIMO_REINTENTOS_ACEPTACION_STR										= "Tope de intentos de aceptacion,no envio respuesta";

	public static final Integer					SEMAFORO_ESTADO_ACTIVO															= 1;
	public static final Integer					SEMAFORO_ESTADO_INACTIVO														= 0;
	public static final Integer					SEMAFORO_ESTADO_REINTENTOS													= 3;
	public static final Integer					SEMAFORO_ESTADO_FIRMADO															= 1;
	public static final Integer					SEMAFORO_ESTADO_ENVIO																= 2;
	public static final Integer					SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS								= 3;
	public static final Integer					SEMAFORO_ESTADO_ENVIAR_CORREOS											= 4;

	public static final String					IDP_URI_PRODUCCION																	= "https://idp.comprobanteselectronicos.go.cr/auth/realms/rut/protocol/openid-connect";
	public static final String					IDP_URI_DOCUMENTOS_PRODUCCION												= "https://api.comprobanteselectronicos.go.cr/recepcion/v1/recepcion";
	public static final String					IDP_URI_DOCUMENTOS_DESARROLLO												= "https://api.comprobanteselectronicos.go.cr/recepcion-sandbox/v1/recepcion";
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
	public static final String					UNIDAD_MEDIDA																				= "Unid";
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
	public static final Integer					HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS		= 99;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_HACIENDA										= 6;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_RECHAZADO									= 7;
	public static final Integer					HACIENDA_ESTADO_ACEPTADO_PARCIAL										= 8;
	public static final Integer					HACIENDA_ESTADO_ANULADA															= 9;
	public static final Integer					HACIENDA_ESTADO_ERROR																= 10;
	public static final Integer					HACIENDA_ESTADO_PROBLEMA_ENVIO_NO_TRASABLE					= 99;
	public static final Integer					HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO								= 98;
	public static final String					HACIENDA_ESTADO_ENVIADO_HACIENDA_str								= "Documento eviado a hacienda.";
	public static final String					HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR								= "OK";
	public static final String					HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR							= "ERROR";
	public static final String					HACIENDA_ESTADO_ACEPTADO_RECIBIDO										= "recibido";
	public static final Integer					HACIENDA_ESTADO_ACEPTACION_HACIENDA_TOPE_REINTENTOS	= 8;
	// Hacienda
	public static final Integer					HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE								= 1;
	public static final Integer					HACIENDA_NOTIFICAR_CLIENTE_ENVIADO									= 2;
	public static final Integer					HACIENDA_NOTIFICAR_CLIENTE_FRECUENTE_NO_ENVIADO			= 3;

	public static final String					ESTADO_HACIENDA_ACEPTADO														= "1";
	public static final String					ESTADO_HACIENDA_ACEPTADO_PARCIAL										= "2";
	public static final String					ESTADO_HACIENDA_RECHAZADO														= "3";

	public static final String					HACIENDA_TIPODOC_COMPRAS														= " ";

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
	public static final String					MOTIVO_SALIDA_INVENTARIO_COMPRA_ANULACION						= "Salida por compra anulada:";
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

	// Cuentas por Pagar
	public static final String					CUENTA_POR_PAGAR_ESTADO_PENDIENTE										= "Pendiente";
	public static final String					CUENTA_POR_PAGAR_DESCRIPCION_AUTOMATICO							= "Realizada por ventas";
	public static final String					CUENTA_POR_PAGAR_NOTA_AUTOMATICO										= "Realizada por ventas";
	public static final String					CUENTA_POR_PAGAR_ESTADO_CERRADO											= "Cerrada";
	public static final String					CUENTA_POR_PAGAR_ESTADO_ANULADA											= "Anulada";
	// Abono Pagar
	public static final String					ABONO_PAGAR_ESTADO_PAGADO														= "Pagado";
	public static final String					ABONO_PAGAR_ESTADO_ANULADO													= "Anulado";

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
	public static final String					ROL_USUARIO_VENDEDOR																= "usuario_vendedor";
	public static final String					ROL_ADMINISTRADOR_CAJERO														= "Administrador_Cajero";
	public static final String					ROL_ADMINISTRADOR_RESTAURANTE												= "Administrador_Restaurante";
	public static final String					ROL_ADMINISTRADOR_EMPRESA														= "Administrador_Empresa";

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

	public static final String					FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO						= "01";
	public static final String					FACTURA_CODIGO_REFERENCIA_CORRIJE_TEXTO							= "02";
	public static final String					FACTURA_CODIGO_REFERENCIA_CORRIJE_MONTO							= "03";
	public static final String					FACTURA_CODIGO_REFERENCIA_OTRO_DOCUMENTO						= "04";
	public static final String					FACTURA_CODIGO_REFERENCIA_SUSTITUYE_COMPROBANTE			= "05";

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

	public static final String					RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_1								= "01";
	public static final String					RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL_1				= "02";
	public static final String					RECEPCION_FACTURA_TIPO_DOC_RECHAZADO_1							= "03";

	public static final String					FACTURA_TIPO_DOC_TIQUETE_STR												= "factura.tipo.documento.factura.tiquete";
	public static final String					FACTURA_TIPO_DOC_PROFORMAS_STR											= "factura.tipo.documento.factura.proforma";
	public static final String					FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO_STR						= "factura.tipo.documento.factura.tiquete.uso.interno";
	public static final String					FACTURA_TIPO_DOC_FACTURA_ELECTRONICA_STR						= "factura.tipo.documento.factura.electronica";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_STR						= "factura.tipo.documento.nota.debito";
	public static final String					FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO_STR						= "factura.tipo.documento.nota.credito";
	public static final String					FACTURA_TIPO_DOC_COMPRAS_STR												= "factura.tipo.documento.compras";

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
	public static final Integer					FACTURA_ESTADO_ANULADA															= 5;

	public static final Integer					FACTURA_ESTADO_FIRMA_PENDIENTE											= 1;
	public static final Integer					FACTURA_ESTADO_REFIRMAR_DOCUMENTO										= 8;
	public static final Integer					FACTURA_ESTADO_FIRMA_COMPLETO												= 2;
	public static final Integer					FACTURA_ESTADO_FIRMA_EN_PROCESOS										= 3;
	public static final Integer					FACTURA_ESTADO_PROBLEMA_AL_FIRMAR										= 98;

	public static final String					FACTURA_ESTADO_PENDIENTE_STR												= "factura.estado.pendiente";
	public static final String					FACTURA_ESTADO_FACTURADO_STR												= "factura.estado.facturado";

	// Nombres de procedimientos
	public static final String					SP_TOTAL_FACTURAS																		= "SPTOTALFACTURAS";
	public static final String					SP_TOTAL_FACTURAS_IN_FECHA_INICIO										= "FECHA_INICIO";
	public static final String					SP_TOTAL_FACTURAS_IN_FECHA_FIN											= "FECHA_FIN";
	public static final String					SP_TOTAL_FACTURAS_IN_ID_EMPRESA											= "ID_EMPRESA";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL													= "TOTAL";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL_DESCUENTO								= "TOTAL_DESC";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL_IMPUESTOS								= "TOTAL_IMPUESTOS";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL_VENTAS_NETAS						= "TOTAL_NETAS";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL_VENTAS_EXENTAS					= "TOTAL_EXENTAS";
	public static final String					SP_TOTAL_FACTURAS_OUT_TOTAL_VENTAS_GRABADAS					= "TOTAL_GRABADAS";
	// Totales de Compras Aceptadas
	public static final String					SP_TOTAL_ACEPTADAS_IN_FECHA_INICIO									= "FECHA_INICIO";
	public static final String					SP_TOTAL_ACEPTADAS_IN_FECHA_FIN											= "FECHA_FIN";
	public static final String					SP_TOTAL_ACEPTADAS_IN_ID_EMPRESA										= "ID_EMPRESA";
	public static final String					SP_TOTAL_COMPRAS_ACEPTADAS													= "SPTOTALACEPTADAS";
	public static final String					SP_TOTAL_COMPRAS_ACEPTADAS_ID_EMPRESA								= "ID_EMPRESA";
	public static final String					SP_TOTAL_COMPRAS_ACEPTADAS_OUT											= "TOTAL";
	public static final String					SP_TOTAL_IMPUESTOS_COMPRAS_ACEPTADAS_OUT						= "TOTAL_IMPUESTOS";

	// Totales de inventarios
	public static final String					SP_TOTAL_INVENTARIO																	= "SPTOTALINVENTARIOS";
	public static final String					SP_INVENTARIO_ID_EMPRESA														= "ID_EMPRESA";
	public static final String					SP_TOTAL_COSTO_OUT																	= "TOTALCOSTO";
	public static final String					SP_TOTAL_VENTA_OUT																	= "TOTALVENTA";

	// Totales de cuentas por cobrar
	public static final String					SP_TOTAL_CUENTA_COBRAR															= "SPTOTALFACTURAS";
	public static final String					SP_TOTAL_CUENTA_COBRAR_IN_FECHA_INICIO							= "FECHA_INICIO";
	public static final String					SP_TOTAL_CUENTA_COBRAR_IN_FECHA_FIN									= "FECHA_FIN";
	public static final String					SP_TOTAL_CUENTA_COBRAR_IN_ID_EMPRESA								= "ID_EMPRESA";
	public static final String					SP_TOTAL_CUENTA_COBRAR_IN_ID_CLIENTE								= "ID_CLIENTE";
	public static final String					SP_TOTAL_CUENTA_COBRAR_OUT_TOTAL										= "TOTAL";
	public static final String					SP_TOTAL_CUENTA_COBRAR_OUT_SALDO										= "TOTAL_SALDO";
	public static final String					SP_TOTAL_CUENTA_COBRAR_OUT_ABONO										= "TOTAL_ABONO";

	// Procedimientos para generar el consecutivo de una factura
	public static final String					SP_GENERAR_CONSECUTIVO															= "SPGENERARCONSECUTIVOFACTURA";
	public static final String					SP_GENERAR_CONSECUTIVO_IN_ID_EMPRESA								= "ID_EMRPESA";
	public static final String					SP_GENERAR_CONSECUTIVO_IN_ID_USUARIO								= "ID_USUARIO";
	public static final String					SP_GENERAR_CONSECUTIVO_IN_TIPO_DOCUMENTO						= "TIPO_DOCUMENTO";

	public static final String					SP_GENERAR_CONSECUTIVO_OUT_CONSECUTIVO							= "CONSECUTIVO";

	// Procedimiento para generar el consecutivo para una recepcion de factura
	public static final String					SP_GENERAR_CONSECUTIVO_RECEPCION										= "SPGENERARCONSECUTIVORECEPCIONFACTURA";
	public static final String					SP_GENERAR_CONSECUTIVO_RECEPCION_IN_ID_EMPRESA			= "ID_EMRPESA";
	public static final String					SP_GENERAR_CONSECUTIVO_RECEPCION_IN_ID_USUARIO			= "ID_USUARIO";
	public static final String					SP_GENERAR_CONSECUTIVO_RECEPCION_IN_TIPO_DOCUMENTO	= "TIPO_DOCUMENTO";
	public static final String					SP_GENERAR_CONSECUTIVO_RECEPCION_OUT_CONSECUTIVO		= "CONSECUTIVO";

//Procedimiento para generar totales por linea de datalles de la factura
	public static final String					SP_VENTASXDETALLE																		= "ventasxdetalle";
	public static final String					SP_VENTASXDETALLE_IN_ID_EMPRESA											= "e_empresa";
	public static final String					SP_VENTASXDETALLE_IN_FECHA_INICIAL									= "fecha_inicio";
	public static final String					SP_VENTASXDETALLE_IN_FECHA_FINAL										= "fecha_fin";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL													= "total";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL_IMPUESTO								= "total_imp";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL_GRAVADO									= "total_grav";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL_DESCUENTO								= "total_desc";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL_EXENTOS									= "total_exc";
	public static final String					SP_VENTASXDETALLE_OUT_TOTAL_GANANCIA								= "total_gananc";
//Procedimiento callback para actualizar la aceptacion de hacienda
	public static final String					SP_CALLBACK_HACIENDA																= "sp_callback";
	public static final String					SP_CALLBACK_HACIENDA_CLAVE													= "clave_e";
	public static final String					SP_CALLBACK_HACIENDA_ESTADO													= "estado_e";
	public static final String					SP_CALLBACK_HACIENDA_XML														= "xml_e";
	public static final String					SP_CALLBACK_HACIENDA_MENSAJE												= "mensaje_e";

	// Procecimiento para actualizar la caja
	public static final String					SP_ACTUALIZA_CAJA																		= "SPACTUALIZACAJAUSUARIO";
	public static final String					SP_ACTUALIZA_CAJA_ID_CAJA_USUARIO										= "ID_CAJA_USUARIO";
}
