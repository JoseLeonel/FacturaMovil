<proveedor-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("proveedor.titulo")}  </h1>
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
                                <th class="table-header" >{$.i18n.prop("proveedor.cedula")}            </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.nombreCompleto")}    </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.movil")}             </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.telefono")}          </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.created_at")}        </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.updated_at")}        </th>
                                <th class="table-header" >{$.i18n.prop("proveedor.estado")}            </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}           </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("proveedor.cedula")}            </th>
                                <th>{$.i18n.prop("proveedor.nombreCompleto")}    </th>
                                <th>{$.i18n.prop("proveedor.movil")}             </th>
                                <th>{$.i18n.prop("proveedor.telefono")}          </th>
                                <th>{$.i18n.prop("proveedor.created_at")}        </th>
                                <th>{$.i18n.prop("proveedor.updated_at")}        </th>
                                <th>{$.i18n.prop("proveedor.estado")}            </th>
                                <th> </th>

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
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {proveedor.id > 0 ? $.i18n.prop("titulo.modificar.proveedor")   :$.i18n.prop("titulo.agregar.proveedor")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{proveedor.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("proveedor.empresa")}  <span class="requeridoDato">*</span></label>
                                 <select  class="form-control" id="empresa" name="empresa" >
                                    <option  each={empresas.aaData}  value="{id}"  >{nombre}</option>
                                </select>
                            </div>
                        </div>    
                        
                        <div class="row">
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("proveedor.nombreCompleto")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control nombreCompleto" placeHolder ="{$.i18n.prop("proveedor.nombreCompleto")}" id="nombreCompleto" name="nombreCompleto" value="{proveedor.nombreCompleto}"  >

                            </div>
                            <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label" >{$.i18n.prop("proveedor.cedula")} <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control cedula" placeHolder ="{$.i18n.prop("proveedor.cedula")}" id="cedula" name="cedula" value="{proveedor.cedula}"  >
                            </div>
                            
                        </div>
                        <div class="row">
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("proveedor.telefono")} </label>
                                <input type="text" class="form-control telefono" placeHolder ="{$.i18n.prop("proveedor.telefono")}" id="telefono" name="telefono" value="{proveedor.telefono}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("proveedor.movil")} </label>
                                <input type="text" class="form-control movil" placeHolder ="{$.i18n.prop("proveedor.movil")}" id="movil" name="movil" value="{proveedor.movil}"  >
                            </div>
                            <div class= "col-md-3 col-sx-12 col-sm-3 col-lg-3">
                                <label class="knob-label" >{$.i18n.prop("proveedor.representante")} </label>
                                <input type="text" class="form-control representante" placeHolder ="{$.i18n.prop("proveedor.representante")}" id="representante" name="representante" value="{proveedor.representante}"  >
                            </div>

                        </div>
                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("proveedor.email")}</label>
                                <input type="text" class="form-control email" placeHolder ="{$.i18n.prop("proveedor.email")}" id="email" name="email" value="{proveedor.email}"  >
                            </div>
                        </div>

                        <div class="row">    
                            <div class= "col-md-3 col-sx-12 col-sm-12 col-lg-12">
                                <label class="knob-label" >{$.i18n.prop("proveedor.direccion")}</label>
                                <input type="text" class="form-control direccion" placeHolder ="{$.i18n.prop("proveedor.direccion")}" id="direccion" name="direccion" value="{proveedor.direccion}"  >
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                                <label class="knob-label">{$.i18n.prop("proveedor.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{proveedor.estado ==codigo?true:false}" >{descripcion}</option>
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
        border-width: 5px;proveedor
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
    self.empresas                  = {aaData:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.proveedor                 ={
            id:null,
            cedula:"",
            razonSocial:"",
            nombreCompleto:"",
            representante:"",
            email:"",
            telefono:"",
            movil:"",
            direccion:"",
            estado:"",
            created_at:"",
            updated_at:"",
            empresa:{
                id:0
            }
    }



self.on('mount',function(){
    $("#formulario").validate(reglasDeValidacion());
    __InicializarTabla('.tableListar')
    __listado()
    __listadoEmpresasActivas()
    __Eventos()
    includeActions('.dataTables_wrapper','.dataTables_length')
    __MantenimientoAgregar()
    __ComboEstados()
    agregarInputsCombos();
    ActivarEventoFiltro(".tableListar")
          
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
                minlength:1,
			},

			nombreCompleto : {
				required : true,
                maxlength:150,
                minlength:1,
			},
            representante : {
                maxlength:150,
                minlength:1,
			},
			email : {
                maxlength:200,
                minlength:1,
                email:true
			},
            telefono : {
                maxlength:9,
                minlength:1,
                telefonoFormat:true
			},
            movil : {
                maxlength:9,
                minlength:1,
                telefonoFormat:true
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
    $("#nombreCompleto").attr("maxlength", 150);
    $("#representante").attr("maxlength", 150);
    $("#cedula").attr("maxlength", 20);
    $("#email").attr("maxlength", 200);
    $("#direccion").attr("maxlength", 160);
    $("#telefono").attr("maxlength", 9);
    $("#movil").attr("maxlength", 9);
    $('#telefono').mask('0000-0000', {
            'translation' : {
                0 : {
                    pattern : /[0-9]/
                }
            }
	})   
    $('#movil').mask('0000-0000', {
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
                self.update()
                
                __listado();

            }
    });    
}

/**
*  Mostrar listado datatable Empresas Activas
**/
function __listadoEmpresasActivas(){
    $.ajax({
         url: "ListarEmpresasActivasAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            console.log(result)
            if(result.aaData.length > 0){
                self.empresas.aaData =  result.aaData
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
** Modificar la Empresa
**/

__Modificar(){
    self.error = false;
    self.exito = false;
    self.update();
    __modificarRegistro("#formulario",$.i18n.prop("proveedor.mensaje.alert.modificar"),'ModificarProveedorAjax.do','ListarProveedoresAjax.do','#tableListar')
}
/**
*   Agregar 
**/
__agregar(){
    __agregarRegistro("#formulario",$.i18n.prop("proveedor.mensaje.alert.agregar"),'AgregarProveedorAjax.do','ListarProveedoresAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarProveedoresAjax.do",
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
                            {'data' :'cedula'             ,"name":"cedula"             ,"title" : $.i18n.prop("proveedor.cedula")             ,"autoWidth" :false },
                            {'data' :'nombreCompleto'     ,"name":"nombreCompleto"     ,"title" : $.i18n.prop("proveedor.nombreCompleto")     ,"autoWidth" :false },
                            {'data' :'movil'              ,"name":"movil"              ,"title" : $.i18n.prop("proveedor.movil")              ,"autoWidth" :false},
                            {'data' :'telefono'           ,"name":"telefono"           ,"title" : $.i18n.prop("proveedor.telefono")           ,"autoWidth" :false},
                            {'data' :'created_at'         ,"name":"created_at"         ,"title" : $.i18n.prop("proveedor.created_at")         ,"autoWidth" :false,
                                "render":function(created_at,type, row){
                                      return __displayDate_detail(created_at);
                                 }
	      		            },
                            {'data' : 'updated_at'        ,"name":"updated_at"           ,"title" : $.i18n.prop("proveedor.updated_at")         ,"autoWidth" :false,
                                "render":function(updated_at,type, row){
                                      return __displayDate_detail(updated_at);
                                 }
	      		            },
                            {'data' : 'estado'            ,"name":"estado"               ,"title" : $.i18n.prop("proveedor.estado")            ,"autoWidth" :false},
                            {'data' : 'id'                ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
                                "render":function(id,type, row){
                                      return __Opciones(id,type,row);
                                 }
	      		            }];
    self.update();
   
}

                                    
/**
* Opciones listado de los proveedors
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
        $("#formulario").validate(reglasDeValidacion());
        $(".errorServerSideJgrid").remove();
		var table = $('#tableListar').DataTable();
		if(table.row(this).child.isShown()){
			//cuando el datatable esta en modo responsive
	       var data = table.row(this).data();
	    }else{	
	       var data = table.row($(this).parents("tr")).data();
	    }
        
        
          
        self.proveedor = data
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
        url: "MostrarProveedorAjax.do",
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
                        self.botonAgregar = false;
                        self.proveedor  =  modeloTabla
                        self.update()
                       
                        __ComboEstados()
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
        self.proveedor    = {};     
        
        self.update();
     
        
         
        //Inicializar el Formulario
        $(".errorServerSideJgrid").remove();
        $("#formulario").validate(reglasDeValidacion());
   
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
        if ( $(this).index() != 7    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
         // Select
    	if ($(this).index() == 6  ){
    	    var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}



</script>
</proveedor-crud>