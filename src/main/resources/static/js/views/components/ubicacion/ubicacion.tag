<ubicacion-crud>
    <!-- Titulos -->
    <div  class="row titulo-encabezado"  >
        <div  class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <h1 ><i class="fa fa-cog"></i>&nbsp {$.i18n.prop("ubicacion.titulo")}  </h1>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"></div>
    </div>
    
<!-- Listado  -->
<div classs="contenedor-listar container " id="container"  show={mostrarListado}  >
 <div class="box">
                <div class="box-body">
                    <div class="planel-body" >
        <div class="row">
            <div class="col-sx-12  col-lg-12  col-md-12 col-sm-12 tableListar_ajuste " >
                    <table id="tableListar" class="display table responsive table-hover nowrap table-condensed  tableListar"   cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="table-header" >{$.i18n.prop("ubicacion.descripcion")}   </th>
                                <th class="table-header" >{$.i18n.prop("ubicacion.estado")}        </th>
                                <th class="table-header" > {$.i18n.prop("listado.acciones")}   </th>
                            </tr>
                        </thead>
                        <tfoot style="display: table-header-group;">
                            <tr>
                                <th>{$.i18n.prop("ubicacion.descripcion")}   </th>
                                <th>{$.i18n.prop("ubicacion.estado")}        </th>
                                <th>  </th>
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

<div  >
    <div class="row center " show ={mostrarFormulario} >
    <div class=" col-lg-4 "></div>
        <div class="col-md-12 col-lg-4 col-sx-12 col-sm-12">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h1 class="box-title"><i class="fa fa-edit"></i>&nbsp {ubicacion.id > 0 ? $.i18n.prop("ubicacion.modificar")   :$.i18n.prop("ubicacion.agregar")}     </h1>
                </div>
                <div class="box-body">
                    <form id = "formulario" name ="formulario "   class="advanced-search-form">
                        <input type="hidden" name="id" id="id" value="{ubicacion.id}">
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12 left">
                                <label class="campos-requeridos-label">{$.i18n.prop("mensaje.campos.obligatorios")} </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class= "col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label  >{$.i18n.prop("marca.descripcion")}  <span class="requeridoDato">*</span></label>
                                <input type="text" class="form-control descripcion" placeHolder ="{$.i18n.prop("marca.descripcion")}" id="descripcion" name="descripcion" value="{ubicacion.descripcion}"  >

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 col-sx-12 col-sm-12 col-lg-12">
                                <label >{$.i18n.prop("ubicacion.estado")}</label>
                                <select  class="form-control" id="estado" name="estado" >
                                    <option  each={estados}  value="{codigo}" selected="{ubicacion.estado ==codigo?true:false}" >{descripcion}</option>
                                </select>
                            </div>
                        </div>
                    </form>    
                </div>
                <div class="box-footer">
                    <div class="row">
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button onclick ={__regresarAlListado}  type="button" class="btn-dark-gray btn-back pull-left "  id= "btnCancelarEmpresa" name = "btnCancelarEmpresa">
                                {$.i18n.prop("btn.volver")}
                            </button>
                       </div>
                       <div class="col-md-6 col-sx-12 col-sm-6 col-lg-6">
                            <button  onclick={__Modificar} show={botonModificar}  class="btn-green btn-edit pull-right" >  {$.i18n.prop("btn.modificar")}</button>
                            <button show = {botonAgregar}   onclick={__agregar}   class="btn-green btn-add pull-right" > {$.i18n.prop("btn.agregar")}</button>
                       </div>
                  
                </div>
            </div>   
        </div>
        <div class="col-lg-4 "></div>
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
    self.empresas                  = {aaData:[]}
    self.mostrarListado            = true 
    self.botonModificar            = false
    self.botonAgregar              = false
    self.ubicacion = {
        id:null,
        descripcion:"",
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
    __ComboEstados()
    Limpiar()
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
			descripcion : {
				required : true,
                maxlength:80,
                minlength:1,
			}                                   
                        
		},
		ignore : []

	});
	return validationOptions;
};

/**
* Limpiar
**/
function Limpiar(){
    $("#descripcion").val(null)
    $(".errorServerSideJgrid").remove();
    $("#formulario").validate(reglasDeValidacion());
    self.marca = {
        id:null,
        descripcion:"",
        estado:""
    }
    self.update()
    __ComboEstados()
}

