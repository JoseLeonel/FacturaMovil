<cerrar-caja>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-unlock"></i>&nbsp Cajas con Apertura </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    
<!-- Listado  -->
<div classs="contenedor-listar container" id="container"  show={mostrarListado}  >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 " style="width:98.50%;">
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header" style="width:2%" >{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th class="table-header" style="width:10%">{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th class="table-header"  style="width:10%">{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th class="table-header" style="width:10%" >{$.i18n.prop("usuarioCaja.totalNeto")}     </th>
                                <th class="table-header"  style="width:10%" >{$.i18n.prop("usuarioCaja.estado")}        </th>
                                <th class="table-header" >{$.i18n.prop("listado.acciones")}          </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.caja")}          </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.created_at")}    </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.updated_at")}    </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.usuario")}       </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.fondoIncial")}   </th>
                                <th style="width:10%">{$.i18n.prop("usuarioCaja.totalNeto")}     </th>
                                <th  style="width:10%">{$.i18n.prop("usuarioCaja.estado")}        </th>
                                <th>                                    </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->
<div >
    <div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-sx-12 col-lg-4 col-sm-2"></div>
        <div class="col-md-12 col-lg-4 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.agregar.usuarioCaja")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.caja")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control" id="caja" name="caja" >
                                    <option  each={cajas.aaData}  value="{id}"  >{descripcion}</option>
                                </select>
                            </div>
                        </div>    
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.fondoIncial")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="form-control totalFondoInicial" id="totalFondoInicial" name="totalFondoInicial" value="{usuarioCaja.totalFondoInicial}"  >
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado} type="button" class="btn-dark-gray btn-back pull-left " >
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                          <button onclick={__agregar} class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                        </div>
                        
                    </div>   
                  
                </div>
            </div>   
        </div>
        <div class=" col-lg-4 "></div>
    </div>
</div>

<div >
    <div class="row center " show ={mostrarVerDetalle} >
    <div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-search"></i>&nbsp {$.i18n.prop("titulo.mostrar.usuarioCaja")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formularioUsuarioCaja" name ="formularioUsuarioCaja"   class="advanced-search-form ">
                        <input type="hidden" name="id" id="id" value="{usuarioCaja.id}">
                        <input type="hidden" name="caja" id="caja" value="{usuarioCaja.caja.id}">
                        
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.caja")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.caja.descripcion}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.usuario")}  </label>
                                <input type="text"  class="form-control"  value="{usuarioCaja.usuario.nombre}"  readonly>
                            </div>

                        </div>

                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.fondoIncial")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalFondoInicialSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalEfectivo")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalEfectivoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalTarjeta")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalTarjetaSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalBanco")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalBancoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalServicio")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalServicioSTR}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalAbono")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalAbonoSTR}" readonly >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-6 col-lg-4 ">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalNeto")}  </label>
                                <input type="text" class="form-control "  value=" {usuarioCaja.totalNetoSTR}" readonly >
                            </div>                           
                            <div class= "col-md-4 col-sx-12 col-sm-6 col-lg-4">
                               <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalDolares")}  </label>
                               <input type="text" class="form-control "  value=" {usuarioCaja.totalDolaresSTR}" readonly >
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                    <button show ={mostrarConsultaComanda} onclick ={__consultarTotalesArticulo}  type="button" class="btn-green btn-edit pull-right" id="btnTotalesArticulo" name = "btnTotalesArticulo">
                        {$.i18n.prop("btn.totales.articulo")}
                    </button>
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
</div>

<style type ="text/css">
    .fondoEncabezado {
        background: #00539B;
        color: #f9fafc;
    }
  .requeridoDato {
            color: red;
            text-align: left;
            font-weight: 500;
            font-size: 13px;
        }
    
        .wrap{
            max-width:1100px;
            width:100%;
        }
        body {
            overflow: hidden;
            background:white;
            font-size: 12px !important;
        }
        .contenedor-listar{
            width:100%;
        }
        .input-table-search{
            margin-left: 15px;
            margin-right: 15px;
            width:100%;
        }
        .botonConsulta{
            margin-top:28px;
        }
        
        table td{ 
            text-align: center;
            font-size: 12px;
            
                }
        table th {
                text-align: center;
                font-size: 12px;
        }
        th, td {
            white-space: nowrap;
        }
    </style>
