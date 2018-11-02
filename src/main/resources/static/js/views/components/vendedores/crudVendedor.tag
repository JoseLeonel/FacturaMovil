<vendedor-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("vendedor.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("vendedor.cedula")}             </th>
                                <th class="table-header" >{$.i18n.prop("vendedor.nombreCompleto")}     </th>
                                <th class="table-header" >{$.i18n.prop("vendedor.telefono")}           </th>
                                <th class="table-header" >{$.i18n.prop("vendedor.celular")}            </th>
                                <th class="table-header" >{$.i18n.prop("vendedor.correoElectronico")}  </th>
                                <th class="table-header" >{$.i18n.prop("vendedor.estado")}             </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}           </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("vendedor.cedula")}             </th>
                                <th>{$.i18n.prop("vendedor.nombreCompleto")}     </th>
                                <th>{$.i18n.prop("vendedor.telefono")}           </th>
                                <th>{$.i18n.prop("vendedor.celular")}            </th>
                                <th>{$.i18n.prop("vendedor.correoElectronico")}  </th>
                                <th>{$.i18n.prop("vendedor.estado")}             </th>
                                <th >        </th>
                            </tr>
                        </tfoot>
                    </table>
            </div>
        </div>    
</div>
<!-- Fin del Listado -->

 

<div class="row center " show ={mostrarFormulario} >
    <div class="col-md-2 col-lg-2 col-sm-2"></div>
        <div class="col-md-8 col-lg-8 col-sx-12 col-sm-8">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {dataTable.id > 0 ? $.i18n.prop("titulo.modificar.vendedor")   :$.i18n.prop("titulo.agregar.vendedor")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{dataTable.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("vendedor.nombreCompleto")} <span class="requeridoDato">*</span></label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.nombreCompleto")}" class="form-control nombreCompleto" id="nombreCompleto" name="nombreCompleto" value="{dataTable.nombreCompleto}"  >
                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("vendedor.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.cedula")}" class="form-control cedula" id="cedula" name="cedula" value='{dataTable.cedula}'  >
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("vendedor.telefono")} </label>
                                <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("vendedor.telefono")}" id="telefono" name="telefono" value="{dataTable.telefono}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("vendedor.celular")} </label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.celular")}" class="form-control celular" id="celular" name="celular" value="{dataTable.celular}"  >
                            </div>

                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("vendedor.descuento")} </label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.descuento")}" class="form-control descuento" id="descuento" name="descuento" value="{dataTable.descuento}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("vendedor.porcentajeComision")} </label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.porcentajeComision")}" class="form-control porcentajeComision" id="porcentajeComision" name="porcentajeComision" value="{dataTable.porcentajeComision}"  >
                            </div>

                        </div>

                         <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("vendedor.correoElectronico")}</label>
                                <input type="text" placeHolder ="{$.i18n.prop("vendedor.correoElectronico")}" class="form-control correoElectronico" id="correoElectronico" name="correoElectronico" value="{dataTable.correoElectronico}"  >
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("vendedor.otraSena")}</label>
                                <textarea maxlength="250" placeHolder ="{$.i18n.prop("vendedor.otraSena")}" class="form-control otraSena" id="otraSena" name="otraSena" value="{dataTable.otraSena}" > </textarea> 
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label">{$.i18n.prop("vendedor.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{dataTable.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>

                    </form>    
                </div>
                <div class="box-footer">
                    <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                        {$.i18n.prop("btn.volver")}
                    </button>
                     <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" > &nbsp {$.i18n.prop("btn.modificar")}</button>
                     <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" >&nbsp {$.i18n.prop("btn.agregar")}</button>
                  
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
    border-width: 5px;
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
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.empresas                = {aaData:[]}
    self.dataTable                 = {
		id:null,
		nombreCompleto:"",
        cedula:"",
        celular:"",
        telefono:"",
        correoElectronico:"",
        otraSena:"",
        descuento:0,
        porcentajeComision:0,
        estado:"",
        created_at:null,
		updated_at:null,
		sucursal:{
            id:0
        }
    }


self.on('mount',function(){
   

    _inicializarCampos()
    __InicializarTabla('.tableListar')
    agregarInputsCombos()
    ActivarEventoFiltro('.tableListar')
      includeActions('.dataTables_wrapper','.dataTables_length')
    __listado()
    __MantenimientoAgregar()
    __ComboEstados()

    window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );
  
})
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
                maxlength:255,
                minlength:1,
			},
			otraSenas : {
                maxlength:255,
                minlength:1,
			},
			correoElectronico : {
                maxlength:255,
                minlength:1,
                email:true
			},
            telefono : {
                maxlength:8,
                minlength:1,
                telefonoFormat:true
			},
            celular : {
                maxlength:8,
                minlength:1,
                telefonoFormat:true
			}   

		},
		ignore : []

	});
	return validationOptions;
};

