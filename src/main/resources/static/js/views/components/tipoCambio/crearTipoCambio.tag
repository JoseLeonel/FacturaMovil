<tipo-CambioMoneda>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-balance-scale"></i>&nbsp {$.i18n.prop("tipoCambio.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("tipoCambio.created_at")} </th>
                                <th class="table-header" >{$.i18n.prop("tipoCambio.updated_at")} </th>
                                <th class="table-header" >{$.i18n.prop("tipoCambio.usuario")}    </th>
                                <th class="table-header" >{$.i18n.prop("tipoCambio.moneda")}     </th>
                                <th class="table-header" >{$.i18n.prop("tipoCambio.total")}      </th>
                                <th class="table-header" >{$.i18n.prop("usuarioCaja.estado")}    </th>
                                <th class="table-header" >{$.i18n.prop("listado.acciones")}      </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("tipoCambio.created_at")}    </th>
                                <th>{$.i18n.prop("tipoCambio.updated_at")}    </th>
                                <th>{$.i18n.prop("tipoCambio.usuario")}       </th>
                                <th>{$.i18n.prop("tipoCambio.moneda")}        </th>
                                <th>{$.i18n.prop("tipoCambio.total")}         </th>
                                <th>{$.i18n.prop("tipoCambio.estado")}        </th>
                                <th>                                          </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->
<div >
    <div class="row center " show ={mostrarFormulario} >
    <div class="col-lg-4 "></div>
        <div class="col-md-12 col-lg-4 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {$.i18n.prop("titulo.agregar.tipoCambio")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{tipoCambio.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("tipoCambio.total")}  <span class="requeridoDato">*</span></label>
                                <input type="number" step="any" class="form-control total" placeHolder ="{$.i18n.prop("tipoCambio.total")}" id="total" name="total" value="{tipoCambio.total}"  >
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left " >
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                           <button onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>  
                       </div>
                    </div>   
                </div>
            </div>   
        </div>
        <div class="col-lg-4 "></div>
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
                    <form id = "formulario" name ="formulario"   class="advanced-search-form ">
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
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalFondoInicial.toLocaleString('de-DE')}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalEfectivo")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalEfectivo.toLocaleString('de-DE')}" readonly >
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalTarjeta")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalTarjeta.toLocaleString('de-DE')}" readonly >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalBanco")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalBanco.toLocaleString('de-DE')}" readonly >
                            </div>

                        </div>
                        <div class="row">
                            <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalCredito")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalCredito.toLocaleString('de-DE')}" readonly >
                            </div>
                             <div class= "col-md-4 col-sx-12 col-sm-4 col-lg-4">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalAbono")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalAbono.toLocaleString('de-DE')}" readonly >
                            </div>
                            <div class= "col-md-4 col-sx-12 col-sm-6 col-lg-4">
                                <label class="knob-label" >{$.i18n.prop("usuarioCaja.totalNeto")}  </label>
                                <input type="text" class="form-control "  value="₡ {usuarioCaja.totalNeto.toLocaleString('de-DE')}" readonly >
                            </div>
                           
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>

                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
    </div>
</div>
<imprimir-caja></imprimir-caja>
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
    self.botonModificar            = false
    self.botonAgregar              = false
    self.mostrarVerDetalle         = false
    self.tipoCambio = {
        id:null,
        total:"",
        estado:""
    }
self.on('mount',function(){
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
    __listado()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __Eventos()
   
})
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			total : {
				required : true,
                maxlength:7,
                minlength:1,
			}                        
		},
		ignore : []

	});
	return validationOptions;
};



/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $('#total').mask('000.000', {
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
    swal({
        title: "", 
        text: $.i18n.prop("mensaje.alert.regresar.listado"), 
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#00539B',
        cancelButtonColor: '#d33',
        confirmButtonText:$.i18n.prop("confirmacion.si"),
        cancelButtonText: $.i18n.prop("confirmacion.no"),
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger'
        }).then(function (isConfirm) {
            if(isConfirm){
                self.mostrarListado     = true;
                self.botonAgregar       = false;
                self.botonModificar     = false;
                self.mostrarFormulario  = false 
                self.mostrarVerDetalle  = false
                self.update()
                __listado();

            }
    });    
}
// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        //desahabilita boton modificar
        self.botonModificar   = false;
        // habilita el formulario
        self.botonAgregar     = true;
         self.tipoCambio = {
            id:null,
            total:"",
            estado:""
            }
        self.update();
        //Inicializar el Formulario
        $(".errorServerSideJgrid").remove();
        $("#formulario").validate(reglasDeValidacion());
   
    })
}


