<cliente-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("cliente.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("cliente.empresa")}          </th>
                                <th class="table-header" >{$.i18n.prop("cliente.cedula")}            </th>
                                <th class="table-header" >{$.i18n.prop("cliente.nombreCompleto")}    </th>
                                <th class="table-header" style= "width:6%;" >{$.i18n.prop("cliente.celular")}           </th>
                                <th class="table-header" style= "width:6%;">{$.i18n.prop("cliente.telefono")}          </th>
                                <th class="table-header" >{$.i18n.prop("cliente.created_at")}        </th>
                                <th class="table-header" >{$.i18n.prop("cliente.updated_at")}        </th>
                                <th class="table-header" >{$.i18n.prop("empresa.estado")}            </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}         </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("cliente.empresa")}          </th>
                                <th>{$.i18n.prop("cliente.cedula")}            </th>
                                <th>{$.i18n.prop("cliente.nombreCompleto")}    </th>
                                <th>{$.i18n.prop("cliente.celular")}           </th>
                                <th>{$.i18n.prop("cliente.telefono")}          </th>
                                <th>{$.i18n.prop("cliente.created_at")}        </th>
                                <th>{$.i18n.prop("cliente.updated_at")}        </th>
                                <th>{$.i18n.prop("cliente.estado")}            </th>
                                <th>      </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
    </div>
<!-- Fin del Listado -->

