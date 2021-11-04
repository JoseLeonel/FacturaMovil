<crud-Usuario>
<!-- Titulos -->
<div  class="row titulo-encabezado"  >
    <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
        <h1 ><i class="fa fa-edit"></i>&nbsp{$.i18n.prop("usuario.usuarios")}  </h1>
        </div>
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
    </div>
</div>


<!-- Listado  -->
<div classs="contenedor-listar container" show={mostrarListado} >
    <div class="box">
		<div class="box-body">
			<div class="planel-body">
    <div  class="row">
        <div  class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="width:98.50%;">
            <table id="tableListar" class="responsive display nowrap tableListar"  cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th class="table-header" >{$.i18n.prop("usuario.empresa")}        </th>
                        <th class="table-header" >{$.i18n.prop("usuario.nombre")}         </th>
                        <th class="table-header" >{$.i18n.prop("usuario.primerApellido")} </th>
                        <th class="table-header" >{$.i18n.prop("usuario.segundoApellido")}</th>
                        <th class="table-header" >{$.i18n.prop("usuario.nombreUsuario")}  </th>
                        <th class="table-header" >{$.i18n.prop("usuario.estado")}         </th>
                          <th class="table-header" >Descuento Venta %         </th>
                        <th class="table-header" >{$.i18n.prop("btn.acciones")}           </th>
                    </tr>
                </thead>
                <tfoot style="display: table-header-group;">
					<tr>
                        <th>{$.i18n.prop("usuario.empresa")}         </th>
                        <th>{$.i18n.prop("usuario.nombreUsuario")}   </th>
                        <th>{$.i18n.prop("usuario.nombre")}          </th>
                        <th>{$.i18n.prop("usuario.primerApellido")}  </th>
                        <th>{$.i18n.prop("usuario.segundoApellido")} </th>
                         <th>Descuento Venta %   </th>
                        <th> {$.i18n.prop("usuario.estado")}         </th>
                        <th>                                         </th>
					</tr>
				</tfoot>
            </table>
        </div>
        </div>
        </div>
        </div>
    </div>    
</div>
<!-- Fin del Listado -->

<!-- Formulario-->
<div show ={mostrarFormulario} >
    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
    <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
       <div class="box box-solid box-primary">
            <div class="box-header with-border">
                <h1 show={btnAgregar} class="box-title"><i class="fa fa-plus"></i>&nbsp{$.i18n.prop("titulo.agregar.usuario")} </h1>
                <h1 show={btnModificar} class="box-title"><i class="fa fa-edit"></i>&nbsp{$.i18n.prop("titulo.modificar.usuario")} </h1>
            </div>
            <div class="box-body">
                <form id = "formulario" name ="formulario " class="advanced-search-form">
                    <input type="hidden" id="id" name="id" value="{modeloTabla.id}" />
                    <div class="row">
                        <div show={btnAgregar || btnModificar} class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                            <label class="campos-requeridos-label">Los campos con(*) son obligatorios</label>
                        </div>
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6"></div>
                    </div>
                    <div class="row">
                        <div class= "col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <label class="knob-label" >{$.i18n.prop("categoria.empresa")}  <span class="requeridoDato">*</span></label>
                            <select  class="form-control selectEmpresa" id="empresa" name="empresa" data-live-search="true" >
                               <option  data-tokens="{nombre}" each={empresas.aaData}  value="{id}"  >{nombre}</option>
                           </select>
                        </div>

                    </div>    
                    <div class="row">
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.nombre")} &nbsp<span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control nombre" placeholder = "{$.i18n.prop("usuario.nombre")}" title="{$.i18n.prop("usuario.nombre")}" name="nombre" id= "nombre"   value='{modeloTabla.nombre}' maxlength="55">
                            
                        </div>
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.primerApellido")}&nbsp<span class="requeridoDato">*</span></label>
                            <input type="text" class="form-control primerApellido " placeholder = "{$.i18n.prop("usuario.primerApellido")}" title="{$.i18n.prop("usuario.primerApellido")}" name="primerApellido" id= "primerApellido"   value='{modeloTabla.primerApellido}' maxlength="55">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.segundoApellido")}</label>
                            <input type="text" class="form-control segundoApellido" placeholder = "{$.i18n.prop("usuario.segundoApellido")}" title="{$.i18n.prop("usuario.segundoApellido")}" name="segundoApellido" id= "segundoApellido"   value='{modeloTabla.segundoApellido}' maxlength="55">
                        </div>
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.nombreUsuario")}&nbsp<span class="requeridoDato">*</span></label>
                            <input  type="text" class="form-control nombreUsuario " placeholder = "{$.i18n.prop("usuario.nombreUsuario")}" title="{$.i18n.prop("usuario.nombreUsuario")}" name="nombreUsuario" id= "nombreUsuario"   value='{modeloTabla.nombreUsuario}' maxlength="32">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sx-6 col-sm-6 col-lg-6">
                            <label class="knob-label">{$.i18n.prop("usuario.estado")}&nbsp<span class="requeridoDato">*</span></label>
                            <select id="estado" name="estado" class="form-control estado">
                                <option each={estados} value="{codigo}"  selected = "{modeloTabla.estado == codigo}" >{descripcion}</option>
                            </select>
                        </div>
                        <div class="col-md-6 col-sx-7 col-sm-6 col-lg-6">
                            <label class="knob-label">Descuento para la venta %<span class="requeridoDato">*</span></label>
                            <input   class="form-control descuentoVenta " onkeyup={ aplicarDescuentoGlobalInterfaz } onBlur = {aplicarDescuentoGlobalInterfaz}  type="number"  onkeypress = {aplicarDescuentoGlobalInterfaz}  step="any"   placeholder = "%" title="Descuento" name="descuentoVenta" id= "descuentoVenta"   value='{modeloTabla.descuentoVenta}' >
                        
                        </div>
                    </div>
                </form>    
            </div>
            <div class="box-footer">
                <button onclick ={__agregarRegistro}   type="button" show= {btnAgregar} class="btn-add btn-green pull-right" >
                    {$.i18n.prop("btn.agregar")}
                </button>
                <button onclick ={__modificarRegistro}   type="button" show= {btnModificar} class="btn-green btn-edit pull-right" >
                    {$.i18n.prop("btn.modificar")}
                </button>
                <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left"  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                    {$.i18n.prop("btn.volver")}
                </button>
       
            </div>
       </div>   
    </div>
    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div> 