/**
*   Agregar 
**/
__agregar(){
   
    if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("tipoCambio.mensaje.alert.agregar"),
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
                    url : 'AgregarTipoCambioAjax.do',
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
      	                           confirmButtonText: $.i18n.prop("btn.aceptar"),
      	                         })
                            }
                            
                        } else {
                        	serverMessageJson(data);
                               swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: $.i18n.prop("btn.aceptar"),
	                         })
	                        $("#formulario").validate(reglasDeValidacion());
                            $(".errorServerSideJgrid").remove();
                            $("#descripcion").val(null);
                            $("#terminal").val(null);
                              __Eventos()
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

/**
** Modificar la Empresa
**/
__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("caja.mensaje.alert.modificar"),'ModificarCajaAjax.do','ListarcajasAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarTipoCambioAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                includeActions('.dataTables_wrapper','.dataTables_length')
                agregarInputsCombos();
                __MantenimientoAgregar()
                    //Activar filtros
                ActivarEventoFiltro(".tableListar")
                __Eventos()
                __cerrarCaja()
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
                               {'data' : 'created_at'        ,"name":"created_at"  ,"title" : $.i18n.prop("tipoCambio.created_at")  ,"autoWidth" :false,
                                    "render":function(created_at,type, row){
                                        return __displayDate_detail(created_at);
                                    }
                                },
                                {'data' : 'updated_at'        ,"name":"updated_at" ,"title" : $.i18n.prop("tipoCambio.updated_at")  ,"autoWidth" :false,
                                    "render":function(updated_at,type, row){
                                        return __displayDate_detail(updated_at);
                                    }
                                },
                               {'data' : 'usuario'       ,"name":"usuario"         ,"title" : $.i18n.prop("tipoCambio.usuario")     ,"autoWidth" :false,
                                    "render":function(usuario,type, row){
                                        return usuario.nombreUsuario;
                                    }
                               },
                               {'data' : 'moneda'        ,"name":"moneda"  ,"title" : $.i18n.prop("tipoCambio.moneda")  ,"autoWidth" :false },
                               {'data' : 'total'         ,"name":"total"   ,"title" : $.i18n.prop("tipoCambio.total")   ,"autoWidth" :false,
                                    "render":function(total,type, row){
                                        return "₡" + total.toLocaleString('de-DE');
                                    }
                               },
                               {'data' : 'estado'        ,"name":"estado"          ,"title" : $.i18n.prop("tipoCambio.estado")      ,"autoWidth" :false},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
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
  var cerrar  = '<a href="#"  title="Desactivar Cambio Moneda" class="btn btn-danger  btn-cerrar btnCerrarTipoCambio" role="button"> </a>';
  cerrar = row.estado =="Activo"?cerrar:""
  return  cerrar ;
}


/**
 * Funcion para Modificar del Listar
 */
function __cerrarCaja(){
	$('#tableListar').on('click','.btnCerrarTipoCambio',function(e){
    	var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        self.tipoCambio  = data

        self.update()
        cerrarTipoCambioAjax()
       
	});
}

/**
*Cerrar caja
**/
function cerrarTipoCambioAjax(){
         var formulario = $('#formulario').serialize();
        swal({
           title: '',
           text: $.i18n.prop("tipoCambio.mensaje.alert.desactivar"),
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
                    url : 'DesactivarTipoCambioAjax.do',
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
      	                           confirmButtonText: $.i18n.prop("btn.aceptar"),
      	                         })
                            }
                            
                        } else {
                        	serverMessageJson(data);
                               swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: $.i18n.prop("btn.aceptar"),
	                         })
	                        __listado()
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
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 6    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
        // Select
    	if ($(this).index() == 5  ){
    	    var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}



</script>
</tipo-CambioMoneda>