<div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-sx-12 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {cliente.id > 0 ? $.i18n.prop("titulo.modificar.cliente")   :$.i18n.prop("titulo.agregar.cliente")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{cliente.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombreCompleto" placeHolder ="{$.i18n.prop("cliente.nombreCompleto")}" id="nombreCompleto" name="nombreCompleto" value="{cliente.nombreCompleto}"  >

                            </div>
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.tipoCedula")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control tipoCedula " id="tipoCedula" name="tipoCedula" >
                                    <option  each={tipoCedulas.data}  value="{valor}" selected="{cliente.tipoCedula ==valor?true:false}"  >{descripcion}</option>
                                </select>
                            </div>                            
                             <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" id="cedula" name="cedula" placeHolder ="{$.i18n.prop("cliente.cedula")}" value="{cliente.cedula}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.nombreComercial")} </label>
                                <input type="text" class="form-control nombreComercial" placeHolder ="{$.i18n.prop("cliente.nombreComercial")}" id="nombreComercial" name="nombreComercial" value="{cliente.nombreComercial}"  >
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.codigoPais")} <span class="requeridoDato">*</span> </label>
                                <input type="text" class="form-control codigoPais" placeHolder ="{$.i18n.prop("cliente.codigoPais.ejemplo")}" id="codigoPais" name="codigoPais" value="{cliente.codigoPais}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.telefono")} </label>
                                <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("cliente.telefono")}" id="telefono" name="telefono" value="{cliente.telefono}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.celular")} </label>
                                <input type="text" class="form-control celular" placeHolder ="{$.i18n.prop("cliente.celular")}" id="celular" name="celular" value="{cliente.celular}"  >
                            </div>
                        
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.descuento")} </label>
                                <input type="number" class="form-control descuento" placeHolder ="{$.i18n.prop("cliente.descuento")}" id="descuento" name="descuento" value="{cliente.descuento}"  >
                            </div>

                        </div>
                                              
                        <div class="row">    

                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label  >{$.i18n.prop("cliente.identificacionExtranjero")}</label>
                                <input type="text" class="form-control identificacionExtranjero" placeHolder ="{$.i18n.prop("cliente.identificacionExtranjero")}" id="identificacionExtranjero" name="identificacionExtranjero" value="{cliente.identificacionExtranjero}"  >
                            </div>
                            
                             <div class= "col-md-9 col-sx-12 col-sm-9 col-lg-9">
                                <label  >{$.i18n.prop("cliente.correoElectronico")}</label>
                                <input type="text" class="form-control correoElectronico" placeHolder ="{$.i18n.prop("cliente.correoElectronico")}" id="correoElectronico" name="correoElectronico" value="{cliente.correoElectronico}"  >
                            </div>
                        </div>

                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("cliente.otraSena")}</label>
                                <textarea maxlength="250" placeHolder ="{$.i18n.prop("cliente.otraSena")}" class="form-control otraSena" id="otraSena" name="otraSena" value="{cliente.otraSena}" > </textarea> 
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label >{$.i18n.prop("cliente.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{cliente.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                        </div>
                        <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > {$.i18n.prop("btn.modificar")}</button>
                            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                        </div>
                    </div>    
                               
                  
                </div>
            </div>   
        </div>
        <div class="col-md-2 col-lg-2 col-sm-2"></div>
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
    .fondoFacturacion {
        background: rgb(247, 244, 244);
        color: #f9fafc;
        border-style: solid;
        border-width: 5px;cliente
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
        text-align: left;
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
    self.provincias                = []
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.provincias                = {data:[]}  // definir el data del datatable
    self.cantones                  = {data:[]}  // definir el data del datatable
    self.distritos                  = {data:[]}  // definir el data del datatable
    self.barrios                  = {data:[]}  // definir el data del datatable
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.cliente                   ={
		id:null,
    	nombreCompleto:"",
		cedula:"",
		provincia:"",
		celular:"",
		telefono:"",
		otraSena:"",
		correoElectronico:"",
     	descuento:0,
		estado:"",
		sucursal:{
            id:null
        }
    }
self.on('mount',function(){
    _incializarCampos()
    __InicializarTabla('.tableListar')
    __listado()

    __Eventos()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __ComboEstados()
    __listadoTipoCedulas()
    agregarInputsCombos();
    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
    }, false );
})


/**
* Limpiar campos
**/
function _incializarCampos(){
    $("#tipoCedula").val($("#tipoCedula option:first").val());
    $('.nombreCompleto').val(null)
    $('.cedula').val(null)
    $('.identificacionExtranjero').val(null)
    $('.descuento').val(null)
    $('.celular').val(null)
    $('.telefono').val(null)
    $('.codigoPais').val(null)
    $('.correoElectronico').val(null)
    $('.otraSena').val(null)
    $('.nombreComercial').val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
    self.cliente = {
        id:null,
        nombreCompleto:"",
        identificacionExtranjero:"",
        barrio:"",
        distrito:"",
        codigoPais:"",
        cedula:"",
        provincia:"",
        celular:"",
        telefono:"",
        otraSena:"",
        correoElectronico:"",
        descuento:0,
        estado:"",
        sucursal:{
           id:null
        }
    }
    self.update()
}

/**
*  Mostrar listado datatable TipoCedulas
**/
function __listadoTipoCedulas(){
    self.tipoCedulas               = {data:[]}  // definir el data del datatable
    self.update()
    self.tipoCedulas.data.push({
        valor:"01",
        descripcion:$.i18n.prop("tipo.cedula.fisica")
    })
   self.tipoCedulas.data.push({
        valor:"02",
        descripcion:$.i18n.prop("tipo.cedula.juridica")
    })
   self.tipoCedulas.data.push({
        valor:"03",
        descripcion:$.i18n.prop("tipo.cedula.dimex")
    })
     self.tipoCedulas.data.push({
        valor:"04",
        descripcion:$.i18n.prop("tipo.cedula.nite")
    })
    self.update()
}
/**
* Camps requeridos
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			cedula : {
				required : true,
                maxlength:20,
                minlength:9,
			},

			nombreCompleto : {
				required : true,
                maxlength:250,
                minlength:1,
                lettersOnly : true
			},
			otraSenas : {
                maxlength:250,
                minlength:1,
                lettersOnly : true
			},
			correoElectronico : {
                maxlength:250,
                minlength:1,
                email:true
			},
            telefono : {
                maxlength:8,
                minlength:8,
                telefonoFormat:true
			},
            
            celular : {
                maxlength:8,
                minlength:8,
                telefonoFormat:true

			},
            codigoPais : {
                required : true,
                minlength:3,

			},
            correoElectronico : {
                required : true,
                email:true,
                maxlength:250,

			},
            identificacionExtranjero : {
                maxlength:20,
                minlength:9,
			}                       
		},
		ignore : []

	});
	return validationOptions;
};
/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.update()
    self.estados.push({
        codigo: "Activo",
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: "Inactivo",
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();

}
/**
*  Activar Eventos
**/
function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#nombreCompleto").attr("maxlength", 250);
    $("#nombreComercial").attr("maxlength", 250);
    $("#cedula").attr("maxlength", 20);
    $("#identificacionExtranjero").attr("maxlength", 20);
    $("#correoElectronico").attr("maxlength", 250);
    $("#otraSenas").attr("maxlength", 250);
    $("#telefono").attr("maxlength", 8);
    $("#celular").attr("maxlength", 8);
    $('#descuento').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('#codigoPais').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('#cedula').mask('000000000000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('.telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})
    $('.celular').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})
}
/**
*  Regresar al listado
**/
__regresarAlListado(){
  
    self.mostrarListado     = true;
    self.botonAgregar       = false;
    self.botonModificar     = false;
    self.mostrarFormulario  = false 
    self.update()
    _incializarCampos()
    __listado();

}