</div>
<!-- Fin Formulario -->

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
    var self                   = this;
    self.formato               = {}
    self.estados               = []
    //Modelo a modificar a agregar 
    self.modeloTabla           = {
        nombre:"",
        primerApellido:"",
        segundoApellido:"",
        nombreUsuario:""
    }
      self.informacion_tabla   = []         // Formato del Listado de la Tabla Usuario
    self.usuarios              = {data:[]}  // definir el data del datatable Usuario 
    self.empresas              = {aaData:[]}
    self.roles                 = {aaData:[]}
    self.mostrarListado        = true       // Muestra el formulario  
    self.mostrarFormulario     = false      // Muestra el formulario  
    self.btnAgregar            = false      // boton agregar
    self.btnModificar          = false      // boton modificar
   
    //Montar el modal pero se llama al ajax para cargar la informacion 
    self.on('mount',function(){
           //Inicializar el Formulario
        $("#formulario").validate(reglasDeValidacion());
     
          //Formato de las columnas del dataTable
        __Fomarto_Campos_Listado()
     
       //Inicializar data Table
        __InicializarTabla('.tableListar')
        includeActionsUsuario('.dataTables_wrapper','.dataTables_length')
      //crea los filtros inputs o select
	    agregarInputsCombos();
        //Activar filtros
        ActivarEventoFiltro('.tableListar')
        __listado()

         //crear vector estados
        __ComboEstados()
          //Agregar
        __mostrarFormularioAgregar()
        //Modificar
        __modificarRegistro_Listar()
      __listadoEmpresasActivas()
      

        window.addEventListener( "keydown", function(evento){
             $(".errorServerSideJgrid").remove();
        }, false );

      

        
    });