/**
*  Crear el combo de estados
**/
function __ComboEstados(){
    self.estados =[]
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Activo"),
        descripcion:$.i18n.prop("combo.estado.Activo")
     });
    self.estados.push({
        codigo: $.i18n.prop("combo.estado.Inactivo"),
        descripcion: $.i18n.prop("combo.estado.Inactivo")
     });
     self.update();

}
/**
*  Activar Eventos
**/

function __Eventos(){
    $("#formulario").validate(reglasDeValidacion());
    $("#descripcion").attr("maxlength", 80);

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
    Limpiar()
    __listado();

   
}

// Mostrar formulario de mantenimiento Agregar
function __MantenimientoAgregar(){
      //Inicializar el Formulario
    $('.dataTables_wrapper').on('click','.btn-agregar',function(e){
        self.ubicacion    = {};                // modelo o domain   
        
        //desahabilita  listado 
        self.mostrarListado   = false;
        self.mostrarFormulario  = true 
        //desahabilita boton modificar
        self.botonModificar   = false;
        // habilita el formulario
        self.botonAgregar     = true;
        self.update();
        //Inicializar el Formulario
        Limpiar()
   
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
        Limpiar()
        self.ubicacion  = data
        self.update()
        $("#descripcion").val(self.ubicacion.descripcion);
        __Eventos()
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
        url: "MostrarUbicacionAjax.do",
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
                    //desahabilita  listado 
                        Limpiar()
                        self.mostrarListado   = false;
                        self.mostrarFormulario  = true 
                        //desahabilita boton modificar
                        self.botonModificar   = true;
                        // habilita el formulario
                        self.botonAgregar     = false;                        
                        self.ubicacion  =  modeloTabla
                        self.update()
                        $("#descripcion").val(self.ubicacion.descripcion);
                        __Eventos()
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
*   Agregar 
**/
__agregar(){
   
    if ($("#formulario").valid()) {
        // Permite obtener todos los valores de los elementos del form del jsp
        var formulario = $("#formulario").serialize();
                $.ajax({
                    type : "POST",
                    dataType : "json",
                    data : formulario,
                    url : 'AgregarUbicacionAjax.do',
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
                             Limpiar()
                              __Eventos()
                            
                        }
                    },
                    error : function(xhr, status) {
                        console.log(xhr);
                        mensajeErrorServidor(xhr, status);
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
    __modificarRegistro("#formulario",$.i18n.prop("ubicacion.mensaje.alert.modificar"),'ModificarUbicacionAjax.do','ListarUbicacionAjax.do','#tableListar')
}

/**
*  Mostrar listado datatable
**/
function __listado(){
    $("#tableListar").dataTable().fnClearTable(); 
    $.ajax({
        url: "ListarUbicacionAjax.do",
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
                __modificarRegistro_Listar()
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
                               {'data' :'descripcion'    ,"name":"descripcion"     ,"title" : $.i18n.prop("marca.descripcion") ,"autoWidth" :true },
                               {'data' : 'estado'        ,"name":"estado"          ,"title" : $.i18n.prop("marca.estado")      ,"autoWidth" :false,
                                "render":function(estado,type, row){
                                 return estadosActivoInactivo(estado,row);//factura.js
                                }},
                               {'data' : 'id'            ,"name":"id" ,"bSortable" : false, "bSearchable" : false, "autoWidth" : true,
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
*  Agregar los inpust  y select de las tablas
**/
function agregarInputsCombos(){
     // Agregar los input de busqueda 
    $('.tableListar tfoot th').each( function (e) {
        var title = $('.tableListar thead th').eq($(this).index()).text();      
        //No se toma en cuenta la columna de las acctiones(botones)
        if ( $(this).index() != 2    ){
	      	$(this).html( '<input id = "filtroCampos" type="text" class="form-control"  placeholder="'+title+'" />' );
	    }
        // Select
    	if ($(this).index() == 1  ){
    	    var select = $('<select id="combo" class="form-control"><option value="">Todos</option></select>');
    	    // se cargan los valores por defecto que existen en el combo
    	   	select.append( '<option value="'+$.i18n.prop("estado.Activo")+'">'+$.i18n.prop("estado.Activo")+'</option>' );
            select.append( '<option value="'+$.i18n.prop("estado.Inactivo")+'">'+$.i18n.prop("estado.Inactivo")+'</option>' );
    	   	$(this).html(select);
    	}
 
    })

}


</script>
</ubicacion-crud>