/**
** Modificar la Empresa
**/
__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
   __modificarRegistro("#formulario",$.i18n.prop("cliente.mensaje.alert.modificar"),'ModificarClienteAjax.do','ListarClientesAjax.do','#tableListar')
}
/**
*   Agregar 
**/
__agregar(){
    resultado = __agregarRegistro("#formulario",$.i18n.prop("cliente.mensaje.alert.agregar"),'AgregarClienteAjax.do','ListarClientesAjax.do','#tableListar')
     if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("cliente.mensaje.alert.agregar"),
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
                    url : 'AgregarClienteAjax.do',
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
                             swal({
	                           title: '',
	                           text: data.message,
	                           type: 'success',
	                           showCancelButton: false,
	                           confirmButtonText: 'Aceptar',
	                                	  
	                         })
	                         _incializarCampos()
                            self.cliente = data
                            self.update()
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
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarClientesAjax.do",
        datatype: "json",
        contentType: "application/json; charset=utf-8",
        method:"GET",
        success: function (result) {
             if(result.aaData.length > 0){
                __InformacionDataTable();
                loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
                includeActions('.dataTables_wrapper','.dataTables_length')
                agregarInputsCombos();
                ActivarEventoFiltro(".tableListar")
                __MantenimientoAgregar()
                __Eventos()
                __modificarRegistro_Listar()
                __ComboEstados()
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
                            {'data' :'empresa.nombre'     ,"name":"empresa.nombre"       ,"title" : $.i18n.prop("cliente.empresa")           ,"autoWidth" :false },
                            {'data' :'cedula'             ,"name":"cedula"               ,"title" : $.i18n.prop("cliente.cedula")             ,"autoWidth" :false },
                            {'data' :'nombreCompleto'     ,"name":"nombreCompleto"       ,"title" : $.i18n.prop("cliente.nombreCompleto")     ,"autoWidth" :false },
                            {'data' : 'celular'           ,"name":"celular"              ,"title" : $.i18n.prop("cliente.celular")             ,"autoWidth" :false},
                            {'data' : 'telefono'          ,"name":"telefono"             ,"title" : $.i18n.prop("cliente.telefono")           ,"autoWidth" :false},
                            {'data' : 'created_at'        ,"name":"created_at"           ,"title" : $.i18n.prop("cliente.created_at")         ,"autoWidth" :false,
                                "render":function(created_at,type, row){
                                      return __displayDate_detail(created_at);
                                 }
	      		            },
                            {'data' : 'updated_at'        ,"name":"updated_at"           ,"title" : $.i18n.prop("cliente.updated_at")         ,"autoWidth" :false,
                                "render":function(updated_at,type, row){
                                      return __displayDate_detail(updated_at);
                                 }
	      		            },
                            {'data' : 'estado'            ,"name":"estado"               ,"title" : $.i18n.prop("cliente.estado")            ,"autoWidth" :false},
                            {'data' : 'id'                ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}
/**
* Opciones listado de los clientes
*/
function __Opciones(){
  var modificar  = '<a href="#"  title="Modificar" class="btn btn-warning  btn-edit btnModificar" role="button"> </a>';
  return  modificar ;
}
/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        _incializarCampos()
        self.cliente = data
        self.update()

        __consultar()
	});
}
/**
*  Consultar  especifico
* 1  Mostrar  2  Modificar
**/
function __consultar(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarClienteAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        //Inicializar el Formulario
                        _incializarCampos()
                        //desahabilita  listado 
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar = false;
                        self.cliente  =  modeloTabla
                        self.update()
                        $('#nombreCompleto').val(self.cliente.nombreCompleto)
                        $('#cedula').val(self.cliente.cedula)
                        $('#telefono').val(self.cliente.telefono)
                        $('#celular').val(self.cliente.celular)
                        $('#descuento').val(self.cliente.descuento)
                        $('#correoElectronico').val(self.cliente.correoElectronico)
                        $('#otraSena').val(self.cliente.otraSena)
                        $('.identificacionExtranjero').val(self.cliente.identificacionExtranjero)
                        
                        $('.codigoPais').val(self.cliente.codigoPais)
                       
                        $('.nombreComercial').val(self.cliente.nombreComercial)
                        __ComboEstados()
                        __Eventos()
                        
                         $('.tipoCedula').val(self.cliente.tipoCedula)
                        
                        
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
 * Mostrar formulario de mantenimiento Agregar
 * */
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
        _incializarCampos()
   
    })
}
/**
*Formato de la fecha con hora
**/
function __displayDate_detail(fecha) {
    var dateTime = new Date(fecha);
    return moment(dateTime).format('DD/MM/YYYY h:mm:ss');
}
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 8    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
         // Select
    	if ($(this).index() == 7  ){
    	    var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}
</script>
</cliente-crud>