<script>
    var self = this;
    self.idiomaDataTable           = []         // idioma de la datatable nuevo
    self.formato_tabla             = []         // Formato del Listado de la Tabla 
    self.mostrarListado            = true 
    self.cajas                  = {aaData:[]}
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.mostrarConsultaComanda    = false
    self.caja = {
        id:null,
        descripcion:"",
        estado:""
    }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
     __Eventos()
    __listadoCajasActivas()
})
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			descripcion : {
				required : true,
                maxlength:80,
                minlength:1,
			},                                   
            terminal : {
				required : true,
                maxlength:3,
                minlength:3,
			}                             
		},
		ignore : []

	});
	return validationOptions;
}
/**
*  Mostrar listado datatable Cajas Activas
**/
function __listadoCajasActivas(){
    self.cajas                  = {aaData:[]}
    $.ajax({
         url: "ListarCajasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            if(result.aaData.length > 0){
                self.cajas.aaData =  result.aaData
                self.update();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);
    $('#terminal').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.mostrarVerDetalle  = false
    self.mostrarConsultaComanda    = false
    self.update()
    __listado();
}

/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formularioUsuarioCaja').serialize();
    $.ajax({
        url: "MostrarUsuarioCajaAjax.do",
        datatype: "json",
        data: formulario,
        method:"Post",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.mostrarVerDetalle = true
                        self.mostrarConsultaComanda = false
                        if(modeloTabla.caja.empresa.comandaEmpresa != null && modeloTabla.caja.empresa.comandaEmpresa > 0){
                            self.mostrarConsultaComanda = true                        	
                        }
                        self.mostrarListado   = false;
                        self.usuarioCaja  = modeloTabla
                        self.update()
                    });
                }
            }
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarUsuariosCajasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                agregarInputsCombos();
                    //Activar filtros
                ActivarEventoFiltro(".tableListar")
                __VerDetalle()
                __Eventos()
             }else{
                 __Eventos()
             } 
        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    })
} 
/**
*Formato del listado de los cambios
**/
function __InformacionDataTable(){
    self.informacion_tabla = [ 
                               {'data' : 'caja'        ,"name":"caja"  ,"title" : $.i18n.prop("usuarioCaja.caja")  ,"autoWidth" :false,
                                    "render":function(caja,type, row){
                                        return caja == null?"":caja.descripcion;
                                    }
                                },
                               {'data' : 'created_atSTR'        ,"name":"created_atSTR"  ,"title" : $.i18n.prop("usuarioCaja.created_at")  ,"autoWidth" :false
                                },
                                {'data' : 'updated_atSTR'        ,"name":"updated_atSTR" ,"title" : $.i18n.prop("usuarioCaja.updated_at")  ,"autoWidth" :false
                                },
                               {'data' : 'usuario'       ,"name":"usuario"         ,"title" : $.i18n.prop("usuarioCaja.usuario")     ,"autoWidth" :false,
                                    "render":function(usuario,type, row){
                                        return usuario.nombreUsuario;
                                    }
                               },
                               {'data' : 'totalFondoInicialSTR'        ,"name":"totalFondoInicialSTR"  ,"title" : $.i18n.prop("usuarioCaja.fondoIncial")  ,"autoWidth" :false},
                               {'data' : 'totalNetoSTR'                ,"name":"totalNetoSTR"           ,"title" : $.i18n.prop("usuarioCaja.totalNeto")      ,"autoWidth" :false},
                               
                               {'data' : 'estado'        ,"name":"estado"          ,"title" : $.i18n.prop("usuarioCaja.estado")      ,"autoWidth" :false},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
Formato de montos
**/
function FormatoMontos(valor){
   var resultado = __valorNumerico(valor)
   return resultado;
}

/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}                                    
/**
* Opciones listado de los clientes
*/
function __Opciones(id,type, row){
  var verDetalle  = '<a href="#"  title="Ver Detalle" class="btn btn-success  btn-buscar btnVerDetalle" role="button"> </a>';
  return  verDetalle ;
}
/**
 * Funcion para Modificar del Listar
 */
function __VerDetalle(){
	$('#tableListar').on('click','.btnVerDetalle',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.usuarioCaja  = data
        self.update()
        __consultar()
	});
}
/**
*  Totales de Articulos
**/
__consultarTotalesArticulo(){
	$.ajax({
        type : "GET",
        dataType : "json",
        data: {
			"idUsuarioCaja":$("#id").val(),
	    },
        url : 'AgrupaArticulosCategoriaAjax.do',
        success : function(data) {
            if (data.status != 200) {
            	serverMessageJson(data);
                if (data.message != null && data.message.length > 0) {
                	swal({
                         title: '',
                         text: data.message,
                         type: 'error',
                         showCancelButton: false,
                         confirmButtonText: $.i18n.prop("btn.aceptar"),
                       })
                }
            } else {
            	var informacion = {
            		nombreImpresora:"factura",
            		cantidadCaracteresLinea:"40",
            		formatoTiquete:"",
            		detalles:data.listaObjetos
            	}
        		var JSONData = JSON.stringify(informacion);		
            	if (data.listaObjetos != null && data.listaObjetos.length > 0) {
            		$.ajax({
            	        contentType: 'application/json',
            	        url: 'http://localhost:8033/service/AgrupaArticulosCategoriaComandaAjax',
            	        datatype: "json",
            	        data : JSONData,
            	        method:"POST",
            	        success: function (result) {
                            swal({
                           	 	title: '',
                            	text: data.message,
                            	type: 'success',
                            	showCancelButton: false,
                            	confirmButtonText: $.i18n.prop("btn.aceptar"),
                          	})
            	      	   self.update()
            	        },
            	        error: function (xhr, status) {
            	            console.log(xhr);
            	            mensajeErrorServidor(xhr, status);
            	        }
            	    });	
            	}
            }
        },
        error : function(xhr, status) {
            console.log(xhr);
            mensajeErrorServidor(xhr, status);
        }
    });
}

/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 7    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
 
    })

}
</script>
</cerrar-caja>