aplicarDescuentoGlobalInterfaz(e){
    var descuentoVenta = __valorNumerico(e.target.value);
    if(descuentoVenta > 100){
        $('.descuentoVenta').val(100)
        // mensajeAlertErrorOConfirmacion('error',$.i18n.prop("error.descuento.global"));   
         return  	
    }
    self.usuario.descuentoVenta = descuentoVenta;
    
    
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
            if(result.aaData.length > 0){
                self.empresas.aaData =  result.aaData
                self.update();
                 $('.selectEmpresa').selectpicker();
            }            
        },
        error: function (xhr, status) {
            console.log(xhr);
             mensajeErrorServidor(xhr, status);
        }
    })
}
/**
* Reglas aplicadas
**/
var reglasDeValidacion = function() {
	var validationOptions = $.extend({}, formValidationDefaults, {
		rules : {
			nombre : {
				required : true
			},
            primerApellido : {
				required : true
			},
            nombreUsuario : {
				required : true
			}
        },
		ignore : []

	});
	return validationOptions;
};





/**
*                   Funciones de RIOT 
**/

/**
*  Regresa al listado
**/
__regresarAlListado(){
    self.mostrarListado        = true       // Muestra el formulario  
    self.mostrarFormulario     = false      // Muestra el formulario  
    self.btnAgregar            = false      // boton agregar
    self.btnModificar          = false      // boton modificar
    self.mostrarListadoSucursales = false
    self.mostarTituloUsuario      = true
    self.mostarTituloSucursal     = false
    self.mostrarFormularioAsociarSucursal = false
    self.modeloTabla               = {
        id:null,
        nombre:"",
        primerApellido:"",
        segundoApellido:"",
        nombreUsuario:""
    }
    self.update()
    __listado()
  
}

/**
*   Agregar 
**/
__agregarRegistro(){
    self.exito = false;
    self.update();
    __agregarRegistro("#formulario",$.i18n.prop("usuario.mensaje.alert.agregar"),'AgregarUsuarioAjax.do','ListarUsuariosAjax.do','#tableListar')
}

/**
** Modificar la Empresa
**/

__modificarRegistro(){
    self.exito = false
    self.update()
    __modificarRegistro("#formulario",$.i18n.prop("usuario.mensaje.alert.modificar"),'ModificarUsuarioAjax.do','ListarUsuariosAjax.do','#tableListar')
}

/**
*                   Funciones de JAVASCRIP
**/
/**
*----------------------------------Del Listado de la tabla de Mantenimiento ---------------------------------------------------------
**/

/**
*  Mostrar listado datatable
**/
function __listado(){
    
    $.ajax({
        url: "ListarUsuariosAjax.do",
        datatype: "json",
        method:"GET",
        success: function (result) {
            console.log(result)
             if(result.aaData.length > 0){
                __Fomarto_Campos_Listado()
                 loadListar(".tableListar",idioma_espanol,self.informacion_tabla,result.aaData)
              //   includeActionsUsuario('.dataTables_wrapper','.dataTables_length')
                agregarInputsCombos();
                ActivarEventoFiltro('.tableListar')
                __mostrarFormularioAgregar() 
                __modificarRegistro_Listar()

             }

        },
        error: function (xhr, status) {
            mensajeErrorServidor(xhr, status);
            console.log(xhr);
        }
    });

}

/**
*Formato del listado de los cambios
**/
function __Fomarto_Campos_Listado(){
    self.informacion_tabla =[	
                            {'data': 'empresa.nombre' ,"name": "empresa.nombre"  ,"title": $.i18n.prop("usuario.empresa") },
                            {'data': 'nombreUsuario'  ,"name": "nombreUsuario"  ,"title": $.i18n.prop("usuario.nombreUsuario") },
                            {'data': 'nombre'         ,"name": "nombre"         ,"title": $.i18n.prop("usuario.nombre")        },
                            {'data': 'primerApellido' ,"name": "primerApellido" ,"title": $.i18n.prop("usuario.primerApellido") },
                            {'data': 'segundoApellido',"name": "segundoApellido","title": $.i18n.prop("usuario.segundoApellido")},
                            {'data': 'descuentoVenta',"name": "descuentoVenta","title": 'Desc Venta %'},
                            {'data': 'strEstado'         ,"name": "strEstado"     ,"title": $.i18n.prop("usuario.estado"),
                                "render": function (id, type, row) {
                                       return row.strEstado;
                                    }
                            
                            },
                            {"bSortable": false, "bSearchable": false, 'data': 'id', "autoWidth": true, "name": "id",
                                "render": function (id, type, row) {
                                    var id = id.toString();
                                    return __mostrarOpcionesPorEstado(id, type, row);
                                }
                            }
						];
   self.update();
}
/****
 * Mostrar los Botones de acuerdo al estado de la solicitud
 * @param id
 * @param type
 * @param row
 * @returns {String} Botones de Mostrar y modificar
 */