/**
*  Limpiar campos
**/
function _inicializarCampos(){
    $(".nombreCompleto").val(null)
    $(".cedula").val(null)
    $('.telefono').val(null)
    $('.celular').val(null)
    $('.descuento').val(null)
    $('.porcentajeComision').val(null)
    $('.correoElectronico').val(null)
    $('.otraSena').val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
    self.dataTable  = {
		id:null,
		nombreCompleto:"",
        cedula:"",
        celular:"",
        telefono:"",
        correoElectronico:"",
        otraSena:"",
        descuento:0,
        porcentajeComision:0,
        estado:"",
        created_at:null,
		updated_at:null,
		sucursal:{
            id:0
        }
    }
    self.update()
}

/**
*  Activar Eventos
**/

function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#nombreCompleto").attr("maxlength", 255);
    $("#cedula").attr("maxlength", 20);
    $("#correoElectronico").attr("maxlength", 250);
    $("#otraSenas").attr("maxlength", 250);
    $("#direccion").attr("maxlength", 255);
    $("#telefono").attr("maxlength", 8);
    $("#celular").attr("maxlength", 8);
    
    $('.descuento').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('.porcentajeComision').mask('000', {
		'translation' : {
			0 : {
				pattern : /[0-9]/
			}
		}
	});
    $('#telefono').mask('00000000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})   
    $('#celular').mask('00000000', {
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
    _inicializarCampos()
    __listado();
}
/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
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
*   Agregar 
**/
__agregar(){
   
      if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
        swal({
           title: '',
           text: $.i18n.prop("vendedor.mensaje.alert.agregar"),
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
                    url : 'AgregarVendedorAjax.do',
                    success : function(data) {
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
                             _inicializarCampos()
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
    __modificarRegistro("#formulario",$.i18n.prop("vendedor.mensaje.alert.modificar"),'ModificarVendedorAjax.do','ListarVendedoresAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarVendedoresAjax.do",
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
                __ComboEstados()
                __modificarRegistro_Listar()
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
    self.informacion_tabla = [ {'data' :'cedula'          ,"name":"cedula"               ,"title" : $.i18n.prop("empresa.cedula")             ,"autoWidth" :true },
                            {'data' :'nombreCompleto'     ,"name":"nombreCompleto"       ,"title" : $.i18n.prop("vendedor.nombreCompleto")    ,"autoWidth" :false },
                            {'data' :'telefono'           ,"name":"telefono"             ,"title" : $.i18n.prop("vendedor.telefono")          ,"autoWidth" :false },
                            {'data' :'celular'             ,"name":"celular"             ,"title" : $.i18n.prop("vendedor.celular")           ,"autoWidth" :false },
                            {'data' : 'correoElectronico' ,"name":"correoElectronico"    ,"title" : $.i18n.prop("vendedor.correoElectronico") ,"autoWidth" :false},
                            {'data' : 'estado'            ,"name":"estado"               ,"title" : $.i18n.prop("vendedor.estado")            ,"autoWidth" :false},
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
/***
* Mostrar formulario de mantenimiento Agregar
**/
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
        self.update();

        _inicializarCampos()
        __Eventos()
   
    })
}
/**
 * Funcion para Modificar del Listar
 */
function __modificarRegistro_Listar(){
	$('#tableListar').on('click','.btnModificar',function(e){
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
        }
        _inicializarCampos()
	    self.dataTable = data
        self.update()
        __Eventos()        
        __consultar()
	});
}

/**
*  Consultar  especifico

**/
function __consultar(){
      
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarVendedorAjax.do",
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
                        $(".errorServerSideJgrid").remove();
                        $("#formulario").validate(reglasDeValidacion());
                         
                        //desahabilita  listado 
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar     = false;
                        _inicializarCampos()
                         self.dataTable  =  modeloTabla
                        self.update()
                         $("#nombreCompleto").val(self.dataTable.nombreCompleto);
                        $("#cedula").val(self.dataTable.cedula);
                        $("#correoElectronico").val(self.dataTable.correoElectronico);
                        $("#direccion").val(self.dataTable.direccion);
                        $("#telefono").val(self.dataTable.telefono);
                        $("#celular").val(self.dataTable.celular);
                        $('.descuento').val(self.dataTable.descuento);
                        $("#porcentajeComision").val(self.dataTable.porcentajeComision)
                        $("#otraSena").val(self.dataTable.otraSena)
                        __ComboEstados()
                        __Eventos()
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
</vendedor-crud>