function __mostrarOpcionesPorEstado(id,type,row){
  var modificar = '<a href="#"  title="Modificar" class="btn btn-warning  btn-edit btnModificar" role="button"> </a>';
  return modificar; 
}

/**
* incluir el boton agregar en cada mantenimiento usuario
**/
function includeActionsUsuario(dataTables_wrapper,dataTables_length) {
    $( ".btn-agregar" ).remove();
     $( ".btn-agregarSucursal" ).remove();
    var parent = $(dataTables_wrapper);
    var header_pointer = $(dataTables_length);
    var header_length = header_pointer.html();
    var new_header = "<div  class='new-header-with-actions' style='padding-top:0px; padding-bottom:0px;'>";
    new_header += "<div class='add-new btn-agregar' ><i class='fa fa-plus'></i> Agregar</div>";
    new_header += "</div>";
    parent.prepend(new_header);
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
        self.modeloTabla    = {
            id:null,
            nombre:"",
            primerApellido:"",
            segundoApellido:"",
            nombreUsuario:""
        }
        self.modeloTabla  = data
        self.update()
        __consultarUsuario()
	});
}
/**
*  Consultar usuario especifico
* 1  Mostrar  2  Modificar
**/
function __consultarUsuario(){
    var formulario = $('#formulario').serialize();
    $.ajax({
        url: "MostrarUsuarioAjax.do",
        datatype: "json",
        data: formulario,
        method:"GET",
        success: function (data) {
            if (data.status != 200) {
                if (data.message != null && data.message.length > 0) {
                    sweetAlert("Error...", data.message, "error");
                }
            }else{
                if (data.message != null && data.message.length > 0) {
                    $.each(data.listaObjetos, function( index, modeloTabla ) {
                        self.mostrarListado        = false       // Muestra el formulario  
                        self.mostrarFormulario     = true      // Muestra el formulario  
                        self.btnAgregar            = false      // boton agregar
                        self.btnModificar          = true      // boton modificar
                        self.mostrarListadoSucursales = false
                        self.mostarTituloUsuario      = true
                        self.mostarTituloSucursal     = false
                        self.mostrarFormularioAsociarSucursal = false
                        self.modeloTabla                = modeloTabla
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
/**
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ($(this).index() != 7    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
         // Select Tipo, 2 columna
        if ($(this).index() == 6  ){
            var select = $('<select id="comboTipoAmbiente" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
    	   	select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
        }
    })

}


/**
*  Mostrar el formulario de Agregar
**/
function __mostrarFormularioAgregar(){
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        self.mostrarListado        = false       // Muestra el formulario  
        self.mostrarFormulario     = true      // Muestra el formulario  
        self.btnAgregar            = true      // boton agregar
        self.btnModificar          = false      // boton modificar
        self.mostarTituloUsuario      = true
        self.mostarTituloSucursal     = false

        self.mostrarListadoSucursales = false
        self.mostrarFormularioAsociarSucursal = false
        self.usuario               = {
            nombre:"",
            primerApellido:"",
            segundoApellido:"",
            nombreUsuario:""
        }
        $('#usuario_nombre').val(null)
        $('#usuario_nombreUsuario').val(null) 
        $('#usuario_primerApellido').val(null)
        $('#usuario_segundoApellido').val(null)
        $("#formulario").validate(reglasDeValidacion());
        self.update()
          //Inicializar el Formulario
      $(".errorServerSideJgrid").remove();
   
    })
}
/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.estados.push({
        codigo: 1,
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: 2,
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();

}
// Funcion estandar para presentar los errores validaciones del servidor
function serverMessageJson(data) {
    console.log(data)
	// Se elimina el class de error anterior
	$(".errorServerSideJgrid").remove();
    $(".errorServerSideJgrid").show();

	// Si existe un error se despliega debajo de cada campo
	if (data.status != null && data.status != 200) {
        data.listaObjetos.forEach(function(fieldError) {
				// Id del campo en el formulario
				var selector = "." + fieldError.field;
				// Leonel Para poder borrar los mensajes de error al presionar
				// el clic
				var selectorMensaje = "error" + fieldError.field;
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
*----------------------------------Fin  Listado de la tabla de Mantenimiento ---------------------------------------------------------
**/
</script>
</crud-Usuario>